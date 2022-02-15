package com.kona.kms.web.cert.utils.parser;

import com.kona.kms.KmsException;
import com.kona.kms.web.cert.model.CertificateModel;
import com.kona.kms.web.cert.utils.ICertiParser;

public class PbocParser implements ICertiParser{

	private CertificateModel model;
	
	public PbocParser(CertificateModel model){
		this.model = model;
	}

	@Override
	public void parse(String hexString, String filename) throws KmsException {
		
		
		this.validate();		
	}

	
	private void validate() throws KmsException {
		
	}

}
