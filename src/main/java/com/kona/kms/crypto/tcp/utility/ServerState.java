/**
 * File : ServerState.java
 *
 * Copyright (C) 2012 - 2013, KONA I Co.Ltd.
 * All rights reserved.
 *
 * This program is a property of KONA I. you can not redistribute it and/or modify it
 * without any permission of KONA I.
 *
 * @author	: 
 */
package com.kona.kms.crypto.tcp.utility;

public enum ServerState {

	READING(0), 
	SENDING(1), 
	PROCESSING(2);

	// Initial state
	private int state;
	
	/**
	 * 
	 * @param state
	 */
	private ServerState(int state){
		this.state = state;
	}
	
}
