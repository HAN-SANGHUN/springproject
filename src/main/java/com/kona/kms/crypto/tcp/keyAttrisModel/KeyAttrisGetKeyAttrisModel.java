package com.kona.kms.crypto.tcp.keyAttrisModel;

import com.kona.kms.crypto.KeyAttributes;
import com.kona.kms.crypto.tcp.utility.MSGModelConstant;

public class KeyAttrisGetKeyAttrisModel {

	private KeyAttributes keyAttributes;

	public KeyAttrisGetKeyAttrisModel(byte[] keyAttrisArray) {

		byte[] keyClassCheckArray = new byte[32];
		System.arraycopy(keyAttrisArray, 0, keyClassCheckArray, 0, 32);
		String keyClassCheckStr = new String(keyClassCheckArray);
		keyClassCheckStr = keyClassCheckStr.replaceAll("\\s+", "");

		if (Long.valueOf(keyClassCheckStr) == 4) {
			keyAttributes = new KeyAttributes("SECRET");

			byte[] keyClassArray = new byte[32];
			System.arraycopy(keyAttrisArray, 0, keyClassArray, 0, 32);
			String keyClassStr = new String(keyClassArray);
			keyClassStr = keyClassStr.replaceAll("\\s+", "");
			keyAttributes.cka_keyClass = Long.valueOf(keyClassStr);

			byte[] keyTypeArray = new byte[32];
			System.arraycopy(keyAttrisArray, 32, keyTypeArray, 0, 32);
			String keyTypeStr = new String(keyTypeArray);
			keyTypeStr = keyTypeStr.replaceAll("\\s+", "");
			keyAttributes.cka_keyType = Long.valueOf(keyTypeStr);

			byte[] tokenArray = new byte[MSGModelConstant.KEY_ATTRS_BOOLEAN_LEN];
			System.arraycopy(keyAttrisArray, 32 + 32, tokenArray, 0, MSGModelConstant.KEY_ATTRS_BOOLEAN_LEN);
			String tokenStr = new String(tokenArray);
			keyAttributes.cka_token = (tokenStr.equals("1")) ? true : false;

			byte[] privateArray = new byte[MSGModelConstant.KEY_ATTRS_BOOLEAN_LEN];
			System.arraycopy(keyAttrisArray, 32 + 32 + MSGModelConstant.KEY_ATTRS_BOOLEAN_LEN, privateArray, 0, MSGModelConstant.KEY_ATTRS_BOOLEAN_LEN);
			String privateStr = new String(privateArray);
			keyAttributes.cka_private = (privateStr.equals("1")) ? true : false;

			byte[] sensitiveArray = new byte[MSGModelConstant.KEY_ATTRS_BOOLEAN_LEN];
			System.arraycopy(keyAttrisArray, 32 + 32 + MSGModelConstant.KEY_ATTRS_BOOLEAN_LEN * 2, sensitiveArray, 0, MSGModelConstant.KEY_ATTRS_BOOLEAN_LEN);
			String sensitiveStr = new String(sensitiveArray);
			keyAttributes.cka_sensitive = (sensitiveStr.equals("1")) ? true : false;

			byte[] extractableArray = new byte[MSGModelConstant.KEY_ATTRS_BOOLEAN_LEN];
			System.arraycopy(keyAttrisArray, 32 + 32 + MSGModelConstant.KEY_ATTRS_BOOLEAN_LEN * 3, extractableArray, 0, MSGModelConstant.KEY_ATTRS_BOOLEAN_LEN);
			String extractableStr = new String(extractableArray);
			keyAttributes.cka_extractable = (extractableStr.equals("1")) ? true : false;

			byte[] encryptArray = new byte[MSGModelConstant.KEY_ATTRS_BOOLEAN_LEN];
			System.arraycopy(keyAttrisArray, 32 + 32 + MSGModelConstant.KEY_ATTRS_BOOLEAN_LEN * 4, encryptArray, 0, MSGModelConstant.KEY_ATTRS_BOOLEAN_LEN);
			String encryptStr = new String(encryptArray);
			keyAttributes.cka_encrypt = (encryptStr.equals("1")) ? true : false;

			byte[] decryptArray = new byte[MSGModelConstant.KEY_ATTRS_BOOLEAN_LEN];
			System.arraycopy(keyAttrisArray, 32 + 32 + MSGModelConstant.KEY_ATTRS_BOOLEAN_LEN * 5, decryptArray, 0, MSGModelConstant.KEY_ATTRS_BOOLEAN_LEN);
			String decryptStr = new String(decryptArray);
			keyAttributes.cka_decrypt = (decryptStr.equals("1")) ? true : false;

			byte[] wrapArray = new byte[MSGModelConstant.KEY_ATTRS_BOOLEAN_LEN];
			System.arraycopy(keyAttrisArray, 32 + 32 + MSGModelConstant.KEY_ATTRS_BOOLEAN_LEN * 6, wrapArray, 0, MSGModelConstant.KEY_ATTRS_BOOLEAN_LEN);
			String wrapStr = new String(wrapArray);
			keyAttributes.cka_wrap = (wrapStr.equals("1")) ? true : false;

			byte[] unwrapArray = new byte[MSGModelConstant.KEY_ATTRS_BOOLEAN_LEN];
			System.arraycopy(keyAttrisArray, 32 + 32 + MSGModelConstant.KEY_ATTRS_BOOLEAN_LEN * 7, unwrapArray, 0, MSGModelConstant.KEY_ATTRS_BOOLEAN_LEN);
			String unwrapStr = new String(unwrapArray);
			keyAttributes.cka_unwrap = (unwrapStr.equals("1")) ? true : false;

			byte[] signArray = new byte[MSGModelConstant.KEY_ATTRS_BOOLEAN_LEN];
			System.arraycopy(keyAttrisArray, 32 + 32 + MSGModelConstant.KEY_ATTRS_BOOLEAN_LEN * 8, signArray, 0, MSGModelConstant.KEY_ATTRS_BOOLEAN_LEN);
			String signStr = new String(signArray);
			keyAttributes.cka_sign = (signStr.equals("1")) ? true : false;

			byte[] verifyArray = new byte[MSGModelConstant.KEY_ATTRS_BOOLEAN_LEN];
			System.arraycopy(keyAttrisArray, 32 + 32 + MSGModelConstant.KEY_ATTRS_BOOLEAN_LEN * 9, verifyArray, 0, MSGModelConstant.KEY_ATTRS_BOOLEAN_LEN);
			String verifyStr = new String(verifyArray);
			keyAttributes.cka_verify = (verifyStr.equals("1")) ? true : false;

			byte[] signRecoverArray = new byte[MSGModelConstant.KEY_ATTRS_BOOLEAN_LEN];
			System.arraycopy(keyAttrisArray, 32 + 32 + MSGModelConstant.KEY_ATTRS_BOOLEAN_LEN * 10, signRecoverArray, 0, MSGModelConstant.KEY_ATTRS_BOOLEAN_LEN);
			String signRecoverStr = new String(signRecoverArray);
			keyAttributes.cka_signRecover = (signRecoverStr.equals("1")) ? true : false;

			byte[] verifyRecoverArray = new byte[MSGModelConstant.KEY_ATTRS_BOOLEAN_LEN];
			System.arraycopy(keyAttrisArray, 32 + 32 + MSGModelConstant.KEY_ATTRS_BOOLEAN_LEN * 11, verifyRecoverArray, 0, MSGModelConstant.KEY_ATTRS_BOOLEAN_LEN);
			String verifyRecoverStr = new String(verifyRecoverArray);
			keyAttributes.cka_verifyRecover = (verifyRecoverStr.equals("1")) ? true : false;

			byte[] deriveArray = new byte[MSGModelConstant.KEY_ATTRS_BOOLEAN_LEN];
			System.arraycopy(keyAttrisArray, 32 + 32 + MSGModelConstant.KEY_ATTRS_BOOLEAN_LEN * 12, deriveArray, 0, MSGModelConstant.KEY_ATTRS_BOOLEAN_LEN);
			String deriveStr = new String(deriveArray);
			keyAttributes.cka_derive = (deriveStr.equals("1")) ? true : false;

			byte[] keyLabelValArray = new byte[256];
			System.arraycopy(keyAttrisArray, 32 + 32 + MSGModelConstant.KEY_ATTRS_BOOLEAN_LEN * 13, keyLabelValArray, 0, 256);
			String keyLabelValStrTemp = new String(keyLabelValArray);
			String keyLabelValStr = keyLabelValStrTemp.replaceAll("\\s+", "");
			keyAttributes.cka_label = keyLabelValStr.getBytes();

			byte[] startDateArray = new byte[8];
			System.arraycopy(keyAttrisArray, 32 + 32 + MSGModelConstant.KEY_ATTRS_BOOLEAN_LEN * 13 + 256, startDateArray, 0, 8);
			keyAttributes.cka_startDate = startDateArray;

			byte[] endDateArray = new byte[8];
			System.arraycopy(keyAttrisArray, 32 + 32 + MSGModelConstant.KEY_ATTRS_BOOLEAN_LEN * 13 + 256 + 8, endDateArray, 0, 8);
			keyAttributes.cka_endDate = endDateArray;

			byte[] keyValLenValArray = new byte[32];
			System.arraycopy(keyAttrisArray, 32 + 32 + MSGModelConstant.KEY_ATTRS_BOOLEAN_LEN * 13 + 256 + 8 + 8, keyValLenValArray, 0, 32);
			String keyValLenValStr = new String(keyValLenValArray);
			keyValLenValStr = keyValLenValStr.replaceAll("\\s+", "");
			keyAttributes.cka_value_len = Long.valueOf(keyValLenValStr);
		} else if (Long.valueOf(keyClassCheckStr) == 2) {

			keyAttributes = new KeyAttributes("PUBLIC");

			byte[] keyClassArray = new byte[32];
			System.arraycopy(keyAttrisArray, 0, keyClassArray, 0, 32);
			String keyClassStr = new String(keyClassArray);
			keyClassStr = keyClassStr.replaceAll("\\s+", "");
			keyAttributes.cka_keyClass = Long.valueOf(keyClassStr);

			byte[] keyTypeArray = new byte[32];
			System.arraycopy(keyAttrisArray, 32, keyTypeArray, 0, 32);
			String keyTypeStr = new String(keyTypeArray);
			keyTypeStr = keyTypeStr.replaceAll("\\s+", "");
			keyAttributes.cka_keyType = Long.valueOf(keyTypeStr);

			byte[] tokenArray = new byte[MSGModelConstant.KEY_ATTRS_BOOLEAN_LEN];
			System.arraycopy(keyAttrisArray, 32 + 32, tokenArray, 0, MSGModelConstant.KEY_ATTRS_BOOLEAN_LEN);
			String tokenStr = new String(tokenArray);
			keyAttributes.cka_token = (tokenStr.equals("1")) ? true : false;

			byte[] privateArray = new byte[MSGModelConstant.KEY_ATTRS_BOOLEAN_LEN];
			System.arraycopy(keyAttrisArray, 32 + 32 + MSGModelConstant.KEY_ATTRS_BOOLEAN_LEN, privateArray, 0, MSGModelConstant.KEY_ATTRS_BOOLEAN_LEN);
			String privateStr = new String(privateArray);
			keyAttributes.cka_private = (privateStr.equals("1")) ? true : false;

			byte[] sensitiveArray = new byte[MSGModelConstant.KEY_ATTRS_BOOLEAN_LEN];
			System.arraycopy(keyAttrisArray, 32 + 32 + MSGModelConstant.KEY_ATTRS_BOOLEAN_LEN * 2, sensitiveArray, 0, MSGModelConstant.KEY_ATTRS_BOOLEAN_LEN);
			String sensitiveStr = new String(sensitiveArray);
			keyAttributes.cka_sensitive = (sensitiveStr.equals("1")) ? true : false;

			byte[] extractableArray = new byte[MSGModelConstant.KEY_ATTRS_BOOLEAN_LEN];
			System.arraycopy(keyAttrisArray, 32 + 32 + MSGModelConstant.KEY_ATTRS_BOOLEAN_LEN * 3, extractableArray, 0, MSGModelConstant.KEY_ATTRS_BOOLEAN_LEN);
			String extractableStr = new String(extractableArray);
			keyAttributes.cka_extractable = (extractableStr.equals("1")) ? true : false;

			byte[] encryptArray = new byte[MSGModelConstant.KEY_ATTRS_BOOLEAN_LEN];
			System.arraycopy(keyAttrisArray, 32 + 32 + MSGModelConstant.KEY_ATTRS_BOOLEAN_LEN * 4, encryptArray, 0, MSGModelConstant.KEY_ATTRS_BOOLEAN_LEN);
			String encryptStr = new String(encryptArray);
			keyAttributes.cka_encrypt = (encryptStr.equals("1")) ? true : false;

			byte[] decryptArray = new byte[MSGModelConstant.KEY_ATTRS_BOOLEAN_LEN];
			System.arraycopy(keyAttrisArray, 32 + 32 + MSGModelConstant.KEY_ATTRS_BOOLEAN_LEN * 5, decryptArray, 0, MSGModelConstant.KEY_ATTRS_BOOLEAN_LEN);
			String decryptStr = new String(decryptArray);
			keyAttributes.cka_decrypt = (decryptStr.equals("1")) ? true : false;

			byte[] wrapArray = new byte[MSGModelConstant.KEY_ATTRS_BOOLEAN_LEN];
			System.arraycopy(keyAttrisArray, 32 + 32 + MSGModelConstant.KEY_ATTRS_BOOLEAN_LEN * 6, wrapArray, 0, MSGModelConstant.KEY_ATTRS_BOOLEAN_LEN);
			String wrapStr = new String(wrapArray);
			keyAttributes.cka_wrap = (wrapStr.equals("1")) ? true : false;

			byte[] unwrapArray = new byte[MSGModelConstant.KEY_ATTRS_BOOLEAN_LEN];
			System.arraycopy(keyAttrisArray, 32 + 32 + MSGModelConstant.KEY_ATTRS_BOOLEAN_LEN * 7, unwrapArray, 0, MSGModelConstant.KEY_ATTRS_BOOLEAN_LEN);
			String unwrapStr = new String(unwrapArray);
			keyAttributes.cka_unwrap = (unwrapStr.equals("1")) ? true : false;

			byte[] signArray = new byte[MSGModelConstant.KEY_ATTRS_BOOLEAN_LEN];
			System.arraycopy(keyAttrisArray, 32 + 32 + MSGModelConstant.KEY_ATTRS_BOOLEAN_LEN * 8, signArray, 0, MSGModelConstant.KEY_ATTRS_BOOLEAN_LEN);
			String signStr = new String(signArray);
			keyAttributes.cka_sign = (signStr.equals("1")) ? true : false;

			byte[] verifyArray = new byte[MSGModelConstant.KEY_ATTRS_BOOLEAN_LEN];
			System.arraycopy(keyAttrisArray, 32 + 32 + MSGModelConstant.KEY_ATTRS_BOOLEAN_LEN * 9, verifyArray, 0, MSGModelConstant.KEY_ATTRS_BOOLEAN_LEN);
			String verifyStr = new String(verifyArray);
			keyAttributes.cka_verify = (verifyStr.equals("1")) ? true : false;

			byte[] signRecoverArray = new byte[MSGModelConstant.KEY_ATTRS_BOOLEAN_LEN];
			System.arraycopy(keyAttrisArray, 32 + 32 + MSGModelConstant.KEY_ATTRS_BOOLEAN_LEN * 10, signRecoverArray, 0, MSGModelConstant.KEY_ATTRS_BOOLEAN_LEN);
			String signRecoverStr = new String(signRecoverArray);
			keyAttributes.cka_signRecover = (signRecoverStr.equals("1")) ? true : false;

			byte[] verifyRecoverArray = new byte[MSGModelConstant.KEY_ATTRS_BOOLEAN_LEN];
			System.arraycopy(keyAttrisArray, 32 + 32 + MSGModelConstant.KEY_ATTRS_BOOLEAN_LEN * 11, verifyRecoverArray, 0, MSGModelConstant.KEY_ATTRS_BOOLEAN_LEN);
			String verifyRecoverStr = new String(verifyRecoverArray);
			keyAttributes.cka_verifyRecover = (verifyRecoverStr.equals("1")) ? true : false;

			byte[] deriveArray = new byte[MSGModelConstant.KEY_ATTRS_BOOLEAN_LEN];
			System.arraycopy(keyAttrisArray, 32 + 32 + MSGModelConstant.KEY_ATTRS_BOOLEAN_LEN * 12, deriveArray, 0, MSGModelConstant.KEY_ATTRS_BOOLEAN_LEN);
			String deriveStr = new String(deriveArray);
			keyAttributes.cka_derive = (deriveStr.equals("1")) ? true : false;

			byte[] keyLabelValArray = new byte[256];
			System.arraycopy(keyAttrisArray, 32 + 32 + MSGModelConstant.KEY_ATTRS_BOOLEAN_LEN * 13, keyLabelValArray, 0, 256);
			String keyLabelValStrTemp = new String(keyLabelValArray);
			String keyLabelValStr = keyLabelValStrTemp.replaceAll("\\s+", "");
			keyAttributes.cka_label = keyLabelValStr.getBytes();
			
			
			
			
			//add by shifei
			

			byte[] modulusLenArray = new byte[4];
			System.arraycopy(keyAttrisArray, 32 + 32 + MSGModelConstant.KEY_ATTRS_BOOLEAN_LEN * 13 + 256, modulusLenArray, 0, 4);
			String modulusLenStrTemp = new String(modulusLenArray);
			Integer modulusLenInt = Integer.parseInt(modulusLenStrTemp);

			byte[] modulusValArray = new byte[modulusLenInt];
			System.arraycopy(keyAttrisArray, 32 + 32 + MSGModelConstant.KEY_ATTRS_BOOLEAN_LEN * 13 + 256 + 4, modulusValArray, 0, modulusLenInt);
			keyAttributes.cka_modulus = modulusValArray;
			
			
			
			

			byte[] modulusBitsValArray = new byte[32];
			System.arraycopy(keyAttrisArray, 32 + 32 + MSGModelConstant.KEY_ATTRS_BOOLEAN_LEN * 13 + 256 + 4 + modulusLenInt, modulusBitsValArray, 0, 32);
			String modulusBitsValStrTemp = new String(modulusBitsValArray);
			Integer modulusBitsValInt = Integer.parseInt(modulusBitsValStrTemp);
			keyAttributes.cka_modulus_bits = modulusBitsValInt;

			byte[] pubExpLenArray = new byte[4];
			System.arraycopy(keyAttrisArray, 32 + 32 + MSGModelConstant.KEY_ATTRS_BOOLEAN_LEN * 13 + 256 + 4 + modulusLenInt + 32, pubExpLenArray, 0, 4);
			String pubExpLenArrayStrTemp = new String(pubExpLenArray);
			Integer pubExpLenInt = Integer.parseInt(pubExpLenArrayStrTemp);

			byte[] pubExpValArray = new byte[pubExpLenInt];
			System.arraycopy(keyAttrisArray, 32 + 32 + MSGModelConstant.KEY_ATTRS_BOOLEAN_LEN * 13 + 256 + 4 + modulusLenInt + 32 + 4, pubExpValArray, 0, pubExpLenInt);
			keyAttributes.cka_public_exponent = pubExpValArray;

			byte[] subjectLenArray = new byte[4];
			System.arraycopy(keyAttrisArray, 32 + 32 + MSGModelConstant.KEY_ATTRS_BOOLEAN_LEN * 13 + 256 + 4 + modulusLenInt +32 + 4 + pubExpLenInt, subjectLenArray, 0, 4);
			String subjectLenStrTemp = new String(subjectLenArray);
			Integer subjectLenInt = Integer.parseInt(subjectLenStrTemp);

			byte[] subjectValArray = new byte[subjectLenInt];
			System.arraycopy(keyAttrisArray, 32 + 32 + MSGModelConstant.KEY_ATTRS_BOOLEAN_LEN * 13 + 256 + 4 + modulusLenInt +32 + 4 + pubExpLenInt + 4, subjectValArray, 0, subjectLenInt);
			keyAttributes.cka_subject = subjectValArray;

			byte[] startDateArray = new byte[8];
			System.arraycopy(keyAttrisArray, 32 + 32 + MSGModelConstant.KEY_ATTRS_BOOLEAN_LEN * 13 + 256 + 4 + modulusLenInt +32 + 4 + pubExpLenInt + 4 + subjectLenInt, startDateArray, 0, 8);
			keyAttributes.cka_startDate = startDateArray;

			byte[] endDateArray = new byte[8];
			System.arraycopy(keyAttrisArray, 32 + 32 + MSGModelConstant.KEY_ATTRS_BOOLEAN_LEN * 13 + 256 + 4 + modulusLenInt +32 + 4 + pubExpLenInt + 4 + subjectLenInt + 8, endDateArray, 0, 8);
			keyAttributes.cka_endDate = endDateArray;

		} else if (Long.valueOf(keyClassCheckStr) == 3) {

			keyAttributes = new KeyAttributes("PRIVATE");

			byte[] keyClassArray = new byte[32];
			System.arraycopy(keyAttrisArray, 0, keyClassArray, 0, 32);
			String keyClassStr = new String(keyClassArray);
			keyClassStr = keyClassStr.replaceAll("\\s+", "");
			keyAttributes.cka_keyClass = Long.valueOf(keyClassStr);

			byte[] keyTypeArray = new byte[32];
			System.arraycopy(keyAttrisArray, 32, keyTypeArray, 0, 32);
			String keyTypeStr = new String(keyTypeArray);
			keyTypeStr = keyTypeStr.replaceAll("\\s+", "");
			keyAttributes.cka_keyType = Long.valueOf(keyTypeStr);

			byte[] tokenArray = new byte[MSGModelConstant.KEY_ATTRS_BOOLEAN_LEN];
			System.arraycopy(keyAttrisArray, 32 + 32, tokenArray, 0, MSGModelConstant.KEY_ATTRS_BOOLEAN_LEN);
			String tokenStr = new String(tokenArray);
			keyAttributes.cka_token = (tokenStr.equals("1")) ? true : false;

			byte[] privateArray = new byte[MSGModelConstant.KEY_ATTRS_BOOLEAN_LEN];
			System.arraycopy(keyAttrisArray, 32 + 32 + MSGModelConstant.KEY_ATTRS_BOOLEAN_LEN, privateArray, 0, MSGModelConstant.KEY_ATTRS_BOOLEAN_LEN);
			String privateStr = new String(privateArray);
			keyAttributes.cka_private = (privateStr.equals("1")) ? true : false;

			byte[] sensitiveArray = new byte[MSGModelConstant.KEY_ATTRS_BOOLEAN_LEN];
			System.arraycopy(keyAttrisArray, 32 + 32 + MSGModelConstant.KEY_ATTRS_BOOLEAN_LEN * 2, sensitiveArray, 0, MSGModelConstant.KEY_ATTRS_BOOLEAN_LEN);
			String sensitiveStr = new String(sensitiveArray);
			keyAttributes.cka_sensitive = (sensitiveStr.equals("1")) ? true : false;

			byte[] extractableArray = new byte[MSGModelConstant.KEY_ATTRS_BOOLEAN_LEN];
			System.arraycopy(keyAttrisArray, 32 + 32 + MSGModelConstant.KEY_ATTRS_BOOLEAN_LEN * 3, extractableArray, 0, MSGModelConstant.KEY_ATTRS_BOOLEAN_LEN);
			String extractableStr = new String(extractableArray);
			keyAttributes.cka_extractable = (extractableStr.equals("1")) ? true : false;

			byte[] encryptArray = new byte[MSGModelConstant.KEY_ATTRS_BOOLEAN_LEN];
			System.arraycopy(keyAttrisArray, 32 + 32 + MSGModelConstant.KEY_ATTRS_BOOLEAN_LEN * 4, encryptArray, 0, MSGModelConstant.KEY_ATTRS_BOOLEAN_LEN);
			String encryptStr = new String(encryptArray);
			keyAttributes.cka_encrypt = (encryptStr.equals("1")) ? true : false;

			byte[] decryptArray = new byte[MSGModelConstant.KEY_ATTRS_BOOLEAN_LEN];
			System.arraycopy(keyAttrisArray, 32 + 32 + MSGModelConstant.KEY_ATTRS_BOOLEAN_LEN * 5, decryptArray, 0, MSGModelConstant.KEY_ATTRS_BOOLEAN_LEN);
			String decryptStr = new String(decryptArray);
			keyAttributes.cka_decrypt = (decryptStr.equals("1")) ? true : false;

			byte[] wrapArray = new byte[MSGModelConstant.KEY_ATTRS_BOOLEAN_LEN];
			System.arraycopy(keyAttrisArray, 32 + 32 + MSGModelConstant.KEY_ATTRS_BOOLEAN_LEN * 6, wrapArray, 0, MSGModelConstant.KEY_ATTRS_BOOLEAN_LEN);
			String wrapStr = new String(wrapArray);
			keyAttributes.cka_wrap = (wrapStr.equals("1")) ? true : false;

			byte[] unwrapArray = new byte[MSGModelConstant.KEY_ATTRS_BOOLEAN_LEN];
			System.arraycopy(keyAttrisArray, 32 + 32 + MSGModelConstant.KEY_ATTRS_BOOLEAN_LEN * 7, unwrapArray, 0, MSGModelConstant.KEY_ATTRS_BOOLEAN_LEN);
			String unwrapStr = new String(unwrapArray);
			keyAttributes.cka_unwrap = (unwrapStr.equals("1")) ? true : false;

			byte[] signArray = new byte[MSGModelConstant.KEY_ATTRS_BOOLEAN_LEN];
			System.arraycopy(keyAttrisArray, 32 + 32 + MSGModelConstant.KEY_ATTRS_BOOLEAN_LEN * 8, signArray, 0, MSGModelConstant.KEY_ATTRS_BOOLEAN_LEN);
			String signStr = new String(signArray);
			keyAttributes.cka_sign = (signStr.equals("1")) ? true : false;

			byte[] verifyArray = new byte[MSGModelConstant.KEY_ATTRS_BOOLEAN_LEN];
			System.arraycopy(keyAttrisArray, 32 + 32 + MSGModelConstant.KEY_ATTRS_BOOLEAN_LEN * 9, verifyArray, 0, MSGModelConstant.KEY_ATTRS_BOOLEAN_LEN);
			String verifyStr = new String(verifyArray);
			keyAttributes.cka_verify = (verifyStr.equals("1")) ? true : false;

			byte[] signRecoverArray = new byte[MSGModelConstant.KEY_ATTRS_BOOLEAN_LEN];
			System.arraycopy(keyAttrisArray, 32 + 32 + MSGModelConstant.KEY_ATTRS_BOOLEAN_LEN * 10, signRecoverArray, 0, MSGModelConstant.KEY_ATTRS_BOOLEAN_LEN);
			String signRecoverStr = new String(signRecoverArray);
			keyAttributes.cka_signRecover = (signRecoverStr.equals("1")) ? true : false;

			byte[] verifyRecoverArray = new byte[MSGModelConstant.KEY_ATTRS_BOOLEAN_LEN];
			System.arraycopy(keyAttrisArray, 32 + 32 + MSGModelConstant.KEY_ATTRS_BOOLEAN_LEN * 11, verifyRecoverArray, 0, MSGModelConstant.KEY_ATTRS_BOOLEAN_LEN);
			String verifyRecoverStr = new String(verifyRecoverArray);
			keyAttributes.cka_verifyRecover = (verifyRecoverStr.equals("1")) ? true : false;

			byte[] deriveArray = new byte[MSGModelConstant.KEY_ATTRS_BOOLEAN_LEN];
			System.arraycopy(keyAttrisArray, 32 + 32 + MSGModelConstant.KEY_ATTRS_BOOLEAN_LEN * 12, deriveArray, 0, MSGModelConstant.KEY_ATTRS_BOOLEAN_LEN);
			String deriveStr = new String(deriveArray);
			keyAttributes.cka_derive = (deriveStr.equals("1")) ? true : false;

			byte[] keyLabelValArray = new byte[256];
			System.arraycopy(keyAttrisArray, 32 + 32 + MSGModelConstant.KEY_ATTRS_BOOLEAN_LEN * 13, keyLabelValArray, 0, 256);
			String keyLabelValStrTemp = new String(keyLabelValArray);
			String keyLabelValStr = keyLabelValStrTemp.replaceAll("\\s+", "");
			keyAttributes.cka_label = keyLabelValStr.getBytes();
			
			
			
			
//add by shifei
			

			byte[] modulusLenArray = new byte[4];
			System.arraycopy(keyAttrisArray, 32 + 32 + MSGModelConstant.KEY_ATTRS_BOOLEAN_LEN * 13 + 256, modulusLenArray, 0, 4);
			String modulusLenStrTemp = new String(modulusLenArray);
			Integer modulusLenInt = Integer.parseInt(modulusLenStrTemp);

			byte[] modulusValArray = new byte[modulusLenInt];
			System.arraycopy(keyAttrisArray, 32 + 32 + MSGModelConstant.KEY_ATTRS_BOOLEAN_LEN * 13 + 256 + 4, modulusValArray, 0, modulusLenInt);
			keyAttributes.cka_modulus = modulusValArray;
			
			
			
			
			
			
			
			byte[] priExpLenArray = new byte[4];
			System.arraycopy(keyAttrisArray, 32 + 32 + MSGModelConstant.KEY_ATTRS_BOOLEAN_LEN * 13 + 256 + 4 + modulusLenInt, priExpLenArray, 0, 4);
			String priExpLenArrayStrTemp = new String(priExpLenArray);
			Integer priExpLenInt = Integer.parseInt(priExpLenArrayStrTemp);

			byte[] priExpValArray = new byte[priExpLenInt];
			System.arraycopy(keyAttrisArray, 32 + 32 + MSGModelConstant.KEY_ATTRS_BOOLEAN_LEN * 13 + 256 + 4 + modulusLenInt + 4, priExpValArray, 0, priExpLenInt);
			keyAttributes.cka_private_exponent = priExpValArray;
			
			
			
			
			
			

			byte[] subjectLenArray = new byte[4];
			System.arraycopy(keyAttrisArray, 32 + 32 + MSGModelConstant.KEY_ATTRS_BOOLEAN_LEN * 13 + 256 + 4 + modulusLenInt +4 +priExpLenInt, subjectLenArray, 0, 4);
			String subjectLenStrTemp = new String(subjectLenArray);
			Integer subjectLenInt = Integer.parseInt(subjectLenStrTemp);

			byte[] subjectValArray = new byte[subjectLenInt];
			System.arraycopy(keyAttrisArray, 32 + 32 + MSGModelConstant.KEY_ATTRS_BOOLEAN_LEN * 13 + 256 + 4 + modulusLenInt+4 +priExpLenInt+ 4, subjectValArray, 0, subjectLenInt);
			keyAttributes.cka_subject = subjectValArray;

			byte[] startDateArray = new byte[8];
			System.arraycopy(keyAttrisArray, 32 + 32 + MSGModelConstant.KEY_ATTRS_BOOLEAN_LEN * 13 + 256 + 4 + modulusLenInt+4 +priExpLenInt+ 4 + subjectLenInt, startDateArray, 0, 8);
			keyAttributes.cka_startDate = startDateArray;

			byte[] endDateArray = new byte[8];
			System.arraycopy(keyAttrisArray, 32 + 32 + MSGModelConstant.KEY_ATTRS_BOOLEAN_LEN * 13 + 256 + 4 + modulusLenInt+4 +priExpLenInt+ 4 + subjectLenInt + 8, endDateArray, 0, 8);
			keyAttributes.cka_endDate = endDateArray;
			
			
			
			
			byte[] cka_prime_1LenArray = new byte[4];
			System.arraycopy(keyAttrisArray, 32 + 32 + MSGModelConstant.KEY_ATTRS_BOOLEAN_LEN * 13 + 256 + 4 + modulusLenInt+4 +priExpLenInt+ 4 + subjectLenInt + 8+8, cka_prime_1LenArray, 0, 4);
			String cka_prime_1LenArrayStrTemp = new String(cka_prime_1LenArray);
			Integer cka_prime_1LenInt = Integer.parseInt(cka_prime_1LenArrayStrTemp);

			byte[] cka_prime_1ValArray = new byte[cka_prime_1LenInt];
			System.arraycopy(keyAttrisArray, 32 + 32 + MSGModelConstant.KEY_ATTRS_BOOLEAN_LEN * 13 + 256 + 4 + modulusLenInt + 4+priExpLenInt+ 4 + subjectLenInt + 8+8+4, cka_prime_1ValArray, 0, cka_prime_1LenInt);
			keyAttributes.cka_prime_1 = cka_prime_1ValArray;
			
			
			byte[] cka_prime_2LenArray = new byte[4];
			System.arraycopy(keyAttrisArray, 32 + 32 + MSGModelConstant.KEY_ATTRS_BOOLEAN_LEN * 13 + 256 + 4 + modulusLenInt+4 +priExpLenInt+ 4 + subjectLenInt + 8+8+4+cka_prime_1LenInt, cka_prime_2LenArray, 0, 4);
			String cka_prime_2LenArrayStrTemp = new String(cka_prime_2LenArray);
			Integer cka_prime_2LenInt = Integer.parseInt(cka_prime_2LenArrayStrTemp);

			byte[] cka_prime_2ValArray = new byte[cka_prime_2LenInt];
			System.arraycopy(keyAttrisArray, 32 + 32 + MSGModelConstant.KEY_ATTRS_BOOLEAN_LEN * 13 + 256 + 4 + modulusLenInt + 4+priExpLenInt+ 4 + subjectLenInt + 8+8+4+cka_prime_1LenInt+4, cka_prime_2ValArray, 0, cka_prime_2LenInt);
			keyAttributes.cka_prime_2 = cka_prime_2ValArray;
			
			
			byte[] cka_exponent_1LenArray = new byte[4];
			System.arraycopy(keyAttrisArray, 32 + 32 + MSGModelConstant.KEY_ATTRS_BOOLEAN_LEN * 13 + 256 + 4 + modulusLenInt+4 +priExpLenInt+ 4 + subjectLenInt + 8+8+4+cka_prime_1LenInt+4+cka_prime_2LenInt, cka_exponent_1LenArray, 0, 4);
			String cka_exponent_1LenArrayStrTemp = new String(cka_exponent_1LenArray);
			Integer cka_exponent_1LenInt = Integer.parseInt(cka_exponent_1LenArrayStrTemp);

			byte[] cka_exponent_1ValArray = new byte[cka_exponent_1LenInt];
			System.arraycopy(keyAttrisArray, 32 + 32 + MSGModelConstant.KEY_ATTRS_BOOLEAN_LEN * 13 + 256 + 4 + modulusLenInt + 4+priExpLenInt+ 4 + subjectLenInt + 8+8+4+cka_prime_1LenInt+4+cka_prime_2LenInt+4, cka_exponent_1ValArray, 0, cka_exponent_1LenInt);
			keyAttributes.cka_exponent_1 = cka_exponent_1ValArray;
			
			byte[] cka_exponent_2LenArray = new byte[4];
			System.arraycopy(keyAttrisArray, 32 + 32 + MSGModelConstant.KEY_ATTRS_BOOLEAN_LEN * 13 + 256 + 4 + modulusLenInt+4 +priExpLenInt+ 4 + subjectLenInt + 8+8+4+cka_prime_1LenInt+4+cka_prime_2LenInt+4+cka_exponent_1LenInt, cka_exponent_2LenArray, 0, 4);
			String cka_exponent_2LenArrayStrTemp = new String(cka_exponent_2LenArray);
			Integer cka_exponent_2LenInt = Integer.parseInt(cka_exponent_2LenArrayStrTemp);

			byte[] cka_exponent_2ValArray = new byte[cka_exponent_2LenInt];
			System.arraycopy(keyAttrisArray, 32 + 32 + MSGModelConstant.KEY_ATTRS_BOOLEAN_LEN * 13 + 256 + 4 + modulusLenInt + 4+priExpLenInt+ 4 + subjectLenInt + 8+8+4+cka_prime_1LenInt+4+cka_prime_2LenInt+4+cka_exponent_1LenInt+4, cka_exponent_2ValArray, 0, cka_exponent_2LenInt);
			keyAttributes.cka_exponent_2 = cka_exponent_2ValArray;
			
			
			byte[] cka_coefficientLenArray = new byte[4];
			System.arraycopy(keyAttrisArray, 32 + 32 + MSGModelConstant.KEY_ATTRS_BOOLEAN_LEN * 13 + 256 + 4 + modulusLenInt+4 +priExpLenInt+ 4 + subjectLenInt + 8+8+4+cka_prime_1LenInt+4+cka_prime_2LenInt+4+cka_exponent_1LenInt+4+cka_exponent_2LenInt, cka_coefficientLenArray, 0, 4);
			String cka_coefficientLenArrayStrTemp = new String(cka_coefficientLenArray);
			Integer cka_coefficientLenInt = Integer.parseInt(cka_coefficientLenArrayStrTemp);

			byte[] cka_coefficientValArray = new byte[cka_coefficientLenInt];
			System.arraycopy(keyAttrisArray, 32 + 32 + MSGModelConstant.KEY_ATTRS_BOOLEAN_LEN * 13 + 256 + 4 + modulusLenInt + 4+priExpLenInt+ 4 + subjectLenInt + 8+8+4+cka_prime_1LenInt+4+cka_prime_2LenInt+4+cka_exponent_1LenInt+4+cka_exponent_2LenInt+4, cka_coefficientValArray, 0, cka_coefficientLenInt);
			keyAttributes.cka_coefficient = cka_coefficientValArray;
			
			
			
			
		}

	}

	public KeyAttributes getKeyAttributes() {
		return keyAttributes;
	}

	public void setKeyAttributes(KeyAttributes keyAttributes) {
		this.keyAttributes = keyAttributes;
	}

}
