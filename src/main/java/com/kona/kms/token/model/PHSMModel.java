/**
 * 
 */
package com.kona.kms.token.model;

import java.io.Serializable;
import java.util.Date;
import sun.security.pkcs11.wrapper.PKCS11;


/**
 * @author bizais
 *
 */
public class PHSMModel implements Serializable {

	private static final long serialVersionUID = 160654624132729413L;
	private int hsmNo;
	private String hsmLabel;
	private PKCS11 pkcs11;
	private	int lhsmNo;
	private long slotCount;
	private long slotStartNum;
	private long slotEndNum;
	private String ipAddress;
	private int port;
	private String statusCode;
	private String description;
	private String primaryCode;
	private String registrationUserId;
	private Date registrationDate;
	private String updateUserId;
	private Date updateDate;

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
	 * @return the hsmLabel
	 */
	public String getHsmLabel() {
		return hsmLabel;
	}

	/**
	 * @param hsmLabel the hsmLabel to set
	 */
	public void setHsmLabel(String hsmLabel) {
		this.hsmLabel = hsmLabel;
	}

	/**
	 * @return the hsmLabel
	 */
	public PKCS11 getPkcs11() {
		return pkcs11;
	}

	/**
	 * @param hsmLabel the hsmLabel to set
	 */
	public void setPkcs11(PKCS11 pkcs11) {
		this.pkcs11 = pkcs11;
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
	 * @return the slotCount
	 */
	public long getSlotCount() {
		return slotCount;
	}

	/**
	 * @param slotCount the slotCount to set
	 */
	public void setSlotCount(long slotCount) {
		this.slotCount = slotCount;
	}

	/**
	 * @return the slotStartNum
	 */
	public long getSlotStartNum() {
		return slotStartNum;
	}

	/**
	 * @param slotStartNum the slotStartNum to set
	 */
	public void setSlotStartNum(long slotStartNum) {
		this.slotStartNum = slotStartNum;
	}

	/**
	 * @return the slotEndNum
	 */
	public long getSlotEndNum() {
		return slotEndNum;
	}

	/**
	 * @param slotEndNum the slotEndNum to set
	 */
	public void setSlotEndNum(long slotEndNum) {
		this.slotEndNum = slotEndNum;
	}

	/**
	 * @return the ipAddress
	 */
	public String getIpAddress() {
		return ipAddress;
	}

	/**
	 * @param ipAddress the ipAddress to set
	 */
	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

	/**
	 * @return the port
	 */
	public int getPort() {
		return port;
	}

	/**
	 * @param port the port to set
	 */
	public void setPort(int port) {
		this.port = port;
	}

	/**
	 * @return the statusCode
	 */
	public String getStatusCode() {
		return statusCode;
	}

	/**
	 * @param statusCode the statusCode to set
	 */
	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
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
	 * @return the primaryCode
	 */
	public String getPrimaryCode() {
		return primaryCode;
	}

	/**
	 * @param primaryCode the primaryCode to set
	 */
	public void setPrimaryCode(String primaryCode) {
		this.primaryCode = primaryCode;
	}

	/**
	 * @return the registrationUserId
	 */
	public String getRegistrationUserId() {
		return registrationUserId;
	}

	/**
	 * @param registrationUserId the registrationUserId to set
	 */
	public void setRegistrationUserId(String registrationUserId) {
		this.registrationUserId = registrationUserId;
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
	 * @return the updateUserId
	 */
	public String getUpdateUserId() {
		return updateUserId;
	}

	/**
	 * @param updateUserId the updateUserId to set
	 */
	public void setUpdateUserId(String updateUserId) {
		this.updateUserId = updateUserId;
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
		return "PHSMModel [hsmNo=" + hsmNo + ", hsmLabel=" + hsmLabel
				+ ", lhsmNo=" + lhsmNo + ", slotCount=" + slotCount
				+ ", slotStartNum=" + slotStartNum + ", slotEndNum="
				+ slotEndNum + ", ipAddress=" + ipAddress + ", port=" + port
				+ ", statusCode=" + statusCode + ", description=" + description
				+ ", primaryCode=" + primaryCode + ", registrationUserId="
				+ registrationUserId + ", registrationDate=" + registrationDate
				+ ", updateUserId=" + updateUserId + ", updateDate="
				+ updateDate + "]";
	}	

}
