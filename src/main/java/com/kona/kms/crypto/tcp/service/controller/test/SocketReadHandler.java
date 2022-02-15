package com.kona.kms.crypto.tcp.service.controller.test;


import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.CancelledKeyException;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.kona.kms.KmsException;
import com.kona.kms.crypto.tcp.model.MSGHeaderModel;
import com.kona.kms.crypto.tcp.model.OutputModel;

import com.kona.kms.crypto.tcp.service.wrapper.DecryptHandler;
import com.kona.kms.crypto.tcp.service.wrapper.DeriveKeyHandler;
import com.kona.kms.crypto.tcp.service.wrapper.DestroyKeyHandler;
import com.kona.kms.crypto.tcp.service.wrapper.DigestHandler;
import com.kona.kms.crypto.tcp.service.wrapper.EncryptHandler;
import com.kona.kms.crypto.tcp.service.wrapper.GenRanHandler;
import com.kona.kms.crypto.tcp.service.wrapper.GenerateKeyHandler;
import com.kona.kms.crypto.tcp.service.wrapper.GenerateKeyPairHandler;
import com.kona.kms.crypto.tcp.service.wrapper.GetKeyAttrisHandler;
import com.kona.kms.crypto.tcp.service.wrapper.HandlerInterface;
import com.kona.kms.crypto.tcp.service.wrapper.ImportPriKeyHandler;
import com.kona.kms.crypto.tcp.service.wrapper.ImportPubKeyHandler;
import com.kona.kms.crypto.tcp.service.wrapper.ImportSecretKeyHandler;
import com.kona.kms.crypto.tcp.service.wrapper.SignHandler;
import com.kona.kms.crypto.tcp.service.wrapper.UnWrapKeyHandler;
import com.kona.kms.crypto.tcp.service.wrapper.VerifyHandler;
import com.kona.kms.crypto.tcp.service.wrapper.WrapKeyHandler;
import com.kona.kms.crypto.tcp.utility.ServerState;
import com.kona.kms.crypto.tcp.utility.TransactionCode;


public class SocketReadHandler extends SocketHandler{
	private static final Logger logger = LoggerFactory.getLogger(SocketReadHandler.class);
	private SelectionKey selectionKey;
	private  int BLOCK = 4096;    
	private  ByteBuffer receivebuffer = ByteBuffer.allocate(BLOCK);  
	
	public static OutputModel outputModel = null;
	
	private static ExecutorService pool = Executors.newFixedThreadPool(10);
	
	
	public SocketReadHandler(ServerDispatcher dispatcher, ServerSocketChannel sc, Selector selector) throws IOException{
		super(dispatcher, sc, selector);
	}
	
	@Override
	public void runnerExecute(int readyKeyOps) throws IOException {
		// TODO Auto-generated method stub
//		logger.debug("[Handler] Read start..............\n");
		
	if (SelectionKey.OP_READ == readyKeyOps)
	{
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
			readCount = socketChannel.read(msgHeadBuffer);
		} catch (Exception ex) {
//			ex.printStackTrace();
			readCount =-1;
		}finally{
			
			if(readCount <=0){
				this.socketChannel.socket().close();
				this.socketChannel.close();
				selectionKey.cancel();
			}
			
		}
		
//		if (readCount <= 0) {
//			this.socketChannel.socket().close();
//			this.socketChannel.close();
//			selectionKey.cancel();
//			return;
//		}
		
		msgHeadBuffer.flip();
	
		//parse message header
//		logger.debug("Before parsing header \n");
		try {
			msgHeaderModel.parseMsgHeader(msgHeadBuffer);
		} catch (KmsException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
//		if (readCount <= 0) {
//			this.socketChannel.socket().close();
//			this.socketChannel.close();
//			selectionKey.cancel();
//			
//			return;
//		}
		
//		logger.debug("[Handler] Read end..............\n");
		
		
//		if (readCount > 0) {
//			svrState	= ServerState.PROCESSING;
//			pool.execute(new Processer(msgHeaderModel, msgBodyBuffer));
//		}
		
		
//		this.svrState	= ServerState.PROCESSING;
//		pool.execute(new Processer(msgHeaderModel, msgBodyBuffer));
//		selectionKey.interestOps(SelectionKey.OP_WRITE);
//		return;
		
		if (readCount > 0) {
//			svrState	= ServerState.PROCESSING;
			Future<Integer> future=pool.submit(new Processer(msgHeaderModel, msgBodyBuffer));
			while(!future.isDone()){
				
				try {
					Thread.sleep(0);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
//			new Processer(msgHeaderModel, msgBodyBuffer);
		}
		
		
		 socketChannel.register(dispatcher.getWriteSelector(), SelectionKey.OP_WRITE);
		
}	
		
//		int count = 0;
//		if (SelectionKey.OP_READ == readyKeyOps)
//		{
//            receivebuffer.clear();
//            count = socketChannel.read(receivebuffer);   
//            if (count > 0) {  
//            	logger.debug("Server : Readable.");  
//            	receivebuffer.flip();  
//	            byte[] bytes = new byte[receivebuffer.remaining()];
//	            receivebuffer.get(bytes);
//	            String body = new String(bytes, "UTF-8"); 
//	            logger.debug("Server : Receive :" + body);  	            
//	            socketChannel.register(dispatcher.getWriteSelector(), SelectionKey.OP_WRITE);
//            }  
//		}

	}
	
	
class Processer implements Callable<Integer>{
		
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
		public Integer call() {
			try {
//				logger.debug("Before process \n");
				processAndHandOff(msgHeaderModel, msgBodyBuffer);
//				logger.debug("After process \n");
			} catch (KmsException e) {
				// TODO Auto-generated catch block
				logger.error(e.toString());
				e.printStackTrace();
				return -1;
				
			}
			return 0;
			
			
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
//		this.svrState = ServerState.SENDING;
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
		if(handlerInf != null){
			outputModel = handlerInf.processRequest(msgHeaderModel, msgBodyBuffer);
		}
		
		return outputModel;
	}
}

