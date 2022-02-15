package com.kona.kms.crypto.tcp.keyAttrisModel;

import com.kona.kms.crypto.KeyAttributes;
import com.kona.kms.crypto.tcp.utility.MSGModelConstant;

public class KeyAttrisGenKeyPairModel {

	private KeyAttributes pubKeyAttributes;
	private KeyAttributes priKeyAttributes;

	public KeyAttrisGenKeyPairModel(byte[] pairKeyAttrisArray) {

		pubKeyAttributes = new KeyAttributes("PUBLIC");
		priKeyAttributes = new KeyAttributes("PRIVATE");

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
		System.arraycopy(pairKeyAttrisArray, 32 + 32, tokenArray, 0, MSGModelConstant.KEY_ATTRS_BOOLEAN_LEN);
		String tokenStr = new String(tokenArray);
		pubKeyAttributes.cka_token = (tokenStr.equals("1")) ? true : false;

		byte[] privateArray = new byte[MSGModelConstant.KEY_ATTRS_BOOLEAN_LEN];
		System.arraycopy(pairKeyAttrisArray, 32 + 32 + MSGModelConstant.KEY_ATTRS_BOOLEAN_LEN, privateArray, 0, MSGModelConstant.KEY_ATTRS_BOOLEAN_LEN);
		String privateStr = new String(privateArray);
		pubKeyAttributes.cka_private = (privateStr.equals("1")) ? true : false;

		byte[] sensitiveArray = new byte[MSGModelConstant.KEY_ATTRS_BOOLEAN_LEN];
		System.arraycopy(pairKeyAttrisArray, 32 + 32 + MSGModelConstant.KEY_ATTRS_BOOLEAN_LEN * 2, sensitiveArray, 0, MSGModelConstant.KEY_ATTRS_BOOLEAN_LEN);
		String sensitiveStr = new String(sensitiveArray);
		pubKeyAttributes.cka_sensitive = (sensitiveStr.equals("1")) ? true : false;

		byte[] extractableArray = new byte[MSGModelConstant.KEY_ATTRS_BOOLEAN_LEN];
		System.arraycopy(pairKeyAttrisArray, 32 + 32 + MSGModelConstant.KEY_ATTRS_BOOLEAN_LEN * 3, extractableArray, 0, MSGModelConstant.KEY_ATTRS_BOOLEAN_LEN);
		String extractableStr = new String(extractableArray);
		pubKeyAttributes.cka_extractable = (extractableStr.equals("1")) ? true : false;

		byte[] encryptArray = new byte[MSGModelConstant.KEY_ATTRS_BOOLEAN_LEN];
		System.arraycopy(pairKeyAttrisArray, 32 + 32 + MSGModelConstant.KEY_ATTRS_BOOLEAN_LEN * 4, encryptArray, 0, MSGModelConstant.KEY_ATTRS_BOOLEAN_LEN);
		String encryptStr = new String(encryptArray);
		pubKeyAttributes.cka_encrypt = (encryptStr.equals("1")) ? true : false;

		byte[] decryptArray = new byte[MSGModelConstant.KEY_ATTRS_BOOLEAN_LEN];
		System.arraycopy(pairKeyAttrisArray, 32 + 32 + MSGModelConstant.KEY_ATTRS_BOOLEAN_LEN * 5, decryptArray, 0, MSGModelConstant.KEY_ATTRS_BOOLEAN_LEN);
		String decryptStr = new String(decryptArray);
		pubKeyAttributes.cka_decrypt = (decryptStr.equals("1")) ? true : false;

		byte[] wrapArray = new byte[MSGModelConstant.KEY_ATTRS_BOOLEAN_LEN];
		System.arraycopy(pairKeyAttrisArray, 32 + 32 + MSGModelConstant.KEY_ATTRS_BOOLEAN_LEN * 6, wrapArray, 0, MSGModelConstant.KEY_ATTRS_BOOLEAN_LEN);
		String wrapStr = new String(wrapArray);
		pubKeyAttributes.cka_wrap = (wrapStr.equals("1")) ? true : false;

		byte[] unwrapArray = new byte[MSGModelConstant.KEY_ATTRS_BOOLEAN_LEN];
		System.arraycopy(pairKeyAttrisArray, 32 + 32 + MSGModelConstant.KEY_ATTRS_BOOLEAN_LEN * 7, unwrapArray, 0, MSGModelConstant.KEY_ATTRS_BOOLEAN_LEN);
		String unwrapStr = new String(unwrapArray);
		pubKeyAttributes.cka_unwrap = (unwrapStr.equals("1")) ? true : false;

		byte[] signArray = new byte[MSGModelConstant.KEY_ATTRS_BOOLEAN_LEN];
		System.arraycopy(pairKeyAttrisArray, 32 + 32 + MSGModelConstant.KEY_ATTRS_BOOLEAN_LEN * 8, signArray, 0, MSGModelConstant.KEY_ATTRS_BOOLEAN_LEN);
		String signStr = new String(signArray);
		pubKeyAttributes.cka_sign = (signStr.equals("1")) ? true : false;

		byte[] verifyArray = new byte[MSGModelConstant.KEY_ATTRS_BOOLEAN_LEN];
		System.arraycopy(pairKeyAttrisArray, 32 + 32 + MSGModelConstant.KEY_ATTRS_BOOLEAN_LEN * 9, verifyArray, 0, MSGModelConstant.KEY_ATTRS_BOOLEAN_LEN);
		String verifyStr = new String(verifyArray);
		pubKeyAttributes.cka_verify = (verifyStr.equals("1")) ? true : false;

		byte[] signRecoverArray = new byte[MSGModelConstant.KEY_ATTRS_BOOLEAN_LEN];
		System.arraycopy(pairKeyAttrisArray, 32 + 32 + MSGModelConstant.KEY_ATTRS_BOOLEAN_LEN * 10, signRecoverArray, 0, MSGModelConstant.KEY_ATTRS_BOOLEAN_LEN);
		String signRecoverStr = new String(signRecoverArray);
		pubKeyAttributes.cka_signRecover = (signRecoverStr.equals("1")) ? true : false;

		byte[] verifyRecoverArray = new byte[MSGModelConstant.KEY_ATTRS_BOOLEAN_LEN];
		System.arraycopy(pairKeyAttrisArray, 32 + 32 + MSGModelConstant.KEY_ATTRS_BOOLEAN_LEN * 11, verifyRecoverArray, 0, MSGModelConstant.KEY_ATTRS_BOOLEAN_LEN);
		String verifyRecoverStr = new String(verifyRecoverArray);
		pubKeyAttributes.cka_verifyRecover = (verifyRecoverStr.equals("1")) ? true : false;

		byte[] deriveArray = new byte[MSGModelConstant.KEY_ATTRS_BOOLEAN_LEN];
		System.arraycopy(pairKeyAttrisArray, 32 + 32 + MSGModelConstant.KEY_ATTRS_BOOLEAN_LEN * 12, deriveArray, 0, MSGModelConstant.KEY_ATTRS_BOOLEAN_LEN);
		String deriveStr = new String(deriveArray);
		pubKeyAttributes.cka_derive = (deriveStr.equals("1")) ? true : false;

		byte[] keyLabelValArray = new byte[256];
		System.arraycopy(pairKeyAttrisArray, 32 + 32 + MSGModelConstant.KEY_ATTRS_BOOLEAN_LEN * 13, keyLabelValArray, 0, 256);
		String keyLabelValStrTemp = new String(keyLabelValArray);
		String keyLabelValStr = keyLabelValStrTemp.replaceAll("\\s+", "");
		pubKeyAttributes.cka_label = keyLabelValStr.getBytes();

		byte[] modulusBitsValArray = new byte[32];
		System.arraycopy(pairKeyAttrisArray, 32 + 32 + MSGModelConstant.KEY_ATTRS_BOOLEAN_LEN * 13 + 256, modulusBitsValArray, 0, 32);
		String modulusBitsValStrTemp = new String(modulusBitsValArray);
		Integer modulusBitsValInt = Integer.parseInt(modulusBitsValStrTemp);
		pubKeyAttributes.cka_modulus_bits = modulusBitsValInt;

		byte[] pubExpLenArray = new byte[4];
		System.arraycopy(pairKeyAttrisArray, 32 + 32 + MSGModelConstant.KEY_ATTRS_BOOLEAN_LEN * 13 + 256 + 32, pubExpLenArray, 0, 4);
		String pubExpLenArrayStrTemp = new String(pubExpLenArray);
		Integer pubExpLenInt = Integer.parseInt(pubExpLenArrayStrTemp);

		byte[] pubExpValArray = new byte[pubExpLenInt];
		System.arraycopy(pairKeyAttrisArray, 32 + 32 + MSGModelConstant.KEY_ATTRS_BOOLEAN_LEN * 13 + 256 + 32 + 4, pubExpValArray, 0, pubExpLenInt);
		pubKeyAttributes.cka_public_exponent = pubExpValArray;

		byte[] subjectLenArray = new byte[4];
		System.arraycopy(pairKeyAttrisArray, 32 + 32 + MSGModelConstant.KEY_ATTRS_BOOLEAN_LEN * 13 + 256 + 32 + 4 + pubExpLenInt, subjectLenArray, 0, 4);
		String subjectLenStrTemp = new String(subjectLenArray);
		Integer subjectLenInt = Integer.parseInt(subjectLenStrTemp);

		byte[] subjectValArray = new byte[subjectLenInt];
		System.arraycopy(pairKeyAttrisArray, 32 + 32 + MSGModelConstant.KEY_ATTRS_BOOLEAN_LEN * 13 + 256 + 32 + 4 + pubExpLenInt + 4, subjectValArray, 0, subjectLenInt);
		pubKeyAttributes.cka_subject = subjectValArray;

		byte[] startDateArray = new byte[8];
		System.arraycopy(pairKeyAttrisArray, 32 + 32 + MSGModelConstant.KEY_ATTRS_BOOLEAN_LEN * 13 + 256 + 32 + 4 + pubExpLenInt + 4 + subjectLenInt, startDateArray, 0, 8);
		pubKeyAttributes.cka_startDate = startDateArray;

		byte[] endDateArray = new byte[8];
		System.arraycopy(pairKeyAttrisArray, 32 + 32 + MSGModelConstant.KEY_ATTRS_BOOLEAN_LEN * 13 + 256 + 32 + 4 + pubExpLenInt + 4 + subjectLenInt + 8, endDateArray, 0, 8);
		pubKeyAttributes.cka_endDate = endDateArray;

		// -------------- private key attributes

		keyClassArray = new byte[32];
		System.arraycopy(pairKeyAttrisArray, 32 + 32 + MSGModelConstant.KEY_ATTRS_BOOLEAN_LEN * 13 + 256 + 32 + 4 + pubExpLenInt + 4 + subjectLenInt + 8 + 8, keyClassArray, 0, 32);
		keyClassStr = new String(keyClassArray);
		keyClassStr = keyClassStr.replaceAll("\\s+", "");
		priKeyAttributes.cka_keyClass = Long.valueOf(keyClassStr);

		keyTypeArray = new byte[32];
		System.arraycopy(pairKeyAttrisArray, 32 + 32 + MSGModelConstant.KEY_ATTRS_BOOLEAN_LEN * 13 + 256 + 32 + 4 + pubExpLenInt + 4 + subjectLenInt + 8 + 8 + 32, keyTypeArray, 0, 32);
		keyTypeStr = new String(keyTypeArray);
		keyTypeStr = keyTypeStr.replaceAll("\\s+", "");
		priKeyAttributes.cka_keyType = Long.valueOf(keyTypeStr);

		tokenArray = new byte[1];
		System.arraycopy(pairKeyAttrisArray, 32 + 32 + MSGModelConstant.KEY_ATTRS_BOOLEAN_LEN * 13 + 256 + 32 + 4 + pubExpLenInt + 4 + subjectLenInt + 8 + 8 + 32 + 32, tokenArray, 0, 1);
		tokenStr = new String(tokenArray);
		priKeyAttributes.cka_token = (tokenStr.equals("1")) ? true : false;

		privateArray = new byte[1];
		System.arraycopy(pairKeyAttrisArray, 32 + 32 + MSGModelConstant.KEY_ATTRS_BOOLEAN_LEN * 13 + 256 + 32 + 4 + pubExpLenInt + 4 + subjectLenInt + 8 + 8 + 32 + 32 + 1, privateArray, 0, 1);
		privateStr = new String(privateArray);
		priKeyAttributes.cka_private = (privateStr.equals("1")) ? true : false;

		sensitiveArray = new byte[1];
		System.arraycopy(pairKeyAttrisArray, 32 + 32 + MSGModelConstant.KEY_ATTRS_BOOLEAN_LEN * 13 + 256 + 32 + 4 + pubExpLenInt + 4 + subjectLenInt + 8 + 8 + 32 + 32 + 1 + 1, sensitiveArray, 0, 1);
		sensitiveStr = new String(sensitiveArray);
		priKeyAttributes.cka_sensitive = (sensitiveStr.equals("1")) ? true : false;

		extractableArray = new byte[1];
		System.arraycopy(pairKeyAttrisArray, 32 + 32 + MSGModelConstant.KEY_ATTRS_BOOLEAN_LEN * 13 + 256 + 32 + 4 + pubExpLenInt + 4 + subjectLenInt + 8 + 8 + 32 + 32 + 1 + 1 + 1, extractableArray, 0, 1);
		extractableStr = new String(extractableArray);
		priKeyAttributes.cka_extractable = (extractableStr.equals("1")) ? true : false;

		encryptArray = new byte[1];
		System.arraycopy(pairKeyAttrisArray, 32 + 32 + MSGModelConstant.KEY_ATTRS_BOOLEAN_LEN * 13 + 256 + 32 + 4 + pubExpLenInt + 4 + subjectLenInt + 8 + 8 + 32 + 32 + 1 + 1 + 1 + 1, encryptArray, 0, 1);
		encryptStr = new String(encryptArray);
		priKeyAttributes.cka_encrypt = (encryptStr.equals("1")) ? true : false;

		decryptArray = new byte[1];
		System.arraycopy(pairKeyAttrisArray, 32 + 32 + MSGModelConstant.KEY_ATTRS_BOOLEAN_LEN * 13 + 256 + 32 + 4 + pubExpLenInt + 4 + subjectLenInt + 8 + 8 + 32 + 32 + 1 + 1 + 1 + 1 + 1, decryptArray, 0, 1);
		decryptStr = new String(decryptArray);
		priKeyAttributes.cka_decrypt = (decryptStr.equals("1")) ? true : false;

		wrapArray = new byte[1];
		System.arraycopy(pairKeyAttrisArray, 32 + 32 + MSGModelConstant.KEY_ATTRS_BOOLEAN_LEN * 13 + 256 + 32 + 4 + pubExpLenInt + 4 + subjectLenInt + 8 + 8 + 32 + 32 + 1 + 1 + 1 + 1 + 1 + 1, wrapArray, 0, 1);
		wrapStr = new String(wrapArray);
		priKeyAttributes.cka_wrap = (wrapStr.equals("1")) ? true : false;

		unwrapArray = new byte[1];
		System.arraycopy(pairKeyAttrisArray, 32 + 32 + MSGModelConstant.KEY_ATTRS_BOOLEAN_LEN * 13 + 256 + 32 + 4 + pubExpLenInt + 4 + subjectLenInt + 8 + 8 + 32 + 32 + 1 + 1 + 1 + 1 + 1 + 1 + 1, unwrapArray, 0, 1);
		unwrapStr = new String(unwrapArray);
		priKeyAttributes.cka_unwrap = (unwrapStr.equals("1")) ? true : false;

		signArray = new byte[1];
		System.arraycopy(pairKeyAttrisArray, 32 + 32 + MSGModelConstant.KEY_ATTRS_BOOLEAN_LEN * 13 + 256 + 32 + 4 + pubExpLenInt + 4 + subjectLenInt + 8 + 8 + 32 + 32 + 1 + 1 + 1 + 1 + 1 + 1 + 1 + 1, signArray, 0, 1);
		signStr = new String(signArray);
		priKeyAttributes.cka_sign = (signStr.equals("1")) ? true : false;

		verifyArray = new byte[1];
		System.arraycopy(pairKeyAttrisArray, 32 + 32 + MSGModelConstant.KEY_ATTRS_BOOLEAN_LEN * 13 + 256 + 32 + 4 + pubExpLenInt + 4 + subjectLenInt + 8 + 8 + 32 + 32 + 1 + 1 + 1 + 1 + 1 + 1 + 1 + 1 + 1, verifyArray, 0, 1);
		verifyStr = new String(verifyArray);
		priKeyAttributes.cka_verify = (verifyStr.equals("1")) ? true : false;

		signRecoverArray = new byte[1];
		System.arraycopy(pairKeyAttrisArray, 32 + 32 + MSGModelConstant.KEY_ATTRS_BOOLEAN_LEN * 13 + 256 + 32 + 4 + pubExpLenInt + 4 + subjectLenInt + 8 + 8 + 32 + 32 + 1 + 1 + 1 + 1 + 1 + 1 + 1 + 1 + 1 + 1, signRecoverArray, 0, 1);
		signRecoverStr = new String(signRecoverArray);
		priKeyAttributes.cka_signRecover = (signRecoverStr.equals("1")) ? true : false;

		verifyRecoverArray = new byte[1];
		System.arraycopy(pairKeyAttrisArray, 32 + 32 + MSGModelConstant.KEY_ATTRS_BOOLEAN_LEN * 13 + 256 + 32 + 4 + pubExpLenInt + 4 + subjectLenInt + 8 + 8 + 32 + 32 + 1 + 1 + 1 + 1 + 1 + 1 + 1 + 1 + 1 + 1 + 1, verifyRecoverArray, 0, 1);
		verifyRecoverStr = new String(verifyRecoverArray);
		priKeyAttributes.cka_verifyRecover = (verifyRecoverStr.equals("1")) ? true : false;

		deriveArray = new byte[1];
		System.arraycopy(pairKeyAttrisArray, 32 + 32 + MSGModelConstant.KEY_ATTRS_BOOLEAN_LEN * 13 + 256 + 32 + 4 + pubExpLenInt + 4 + subjectLenInt + 8 + 8 + 32 + 32 + 1 + 1 + 1 + 1 + 1 + 1 + 1 + 1 + 1 + 1 + 1 + 1, deriveArray, 0, 1);
		deriveStr = new String(deriveArray);
		priKeyAttributes.cka_derive = (deriveStr.equals("1")) ? true : false;

		keyLabelValArray = new byte[256];
		System.arraycopy(pairKeyAttrisArray, 32 + 32 + MSGModelConstant.KEY_ATTRS_BOOLEAN_LEN * 13 + 256 + 32 + 4 + pubExpLenInt + 4 + subjectLenInt + 8 + 8 + 32 + 32 + 1 + 1 + 1 + 1 + 1 + 1 + 1 + 1 + 1 + 1 + 1 + 1 + 1, keyLabelValArray, 0, 256);
		keyLabelValStrTemp = new String(keyLabelValArray);
		keyLabelValStr = keyLabelValStrTemp.replaceAll("\\s+", "");
		priKeyAttributes.cka_label = keyLabelValStr.getBytes();

		subjectLenArray = new byte[4];
		System.arraycopy(pairKeyAttrisArray, 32 + 32 + MSGModelConstant.KEY_ATTRS_BOOLEAN_LEN * 13 + 256 + 32 + 4 + pubExpLenInt + 4 + subjectLenInt + 8 + 8 + 32 + 32 + 1 + 1 + 1 + 1 + 1 + 1 + 1 + 1 + 1 + 1 + 1 + 1 + 1 + 256, subjectLenArray, 0, 4);
		subjectLenStrTemp = new String(subjectLenArray);
		subjectLenInt = Integer.parseInt(subjectLenStrTemp);

		subjectValArray = new byte[subjectLenInt];
		System.arraycopy(pairKeyAttrisArray, 32 + 32 + MSGModelConstant.KEY_ATTRS_BOOLEAN_LEN * 13 + 256 + 32 + 4 + pubExpLenInt + 4 + subjectLenInt + 8 + 8 + 32 + 32 + 1 + 1 + 1 + 1 + 1 + 1 + 1 + 1 + 1 + 1 + 1 + 1 + 1 + 256 + 4, subjectValArray, 0, subjectLenInt);
		priKeyAttributes.cka_subject = subjectValArray;

		startDateArray = new byte[8];
		System.arraycopy(pairKeyAttrisArray, 32 + 32 + MSGModelConstant.KEY_ATTRS_BOOLEAN_LEN * 13 + 256 + 32 + 4 + pubExpLenInt + 4 + subjectLenInt + 8 + 8 + 32 + 32 + 1 + 1 + 1 + 1 + 1 + 1 + 1 + 1 + 1 + 1 + 1 + 1 + 1 + 256 + 4 + subjectLenInt, startDateArray, 0, 8);
		priKeyAttributes.cka_startDate = startDateArray;

		endDateArray = new byte[8];
		System.arraycopy(pairKeyAttrisArray, 32 + 32 + MSGModelConstant.KEY_ATTRS_BOOLEAN_LEN * 13 + 256 + 32 + 4 + pubExpLenInt + 4 + subjectLenInt + 8 + 8 + 32 + 32 + 1 + 1 + 1 + 1 + 1 + 1 + 1 + 1 + 1 + 1 + 1 + 1 + 1 + 256 + 4 + subjectLenInt + 8, endDateArray, 0, 8);
		priKeyAttributes.cka_endDate = endDateArray;

	}

	public KeyAttributes getPubKeyAttributes() {
		return pubKeyAttributes;
	}

	public void setPubKeyAttributes(KeyAttributes pubKeyAttributes) {
		this.pubKeyAttributes = pubKeyAttributes;
	}

	public KeyAttributes getPriKeyAttributes() {
		return priKeyAttributes;
	}

	public void setPriKeyAttributes(KeyAttributes priKeyAttributes) {
		this.priKeyAttributes = priKeyAttributes;
	}

}
