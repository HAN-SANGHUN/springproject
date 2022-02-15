package com.kona.kms.web.profile.bo;

import java.util.List;

import com.kona.kms.KmsException;
import com.kona.kms.web.profile.model.KeyProfileJobModel;
import com.kona.kms.web.profile.model.KeyProfileModel;
import com.kona.kms.web.profile.model.KeyProfileModel1;
import com.kona.kms.web.profile.model.KeyValueDTO;
import com.kona.kms.web.profile.model.ProfileExportDTO;
import com.kona.kms.web.utils.GridPageData;
import com.kona.kms.web.utils.ProfileIDAttr;

public interface KeyProfileBO {
	
	List<KeyProfileModel> getPairKeyProfiles(String keySubject);
	
	KeyProfileModel getKeyProfile(String id, String version);
	
	GridPageData<KeyProfileModel> getKeyProfileList(KeyProfileModel condition);
	
	List<KeyProfileModel> getKeyProfileListAll(KeyProfileModel condition);
	
	void updateKeyProfile(KeyProfileModel model) throws KmsException;
	
	void updatePairKeyProfile(KeyProfileModel priModel, KeyProfileModel1 pubModel) throws KmsException;
	
	ProfileIDAttr insertPairKeyProfile(KeyProfileModel priModel, KeyProfileModel1 pubModel) throws KmsException;
	
	ProfileIDAttr insertKeyProfile(KeyProfileModel model) throws KmsException;	
	
	String getSecretKeyKCV(String mech, int keySize, String text) throws KmsException;
	
	KeyValueDTO setSecretKeyValue(KeyValueDTO dto) throws KmsException, Exception;
	
	void setPairKeyValue(KeyValueDTO dto) throws KmsException;
	
	void deleteKeyProfile(String[] profileID, String updater);
	
	void sendKeyProfile(String keyProfileID, String keyProfileVersion, String updater) throws KmsException;
	
	String generateKeyProfileID(String companyId);

	void importPairKeyProfile(KeyProfileModel priModel, KeyProfileModel pubModel0) throws KmsException, Exception;

	void importKeyProfile(KeyProfileModel model) throws KmsException, Exception;

	void exportKeyProfile(ProfileExportDTO dto) throws KmsException;

	List<KeyProfileJobModel> getKeyProfilesToTransfer();
	
//	KeyProfileJobModel getKeyProfileToTransfer();

	void transferSecretKeyProfile(KeyProfileJobModel job) throws KmsException;
	
	void transferPairKeyProfile(KeyProfileJobModel job) throws KmsException;
	
	void deleteKeyProfileJob(KeyProfileJobModel job, KeyProfileModel profile);
	
	void deleteKeyProfileJob(KeyProfileJobModel job, List<KeyProfileModel> profile);

	void logTransferException(KeyProfileJobModel job);
	
	byte[] marshalKeyProfile(KeyProfileModel model) throws KmsException;	
	
	void checkKeyIndexUnique(int keyIndex) throws KmsException;
	
	void clearKeyIndex(int keyIndex);

	KeyProfileJobModel getKeyProfileToTransfer(String keyProfileID, String keyProfileVersion);
	
}
