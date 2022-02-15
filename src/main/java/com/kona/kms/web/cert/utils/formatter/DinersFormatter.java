package com.kona.kms.web.cert.utils.formatter;

import com.kona.kms.web.cert.model.CertificateModel;
import com.kona.kms.web.cert.utils.ICertiReqFormatter;

public class DinersFormatter implements ICertiReqFormatter{

	private static final String serviceIdentifier = "10100000";
	
	private CertificateModel model;
	
	private String extentionData;
	
	private String selfSignData;
	
	private String cryptoData;
	
	private String hexDigestStr;
	
	public DinersFormatter(CertificateModel model){
		this.model = model;
	}
	
	@Override
	public void format(String modulus, String exponent, String ipkSize, String expSize, String leftmost) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getSelfSignData() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getCrytoData(String hexDigestStr) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getCertiInfo(String hexCryptoResult) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getCertiHashValue() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getCertiInfoFileName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getCertiHashValueFileName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getHashValueFileType() {
		return "HEX_STRING";
	}

}
