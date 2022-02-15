package com.kona.kms.crypto.tcp.keyAttrisModel;

import com.kona.kms.crypto.KeyAttributes;
import com.kona.kms.crypto.tcp.utility.MSGModelConstant;

public class KeyAttrisImportPriKeyModel {

	private KeyAttributes keyAttributes;

	public KeyAttrisImportPriKeyModel(byte[] keyAttrisArray) {

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

		byte[] subjectLenArray = new byte[4];
		System.arraycopy(keyAttrisArray, 32 + 32 + MSGModelConstant.KEY_ATTRS_BOOLEAN_LEN * 13 + 256, subjectLenArray, 0, 4);
		String subjectLenStrTemp = new String(subjectLenArray);
		Integer subjectLenInt = Integer.parseInt(subjectLenStrTemp);

		byte[] subjectValArray = new byte[subjectLenInt];
		System.arraycopy(keyAttrisArray, 32 + 32 + MSGModelConstant.KEY_ATTRS_BOOLEAN_LEN * 13 + 256 + 4, subjectValArray, 0, subjectLenInt);
		keyAttributes.cka_subject = subjectValArray;

		byte[] modulusLenArray = new byte[4];
		System.arraycopy(keyAttrisArray, 32 + 32 + MSGModelConstant.KEY_ATTRS_BOOLEAN_LEN * 13 + 256 + 4 + subjectLenInt, modulusLenArray, 0, 4);
		String modulusLenStrTemp = new String(modulusLenArray);
		Integer modulusLenInt = Integer.parseInt(modulusLenStrTemp);

		byte[] modulusValArray = new byte[modulusLenInt];
		System.arraycopy(keyAttrisArray, 32 + 32 + MSGModelConstant.KEY_ATTRS_BOOLEAN_LEN * 13 + 256 + 4 + subjectLenInt + 4, modulusValArray, 0, modulusLenInt);
		keyAttributes.cka_modulus = modulusValArray;

		byte[] priExpLenArray = new byte[4];
		System.arraycopy(keyAttrisArray, 32 + 32 + MSGModelConstant.KEY_ATTRS_BOOLEAN_LEN * 13 + 256 + 4 + subjectLenInt + 4 + modulusLenInt, priExpLenArray, 0, 4);
		String priExpLenStrTemp = new String(priExpLenArray);
		Integer priExpLenInt = Integer.parseInt(priExpLenStrTemp);

		byte[] priExpValArray = new byte[priExpLenInt];
		System.arraycopy(keyAttrisArray, 32 + 32 + MSGModelConstant.KEY_ATTRS_BOOLEAN_LEN * 13 + 256 + 4 + subjectLenInt + 4 + modulusLenInt + 4, priExpValArray, 0, priExpLenInt);
		keyAttributes.cka_private_exponent = priExpValArray;

		byte[] prime1LenArray = new byte[4];
		System.arraycopy(keyAttrisArray, 32 + 32 + MSGModelConstant.KEY_ATTRS_BOOLEAN_LEN * 13 + 256 + 4 + subjectLenInt + 4 + modulusLenInt + 4 + priExpLenInt, prime1LenArray, 0, 4);
		String prime1LenStrTemp = new String(prime1LenArray);
		Integer prime1LenInt = Integer.parseInt(prime1LenStrTemp);

		byte[] prime1ValArray = new byte[prime1LenInt];
		System.arraycopy(keyAttrisArray, 32 + 32 + MSGModelConstant.KEY_ATTRS_BOOLEAN_LEN * 13 + 256 + 4 + subjectLenInt + 4 + modulusLenInt + 4 + priExpLenInt + 4, prime1ValArray, 0, prime1LenInt);
		keyAttributes.cka_prime_1 = prime1ValArray;

		byte[] prime2LenArray = new byte[4];
		System.arraycopy(keyAttrisArray, 32 + 32 + MSGModelConstant.KEY_ATTRS_BOOLEAN_LEN * 13 + 256 + 4 + subjectLenInt + 4 + modulusLenInt + 4 + priExpLenInt + 4 + prime1LenInt, prime2LenArray, 0, 4);
		String prime2LenStrTemp = new String(prime2LenArray);
		Integer prime2LenInt = Integer.parseInt(prime2LenStrTemp);

		byte[] prime2ValArray = new byte[prime2LenInt];
		System.arraycopy(keyAttrisArray, 32 + 32 + MSGModelConstant.KEY_ATTRS_BOOLEAN_LEN * 13 + 256 + 4 + subjectLenInt + 4 + modulusLenInt + 4 + priExpLenInt + 4 + prime1LenInt + 4, prime2ValArray, 0, prime2LenInt);
		keyAttributes.cka_prime_2 = prime2ValArray;

		byte[] exp1LenArray = new byte[4];
		System.arraycopy(keyAttrisArray, 32 + 32 + MSGModelConstant.KEY_ATTRS_BOOLEAN_LEN * 13 + 256 + 4 + subjectLenInt + 4 + modulusLenInt + 4 + priExpLenInt + 4 + prime1LenInt + 4 + prime2LenInt, exp1LenArray, 0, 4);
		String exp1LenStrTemp = new String(exp1LenArray);
		Integer exp1LenInt = Integer.parseInt(exp1LenStrTemp);

		byte[] exp1ValArray = new byte[exp1LenInt];
		System.arraycopy(keyAttrisArray, 32 + 32 + MSGModelConstant.KEY_ATTRS_BOOLEAN_LEN * 13 + 256 + 4 + subjectLenInt + 4 + modulusLenInt + 4 + priExpLenInt + 4 + prime1LenInt + 4 + prime2LenInt + 4, exp1ValArray, 0, exp1LenInt);
		keyAttributes.cka_exponent_1 = exp1ValArray;

		byte[] exp2LenArray = new byte[4];
		System.arraycopy(keyAttrisArray, 32 + 32 + MSGModelConstant.KEY_ATTRS_BOOLEAN_LEN * 13 + 256 + 4 + subjectLenInt + 4 + modulusLenInt + 4 + priExpLenInt + 4 + prime1LenInt + 4 + prime2LenInt + 4 + exp1LenInt, exp2LenArray, 0, 4);
		String exp2LenStrTemp = new String(exp2LenArray);
		Integer exp2LenInt = Integer.parseInt(exp2LenStrTemp);

		byte[] exp2ValArray = new byte[exp2LenInt];
		System.arraycopy(keyAttrisArray, 32 + 32 + MSGModelConstant.KEY_ATTRS_BOOLEAN_LEN * 13 + 256 + 4 + subjectLenInt + 4 + modulusLenInt + 4 + priExpLenInt + 4 + prime1LenInt + 4 + prime2LenInt + 4 + exp1LenInt + 4, exp2ValArray, 0, exp2LenInt);
		keyAttributes.cka_exponent_2 = exp2ValArray;

		byte[] coefficientLenArray = new byte[4];
		System.arraycopy(keyAttrisArray, 32 + 32 + MSGModelConstant.KEY_ATTRS_BOOLEAN_LEN * 13 + 256 + 4 + subjectLenInt + 4 + modulusLenInt + 4 + priExpLenInt + 4 + prime1LenInt + 4 + prime2LenInt + 4 + exp1LenInt + 4 + exp2LenInt, coefficientLenArray, 0, 4);
		String coefficientLenStrTemp = new String(coefficientLenArray);
		Integer coefficientLenInt = Integer.parseInt(coefficientLenStrTemp);

		byte[] coefficientValArray = new byte[coefficientLenInt];
		System.arraycopy(keyAttrisArray, 32 + 32 + MSGModelConstant.KEY_ATTRS_BOOLEAN_LEN * 13 + 256 + 4 + subjectLenInt + 4 + modulusLenInt + 4 + priExpLenInt + 4 + prime1LenInt + 4 + prime2LenInt + 4 + exp1LenInt + 4 + exp2LenInt + 4, coefficientValArray, 0, coefficientLenInt);
		keyAttributes.cka_coefficient = coefficientValArray;

		byte[] keyValLenArray = new byte[4];
		System.arraycopy(keyAttrisArray, 32 + 32 + MSGModelConstant.KEY_ATTRS_BOOLEAN_LEN * 13 + 256 + 4 + subjectLenInt + 4 + modulusLenInt + 4 + priExpLenInt + 4 + prime1LenInt + 4 + prime2LenInt + 4 + exp1LenInt + 4 + exp2LenInt + 4 + coefficientLenInt, keyValLenArray, 0, 4);
		String keyValLenStrTemp = new String(keyValLenArray);
		Integer keyValLenInt = Integer.parseInt(keyValLenStrTemp);

		byte[] keyValArray = new byte[keyValLenInt];
		System.arraycopy(keyAttrisArray, 32 + 32 + MSGModelConstant.KEY_ATTRS_BOOLEAN_LEN * 13 + 256 + 4 + subjectLenInt + 4 + modulusLenInt + 4 + priExpLenInt + 4 + prime1LenInt + 4 + prime2LenInt + 4 + exp1LenInt + 4 + exp2LenInt + 4 + coefficientLenInt + 4, keyValArray, 0, keyValLenInt);
		keyAttributes.cka_value = keyValArray;

		byte[] startDateArray = new byte[8];
		System.arraycopy(keyAttrisArray, 32 + 32 + MSGModelConstant.KEY_ATTRS_BOOLEAN_LEN * 13 + 256 + 4 + subjectLenInt + 4 + modulusLenInt + 4 + priExpLenInt + 4 + prime1LenInt + 4 + prime2LenInt + 4 + exp1LenInt + 4 + exp2LenInt + 4 + coefficientLenInt + 4 + keyValLenInt, startDateArray, 0, 8);
		keyAttributes.cka_startDate = startDateArray;

		byte[] endDateArray = new byte[8];
		System.arraycopy(keyAttrisArray, 32 + 32 + MSGModelConstant.KEY_ATTRS_BOOLEAN_LEN * 13 + 256 + 4 + subjectLenInt + 4 + modulusLenInt + 4 + priExpLenInt + 4 + prime1LenInt + 4 + prime2LenInt + 4 + exp1LenInt + 4 + exp2LenInt + 4 + coefficientLenInt + 4 + keyValLenInt + 8, endDateArray, 0, 8);
		keyAttributes.cka_endDate = endDateArray;

	}

	public KeyAttributes getKeyAttributes() {
		return keyAttributes;
	}

	public void setKeyAttributes(KeyAttributes keyAttributes) {
		this.keyAttributes = keyAttributes;
	}

}
