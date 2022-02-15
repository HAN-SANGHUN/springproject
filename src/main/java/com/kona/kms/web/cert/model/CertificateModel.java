package com.kona.kms.web.cert.model;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.kona.kms.utils.Util;
import com.kona.kms.web.cert.BrandType;
import com.kona.kms.web.utils.PageableModel;

public class CertificateModel extends PageableModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5544893209180064832L;
	
	private String certificateUID;
	
	private String certificateName;
	
	private String companyID;
	
	private String companyName;	
	
	private String brandTypeCode;
	
	private int brandTypeInt;
	
	private String binNumber;
	
	private String trackingNumber;
	
	private int ipkIndex;
	
	private int ipkSize;
	
	private Date requestDate;
	
	private Date expireDate;	
	
	private String keySubject;
	
	private String keyProfileID;
	
	private String keyProfileVersion;
	
	private String keyProfileName;	
	
	private String certiReqFileName;
	
	private byte[] certiReqBinaryFile;	
	
	private String exponentValue;
	
	private String hashValueFileName;
	
	private String hashValue;
	
	private String modulusSize;	
	
	private String certiResFileName;
	
	private byte[] certiResBinaryFile;
	
	private String caSerialNumber;
	
	private String caPublicKeyIndex;
	
	private String caPublicKeyLength;
	
	private Date registrationDate;
	
	private String registrationUserID;
	
	private Date updateDate;
	
	private String updateUserID;
	
	private String binStatusCode;
	
	private String reqSendStatusCode;
	
	private String resSendStatusCode;
	
	private String amexFileType;
	
	///////////////////////////////////////////////////////////////////
	// Grid Search Specific Data
	///////////////////////////////////////////////////////////////////
	
	private String periodType;
	
	private String startDate;
	
	private String endDate;
	
	private static SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

	public String getCertificateUID() {
		return certificateUID;
	}

	public void setCertificateUID(String certificateUID) {
		this.certificateUID = certificateUID;
	}

	public String getCompanyID() {
		return companyID;
	}

	public String getCertificateName() {
		return certificateName;
	}

	public void setCertificateName(String certificateName) {
		this.certificateName = certificateName;
	}

	public void setCompanyID(String companyID) {
		this.companyID = companyID;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	
	public String getBrandTypeCode() {
		return brandTypeCode;
	}

	public void setBrandTypeCode(String brandTypeCode) {
		this.brandTypeCode = brandTypeCode;
	}

	public int getBrandTypeInt() {
		return brandTypeInt;
	}

	public void setBrandTypeInt(int brandTypeInt) {
		this.brandTypeInt = brandTypeInt;
	}

	public String getBinNumber() {
		return binNumber;
	}

	public void setBinNumber(String binNumber) {
		this.binNumber = binNumber;
	}

	public String getTrackingNumber() {
		return trackingNumber;
	}

	public void setTrackingNumber(String trackingNumber) {
		this.trackingNumber = trackingNumber;
	}

	public int getIpkIndex() {
		return ipkIndex;
	}

	public void setIpkIndex(int ipkIndex) {
		this.ipkIndex = ipkIndex;
	}

	public int getIpkSize() {
		return ipkSize;
	}

	public void setIpkSize(int ipkSize) {
		this.ipkSize = ipkSize;
	}

	public Date getRequestDate() {
		return requestDate;
	}

	public void setRequestDate(Date requestDate) {
		this.requestDate = requestDate;
	}
	
	public void setRequestDateStr(String date) throws ParseException{
		
		if(date != null && !"".equals(date)){
			this.requestDate = formatter.parse(date);
		}		
	}
	
	public String getRequestDateStr(){
		if(this.requestDate == null) return "";
		return formatter.format(this.requestDate);
	}

	public Date getExpireDate() {
		return expireDate;
	}

	public void setExpireDate(Date expireDate) {
		this.expireDate = expireDate;
	}
	
	public void setExpireDateStr(String date) throws ParseException{
		
		if(date != null && !"".equals(date)){
			this.expireDate = formatter.parse(date);
		}		
	}
	
	public String getExpireDateStr(){
		if(this.expireDate == null) return "";
		return formatter.format(this.expireDate);
	}	

	public String getKeySubject() {
		return keySubject;
	}

	public void setKeySubject(String keySubject) {
		this.keySubject = keySubject;
	}

	public String getKeyProfileID() {
		return keyProfileID;
	}

	public void setKeyProfileID(String keyProfileID) {
		this.keyProfileID = keyProfileID;
	}

	public String getKeyProfileVersion() {
		return keyProfileVersion;
	}

	public void setKeyProfileVersion(String keyProfileVersion) {
		this.keyProfileVersion = keyProfileVersion;
	}

	public String getKeyProfileName() {
		return keyProfileName;
	}

	public void setKeyProfileName(String keyProfileName) {
		this.keyProfileName = keyProfileName;
	}	

	public String getCertiReqFileName() {
		return certiReqFileName;
	}

	public void setCertiReqFileName(String certiReqFileName) {
		this.certiReqFileName = certiReqFileName;
	}

	public byte[] getCertiReqBinaryFile() {
		return certiReqBinaryFile;
	}
	
	public String getCertiReqBinaryFileStr() {
		if(certiReqBinaryFile == null) return "";
		
		return new String(certiReqBinaryFile);
	}

	public void setCertiReqBinaryFile(byte[] certiReqBinaryFile) {
		this.certiReqBinaryFile = certiReqBinaryFile;
	}

	public String getExponentValue() {
		return exponentValue;
	}

	public void setExponentValue(String exponentValue) {
		this.exponentValue = exponentValue;
	}	

	public String getHashValueFileName() {
		return hashValueFileName;
	}

	public void setHashValueFileName(String hashValueFileName) {
		this.hashValueFileName = hashValueFileName;
	}

	public String getHashValue() {
		return hashValue;
	}

	public void setHashValue(String hashValue) {
		this.hashValue = hashValue;
	}

	public String getModulusSize() {
		return modulusSize;
	}

	public void setModulusSize(String modulusSize) {
		this.modulusSize = modulusSize;
	}

	public String getCertiResFileName() {
		return certiResFileName;
	}

	public void setCertiResFileName(String certiResFileName) {
		this.certiResFileName = certiResFileName;
	}
	
	public String getCertiResBinaryFileStr() {
		
		if(this.certiResBinaryFile != null){
			return Util.byteArray2hexString(certiResBinaryFile);
		}else{
			return "";
		}		
	}
	
	public byte[] getCertiResBinaryFile() {
		return certiResBinaryFile;
	}

	public void setCertiResBinaryFile(byte[] certiResBinaryFile) {
		this.certiResBinaryFile = certiResBinaryFile;
	}	

	public String getCaSerialNumber() {
		return caSerialNumber;
	}

	public void setCaSerialNumber(String caSerialNumber) {
		this.caSerialNumber = caSerialNumber;
	}

	public String getCaPublicKeyIndex() {
		return caPublicKeyIndex;
	}

	public void setCaPublicKeyIndex(String caPublicKeyIndex) {
		this.caPublicKeyIndex = caPublicKeyIndex;
	}

	public String getCaPublicKeyLength() {
		return caPublicKeyLength;
	}

	public void setCaPublicKeyLength(String caPublicKeyLength) {
		this.caPublicKeyLength = caPublicKeyLength;
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

	public String getRegistrationUserID() {
		return registrationUserID;
	}

	public void setRegistrationUserID(String registrationUserID) {
		this.registrationUserID = registrationUserID;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}
	
	public String getUpdateDateStr(){
		if(this.updateDate == null) return "";
		return formatter.format(this.updateDate);
	}

	public String getUpdateUserID() {
		return updateUserID;
	}

	public void setUpdateUserID(String updateUserID) {
		this.updateUserID = updateUserID;
	}	

	public String getPeriodType() {
		return periodType;
	}

	public void setPeriodType(String periodType) {
		this.periodType = periodType;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}		

	public String getBinStatusCode() {
		return binStatusCode;
	}

	public void setBinStatusCode(String binStatusCode) {
		this.binStatusCode = binStatusCode;
	}

	public String getReqSendStatusCode() {
		return reqSendStatusCode;
	}

	public void setReqSendStatusCode(String reqSendStatusCode) {
		this.reqSendStatusCode = reqSendStatusCode;
	}

	public String getResSendStatusCode() {
		return resSendStatusCode;
	}

	public void setResSendStatusCode(String resSendStatusCode) {
		this.resSendStatusCode = resSendStatusCode;
	}
	
	public BrandType getBrandType(){
		if(this.brandTypeCode != null){
			return BrandType.nameFor(this.brandTypeCode);
		}else{
			return BrandType.codeFor(this.brandTypeInt);
		}
	}	

	public String getAmexFileType() {
		return amexFileType;
	}

	public void setAmexFileType(String amexFileType) {
		this.amexFileType = amexFileType;
	}

	@Override
	public String toString() {
		return "CertificateModel [certificateUID=" + certificateUID
				+ ", certificateName=" + certificateName + ", companyID="
				+ companyID + ", companyName=" + companyName
				+ ", brandTypeCode=" + brandTypeCode + ", brandTypeInt="
				+ brandTypeInt + ", binNumber=" + binNumber
				+ ", trackingNumber=" + trackingNumber + ", ipkIndex="
				+ ipkIndex + ", ipkSize=" + ipkSize + ", requestDate="
				+ requestDate + ", expireDate=" + expireDate + ", keySubject="
				+ keySubject + ", keyProfileID=" + keyProfileID
				+ ", keyProfileVersion=" + keyProfileVersion
				+ ", keyProfileName=" + keyProfileName + ", certiReqFileName="
				+ certiReqFileName + ", exponentValue=" + exponentValue
				+ ", hashValue=" + hashValue + ", modulusSize=" + modulusSize
				+ ", certiResFileName=" + certiResFileName
				+ ", registrationDate=" + registrationDate
				+ ", registrationUserID=" + registrationUserID
				+ ", updateDate=" + updateDate + ", updateUserID="
				+ updateUserID + ", binStatusCode=" + binStatusCode
				+ ", reqSendStatusCode=" + reqSendStatusCode
				+ ", resSendStatusCode=" + resSendStatusCode + ", periodType="
				+ periodType + ", startDate=" + startDate + ", endDate="
				+ endDate + "]";
	}
	
}
