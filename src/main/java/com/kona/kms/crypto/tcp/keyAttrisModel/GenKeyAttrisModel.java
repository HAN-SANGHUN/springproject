package com.kona.kms.crypto.tcp.keyAttrisModel;

import com.kona.kms.crypto.KeyAttributes;
import com.kona.kms.crypto.tcp.utility.MSGModelConstant;


public class GenKeyAttrisModel {
	private byte[] keyAttrisArray;
	private int keyAttrisLen;
	
//	private byte[] keyClassLen;
	private byte[] keyClassVal;
//	private byte[] keyTypeLen;
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
//	private byte[] labelLen;
	private byte[] keyLabelVal;
	private byte[] statData;
	private byte[] endData;
//	private byte[] keyValLenLen;
	private byte[] keyValLenVal;
	
	public GenKeyAttrisModel(KeyAttributes keyAttris){
		parseKeyAttris(keyAttris);
	}
	
	public void parseKeyAttris(KeyAttributes keyAttris){
//		keyClassLen = new byte[4];
		keyClassVal = new byte[32];
//		keyTypeLen = new byte[4];
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
//		labelLen = new byte[4];
		keyLabelVal = new byte[256];
		statData = new byte[8];
		endData = new byte[8];
//		keyValLenLen = new byte[4];
		keyValLenVal = new byte[32];
		
		int loop;
		
		
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
		
		String keyLabelValStr;
		if(keyAttris.cka_label == null){
			keyLabelValStr = "";
			loop = 0; 
			for(int i=0; i < 256; i++){
				keyLabelValStr = keyLabelValStr + " ";
			}
			keyLabelVal = keyLabelValStr.getBytes();
		} else{
			keyLabelValStr = new String(keyAttris.cka_label);
			loop = keyLabelValStr.length(); 
			for(int i=0; i < 256 - loop; i++){
				keyLabelValStr = keyLabelValStr + " ";
			}
			keyLabelVal = keyLabelValStr.getBytes();
		}
				
		statData = keyAttris.cka_startDate;
		
		endData = keyAttris.cka_endDate;
		
		String keyValLenValStr = String.valueOf(keyAttris.cka_value_len);
		loop = keyValLenValStr.length(); 
		for(int i=0; i < 32 - loop; i++){
			keyValLenValStr = keyValLenValStr + " ";
		}
		keyValLenVal = keyValLenValStr.getBytes();
		
		keyAttrisLen = 32 + 32 + MSGModelConstant.KEY_ATTRS_BOOLEAN_LEN*13 + 256 + 8 + 8 + 32;
		
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
		System.arraycopy(keyLabelVal, 0, keyAttrisArray, 32+32+MSGModelConstant.KEY_ATTRS_BOOLEAN_LEN*13, 256);
		System.arraycopy(statData, 0, keyAttrisArray, 32+32+MSGModelConstant.KEY_ATTRS_BOOLEAN_LEN*13+256, 8);
		System.arraycopy(endData, 0, keyAttrisArray, 32+32+MSGModelConstant.KEY_ATTRS_BOOLEAN_LEN*13+256+8, 8);
		System.arraycopy(keyValLenVal, 0, keyAttrisArray, 32+32+MSGModelConstant.KEY_ATTRS_BOOLEAN_LEN*13+256+8+8, 32);
			
		
		
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
		return statData;
	}

	public void setStatData(byte[] statData) {
		this.statData = statData;
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
	
	
	
}
