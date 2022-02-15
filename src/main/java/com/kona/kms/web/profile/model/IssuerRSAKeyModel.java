package com.kona.kms.web.profile.model;

import java.util.Date;

public class IssuerRSAKeyModel {

	private String companyID;
	
	private int issuerPublicKeyIndex;
	
	private String publicKeyProfileID;
	
	private String publicKeyProfileVersion;
	
	private String publicKeyLabel;
	
	private String privateKeyProfileID;
	
	private String privateKeyProfileVersion;
	
	private String privateKeyLabel;
	
	private Date expireDate;
	
	private String approStatsCd;
	
	private String activeStatusCode;
	
	private String registrationUserID;

	private Date registrationDate;

	private String updateUserID;

	private Date updateDate;
	
	private String keyRoleCode;
	
	private int keyIndex;
	
	

	public String getCompanyID() {
		return companyID;
	}






	public void setCompanyID(String companyID) {
		this.companyID = companyID;
	}






	public int getIssuerPublicKeyIndex() {
		return issuerPublicKeyIndex;
	}






	public void setIssuerPublicKeyIndex(int issuerPublicKeyIndex) {
		this.issuerPublicKeyIndex = issuerPublicKeyIndex;
	}






	public String getPublicKeyProfileID() {
		return publicKeyProfileID;
	}






	public void setPublicKeyProfileID(String publicKeyProfileID) {
		this.publicKeyProfileID = publicKeyProfileID;
	}






	public String getPublicKeyProfileVersion() {
		return publicKeyProfileVersion;
	}






	public void setPublicKeyProfileVersion(String publicKeyProfileVersion) {
		this.publicKeyProfileVersion = publicKeyProfileVersion;
	}






	public String getPublicKeyLabel() {
		return publicKeyLabel;
	}






	public void setPublicKeyLabel(String publicKeyLabel) {
		this.publicKeyLabel = publicKeyLabel;
	}






	public String getPrivateKeyProfileID() {
		return privateKeyProfileID;
	}






	public void setPrivateKeyProfileID(String privateKeyProfileID) {
		this.privateKeyProfileID = privateKeyProfileID;
	}






	public String getPrivateKeyProfileVersion() {
		return privateKeyProfileVersion;
	}






	public void setPrivateKeyProfileVersion(String privateKeyProfileVersion) {
		this.privateKeyProfileVersion = privateKeyProfileVersion;
	}






	public String getPrivateKeyLabel() {
		return privateKeyLabel;
	}






	public void setPrivateKeyLabel(String privateKeyLabel) {
		this.privateKeyLabel = privateKeyLabel;
	}






	public Date getExpireDate() {
		return expireDate;
	}






	public void setExpireDate(Date expireDate) {
		this.expireDate = expireDate;
	}






	public String getApproStatsCd() {
		return approStatsCd;
	}






	public void setApproStatsCd(String approStatsCd) {
		this.approStatsCd = approStatsCd;
	}






	public String getActiveStatusCode() {
		return activeStatusCode;
	}






	public void setActiveStatusCode(String activeStatusCode) {
		this.activeStatusCode = activeStatusCode;
	}






	public String getRegistrationUserID() {
		return registrationUserID;
	}






	public void setRegistrationUserID(String registrationUserID) {
		this.registrationUserID = registrationUserID;
	}






	public Date getRegistrationDate() {
		return registrationDate;
	}






	public void setRegistrationDate(Date registrationDate) {
		this.registrationDate = registrationDate;
	}






	public String getUpdateUserID() {
		return updateUserID;
	}






	public void setUpdateUserID(String updateUserID) {
		this.updateUserID = updateUserID;
	}






	public Date getUpdateDate() {
		return updateDate;
	}






	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}



	


	public String getKeyRoleCode() {
		return keyRoleCode;
	}






	public void setKeyRoleCode(String keyRoleCode) {
		this.keyRoleCode = keyRoleCode;
	}






	public int getKeyIndex() {
		return keyIndex;
	}






	public void setKeyIndex(int keyIndex) {
		this.keyIndex = keyIndex;
	}





	
	@Override
	public String toString() {
		return "KeyProfileJobModel [companyID=" + companyID + ", issuerPublicKeyIndex=" + issuerPublicKeyIndex + ", publicKeyProfileID=" + publicKeyProfileID + ", publicKeyProfileVersion=" + publicKeyProfileVersion + ", publicKeyLabel=" + publicKeyLabel + ", privateKeyProfileID=" + privateKeyProfileID + ", privateKeyProfileVersion=" + privateKeyProfileVersion + ", privateKeyLabel=" + privateKeyLabel + ", expireDate=" + expireDate + ", approStatsCd=" + approStatsCd + ", activeStatusCode=" + activeStatusCode + ", registrationUserID=" + registrationUserID + ", registrationDate=" + registrationDate + ", updateUserID=" + updateUserID + ", updateDate=" + updateDate + ", keyRoleCode=" + keyRoleCode + ", keyIndex=" + keyIndex + "]";
	}
}
