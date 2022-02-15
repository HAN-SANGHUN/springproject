/**
 * 
 */
package com.kona.kms.token.model;

import java.io.Serializable;
import java.util.Date;

/**
 * @author bizais
 *
 */
public class PTokenModel implements Serializable {	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2765361355180289063L;

	private long slotIndex;
	
	private int hsmNo;
	
	private long slotNo;
	
	private String slotStatusCode;
	
	private long tokenNo;
	
	private String registrationUserID;
	
	private Date registrationDate;
	
	private String updateUserID;
	
	private Date updateDate;

	
	/**
	 * @return the slotIndex
	 */
	public long getSlotIndex() {
		return slotIndex;
	}

	/**
	 * @param slotIndex the slotIndex to set
	 */
	public void setSlotIndex(long slotIndex) {
		this.slotIndex = slotIndex;
	}

	/**
	 * @return the hsmNo
	 */
	public int getHsmNo() {
		return hsmNo;
	}

	/**
	 * @param hsmNo the hsmNo to set
	 */
	public void setHsmNo(int hsmNo) {
		this.hsmNo = hsmNo;
	}

	/**
	 * @return the slotNo
	 */
	public long getSlotNo() {
		return this.slotNo;
	}

	/**
	 * @param slotNo the slotNo to set
	 */
	public void setSlotNo(long slotNo) {
		this.slotNo = slotNo;
	}

	/**
	 * @return the slotStatusCode
	 */
	public String getSlotStatusCode() {
		return slotStatusCode;
	}

	/**
	 * @param slotStatusCode the slotStatusCode to set
	 */
	public void setSlotStatusCode(String slotStatusCode) {
		this.slotStatusCode = slotStatusCode;
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

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "PTokenModel [hsmNo=" + hsmNo + ", slotNo=" + slotNo
				+ ", slotStatusCode=" + slotStatusCode + ", tokenNo=" + tokenNo
				+ ", registrationUserID=" + registrationUserID
				+ ", registrationDate=" + registrationDate + ", updateUserID="
				+ updateUserID + ", updateDate=" + updateDate + "]";
	}
}
