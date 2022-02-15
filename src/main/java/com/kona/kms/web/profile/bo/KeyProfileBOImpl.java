package com.kona.kms.web.profile.bo;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.globalplatform.namespaces.systems_profiles._1_1.KeyProfile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.kona.kms.KMSCode;
import com.kona.kms.KmsException;
import com.kona.kms.cao.KMSCrypto;
import com.kona.kms.framework.GpMsgBinder;
import com.kona.kms.web.profile.ConvertUtil;
import com.kona.kms.web.profile.TransferUtil;
import com.kona.kms.web.profile.dao.KeyProfileDao;
import com.kona.kms.web.profile.model.KeyProfileJobModel;
import com.kona.kms.web.profile.model.KeyProfileModel;
import com.kona.kms.web.profile.model.KeyProfileModel1;
import com.kona.kms.web.profile.model.KeyProfileRevisionModel;
import com.kona.kms.web.profile.model.KeyValueDTO;
import com.kona.kms.web.profile.model.ProfileExportDTO;
import com.kona.kms.web.profile.model.KeyValueModel.ValueComponent;
import com.kona.kms.web.utils.FileUtil;
import com.kona.kms.web.utils.GridPageData;
import com.kona.kms.web.utils.ProfileIDAttr;
import com.konai.keymanagement.KeyManagementPort;
import com.konai.kmsexchangemessage.KeyInfoRequestType;
import com.konai.kmsexchangemessage.ResponseType;

@Service
@Transactional(propagation=Propagation.REQUIRED, readOnly=true)
public class KeyProfileBOImpl implements KeyProfileBO {

	private static final Logger logger = LoggerFactory.getLogger(KeyProfileBOImpl.class);
	
	@Autowired
	private KeyProfileDao keyProfileDao;
	
	@Autowired
	private KeyManagementPort kiClient;
	
	private Set<Integer> indexes = new HashSet<Integer>();
		
	private String savePath;
	
	@Value("${key_profile_xml_save_path}")
	public void setKeyProfileSavePath(String savePath){
		this.savePath = StringUtils.trimWhitespace(savePath);
	}

	@Override
	public List<KeyProfileModel> getPairKeyProfiles(String keySubject) {
		return keyProfileDao.getPairKeyProfiles(keySubject);
	}

	@Override
	public KeyProfileModel getKeyProfile(String id, String version) {
		KeyProfileModel model =  keyProfileDao.getKeyProfile(id, version);
		
/*		if(model != null){
			model.setRevisionHistory(keyProfileDao.getProfileResvisionList(model.getKeyProfileID(), model.getKeyProfileVersion()));
		}
*/		return model;
	}
	
	@Override
	public GridPageData<KeyProfileModel> getKeyProfileList(KeyProfileModel condition) {
		
		List<KeyProfileModel> values = keyProfileDao.getKeyProfileList(condition);
						
		int totalRecords = 0;
		if(!values.isEmpty()){
			totalRecords = keyProfileDao.getTotalRecord(condition);
		}
		
		return new GridPageData<KeyProfileModel>(condition.getRowSize(), condition.getPage(), totalRecords, values);
	}

	@Override
	public List<KeyProfileModel> getKeyProfileListAll(KeyProfileModel condition) {
		return keyProfileDao.getKeyProfileListAll(condition);
	}
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED, readOnly=false, rollbackFor=Exception.class)
	public void updatePairKeyProfile(KeyProfileModel priModel,
			KeyProfileModel1 pubModel) throws KmsException {
		
		KeyProfileModel pubModel0 = ConvertUtil.createPublicKeyProfileModel(priModel, pubModel);
		
		this.updateKeyProfile(priModel);
		this.clearKeyIndex(priModel.getKeyIndex());
		
		this.updateKeyProfile(pubModel0);	
		this.clearKeyIndex(pubModel0.getKeyIndex());	
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, readOnly=false, rollbackFor=Exception.class)
	public void updateKeyProfile(KeyProfileModel newModel) throws KmsException {
		
//		KeyProfileModel oldModel = keyProfileDao.getKeyProfile(newModel.getKeyProfileID(), newModel.getOldKeyProfileVersion());
		
		//modify by shifei 2014-04-22
		KeyProfileModel oldModel;
		if (newModel.getOldKeyProfileVersion() != null){
			oldModel = keyProfileDao.getKeyProfile(newModel.getKeyProfileID(), newModel.getOldKeyProfileVersion());	
//			oldModel = keyProfileDao.getKeyProfile(newModel.getOldKeyProfileID(), newModel.getOldKeyProfileVersion());
		}
		else {
			oldModel = keyProfileDao.getKeyProfile(newModel.getKeyProfileID(), null);
		}
		
		if(!newModel.getKeyTypeCode().equals("PUBLIC") && (newModel.getKeyRoleCode().equals("Transport Key") || newModel.getKeyRoleCode().equals("Issuer RSA Key"))){
			if(oldModel.getKeyIndex() != newModel.getKeyIndex()){
				this.checkKeyIndexUnique(newModel.getKeyIndex());
			}
		}
		
//		boolean isVersionUp = ConvertUtil.compare(newModel, oldModel);
		
		boolean isVersionUp;
		if (oldModel != null){
			isVersionUp = ConvertUtil.compare(newModel, oldModel);	
		} else{
			isVersionUp = true;
		}
				
		try{
			ConvertUtil.setKeyProfileSavePath(this.savePath, newModel);
			
			if(isVersionUp) {
				ConvertUtil.setNewKeyProfileVersion(newModel);		
			}else{
				/*
				 * Set revision history(if exists) to create new xml file.
				 */
//				newModel.setRevisionHistory(keyProfileDao.getProfileResvisionList(newModel.getKeyProfileID(), newModel.getKeyProfileVersion()));
			}
						
			newModel.setKeyProfileXMLFile(new String(this.marshalKeyProfile(newModel)));
		}catch(Exception e){
			logger.error("KeyProfileBO: updteKeyProfile: " + e.getMessage());
			throw new KmsException(KMSCode.KEY_EDIT_FAIL);
		}	
		
		
		if(isVersionUp){
			
			keyProfileDao.insertKeyProfile(newModel);			
			
//			keyProfileDao.insertKeyProfileRevisionHistory(newModel.getRevisionHistory().get(0));
		
			oldModel.setActiveStatusCode("Inactive");
			keyProfileDao.deleteKeyProfile(oldModel);
			
			
			//20150309
			keyProfileDao.insertKeyProfileJob(ConvertUtil.createKeyProfileJob(newModel));
			
			
			
		}else{
			keyProfileDao.updateKeyProfile(newModel);
		}
		
		try{
			FileUtil.saveAsFile(newModel.getKeyProfileXMLPath(), newModel.getKeyProfileXMLFile());
		}catch(Exception e){			
			logger.error("KeyProfileBO: updateKeyProfile: saveKeyProfileXMLFile: " + e.getMessage()); // ignore
		}		
	}
	

	@Override
	@Transactional(propagation=Propagation.REQUIRED, readOnly=false, rollbackFor=Exception.class)
	public ProfileIDAttr insertPairKeyProfile(KeyProfileModel priModel,
			KeyProfileModel1 pubModel) throws KmsException {
		
		KeyProfileModel pubModel0 = ConvertUtil.createPublicKeyProfileModel(priModel, pubModel);
		
		this.insertKeyProfile(priModel);	
		this.clearKeyIndex(priModel.getKeyIndex());
		
		this.insertKeyProfile(pubModel0);
		this.clearKeyIndex(pubModel0.getKeyIndex());
		
		//modify by shifei 20140915
		//add by shifei 20140822
//		keyProfileDao.insertKeyProfileJob(ConvertUtil.createKeyProfileJob(priModel));
//		keyProfileDao.insertKeyProfileJob(ConvertUtil.createKeyProfileJob(pubModel0));
		
		return new ProfileIDAttr(true, priModel.getKeyProfileID(), priModel.getKeyProfileVersion(), pubModel0.getKeyProfileID(), pubModel0.getKeyProfileVersion());
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, readOnly=false, rollbackFor=Exception.class)
	public ProfileIDAttr insertKeyProfile(KeyProfileModel model) throws KmsException {
						
		if(!model.getKeyTypeCode().equals("PUBLIC") && (model.getKeyRoleCode().equals("Transport Key") || model.getKeyRoleCode().equals("Issuer RSA Key"))){
			
			this.checkKeyIndexUnique(model.getKeyIndex());
		}
		
		ConvertUtil.setKeyProfileSavePath(this.savePath, model);
						
		model.setKeyProfileXMLFile(new String(this.marshalKeyProfile(model)));			
		
		keyProfileDao.insertKeyProfile(model);
		
		//add by shifei 20140822
		keyProfileDao.insertKeyProfileJob(ConvertUtil.createKeyProfileJob(model));
		
		
		try{
			FileUtil.saveAsFile(model.getKeyProfileXMLPath(), model.getKeyProfileXMLFile());
		}catch(Exception e){
			logger.error("KeyProfileBO: insertKeyProfile: saveKeyProfileXMLFile: " + e.getMessage()); //ignore
		}
		
		return new ProfileIDAttr(false, model.getKeyProfileID(), model.getKeyProfileVersion(), null,null);
	}

	@Override
	public String getSecretKeyKCV(String mech, int keySize, String text)
			throws KmsException {
		
		String kcv = null;
		try{
			kcv = KMSCrypto.getInstance().getKCV(mech, keySize, text);			
		}catch(Exception e){
			logger.error("KeyProfileBO: getSecretKeyKCV: " + e.getMessage());
			throw new KmsException(KMSCode.KEY_KCV_CREATE_FAIL);
		}
		return kcv;
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, readOnly=false, rollbackFor=Exception.class)
	public KeyValueDTO setSecretKeyValue(KeyValueDTO dto) throws KmsException, Exception{
		
		KeyProfileModel model = keyProfileDao.getKeyProfile(dto.getProfileID(), dto.getProfileVersion());
				
			
		String[] kcvs = null;
		
		try{
			logger.error("KeyProfileBO: ConvertUtil: createKeyAttrs");
			String[] attrs = ConvertUtil.createKeyAttrs(model);
//			logger.error("KeyProfileBO: attrs: [{}]",attrs);
			
			kcvs = KMSCrypto.getInstance().generateKeyComponents(model.getKeySubtypeCode(), (int)model.getKeySize(), model.getTokenLabel(), attrs, dto.getComponents());
			model.setKeyValueFlag("Y");
		}
		catch(Exception e){
			logger.error("KeyProfileBO: setSecretKeyValue: generateKeyComponents: " + e.getMessage());
			throw new KmsException(KMSCode.KEY_VALUE_CREATE_FAIL);
		}
		
		if(kcvs == null || kcvs.length != 4){
			logger.error("KeyProfileBO: setSecretKeyValue: Returned KCVs values should be lenght of 4");
			throw new KmsException(KMSCode.KEY_VALUE_CREATE_FAIL);
		}
		
		dto.setKcv1(kcvs[0]);
		dto.setKcv2(kcvs[1]);
		dto.setKcv3(kcvs[2]);
		dto.setCombinedKcv(kcvs[3]);	
		
		if(!model.getKeyValueAttribute().isSensitive){
			
			try{
				String[] attrs0 = KMSCrypto.getInstance().getKeyAttributes(model.getTokenLabel(), model.getKeyLabel());
							
				ConvertUtil.setSecretKeyValue(model, attrs0);
			}catch(KmsException e){
				logger.error("KeyProfileBO: setScretKeyValue: retrieve Key Value from HSM: " + e.getMessage());
				
				throw new KmsException(KMSCode.KEY_VALUE_CREATE_FAIL);
			}				
		}
		
		model.setKeyProfileXMLFile(new String(this.marshalKeyProfile(model)));		
		
		keyProfileDao.updateKeyValue(model);
		//modify by shifei 20140818
//		keyProfileDao.insertKeyProfileJob(ConvertUtil.createKeyProfileJob(model));
				
		try{
			FileUtil.saveAsFile(model.getKeyProfileXMLPath(), model.getKeyProfileXMLFile());
		}catch(Exception e){
			logger.error("KeyProfileBO: setScretKeyValue: saveKeyProfileXMLFile: " + e.getMessage()); //ignore
		}
		
		return dto;
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, readOnly=false, rollbackFor=Exception.class)
	public void setPairKeyValue(KeyValueDTO dto) throws KmsException{
		
				
		List<KeyProfileModel> models = keyProfileDao.getPairKeyProfiles(dto.getKeySubject());
				
		
		for(KeyProfileModel model : models){
			try{
				String[] attrs = ConvertUtil.createKeyAttrs(model, dto);
			
			
				if(model.getKeyTypeCode().equals("PRIVATE")){
					KMSCrypto.getInstance().generatePrivateKey(model.getTokenLabel(), attrs, (int)model.getKeySize());
					
				}else if(model.getKeyTypeCode().equals("PUBLIC")){
					KMSCrypto.getInstance().generatePublicKey(model.getTokenLabel(), attrs, (int)model.getKeySize());
				}				
				
				// Key Profile  XML file Update with Key Value
				if(!model.getKeyValueAttribute().isSensitive){					
					String[] attrs0 = KMSCrypto.getInstance().getKeyAttributes(model.getTokenLabel(), model.getKeyLabel());
				
					ConvertUtil.setPairKeyValue(model, attrs0);									
				}	
			}catch(Exception e){
				logger.error("KeyProfileBO: setPairKeyValue: generateKey ERROR: " + e.getMessage());
				throw new KmsException(KMSCode.KEY_VALUE_CREATE_FAIL);
			}
			
			
			model.setKeyProfileXMLFile(new String(this.marshalKeyProfile(model)));
			

			model.setKeyValueFlag("Y");
		}	
		
		for(KeyProfileModel model : models){
			keyProfileDao.updateKeyValue(model);
			
			try{
				FileUtil.saveAsFile(model.getKeyProfileXMLPath(), model.getKeyProfileXMLFile());
			}catch(Exception e){
				logger.error("KeyProfileBO: setPairKeyValue: saveKeyProfileXMLFile: " + e.getMessage()); //ignore
			}
		}	
		
		//modify by shifei 20140822
//		keyProfileDao.insertKeyProfileJob(ConvertUtil.createKeyProfileJob(models.get(0)));
		//add by shifei 20140618
//		keyProfileDao.insertKeyProfileJob(ConvertUtil.createKeyProfileJob(models.get(1)));
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, readOnly=false, rollbackFor=Exception.class)
	public void deleteKeyProfile(String[] profileID, String updater) {

		Date now = new Date();
		
		for(String id : profileID){
			String[] keys = id.split("-");
			
			KeyProfileModel old = keyProfileDao.getKeyProfile(keys[0], keys[1]);
			
			if(old != null){
				if(old.isKeyValueSet()){
					old.setActiveStatusCode("Inactive");
					old.setUpdateDate(now);
					old.setUpdateUserID(updater);
					
					keyProfileDao.deleteKeyProfile(old);
					
					keyProfileDao.insertKeyProfileJob(ConvertUtil.createKeyProfileJob(old));
				}else{
					keyProfileDao.deleteKeyProfilePermanent(old);
				}				
			}
		}
	}
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED, readOnly=false, rollbackFor=Exception.class)
	public void sendKeyProfile(String keyProfileID, String keyProfileVersion, String updater) throws KmsException {

//		KeyProfileJobModel job = keyProfileBO.getKeyProfileToTransfer(keyProfileID, keyProfileVersion);
				
		KeyProfileJobModel job = keyProfileDao.getKeyProfileToTransfer(keyProfileID, keyProfileVersion);
		
		if(job == null) return;
		
		try{
			if(job.getKeyTypeCode().equals("SECRET")){
//				keyProfileBO.transferSecretKeyProfile(job);
				KeyProfileModel profile = keyProfileDao.getKeyProfile(job.getKeyProfileID(), job.getKeyProfileVersion());
				
				KeyProfile gpMsg = null;
				
				try {
					gpMsg = ConvertUtil.createKeyProfile(profile);
				} catch (Exception e) {
					logger.error("KeyProfileBO: transferSecretKeyProfile: converting Key Profile: " + e.getMessage());
					
					throw new KmsException(KMSCode.KEY_CREATE_FAIL);
				}
				
				KeyInfoRequestType keyInfo = TransferUtil.createKeyInfo(gpMsg, profile);
				
				ResponseType response = kiClient.sendKeyInfo(keyInfo);	
				
				if(response == ResponseType.FAIL){			
					throw new KmsException(KMSCode.TSM_TRANSFER_FAIL);
				}
				
				this.deleteKeyProfileJob(job, profile);	
			}else{
//				keyProfileBO.transferPairKeyProfile(job);
				List<KeyProfileModel> profiles = keyProfileDao.getPairKeyProfiles(job.getKeySubject());
				
				KeyProfileModel pri = null;
				KeyProfileModel pub = null;
				KeyProfile priProfile = null;
				KeyProfile pubProfile = null;
				
				for(KeyProfileModel profile : profiles){
					if(profile.getKeyTypeCode().equals("PRIVATE")){
						pri = profile;
					}else{
						pub = profile;
					}
				}
				
				try {
					priProfile = ConvertUtil.createKeyProfile(pri);
					pubProfile = ConvertUtil.createKeyProfile(pub);
				} catch (Exception e) {
								
					logger.error("keyProfileBO: transferPairKeyProfile: converting Key Profile: " + e.getMessage());
					
					throw new KmsException(KMSCode.KEY_CREATE_FAIL);
				}
				
				KeyInfoRequestType keyInfo = TransferUtil.createKeyInfo(priProfile, pri, pubProfile, pub);
				//add by shifei 2014-06-18
//				KeyInfoRequestType keyInfoPri = TransferUtil.createKeyInfo(priProfile, pri);
//				KeyInfoRequestType keyInfoPub = TransferUtil.createKeyInfo(pubProfile, pub);
				
				ResponseType response = kiClient.sendKeyInfo(keyInfo);
				//add by shifei 2014-06-18
//				ResponseType responsePri = kiClient.sendKeyInfo(keyInfoPri);
//				ResponseType responsePub = kiClient.sendKeyInfo(keyInfoPub);	
				
//				if(responsePri == ResponseType.FAIL || responsePub == ResponseType.FAIL){
//					throw new KmsException(KMSCode.TSM_TRANSFER_FAIL);
//				}
				
				if(response == ResponseType.FAIL){
				throw new KmsException(KMSCode.TSM_TRANSFER_FAIL);
				}
				
				this.deleteKeyProfileJob(job, profiles);	
			}				
			logger.info("KeyProfileJob finished:" + job.getKeyProfileID() + ":" + job.getKeyProfileVersion());
		}catch(Exception e){
			logger.error("KeyProfileJob: " + e.getMessage());
			
		}
			
		
	}
	

	@Override
	public String generateKeyProfileID(String companyId) {
		
		String seq = keyProfileDao.getKeyProfileSequnce();
		
		StringBuffer id = new StringBuffer();
		id.append(companyId).append("EF").append(seq);
		
		logger.debug("KeyProfileBO: generateKeyProfileID:" + id.toString() ); //ignore

		return id.toString();
	}
	


	@Override
	@Transactional(propagation=Propagation.REQUIRED, readOnly=false, rollbackFor=Exception.class)
	public void importPairKeyProfile(KeyProfileModel priModel, KeyProfileModel pubModel) throws KmsException, Exception {
		this.importKeyProfile(priModel);
		this.importKeyProfile(pubModel);		
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, readOnly=false, rollbackFor=Exception.class)
	public void importKeyProfile(KeyProfileModel model) throws KmsException, Exception {
				
		KeyProfileModel old = keyProfileDao.getKeyProfile(model.getKeyProfileID(), model.getKeyProfileVersion());
		
		if(old != null){
			logger.error("KeyProfileBO: importKeyProfile: Key Profile Already Exists: [KeyProfileID:KeyProfileVersion]: " + old.getKeyProfileID() +":" + old.getKeyProfileVersion());
			throw new KmsException(KMSCode.KEY_ALEADY_EXISTS);
		}		
		
		if(!model.getKeyTypeCode().equals("PUBLIC") && (model.getKeyRoleCode().equals("Transport Key") || model.getKeyRoleCode().equals("Issuer RSA Key"))){
			
			this.checkKeyIndexUnique(model.getKeyIndex());
		}
						
		/*
		 * If key value exists, insert into HSM
		 */
		//modify by shifei 20140926
//		if(model.getKeyValue() != null || !model.getKeyValue().getComponents().isEmpty()){
		if(model.getKeyValue() != null && !model.getKeyValue().getComponents().isEmpty()){
			
			if(model.getKeyTypeCode().equals("SECRET")){
				ValueComponent comp = model.getKeyValue().getComponents().get(0);
				
				if(comp.getValue() != null && !comp.getValue().equals("")){				
			
					String[] attrs = null;
					
					try{
						attrs = ConvertUtil.createKeyAttrs(model);
					}catch(Exception e){
						logger.error("KeyProfileBO: importKeyProfile: generateSecretKey: " + e.getMessage());
						throw new KmsException(KMSCode.KEY_IMPORT_CRYPTO_FAIL);
					}
					
					if(model.getTransportKey() == null){
						try{
							KMSCrypto.getInstance().generateSecretKey(model.getKeySubtypeCode(), (int)model.getKeySize(), model.getTokenLabel(), attrs);
							model.setKeyValueFlag("Y");
						}catch(Exception e){
							logger.error("KeyProfileBO: importKeyProfile: generateSecretKey: " + e.getMessage());
							throw new KmsException(KMSCode.KEY_IMPORT_CRYPTO_FAIL);
						}
					}else{
						
						KeyProfileModel tranModel = keyProfileDao.getKeyProfile(model.getTransportKey().getKeyID(), null);
						
						String mechanism = null;
						
						if(tranModel.getKeySize() == 64){
							mechanism = "DES_CBC";
						}else{
							mechanism = "DES3_CBC";
						}
											
						try{
							KMSCrypto.getInstance().unwrapKey(mechanism, null, tranModel.getKeyLabel(), comp.getValue(), attrs);
							model.setKeyValueFlag("Y");
						}catch(Exception e){
							logger.error("KeyProfileBO: importKeyProfile: generateSensitiveSecretKey: " + e.getMessage());
							throw new KmsException(KMSCode.KEY_IMPORT_CRYPTO_FAIL);
						}					
					}	
					
					// Don't marsah with original value
					model.setTransportKey(null);
					model.setKeyValue(null);
					
					/* Get new key value from hsm for Xml file */
					if(!model.getKeyValueAttribute().isSensitive){
						try{
							String[] attrs0 = KMSCrypto.getInstance().getKeyAttributes(model.getTokenLabel(), model.getKeyLabel());
										
							ConvertUtil.setSecretKeyValue(model, attrs0);
						}catch(KmsException e){
							logger.error("KeyProfileBO: importKeyProfile: setScretKeyValue: retrieve Key Value from HSM: " + e.getMessage());
							
							throw new KmsException(KMSCode.KEY_VALUE_CREATE_FAIL);
						}	
					}
				
				}
			}else{
				
				if(model.getKeyValue() != null){
					try{
						String[] attrs = ConvertUtil.createKeyAttrs(model);					
					
						if(model.getKeyTypeCode().equals("PRIVATE")){
							
							if(model.getTransportKey() == null){
								try{
									KMSCrypto.getInstance().generatePrivateKey(model.getTokenLabel(), attrs, (int)model.getKeySize());
									model.setKeyValueFlag("Y");
								}catch(Exception e){
									logger.error("KeyProfileBO: importKeyProfile: generatePrivateKey: " + e.getMessage());
									throw new KmsException(KMSCode.KEY_IMPORT_CRYPTO_FAIL);
								}
							}else{
								//add by shifei 2014-07-01
								ValueComponent comp = model.getKeyValue().getComponents().get(1);
								KeyProfileModel tranModel = keyProfileDao.getKeyProfile(model.getTransportKey().getKeyID(), null);
								attrs = null;
								attrs = ConvertUtil.createPriKeyAttrs(model);	
								String mechanism = null;
								
								if(tranModel.getKeySize() == 64){
									mechanism = "DES_CBC";
								}else{
									mechanism = "DES3_CBC";
								}
										
								try{
									KMSCrypto.getInstance().unwrapKey(mechanism, null, tranModel.getKeyLabel(), comp.getValue(), attrs);
									model.setKeyValueFlag("Y");
								}catch(Exception e){
									logger.error("KeyProfileBO: importKeyProfile: generateSensitiveSecretKey: " + e.getMessage());
									throw new KmsException(KMSCode.KEY_IMPORT_CRYPTO_FAIL);
								}
							}
						
						}else if(model.getKeyTypeCode().equals("PUBLIC")){
							KMSCrypto.getInstance().generatePublicKey(model.getTokenLabel(), attrs, (int)model.getKeySize());
						}				
						
						model.setKeyValueFlag("Y");
					}catch(Exception e){
						logger.error("KeyProfileBO: importKeyProfile: generatePairKey: " + e.getMessage());
						throw new KmsException(KMSCode.KEY_VALUE_CREATE_FAIL);
					}	
				}	
				
				// Don't marsah with original value
				//model.setTransportKey(null);
				//model.setKeyValue(null);
				
				// Don't marsah with original value
				model.setTransportKey(null);
				model.setKeyValue(null);
				
				/* Get new key value from hsm for Xml file */
				if(!model.getKeyValueAttribute().isSensitive){
					try{
						String[] attrs0 = KMSCrypto.getInstance().getKeyAttributes(model.getTokenLabel(), model.getKeyLabel());
									
						ConvertUtil.setPairKeyValue(model, attrs0);
					}catch(KmsException e){
						logger.error("KeyProfileBO: importKeyProfile: setPairKeyValue: retrieve Key Value from HSM: " + e.getMessage());
						
						throw new KmsException(KMSCode.KEY_VALUE_CREATE_FAIL);
					}	
				}
			}			
		}
		
		model.setKeyProfileXMLFile(new String(this.marshalKeyProfile(model)));
		
		keyProfileDao.insertKeyProfile(model);	
			
//		if(model.getRevisionHistory() != null && !model.getRevisionHistory().isEmpty()){
//			for(KeyProfileRevisionModel hist : model.getRevisionHistory()){
//				keyProfileDao.insertKeyProfileRevisionHistory(hist);
//			}
//		}
		
		if(model.isKeyValueSet()){
			keyProfileDao.insertKeyProfileJob(ConvertUtil.createKeyProfileJob(model));
		}
	}

	@Override
	public void exportKeyProfile(ProfileExportDTO dto) throws KmsException {
		
		KeyProfileModel expModel = keyProfileDao.getKeyProfile(dto.getKeyProfileID(), dto.getKeyProfileVersion());
		
		KeyProfileModel tranModel = null;
		
		String[] attrs0 = null;		
			
		if(expModel.getKeyValueAttribute().isSensitive){
			tranModel = keyProfileDao.getKeyProfile(dto.getTranProfileID(), dto.getTranProfileVersion());
			
			if(expModel.getKeyTypeCode().equals("SECRET")){
				String mechanism = null;
				//modify by shifei 2014-07-16
//				if(expModel.getKeySize() == 64){
				if(tranModel.getKeySize() == 64){
					mechanism = "DES_CBC";
				}else{
					mechanism = "DES3_CBC";
				}
				
				String wrappedValue = null;
				try{
					wrappedValue = KMSCrypto.getInstance().wrapKey(mechanism, null, tranModel.getKeyLabel(), expModel.getKeyLabel());
					
				}catch(Exception e){
					logger.error("KeyProfileBO: exportKeyProfile: wrapSensitiveSecretKey: " + e.getMessage());
					throw new KmsException(KMSCode.KEY_EXPORT_FAIL);
				}
				
				ConvertUtil.createSensitiveKeyValueModel(expModel, tranModel, wrappedValue);
			}else{
				// Export Sensitive PairKey
				//add by shifei 2014-07-01
				if(expModel.getKeyTypeCode().equalsIgnoreCase("PRIVATE")){
					String mechanism = null;
					//modify by shifei 2014-07-16
//					if(expModel.getKeySize() == 64){
					if(tranModel.getKeySize() == 64){
						mechanism = "DES_CBC";
					}else{
						mechanism = "DES3_CBC";
					}
					
					String wrappedValue = null;
					try{
						wrappedValue = KMSCrypto.getInstance().wrapKey(mechanism, null, tranModel.getKeyLabel(), expModel.getKeyLabel());
					}catch(Exception e){
						logger.error("KeyProfileBO: exportKeyProfile: wrapSensitiveSecretKey: " + e.getMessage());
						throw new KmsException(KMSCode.KEY_EXPORT_FAIL);
					}
					
					ConvertUtil.createSensitivePriKeyValueModel(expModel, tranModel, wrappedValue);
				}else{
					ConvertUtil.createSensitivePubKeyValueModel(expModel);
				}

			}
		}else{
			try{
				attrs0 = KMSCrypto.getInstance().getKeyAttributes(expModel.getTokenLabel(), expModel.getKeyLabel());
			}catch(Exception e){
				logger.error("KeyProfileBO: exportKeyProfile: wrapInsensitiveKey: " + e.getMessage());
				
				throw new KmsException(KMSCode.KEY_EXPORT_FAIL);
			}
			
			ConvertUtil.createKeyValueModel(expModel, tranModel, attrs0);
		}			
				
		try{
			dto.setExportXmlFile(new String(this.marshalKeyProfile(expModel)));
		}catch(Exception e){
			logger.error("KeyProfileBO: exportKeyProfile: " + e.getMessage());
			throw new KmsException(KMSCode.KEY_EXPORT_FAIL);
		}		
	}

	@Override
	public List<KeyProfileJobModel> getKeyProfilesToTransfer() {
		return keyProfileDao.getKeyProfilesToTransfer();
	}
	
	@Override
	public KeyProfileJobModel getKeyProfileToTransfer(String keyProfileID, String keyProfileVersion) {
		return keyProfileDao.getKeyProfileToTransfer(keyProfileID, keyProfileVersion);
	}
	

	@Override	
	public void transferSecretKeyProfile(KeyProfileJobModel job) throws KmsException {
		
		KeyProfileModel profile = keyProfileDao.getKeyProfile(job.getKeyProfileID(), job.getKeyProfileVersion());
		
		KeyProfile gpMsg = null;
		
		try {
			gpMsg = ConvertUtil.createKeyProfile(profile);
		} catch (Exception e) {
			logger.error("KeyProfileBO: transferSecretKeyProfile: converting Key Profile: " + e.getMessage());
			
			throw new KmsException(KMSCode.KEY_CREATE_FAIL);
		}
		
		KeyInfoRequestType keyInfo = TransferUtil.createKeyInfo(gpMsg, profile);
		
		ResponseType response = kiClient.sendKeyInfo(keyInfo);	
		
		if(response == ResponseType.FAIL){			
			throw new KmsException(KMSCode.TSM_TRANSFER_FAIL);
		}
		
		this.deleteKeyProfileJob(job, profile);		
	}
	
	@Override	
	public void transferPairKeyProfile(KeyProfileJobModel job) throws KmsException {
		
		List<KeyProfileModel> profiles = keyProfileDao.getPairKeyProfiles(job.getKeySubject());
		
		KeyProfileModel pri = null;
		KeyProfileModel pub = null;
		KeyProfile priProfile = null;
		KeyProfile pubProfile = null;
		
		for(KeyProfileModel profile : profiles){
			if(profile.getKeyTypeCode().equals("PRIVATE")){
				pri = profile;
			}else{
				pub = profile;
			}
		}
		
		try {
			priProfile = ConvertUtil.createKeyProfile(pri);
			pubProfile = ConvertUtil.createKeyProfile(pub);
		} catch (Exception e) {
						
			logger.error("keyProfileBO: transferPairKeyProfile: converting Key Profile: " + e.getMessage());
			
			throw new KmsException(KMSCode.KEY_CREATE_FAIL);
		}
		
		KeyInfoRequestType keyInfo = TransferUtil.createKeyInfo(priProfile, pri, pubProfile, pub);
		
		ResponseType response = kiClient.sendKeyInfo(keyInfo);	
		
		if(response == ResponseType.FAIL){
			throw new KmsException(KMSCode.TSM_TRANSFER_FAIL);
		}
		
		this.deleteKeyProfileJob(job, profiles);		
	}
	

	@Override
	@Transactional(propagation=Propagation.REQUIRED, readOnly=false, rollbackFor=Exception.class)
	public void deleteKeyProfileJob(KeyProfileJobModel job, KeyProfileModel profile) {
		
		keyProfileDao.deleteKeyProfileJob(job);
		keyProfileDao.updateTransferFlag(profile);
	}	
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED, readOnly=false, rollbackFor=Exception.class)
	public void deleteKeyProfileJob(KeyProfileJobModel job, List<KeyProfileModel> profiles) {
		
		keyProfileDao.deleteKeyProfileJob(job);
		
		for(KeyProfileModel profile : profiles){
			keyProfileDao.updateTransferFlag(profile);
		}		
	}	

	@Override
	@Transactional(propagation=Propagation.REQUIRED, readOnly=false, rollbackFor=Exception.class)
	public void logTransferException(KeyProfileJobModel job) {
		keyProfileDao.logTransferException(job);		
	}

	@Override
	public byte[] marshalKeyProfile(KeyProfileModel model) throws KmsException {
		KeyProfile gpMsg = null;
		try {
			gpMsg = ConvertUtil.createKeyProfile(model);
		} catch (Exception e) {
			
			logger.error("KeyProfileBO: marshalKeyProfile: converting Key Profile: " + e.getMessage());
			
			throw new KmsException(KMSCode.KEY_PROFILE_PARSING_FAIL);
		}
		
		byte[] xmlBytes = GpMsgBinder.getInstance().marshal(gpMsg);
		
		return xmlBytes;
	}

	@Override
	public void checkKeyIndexUnique(int keyIndex) throws KmsException {
		
		synchronized(indexes){
			if(indexes.contains(keyIndex)){
				throw new KmsException(KMSCode.KEY_INDEX_NOT_UNIQUE);
			}
			
			int count = keyProfileDao.checkKeyIndexUnique(keyIndex);			
			
			if(count > 0){
				throw new KmsException(KMSCode.KEY_INDEX_NOT_UNIQUE);
			}
			
			indexes.add(keyIndex);
		}		
	}

	@Override
	public void clearKeyIndex(int keyIndex) {
		if(keyIndex > 0){
			synchronized(indexes){
				this.indexes.remove(keyIndex);
			}		
		}
	}


}