package com.kona.kms.crypto.tcp.keyAttrisModel;

import com.kona.kms.crypto.KeyAttributes;
import com.kona.kms.crypto.tcp.utility.MSGModelConstant;

public class KeyAttrisDeriveKeyPubModel {
	
	private KeyAttributes pubKeyAttributes;
	
	public KeyAttrisDeriveKeyPubModel(byte[] pairKeyAttrisArray){
		
		pubKeyAttributes = new KeyAttributes("PUBLIC");
		
		byte[] keyClassArray = new byte[32];
		System.arraycopy(pairKeyAttrisArray, 0, keyClassArray, 0, 32);
		String keyClassStr = new String(keyClassArray);
		keyClassStr = keyClassStr.replaceAll("\\s+", "");
		pubKeyAttributes.cka_keyClass = Long.valueOf(keyClassStr);
		
		byte[] keyTypeArray = new byte[32];
		System.arraycopy(pairKeyAttrisArray, 32, keyTypeArray, 0, 32);
		String keyTypeStr = new String(keyTypeArray);
		keyTypeStr = keyTypeStr.replaceAll("\\s+", "");
		pubKeyAttributes.cka_keyType = Long.valueOf(keyTypeStr);
		
		byte[] tokenArray = new byte[MSGModelConstant.KEY_ATTRS_BOOLEAN_LEN];
		System.arraycopy(pairKeyAttrisArray, 32+32, tokenArray, 0, MSGModelConstant.KEY_ATTRS_BOOLEAN_LEN);
		String tokenStr = new String(tokenArray);
		pubKeyAttributes.cka_token = (tokenStr.equals("1")) ? true : false;
		
		byte[] privateArray = new byte[MSGModelConstant.KEY_ATTRS_BOOLEAN_LEN];
		System.arraycopy(pairKeyAttrisArray, 32+32+MSGModelConstant.KEY_ATTRS_BOOLEAN_LEN, privateArray, 0, MSGModelConstant.KEY_ATTRS_BOOLEAN_LEN);
		String privateStr = new String(privateArray);
		pubKeyAttributes.cka_private = (privateStr.equals("1")) ? true : false;
		
		byte[] sensitiveArray = new byte[MSGModelConstant.KEY_ATTRS_BOOLEAN_LEN];
		System.arraycopy(pairKeyAttrisArray, 32+32+MSGModelConstant.KEY_ATTRS_BOOLEAN_LEN*2, sensitiveArray, 0, MSGModelConstant.KEY_ATTRS_BOOLEAN_LEN);
		String sensitiveStr = new String(sensitiveArray);
		pubKeyAttributes.cka_sensitive = (sensitiveStr.equals("1")) ? true : false;
		
		byte[] extractableArray = new byte[MSGModelConstant.KEY_ATTRS_BOOLEAN_LEN];
		System.arraycopy(pairKeyAttrisArray, 32+32+MSGModelConstant.KEY_ATTRS_BOOLEAN_LEN*3, extractableArray, 0, MSGModelConstant.KEY_ATTRS_BOOLEAN_LEN);
		String extractableStr = new String(extractableArray);
		pubKeyAttributes.cka_extractable = (extractableStr.equals("1")) ? true : false;
		
		byte[] encryptArray = new byte[MSGModelConstant.KEY_ATTRS_BOOLEAN_LEN];
		System.arraycopy(pairKeyAttrisArray, 32+32+MSGModelConstant.KEY_ATTRS_BOOLEAN_LEN*4, encryptArray, 0, MSGModelConstant.KEY_ATTRS_BOOLEAN_LEN);
		String encryptStr = new String(encryptArray);
		pubKeyAttributes.cka_encrypt = (encryptStr.equals("1")) ? true : false;
		
		byte[] decryptArray = new byte[MSGModelConstant.KEY_ATTRS_BOOLEAN_LEN];
		System.arraycopy(pairKeyAttrisArray, 32+32+MSGModelConstant.KEY_ATTRS_BOOLEAN_LEN*5, decryptArray, 0, MSGModelConstant.KEY_ATTRS_BOOLEAN_LEN);
		String decryptStr = new String(decryptArray);
		pubKeyAttributes.cka_decrypt = (decryptStr.equals("1")) ? true : false;
		
		byte[] wrapArray = new byte[MSGModelConstant.KEY_ATTRS_BOOLEAN_LEN];
		System.arraycopy(pairKeyAttrisArray, 32+32+MSGModelConstant.KEY_ATTRS_BOOLEAN_LEN*6, wrapArray, 0, MSGModelConstant.KEY_ATTRS_BOOLEAN_LEN);
		String wrapStr = new String(wrapArray);
		pubKeyAttributes.cka_wrap = (wrapStr.equals("1")) ? true : false;
		
		byte[] unwrapArray = new byte[MSGModelConstant.KEY_ATTRS_BOOLEAN_LEN];
		System.arraycopy(pairKeyAttrisArray, 32+32+MSGModelConstant.KEY_ATTRS_BOOLEAN_LEN*7, unwrapArray, 0, MSGModelConstant.KEY_ATTRS_BOOLEAN_LEN);
		String unwrapStr = new String(unwrapArray);
		pubKeyAttributes.cka_unwrap = (unwrapStr.equals("1")) ? true : false;
		
		byte[] signArray = new byte[MSGModelConstant.KEY_ATTRS_BOOLEAN_LEN];
		System.arraycopy(pairKeyAttrisArray, 32+32+MSGModelConstant.KEY_ATTRS_BOOLEAN_LEN*8, signArray, 0, MSGModelConstant.KEY_ATTRS_BOOLEAN_LEN);
		String signStr = new String(signArray);
		pubKeyAttributes.cka_sign = (signStr.equals("1")) ? true : false;
		
		byte[] verifyArray = new byte[MSGModelConstant.KEY_ATTRS_BOOLEAN_LEN];
		System.arraycopy(pairKeyAttrisArray, 32+32+MSGModelConstant.KEY_ATTRS_BOOLEAN_LEN*9, verifyArray, 0, MSGModelConstant.KEY_ATTRS_BOOLEAN_LEN);
		String verifyStr = new String(verifyArray);
		pubKeyAttributes.cka_verify = (verifyStr.equals("1")) ? true : false;
		
		byte[] signRecoverArray = new byte[MSGModelConstant.KEY_ATTRS_BOOLEAN_LEN];
		System.arraycopy(pairKeyAttrisArray, 32+32+MSGModelConstant.KEY_ATTRS_BOOLEAN_LEN*10, signRecoverArray, 0, MSGModelConstant.KEY_ATTRS_BOOLEAN_LEN);
		String signRecoverStr = new String(signRecoverArray);
		pubKeyAttributes.cka_signRecover = (signRecoverStr.equals("1")) ? true : false;
		
		byte[] verifyRecoverArray = new byte[MSGModelConstant.KEY_ATTRS_BOOLEAN_LEN];
		System.arraycopy(pairKeyAttrisArray, 32+32+MSGModelConstant.KEY_ATTRS_BOOLEAN_LEN*11, verifyRecoverArray, 0, MSGModelConstant.KEY_ATTRS_BOOLEAN_LEN);
		String verifyRecoverStr = new String(verifyRecoverArray);
		pubKeyAttributes.cka_verifyRecover = (verifyRecoverStr.equals("1")) ? true : false;
		
		byte[] deriveArray = new byte[MSGModelConstant.KEY_ATTRS_BOOLEAN_LEN];
		System.arraycopy(pairKeyAttrisArray, 32+32+MSGModelConstant.KEY_ATTRS_BOOLEAN_LEN*12, deriveArray, 0, MSGModelConstant.KEY_ATTRS_BOOLEAN_LEN);
		String deriveStr = new String(deriveArray);
		pubKeyAttributes.cka_derive = (deriveStr.equals("1")) ? true : false;
		
		byte[] keyLabelValArray = new byte[256];
		System.arraycopy(pairKeyAttrisArray, 32+32+MSGModelConstant.KEY_ATTRS_BOOLEAN_LEN*13, keyLabelValArray, 0, 256);
		String keyLabelValStrTemp = new String(keyLabelValArray);
		String keyLabelValStr = keyLabelValStrTemp.replaceAll("\\s+","");
		pubKeyAttributes.cka_label = keyLabelValStr.getBytes();		
		
		byte[] modulusBitsValArray = new byte[32];
		System.arraycopy(pairKeyAttrisArray, 32+32+MSGModelConstant.KEY_ATTRS_BOOLEAN_LEN*13+256, modulusBitsValArray, 0, 32);
		String modulusBitsValStrTemp = new String(modulusBitsValArray);
		Integer modulusBitsValInt = Integer.parseInt(modulusBitsValStrTemp);
		pubKeyAttributes.cka_modulus_bits = modulusBitsValInt;		
		
		byte[] pubExpLenArray = new byte[4];
		System.arraycopy(pairKeyAttrisArray, 32+32+MSGModelConstant.KEY_ATTRS_BOOLEAN_LEN*13+256+32, pubExpLenArray, 0, 4);
		String pubExpLenArrayStrTemp = new String(pubExpLenArray);
		Integer pubExpLenInt = Integer.parseInt(pubExpLenArrayStrTemp);
		
		byte[] pubExpValArray = new byte[pubExpLenInt];
		System.arraycopy(pairKeyAttrisArray, 32+32+MSGModelConstant.KEY_ATTRS_BOOLEAN_LEN*13+256+32+4, pubExpValArray, 0, pubExpLenInt);
		pubKeyAttributes.cka_public_exponent = pubExpValArray;
		
		byte[] subjectLenArray = new byte[4];
		System.arraycopy(pairKeyAttrisArray, 32+32+MSGModelConstant.KEY_ATTRS_BOOLEAN_LEN*13+256+32+4+pubExpLenInt, subjectLenArray, 0, 4);
		String subjectLenStrTemp = new String(subjectLenArray);
		Integer subjectLenInt = Integer.parseInt(subjectLenStrTemp);
		
		byte[] subjectValArray = new byte[subjectLenInt];
		System.arraycopy(pairKeyAttrisArray, 32+32+MSGModelConstant.KEY_ATTRS_BOOLEAN_LEN*13+256+32+4+pubExpLenInt+4, subjectValArray, 0, subjectLenInt);
		pubKeyAttributes.cka_subject = subjectValArray;
		
		byte[] startDateArray = new byte[8];
		System.arraycopy(pairKeyAttrisArray, 32+32+MSGModelConstant.KEY_ATTRS_BOOLEAN_LEN*13+256+32+4+pubExpLenInt+4+subjectLenInt, startDateArray, 0, 8);
		pubKeyAttributes.cka_startDate = startDateArray;
		
		byte[] endDateArray = new byte[8];
		System.arraycopy(pairKeyAttrisArray, 32+32+MSGModelConstant.KEY_ATTRS_BOOLEAN_LEN*13+256+32+4+pubExpLenInt+4+subjectLenInt+8, endDateArray, 0, 8);
		pubKeyAttributes.cka_endDate = endDateArray;

	}

	public KeyAttributes getPubKeyAttributes() {
		return pubKeyAttributes;
	}

	public void setPubKeyAttributes(KeyAttributes pubKeyAttributes) {
		this.pubKeyAttributes = pubKeyAttributes;
	}	
	
}
