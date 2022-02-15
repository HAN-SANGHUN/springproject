package com.kona.kms.crypto.tcp.service.controller.test;


import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.channels.spi.SelectorProvider;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class ServerReactor implements Runnable {

	private static final Logger logger = LoggerFactory.getLogger(ServerReactor.class);
	private SelectorProvider selectorProvider = SelectorProvider.provider();  
	private ServerSocketChannel serverSocketChannel;
	public ServerReactor(int port) throws IOException {  
        serverSocketChannel = selectorProvider.openServerSocketChannel(); //ServerSocketChannel.open();  
        ServerSocket serverSocket = serverSocketChannel.socket();  
//        serverSocket.bind(new InetSocketAddress("localhost", port), 1024);  
        serverSocket.bind(new InetSocketAddress(port));
        serverSocketChannel.configureBlocking(false);  
        logger.debug("Server : Server Start.");
    }  


    public void run() {  
    	try {
			new ServerDispatcher(serverSocketChannel, SelectorProvider.provider()).execute();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }  
}

