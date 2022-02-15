package com.kona.kms.crypto.tcp.keyAttrisModel;

import com.kona.kms.crypto.KeyAttributes;
//import com.kona.kms.crypto.tcp.MSGModelConstant;
import com.kona.kms.crypto.tcp.utility.MSGModelConstant;

public class EncryptAttrisModel {
	private byte[] keyAttrisArray;
	private int keyAttrisLen;
	
	private byte[] keyClassVal;
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
	private byte[] keyLabelVal;
	
	private byte[] keyValLen;
	private byte[] keyVal;
	
	private byte[] startData;
	private byte[] endData;
	
	public EncryptAttrisModel(KeyAttributes keyAttris){
		parseKeyAttris(keyAttris);
	}
	
	public void parseKeyAttris(KeyAttributes keyAttris){
		keyClassVal = new byte[32];
		keyTypeVal = new byte[32];
		token = new byte[MSGModelConstant.KEY_ATTRS_BOOLEAN_LEN];
		privateAcc = new byte[MSGModelConstant.KEY_ATTRS_BOOLEAN_LEN];
		sensitive = new byte[MSGModelConstant.KEY_ATTRS_BOOLEAN_LEN];
		extractable = new byte[MSGModelConstant.KEY_ATTRS_BOOLEAN_LEN];
		encrypt = new byte[MSGModelConstant.KEY_ATTRS_BOOLEAN_LEN];
		decrypt = new byte[MSGModelConstant.KEY_ATTRS_BOOLEAN_LEN];
		wrap = new byte[MSGModelConstant.KEY_ATTRS_BOOLEAN_LEN];
		unwrap = new byte[MSGModelConstant.KEY_ATTRS_BOOLEAN_LEN];
		sign = new byte[MSGModelConstant.KEY_ATTRS_BOOLEAN_LEN];
		verify = new byte[MSGModelConstant.KEY_ATTRS_BOOLEAN_LEN];
		signRecover = new byte[MSGModelConstant.KEY_ATTRS_BOOLEAN_LEN];
		verifyRecover = new byte[MSGModelConstant.KEY_ATTRS_BOOLEAN_LEN];
		derive = new byte[MSGModelConstant.KEY_ATTRS_BOOLEAN_LEN];
		keyLabelVal = new byte[256];
	
		keyValLen = new byte[4];
				
		startData = new byte[8];
		endData = new byte[8];
		
		int loop;
		
		Integer keyValLenInt;
		
		String keyClassStr = String.valueOf(keyAttris.cka_keyClass);
		loop = keyClassStr.length(); 
		for(int i=0; i < 32 - loop; i++){
			keyClassStr = keyClassStr + " ";
		}
		keyClassVal = keyClassStr.getBytes();
		
		String keyTypeStr = String.valueOf(keyAttris.cka_keyType);
		loop = keyTypeStr.length(); 
		for(int i=0; i < 32 - loop; i++){
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
		
		if(keyAttris.cka_label == null){
			String keyLabelValStr = "";
			for(int i=0; i < 256; i++){
				keyLabelValStr = keyLabelValStr + " ";
			}
			keyLabelVal = keyLabelValStr.getBytes();
		} else{
			String keyLabelValStr = new String(keyAttris.cka_label);
			loop = keyLabelValStr.length(); 
			for(int i=0; i < 256 - loop; i++){
				keyLabelValStr = keyLabelValStr + " ";
			}
			keyLabelVal = keyLabelValStr.getBytes();
		}
		
		if(keyAttris.cka_value == null){
			keyVal = null;
			keyValLen = "0000".getBytes();
			keyValLenInt = 0;
		} else{
			keyVal = keyAttris.cka_value;
			keyValLenInt = keyVal.length; // ******************* here might be problem. hexStringToByteArray?*******************
			String keyValLenStr = keyValLenInt.toString();
			loop = keyValLenStr.length();
			for (int i=0; i < 4 - loop; i++){
				keyValLenStr = "0" + keyValLenStr;
			}
			keyValLen = keyValLenStr.getBytes();
		}
		
		
		startData = keyAttris.cka_startDate;
		
		endData = keyAttris.cka_endDate;
		
		keyAttrisLen = 32 + 32 + MSGModelConstant.KEY_ATTRS_BOOLEAN_LEN*13 + 256 + 4 + keyValLenInt + 8 + 8;
		
		keyAttrisArray = new byte[keyAttrisLen];
		
		System.arraycopy(keyClassVal, 0, keyAttrisArray, 0, 32);
		System.arraycopy(keyTypeVal, 0, keyAttrisArray, 32, 32);
		System.arraycopy(token, 0, keyAttrisArray, 32+32, MSGModelConstant.KEY_ATTRS_BOOLEAN_LEN);
		System.arraycopy(privateAcc, 0, keyAttrisArray, 32+32+MSGModelConstant.KEY_ATTRS_BOOLEAN_LEN, MSGModelConstant.KEY_ATTRS_BOOLEAN_LEN);
		System.arraycopy(sensitive, 0, keyAttrisArray, 32+32+MSGModelConstant.KEY_ATTRS_BOOLEAN_LEN*2, MSGModelConstant.KEY_ATTRS_BOOLEAN_LEN);
		System.arraycopy(extractable, 0, keyAttrisArray, 32+32+MSGModelConstant.KEY_ATTRS_BOOLEAN_LEN*3, MSGModelConstant.KEY_ATTRS_BOOLEAN_LEN);
		System.arraycopy(encrypt, 0, keyAttrisArray, 32+32+MSGModelConstant.KEY_ATTRS_BOOLEAN_LEN*4, MSGModelConstant.KEY_ATTRS_BOOLEAN_LEN);
		System.arraycopy(decrypt, 0, keyAttrisArray, 32+32+MSGModelConstant.KEY_ATTRS_BOOLEAN_LEN*5, MSGModelConstant.KEY_ATTRS_BOOLEAN_LEN);
		System.arraycopy(wrap, 0, keyAttrisArray, 32+32+MSGModelConstant.KEY_ATTRS_BOOLEAN_LEN*6, MSGModelConstant.KEY_ATTRS_BOOLEAN_LEN);
		System.arraycopy(unwrap, 0, keyAttrisArray, 32+32+MSGModelConstant.KEY_ATTRS_BOOLEAN_LEN*7, MSGModelConstant.KEY_ATTRS_BOOLEAN_LEN);
		System.arraycopy(sign, 0, keyAttrisArray, 32+32+MSGModelConstant.KEY_ATTRS_BOOLEAN_LEN*8, MSGModelConstant.KEY_ATTRS_BOOLEAN_LEN);
		System.arraycopy(verify, 0, keyAttrisArray, 32+32+MSGModelConstant.KEY_ATTRS_BOOLEAN_LEN*9, MSGModelConstant.KEY_ATTRS_BOOLEAN_LEN);
		System.arraycopy(signRecover, 0, keyAttrisArray, 32+32+MSGModelConstant.KEY_ATTRS_BOOLEAN_LEN*10, MSGModelConstant.KEY_ATTRS_BOOLEAN_LEN);
		System.arraycopy(verifyRecover, 0, keyAttrisArray, 32+32+MSGModelConstant.KEY_ATTRS_BOOLEAN_LEN*11, MSGModelConstant.KEY_ATTRS_BOOLEAN_LEN);
		System.arraycopy(derive, 0, keyAttrisArray, 32+32+MSGModelConstant.KEY_ATTRS_BOOLEAN_LEN*12, MSGModelConstant.KEY_ATTRS_BOOLEAN_LEN);
		
		
//		if (keyLabelVal != null){
//			System.arraycopy(keyLabelVal, 0, keyAttrisArray, 32+32+MSGModelConstant.KEY_ATTRS_BOOLEAN_LEN*13, 256);
//			System.arraycopy(keyValLen, 0, keyAttrisArray, 32+32+MSGModelConstant.KEY_ATTRS_BOOLEAN_LEN*13+256, 4);
//			System.arraycopy(keyVal, 0, keyAttrisArray, 32+32+MSGModelConstant.KEY_ATTRS_BOOLEAN_LEN*13+256+4, keyValLenInt);
//			System.arraycopy(startData, 0, keyAttrisArray, 32+32+MSGModelConstant.KEY_ATTRS_BOOLEAN_LEN*13+256+4+keyValLenInt, 8);
//			System.arraycopy(endData, 0, keyAttrisArray, 32+32+MSGModelConstant.KEY_ATTRS_BOOLEAN_LEN*13+256+4+keyValLenInt+8, 8);	
//		} else{
//			System.arraycopy(keyValLen, 0, keyAttrisArray, 32+32+MSGModelConstant.KEY_ATTRS_BOOLEAN_LEN*13, 4);
//			System.arraycopy(keyVal, 0, keyAttrisArray, 32+32+MSGModelConstant.KEY_ATTRS_BOOLEAN_LEN*13+4, keyValLenInt);
//			System.arraycopy(startData, 0, keyAttrisArray, 32+32+MSGModelConstant.KEY_ATTRS_BOOLEAN_LEN*13+4+keyValLenInt, 8);
//			System.arraycopy(endData, 0, keyAttrisArray, 32+32+MSGModelConstant.KEY_ATTRS_BOOLEAN_LEN*13+4+keyValLenInt+8, 8);	
//		}
		
		System.arraycopy(keyLabelVal, 0, keyAttrisArray, 32+32+MSGModelConstant.KEY_ATTRS_BOOLEAN_LEN*13, 256);
		System.arraycopy(keyValLen, 0, keyAttrisArray, 32+32+MSGModelConstant.KEY_ATTRS_BOOLEAN_LEN*13+256, 4);
		System.arraycopy(keyVal, 0, keyAttrisArray, 32+32+MSGModelConstant.KEY_ATTRS_BOOLEAN_LEN*13+256+4, keyValLenInt);
		System.arraycopy(startData, 0, keyAttrisArray, 32+32+MSGModelConstant.KEY_ATTRS_BOOLEAN_LEN*13+256+4+keyValLenInt, 8);
		System.arraycopy(endData, 0, keyAttrisArray, 32+32+MSGModelConstant.KEY_ATTRS_BOOLEAN_LEN*13+256+4+keyValLenInt+8, 8);
		
		
	}

	public byte[] getKeyAttrisArray() {
		return keyAttrisArray;
	}

	public void setKeyAttrisArray(byte[] keyAttrisArray) {
		this.keyAttrisArray = keyAttrisArray;
	}

	public int getKeyAttrisLen() {
		return keyAttrisLen;
	}

	public void setKeyAttrisLen(int keyAttrisLen) {
		this.keyAttrisLen = keyAttrisLen;
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

	
	public byte[] getKeyValLen() {
		return keyValLen;
	}

	public void setKeyValLen(byte[] keyValLen) {
		this.keyValLen = keyValLen;
	}

	public byte[] getKeyVal() {
		return keyVal;
	}

	public void setKeyVal(byte[] keyVal) {
		this.keyVal = keyVal;
	}

	public byte[] getStartData() {
		return startData;
	}

	public void setStartData(byte[] startData) {
		this.startData = startData;
	}

	public byte[] getEndData() {
		return endData;
	}

	public void setEndData(byte[] endData) {
		this.endData = endData;
	}

	
}
