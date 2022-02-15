package com.kona.kms.web.cert.model;

import java.util.Date;

public class CertJobModel {

	private String certificateUID;
	
	private String certificateName;
	
	private String workcode;
	
	private int tryCount;
	
	private String description;
	
	private String registrationUserID;
	
	private Date registrationDate;
	
	private String updateUserID;
	
	private Date updateDate;

	public String getCertificateUID() {
		return certificateUID;
	}

	public void setCertificateUID(String certificateUID) {
		this.certificateUID = certificateUID;
	}

	public String getCertificateName() {
		return certificateName;
	}

	public void setCertificateName(String certificateName) {
		this.certificateName = certificateName;
	}

	public String getWorkcode() {
		return workcode;
	}

	public void setWorkcode(String workcode) {
		this.workcode = workcode;
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
		return "CertJobModel [certificateUID=" + certificateUID
				+ ", certificateName=" + certificateName + ", workcode="
				+ workcode + ", tryCount=" + tryCount + ", description="
				+ description + ", registrationUserID=" + registrationUserID
				+ ", registrationDate=" + registrationDate + ", updateUserID="
				+ updateUserID + ", updateDate=" + updateDate + "]";
	}
	
}
