/**
 * 
 */
package com.kona.kms.token.model;

// key and LToken info
public class KeyInfo {
	
	String keyLabel;
	String keyType; 	// SECRET, PUBLIC, PRIVATE
	String keySubType; 	// DES, RSA, etc
	int keySize;
	String tokenLabel;
	
	public KeyInfo(){
		
	}		
	
	public KeyInfo(String keyLabel, String keyType, String keySubType, int keySize, String tokenLabel) {
		this.keyLabel = keyLabel;
		this.keyType = keyType;
		this.keySubType = keySubType;
		this.keySize = keySize;
		this.tokenLabel = tokenLabel;
	}

	public String getKeyLabel() {
		return keyLabel;
	}

	public void setKeyLabel(String keyLabel) {
		this.keyLabel = keyLabel;
	}

	public String getKeyType() {
		return keyType;
	}

	public void setKeyType(String keyType) {
		this.keyType = keyType;
	}

	public String getKeySubType() {
		return keySubType;
	}

	public void setKeySubType(String keySubType) {
		this.keySubType = keySubType;
	}

	public int getKeySize() {
		return keySize;
	}

	public void setKeySize(int keySize) {
		this.keySize = keySize;
	}

	public String getTokenLabel() {
		return tokenLabel;
	}

	public void setTokenLabel(String tokenLabel) {
		this.tokenLabel = tokenLabel;
	}

	@Override
	public String toString() {
		return "KeyInfo [keyLabel=" + keyLabel + ", keyType=" + keyType
				+ ", keySubType=" + keySubType + ", keySize=" + keySize
				+ ", tokenLabel=" + tokenLabel
				+ "]";
	}

}
