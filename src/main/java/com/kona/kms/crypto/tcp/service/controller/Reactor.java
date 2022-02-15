package com.kona.kms.crypto.tcp.service.controller;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/*
 In server, a Selector uses one thread to response all requires of all clients.

 1. When ACCEPT event is ready, Acceptor is choosed, it creates a Handler (e.g., Handler 1),
 and the interestOps of this handler is initialized as READ.
 2. When READ event is ready, the Handler 1 is selected and processed.

 Hence, whenever a new client connects, server creates a corresponding Handler in the same thread.

 Selection Key   Channel                 Handler     Interested Operation
 ------------------------------------------------------------------------
 SelectionKey 0  ServerSocketChannel     Acceptor    Accept
 SelectionKey 1  SocketChannel 1         Handler 1   Read and Write
 SelectionKey 2  SocketChannel 2         Handler 2   Read and Write
 SelectionKey 3  SocketChannel 3         Handler 3   Read and Write

 */
public class Reactor implements Runnable {
	private static final Logger logger = LoggerFactory.getLogger(Reactor.class);
	
	final Selector 				selector;
	final ServerSocketChannel 	serverSocketChannel;

	private static 	int 		DEFAULT_PORT		= 9900;
	private static 	Reactor 	reactor 			= null;
	private Acceptor 			connectionAcceptor 	= null;
	
	
	/**
	 * To get singleton instance
	 * @return
	 */
	public synchronized static Reactor getInstance(){
		
		try{
			
			if(reactor == null){
				reactor = new Reactor(Reactor.DEFAULT_PORT);
			}
			
		}catch(Exception e){
			logger.error(e.toString());
			e.printStackTrace();
			return null;
		}
		
		return reactor;
	}
	
	
	/**
	 * 
	 * @param port
	 * @throws IOException
	 */
	public Reactor(int port) throws IOException {
		
		
//		 	selKeyForAcceptor	= null;
		
		selector 			= Selector.open();
		serverSocketChannel= ServerSocketChannel.open();
		serverSocketChannel.socket().bind(new InetSocketAddress(port));
		serverSocketChannel.configureBlocking(false);	
//		connectionAcceptor=new Acceptor(selector,serverSocketChannel);
		SelectionKey selKeyForAcceptor= serverSocketChannel.register(selector,SelectionKey.OP_ACCEPT);
		connectionAcceptor=new Acceptor(selector,serverSocketChannel);
		if(selKeyForAcceptor != null){
			selKeyForAcceptor.attach(this.connectionAcceptor);
		}
	}
	
	/**
	 * 
	 * @return
	 * @throws IOException
	 */
	public Thread initReactor() throws IOException {
		
		Thread threadHandle = null;
		
		if(reactor == null){
			return null;
		}
		
		threadHandle=new Thread(reactor);
		threadHandle.start();
		return threadHandle;
	}
	

	/*
	 * (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	public void run() {
		
		int readySelectionKeyCount 	= 0;
//		Set<SelectionKey> selected	= null;
//		Iterator<SelectionKey> itor	= null;
		
		try {
		
			while (!Thread.interrupted()) {

//					logger.debug("[Reactor] Before Select invoke\n");
					readySelectionKeyCount = selector.select();
					if(readySelectionKeyCount == 0) {
	//					Thread.sleep(0, 1000);
						try {
//							Thread.sleep(0, 10);
							Thread.sleep(0);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
	//					Thread.sleep(0);
						continue;
					}
					
					Set<SelectionKey> selected= selector.selectedKeys();
					Iterator<SelectionKey> itor 	= selected.iterator();
					while (itor.hasNext()) {
//						logger.debug("[Reactor] Before Select dispatch invoke\n");
						this.dispatch((SelectionKey) (itor.next()));
//						itor.remove();//add by shifei 20141127
//						logger.debug("[Reactor] After Select dispatch invoke\n");
					}
					
					
//					logger.debug("[Reactor] Before Select clear invoke\n");
					selected.clear();
//					logger.debug("[Reactor] After Select clear invoke\n");

			}
		}catch (IOException ex) {
			logger.error(ex.toString());
			ex.printStackTrace();
		}
	}

	/**
	 * 
	 * @param key
	 */
	void dispatch(SelectionKey key) {
	
		//Trigger a thread
		Runnable runnableTask = (Runnable) (key.attachment());
		if(runnableTask != null) {
			runnableTask.run();
		}
	}


	

}