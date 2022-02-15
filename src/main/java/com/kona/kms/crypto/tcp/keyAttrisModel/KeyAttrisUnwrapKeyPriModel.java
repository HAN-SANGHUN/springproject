package com.kona.kms.crypto.tcp.keyAttrisModel;

import com.kona.kms.crypto.KeyAttributes;

public class KeyAttrisUnwrapKeyPriModel {
	
	private KeyAttributes priKeyAttributes;
	
	public KeyAttrisUnwrapKeyPriModel(byte[] pairKeyAttrisArray){
		
		priKeyAttributes = new KeyAttributes("PRIVATE");
		
		byte[] keyClassArray;
		String keyClassStr;
		
		byte[] keyTypeArray;
		String keyTypeStr;
		
		byte[] tokenArray;
		String tokenStr;
		
		byte[] privateArray;
		String privateStr;
		
		byte[] sensitiveArray;
		String sensitiveStr;
		
		byte[] extractableArray;
		String extractableStr;
		
		byte[] encryptArray;
		String encryptStr;
		
		byte[] decryptArray;
		String decryptStr;
		
		byte[] wrapArray;
		String wrapStr;
		
		byte[] unwrapArray;
		String unwrapStr;
		
		byte[] signArray;
		String signStr;
		
		byte[] verifyArray;
		String verifyStr;
		
		byte[] signRecoverArray;
		String signRecoverStr;
		
		byte[] verifyRecoverArray;
		String verifyRecoverStr;
		
		byte[] deriveArray;
		String deriveStr;
		
		byte[] keyLabelValArray;
		String keyLabelValStrTemp;
		String keyLabelValStr;		
		
		byte[] subjectLenArray;
		String subjectLenStrTemp;
		Integer subjectLenInt;
		
		byte[] subjectValArray;
		
		byte[] startDateArray;
		
		byte[] endDateArray;

		
		
		//-------------- private key attributes
		
		keyClassArray = new byte[32];
		System.arraycopy(pairKeyAttrisArray, 0, keyClassArray, 0, 32);
		keyClassStr = new String(keyClassArray);
		keyClassStr = keyClassStr.replaceAll("\\s+", "");
		priKeyAttributes.cka_keyClass = Long.valueOf(keyClassStr);
		
		keyTypeArray = new byte[32];
		System.arraycopy(pairKeyAttrisArray, 32, keyTypeArray, 0, 32);
		keyTypeStr = new String(keyTypeArray);
		keyTypeStr = keyTypeStr.replaceAll("\\s+", "");
		priKeyAttributes.cka_keyType = Long.valueOf(keyTypeStr);
		
		tokenArray = new byte[1];
		System.arraycopy(pairKeyAttrisArray, 32+32, tokenArray, 0, 1);
		tokenStr = new String(tokenArray);
		priKeyAttributes.cka_token = (tokenStr.equals("1")) ? true : false;
		
		privateArray = new byte[1];
		System.arraycopy(pairKeyAttrisArray, 32+32+1, privateArray, 0, 1);
		privateStr = new String(privateArray);
		priKeyAttributes.cka_private = (privateStr.equals("1")) ? true : false;
		
		sensitiveArray = new byte[1];
		System.arraycopy(pairKeyAttrisArray, 32+32+1+1, sensitiveArray, 0, 1);
		sensitiveStr = new String(sensitiveArray);
		priKeyAttributes.cka_sensitive = (sensitiveStr.equals("1")) ? true : false;
		
		extractableArray = new byte[1];
		System.arraycopy(pairKeyAttrisArray, 32+32+1+1+1, extractableArray, 0, 1);
		extractableStr = new String(extractableArray);
		priKeyAttributes.cka_extractable = (extractableStr.equals("1")) ? true : false;
		
		encryptArray = new byte[1];
		System.arraycopy(pairKeyAttrisArray, 32+32+1+1+1+1, encryptArray, 0, 1);
		encryptStr = new String(encryptArray);
		priKeyAttributes.cka_encrypt = (encryptStr.equals("1")) ? true : false;
		
		decryptArray = new byte[1];
		System.arraycopy(pairKeyAttrisArray, 32+32+1+1+1+1+1, decryptArray, 0, 1);
		decryptStr = new String(decryptArray);
		priKeyAttributes.cka_decrypt = (decryptStr.equals("1")) ? true : false;
		
		wrapArray = new byte[1];
		System.arraycopy(pairKeyAttrisArray, 32+32+1+1+1+1+1+1, wrapArray, 0, 1);
		wrapStr = new String(wrapArray);
		priKeyAttributes.cka_wrap = (wrapStr.equals("1")) ? true : false;
		
		unwrapArray = new byte[1];
		System.arraycopy(pairKeyAttrisArray, 32+32+1+1+1+1+1+1+1, unwrapArray, 0, 1);
		unwrapStr = new String(unwrapArray);
		priKeyAttributes.cka_unwrap = (unwrapStr.equals("1")) ? true : false;
		
		signArray = new byte[1];
		System.arraycopy(pairKeyAttrisArray, 32+32+1+1+1+1+1+1+1+1, signArray, 0, 1);
		signStr = new String(signArray);
		priKeyAttributes.cka_sign = (signStr.equals("1")) ? true : false;
		
		verifyArray = new byte[1];
		System.arraycopy(pairKeyAttrisArray, 32+32+1+1+1+1+1+1+1+1+1, verifyArray, 0, 1);
		verifyStr = new String(verifyArray);
		priKeyAttributes.cka_verify = (verifyStr.equals("1")) ? true : false;
		
		signRecoverArray = new byte[1];
		System.arraycopy(pairKeyAttrisArray, 32+32+1+1+1+1+1+1+1+1+1+1, signRecoverArray, 0, 1);
		signRecoverStr = new String(signRecoverArray);
		priKeyAttributes.cka_signRecover = (signRecoverStr.equals("1")) ? true : false;
		
		verifyRecoverArray = new byte[1];
		System.arraycopy(pairKeyAttrisArray, 32+32+1+1+1+1+1+1+1+1+1+1+1, verifyRecoverArray, 0, 1);
		verifyRecoverStr = new String(verifyRecoverArray);
		priKeyAttributes.cka_verifyRecover = (verifyRecoverStr.equals("1")) ? true : false;
		
		deriveArray = new byte[1];
		System.arraycopy(pairKeyAttrisArray, 32+32+1+1+1+1+1+1+1+1+1+1+1+1, deriveArray, 0, 1);
		deriveStr = new String(deriveArray);
		priKeyAttributes.cka_derive = (deriveStr.equals("1")) ? true : false;
		
		keyLabelValArray = new byte[256];
		System.arraycopy(pairKeyAttrisArray, 32+32+1+1+1+1+1+1+1+1+1+1+1+1+1, keyLabelValArray, 0, 256);
		keyLabelValStrTemp = new String(keyLabelValArray);
		keyLabelValStr = keyLabelValStrTemp.replaceAll("\\s+","");
		priKeyAttributes.cka_label = keyLabelValStr.getBytes();		
		
		subjectLenArray = new byte[4];
		System.arraycopy(pairKeyAttrisArray, 32+32+1+1+1+1+1+1+1+1+1+1+1+1+1+256, subjectLenArray, 0, 4);
		subjectLenStrTemp = new String(subjectLenArray);
		subjectLenInt = Integer.parseInt(subjectLenStrTemp);
		
		subjectValArray = new byte[subjectLenInt];
		System.arraycopy(pairKeyAttrisArray, 32+32+1+1+1+1+1+1+1+1+1+1+1+1+1+256+4, subjectValArray, 0, subjectLenInt);
		priKeyAttributes.cka_subject = subjectValArray;
		
		startDateArray = new byte[8];
		System.arraycopy(pairKeyAttrisArray, 32+32+1+1+1+1+1+1+1+1+1+1+1+1+1+256+4+subjectLenInt, startDateArray, 0, 8);
		priKeyAttributes.cka_startDate = startDateArray;
		
		endDateArray = new byte[8];
		System.arraycopy(pairKeyAttrisArray, 32+32+1+1+1+1+1+1+1+1+1+1+1+1+1+256+4+subjectLenInt+8, endDateArray, 0, 8);
		priKeyAttributes.cka_endDate = endDateArray;
		
	}


	public KeyAttributes getPriKeyAttributes() {
		return priKeyAttributes;
	}

	public void setPriKeyAttributes(KeyAttributes priKeyAttributes) {
		this.priKeyAttributes = priKeyAttributes;
	}

	
	
	
}
