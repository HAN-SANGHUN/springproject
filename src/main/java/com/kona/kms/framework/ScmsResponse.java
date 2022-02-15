package com.kona.kms.framework;



public class ScmsResponse implements IOResponse {
	
	private ScmsReqContext reqCtx;
	
	public ScmsResponse() {
		reqCtx = null;
	}
	
	public void setReqCtx(ScmsReqContext ctx) {
		reqCtx = ctx;
	}
	public ScmsReqContext getReqCtx() {
		return reqCtx;
	}
	
}
