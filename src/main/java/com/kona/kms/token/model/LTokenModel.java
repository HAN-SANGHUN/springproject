/**
 * 
 */
package com.kona.kms.token.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author bizais
 *
 */
public class LTokenModel implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1917910991005205408L;
	private long 		tokenNo;
	private String 		tokenLabel;
	private String 		companyID;
	private int 			lhsmNo;
	private String 		tokenPin;
	private int 			accessNo = 0;
	private String 		description;
	private String 		registrationUserID;
	private Date 		registrationDate;
	private String 		updateUserID;
	private Date 		updateDate;
	private List<PTokenModel> ptokenModel;

	public LTokenModel(){
		
	}
	/**
	 * @return the tokenNo
	 */
	public long getTokenNo() {
		return tokenNo;
	}

	/**
	 * @param tokenNo the tokenNo to set
	 */
	public void setTokenNo(long tokenNo) {
		this.tokenNo = tokenNo;
	}

	/**
	 * @return the tokenLabel
	 */
	public String getTokenLabel() {
		return tokenLabel;
	}

	/**
	 * @param tokenLabel the tokenLabel to set
	 */
	public void setTokenLabel(String tokenLabel) {
		this.tokenLabel = tokenLabel;
	}

	/**
	 * @return the companyID
	 */
	public String getCompanyID() {
		return companyID;
	}

	/**
	 * @param companyID the companyID to set
	 */
	public void setCompanyID(String companyID) {
		this.companyID = companyID;
	}

	/**
	 * @return the lhsmNo
	 */
	public int getLhsmNo() {
		return lhsmNo;
	}

	/**
	 * @param lhsmNo the lhsmNo to set
	 */
	public void setLhsmNo(int lhsmNo) {
		this.lhsmNo = lhsmNo;
	}

	/**
	 * @return the tokenPin
	 */
	public String getTokenPin() {
		return tokenPin;
	}

	/**
	 * @param tokenPin the tokenPin to set
	 */
	public void setTokenPin(String tokenPin) {
		this.tokenPin = tokenPin;
	}

	/**
	 * @return the accessNo
	 */
	public int getAccessNo() {
		return accessNo;
	}

	/**
	 * @param accessNo the accessNo to set
	 */
	public synchronized void setAccessNo(int accessNo) {
		
		this.accessNo = accessNo;
	}
	
	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the registrationUserID
	 */
	public String getRegistrationUserID() {
		return registrationUserID;
	}

	/**
	 * @param registrationUserID the registrationUserID to set
	 */
	public void setRegistrationUserID(String registrationUserID) {
		this.registrationUserID = registrationUserID;
	}

	/**
	 * @return the registrationDate
	 */
	public Date getRegistrationDate() {
		return registrationDate;
	}

	/**
	 * @param registrationDate the registrationDate to set
	 */
	public void setRegistrationDate(Date registrationDate) {
		this.registrationDate = registrationDate;
	}

	/**
	 * @return the updateUserID
	 */
	public String getUpdateUserID() {
		return updateUserID;
	}

	/**
	 * @param updateUserID the updateUserID to set
	 */
	public void setUpdateUserID(String updateUserID) {
		this.updateUserID = updateUserID;
	}

	/**
	 * @return the updateDate
	 */
	public Date getUpdateDate() {
		return updateDate;
	}

	/**
	 * @param updateDate the updateDate to set
	 */
	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	/**
	 * @return the ptokenInfo
	 */
	public List<PTokenModel> getPtokenModel() {
		return ptokenModel;
	}

	/**
	 * @param ptokenInfo the ptokenInfo to set
	 */
	public void setPtokenModel(List<PTokenModel> ptokenModel) {
		this.ptokenModel = ptokenModel;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "LTokenModel [tokenNo=" + tokenNo + ", tokenLabel=" + tokenLabel
				+ ", companyID=" + companyID + ", lhsmNo=" + lhsmNo
				+ ", tokenPin=" + tokenPin + ", description=" + description
				+ ", registrationUserID=" + registrationUserID
				+ ", registrationDate=" + registrationDate + ", updateUserID="
				+ updateUserID + ", updateDate=" + updateDate + "]";
	}
	
	
}
