package com.kona.kms.crypto.tcp.service.controller.test;


import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.kona.kms.crypto.tcp.utility.ServerState;

import com.kona.kms.crypto.tcp.service.controller.test.SocketReadHandler;

public class SocketWriteHandler extends SocketHandler{
	private static final Logger logger = LoggerFactory.getLogger(SocketWriteHandler.class);
	private  int BLOCK = 4096;  
	private  ByteBuffer sendbuffer = ByteBuffer.allocate(BLOCK);  
	private static int Index = 1;
	public SocketWriteHandler(ServerDispatcher dispatcher, ServerSocketChannel sc, Selector selector) throws IOException{
		super(dispatcher, sc, selector);
	}
	
	@Override
	public void runnerExecute(int readyKeyOps) throws IOException {
		// TODO Auto-generated method stub
		if (readyKeyOps == SelectionKey.OP_WRITE)
		{
//			logger.debug("Send method start..............\n");
			ByteBuffer 	output 				= null;
			byte[] 		outputToBufferArray = null; 
			
			outputToBufferArray = new byte[SocketReadHandler.outputModel.getOutPutSize()];
			outputToBufferArray = SocketReadHandler.outputModel.getServerMsg();
			
			String serverMsgString = new String(outputToBufferArray);
//			logger.debug("serverMsgString: " + serverMsgString);
			
			String totalLenString = new String(SocketReadHandler.outputModel.getTotalLen());
			Integer totalLenInt = Integer.parseInt(totalLenString);
//			logger.debug("totalLenString: " + totalLenString);
			
			
			output = ByteBuffer.wrap(outputToBufferArray);
			
//			String debugServerMsg = new String(outputToBufferArray);
//			int debugServerMsgLen = debugServerMsg.length();
			
//			logger.debug("debugServerMsg: " + debugServerMsg);
//			logger.debug("debugServerMsgLen: " + debugServerMsgLen);
			
			
			socketChannel.write(output); //send msg to client
			
//			logger.debug("Send to Client.............\n");
			
//			selectionKey.interestOps(SelectionKey.OP_READ);		
//			svrState = ServerState.READING;
			
			socketChannel.register(dispatcher.getReadSelector(), SelectionKey.OP_READ);
			
			
			
			
//            logger.debug("Server : Writable.");  
//        	String data = String.format("%d", Index);
//        	byte[] req = data.getBytes();
//            sendbuffer.clear();  
//
//
//            logger.debug(String.format("Server : Write %s", data));
//                                
//            sendbuffer = ByteBuffer.allocate(req.length);
//			sendbuffer.put(req);
//			sendbuffer.flip();        			
//            socketChannel.write(sendbuffer);    
//            Index++;
//            socketChannel.register(dispatcher.getReadSelector(), SelectionKey.OP_READ);
		}
	}
}

