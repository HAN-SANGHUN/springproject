package com.kona.kms.web.cert.utils.parser;

import com.kona.kms.KmsException;
import com.kona.kms.web.cert.model.CertificateModel;
import com.kona.kms.web.cert.utils.ICertiParser;

public class DinersParser implements ICertiParser{

	private CertificateModel model;
	
	public DinersParser(CertificateModel model){
		this.model = model;
	}

	@Override
	public void parse(String hexString, String filename) throws KmsException {
		// TODO Auto-generated method stub
		
		this.validate();
	}

	
	public void validate() {
		
	}
}
