package com.kona.kms.web.ss.token.model;

import java.io.Serializable;
import java.util.Date;

import com.kona.kms.web.utils.PageableModel;

public class SlotModel extends PageableModel implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2489308889760863020L;
	
	private int slotIndex;
	
	private long tokenNo;
	
	private String hsmNo;
	
	private String hsmLabel;
	
	private String slotNo;
	
	private String slotStatusCode;
	
	private String registrationUserID;
	
	private Date registrationDate;
	
	private String updateUserID;
	
	private Date updateDate;

	public int getSlotIndex() {
		return slotIndex;
	}

	public void setSlotIndex(int slotIndex) {
		this.slotIndex = slotIndex;
	}

	public long getTokenNo() {
		return tokenNo;
	}

	public void setTokenNo(long tokenNo) {
		this.tokenNo = tokenNo;
	}	

	public String getHsmNo() {
		return hsmNo;
	}

	public void setHsmNo(String hsmNo) {
		this.hsmNo = hsmNo;
	}	

	public String getHsmLabel() {
		return hsmLabel;
	}

	public void setHsmLabel(String hsmLabel) {
		this.hsmLabel = hsmLabel;
	}

	public String getSlotNo() {
		return slotNo;
	}

	public void setSlotNo(String slotNo) {
		this.slotNo = slotNo;
	}

	public String getSlotStatusCode() {
		return slotStatusCode;
	}

	public void setSlotStatusCode(String slotStatusCode) {
		this.slotStatusCode = slotStatusCode;
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
		return "SlotModel [slotIndex=" + slotIndex + ", tokenNo=" + tokenNo
				+ ", HsmNo=" + hsmNo + ", slotNo=" + slotNo
				+ ", slotStatusCode=" + slotStatusCode
				+ ", registrationUserID=" + registrationUserID
				+ ", registrationDate=" + registrationDate + ", updateUserID="
				+ updateUserID + ", updateDate=" + updateDate + "]";
	}
	
}
