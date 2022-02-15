package com.kona.kms.web.profile.model;

import java.io.Serializable;

public class KeyProfileModel1 implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6837668697407572652L;
	
	private String keyProfileID1;
	
	private String keyProfileVersion1;
	
	private String keyProfileName1;
	
	private String description1;
	
	private String keyLabel1;
	
	private long keySize1;
	
	private String keyAlgorithm1;
	
	private String keyUsageIndicatorValue1;	
	
	private boolean isImportable1;
	
	private boolean isExportable1;
	
	private boolean isSensitive1;
	
	private boolean isEncrypt1;
	
	private boolean isDecrypt1;
	
	private boolean isEncryptDecrypt1;
	
	private boolean isWrap1;
	
	private boolean isUnwrap1;
	
	private boolean isWrapUnwrap1;
	
	private boolean isSign1;
	
	private boolean isVerify1;
	
	private boolean isDerive1;
	
	public KeyProfileModel1(){
		
	}	

	public String getKeyProfileID1() {
		return keyProfileID1;
	}

	public void setKeyProfileID1(String keyProfileID1) {
		this.keyProfileID1 = keyProfileID1;
	}

	public String getKeyProfileVersion1() {
		return keyProfileVersion1;
	}

	public void setKeyProfileVersion1(String keyProfileVersion1) {
		this.keyProfileVersion1 = keyProfileVersion1;
	}

	public String getKeyProfileName1() {
		return keyProfileName1;
	}

	public void setKeyProfileName1(String keyProfileName1) {
		this.keyProfileName1 = keyProfileName1;
	}

	public String getDescription1() {
		return description1;
	}

	public void setDescription1(String description1) {
		this.description1 = description1;
	}

	public String getKeyLabel1() {
		return keyLabel1;
	}

	public void setKeyLabel1(String keyLabel1) {
		this.keyLabel1 = keyLabel1;
	}
	

	public long getKeySize1() {
		return keySize1;
	}

	public void setKeySize1(long keySize1) {
		this.keySize1 = keySize1;
	}

	public String getKeyAlgorithm1() {
		return keyAlgorithm1;
	}

	public void setKeyAlgorithm1(String keyAlgorithm1) {
		this.keyAlgorithm1 = keyAlgorithm1;
	}

	public String getKeyUsageIndicatorValue1() {
		if(this.keyUsageIndicatorValue1 != null){

			return keyUsageIndicatorValue1;
		}else{
			StringBuffer sb = new StringBuffer();
			
			sb.append((this.isEncrypt1)?"0":"1");
			sb.append((this.isDecrypt1)?"0":"1");
			sb.append((this.isEncryptDecrypt1)?"0":"1");
			sb.append((this.isSign1)?"0":"1");
			sb.append((this.isVerify1)?"0":"1");
			sb.append((this.isWrap1)?"0":"1");
			sb.append((this.isUnwrap1)?"0":"1");
			sb.append((this.isWrapUnwrap1)?"0":"1");
			sb.append((this.isDerive1)?"0":"1");
			sb.append((this.isImportable1)?"0":"1");
			sb.append((this.isExportable1)?"0":"1");
			sb.append((this.isSensitive1)?"0":"1");
			
			
			return sb.toString();
		}
	}

	public void setKeyUsageIndicatorValue1(String keyUsageIndicatorValue1) {
		this.keyUsageIndicatorValue1 = keyUsageIndicatorValue1;
	}

	public boolean isImportable1() {
		return isImportable1;
	}

	public void setImportable1(boolean isImportable1) {
		this.isImportable1 = isImportable1;
	}

	public boolean isExportable1() {
		return isExportable1;
	}

	public void setExportable1(boolean isExportable1) {
		this.isExportable1 = isExportable1;
	}

	public boolean isSensitive1() {
		return isSensitive1;
	}

	public void setSensitive1(boolean isSensitive1) {
		this.isSensitive1 = isSensitive1;
	}

	public boolean isEncrypt1() {
		return isEncrypt1;
	}

	public void setEncrypt1(boolean isEncrypt1) {
		this.isEncrypt1 = isEncrypt1;
	}

	public boolean isDecrypt1() {
		return isDecrypt1;
	}

	public void setDecrypt1(boolean isDecrypt1) {
		this.isDecrypt1 = isDecrypt1;
	}

	public boolean isEncryptDecrypt1() {
		return isEncryptDecrypt1;
	}

	public void setEncryptDecrypt1(boolean isEncryptDecrypt1) {
		this.isEncryptDecrypt1 = isEncryptDecrypt1;
	}

	public boolean isWrap1() {
		return isWrap1;
	}

	public void setWrap1(boolean isWrap1) {
		this.isWrap1 = isWrap1;
	}

	public boolean isUnwrap1() {
		return isUnwrap1;
	}

	public void setUnwrap1(boolean isUnwrap1) {
		this.isUnwrap1 = isUnwrap1;
	}

	public boolean isWrapUnwrap1() {
		return isWrapUnwrap1;
	}

	public void setWrapUnwrap1(boolean isWrapUnwrap1) {
		this.isWrapUnwrap1 = isWrapUnwrap1;
	}

	public boolean isSign1() {
		return isSign1;
	}

	public void setSign1(boolean isSign1) {
		this.isSign1 = isSign1;
	}

	public boolean isVerify1() {
		return isVerify1;
	}

	public void setVerify1(boolean isVerify1) {
		this.isVerify1 = isVerify1;
	}

	public boolean isDerive1() {
		return isDerive1;
	}

	public void setDerive1(boolean isDerive1) {
		this.isDerive1 = isDerive1;
	}

	@Override
	public String toString() {
		return "KeyProfileModel1 [keyProfileID1=" + keyProfileID1
				+ ", keyProfileVersion1=" + keyProfileVersion1
				+ ", keyProfileName1=" + keyProfileName1 + ", description1="
				+ description1 + ", keyLabel1=" + keyLabel1 + ", keySize1="
				+ keySize1 + ", keyAlgorithm1=" + keyAlgorithm1
				+ ", keyUsageIndicatorValue1=" + keyUsageIndicatorValue1
				+ ", isImportable1=" + isImportable1 + ", isExportable1="
				+ isExportable1 + ", isSensitive1=" + isSensitive1
				+ ", isEncrypt1=" + isEncrypt1 + ", isDecrypt1=" + isDecrypt1
				+ ", isEncryptDecrypt1=" + isEncryptDecrypt1 + ", isWrap1="
				+ isWrap1 + ", isUnwrap1=" + isUnwrap1 + ", isWrapUnwrap1="
				+ isWrapUnwrap1 + ", isSign1=" + isSign1 + ", isVerify1="
				+ isVerify1 + ", isDerive1=" + isDerive1 + "]";
	}

}
