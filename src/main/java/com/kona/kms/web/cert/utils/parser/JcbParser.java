package com.kona.kms.web.cert.utils.parser;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.kona.kms.KMSCode;
import com.kona.kms.KmsException;
import com.kona.kms.utils.Util;
import com.kona.kms.web.cert.model.CertificateModel;
import com.kona.kms.web.cert.utils.ICertiParser;

public class JcbParser implements ICertiParser {

	private CertificateModel model;
	
	private String hexString;
	
	private String serviceIdentifier = "10100000";
	
	/* issuer bin number */
	private String issuerIdentificationNumber;
	
	private String certiSerialNummber = "00E381";
	
	private String issuerPKIndex;
	
	private String caPKIndex;
	
	private String issuerPKRemainderLength;
	
	private String issuerPKModulusRemainder;
	
	private String issuerPKExponentLength;
	
	private String issuerPKExponent;
	
	private String caPKLength;
	
	private ConcurrentHashMap<String, String> caPKLenMap;
	
	public JcbParser(CertificateModel model){
		this.model = model;
		
		caPKLenMap = new ConcurrentHashMap<String, String>();
		
		caPKLenMap.put("J09", "80");
		caPKLenMap.put("J10", "90");
		caPKLenMap.put("J12", "B0");
		caPKLenMap.put("J14", "F8");
		caPKLenMap.put("JFF", "90");
		caPKLenMap.put("J16", "90");
	}

	@Override
	public void parse(String hexString, String filename) throws KmsException {
		
		if(filename != null) validateFilename(filename);
		
		this.hexString = hexString;
		
		int index = 0;
		
		this.issuerIdentificationNumber = hexString.substring(index, index+8);
		index += 8;
		
		this.issuerPKIndex = hexString.substring(index, index+6);
		index += 6;
		
		this.caPKIndex = hexString.substring(index, index+2);
		index += 2;
		
		// multiply ipksize by 2 because we need hex size
		this.issuerPKRemainderLength = Integer.toString(model.getIpkSize()*2 - Util.Hex2Dec(this.caPKLenMap.get("J" + this.caPKIndex)) + 72);
		
		this.issuerPKModulusRemainder = hexString.substring(index, index+Integer.valueOf(this.issuerPKRemainderLength));
		index += Integer.valueOf(this.issuerPKRemainderLength);
		
		this.issuerPKExponent = hexString.substring(index, index+2);
		index += 2;
		
		this.caPKLength=Integer.toString(Util.Hex2bin(
				Integer.toString(Util.Hex2Dec(this.caPKLenMap.get("J" + this.caPKIndex)))));
		
		this.validate();
		
	}
	
	/*
	 * JCB File Name Convention: LXXXXXXX99
	 */
	private void validateFilename(String filename) throws KmsException{
						
		if(filename.length() != 10){
			throw new KmsException(KMSCode.CERTI_FILENAME_INVALID, filename);
		}
		
		if(!String.valueOf(filename.charAt(0)).toUpperCase().equals("L")){
			throw new KmsException(KMSCode.CERTI_FILENAME_INVALID, filename);
		}		
		
		if(!filename.substring(8).equals("99")){
			throw new KmsException(KMSCode.CERTI_FILENAME_INVALID, filename);
		}
	}

	public void validate() throws KmsException{
		model.setCaSerialNumber(this.certiSerialNummber);
		model.setCaPublicKeyIndex(this.caPKIndex);
		model.setCaPublicKeyLength(this.caPKLength);
		
		boolean isValid = true;
		
		StringBuffer sb = new StringBuffer();
		
		if(!model.getBinNumber().equals(this.issuerIdentificationNumber.substring(0, 6))){
			sb.append("[BIN:").append(this.issuerIdentificationNumber).append("]");
			isValid = false;
		}
		
		if(!String.valueOf(model.getIpkIndex()).equals(this.issuerPKIndex)){
			sb.append("[Ipk Index:").append(this.issuerPKIndex).append("]");
			isValid = false;
		}
		
		if(!isValid){
			throw new KmsException(KMSCode.CERTI_INVALID, sb.toString());
		}
	}

	@Override
	public String toString() {
		return "JcbParser [hexString=" + hexString + ", serviceIdentifier="
				+ serviceIdentifier + ", issuerIdentificationNumber="
				+ issuerIdentificationNumber + ", certiSerialNummber="
				+ certiSerialNummber + ", issuerPKIndex=" + issuerPKIndex
				+ ", caPKIndex=" + caPKIndex + ", issuerPKRemainderLength="
				+ issuerPKRemainderLength + ", issuerPKModulusRemainder="
				+ issuerPKModulusRemainder + ", issuerPKExponentLength="
				+ issuerPKExponentLength + ", issuerPKExponent="
				+ issuerPKExponent + ", caPKLength=" + caPKLength
				+ ", caPKLenMap=" + caPKLenMap + "]";
	}	
}
