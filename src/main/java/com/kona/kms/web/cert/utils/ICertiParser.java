package com.kona.kms.web.cert.utils;

import com.kona.kms.KmsException;

public interface ICertiParser {
	
	void parse(String hexString, String filename) throws KmsException;
}
