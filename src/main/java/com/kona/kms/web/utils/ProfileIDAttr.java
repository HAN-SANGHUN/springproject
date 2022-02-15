package com.kona.kms.web.utils;

import java.io.Serializable;

public class ProfileIDAttr implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1555339489771149689L;
	
	private boolean isPairkey;
	
	private String profileID;
	
	private String profileVersion;
	
	private String pubProfileID;
	
	private String pubProfileVersion;
	
	public ProfileIDAttr(boolean isPairkey, String profileID, String profileVersion, String pubProfileID, String pubProfileVersion){
		this.isPairkey = isPairkey;
		this.profileID = profileID;
		this.profileVersion = profileVersion;
		this.pubProfileID = pubProfileID;
		this.pubProfileVersion = pubProfileVersion;		
	}

	public boolean isPairkey() {
		return isPairkey;
	}

	public void setPairkey(boolean isPairkey) {
		this.isPairkey = isPairkey;
	}

	public String getProfileID() {
		return profileID;
	}

	public void setProfileID(String profileID) {
		this.profileID = profileID;
	}

	public String getProfileVersion() {
		return profileVersion;
	}

	public void setProfileVersion(String profileVersion) {
		this.profileVersion = profileVersion;
	}

	public String getPubProfileID() {
		return pubProfileID;
	}

	public void setPubProfileID(String pubProfileID) {
		this.pubProfileID = pubProfileID;
	}

	public String getPubProfileVersion() {
		return pubProfileVersion;
	}

	public void setPubProfileVersion(String pubProfileVersion) {
		this.pubProfileVersion = pubProfileVersion;
	}

	@Override
	public String toString() {
		return "ProfileIDAttr [isPairkey=" + isPairkey + ", profileID="
				+ profileID + ", profileVersion=" + profileVersion
				+ ", pubProfileID=" + pubProfileID + ", pubProfileVersion="
				+ pubProfileVersion + "]";
	}

}
