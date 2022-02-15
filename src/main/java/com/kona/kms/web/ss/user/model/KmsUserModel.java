package com.kona.kms.web.ss.user.model;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.kona.kms.web.utils.PageableModel;

public class KmsUserModel extends PageableModel implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -9077579146072420191L;
	
	///////////////////////////////////////////////////////////////////
	// Grid Search Specific Data
	///////////////////////////////////////////////////////////////////
	
	private String hsmLabel;
	
	private String tokenLabel;
	
	///////////////////////////////////////////////////////////////////
	
	private String userId;
	
	private String userPwd;
	
	private String companyId;
	
	private String companyName;
	
	private String activeStatusCode;
	
	private String acountStatusCode;
	
	private String userRoleCode;	
	
	private String userName;
	
	private String email;
	
	private String officeTelNum;
	
	private String cellphoneNum;
	
	private String address;
	
	private String department;
	
	private String designation;
	
	private String description;
	
	private String loginIPAddress;
	
	private String registrationUserId;
	
	private Date registrationDate;
	
	private String updateUserId;
	
	private Date updateDate;
	

	public KmsUserModel(){
		
	}	
	
	public String getTokenLabel() {
		return tokenLabel;
	}

	public void setTokenLabel(String tokenLabel) {
		this.tokenLabel = tokenLabel;
	}

	public String getHsmLabel() {
		return hsmLabel;
	}

	public void setHsmLabel(String hsmLabel) {
		this.hsmLabel = hsmLabel;
	}

	public String getUserRoleCode() {
		return userRoleCode;
	}

	public void setUserRoleCode(String userRoleCode) {
		this.userRoleCode = userRoleCode;
	}
	

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserPwd() {
		return userPwd;
	}

	public void setUserPwd(String userPwd) {
		this.userPwd = userPwd;
	}

	public String getCompanyId() {
		return companyId;
	}

	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}

	

	public String getActiveStatusCode() {
		return activeStatusCode;
	}

	public void setActiveStatusCode(String activeStatusCode) {
		this.activeStatusCode = activeStatusCode;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getOfficeTelNum() {
		return officeTelNum;
	}

	public void setOfficeTelNum(String officeTelNum) {
		this.officeTelNum = officeTelNum;
	}

	public String getCellphoneNum() {
		return cellphoneNum;
	}

	public void setCellphoneNum(String cellphoneNum) {
		this.cellphoneNum = cellphoneNum;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getLoginIPAddress() {
		return loginIPAddress;
	}

	public void setLoginIPAddress(String loginIPAddress) {
		this.loginIPAddress = loginIPAddress;
	}

	public String getAcountStatusCode() {
		return acountStatusCode;
	}

	public void setAcountStatusCode(String acountStatusCode) {
		this.acountStatusCode = acountStatusCode;
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
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm");
		
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

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}
	
	public String getUpdateDateStr(){
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm");
		
		return formatter.format(this.updateDate);
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	
	public String getDescriptionWeb(){
		
		if(this.description != null)
			return this.description.replace("\n", "</br>");
		else return null;
	}

	@Override
	public String toString() {
		return "KmsUserModel [hsmLabel=" + hsmLabel + ", tokenLabel="
				+ tokenLabel + ", userId=" + userId + ", userPwd=" + userPwd
				+ ", companyId=" + companyId + ", companyName=" + companyName
				+ ", activeStatusCode=" + activeStatusCode
				+ ", acountStatusCode=" + acountStatusCode + ", userRoleCode="
				+ userRoleCode + ", userName=" + userName + ", email=" + email
				+ ", officeTelNum=" + officeTelNum + ", cellphoneNum="
				+ cellphoneNum + ", address=" + address + ", department="
				+ department + ", designation=" + designation
				+ ", description=" + description + ", loginIPAddress="
				+ loginIPAddress + ", registrationUserId=" + registrationUserId
				+ ", registrationDate=" + registrationDate + ", updateUserId="
				+ updateUserId + ", updateDate=" + updateDate + "]";
	}	

}
