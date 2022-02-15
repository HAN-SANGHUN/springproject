package com.kona.kms.crypto.tcp.keyAttrisModel;

import sun.security.pkcs11.wrapper.PKCS11Constants;

import com.kona.kms.crypto.KeyAttributes;
import com.kona.kms.crypto.tcp.utility.MSGModelConstant;

public class GetKeyAttrisModel {
	private byte[] keyAttrisArray;
	private int keyAttrisLen;

	// private byte[] keyClassLen;
	private byte[] keyClassVal;
	// private byte[] keyTypeLen;
	private byte[] keyTypeVal;
	private byte[] token;
	private byte[] privateAcc;
	private byte[] sensitive;
	private byte[] extractable;
	private byte[] encrypt;
	private byte[] decrypt;
	private byte[] wrap;
	private byte[] unwrap;
	private byte[] sign;
	private byte[] verify;
	private byte[] signRecover;
	private byte[] verifyRecover;
	private byte[] derive;
	// private byte[] labelLen;
	private byte[] keyLabelVal;
	private byte[] startData;
	private byte[] endData;
	// private byte[] keyValLenLen;
	private byte[] keyValLenVal;

	private byte[] modulusLen;
	private byte[] modulus;
	private byte[] modulusBitsVal;
	private byte[] pubExpLen;
	private byte[] pubExpVal;
	private byte[] subjectLen;
	private byte[] subjectVal;
	
	private byte[] priExpLen;
	private byte[] priExpVal;
	
	private byte[] cka_prime_1Len;
	private byte[] cka_prime_1; // Prime p (BigInteger)
	private byte[] cka_prime_2Len;
	private byte[] cka_prime_2; // Prime q (BigInteger)
	private byte[] cka_exponent_1Len;
	private byte[] cka_exponent_1; // Private exponent d modulo p-1
											// (BigInteger)
	private byte[] cka_exponent_2Len;
	private byte[] cka_exponent_2; // Private exponent d modulo q-1
											// (BigInteger)
	private byte[] cka_coefficientLen;
	private byte[] cka_coefficient; // CRT coefficient q-1 mod p
	

	public GetKeyAttrisModel(KeyAttributes keyAttris) {
		parseKeyAttris(keyAttris);
	}

	public void parseKeyAttris(KeyAttributes keyAttris) {

		if (keyAttris.cka_keyClass == 4) {
			// keyAttris = new KeyAttributes("SECRET");

			// keyClassLen = new byte[4];
			keyClassVal = new byte[32];
			// keyTypeLen = new byte[4];
			keyTypeVal = new byte[32];
			token = new byte[1];
			privateAcc = new byte[1];
			sensitive = new byte[1];
			extractable = new byte[1];
			encrypt = new byte[1];
			decrypt = new byte[1];
			wrap = new byte[1];
			unwrap = new byte[1];
			sign = new byte[1];
			verify = new byte[1];
			signRecover = new byte[1];
			verifyRecover = new byte[1];
			derive = new byte[1];
			// labelLen = new byte[4];
			keyLabelVal = new byte[256];
			startData = new byte[8];
			endData = new byte[8];
			// keyValLenLen = new byte[4];
			keyValLenVal = new byte[32];

			int loop;

			String keyClassStr = String.valueOf(keyAttris.cka_keyClass);
			loop = keyClassStr.length();
			for (int i = 0; i < 32 - loop; i++) {
				keyClassStr = keyClassStr + " ";
			}
			keyClassVal = keyClassStr.getBytes();

			String keyTypeStr = String.valueOf(keyAttris.cka_keyType);
			loop = keyTypeStr.length();
			for (int i = 0; i < 32 - loop; i++) {
				keyTypeStr = keyTypeStr + " ";
			}
			keyTypeVal = keyTypeStr.getBytes();

			String tokenStr = (keyAttris.cka_encrypt == true) ? "1" : "0";
			token = tokenStr.getBytes();

			String privateAccStr = (keyAttris.cka_private == true) ? "1" : "0";
			privateAcc = privateAccStr.getBytes();

			String sensitiveStr = (keyAttris.cka_sensitive == true) ? "1" : "0";
			sensitive = sensitiveStr.getBytes();

			String extractableStr = (keyAttris.cka_extractable == true) ? "1" : "0";
			extractable = extractableStr.getBytes();

			String encryptStr = (keyAttris.cka_encrypt == true) ? "1" : "0";
			encrypt = encryptStr.getBytes();

			String decryptStr = (keyAttris.cka_decrypt == true) ? "1" : "0";
			decrypt = decryptStr.getBytes();

			String wrapStr = (keyAttris.cka_wrap == true) ? "1" : "0";
			wrap = wrapStr.getBytes();

			String unwrapStr = (keyAttris.cka_unwrap == true) ? "1" : "0";
			unwrap = unwrapStr.getBytes();

			String signStr = (keyAttris.cka_sign == true) ? "1" : "0";
			sign = signStr.getBytes();

			String verifyStr = (keyAttris.cka_verify == true) ? "1" : "0";
			verify = verifyStr.getBytes();

			String signRecoverStr = (keyAttris.cka_signRecover == true) ? "1" : "0";
			signRecover = signRecoverStr.getBytes();

			String verifyRecoverStr = (keyAttris.cka_verifyRecover == true) ? "1" : "0";
			verifyRecover = verifyRecoverStr.getBytes();

			String deriveStr = (keyAttris.cka_derive == true) ? "1" : "0";
			derive = deriveStr.getBytes();

			String keyLabelValStr = new String(keyAttris.cka_label);
			loop = keyLabelValStr.length();
			for (int i = 0; i < 256 - loop; i++) {
				keyLabelValStr = keyLabelValStr + " ";
			}
			keyLabelVal = keyLabelValStr.getBytes();

			startData = keyAttris.cka_startDate;

			endData = keyAttris.cka_endDate;

			String keyValLenValStr = String.valueOf(keyAttris.cka_value_len);
			loop = keyValLenValStr.length();
			for (int i = 0; i < 32 - loop; i++) {
				keyValLenValStr = keyValLenValStr + " ";
			}
			keyValLenVal = keyValLenValStr.getBytes();

			keyAttrisLen = 32 + 32 + 13 + 256 + 8 + 8 + 32;

			keyAttrisArray = new byte[keyAttrisLen];

			System.arraycopy(keyClassVal, 0, keyAttrisArray, 0, 32);
			System.arraycopy(keyTypeVal, 0, keyAttrisArray, 32, 32);
			System.arraycopy(token, 0, keyAttrisArray, 32 + 32, 1);
			System.arraycopy(privateAcc, 0, keyAttrisArray, 32 + 32 + 1, 1);
			System.arraycopy(sensitive, 0, keyAttrisArray, 32 + 32 + 1 + 1, 1);
			System.arraycopy(extractable, 0, keyAttrisArray, 32 + 32 + 1 + 1 + 1, 1);
			System.arraycopy(encrypt, 0, keyAttrisArray, 32 + 32 + 1 + 1 + 1 + 1, 1);
			System.arraycopy(decrypt, 0, keyAttrisArray, 32 + 32 + 1 + 1 + 1 + 1 + 1, 1);
			System.arraycopy(wrap, 0, keyAttrisArray, 32 + 32 + 1 + 1 + 1 + 1 + 1 + 1, 1);
			System.arraycopy(unwrap, 0, keyAttrisArray, 32 + 32 + 1 + 1 + 1 + 1 + 1 + 1 + 1, 1);
			System.arraycopy(sign, 0, keyAttrisArray, 32 + 32 + 1 + 1 + 1 + 1 + 1 + 1 + 1 + 1, 1);
			System.arraycopy(verify, 0, keyAttrisArray, 32 + 32 + 1 + 1 + 1 + 1 + 1 + 1 + 1 + 1 + 1, 1);
			System.arraycopy(signRecover, 0, keyAttrisArray, 32 + 32 + 1 + 1 + 1 + 1 + 1 + 1 + 1 + 1 + 1 + 1, 1);
			System.arraycopy(verifyRecover, 0, keyAttrisArray, 32 + 32 + 1 + 1 + 1 + 1 + 1 + 1 + 1 + 1 + 1 + 1 + 1, 1);
			System.arraycopy(derive, 0, keyAttrisArray, 32 + 32 + 1 + 1 + 1 + 1 + 1 + 1 + 1 + 1 + 1 + 1 + 1 + 1, 1);
			System.arraycopy(keyLabelVal, 0, keyAttrisArray, 32 + 32 + 1 + 1 + 1 + 1 + 1 + 1 + 1 + 1 + 1 + 1 + 1 + 1 + 1, 256);
			System.arraycopy(startData, 0, keyAttrisArray, 32 + 32 + 1 + 1 + 1 + 1 + 1 + 1 + 1 + 1 + 1 + 1 + 1 + 1 + 1 + 256, 8);
			System.arraycopy(endData, 0, keyAttrisArray, 32 + 32 + 1 + 1 + 1 + 1 + 1 + 1 + 1 + 1 + 1 + 1 + 1 + 1 + 1 + 256 + 8, 8);
			System.arraycopy(keyValLenVal, 0, keyAttrisArray, 32 + 32 + 1 + 1 + 1 + 1 + 1 + 1 + 1 + 1 + 1 + 1 + 1 + 1 + 1 + 256 + 8 + 8, 32);

		} else if (keyAttris.cka_keyClass == 2) {
			keyClassVal = new byte[32];
			keyTypeVal = new byte[32];
			token = new byte[1];
			privateAcc = new byte[1];
			sensitive = new byte[1];
			extractable = new byte[1];
			encrypt = new byte[1];
			decrypt = new byte[1];
			wrap = new byte[1];
			unwrap = new byte[1];
			sign = new byte[1];
			verify = new byte[1];
			signRecover = new byte[1];
			verifyRecover = new byte[1];
			derive = new byte[1];
			keyLabelVal = new byte[256];

			modulusLen = new byte[4];
			
			modulusBitsVal = new byte[32];

			pubExpLen = new byte[4];
			subjectLen = new byte[4];

			// String modulusLenStr = new String(modulusLen);
			// modulusVal= new byte[Integer.parseInt(modulusLenStr)];

			startData = new byte[8];
			endData = new byte[8];

			int loop;

			String keyClassStr = String.valueOf(keyAttris.cka_keyClass);
			loop = keyClassStr.length();
			for (int i = 0; i < 32 - loop; i++) {
				keyClassStr = keyClassStr + " ";
			}
			keyClassVal = keyClassStr.getBytes();

			String keyTypeStr = String.valueOf(keyAttris.cka_keyType);
			loop = keyTypeStr.length();
			for (int i = 0; i < 32 - loop; i++) {
				keyTypeStr = keyTypeStr + " ";
			}
			keyTypeVal = keyTypeStr.getBytes();

			String tokenStr = (keyAttris.cka_encrypt == true) ? "1" : "0";
			token = tokenStr.getBytes();

			String privateAccStr = (keyAttris.cka_private == true) ? "1" : "0";
			privateAcc = privateAccStr.getBytes();

			String sensitiveStr = (keyAttris.cka_sensitive == true) ? "1" : "0";
			sensitive = sensitiveStr.getBytes();

			String extractableStr = (keyAttris.cka_extractable == true) ? "1" : "0";
			extractable = extractableStr.getBytes();

			String encryptStr = (keyAttris.cka_encrypt == true) ? "1" : "0";
			encrypt = encryptStr.getBytes();

			String decryptStr = (keyAttris.cka_decrypt == true) ? "1" : "0";
			decrypt = decryptStr.getBytes();

			String wrapStr = (keyAttris.cka_wrap == true) ? "1" : "0";
			wrap = wrapStr.getBytes();

			String unwrapStr = (keyAttris.cka_unwrap == true) ? "1" : "0";
			unwrap = unwrapStr.getBytes();

			String signStr = (keyAttris.cka_sign == true) ? "1" : "0";
			sign = signStr.getBytes();

			String verifyStr = (keyAttris.cka_verify == true) ? "1" : "0";
			verify = verifyStr.getBytes();

			String signRecoverStr = (keyAttris.cka_signRecover == true) ? "1" : "0";
			signRecover = signRecoverStr.getBytes();

			String verifyRecoverStr = (keyAttris.cka_verifyRecover == true) ? "1" : "0";
			verifyRecover = verifyRecoverStr.getBytes();

			String deriveStr = (keyAttris.cka_derive == true) ? "1" : "0";
			derive = deriveStr.getBytes();

			if (keyAttris.cka_label == null) {
				keyLabelVal = null;
			} else {
				String keyLabelValStr = new String(keyAttris.cka_label);
				loop = keyLabelValStr.length();
				for (int i = 0; i < 256 - loop; i++) {
					keyLabelValStr = keyLabelValStr + " ";
				}
				keyLabelVal = keyLabelValStr.getBytes();
			}

			
			
			// add by shifei 2014-04-18
			Integer modulusLenInt;
			if(keyAttris.cka_modulus == null){
				modulus = null;
				modulusLen = "0000".getBytes();
				modulusLenInt = 0;
				
			} else {
				modulus = keyAttris.cka_modulus;
				modulusLenInt = modulus.length;
				String modulusLenStr = modulusLenInt.toString();
				loop = modulusLenStr.length();
				for (int i = 0; i < 4 - loop; i++) {
					modulusLenStr = "0" + modulusLenStr;
				}
				modulusLen = modulusLenStr.getBytes();
			}
			
			
			
			String modulusBitsValStr = String.valueOf(keyAttris.cka_modulus_bits);
			loop = modulusBitsValStr.length();
			for (int i = 0; i < 32 - loop; i++) {
				modulusBitsValStr = "0" + modulusBitsValStr;
			}
			modulusBitsVal = modulusBitsValStr.getBytes();
			
			

			Integer pubExpLenInt;
			if (keyAttris.cka_public_exponent == null){
				pubExpVal = null;
				pubExpLen = "0000".getBytes();
				pubExpLenInt = 0;
				
			} else {
				pubExpVal = keyAttris.cka_public_exponent;

				pubExpLenInt = pubExpVal.length; // ******************* here
															// might be problem.
															// hexStringToByteArray*******************
				String pubExpLenStr = pubExpLenInt.toString();
				loop = pubExpLenStr.length();
				for (int i = 0; i < 4 - loop; i++) {
					pubExpLenStr = "0" + pubExpLenStr;
				}
				pubExpLen = pubExpLenStr.getBytes();
			}
			
			Integer subjectLenInt;
			if (keyAttris.cka_subject == null){
				subjectVal = null;
				subjectLen = "0000".getBytes();
				subjectLenInt = 0;
			} else{
				subjectVal = keyAttris.cka_subject;

				subjectLenInt = subjectVal.length; // *******************
															// here might be
															// problem.
															// hexStringToByteArray*******************
				String subjectLenStr = subjectLenInt.toString();
				loop = subjectLenStr.length();
				for (int i = 0; i < 4 - loop; i++) {
					subjectLenStr = "0" + subjectLenStr;
				}
				subjectLen = subjectLenStr.getBytes();
			}
			

			startData = keyAttris.cka_startDate;

			endData = keyAttris.cka_endDate;

			keyAttrisLen = 32 + 32 + 13 + 256 + 32 + 4 + pubExpLenInt + 4 + subjectLenInt + 8 + 8
					+ 4 + modulusLenInt;

			keyAttrisArray = new byte[keyAttrisLen];

			System.arraycopy(keyClassVal, 0, keyAttrisArray, 0, 32);
			System.arraycopy(keyTypeVal, 0, keyAttrisArray, 32, 32);
			System.arraycopy(token, 0, keyAttrisArray, 32 + 32, 1);
			System.arraycopy(privateAcc, 0, keyAttrisArray, 32 + 32 + 1, 1);
			System.arraycopy(sensitive, 0, keyAttrisArray, 32 + 32 + 1 + 1, 1);
			System.arraycopy(extractable, 0, keyAttrisArray, 32 + 32 + 1 + 1 + 1, 1);
			System.arraycopy(encrypt, 0, keyAttrisArray, 32 + 32 + 1 + 1 + 1 + 1, 1);
			System.arraycopy(decrypt, 0, keyAttrisArray, 32 + 32 + 1 + 1 + 1 + 1 + 1, 1);
			System.arraycopy(wrap, 0, keyAttrisArray, 32 + 32 + 1 + 1 + 1 + 1 + 1 + 1, 1);
			System.arraycopy(unwrap, 0, keyAttrisArray, 32 + 32 + 1 + 1 + 1 + 1 + 1 + 1 + 1, 1);
			System.arraycopy(sign, 0, keyAttrisArray, 32 + 32 + 1 + 1 + 1 + 1 + 1 + 1 + 1 + 1, 1);
			System.arraycopy(verify, 0, keyAttrisArray, 32 + 32 + 1 + 1 + 1 + 1 + 1 + 1 + 1 + 1 + 1, 1);
			System.arraycopy(signRecover, 0, keyAttrisArray, 32 + 32 + 1 + 1 + 1 + 1 + 1 + 1 + 1 + 1 + 1 + 1, 1);
			System.arraycopy(verifyRecover, 0, keyAttrisArray, 32 + 32 + 1 + 1 + 1 + 1 + 1 + 1 + 1 + 1 + 1 + 1 + 1, 1);
			System.arraycopy(derive, 0, keyAttrisArray, 32 + 32 + 1 + 1 + 1 + 1 + 1 + 1 + 1 + 1 + 1 + 1 + 1 + 1, 1);
			System.arraycopy(keyLabelVal, 0, keyAttrisArray, 32 + 32 + 1 + 1 + 1 + 1 + 1 + 1 + 1 + 1 + 1 + 1 + 1 + 1 + 1, 256);
			
			System.arraycopy(modulusLen, 0, keyAttrisArray, 32 + 32 + 1 + 1 + 1 + 1 + 1 + 1 + 1 + 1 + 1 + 1 + 1 + 1 + 1+256, 4);
			if (modulus != null){
				System.arraycopy(modulus, 0, keyAttrisArray, 32 + 32 + 1 + 1 + 1 + 1 + 1 + 1 + 1 + 1 + 1 + 1 + 1 + 1 + 1+256+4, modulusLenInt);	
			}
		
			
			System.arraycopy(modulusBitsVal, 0, keyAttrisArray, 32 + 32 + 1 + 1 + 1 + 1 + 1 + 1 + 1 + 1 + 1 + 1 + 1 + 1 + 1 + 256 +4+modulusLenInt, 32);
			
			
			System.arraycopy(pubExpLen, 0, keyAttrisArray, 32 + 32 + 1 + 1 + 1 + 1 + 1 + 1 + 1 + 1 + 1 + 1 + 1 + 1 + 1 + 256 +4+modulusLenInt+ 32, 4);
			if (pubExpVal != null){
				System.arraycopy(pubExpVal, 0, keyAttrisArray, 32 + 32 + 1 + 1 + 1 + 1 + 1 + 1 + 1 + 1 + 1 + 1 + 1 + 1 + 1 + 256 +4+modulusLenInt+ 32 + 4, pubExpLenInt);
			}
			
			System.arraycopy(subjectLen, 0, keyAttrisArray, 32 + 32 + 1 + 1 + 1 + 1 + 1 + 1 + 1 + 1 + 1 + 1 + 1 + 1 + 1 + 256 +4+modulusLenInt+ 32 + 4 + pubExpLenInt, 4);
			if (subjectVal != null){
				System.arraycopy(subjectVal, 0, keyAttrisArray, 32 + 32 + 1 + 1 + 1 + 1 + 1 + 1 + 1 + 1 + 1 + 1 + 1 + 1 + 1 + 256 +4+modulusLenInt+ 32 + 4 + pubExpLenInt + 4, subjectLenInt);				
			}

			System.arraycopy(startData, 0, keyAttrisArray, 32 + 32 + 1 + 1 + 1 + 1 + 1 + 1 + 1 + 1 + 1 + 1 + 1 + 1 + 1 + 256 +4+modulusLenInt+ 32 + 4 + pubExpLenInt + 4 + subjectLenInt, 8);
			System.arraycopy(endData, 0, keyAttrisArray, 32 + 32 + 1 + 1 + 1 + 1 + 1 + 1 + 1 + 1 + 1 + 1 + 1 + 1 + 1 + 256 +4+modulusLenInt+ 32 + 4 + pubExpLenInt + 4 + subjectLenInt + 8, 8);

		} else if (keyAttris.cka_keyClass == 3) {
			keyClassVal = new byte[32];
			keyTypeVal = new byte[32];
			token = new byte[1];
			privateAcc = new byte[1];
			sensitive = new byte[1];
			extractable = new byte[1];
			encrypt = new byte[1];
			decrypt = new byte[1];
			wrap = new byte[1];
			unwrap = new byte[1];
			sign = new byte[1];
			verify = new byte[1];
			signRecover = new byte[1];
			verifyRecover = new byte[1];
			derive = new byte[1];
			keyLabelVal = new byte[256];
			
			cka_prime_1Len = new byte[4];
			cka_prime_2Len = new byte[4];
			cka_exponent_1Len = new byte[4];
			cka_exponent_2Len = new byte[4];
			cka_coefficientLen = new byte[4];

			
			modulusLen = new byte[4];
			
			priExpLen = new byte[4];
			
			subjectLen = new byte[4];

			startData = new byte[8];
			endData = new byte[8];

			int loop;

			String keyClassStr = String.valueOf(keyAttris.cka_keyClass);
			loop = keyClassStr.length();
			for (int i = 0; i < 32 - loop; i++) {
				keyClassStr = keyClassStr + " ";
			}
			keyClassVal = keyClassStr.getBytes();

			String keyTypeStr = String.valueOf(keyAttris.cka_keyType);
			loop = keyTypeStr.length();
			for (int i = 0; i < 32 - loop; i++) {
				keyTypeStr = keyTypeStr + " ";
			}
			keyTypeVal = keyTypeStr.getBytes();

			String tokenStr = (keyAttris.cka_encrypt == true) ? "1" : "0";
			token = tokenStr.getBytes();

			String privateAccStr = (keyAttris.cka_private == true) ? "1" : "0";
			privateAcc = privateAccStr.getBytes();

			String sensitiveStr = (keyAttris.cka_sensitive == true) ? "1" : "0";
			sensitive = sensitiveStr.getBytes();

			String extractableStr = (keyAttris.cka_extractable == true) ? "1" : "0";
			extractable = extractableStr.getBytes();

			String encryptStr = (keyAttris.cka_encrypt == true) ? "1" : "0";
			encrypt = encryptStr.getBytes();

			String decryptStr = (keyAttris.cka_decrypt == true) ? "1" : "0";
			decrypt = decryptStr.getBytes();

			String wrapStr = (keyAttris.cka_wrap == true) ? "1" : "0";
			wrap = wrapStr.getBytes();

			String unwrapStr = (keyAttris.cka_unwrap == true) ? "1" : "0";
			unwrap = unwrapStr.getBytes();

			String signStr = (keyAttris.cka_sign == true) ? "1" : "0";
			sign = signStr.getBytes();

			String verifyStr = (keyAttris.cka_verify == true) ? "1" : "0";
			verify = verifyStr.getBytes();

			String signRecoverStr = (keyAttris.cka_signRecover == true) ? "1" : "0";
			signRecover = signRecoverStr.getBytes();

			String verifyRecoverStr = (keyAttris.cka_verifyRecover == true) ? "1" : "0";
			verifyRecover = verifyRecoverStr.getBytes();

			String deriveStr = (keyAttris.cka_derive == true) ? "1" : "0";
			derive = deriveStr.getBytes();

			if (keyAttris.cka_label == null) {
				keyLabelVal = null;
			} else {
				String keyLabelValStr = new String(keyAttris.cka_label);
				loop = keyLabelValStr.length();
				for (int i = 0; i < 256 - loop; i++) {
					keyLabelValStr = keyLabelValStr + " ";
				}
				keyLabelVal = keyLabelValStr.getBytes();
			}

			
			// add by shifei 2014-04-18
			Integer modulusLenInt;
			if (keyAttris.cka_modulus == null){
				modulus = null;
				modulusLen = "0000".getBytes();
				modulusLenInt = 0;
			} else {
				modulus = keyAttris.cka_modulus;
				modulusLenInt = modulus.length;
				String modulusLenStr = modulusLenInt.toString();
				loop = modulusLenStr.length();
				for (int i = 0; i < 4 - loop; i++) {
					modulusLenStr = "0" + modulusLenStr;
				}
				modulusLen = modulusLenStr.getBytes();
			}
			
						
			Integer priExpLenInt;
			if ( keyAttris.cka_private_exponent == null){
				priExpVal = null;
				priExpLen = "0000".getBytes();
				priExpLenInt = 0;
				
			}else {
				priExpVal = keyAttris.cka_private_exponent;

				priExpLenInt = priExpVal.length; // ******************* here
															// might be problem.
															// hexStringToByteArray*******************
				String priExpLenStr = priExpLenInt.toString();
				loop = priExpLenStr.length();
				for (int i = 0; i < 4 - loop; i++) {
					priExpLenStr = "0" + priExpLenStr;
				}
				priExpLen = priExpLenStr.getBytes();
			}
			
			
			Integer subjectLenInt;
			if ( keyAttris.cka_subject ==null){
				subjectVal = null;
				subjectLen = "0000".getBytes();
				subjectLenInt = 0;
			} else {
				subjectVal = keyAttris.cka_subject;

				 subjectLenInt = subjectVal.length; // *******************
															// here might be
															// problem.
															// hexStringToByteArray?*******************
				String subjectLenStr = subjectLenInt.toString();
				loop = subjectLenStr.length();
				for (int i = 0; i < 4 - loop; i++) {
					subjectLenStr = "0" + subjectLenStr;
				}
				subjectLen = subjectLenStr.getBytes();
			}
			

			startData = keyAttris.cka_startDate;

			endData = keyAttris.cka_endDate;
			
			
			Integer cka_prime_1LenInt;
			if (keyAttris.cka_prime_1 == null){
				cka_prime_1 = null;
				cka_prime_1Len = "0000".getBytes();
				cka_prime_1LenInt = 0;
				
			} else {
				cka_prime_1 = keyAttris.cka_prime_1;
				cka_prime_1LenInt = cka_prime_1.length;
				String cka_prime_1LenStr = cka_prime_1LenInt.toString();
				loop = cka_prime_1LenStr.length();
				for (int i = 0; i < 4 - loop; i++) {
					cka_prime_1LenStr = "0" + cka_prime_1LenStr;
				}
				cka_prime_1Len = cka_prime_1LenStr.getBytes();
			}
			
			
			Integer cka_prime_2LenInt;
			if (keyAttris.cka_prime_2 == null){
				cka_prime_2 = null;
				cka_prime_2Len = "0000".getBytes();
				cka_prime_2LenInt = 0;
			} else {
				cka_prime_2 = keyAttris.cka_prime_2;
				cka_prime_2LenInt = cka_prime_2.length;
				String cka_prime_2LenStr = cka_prime_2LenInt.toString();
				loop = cka_prime_2LenStr.length();
				for (int i = 0; i < 4 - loop; i++) {
					cka_prime_2LenStr = "0" + cka_prime_2LenStr;
				}
				cka_prime_2Len = cka_prime_2LenStr.getBytes();
			}
			
			
			Integer cka_exponent_1LenInt;
			if (keyAttris.cka_exponent_1 == null){
				cka_exponent_1 = null;
				cka_exponent_1Len = "0000".getBytes();
				cka_exponent_1LenInt = 0;
			} else {
				cka_exponent_1 = keyAttris.cka_exponent_1;
				cka_exponent_1LenInt = cka_exponent_1.length;
				String cka_exponent_1LenStr = cka_exponent_1LenInt.toString();
				loop = cka_exponent_1LenStr.length();
				for (int i = 0; i < 4 - loop; i++) {
					cka_exponent_1LenStr = "0" + cka_exponent_1LenStr;
				}
				cka_exponent_1Len = cka_exponent_1LenStr.getBytes();
			}
			
			
			Integer cka_exponent_2LenInt;
			if (keyAttris.cka_exponent_2 == null){
				cka_exponent_2 = null;
				cka_exponent_2Len = "0000".getBytes();
				cka_exponent_2LenInt = 0;
			} else {
				cka_exponent_2 = keyAttris.cka_exponent_2;
				cka_exponent_2LenInt = cka_exponent_2.length;
				String cka_exponent_2LenStr = cka_exponent_2LenInt.toString();
				loop = cka_exponent_2LenStr.length();
				for (int i = 0; i < 4 - loop; i++) {
					cka_exponent_2LenStr = "0" + cka_exponent_2LenStr;
				}
				cka_exponent_2Len = cka_exponent_2LenStr.getBytes();
			}
			
			
			
			Integer cka_coefficientLenInt;
			if (keyAttris.cka_coefficient  == null){
				cka_coefficient = null;
				cka_coefficientLen = "0000".getBytes();
				cka_coefficientLenInt = 0;
			} else {
				cka_coefficient = keyAttris.cka_coefficient;
				cka_coefficientLenInt = cka_coefficient.length;
				String cka_coefficientLenStr = cka_coefficientLenInt.toString();
				loop = cka_coefficientLenStr.length();
				for (int i = 0; i < 4 - loop; i++) {
					cka_coefficientLenStr = "0" + cka_coefficientLenStr;
				}
				cka_coefficientLen = cka_coefficientLenStr.getBytes();
			}
			
			
			
			
			
			
			
			
			

			keyAttrisLen = 32 + 32 + 13 + 256 + 4 + subjectLenInt+ 4 + priExpLenInt+ 8 + 8 + 4 + modulusLenInt + 4*5 + 
					cka_prime_1LenInt + cka_prime_2LenInt + cka_exponent_1LenInt + cka_exponent_2LenInt + cka_coefficientLenInt;

			keyAttrisArray = new byte[keyAttrisLen];

			System.arraycopy(keyClassVal, 0, keyAttrisArray, 0, 32);
			System.arraycopy(keyTypeVal, 0, keyAttrisArray, 32, 32);
			System.arraycopy(token, 0, keyAttrisArray, 32 + 32, 1);
			System.arraycopy(privateAcc, 0, keyAttrisArray, 32 + 32 + 1, 1);
			System.arraycopy(sensitive, 0, keyAttrisArray, 32 + 32 + 1 + 1, 1);
			System.arraycopy(extractable, 0, keyAttrisArray, 32 + 32 + 1 + 1 + 1, 1);
			System.arraycopy(encrypt, 0, keyAttrisArray, 32 + 32 + 1 + 1 + 1 + 1, 1);
			System.arraycopy(decrypt, 0, keyAttrisArray, 32 + 32 + 1 + 1 + 1 + 1 + 1, 1);
			System.arraycopy(wrap, 0, keyAttrisArray, 32 + 32 + 1 + 1 + 1 + 1 + 1 + 1, 1);
			System.arraycopy(unwrap, 0, keyAttrisArray, 32 + 32 + 1 + 1 + 1 + 1 + 1 + 1 + 1, 1);
			System.arraycopy(sign, 0, keyAttrisArray, 32 + 32 + 1 + 1 + 1 + 1 + 1 + 1 + 1 + 1, 1);
			System.arraycopy(verify, 0, keyAttrisArray, 32 + 32 + 1 + 1 + 1 + 1 + 1 + 1 + 1 + 1 + 1, 1);
			System.arraycopy(signRecover, 0, keyAttrisArray, 32 + 32 + 1 + 1 + 1 + 1 + 1 + 1 + 1 + 1 + 1 + 1, 1);
			System.arraycopy(verifyRecover, 0, keyAttrisArray, 32 + 32 + 1 + 1 + 1 + 1 + 1 + 1 + 1 + 1 + 1 + 1 + 1, 1);
			System.arraycopy(derive, 0, keyAttrisArray, 32 + 32 + 1 + 1 + 1 + 1 + 1 + 1 + 1 + 1 + 1 + 1 + 1 + 1, 1);
			System.arraycopy(keyLabelVal, 0, keyAttrisArray, 32 + 32 + 1 + 1 + 1 + 1 + 1 + 1 + 1 + 1 + 1 + 1 + 1 + 1 + 1, 256);
			
			
			System.arraycopy(modulusLen, 0, keyAttrisArray, 32 + 32 + 1 + 1 + 1 + 1 + 1 + 1 + 1 + 1 + 1 + 1 + 1 + 1 + 1+256, 4);
			if (modulus != null){
				System.arraycopy(modulus, 0, keyAttrisArray, 32 + 32 + 1 + 1 + 1 + 1 + 1 + 1 + 1 + 1 + 1 + 1 + 1 + 1 + 1+256+4, modulusLenInt);
			}
			
			System.arraycopy(priExpLen, 0, keyAttrisArray, 32 + 32 + 1 + 1 + 1 + 1 + 1 + 1 + 1 + 1 + 1 + 1 + 1 + 1 + 1 + 256 +4+modulusLenInt, 4);
			if (priExpVal != null){
				System.arraycopy(priExpVal, 0, keyAttrisArray, 32 + 32 + 1 + 1 + 1 + 1 + 1 + 1 + 1 + 1 + 1 + 1 + 1 + 1 + 1 + 256 +4+modulusLenInt + 4, priExpLenInt);
			}
			
			
			System.arraycopy(subjectLen, 0, keyAttrisArray, 32 + 32 + 1 + 1 + 1 + 1 + 1 + 1 + 1 + 1 + 1 + 1 + 1 + 1 + 1 + 256+4+modulusLenInt+4+priExpLenInt, 4);
			if (subjectVal != null){
				System.arraycopy(subjectVal, 0, keyAttrisArray, 32 + 32 + 1 + 1 + 1 + 1 + 1 + 1 + 1 + 1 + 1 + 1 + 1 + 1 + 1 + 256 +4+modulusLenInt+4+priExpLenInt+ 4, subjectLenInt);
			}
			
			
			System.arraycopy(startData, 0, keyAttrisArray, 32 + 32 + 1 + 1 + 1 + 1 + 1 + 1 + 1 + 1 + 1 + 1 + 1 + 1 + 1 + 256 +4+modulusLenInt+4+priExpLenInt+ 4 + subjectLenInt, 8);
			System.arraycopy(endData, 0, keyAttrisArray, 32 + 32 + 1 + 1 + 1 + 1 + 1 + 1 + 1 + 1 + 1 + 1 + 1 + 1 + 1 + 256 +4+modulusLenInt+4+priExpLenInt+ 4 + subjectLenInt + 8, 8);
			
			
			System.arraycopy(cka_prime_1Len, 0, keyAttrisArray, 32 + 32 + 13 + 256 +4+modulusLenInt+4+priExpLenInt+ 4 + subjectLenInt + 8+8, 4);
			if (cka_prime_1 != null){
				System.arraycopy(cka_prime_1, 0, keyAttrisArray, 32 + 32 + 13 + 256 +4+modulusLenInt+4+priExpLenInt+ 4 + subjectLenInt + 8+8+4, cka_prime_1LenInt);
			}
			
			System.arraycopy(cka_prime_2Len, 0, keyAttrisArray, 32 + 32 + 13 + 256 +4+modulusLenInt+4+priExpLenInt+ 4 + subjectLenInt + 8+8+4+cka_prime_1LenInt, 4);
			if (cka_prime_2 != null){
				System.arraycopy(cka_prime_2, 0, keyAttrisArray, 32 + 32 + 13 + 256 +4+modulusLenInt+4+priExpLenInt+ 4 + subjectLenInt + 8+8+4+cka_prime_1LenInt+4, cka_prime_2LenInt);	
			}
		
			System.arraycopy(cka_exponent_1Len, 0, keyAttrisArray, 32 + 32 + 13 + 256 +4+modulusLenInt+4+priExpLenInt+ 4 + subjectLenInt + 8+8+4+cka_prime_1LenInt+4+cka_prime_2LenInt, 4);
			if (cka_exponent_1 != null){
				System.arraycopy(cka_exponent_1, 0, keyAttrisArray, 32 + 32 + 13 + 256 +4+modulusLenInt+4+priExpLenInt+ 4 + subjectLenInt + 8+8+4+cka_prime_1LenInt+4+cka_prime_2LenInt+4, cka_exponent_1LenInt);	
			}
						
			System.arraycopy(cka_exponent_2Len, 0, keyAttrisArray, 32 + 32 + 13 + 256 +4+modulusLenInt+4+priExpLenInt+ 4 + subjectLenInt + 8+8+4+cka_prime_1LenInt+4+cka_prime_2LenInt+4+cka_exponent_1LenInt, 4);
			if (cka_exponent_2 != null){
				System.arraycopy(cka_exponent_2, 0, keyAttrisArray, 32 + 32 + 13 + 256 +4+modulusLenInt+4+priExpLenInt+ 4 + subjectLenInt + 8+8+4+cka_prime_1LenInt+4+cka_prime_2LenInt+4+cka_exponent_1LenInt+4, cka_exponent_2LenInt);				
			}

			
			System.arraycopy(cka_coefficientLen, 0, keyAttrisArray, 32 + 32 + 13 + 256 +4+modulusLenInt+4+priExpLenInt+ 4 + subjectLenInt + 8+8+4+cka_prime_1LenInt+4+cka_prime_2LenInt+4+cka_exponent_1LenInt+4+cka_exponent_2LenInt, 4);
			if (cka_coefficient != null){
				System.arraycopy(cka_coefficient, 0, keyAttrisArray, 32 + 32 + 13 + 256 +4+modulusLenInt+4+priExpLenInt+ 4 + subjectLenInt + 8+8+4+cka_prime_1LenInt+4+cka_prime_2LenInt+4+cka_exponent_1LenInt+4+cka_exponent_2LenInt+4, cka_coefficientLenInt);	
			}
			
			
			
			
		}

	}

	public int getKeyAttrisLen() {
		return keyAttrisLen;
	}

	public void setKeyAttrisLen(int keyAttrisLen) {
		this.keyAttrisLen = keyAttrisLen;
	}

	public byte[] getKeyAttrisArray() {
		return keyAttrisArray;
	}

	public void setKeyAttrisArray(byte[] keyAttrisArray) {
		this.keyAttrisArray = keyAttrisArray;
	}

	public byte[] getKeyClassVal() {
		return keyClassVal;
	}

	public void setKeyClassVal(byte[] keyClassVal) {
		this.keyClassVal = keyClassVal;
	}

	public byte[] getKeyTypeVal() {
		return keyTypeVal;
	}

	public void setKeyTypeVal(byte[] keyTypeVal) {
		this.keyTypeVal = keyTypeVal;
	}

	public byte[] getToken() {
		return token;
	}

	public void setToken(byte[] token) {
		this.token = token;
	}

	public byte[] getPrivateAcc() {
		return privateAcc;
	}

	public void setPrivateAcc(byte[] privateAcc) {
		this.privateAcc = privateAcc;
	}

	public byte[] getSensitive() {
		return sensitive;
	}

	public void setSensitive(byte[] sensitive) {
		this.sensitive = sensitive;
	}

	public byte[] getExtractable() {
		return extractable;
	}

	public void setExtractable(byte[] extractable) {
		this.extractable = extractable;
	}

	public byte[] getEncrypt() {
		return encrypt;
	}

	public void setEncrypt(byte[] encrypt) {
		this.encrypt = encrypt;
	}

	public byte[] getDecrypt() {
		return decrypt;
	}

	public void setDecrypt(byte[] decrypt) {
		this.decrypt = decrypt;
	}

	public byte[] getWrap() {
		return wrap;
	}

	public void setWrap(byte[] wrap) {
		this.wrap = wrap;
	}

	public byte[] getUnwrap() {
		return unwrap;
	}

	public void setUnwrap(byte[] unwrap) {
		this.unwrap = unwrap;
	}

	public byte[] getSign() {
		return sign;
	}

	public void setSign(byte[] sign) {
		this.sign = sign;
	}

	public byte[] getVerify() {
		return verify;
	}

	public void setVerify(byte[] verify) {
		this.verify = verify;
	}

	public byte[] getSignRecover() {
		return signRecover;
	}

	public void setSignRecover(byte[] signRecover) {
		this.signRecover = signRecover;
	}

	public byte[] getVerifyRecover() {
		return verifyRecover;
	}

	public void setVerifyRecover(byte[] verifyRecover) {
		this.verifyRecover = verifyRecover;
	}

	public byte[] getDerive() {
		return derive;
	}

	public void setDerive(byte[] derive) {
		this.derive = derive;
	}

	public byte[] getKeyLabelVal() {
		return keyLabelVal;
	}

	public void setKeyLabelVal(byte[] keyLabelVal) {
		this.keyLabelVal = keyLabelVal;
	}

	public byte[] getStatData() {
		return startData;
	}

	public void setStatData(byte[] statData) {
		this.startData = statData;
	}

	public byte[] getEndData() {
		return endData;
	}

	public void setEndData(byte[] endData) {
		this.endData = endData;
	}

	public byte[] getKeyValLenVal() {
		return keyValLenVal;
	}

	public void setKeyValLenVal(byte[] keyValLenVal) {
		this.keyValLenVal = keyValLenVal;
	}

	public byte[] getStartData() {
		return startData;
	}

	public void setStartData(byte[] startData) {
		this.startData = startData;
	}

	public byte[] getModulusBitsVal() {
		return modulusBitsVal;
	}

	public void setModulusBitsVal(byte[] modulusBitsVal) {
		this.modulusBitsVal = modulusBitsVal;
	}

	public byte[] getPubExpLen() {
		return pubExpLen;
	}

	public void setPubExpLen(byte[] pubExpLen) {
		this.pubExpLen = pubExpLen;
	}

	public byte[] getPubExpVal() {
		return pubExpVal;
	}

	public void setPubExpVal(byte[] pubExpVal) {
		this.pubExpVal = pubExpVal;
	}

	public byte[] getSubjectLen() {
		return subjectLen;
	}

	public void setSubjectLen(byte[] subjectLen) {
		this.subjectLen = subjectLen;
	}

	public byte[] getSubjectVal() {
		return subjectVal;
	}

	public void setSubjectVal(byte[] subjectVal) {
		this.subjectVal = subjectVal;
	}

}
