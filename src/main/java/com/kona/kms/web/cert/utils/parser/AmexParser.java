package com.kona.kms.web.cert.utils.parser;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.kona.kms.KMSCode;
import com.kona.kms.KmsException;
import com.kona.kms.utils.Util;
import com.kona.kms.web.cert.model.CertificateModel;
import com.kona.kms.web.cert.utils.ICertiParser;

/**
 *  A live issuer certification information file returned from the CA 
 *  - LIC00005.dat or PIC00005.dat
 *  
 *  
 * @author 정호
 *
 */
public class AmexParser implements ICertiParser{

	private CertificateModel model;
	
	private String hexString;
	
	/** Unsigned Issuer Public Key Output Extension */
	private String header;
	
	private String serviceIdentifier;
	
	/* issuer bin number */
	private String issuerIdentificationNumber;
	
	private String certiSerialNummber;
	
	/* MMYY */
	private String certiExpireDate;
	
	private String issuerPKRemainderLength;
	
	private String issuerPKModulusRemainder;
	
	private String issuerPKExponentLength;
	
	private String issuerPKExponent;
	
	private String caPKIndex;
	
	/** Issuer Public Key Certificate */
	private String certificate;
	
	/** Public Key Detached Signature */
	private String signature;

	private String caPKLength;
	
	private ConcurrentHashMap<String, String> caPKLenMap;
	
	public AmexParser(CertificateModel model){
		this.model = model;
		
		caPKLenMap = new ConcurrentHashMap<String, String>();
		
		caPKLenMap.put("A14", "90");
		caPKLenMap.put("A65", "90");
	}

	/**
	 * Input: Unsigned Issuer Public Key Output Extension
	 */
	@Override
	public void parse(String hexString, String filename) throws KmsException {
		
		if(filename != null) validateFilename(filename);
		
		this.hexString = hexString;
		
		int index = 0;
		
		this.header = hexString.substring(index, index+2);
		index += 2;
		
		this.serviceIdentifier = hexString.substring(index, index + 8);
		index += 8;
		
		this.issuerIdentificationNumber = hexString.substring(index, index+8);
		index += 8;
		
		this.certiSerialNummber = hexString.substring(index, index+6);
		index += 6;
		
		this.certiExpireDate = hexString.substring(index, index+4);
		index += 4;
		
		this.issuerPKRemainderLength = hexString.substring(index, index+2);
		index += 2;		
		
		this.issuerPKModulusRemainder = hexString.substring(index, index+Util.Hex2Dec(this.issuerPKRemainderLength));
		index += Util.Hex2Dec(this.issuerPKRemainderLength);		
		
		this.issuerPKExponentLength = hexString.substring(index, index+2);
		index += 2;
				
		this.issuerPKExponent = hexString.substring(index, index+Util.Hex2Dec(this.issuerPKExponentLength));
		index += Util.Hex2Dec(this.issuerPKExponentLength);		
		
		this.caPKIndex = hexString.substring(index, index+2);
		index += 2;				
		
		this.caPKLength = Integer.toString(Util.Hex2bin(
				Integer.toString(Util.Hex2Dec(this.caPKLenMap.get("A" + this.caPKIndex)))));		
		
		this.validate();
	}
	
	/*
	 * Amex File Name Convention: LIC00005.dat
	 */
	private void validateFilename(String filename) throws KmsException{
		
		int index = filename.indexOf(".");
		String prefix = filename.substring(0,index);
		String ext = filename.substring(index+1);
		
		if(prefix.length() != 8 || ext.length() != 3){
			throw new KmsException(KMSCode.CERTI_FILENAME_INVALID, filename);
		}
		
		if(ext.toUpperCase().equals("DAT")){
			throw new KmsException(KMSCode.CERTI_FILENAME_INVALID, filename);
		}			
	}

	private void validate() throws KmsException {
		model.setCaSerialNumber(this.certiSerialNummber);
		model.setCaPublicKeyIndex(this.caPKIndex);
		model.setCaPublicKeyLength(this.caPKLength);
		
		boolean isValid = true;
		
		StringBuffer sb = new StringBuffer();
		
		if(!this.header.equals("24")){
			sb.append("[HEADER:").append(this.header).append("]");
			isValid = false;
		}
		
		if(!this.serviceIdentifier.equals("00000000")){
			sb.append("[Service Identifier:").append(this.serviceIdentifier).append("]");
			isValid = false;
		}
		
		if(!model.getBinNumber().equals(this.issuerIdentificationNumber.substring(0, 6))){
			sb.append("[BIN:").append(this.issuerIdentificationNumber).append("]");
			isValid = false;
		}
		
		String MMYY = model.getExpireDateStr().substring(5,7)+model.getExpireDateStr().substring(2,4);
		if(!MMYY.equals(this.certiExpireDate)){
			sb.append("[Expire Date:").append(this.certiExpireDate).append("]");
			isValid = false;
		}
		
		if(!isValid){
			throw new KmsException(KMSCode.CERTI_INVALID, sb.toString());
		}
	}

	@Override
	public String toString() {
		return "AmexParser [hexString=" + hexString + ", header=" + header
				+ ", serviceIdentifier=" + serviceIdentifier
				+ ", issuerIdentificationNumber=" + issuerIdentificationNumber
				+ ", certiSerialNummber=" + certiSerialNummber
				+ ", certiExpireDate=" + certiExpireDate
				+ ", issuerPKRemainderLength=" + issuerPKRemainderLength
				+ ", issuerPKModulusRemainder=" + issuerPKModulusRemainder
				+ ", issuerPKExponentLength=" + issuerPKExponentLength
				+ ", issuerPKExponent=" + issuerPKExponent + ", caPKIndex="
				+ caPKIndex + ", certificate=" + certificate + ", signature="
				+ signature + ", caPKLength=" + caPKLength + ", caPKLenMap="
				+ caPKLenMap + "]";
	}	
}
