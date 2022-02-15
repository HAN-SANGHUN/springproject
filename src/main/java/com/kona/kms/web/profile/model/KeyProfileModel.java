package com.kona.kms.web.profile.model;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.kona.kms.web.utils.PageableModel;

public class KeyProfileModel extends PageableModel implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7724351806267066245L;
	
	///////////////////////////////////////////////////////////////////
	// Key Profile Attributes
	///////////////////////////////////////////////////////////////////
	
	private String hsmLabel;
	
	private String tokenLabel;
	
	private String keyProfileID;
	
	private String keyProfileVersion;
	
	private String oldKeyProfileVersion;
	
	private int keyProfileErrataVersion;
	
	private String keyProfileName;
	
	private String description;
	
	private String keyOwner;
	
	private String companyID;
	
	private String companyName;	

	private Date effectiveStartDate;
	
	private Date effectiveEndDate;	

	private Date expireDate;
	
	private Date revocationDate;
	
	private String testMode;
	
	private String activeStatusCode;	
	
	private String sendStatusCode;	
	
	private String keyProfileXMLPath;
	
	private String keyProfileXMLFile;	

	private List<KeyProfileRevisionModel> revisionHistory;
	
	private KeyPartModel keyPart;
	
	private KeyValueModel keyValue;
	
	private TransportKeyModel transportKey;
	
	//add by shifei 20140919
	private String formatCode;
	
	///////////////////////////////////////////////////////////////////
	// Key Value Attributes
	///////////////////////////////////////////////////////////////////
	
	private String keyTypeCode;	
	
	private String keySubject;
	
	private String keySubtypeCode;	
	
	private String keyVersion;
	
	private String keyIdentifier;
	
	private String keyDefinition;
	
	private String keyRoleCode;	
	
	private int keyIndex;
	
	private String keyLabel;
	
	private long keySize;	
	
	private String keyAlgorithm;
	
	private String keyUsageIndicatorValue;	
	
	private boolean isImportable;
	
	private boolean isExportable;
	
	private boolean isSensitive;
	
	private boolean isEncrypt;
	
	private boolean isDecrypt;
	
	private boolean isEncryptDecrypt;
	
	private boolean isWrap;
	
	private boolean isUnwrap;
	
	private boolean isWrapUnwrap;
	
	private boolean isSign;
	
	private boolean isVerify;
	
	private boolean isDerive;
	
	private String keyValueFlag = "N";
	
	///////////////////////////////////////////////////////////////////
	// Log Data
	///////////////////////////////////////////////////////////////////
	
	private String registrationUserID;
	
	private Date registrationDate;
	
	private String updateUserID;
	
	private Date updateDate;
	
	private boolean clientDownloadable;
	
	private String revisionDescription;

	
	///////////////////////////////////////////////////////////////////
	// Grid Search Specific Data
	///////////////////////////////////////////////////////////////////
	
	private String periodType;
	
	private String startDate;
	
	private String endDate;
	
	private static SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
	
	public KeyProfileModel(){
		
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

	public String getOldKeyProfileVersion() {
		return oldKeyProfileVersion;
	}

	public void setOldKeyProfileVersion(String oldKeyProfileVersion) {
		this.oldKeyProfileVersion = oldKeyProfileVersion;
	}

	public int getKeyProfileErrataVersion() {
		return keyProfileErrataVersion;
	}

	public void setKeyProfileErrataVersion(int keyProfileErrataVersion) {
		this.keyProfileErrataVersion = keyProfileErrataVersion;
	}

	public String getKeyProfileName() {
		return keyProfileName;
	}

	public void setKeyProfileName(String keyProfileName) {
		this.keyProfileName = keyProfileName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getKeyOwner() {
		return keyOwner;
	}

	public void setKeyOwner(String keyOwner) {
		this.keyOwner = keyOwner;
	}

	public String getCompanyID() {
		return companyID;
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

	public Date getEffectiveStartDate() {
		return effectiveStartDate;
	}

	public void setEffectiveStartDate(Date effectiveStartDate) {
		this.effectiveStartDate = effectiveStartDate;
	}

	public Date getEffectiveEndDate() {
		return effectiveEndDate;
	}

	public void setEffectiveEndDate(Date effectiveEndDate) {
		this.effectiveEndDate = effectiveEndDate;
	}

	public Date getExpireDate() {
		return expireDate;
	}

	public void setExpireDate(Date expireDate) {
		this.expireDate = expireDate;
	}

	public Date getRevocationDate() {
		return revocationDate;
	}

	public void setRevocationDate(Date revocationDate) {
		this.revocationDate = revocationDate;
	}

	public String getTestMode() {
		return testMode;
	}

	public void setTestMode(String testMode) {
		this.testMode = testMode;
	}

	public String getActiveStatusCode() {
		return activeStatusCode;
	}

	public void setActiveStatusCode(String activeStatusCode) {
		this.activeStatusCode = activeStatusCode;
	}

	public String getSendStatusCode() {
		return sendStatusCode;
	}

	public void setSendStatusCode(String sendStatusCode) {
		this.sendStatusCode = sendStatusCode;
	}

	public String getKeyProfileXMLPath() {
		return keyProfileXMLPath;
	}

	public void setKeyProfileXMLPath(String keyProfileXMLPath) {
		this.keyProfileXMLPath = keyProfileXMLPath;
	}

	public String getKeyProfileXMLFile() {
		return keyProfileXMLFile;
	}
	
	public String[] getKeyProfileXMLFileWeb() {
		if(this.keyProfileXMLFile != null)
			return keyProfileXMLFile.split("\n");
		return null;
	}
	
	public void setKeyProfileXMLFile(String keyProfileXMLFile) {
		this.keyProfileXMLFile = keyProfileXMLFile;
	}

	public List<KeyProfileRevisionModel> getRevisionHistory() {
		return revisionHistory;
	}

	public void setRevisionHistory(List<KeyProfileRevisionModel> revisionHistory) {
		this.revisionHistory = revisionHistory;
	}	

	public KeyPartModel getKeyPart() {
		return keyPart;
	}

	public void setKeyPart(KeyPartModel keyPart) {
		this.keyPart = keyPart;
	}	

	public KeyValueModel getKeyValue() {
		return keyValue;
	}

	public void setKeyValue(KeyValueModel keyValue) {
		this.keyValue = keyValue;
	}	

	public TransportKeyModel getTransportKey() {
		return transportKey;
	}

	public void setTransportKey(TransportKeyModel transportKey) {
		this.transportKey = transportKey;
	}

	public String getKeyTypeCode() {
		return keyTypeCode;
	}

	public void setKeyTypeCode(String keyTypeCode) {
		this.keyTypeCode = keyTypeCode;
	}

	public String getKeySubject() {
		return keySubject;
	}

	public void setKeySubject(String keySubject) {
		this.keySubject = keySubject;
	}

	public String getKeySubtypeCode() {
		return keySubtypeCode;
	}

	public void setKeySubtypeCode(String keySubtypeCode) {
		this.keySubtypeCode = keySubtypeCode;
	}

	public String getKeyVersion() {
		return keyVersion;
	}

	public void setKeyVersion(String keyVersion) {
		this.keyVersion = keyVersion;
	}

	public String getKeyIdentifier() {
		return keyIdentifier;
	}

	public void setKeyIdentifier(String keyIdentifier) {
		this.keyIdentifier = keyIdentifier;
	}

	public String getKeyDefinition() {
		return keyDefinition;
	}

	public void setKeyDefinition(String keyDefinition) {
		this.keyDefinition = keyDefinition;
	}

	public String getKeyRoleCode() {
		return keyRoleCode;
	}

	public void setKeyRoleCode(String keyRoleCode) {
		this.keyRoleCode = keyRoleCode;
	}

	public int getKeyIndex() {
		return keyIndex;
	}

	public void setKeyIndex(int keyIndex) {
		this.keyIndex = keyIndex;
	}

	public String getKeyLabel() {
		return keyLabel;
	}

	public void setKeyLabel(String keyLabel) {
		this.keyLabel = keyLabel;
	}

	public long getKeySize() {
		return keySize;
	}

	public void setKeySize(long keySize) {
		this.keySize = keySize;
	}	

	public String getKeyAlgorithm() {
		return keyAlgorithm;
	}

	public void setKeyAlgorithm(String keyAlgorithm) {
		this.keyAlgorithm = keyAlgorithm;
	}

	public String getKeyUsageIndicatorValue() {
		
		if(this.keyUsageIndicatorValue != null){

			return keyUsageIndicatorValue;
		}else{
			StringBuffer sb = new StringBuffer();
			
			sb.append((this.isEncrypt)?"0":"1");
			sb.append((this.isDecrypt)?"0":"1");
			sb.append((this.isEncryptDecrypt)?"0":"1");
			sb.append((this.isSign)?"0":"1");
			sb.append((this.isVerify)?"0":"1");
			sb.append((this.isWrap)?"0":"1");
			sb.append((this.isUnwrap)?"0":"1");
			sb.append((this.isWrapUnwrap)?"0":"1");
			sb.append((this.isDerive)?"0":"1");
			sb.append((this.isImportable)?"0":"1");
			sb.append((this.isExportable)?"0":"1");
			sb.append((this.isSensitive)?"0":"1");
			
			
			return sb.toString();
		}
	}

	public void setKeyUsageIndicatorValue(String keyUsageIndicatorValue) {
		this.keyUsageIndicatorValue = keyUsageIndicatorValue;
	}	

	public boolean isImportable() {
		return isImportable;
	}

	public void setImportable(boolean isImportable) {
		this.isImportable = isImportable;
	}

	public boolean isExportable() {
		return isExportable;
	}

	public void setExportable(boolean isExportable) {
		this.isExportable = isExportable;
	}

	public boolean isSensitive() {
		return isSensitive;
	}

	public void setSensitive(boolean isSensitive) {
		this.isSensitive = isSensitive;
	}

	public boolean isEncrypt() {
		return isEncrypt;
	}

	public void setEncrypt(boolean isEncrypt) {
		this.isEncrypt = isEncrypt;
	}

	public boolean isDecrypt() {
		return isDecrypt;
	}

	public void setDecrypt(boolean isDecrypt) {
		this.isDecrypt = isDecrypt;
	}

	public boolean isEncryptDecrypt() {
		return isEncryptDecrypt;
	}

	public void setEncryptDecrypt(boolean isEncryptDecrypt) {
		this.isEncryptDecrypt = isEncryptDecrypt;
	}

	public boolean isWrap() {
		return isWrap;
	}

	public void setWrap(boolean isWrap) {
		this.isWrap = isWrap;
	}

	public boolean isUnwrap() {
		return isUnwrap;
	}

	public void setUnwrap(boolean isUnwrap) {
		this.isUnwrap = isUnwrap;
	}

	public boolean isWrapUnwrap() {
		return isWrapUnwrap;
	}

	public void setWrapUnwrap(boolean isWrapUnwrap) {
		this.isWrapUnwrap = isWrapUnwrap;
	}

	public boolean isSign() {
		return isSign;
	}

	public void setSign(boolean isSign) {
		this.isSign = isSign;
	}

	public boolean isVerify() {
		return isVerify;
	}

	public void setVerify(boolean isVerify) {
		this.isVerify = isVerify;
	}

	public boolean isDerive() {
		return isDerive;
	}

	public void setDerive(boolean isDerive) {
		this.isDerive = isDerive;
	}	

	public String getKeyValueFlag() {
		return keyValueFlag;
	}

	public void setKeyValueFlag(String keyValueFlag) {
		this.keyValueFlag = keyValueFlag;
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
		return formatter.format(this.registrationDate);
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
	
	public KeyValueAttrVO getKeyValueAttribute(){
		
		return new KeyValueAttrVO(this.getKeyUsageIndicatorValue());
	}
	
	public void setEffectiveStartDateStr(String date) throws ParseException{
		
		if(date != null && !"".equals(date)){
			this.effectiveStartDate = formatter.parse(date);
		}		
	}
	
	public String getEffectiveStartDateStr() throws ParseException{
		
		if(this.effectiveStartDate == null) return "";
		return formatter.format(this.effectiveStartDate);
	}
	
	public void setEffectiveEndDateStr(String date) throws ParseException{
		if(date != null && !"".equals(date)){
			this.effectiveEndDate = formatter.parse(date);
		}
	}
	
	public String getEffectiveEndDateStr() throws ParseException{
		
		if(this.effectiveEndDate == null) return "";
		return formatter.format(this.effectiveEndDate);
	}
	
	public void setExpireDateStr(String date) throws ParseException{
		this.expireDate = formatter.parse(date);
	}
	
	public String getExpireDateStr(){
		
		if(this.expireDate == null) return "";
		return formatter.format(this.expireDate);
	}
	
	public void setRevocationDateStr(String date) throws ParseException{
		if(date != null && !"".equals(date)){
			this.revocationDate = formatter.parse(date);
		}
	}		
	
	public String getRevocationDateStr() throws ParseException{
		
		if(this.revocationDate == null) return "";
		return formatter.format(this.revocationDate);
	}

	public boolean isClientDownloadable() {
		return clientDownloadable;
	}

	public void setClientDownloadable(boolean clientDownloadable) {
		this.clientDownloadable = clientDownloadable;
	}	

	public String getRevisionDescription() {
		return revisionDescription;
	}

	public void setRevisionDescription(String revisionDescription) {
		this.revisionDescription = revisionDescription;
	}
	
	public String getFormatCode() {
		return formatCode;
	}

	public void setFormatCode(String formatCode) {
		this.formatCode = formatCode;
	}	

	public boolean isSecretKeyProfile(){
		
		if(this.keyTypeCode == null) throw new IllegalArgumentException("Key Type cannot be null.");
		
		if(this.keyTypeCode.equals("SECRET")){
			return true;
		}else{
			return false;
		}
	}
	
	public boolean isKeyValueSet(){
		if(this.keyValueFlag.equals("Y")) return true;
		return false;
	}
	

	@Override
	public String toString() {
		return "KeyProfileModel [hsmLabel=" + hsmLabel + ", tokenLabel="
				+ tokenLabel + ", keyProfileID=" + keyProfileID
				+ ", keyProfileVersion=" + keyProfileVersion
				+ ", keyProfileName=" + keyProfileName + ", description="
				+ description + ", keyOwner=" + keyOwner + ", companyID="
				+ companyID + ", companyName=" + companyName
				+ ", effectiveStartDate=" + effectiveStartDate
				+ ", effectiveEndDate=" + effectiveEndDate + ", expireDate="
				+ expireDate + ", revocationDate=" + revocationDate
				+ ", testMode=" + testMode + ", activeStatusCode="
				+ activeStatusCode + ", sendStatusCode=" + sendStatusCode
				+ ", keyProfileXMLPath=" + keyProfileXMLPath
				+ ", keyProfileXMLFile=" + keyProfileXMLFile
				+ ", revisionHistory=" + revisionHistory + ", keyTypeCode="
				+ keyTypeCode + ", keySubject=" + keySubject
				+ ", keySubtypeCode=" + keySubtypeCode + ", keyVersion="
				+ keyVersion + ", keyIdentifier=" + keyIdentifier
				+ ", keyDefinition=" + keyDefinition + ", keyRoleCode="
				+ keyRoleCode + ", keyIndex=" + keyIndex + ", keyLabel="
				+ keyLabel + ", keySize=" + keySize + ", keyAlgorithm=" + keyAlgorithm
				+ ", keyUsageIndicatorValue=" + keyUsageIndicatorValue
				+ ", isImportable=" + isImportable + ", isExportable="
				+ isExportable + ", isSensitive=" + isSensitive
				+ ", isEncrypt=" + isEncrypt + ", isDecrypt=" + isDecrypt
				+ ", isEncryptDecrypt=" + isEncryptDecrypt + ", isWrap="
				+ isWrap + ", isUnwrap=" + isUnwrap + ", isWrapUnwrap="
				+ isWrapUnwrap + ", isSign=" + isSign + ", isVerify="
				+ isVerify + ", isDerive=" + isDerive + ", registrationUserID="
				+ registrationUserID + ", registrationDate=" + registrationDate
				+ ", updateUserID=" + updateUserID + ", updateDate="
				+ updateDate + ", periodType=" + periodType + ", startDate="
				+ startDate + ", endDate=" + endDate + ", formatCode=" + formatCode + " ]";
	}	
	
}
