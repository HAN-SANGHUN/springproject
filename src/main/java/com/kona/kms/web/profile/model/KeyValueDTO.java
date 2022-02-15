package com.kona.kms.web.profile.model;

import java.io.Serializable;

public class KeyValueDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5255595735211146074L;
	
	private String profileID;
	
	private String profileVersion;
	
	private String keySubject;
	
	private String combinedComponent;
	
	private String component1;
	
	private String component2;
	
	private String component3;
	
	private String combinedKcv;
	
	private String kcv1;
	
	private String kcv2;
	
	private String kcv3;
	
	private String crtp;
	
	private String crtq;
	
	private String crtdp1;
	
	private String crtdq1;
	
	private String crtpq1;
	
	private String modExp;
	
	private String mod;
	
	private String exp;

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

	public String getKeySubject() {
		return keySubject;
	}

	public void setKeySubject(String keySubject) {
		this.keySubject = keySubject;
	}

	public String getCombinedComponent() {
		return combinedComponent;
	}

	public void setCombinedComponent(String combinedComponent) {
		this.combinedComponent = combinedComponent;
	}

	public String getComponent1() {
		return component1;
	}

	public void setComponent1(String component1) {
		this.component1 = component1;
	}

	public String getComponent2() {
		return component2;
	}

	public void setComponent2(String component2) {
		this.component2 = component2;
	}

	public String getComponent3() {
		return component3;
	}

	public void setComponent3(String component3) {
		this.component3 = component3;
	}

	public String getCombinedKcv() {
		return combinedKcv;
	}

	public void setCombinedKcv(String combinedKcv) {
		this.combinedKcv = combinedKcv;
	}

	public String getKcv1() {
		return kcv1;
	}

	public void setKcv1(String kcv1) {
		this.kcv1 = kcv1;
	}

	public String getKcv2() {
		return kcv2;
	}

	public void setKcv2(String kcv2) {
		this.kcv2 = kcv2;
	}

	public String getKcv3() {
		return kcv3;
	}

	public void setKcv3(String kcv3) {
		this.kcv3 = kcv3;
	}	

	public String getCrtp() {
		return crtp;
	}

	public void setCrtp(String crtp) {
		this.crtp = crtp;
	}

	public String getCrtq() {
		return crtq;
	}

	public void setCrtq(String crtq) {
		this.crtq = crtq;
	}

	public String getCrtdp1() {
		return crtdp1;
	}

	public void setCrtdp1(String crtdp1) {
		this.crtdp1 = crtdp1;
	}

	public String getCrtdq1() {
		return crtdq1;
	}

	public void setCrtdq1(String crtdq1) {
		this.crtdq1 = crtdq1;
	}

	public String getCrtpq1() {
		return crtpq1;
	}

	public void setCrtpq1(String crtpq1) {
		this.crtpq1 = crtpq1;
	}	
	
	public String getModExp() {
		return modExp;
	}

	public void setModExp(String modExp) {
		this.modExp = modExp;
	}

	public String getMod() {
		return mod;
	}

	public void setMod(String mod) {
		this.mod = mod;
	}

	public String getExp() {
		return exp;
	}

	public void setExp(String exp) {
		this.exp = exp;
	}

	public String[] getComponents(){
		String[] values = new String[3];
		
		values[0] = this.component1;
		values[1] = this.component2;
		values[2] = this.component3;
		
		return values;
	}

	@Override
	public String toString() {
		return "KeyValueDTO [profileID=" + profileID + ", profileVersion="
				+ profileVersion + ", keySubject=" + keySubject
				+ ", combinedComponent=" + combinedComponent + ", component1="
				+ component1 + ", component2=" + component2 + ", component3="
				+ component3 + ", combinedKcv=" + combinedKcv + ", kcv1="
				+ kcv1 + ", kcv2=" + kcv2 + ", kcv3=" + kcv3 + ", crtp=" + crtp
				+ ", crtq=" + crtq + ", crtdp1=" + crtdp1 + ", crtdq1="
				+ crtdq1 + ", crtpq1=" + crtpq1 + ", modExp=" + modExp
				+ ", mod=" + mod + ", exp=" + exp + "]";
	}	
}
