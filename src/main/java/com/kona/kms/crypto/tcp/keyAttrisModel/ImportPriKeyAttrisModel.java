package com.kona.kms.crypto.tcp.keyAttrisModel;

import com.kona.kms.crypto.KeyAttributes;
import com.kona.kms.crypto.tcp.utility.MSGModelConstant;


public class ImportPriKeyAttrisModel {
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
	
	
	private byte[] subjectLen;
	private byte[] subjectVal;
	private byte[] modulusLen;
	private byte[] modulusVal;
	private byte[] priExpLen;
	private byte[] priExpVal;
	private byte[] prime1Len;
	private byte[] prime1Val;
	private byte[] prime2Len;
	private byte[] prime2Val;
	private byte[] exp1Len;
	private byte[] exp1Val;
	private byte[] exp2Len;
	private byte[] exp2Val;
	private byte[] coefficientLen;
	private byte[] coefficientVal;
	private byte[] keyValLen;
	private byte[] keyVal;
	
	
	
	
	private byte[] startData;
	private byte[] endData;
	
	public ImportPriKeyAttrisModel(KeyAttributes keyAttris){
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
		
		subjectLen = new byte[4];
		modulusLen = new byte[4];
		priExpLen = new byte[4];
		prime1Len = new byte[4];
		prime2Len = new byte[4];
		exp1Len = new byte[4];
		exp2Len = new byte[4];
		coefficientLen = new byte[4];
		keyValLen = new byte[4];
		
		
//		String modulusLenStr = new String(modulusLen);
//		modulusVal= new byte[Integer.parseInt(modulusLenStr)];
		
		startData = new byte[8];
		endData = new byte[8];
		
		int loop;
		
		Integer subjectLenInt;
		Integer modulusLenInt;
		Integer priExpLenInt;
		Integer prime1LenInt;
		Integer prime2LenInt;
		Integer exp1LenInt;
		Integer exp2LenInt;
		Integer coefficientLenInt;
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
		
		if(keyAttris.cka_subject == null){
			subjectVal = null;
			subjectLen = "0000".getBytes();
			subjectLenInt = 0;
			
		} else{
			subjectVal = keyAttris.cka_subject;
			subjectLenInt = subjectVal.length; // ******************* here might be problem. hexStringToByteArray*******************
			String subjectLenStr = subjectLenInt.toString();
			loop = subjectLenStr.length();
			for (int i=0; i < 4 - loop; i++){
				subjectLenStr = "0" + subjectLenStr;
			}
			subjectLen = subjectLenStr.getBytes();	
		}
		
		if(keyAttris.cka_modulus == null){
			modulusVal = null;
			modulusLen = "0000".getBytes();
			modulusLenInt = 0;
		} else{
			modulusVal = keyAttris.cka_modulus;
			modulusLenInt = modulusVal.length; // ******************* here might be problem. hexStringToByteArray*******************
			String modulusLenStr = modulusLenInt.toString();
			loop = modulusLenStr.length();
			for (int i=0; i < 4 - loop; i++){
				modulusLenStr = "0" + modulusLenStr;
			}
			modulusLen = modulusLenStr.getBytes();	
		}
		
		if(keyAttris.cka_private_exponent == null){
			priExpVal = null;
			priExpLen = "0000".getBytes();
			priExpLenInt = 0;
		} else{
			priExpVal = keyAttris.cka_private_exponent;
			priExpLenInt = priExpVal.length; // ******************* here might be problem. hexStringToByteArray*******************
			String priExpLenStr = priExpLenInt.toString();
			loop = priExpLenStr.length();
			for (int i=0; i < 4 - loop; i++){
				priExpLenStr = "0" + priExpLenStr;
			}
			priExpLen = priExpLenStr.getBytes();	
		}
		
		if(keyAttris.cka_prime_1 == null){
			prime1Val = null;
			prime1Len = "0000".getBytes();
			prime1LenInt = 0;
		} else{
			prime1Val = keyAttris.cka_prime_1;
			prime1LenInt = prime1Val.length; // ******************* here might be problem. hexStringToByteArray*******************
			String prime1LenStr = prime1LenInt.toString();
			loop = prime1LenStr.length();
			for (int i=0; i < 4 - loop; i++){
				prime1LenStr = "0" + prime1LenStr;
			}
			prime1Len = prime1LenStr.getBytes();	
		}
		
		if(keyAttris.cka_prime_2 == null){
			prime2Val = null;
			prime2Len = "0000".getBytes();
			prime2LenInt = 0;
		} else{
			prime2Val = keyAttris.cka_prime_2;
			prime2LenInt = prime2Val.length; // ******************* here might be problem. hexStringToByteArray*******************
			String prime2LenStr = prime2LenInt.toString();
			loop = prime2LenStr.length();
			for (int i=0; i < 4 - loop; i++){
				prime2LenStr = "0" + prime2LenStr;
			}
			prime2Len = prime2LenStr.getBytes();	
		}
		
		if(keyAttris.cka_exponent_1 == null){
			exp1Val = null;
			exp1Len = "0000".getBytes();
			exp1LenInt = 0;
		} else{
			exp1Val = keyAttris.cka_exponent_1;
			exp1LenInt = exp1Val.length; // ******************* here might be problem. hexStringToByteArray*******************
			String exp1LenStr = exp1LenInt.toString();
			loop = exp1LenStr.length();
			for (int i=0; i < 4 - loop; i++){
				exp1LenStr = "0" + exp1LenStr;
			}
			exp1Len = exp1LenStr.getBytes();
		}
		
		if(keyAttris.cka_exponent_2 == null){
			exp2Val = null;
			exp2Len = "0000".getBytes();
			exp2LenInt = 0;
		} else{
			exp2Val = keyAttris.cka_exponent_2;
			exp2LenInt = exp2Val.length; // ******************* here might be problem. hexStringToByteArray*******************
			String exp2LenStr = exp2LenInt.toString();
			loop = exp2LenStr.length();
			for (int i=0; i < 4 - loop; i++){
				exp2LenStr = "0" + exp2LenStr;
			}
			exp2Len = exp2LenStr.getBytes();
		}
		
		if(keyAttris.cka_coefficient == null){
			coefficientVal = null;
			coefficientLen = "0000".getBytes();
			coefficientLenInt = 0;
		} else{
			coefficientVal = keyAttris.cka_coefficient;
			coefficientLenInt = coefficientVal.length; // ******************* here might be problem. hexStringToByteArray*******************
			String coefficientLenStr = coefficientLenInt.toString();
			loop = coefficientLenStr.length();
			for (int i=0; i < 4 - loop; i++){
				coefficientLenStr = "0" + coefficientLenStr;
			}
			coefficientLen = coefficientLenStr.getBytes();
		}
		
		if(keyAttris.cka_value == null){
			keyVal = null;
			keyValLen = "0000".getBytes();
			keyValLenInt = 0;
		} else{
			keyVal = keyAttris.cka_value;
			keyValLenInt = keyVal.length; // ******************* here might be problem. hexStringToByteArray*******************
			String keyValLenStr = keyValLenInt.toString();
			loop = keyValLenStr.length();
			for (int i=0; i < 4 - loop; i++){
				keyValLenStr = "0" + keyValLenStr;
			}
			keyValLen = keyValLenStr.getBytes();
		}
		
		
		startData = keyAttris.cka_startDate;
		
		endData = keyAttris.cka_endDate;
		
		keyAttrisLen = 32 + 32 + MSGModelConstant.KEY_ATTRS_BOOLEAN_LEN*13 + 256 + 4 + subjectLenInt + 4 + modulusLenInt + 4 + priExpLenInt + 4 + prime1LenInt
				+ 4 + prime2LenInt + 4 + exp1LenInt + 4 +  exp2LenInt + 4 + coefficientLenInt + 4 + keyValLenInt + 8 + 8;	
		
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

		System.arraycopy(subjectLen, 0, keyAttrisArray, 32+32+MSGModelConstant.KEY_ATTRS_BOOLEAN_LEN*13+256, 4);
		if (subjectVal != null) {
			System.arraycopy(subjectVal, 0, keyAttrisArray, 32+32+MSGModelConstant.KEY_ATTRS_BOOLEAN_LEN*13+256+4, subjectLenInt);	
		}
		System.arraycopy(modulusLen, 0, keyAttrisArray, 32+32+MSGModelConstant.KEY_ATTRS_BOOLEAN_LEN*13+256+4+subjectLenInt, 4);
		if (modulusVal != null){
			System.arraycopy(modulusVal, 0, keyAttrisArray, 32+32+MSGModelConstant.KEY_ATTRS_BOOLEAN_LEN*13+256+4+subjectLenInt+4, modulusLenInt);	
		}
		System.arraycopy(priExpLen, 0, keyAttrisArray, 32+32+MSGModelConstant.KEY_ATTRS_BOOLEAN_LEN*13+256+4+subjectLenInt+4+modulusLenInt, 4);
		if (priExpVal != null){
			System.arraycopy(priExpVal, 0, keyAttrisArray, 32+32+MSGModelConstant.KEY_ATTRS_BOOLEAN_LEN*13+256+4+subjectLenInt+4+modulusLenInt+4, priExpLenInt);	
		}
		System.arraycopy(prime1Len, 0, keyAttrisArray, 32+32+MSGModelConstant.KEY_ATTRS_BOOLEAN_LEN*13+256+4+subjectLenInt+4+modulusLenInt+4+priExpLenInt, 4);
		if (prime1Val != null){
			System.arraycopy(prime1Val, 0, keyAttrisArray, 32+32+MSGModelConstant.KEY_ATTRS_BOOLEAN_LEN*13+256+4+subjectLenInt+4+modulusLenInt+4+priExpLenInt+4, prime1LenInt);	
		}
		System.arraycopy(prime2Len, 0, keyAttrisArray, 32+32+MSGModelConstant.KEY_ATTRS_BOOLEAN_LEN*13+256+4+subjectLenInt+4+modulusLenInt+4+priExpLenInt+4+prime1LenInt, 4);
		if (prime2Val != null){
			System.arraycopy(prime2Val, 0, keyAttrisArray, 32+32+MSGModelConstant.KEY_ATTRS_BOOLEAN_LEN*13+256+4+subjectLenInt+4+modulusLenInt+4+priExpLenInt+4+prime1LenInt+4, prime2LenInt);	
		}
		System.arraycopy(exp1Len, 0, keyAttrisArray, 32+32+MSGModelConstant.KEY_ATTRS_BOOLEAN_LEN*13+256+4+subjectLenInt+4+modulusLenInt+4+priExpLenInt+4+prime1LenInt+4+prime2LenInt, 4);
		if (exp1Val != null){
			System.arraycopy(exp1Val, 0, keyAttrisArray, 32+32+MSGModelConstant.KEY_ATTRS_BOOLEAN_LEN*13+256+4+subjectLenInt+4+modulusLenInt+4+priExpLenInt+4+prime1LenInt+4+prime2LenInt+4, exp1LenInt);	
		}
		System.arraycopy(exp2Len, 0, keyAttrisArray, 32+32+MSGModelConstant.KEY_ATTRS_BOOLEAN_LEN*13+256+4+subjectLenInt+4+modulusLenInt+4+priExpLenInt+4+prime1LenInt+4+prime2LenInt+4+exp1LenInt, 4);
		if (exp2Val != null){
			System.arraycopy(exp2Val, 0, keyAttrisArray, 32+32+MSGModelConstant.KEY_ATTRS_BOOLEAN_LEN*13+256+4+subjectLenInt+4+modulusLenInt+4+priExpLenInt+4+prime1LenInt+4+prime2LenInt+4+exp1LenInt+4, exp2LenInt);	
		}
		System.arraycopy(coefficientLen, 0, keyAttrisArray, 32+32+MSGModelConstant.KEY_ATTRS_BOOLEAN_LEN*13+256+4+subjectLenInt+4+modulusLenInt+4+priExpLenInt+4+prime1LenInt+4+prime2LenInt+4+exp1LenInt+4+exp2LenInt, 4);
		if (coefficientVal != null){
			System.arraycopy(coefficientVal, 0, keyAttrisArray, 32+32+MSGModelConstant.KEY_ATTRS_BOOLEAN_LEN*13+256+4+subjectLenInt+4+modulusLenInt+4+priExpLenInt+4+prime1LenInt+4+prime2LenInt+4+exp1LenInt+4+exp2LenInt+4, coefficientLenInt);			
		}
		System.arraycopy(keyValLen, 0, keyAttrisArray, 32+32+MSGModelConstant.KEY_ATTRS_BOOLEAN_LEN*13+256+4+subjectLenInt+4+modulusLenInt+4+priExpLenInt+4+prime1LenInt+4+prime2LenInt+4+exp1LenInt+4+exp2LenInt+4+coefficientLenInt, 4);
		if (keyVal != null){
			System.arraycopy(keyVal, 0, keyAttrisArray, 32+32+MSGModelConstant.KEY_ATTRS_BOOLEAN_LEN*13+256+4+subjectLenInt+4+modulusLenInt+4+priExpLenInt+4+prime1LenInt+4+prime2LenInt+4+exp1LenInt+4+exp2LenInt+4+coefficientLenInt+4, keyValLenInt);	
		}
		System.arraycopy(startData, 0, keyAttrisArray, 32+32+MSGModelConstant.KEY_ATTRS_BOOLEAN_LEN*13+256+4+subjectLenInt+4+modulusLenInt+4+priExpLenInt+4+prime1LenInt+4+prime2LenInt+4+exp1LenInt+4+exp2LenInt+4+coefficientLenInt+4+keyValLenInt, 8);
		System.arraycopy(endData, 0, keyAttrisArray, 32+32+MSGModelConstant.KEY_ATTRS_BOOLEAN_LEN*13+256+4+subjectLenInt+4+modulusLenInt+4+priExpLenInt+4+prime1LenInt+4+prime2LenInt+4+exp1LenInt+4+exp2LenInt+4+coefficientLenInt+4+keyValLenInt+8, 8);
		
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

	public byte[] getModulusLen() {
		return modulusLen;
	}

	public void setModulusLen(byte[] modulusLen) {
		this.modulusLen = modulusLen;
	}

	public byte[] getModulusVal() {
		return modulusVal;
	}

	public void setModulusVal(byte[] modulusVal) {
		this.modulusVal = modulusVal;
	}

	public byte[] getPriExpLen() {
		return priExpLen;
	}

	public void setPriExpLen(byte[] priExpLen) {
		this.priExpLen = priExpLen;
	}

	public byte[] getPriExpVal() {
		return priExpVal;
	}

	public void setPriExpVal(byte[] priExpVal) {
		this.priExpVal = priExpVal;
	}

	public byte[] getPrime1Len() {
		return prime1Len;
	}

	public void setPrime1Len(byte[] prime1Len) {
		this.prime1Len = prime1Len;
	}

	public byte[] getPrime1Val() {
		return prime1Val;
	}

	public void setPrime1Val(byte[] prime1Val) {
		this.prime1Val = prime1Val;
	}

	public byte[] getPrime2Len() {
		return prime2Len;
	}

	public void setPrime2Len(byte[] prime2Len) {
		this.prime2Len = prime2Len;
	}

	public byte[] getPrime2Val() {
		return prime2Val;
	}

	public void setPrime2Val(byte[] prime2Val) {
		this.prime2Val = prime2Val;
	}

	public byte[] getExp1Len() {
		return exp1Len;
	}

	public void setExp1Len(byte[] exp1Len) {
		this.exp1Len = exp1Len;
	}

	public byte[] getExp1Val() {
		return exp1Val;
	}

	public void setExp1Val(byte[] exp1Val) {
		this.exp1Val = exp1Val;
	}

	public byte[] getExp2Len() {
		return exp2Len;
	}

	public void setExp2Len(byte[] exp2Len) {
		this.exp2Len = exp2Len;
	}

	public byte[] getExp2Val() {
		return exp2Val;
	}

	public void setExp2Val(byte[] exp2Val) {
		this.exp2Val = exp2Val;
	}

	public byte[] getCoefficientLen() {
		return coefficientLen;
	}

	public void setCoefficientLen(byte[] coefficientLen) {
		this.coefficientLen = coefficientLen;
	}

	public byte[] getCoefficientVal() {
		return coefficientVal;
	}

	public void setCoefficientVal(byte[] coefficientVal) {
		this.coefficientVal = coefficientVal;
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

	
	
	
}
