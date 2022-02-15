package com.kona.kms.framework;


public class ScmsRequest implements IORequest {
	
	private ScmsReqContext reqCtx;
	
	public ScmsRequest() {
		reqCtx = null;
	}
	
	public void setReqCtx(ScmsReqContext ctx) {
		reqCtx = ctx;
	}
	
	public ScmsReqContext getReqCtx() {
		return reqCtx;
	}
	
	public String getCommand() {
		return reqCtx.getCommand();
	}

}
