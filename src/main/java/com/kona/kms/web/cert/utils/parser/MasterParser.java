package com.kona.kms.web.cert.utils.parser;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.kona.kms.KMSCode;
import com.kona.kms.KmsException;
import com.kona.kms.utils.Util;
import com.kona.kms.web.cert.model.CertificateModel;
import com.kona.kms.web.cert.utils.ICertiParser;

public class MasterParser implements ICertiParser{

	private CertificateModel model;
	
	private String hexString;
	
	private String serviceIdentifier = "10100000";
	
	/* issuer bin number */
	private String issuerIdentificationNumber;
	
	private String issuerPKIndex;
	
	private String certiSerialNummber = "00E381";
	
	/* MMYY */
	private String certiExpireDate = "1216";
	
	private String issuerPKRemainderLength;
	
	private String issuerPKModulusRemainder;
	
	private String issuerPKExponentLength;
	
	private String issuerPKExponent;
	
	private String caPKIndex;
	
	private String caPKLength;
	
	private ConcurrentHashMap<String, String> caPKLenMap;
	
	public MasterParser(CertificateModel model){
		this.model = model;
		
		caPKLenMap = new ConcurrentHashMap<String, String>();
		
		caPKLenMap.put("M01", "90");
		caPKLenMap.put("M03", "80");
		caPKLenMap.put("M04", "90");
		caPKLenMap.put("M05", "B0");
		caPKLenMap.put("M06", "F8");
		caPKLenMap.put("MFA", "90");
		caPKLenMap.put("MFE", "80");
		caPKLenMap.put("MFF", "70");
		caPKLenMap.put("M14", "90");
	}

	/**
	 * Input: Issuer Public Key Certificate
	 */
	@Override
	public void parse(String hexString, String filename) throws KmsException{
		
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
		this.issuerPKRemainderLength = Integer.toString(model.getIpkSize()*2 - Util.Hex2Dec(this.caPKLenMap.get("M" + this.caPKIndex)) + 72);
		
		this.issuerPKModulusRemainder = hexString.substring(index, index+Integer.valueOf(this.issuerPKRemainderLength));
		index += Integer.valueOf(this.issuerPKRemainderLength);
		
		this.issuerPKExponent = hexString.substring(index, index+2);
		index += 2;
		
		this.caPKLength=Integer.toString(Util.Hex2bin(
				Integer.toString(Util.Hex2Dec(this.caPKLenMap.get("M" + this.caPKIndex)))));		
		
		this.validate();
	}
	
	/*
	 * Master File Name Convention: .cXX
	 */
	private void validateFilename(String filename) throws KmsException{
		
		int index = filename.indexOf(".");
		String prefix = filename.substring(0,index);
		String ext = filename.substring(index+1);
		
		if(ext.length() != 3){
			throw new KmsException(KMSCode.CERTI_FILENAME_INVALID, filename);
		}
		
		if(!String.valueOf(ext.charAt(0)).toUpperCase().equals("C")){
			throw new KmsException(KMSCode.CERTI_FILENAME_INVALID, filename);
		}			
	}
	
	private void validate() throws KmsException {
		
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
		return "MasterParser [model=" + model + ", hexString=" + hexString
				+ ", serviceIdentifier=" + serviceIdentifier
				+ ", issuerIdentificationNumber=" + issuerIdentificationNumber
				+ ", issuerPKIndex=" + issuerPKIndex + ", certiSerialNummber="
				+ certiSerialNummber + ", certiExpireDate=" + certiExpireDate
				+ ", issuerPKRemainderLength=" + issuerPKRemainderLength
				+ ", issuerPKModulusRemainder=" + issuerPKModulusRemainder
				+ ", issuerPKExponentLength=" + issuerPKExponentLength
				+ ", issuerPKExponent=" + issuerPKExponent + ", caPKIndex="
				+ caPKIndex + ", caPKLength=" + caPKLength + ", caPKLenMap="
				+ caPKLenMap + "]";
	}	
	
}
