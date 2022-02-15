package com.kona.kms.web.profile.model;

import java.io.Serializable;
import java.util.Date;

public class KeyProfileRevisionModel implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6149125213759004000L;
	
	private long sequence;

	private String keyProfileID;
	
	private String keyProfileVersion;
		
	private String digest;
		
	private String description;
	
	private String registrationUserID;
	
	private Date registrationDate;
	
	private String updateUserID;
	
	private Date updateDate;
	

	public long getSequence() {
		return sequence;
	}

	public void setSequence(long sequence) {
		this.sequence = sequence;
	}

	public String getKeyProfileID() {
		return keyProfileID;
	}

	public void setKeyProfileID(String keyProfileID) {
		this.keyProfileID = keyProfileID;
	}

	public String getKeyProfileVersion() {
		return keyProfileVersion;
	}

	public void setKeyProfileVersion(String keyProfileVersion) {
		this.keyProfileVersion = keyProfileVersion;
	}		

	public String getDigest() {
		return digest;
	}

	public void setDigest(String digest) {
		this.digest = digest;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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

	@Override
	public String toString() {
		return "KeyProfileRevisionModel [keyProfileID=" + keyProfileID
				+ ", keyProfileVersion=" + keyProfileVersion + ", digest="
				+ digest + ", description=" + description
				+ ", registrationUserID=" + registrationUserID
				+ ", registrationDate=" + registrationDate + ", updateUserID="
				+ updateUserID + ", updateDate=" + updateDate + "]";
	}		
	
}
