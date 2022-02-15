package com.kona.kms.web.ss.company.model;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.kona.kms.web.utils.PageableModel;

public class CompanyModel extends PageableModel implements Serializable{

	private static final long serialVersionUID = 2308571553360388785L;
	
	private String hsmLabel;
	
	private String tokenLabel;
	
	private String companyId;
	
	private String companyName;
	
	private String companyTypeCode;
	
	private String companyTypeCodeName;
	
	private String countryCode;
	
	private String countryCodeName;
	
	private String fax;
	
	private String email;
	
	private String homepageUrl;
	
	private String address;
	
	private String bizLicenseNo;
	
	private String officeTelNum;
	
	private String registrationUserId;
	
	private Date registrationDate;
	
	private String updateUserId;
	
	private Date updateDate;
	
	private String companyDescription;
	
	private String companyStatusCode;
	
	private String companyStatusName;

	
	private String companyStatusDescription;
	
	public CompanyModel(){
		
	}	
	

	public String getHsmLabel() {
		return hsmLabel;
	}

	public void setHsmLabel(String hsmLabel) {
		this.hsmLabel = hsmLabel;
	}

	public String getTokenLabel() {
		return tokenLabel;
	}

	public void setTokenLabel(String tokenLabel) {
		this.tokenLabel = tokenLabel;
	}

	public String getCompanyId() {
		return companyId;
	}

	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getCompanyTypeCode() {
		return companyTypeCode;
	}

	public void setCompanyTypeCode(String companyTypeCode) {
		this.companyTypeCode = companyTypeCode;
	}

	public String getCompanyTypeCodeName() {
		return companyTypeCodeName;
	}

	public void setCompanyTypeCodeName(String companyTypeCodeName) {
		this.companyTypeCodeName = companyTypeCodeName;
	}

	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	public String getCountryCodeName() {
		return countryCodeName;
	}

	public void setCountryCodeName(String countryCodeName) {
		this.countryCodeName = countryCodeName;
	}

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getHomepageUrl() {
		return homepageUrl;
	}

	public void setHomepageUrl(String homepageUrl) {
		this.homepageUrl = homepageUrl;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getBizLicenseNo() {
		return bizLicenseNo;
	}

	public void setBizLicenseNo(String bizLicenseNo) {
		this.bizLicenseNo = bizLicenseNo;
	}

	public String getOfficeTelNum() {
		return officeTelNum;
	}

	public void setOfficeTelNum(String officeTelNum) {
		this.officeTelNum = officeTelNum;
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
	
	public String getUpdateDateStr(){
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm");
		
		return formatter.format(this.updateDate);
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}


	public String getCompanyStatusCode() {
		return companyStatusCode;
	}


	public void setCompanyStatusCode(String companyStatusCode) {
		this.companyStatusCode = companyStatusCode;
	}


	public String getCompanyStatusName() {
		return companyStatusName;
	}


	public void setCompanyStatusName(String companyStatusName) {
		this.companyStatusName = companyStatusName;
	}


	public String getCompanyDescription() {
		return companyDescription;
	}


	public void setCompanyDescription(String companyDescription) {
		this.companyDescription = companyDescription;
	}


	public String getCompanyStatusDescription() {
		return companyStatusDescription;
	}


	public void setCompanyStatusDescription(String companyStatusDescription) {
		this.companyStatusDescription = companyStatusDescription;
	}


	@Override
	public String toString() {
		return "CompanyModel [companyId=" + companyId + ", companyName="
				+ companyName + ", companyTypeCode=" + companyTypeCode
				+ ", countryCode=" + countryCode + ", fax=" + fax + ", email="
				+ email + ", homepageUrl=" + homepageUrl + ", address="
				+ address + ", bizLicenseNo=" + bizLicenseNo
				+ ", officeTelNum=" + officeTelNum + ", registrationUserId="
				+ registrationUserId + ", registrationDate=" + registrationDate
				+ ", updateUserId=" + updateUserId + ", updateDate="
				+ updateDate + "]";
	}
	

}
