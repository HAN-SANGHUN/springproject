package com.kona.kms.web.profile.model;

import java.util.Date;

public class KeyProfileJobModel {

	private long sequence;
	
	private String keyProfileID;
	
	private String keyProfileVersion;
	
	private String keyTypeCode;
	
	private String keySubject;
	
	private int tryCount;
	
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

	public String getKeyTypeCode() {
		return keyTypeCode;
	}

	public void setKeyTypeCode(String keyTypeCode) {
		this.keyTypeCode = keyTypeCode;
	}

	public String getKeySubject() {
		return keySubject;
	}

	public void setKeySubject(String keySubject) {
		this.keySubject = keySubject;
	}	

	public int getTryCount() {
		return tryCount;
	}

	public void setTryCount(int tryCount) {
		this.tryCount = tryCount;
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
		return "KeyProfileJobModel [sequence=" + sequence + ", keyProfileID="
				+ keyProfileID + ", keyProfileVersion=" + keyProfileVersion
				+ ", keyTypeCode=" + keyTypeCode + ", keySubject=" + keySubject
				+ ", tryCount=" + tryCount + ", description=" + description
				+ ", registrationUserID=" + registrationUserID
				+ ", registrationDate=" + registrationDate + ", updateUserID="
				+ updateUserID + ", updateDate=" + updateDate + "]";
	}	
}
