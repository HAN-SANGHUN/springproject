package com.kona.kms.crypto.tcp.service.controller;

import java.io.IOException;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/*
 * Acceptor's main job: for each connection, return a Handler associated with the SocketChannel.
 */
class Acceptor implements Runnable {

	private static final Logger logger = LoggerFactory.getLogger(Acceptor.class);
	
	private Selector 			selector;
	private ServerSocketChannel svrSocketChannel;
	
	
	/**
	 * 
	 * @param selector
	 * @param svrSocketChannel
	 */
	public Acceptor(Selector selector, ServerSocketChannel svrSocketChannel){
		this.selector			= selector;
		this.svrSocketChannel	= svrSocketChannel;
	}
	
	/*
	 * (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	public void run() {
	
		//modify by shifei 20141125
//		SocketChannel socketChannel = null;
		try {
		
			svrSocketChannel.configureBlocking(false);
			SocketChannel socketChannel= svrSocketChannel.accept();
//			socketChannel.configureBlocking(false);
			
			logger.debug("[Acceptor] Connection Accepted by Reactor\n");
			
			if (socketChannel == null) {
				return;
			}
//			socketChannel.socket().setSoTimeout(10000);
			
//				Thread t = new Thread(new Handler(selector, socketChannel));
//				t.start();
			
//			logger.debug("[Acceptor] Before Handler ....\n");
			new Handler(selector, socketChannel);
		
			
//			while(!Thread.interrupted()){
//				svrSocketChannel.configureBlocking(false);
//				SocketChannel socketChannel= this.svrSocketChannel.accept();
//				socketChannel.configureBlocking(false);
//				new Thread(new Handler(selector, socketChannel)).start();	
//			}
		
			

//			logger.debug("Connection Accepted by Reactor\n");
//			logger.debug("socketChannel: " + socketChannel.toString()+ "\n");
			
		} catch (IOException ex) {
			logger.error(ex.toString());
			ex.printStackTrace();
		}
	}
}