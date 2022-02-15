package com.kona.kms.web.profile.model;

import java.io.Serializable;

import org.springframework.web.multipart.MultipartFile;

public class ProfileImportDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3563034058298254952L;
	
	private MultipartFile file;
	
	private MultipartFile pubFile;
	
	private String importKeyType;
	
	private String companyID;
	
	private String companyName;
	
	private String tokenLabel;
	
	private String profileName;
	
	private String keyLabel;

	public MultipartFile getFile() {
		return file;
	}

	public void setFile(MultipartFile file) {
		this.file = file;
	}	
	
	public MultipartFile getPubFile() {
		return pubFile;
	}

	public void setPubFile(MultipartFile pubFile) {
		this.pubFile = pubFile;
	}

	public boolean isEmpty(){
		if(this.file == null) return true;
		
		if(this.file.isEmpty()) return true;
		
		return false;
	}
	
	public boolean isPubEmpty(){
		if(this.pubFile == null) return true;
		
		if(this.pubFile.isEmpty()) return true;
		
		return false;
	}	

	public String getImportKeyType() {
		return importKeyType;
	}

	public void setImportKeyType(String importKeyType) {
		this.importKeyType = importKeyType;
	}
	
	public boolean isPairKeyImport(){
		if(this.importKeyType == null) return false;
		
		if(this.importKeyType.equals("SECRETKEY")) return false;
		
		return true;
	}

	public String getCompanyID() {
		return companyID;
	}

	public void setCompanyID(String companyID) {
		this.companyID = companyID;
	}		

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getTokenLabel() {
		return tokenLabel;
	}

	public void setTokenLabel(String tokenLabel) {
		this.tokenLabel = tokenLabel;
	}

	public String getProfileName() {
		return profileName;
	}

	public void setProfileName(String profileName) {
		this.profileName = profileName;
	}

	public String getKeyLabel() {
		return keyLabel;
	}

	public void setKeyLabel(String keyLabel) {
		this.keyLabel = keyLabel;
	}	
}
