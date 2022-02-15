package com.kona.kms.web.ss.hsm.model;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.kona.kms.web.utils.PageableModel;

public class HsmMetaModel extends PageableModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8101344054015373045L;
	
	/* Physical HSM No. */
	private int hsmNo;
	
	private String hsmLabel;
	
	/* Logical HSM No. lhsmNo:hsmNo = 1 : N Relationship */
	private int lhsmNo;
	
	private int phsmCount;

	private long slotCount;
	
	private int slotStartNum;
	
	private int slotEndNum;
	
	private int tokenAllocationNum;
	
	private String ipAddress;
	
	private String port;
	
	private String statusCode;
	
	private String StatusDescription;
	
	private String description;
	
	private String primaryCode;
	
	private String registrationUserId;
	
	private Date registrationDate;
	
	private String updateUserId;
	
	private Date updateDate;	
	
	public HsmMetaModel() {
		
	}
	
	public int getHsmNo() {
		return hsmNo;
	}

	public void setHsmNo(int hsmNo) {
		this.hsmNo = hsmNo;
	}

	public String getHsmLabel() {
		return hsmLabel;
	}

	public void setHsmLabel(String hsmLabel) {
		this.hsmLabel = hsmLabel;
	}

	public int getLHsmNo() {
		return lhsmNo;
	}

	public void setLHsmNo(int lhsmNo) {
		this.lhsmNo = lhsmNo;
	}

	public int getPHsmCount() {
		return phsmCount;
	}

	public void setPHsmCount(int phsmCount) {
		this.phsmCount = phsmCount;
	}
	
	
	public long getSlotCount() {
		return slotCount;
	}

	public void setSlotCount(long slotCount) {
		this.slotCount = slotCount;
	}

	public int getSlotStartNum() {
		return slotStartNum;
	}

	public void setSlotStartNum(int slotStartNum) {
		this.slotStartNum = slotStartNum;
	}

	public int getSlotEndNum() {
		return slotEndNum;
	}

	public void setSlotEndNum(int slotEndNum) {
		this.slotEndNum = slotEndNum;
	}	

	public int getTokenAllocationNum() {
		return tokenAllocationNum;
	}

	public void setTokenAllocationNum(int tokenAllocationNum) {
		this.tokenAllocationNum = tokenAllocationNum;
	}

	public String getIpAddress() {
		return ipAddress;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

	public String getPort() {
		return port;
	}

	public void setPort(String port) {
		this.port = port;
	}

	public String getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public String getPrimaryCode() {
		return primaryCode;
	}

	public void setPrimaryCode(String primaryCode) {
		this.primaryCode = primaryCode;
	}

	public String getRegistrationUserId() {
		return registrationUserId;
	}

	public void setRegistrationUserId(String registrationUserId) {
		this.registrationUserId = registrationUserId;
	}

	public Date getRegistrationDate() {
		return registrationDate;
	}
	
	public String getRegistrationDateStr(){
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		
		return formatter.format(this.registrationDate);
	}

	public void setRegistrationDate(Date registrationDate) {
		this.registrationDate = registrationDate;
	}

	public String getUpdateUserId() {
		return updateUserId;
	}

	public void setUpdateUserId(String updateUserId) {
		this.updateUserId = updateUserId;
	}

	public Date getUpdateDate() {
		return updateDate;
	}
	
	public String getUpdateDateStr(){
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm");
		
		return formatter.format(this.updateDate);
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	
	public void setLHSMInitValue(HsmMetaModel pmodel){
		this.hsmLabel = "L-" + pmodel.getHsmLabel();	// lhsm_label
		this.slotCount = pmodel.getSlotCount();			// ltoken_cnt
		this.slotStartNum = 0;							// ltoken_start_no
		this.slotEndNum = (int) (this.slotCount -1);	// ltokenEndNo
		this.statusCode = "Active";						// lhsm_stats_cd
	}
	
	
	
	@Override
	public String toString() {
		return "HsmMetaModel [hsmNo=" + hsmNo + ", hsmLabel=" + hsmLabel
				+ ", slotCount=" + slotCount + ", slotStartNum=" + slotStartNum
				+ ", slotEndNum=" + slotEndNum + ", ipAddress=" + ipAddress
				+ ", port=" + port + ", statusCode=" + statusCode
				+ ", description=" + description + ", registrationUserId="
				+ registrationUserId + ", registrationDate=" + registrationDate
				+ ", updateUserId=" + updateUserId + ", updateDate="
				+ updateDate + "]";
	}

	
}
