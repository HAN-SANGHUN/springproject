package com.kona.kms.web.cert.model;

import java.io.Serializable;

import org.springframework.web.multipart.MultipartFile;

public class CertiRegisterDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -978256845148896217L;

	private String certificateUID;
	
	private MultipartFile certiFile;
	
	private MultipartFile hashFile;
	
	public CertiRegisterDTO(){
		
	}

	public String getCertificateUID() {
		return certificateUID;
	}

	public void setCertificateUID(String certificateUID) {
		this.certificateUID = certificateUID;
	}	
	
	public MultipartFile getCertiFile() {
		return certiFile;
	}

	public void setCertiFile(MultipartFile certiFile) {
		this.certiFile = certiFile;
	}
	
	public String getCertiFileName(){
		return this.certiFile.getOriginalFilename().substring(this.certiFile.getOriginalFilename().lastIndexOf("/")+1);
	}

	public MultipartFile getHashFile() {
		return hashFile;
	}

	public void setHashFile(MultipartFile hashFile) {
		this.hashFile = hashFile;
	}

	public boolean isEmpty(){
		if(this.certiFile == null) return true;
		
		if(this.certiFile.isEmpty()) return true;
		
		return false;
	}	
	
}
