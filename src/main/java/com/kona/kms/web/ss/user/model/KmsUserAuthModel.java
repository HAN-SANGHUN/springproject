package com.kona.kms.web.ss.user.model;

import java.io.Serializable;

public class KmsUserAuthModel implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3057215395455354487L;
	
	private String userAuthCode;
	
	private String userAuthName;
	
	public KmsUserAuthModel(){
		
	}

	public String getUserAuthCode() {
		return userAuthCode;
	}

	public void setUserAuthCode(String userAuthCode) {
		this.userAuthCode = userAuthCode;
	}

	public String getUserAuthName() {
		return userAuthName;
	}

	public void setUserAuthName(String userAuthName) {
		this.userAuthName = userAuthName;
	}	

}
