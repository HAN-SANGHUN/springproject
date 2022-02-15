package com.kona.kms.web.ss.user.bo;

import java.util.Date;
import java.util.List;

import com.kona.kms.KmsException;
import com.kona.kms.web.ss.user.model.KmsUserModel;
import com.kona.kms.web.utils.GridPageData;

public interface KmsUserBO {

	KmsUserModel getKmsUser(String id);
	
	GridPageData<KmsUserModel> getKmsUserList(KmsUserModel condition);
	
	List<KmsUserModel> getKmsUserListAll(KmsUserModel condition);
	
	void updateKmsUser(KmsUserModel model);
	
	void insertKmsUser(KmsUserModel model) throws KmsException;
	
	void deleteKmsUser(String[] userId, String updater);
	
	int inactiveUsers(String companyID, String updater, Date now);
}
