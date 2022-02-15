package com.kona.kms.web.profile.dao;

import java.util.List;

import com.kona.kms.web.profile.model.KeyProfileJobModel;
import com.kona.kms.web.profile.model.KeyProfileModel;
import com.kona.kms.web.profile.model.KeyProfileRevisionModel;

public interface KeyProfileDao {

	KeyProfileModel getKeyProfile(String id, String version);
	
	List<KeyProfileModel> getKeyProfileListAll(KeyProfileModel condition);
	
	List<KeyProfileModel> getKeyProfileList(KeyProfileModel condition);

	List<KeyProfileModel> getPairKeyProfiles(String keySubject);
	
	int getTotalRecord(KeyProfileModel condition);
	
	int updateKeyProfile(KeyProfileModel model);	
	
	int insertKeyProfile(KeyProfileModel model);
	
	int insertKeyProfileRevisionHistory(KeyProfileRevisionModel model);
	
	int updateKeyValue(KeyProfileModel model);
	
	int updateTransferFlag(KeyProfileModel model);

	int deleteKeyProfile(KeyProfileModel model);

	int deleteKeyProfilePermanent(KeyProfileModel model);

	String getKeyProfileSequnce();

	List<KeyProfileRevisionModel> getProfileResvisionList(String id, String version);
	
	int checkKeyLabelUnique(String keyLabel);
	
	int checkKeyIndexUnique(int keyIndex);

	List<KeyProfileJobModel> getKeyProfilesToTransfer();
	
	KeyProfileJobModel getKeyProfileToTransfer(String keyProfileID, String keyProfileVersion);

	int deleteKeyProfileJob(KeyProfileJobModel job);

	void logTransferException(KeyProfileJobModel job);
	
	int insertKeyProfileJob(KeyProfileJobModel job);
}
