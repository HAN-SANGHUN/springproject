package com.kona.kms.web.ss.token.model;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.kona.kms.web.utils.PageableModel;

public class TokenModel extends PageableModel implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2489308889760863020L;
	
	private String hsmLabel;
	
	private long tokenNo;
	
	private String tokenLabel;
	
	private int lhsmNo;
	
	private String tokenPin;
	
	private String oldTokenPin;
	
	private String companyID;
	
	private String companyName;
	
	private String description;
	
	private String registrationUserID;
	
	private Date registrationDate;
	
	private String updateUserID;
	
	private Date updateDate;
	
	private List<SlotModel> slotInfos;
	
	private static SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

	private int slotNo;
	
	
	public String getHsmLabel() {
		return hsmLabel;
	}

	public void setHsmLabel(String hsmLabel) {
		this.hsmLabel = hsmLabel;
	}

	public long getTokenNo() {
		return tokenNo;
	}

	public void setTokenNo(long tokenNo) {
		this.tokenNo = tokenNo;
	}

	public String getTokenLabel() {
		return tokenLabel;
	}

	public void setTokenLabel(String tokenLabel) {
		this.tokenLabel = tokenLabel;
	}	

	public int getLhsmNo() {
		return lhsmNo;
	}

	public void setLhsmNo(int lhsmNo) {
		this.lhsmNo = lhsmNo;
	}

	public String getTokenPin() {
		return tokenPin;
	}

	public void setTokenPin(String tokenPin) {
		this.tokenPin = tokenPin;
	}	

	public String getOldTokenPin() {
		return oldTokenPin;
	}

	public void setOldTokenPin(String oldTokenPin) {
		this.oldTokenPin = oldTokenPin;
	}

	public String getCompanyID() {
		return companyID;
	}

	public void setCompanyID(String companyId) {
		this.companyID = companyId;
	}	

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
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
	
	public String getRegistrationDateStr(){
		if(this.registrationDate == null) return "";
		return formatter.format(this.registrationDate);
	}

	public String getUpdateUserID() {
		return updateUserID;
	}

	public void setUpdateUserID(String updateUserId) {
		this.updateUserID = updateUserId;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}	

	public List<SlotModel> getSlotInfos() {
		return slotInfos;
	}

	public void setSlotInfos(List<SlotModel> slotInfos) {
		this.slotInfos = slotInfos;
	}
	
	public boolean isTokenPinChanged(){
		return (this.tokenPin.equals(this.oldTokenPin)) ? false : true;
	}
	
	public int getSlotNo() {
		return slotNo;
	}

	public void setSlotNo(int slotNo) {
		this.slotNo = slotNo;
	}

	@Override
	public String toString() {
		return "TokenModel [tokenNo=" + tokenNo + ", tokenLabel=" + tokenLabel
				+ ", tokenPin=" + tokenPin + ", companyID=" + companyID
				+ ", companyName=" + companyName
				+ ", description=" + description + ", registrationUserID="
				+ registrationUserID + ", registrationDate=" + registrationDate
				+ ", updateUserID=" + updateUserID + ", updateDate="
				+ updateDate + ", slotNo=" + slotNo + "]";
	}
	
}
