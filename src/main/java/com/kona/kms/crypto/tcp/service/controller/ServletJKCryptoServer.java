package com.kona.kms.crypto.tcp.service.controller;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.kona.kms.crypto.tcp.service.controller.*;
import com.kona.kms.crypto.tcp.service.controller.test.ServerReactor;

/*
 * Servlet implementation class JkcryptoServer
 */
public class ServletJKCryptoServer extends HttpServlet {

	/*
	 * Reactor
	 */
	private Reactor reactor;
	private ServerReactor serverReactor;
	
	/*
	 * default constructor
	 */
	public ServletJKCryptoServer(){
		this.reactor = null;
	}
	
	/*
	 * (non-Javadoc)
	 * @see javax.servlet.GenericServlet#init(javax.servlet.ServletConfig)
	 */
	public void init(ServletConfig config){
		
		try{
//			System.out.println("ServletJKCryptoServer initialize()");
			this.reactor=Reactor.getInstance();
	    	if(this.reactor != null){
	    		this.reactor.initReactor();
	    	}
			
		/*	int port = 9900;
			new Thread(new ServerReactor(port)).start();*/
	    	
		}catch(NullPointerException  ne){
			ne.printStackTrace();
		}catch(Exception e){
			e.printStackTrace();
		}	
	}

	
	/*
	 * (non-Javadoc)
	 * @see javax.servlet.http.HttpServlet#doGet(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}
	
	

	/*
	 * (non-Javadoc)
	 * @see javax.servlet.http.HttpServlet#doPost(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		try{			
		
			response.setContentType("text/html; charset=euc-kr");
			PrintWriter out = new PrintWriter(new OutputStreamWriter(response.getOutputStream(), "UTF-8"));
		
			out.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	/*
	 * (non-Javadoc)
	 * @see javax.servlet.GenericServlet#destroy()
	 */
	public void destroy() {
		
		
	}

	
}
