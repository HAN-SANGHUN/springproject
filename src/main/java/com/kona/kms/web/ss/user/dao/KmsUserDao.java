package com.kona.kms.web.ss.user.dao;

import java.util.Date;
import java.util.List;

import com.kona.kms.web.ss.user.model.KmsUserModel;

public interface KmsUserDao {

	KmsUserModel getKmsUser(String id);	

	List<KmsUserModel> getKmsUserListAll(KmsUserModel condition);
	
	List<KmsUserModel> getKmsUserList(KmsUserModel condition);
	
	int getTotalRecord(KmsUserModel condition);	
	
	int updateKmsUser(KmsUserModel model);
	
	int insertKmsUser(KmsUserModel model);

	int deleteKmsUser(KmsUserModel model);

	int checkUserIDUnique(String userId);

	int inactiveUsers(String companyID, String updater, Date now);

	int checkKmsManagerRegistered();
}
