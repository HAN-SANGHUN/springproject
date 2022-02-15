package com.kona.kms.crypto.tcp.keyAttrisModel;

import com.kona.kms.crypto.KeyAttributes;
import com.kona.kms.crypto.tcp.utility.MSGModelConstant;

public class KeyAttrisImportSecretKeyModel {

	private KeyAttributes keyAttributes;

	public KeyAttrisImportSecretKeyModel(byte[] pairKeyAttrisArray) {

		keyAttributes = new KeyAttributes("SECRET");

		byte[] keyClassArray = new byte[32];
		System.arraycopy(pairKeyAttrisArray, 0, keyClassArray, 0, 32);
		String keyClassStr = new String(keyClassArray);
		keyClassStr = keyClassStr.replaceAll("\\s+", "");
		keyAttributes.cka_keyClass = Long.valueOf(keyClassStr);

		byte[] keyTypeArray = new byte[32];
		System.arraycopy(pairKeyAttrisArray, 32, keyTypeArray, 0, 32);
		String keyTypeStr = new String(keyTypeArray);
		keyTypeStr = keyTypeStr.replaceAll("\\s+", "");
		keyAttributes.cka_keyType = Long.valueOf(keyTypeStr);

		byte[] tokenArray = new byte[MSGModelConstant.KEY_ATTRS_BOOLEAN_LEN];
		System.arraycopy(pairKeyAttrisArray, 32 + 32, tokenArray, 0, MSGModelConstant.KEY_ATTRS_BOOLEAN_LEN);
		String tokenStr = new String(tokenArray);
		keyAttributes.cka_token = (tokenStr.equals("1")) ? true : false;

		byte[] privateArray = new byte[MSGModelConstant.KEY_ATTRS_BOOLEAN_LEN];
		System.arraycopy(pairKeyAttrisArray, 32 + 32 + MSGModelConstant.KEY_ATTRS_BOOLEAN_LEN, privateArray, 0, MSGModelConstant.KEY_ATTRS_BOOLEAN_LEN);
		String privateStr = new String(privateArray);
		keyAttributes.cka_private = (privateStr.equals("1")) ? true : false;

		byte[] sensitiveArray = new byte[MSGModelConstant.KEY_ATTRS_BOOLEAN_LEN];
		System.arraycopy(pairKeyAttrisArray, 32 + 32 + MSGModelConstant.KEY_ATTRS_BOOLEAN_LEN * 2, sensitiveArray, 0, MSGModelConstant.KEY_ATTRS_BOOLEAN_LEN);
		String sensitiveStr = new String(sensitiveArray);
		keyAttributes.cka_sensitive = (sensitiveStr.equals("1")) ? true : false;

		byte[] extractableArray = new byte[MSGModelConstant.KEY_ATTRS_BOOLEAN_LEN];
		System.arraycopy(pairKeyAttrisArray, 32 + 32 + MSGModelConstant.KEY_ATTRS_BOOLEAN_LEN * 3, extractableArray, 0, MSGModelConstant.KEY_ATTRS_BOOLEAN_LEN);
		String extractableStr = new String(extractableArray);
		keyAttributes.cka_extractable = (extractableStr.equals("1")) ? true : false;

		byte[] encryptArray = new byte[MSGModelConstant.KEY_ATTRS_BOOLEAN_LEN];
		System.arraycopy(pairKeyAttrisArray, 32 + 32 + MSGModelConstant.KEY_ATTRS_BOOLEAN_LEN * 4, encryptArray, 0, MSGModelConstant.KEY_ATTRS_BOOLEAN_LEN);
		String encryptStr = new String(encryptArray);
		keyAttributes.cka_encrypt = (encryptStr.equals("1")) ? true : false;

		byte[] decryptArray = new byte[MSGModelConstant.KEY_ATTRS_BOOLEAN_LEN];
		System.arraycopy(pairKeyAttrisArray, 32 + 32 + MSGModelConstant.KEY_ATTRS_BOOLEAN_LEN * 5, decryptArray, 0, MSGModelConstant.KEY_ATTRS_BOOLEAN_LEN);
		String decryptStr = new String(decryptArray);
		keyAttributes.cka_decrypt = (decryptStr.equals("1")) ? true : false;

		byte[] wrapArray = new byte[MSGModelConstant.KEY_ATTRS_BOOLEAN_LEN];
		System.arraycopy(pairKeyAttrisArray, 32 + 32 + MSGModelConstant.KEY_ATTRS_BOOLEAN_LEN * 6, wrapArray, 0, MSGModelConstant.KEY_ATTRS_BOOLEAN_LEN);
		String wrapStr = new String(wrapArray);
		keyAttributes.cka_wrap = (wrapStr.equals("1")) ? true : false;

		byte[] unwrapArray = new byte[MSGModelConstant.KEY_ATTRS_BOOLEAN_LEN];
		System.arraycopy(pairKeyAttrisArray, 32 + 32 + MSGModelConstant.KEY_ATTRS_BOOLEAN_LEN * 7, unwrapArray, 0, MSGModelConstant.KEY_ATTRS_BOOLEAN_LEN);
		String unwrapStr = new String(unwrapArray);
		keyAttributes.cka_unwrap = (unwrapStr.equals("1")) ? true : false;

		byte[] signArray = new byte[MSGModelConstant.KEY_ATTRS_BOOLEAN_LEN];
		System.arraycopy(pairKeyAttrisArray, 32 + 32 + MSGModelConstant.KEY_ATTRS_BOOLEAN_LEN * 8, signArray, 0, MSGModelConstant.KEY_ATTRS_BOOLEAN_LEN);
		String signStr = new String(signArray);
		keyAttributes.cka_sign = (signStr.equals("1")) ? true : false;

		byte[] verifyArray = new byte[MSGModelConstant.KEY_ATTRS_BOOLEAN_LEN];
		System.arraycopy(pairKeyAttrisArray, 32 + 32 + MSGModelConstant.KEY_ATTRS_BOOLEAN_LEN * 9, verifyArray, 0, MSGModelConstant.KEY_ATTRS_BOOLEAN_LEN);
		String verifyStr = new String(verifyArray);
		keyAttributes.cka_verify = (verifyStr.equals("1")) ? true : false;

		byte[] signRecoverArray = new byte[MSGModelConstant.KEY_ATTRS_BOOLEAN_LEN];
		System.arraycopy(pairKeyAttrisArray, 32 + 32 + MSGModelConstant.KEY_ATTRS_BOOLEAN_LEN * 10, signRecoverArray, 0, MSGModelConstant.KEY_ATTRS_BOOLEAN_LEN);
		String signRecoverStr = new String(signRecoverArray);
		keyAttributes.cka_signRecover = (signRecoverStr.equals("1")) ? true : false;

		byte[] verifyRecoverArray = new byte[MSGModelConstant.KEY_ATTRS_BOOLEAN_LEN];
		System.arraycopy(pairKeyAttrisArray, 32 + 32 + MSGModelConstant.KEY_ATTRS_BOOLEAN_LEN * 11, verifyRecoverArray, 0, MSGModelConstant.KEY_ATTRS_BOOLEAN_LEN);
		String verifyRecoverStr = new String(verifyRecoverArray);
		keyAttributes.cka_verifyRecover = (verifyRecoverStr.equals("1")) ? true : false;

		byte[] deriveArray = new byte[MSGModelConstant.KEY_ATTRS_BOOLEAN_LEN];
		System.arraycopy(pairKeyAttrisArray, 32 + 32 + MSGModelConstant.KEY_ATTRS_BOOLEAN_LEN * 12, deriveArray, 0, MSGModelConstant.KEY_ATTRS_BOOLEAN_LEN);
		String deriveStr = new String(deriveArray);
		keyAttributes.cka_derive = (deriveStr.equals("1")) ? true : false;

		byte[] keyLabelValArray = new byte[256];
		System.arraycopy(pairKeyAttrisArray, 32 + 32 + MSGModelConstant.KEY_ATTRS_BOOLEAN_LEN * 13, keyLabelValArray, 0, 256);
		String keyLabelValStrTemp = new String(keyLabelValArray);
		String keyLabelValStr = keyLabelValStrTemp.replaceAll("\\s+", "");
		keyAttributes.cka_label = keyLabelValStr.getBytes();

		byte[] keyValLenArray = new byte[4];
		System.arraycopy(pairKeyAttrisArray, 32 + 32 + MSGModelConstant.KEY_ATTRS_BOOLEAN_LEN * 13 + 256, keyValLenArray, 0, 4);
		String keyValLenStrTemp = new String(keyValLenArray);
		Integer keyValLenInt = Integer.parseInt(keyValLenStrTemp);

		byte[] keyValArray = new byte[keyValLenInt];
		System.arraycopy(pairKeyAttrisArray, 32 + 32 + MSGModelConstant.KEY_ATTRS_BOOLEAN_LEN * 13 + 256 + 4, keyValArray, 0, keyValLenInt);
		keyAttributes.cka_value = keyValArray;

		byte[] startDateArray = new byte[8];
		System.arraycopy(pairKeyAttrisArray, 32 + 32 + MSGModelConstant.KEY_ATTRS_BOOLEAN_LEN * 13 + 256 + 4 + keyValLenInt, startDateArray, 0, 8);
		keyAttributes.cka_startDate = startDateArray;

		byte[] endDateArray = new byte[8];
		System.arraycopy(pairKeyAttrisArray, 32 + 32 + MSGModelConstant.KEY_ATTRS_BOOLEAN_LEN * 13 + 256 + 4 + keyValLenInt + 8, endDateArray, 0, 8);
		keyAttributes.cka_endDate = endDateArray;

	}

	public KeyAttributes getKeyAttributes() {
		return keyAttributes;
	}

	public void setKeyAttributes(KeyAttributes keyAttributes) {
		this.keyAttributes = keyAttributes;
	}

}
