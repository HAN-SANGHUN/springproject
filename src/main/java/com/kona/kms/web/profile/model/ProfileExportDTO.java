package com.kona.kms.web.profile.model;

import java.io.Serializable;

public class ProfileExportDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2223282617564315037L;

	private String keyProfileID;
	
	private String keyProfileVersion;
	
	private String tranProfileID;
	
	private String tranProfileVersion;
	
	private String exportXmlFile;

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

	public String getTranProfileID() {
		return tranProfileID;
	}

	public void setTranProfileID(String tranProfileID) {
		this.tranProfileID = tranProfileID;
	}

	public String getTranProfileVersion() {
		return tranProfileVersion;
	}

	public void setTranProfileVersion(String tranProfileVersion) {
		this.tranProfileVersion = tranProfileVersion;
	}

	public String getExportXmlFile() {
		return exportXmlFile;
	}

	public void setExportXmlFile(String exportXmlFile) {
		this.exportXmlFile = exportXmlFile;
	}	
	
	public boolean isWrappable(){
		if(this.tranProfileID == null || this.tranProfileVersion == null){
			return false;
		}else{
			return true;
		}
	}

	@Override
	public String toString() {
		return "ProfileExportDTO [keyProfileID=" + keyProfileID
				+ ", keyProfileVersion=" + keyProfileVersion
				+ ", tranProfileID=" + tranProfileID + ", tranProfileVersion="
				+ tranProfileVersion + "]";
	}
	
}
