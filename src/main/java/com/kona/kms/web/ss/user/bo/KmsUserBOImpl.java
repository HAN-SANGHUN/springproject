package com.kona.kms.web.ss.user.bo;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.PasswordEncoder;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.kona.kms.KMSCode;
import com.kona.kms.KmsException;
import com.kona.kms.web.UserDetailServiceImpl;
import com.kona.kms.web.ss.user.dao.KmsUserDao;
import com.kona.kms.web.ss.user.model.KmsUserModel;
import com.kona.kms.web.utils.GridPageData;

@Service
@Transactional(propagation=Propagation.REQUIRED, readOnly=true)
public class KmsUserBOImpl implements KmsUserBO{
	
	@Autowired
	private KmsUserDao kmsUserDao;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public KmsUserModel getKmsUser(String id) {
		return kmsUserDao.getKmsUser(id);
	}

	@Override
	public GridPageData<KmsUserModel> getKmsUserList(KmsUserModel condition) {
		List<KmsUserModel> values = kmsUserDao.getKmsUserList(condition);
		
		int totalRecords = 0;
		if(!values.isEmpty()){
			totalRecords = kmsUserDao.getTotalRecord(condition);
		}
		
		return new GridPageData<KmsUserModel>(condition.getRowSize(), condition.getPage(), totalRecords, values);
	}

	@Override
	public List<KmsUserModel> getKmsUserListAll(KmsUserModel condition) {
		return kmsUserDao.getKmsUserListAll(condition);
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, readOnly=false, rollbackFor=Exception.class)
	public void updateKmsUser(KmsUserModel model) {
		
		KmsUserModel old = kmsUserDao.getKmsUser(model.getUserId());
		
		/*
		 * check if password changed
		 */
		if(!old.getUserPwd().equals(model.getUserPwd())){
			model.setUserPwd(passwordEncoder.encodePassword(model.getUserPwd(), model.getUserId()));
		}
		
		kmsUserDao.updateKmsUser(model);		
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, readOnly=false, rollbackFor=Exception.class)
	public synchronized void insertKmsUser(KmsUserModel model) throws KmsException {
		
		int count = kmsUserDao.checkUserIDUnique(model.getUserId());
		
		if(count > 0){
			throw new KmsException(KMSCode.USER_ID_NOT_UNIQUE);
		}
		
		model.setUserPwd(passwordEncoder.encodePassword(model.getUserPwd(), null));
				
		kmsUserDao.insertKmsUser(model);		
		
		if(model.getUserRoleCode().equals("KMS Manager") && !UserDetailServiceImpl.isAdminInit()){
			UserDetailServiceImpl.setAdminInitialized();
		}
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, readOnly=false, rollbackFor=Exception.class)
	public void deleteKmsUser(String[] userIDs, String updater) {
		
		Date now = new Date();
		
		for(String userId : userIDs){
			KmsUserModel user = kmsUserDao.getKmsUser(userId);
			user.setActiveStatusCode("Inactive");
			user.setUpdateDate(now);
			user.setUpdateUserId(updater);
			
			kmsUserDao.deleteKmsUser(user);
			
		}		
	}
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED, readOnly=false, rollbackFor=Exception.class)
	public int inactiveUsers(String companyID, String updater, Date now) {
		return kmsUserDao.inactiveUsers(companyID, updater, now);
	}
	
	public static void main(String[] args){
		PasswordEncoder encoder = new ShaPasswordEncoder();
		
		System.out.println(encoder.encodePassword("admin", null));
	}

}
