package com.kona.kms.web.profile;

import java.text.ParseException;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;

import org.globalplatform.namespaces.systems_profiles._1_1.KeyProfile;

import com.kona.kms.web.cert.BrandType;
import com.kona.kms.web.cert.model.CertificateModel;
import com.kona.kms.web.profile.model.KeyProfileModel; 
import com.konai.kmsexchangemessage.BinStatusCd;
import com.konai.kmsexchangemessage.KeyInfo;
import com.konai.kmsexchangemessage.KeyInfoRequestType;
import com.konai.kmsexchangemessage.KeyInfos;
import com.konai.kmsexchangemessage.KeyRole;
import com.konai.kmsexchangemessage.RegisteredBINCertification;
import com.konai.kmsexchangemessage.RequestedBINCertification;
import com.konai.kmsexchangemessage.WorkCode;

public class TransferUtil {
	
	public static KeyInfoRequestType createKeyInfo(KeyProfile profile, KeyProfileModel model){
		
		KeyInfoRequestType info = new KeyInfoRequestType();
		
		KeyInfos keyinfos = new KeyInfos();
		
		keyinfos.setCompanyID(model.getCompanyID());
		
		if(model.getActiveStatusCode().equals("Active")){
			keyinfos.setWorkCode("INSERT");	
		}else{
			keyinfos.setWorkCode("DELETE");	
		}					
		
		keyinfos.getKeyInfo().add(getKeyInfo(profile, model));		
		
		info.setKeyInfos(keyinfos);
		
		return info;
	}
	
	public static KeyInfoRequestType createKeyInfo(KeyProfile priProfile, KeyProfileModel pri, KeyProfile pubProfile, KeyProfileModel pub){
		
		KeyInfoRequestType info = new KeyInfoRequestType();
		
		KeyInfos keyinfos = new KeyInfos();
		
		keyinfos.setCompanyID(pri.getCompanyID());
		
		if(pri.getActiveStatusCode().equals("Active")){
			keyinfos.setWorkCode("INSERT");	
		}else{
			keyinfos.setWorkCode("DELETE");	
		}	
		
		/*
		 * Private Key Info Setting
		 */
		KeyInfo priValue = getKeyInfo(priProfile, pri);		
		priValue.setPairProfileUniqueID(pub.getKeyProfileID());
		priValue.setPairProfileVersion(pub.getKeyProfileVersion());
		
		keyinfos.getKeyInfo().add(priValue);	
		
		/*
		 * Public Key Info Setting
		 */
		KeyInfo pubValue = getKeyInfo(pubProfile, pub);
		pubValue.setPairProfileUniqueID(pri.getKeyProfileID());
		pubValue.setPairProfileVersion(pri.getKeyProfileVersion());
		
		keyinfos.getKeyInfo().add(pubValue);	
		
		info.setKeyInfos(keyinfos);
		
		return info;
	}
	
	private static KeyInfo getKeyInfo(KeyProfile profile, KeyProfileModel model){
		
		KeyInfo value = new KeyInfo();
		
		value.setProfileUniqueID(model.getKeyProfileID());
		value.setProfileVersion(model.getKeyProfileVersion());
		value.setProfileName(model.getKeyProfileName());
		value.setDesc(model.getDescription());
		
		KeyRole keyRole = null;
		
		if(model.getKeyRoleCode().equals("SD Key")){
			keyRole = KeyRole.SD_KEY;
		}else if(model.getKeyRoleCode().equals("Transport Key")){
			keyRole = KeyRole.TRANSPORT_KEY;
		}else if(model.getKeyRoleCode().equals("Issuer RSA Key")){
			keyRole = KeyRole.ISSUERRSA_KEY;
		}else if(model.getKeyRoleCode().equals("Application Key")){
			keyRole = KeyRole.APPLICATION_KEY;
		}else{
			throw new IllegalArgumentException("Unknown Key Role:[ProfileID:Version:Role]: " + model.getKeyProfileID()+":"+model.getKeyVersion()+":"+model.getKeyRoleCode());
		}
		
		value.setKeyRole(keyRole);
		value.setKeyTypeCd(model.getKeyTypeCode());
		value.setKeySubTypeCd(model.getKeySubtypeCode());
		value.setKeyVersion(model.getKeyVersion());
		value.setKeyIdentfier(model.getKeyIdentifier());
		value.setKeyKMSLabelName(model.getKeyLabel());
		value.setKeySize((int)model.getKeySize());
		value.setKeyIndex(model.getKeyIndex());
		
		try {
			value.setStartDate(model.getEffectiveStartDateStr());
			value.setEndDate(model.getEffectiveEndDateStr());
		} catch (ParseException e) {
			// ignore
		}
		
		value.setKeyProfile(profile);
		
		return value;
	}

	public static RegisteredBINCertification createRegisteredCertInfo(
			CertificateModel model, String workcode)  throws DatatypeConfigurationException{		
		
		RegisteredBINCertification value = new RegisteredBINCertification();
		
		value.setCertificationResponseFileName(model.getCertiResFileName());
		value.setCertificationResponseBinaryFile(model.getCertiResBinaryFile());
		value.setCertificationResponseDate(DatatypeFactory.newInstance().newXMLGregorianCalendar(model.getUpdateDateStr()));		
		
		//modify by shifei 20140926
		if(model.getCaPublicKeyLength()!=null){
			value.setCaPublicKeySize(Integer.valueOf(model.getCaPublicKeyLength()));	
		}
		
		value.setExpireDate(DatatypeFactory.newInstance().newXMLGregorianCalendar(model.getExpireDateStr()));		
		value.setBinStatus(BinStatusCd.USE);			
		
		value.setCompanyID(model.getCompanyID());
		value.setBrandTypeCd(String.valueOf((BrandType.nameFor(model.getBrandTypeCode())).getCode()));
		value.setBinNO(model.getBinNumber());
		value.setIssuerPublicKeyIndex(model.getIpkIndex());
		value.setExpireDate(DatatypeFactory.newInstance().newXMLGregorianCalendar(model.getExpireDateStr()));
				
		value.setWorkCode(WorkCode.UPDATE);		
				
		return value;
	}
	
	public static RequestedBINCertification createRequestedCertInfo(
			CertificateModel model, String workcode) throws DatatypeConfigurationException {
		RequestedBINCertification value = new RequestedBINCertification();
		
		value.setTrackingNo(model.getTrackingNumber());
		value.setIssuerPublicKeySize(model.getIpkSize());
		value.setCertificationRequestFileName(model.getCertiReqFileName());
		value.setCertificationRequestBinaryFile(model.getCertiReqBinaryFile());
		value.setCertificationRequestDate(DatatypeFactory.newInstance().newXMLGregorianCalendar(model.getRegistrationDateStr()));
		value.setIssuerPublicKeyExponentValue(model.getExponentValue());
		value.setCertificationHashValue(model.getHashValue());
		value.setBinStatus(BinStatusCd.DRAFT);
		
		value.setCompanyID(model.getCompanyID());
		value.setBrandTypeCd(String.valueOf((BrandType.nameFor(model.getBrandTypeCode())).getCode()));
		value.setBinNO(model.getBinNumber());
		value.setIssuerPublicKeyIndex(model.getIpkIndex());
		value.setExpireDate(DatatypeFactory.newInstance().newXMLGregorianCalendar(model.getExpireDateStr()));
		
		if(workcode.equals("INSERT")){
			value.setWorkCode(WorkCode.INSERT);
		}else if(workcode.equals("DELETE")){
			value.setWorkCode(WorkCode.DELETE);
		}else if(workcode.equals("UPDATE")){
			value.setWorkCode(WorkCode.UPDATE);
		}
		
		
		return value;
	}
}
