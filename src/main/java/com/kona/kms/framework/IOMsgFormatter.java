package com.kona.kms.framework;

import java.io.InputStream;



public interface IOMsgFormatter {

	public String extCommand();											// extract command attribute from Input Data
	public String extSession();												// extract logical session-id from Input Data
																					// if (session begin) return null; else return session-id-string
	public boolean isLastPayload();										// last : true
	public ScmsRequest parseRequestMsg(Object input);		// extract the request data structure from Input Data
	public byte[] fmtResponseMsg(ScmsResponse response);	// generate Output Data from BizObject return-data-structure
	public byte[] fmtErrResponseMsg(ScmsException e);			// generate Scms Error Data
	public byte[] fmtUnknownErrResponseMsg(Exception e);	// generate Unknown Error Data
	
	public void convProtocolHeaderAftIn();							// After receiving Input data, convert the protocol header
	public void convProtocolHeaderBfrOut();							// Before sending Output data, convert the protocol header

}
