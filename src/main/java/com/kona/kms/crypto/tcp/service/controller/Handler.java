package com.kona.kms.crypto.tcp.service.controller;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.kona.kms.crypto.CryptoManager;
import com.kona.kms.crypto.tcp.utility.ServerState;
import com.kona.kms.crypto.tcp.utility.TransactionCode;
import com.kona.kms.KmsException;
import com.kona.kms.crypto.tcp.service.wrapper.*;
import com.kona.kms.crypto.tcp.model.*;
import com.kona.kms.token.KeyManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/*
 * Handler with Thread Pool.
 * The idea is to put time-consuming operation into other threads.
 * In this way, the Handler can only focus on the IO operation with Channel.
 * Handler can do read or write operation with Channel, so Channel can response to other requires more quickly and timely.
 * After the time-consuming operatioin, a event (change state) can be created.
 * Whenever Handler find the change of the state, it process the read or write operation with Channel.
 */
public class Handler implements Runnable {
	private static final Logger logger = LoggerFactory.getLogger(Handler.class);
	
	private final SocketChannel socketChannel;
	private final SelectionKey 	selectionKey;
	
	private final static Object	lock = new Object();
	private ServerState svrState;

	private static ExecutorService pool = Executors.newFixedThreadPool(10);
//	private static ExecutorService pool = Executors.newCachedThreadPool();
	
	private OutputModel outputModel = null;
	
	/**
	 * Set interestOps in Handler, this interestOps can be changed along with the process of event.
	 * @param selector
	 * @param channel
	 * @throws IOException
	 */
	public Handler(Selector selector, SocketChannel channel) throws IOException {
//		logger.debug("[Handler] Handler start............\n");
		
		svrState		= ServerState.READING;
		socketChannel 	= channel;
		this.socketChannel.configureBlocking(false);
		selectionKey = socketChannel.register(selector, 0);
//		synchronized(lock){
			
			//Register this thread to selectionKey
			selectionKey.attach(this);
			selectionKey.interestOps(SelectionKey.OP_READ);
			selector.wakeup();
	
//		}
//		logger.debug("[Handler] Handler constructor class end............\n");
	}

	
	/*
	 * (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	public void run() {
		
		try {
			
			switch(svrState){
	
				case READING:
					read();
					break;
				
				case SENDING:
					send();
					break;
					
				default:
					break;
			}
			
		} catch (IOException ex) {
			ex.printStackTrace();
		} catch (KmsException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * @throws IOException
	 * @throws KmsException
	 */
	private void read() throws IOException, KmsException {
//		logger.debug("[Handler] Read start..............\n");
		
		int 		readCount	= 0;
		byte[] 		msgHeader 	= new byte[44];
		byte[] 		msgBody		= null;
		int 		msgBodyLen	= 0;
		MSGHeaderModel msgHeaderModel = new MSGHeaderModel(null);
		
		ByteBuffer 	msgHeadBuffer	= null;
		ByteBuffer 	msgBodyBuffer	= null;
		
		msgHeadBuffer=ByteBuffer.wrap(msgHeader);
		
		//msgHeadBuffer.flip();
	
		try{
//			logger.debug("Before socketchannel read header \n");
			readCount = socketChannel.read(msgHeadBuffer);
//			logger.debug("After socketchannel read header \n");
//			logger.debug("readCount: " + readCount);
			
		} catch (Exception ex) {
			this.socketChannel.socket().close();
			this.socketChannel.close();
			selectionKey.cancel();
			
//			logger.error(ex.toString());
//			ex.printStackTrace();
		}
		
		if (readCount <= 0) {
			this.socketChannel.socket().close();
			this.socketChannel.close();
			selectionKey.cancel();
			return;
		}
		
		msgHeadBuffer.flip();
	
		//parse message header
//		logger.debug("Before parsing header \n");
		msgHeaderModel.parseMsgHeader(msgHeadBuffer);
//		logger.debug("After parsing header \n");
		
		msgBodyLen =  msgHeaderModel.getMsgBodyLen();
//		logger.debug("msgBodyLen: " + msgBodyLen);
		
		msgHeadBuffer.clear();
		
//		logger.debug("Before allocating body array \n");
		msgBody 	  = new byte[msgBodyLen];
//		logger.debug("After allocating body array \n");
		
		msgBodyBuffer = ByteBuffer.wrap(msgBody);
	
//		logger.debug("Before socketchannel read body \n");
		readCount = socketChannel.read(msgBodyBuffer);
//		logger.debug("After socketchannel read body \n");
		if (readCount <= 0) {
			this.socketChannel.socket().close();
			this.socketChannel.close();
			selectionKey.cancel();
			
			return;
		}
		
//		logger.debug("[Handler] Read end..............\n");
		
		

		svrState	= ServerState.PROCESSING;
		pool.execute(new Processer(msgHeaderModel, msgBodyBuffer));
		selectionKey.interestOps(SelectionKey.OP_WRITE);
//		return;
	}

	private void send() throws IOException {
//		logger.debug("Send method start..............\n");
		ByteBuffer 	output 				= null;
		byte[] 		outputToBufferArray = null; 
		
		outputToBufferArray = new byte[outputModel.getOutPutSize()];
		outputToBufferArray = outputModel.getServerMsg();
		
		String serverMsgString = new String(outputToBufferArray);
//		logger.debug("serverMsgString: " + serverMsgString);
		
		String totalLenString = new String(outputModel.getTotalLen());
		Integer totalLenInt = Integer.parseInt(totalLenString);
//		logger.debug("totalLenString: " + totalLenString);
		
		
		output = ByteBuffer.wrap(outputToBufferArray);
		
//		String debugServerMsg = new String(outputToBufferArray);
//		int debugServerMsgLen = debugServerMsg.length();
		
//		logger.debug("debugServerMsg: " + debugServerMsg);
//		logger.debug("debugServerMsgLen: " + debugServerMsgLen);
		
		
		socketChannel.write(output); //send msg to client
		
		logger.debug("Send to Client.............\n");
		
		selectionKey.interestOps(SelectionKey.OP_READ);		
		svrState = ServerState.READING;
	}
		
	class Processer implements Runnable {
		
		private MSGHeaderModel 		msgHeaderModel 	= null;
		private ByteBuffer 	msgBodyBuffer 	= null;

		/**
		 * 
		 * @param trCodeString
		 * @param msgBodyBuffer
		 */
		public Processer(MSGHeaderModel msgHeaderModel, ByteBuffer msgBodyBuffer) {
			this.msgHeaderModel = msgHeaderModel;
			this.msgBodyBuffer = msgBodyBuffer;
		}

		/*
		 * (non-Javadoc)
		 * @see java.lang.Runnable#run()
		 */
		public void run() {
			try {
//				logger.debug("Before process \n");
				processAndHandOff(msgHeaderModel, msgBodyBuffer);
//				logger.debug("After process \n");
			} catch (KmsException e) {
				// TODO Auto-generated catch block
				logger.error(e.toString());
				e.printStackTrace();
			}
		}
	}

	/**
	 * 
	 * @param msgHeaderModel
	 * @param msgBodyBuffer
	 * @throws KmsException
	 */
	synchronized void processAndHandOff(MSGHeaderModel msgHeaderModel, ByteBuffer msgBodyBuffer) throws KmsException {
		this.outputModel = this.processDecodeCryptEncode(msgHeaderModel, msgBodyBuffer);
		this.svrState = ServerState.SENDING;
	}

	
	/**
	 * 
	 * @param msgHeaderModel
	 * @param msgBodyBuffer
	 * @throws KmsException
	 */
	public synchronized OutputModel processDecodeCryptEncode(MSGHeaderModel msgHeaderModel, ByteBuffer msgBodyBuffer) throws KmsException {
		
//		byte[]				outputByte	= null;
		TransactionCode 	trCodeValue = null;
		HandlerInterface	handlerInf	= null;
		
		msgBodyBuffer.flip(); // from writing mode to reading mode
		
		trCodeValue=TransactionCode.getEnum(msgHeaderModel.getTrCodeString());

		switch(trCodeValue){
		
			case KMS_ENCRYPT:
				handlerInf=new EncryptHandler();
				break;
				
			case KMS_DECRYPT:
				handlerInf=new DecryptHandler();
				break;
				
			case KMS_DIGEST:
				handlerInf=new DigestHandler();
				break;
				
			case KMS_SIGN:
				handlerInf=new SignHandler();
				break;
				
			case KMS_VERIFY: 
				handlerInf=new VerifyHandler();
				break;
				
			case KMS_WRAP:
				handlerInf=new WrapKeyHandler();
				break;
				
			case KMS_UNWRAP:
				handlerInf=new UnWrapKeyHandler();
				break;
				
			case KMS_DERIVE:
				handlerInf=new DeriveKeyHandler();
				break;
					
			case KMS_DESTROY:
				handlerInf=new DestroyKeyHandler();
				break;
				
			case KMS_GET_KEY_ATTR:
				handlerInf=new GetKeyAttrisHandler();
				break;
				
			case KMS_GEN_KEY:
				handlerInf=new GenerateKeyHandler();
				break;
				
			case KMS_GEN_KEY_PAIR:
				handlerInf=new GenerateKeyPairHandler();
				break;
				
			case KMS_IMPORT_SECRET_KEY:
				handlerInf=new ImportSecretKeyHandler();
				break;
				
			case KMS_IMPORT_PUB_KEY:
				handlerInf=new ImportPubKeyHandler();
				break;
				
			case KMS_IMPORT_PRI_KEY:
				handlerInf=new ImportPriKeyHandler();
				break;
				
			case KMS_GEN_RAN:
				handlerInf=new GenRanHandler();
				break;
				
			case KMS_GET_SYS_INFO:
				
				break;
				
			default:
				logger.debug("-----------No such trCode ----------- trCodeString = " + msgHeaderModel.getTrCodeString());
				break;
		}
		
		//HANDLER PROCESSING
//		if(handlerInf != null){
//			outputModel = handlerInf.processRequest(msgHeaderModel, msgBodyBuffer);
//		}
		
		//add 20150902		
		ByteBuffer bakMsgBodyBuffer = msgBodyBuffer;
		
		try{
			if(handlerInf != null){
				outputModel = handlerInf.processRequest(msgHeaderModel, msgBodyBuffer);
			}
			
		}catch (NullPointerException e1){
			logger.debug("Handler re-handle");
			
			CryptoManager.getInstance().init();
			
			bakMsgBodyBuffer.flip();
			
			if(handlerInf != null){
				outputModel = handlerInf.processRequest(msgHeaderModel, bakMsgBodyBuffer);
			}
			
			KeyManager.setAccessNo(0);
			
		}catch (KmsException e2){
			logger.debug("Handler re-handle");
			
			CryptoManager.getInstance().init();
			
			bakMsgBodyBuffer.flip();
			
			if(handlerInf != null){
				outputModel = handlerInf.processRequest(msgHeaderModel, bakMsgBodyBuffer);
			}
			
			KeyManager.setAccessNo(0);
			
		}catch (Exception e3){
			logger.debug("Handler re-handle");
			
			CryptoManager.getInstance().init();
			
			bakMsgBodyBuffer.flip();
			
			if(handlerInf != null){
				outputModel = handlerInf.processRequest(msgHeaderModel, bakMsgBodyBuffer);
			}
			
			KeyManager.setAccessNo(0);
			
		}
		
		return outputModel;
		
	}
	
}