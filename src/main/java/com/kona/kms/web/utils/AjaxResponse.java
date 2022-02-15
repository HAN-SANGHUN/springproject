package com.kona.kms.web.utils;

import java.io.Serializable;

import com.kona.kms.web.profile.model.KeyValueDTO;

public class AjaxResponse implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8190170128458805431L;

	/** 성공 여부 */
	private String status;
	
	/** 에러 코드 */
	private String code;
	
	/** 에러 메시지 */
	private String message;

	private ProfileIDAttr profileAttr;
	
	private KeyValueDTO keyValue;
	
	private int hsmNo;
	
	private String certificateUID;
	
	private String profileID;
	
	private String kcv;
	
	public AjaxResponse(String status){
		this.status = status;
	}
	
	public AjaxResponse(String status, String message){
		this.status = status;
		this.message = message;
	}
	
	public AjaxResponse(String status, String code, String message){
		this.status = status;
		this.code = code;
		this.message = message;		
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public ProfileIDAttr getProfileAttr() {
		return profileAttr;
	}

	public void setProfileAttr(ProfileIDAttr profileAttr) {
		this.profileAttr = profileAttr;
	}

	public KeyValueDTO getKeyValue() {
		return keyValue;
	}

	public void setKeyValue(KeyValueDTO keyValue) {
		this.keyValue = keyValue;
	}

	public int getHsmNo() {
		return hsmNo;
	}

	public void setHsmNo(int hsmNo) {
		this.hsmNo = hsmNo;
	}

	public String getCertificateUID() {
		return certificateUID;
	}

	public void setCertificateUID(String certificateUID) {
		this.certificateUID = certificateUID;
	}

	public String getProfileID() {
		return profileID;
	}

	public void setProfileID(String profileID) {
		this.profileID = profileID;
	}

	public String getKcv() {
		return kcv;
	}

	public void setKcv(String kcv) {
		this.kcv = kcv;
	}
	
}
