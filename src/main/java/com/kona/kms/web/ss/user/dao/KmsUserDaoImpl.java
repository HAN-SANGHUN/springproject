package com.kona.kms.web.ss.user.dao;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kona.kms.web.ss.user.model.KmsUserModel;

@Repository
public class KmsUserDaoImpl implements KmsUserDao{

	@Autowired
    private SqlSessionTemplate sqlSessionTemplate;

	@Override
	public KmsUserModel getKmsUser(String id) {
		KmsUserModel user = sqlSessionTemplate.selectOne("user.getKmsUser", id);
		
		return user;
	}

	@Override
	public List<KmsUserModel> getKmsUserListAll(KmsUserModel condition) {
		return sqlSessionTemplate.selectList("user.getKmsUserListAll", condition);
	}

	@Override
	public List<KmsUserModel> getKmsUserList(KmsUserModel condition) {
		return sqlSessionTemplate.selectList("user.getKmsUserList", condition);
	}

	@Override
	public int getTotalRecord(KmsUserModel condition) {
		return sqlSessionTemplate.selectOne("user.getTotalRecord", condition);
	}

	@Override
	public int updateKmsUser(KmsUserModel model) {
		return sqlSessionTemplate.update("user.updateKmsUser", model);
	}

	@Override
	public int insertKmsUser(KmsUserModel model) {
		return sqlSessionTemplate.insert("user.insertKmsUser", model);
	}

	@Override
	public int deleteKmsUser(KmsUserModel model) {
		return sqlSessionTemplate.update("user.deleteKmsUser", model);
	}

	@Override
	public int checkUserIDUnique(String userId) {
		return sqlSessionTemplate.selectOne("user.checkUserIDUnique", userId);
	}

	@Override
	public int inactiveUsers(String companyID, String updater, Date now) {
		
		ConcurrentHashMap<Object, Object> values = new ConcurrentHashMap<Object, Object>();
		values.put("companyID", companyID);
		values.put("updateUserId", updater);
		values.put("updateDate", now);
		
		return sqlSessionTemplate.update("user.deleteKmsUsers", values);
	}

	@Override
	public int checkKmsManagerRegistered() {
		return sqlSessionTemplate.selectOne("user.checkKmsManagerRegistered");
	}
}
