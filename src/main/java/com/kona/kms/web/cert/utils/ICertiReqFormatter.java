package com.kona.kms.web.cert.utils;


public interface ICertiReqFormatter {

	void format(String modulus, String exponent, String ipkSize, String expSize, String leftmost);
	
	String getSelfSignData();
	
	String getCrytoData(String hexDigestStr);
	
	String getCertiInfo(String hexCryptoResult);
	
	String getCertiHashValue();
	
	String getCertiInfoFileName();
	
	String getCertiHashValueFileName();
	
	String getHashValueFileType();
	
}
