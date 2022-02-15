package com.kona.kms.web.utils;

public enum AjaxStatus {

	SUCCESS("SUCCESS"),
	
	FAILURE("FAIL");
	
	private String statusCode;
	
	private AjaxStatus(String status){
		this.statusCode = status;
	}
	
	public String getStatusCode(){
		return this.statusCode;
	}	
}
