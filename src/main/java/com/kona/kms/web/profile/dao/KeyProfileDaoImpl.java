package com.kona.kms.web.profile.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kona.kms.web.profile.model.KeyProfileJobModel;
import com.kona.kms.web.profile.model.KeyProfileModel;
import com.kona.kms.web.profile.model.KeyProfileRevisionModel;

@Repository
public class KeyProfileDaoImpl implements KeyProfileDao{

	@Autowired
    private SqlSessionTemplate sqlSessionTemplate;


	@Override
	public List<KeyProfileModel> getKeyProfileListAll(KeyProfileModel condition) {
		return sqlSessionTemplate.selectList("profile.getKeyProfileListAll", condition);
	}
	
	@Override
	public KeyProfileModel getKeyProfile(String id, String version){
		
		ConcurrentHashMap<String, Object> values = new ConcurrentHashMap<String, Object>();
		
		values.put("keyProfileID", id);
//		values.put("keyProfileVersion", version);
		//modify by shifei
		if (version != null){
			values.put("keyProfileVersion", version);
		}
		
		KeyProfileModel value = sqlSessionTemplate.selectOne("profile.getKeyProfile", values);
				
		return value;
	}

	@Override
	public List<KeyProfileModel> getKeyProfileList(KeyProfileModel condition) {
		return sqlSessionTemplate.selectList("profile.getKeyProfileList", condition);
	}	

	@Override
	public int getTotalRecord(KeyProfileModel condition) {
		return sqlSessionTemplate.selectOne("profile.getTotalRecord", condition);
	}


	@Override
	public List<KeyProfileModel> getPairKeyProfiles(String keySubject) {
		return sqlSessionTemplate.selectList("profile.getPairKeyProfiles", keySubject);
	}
	

	@Override
	public int updateKeyProfile(KeyProfileModel model) {
		return sqlSessionTemplate.update("profile.updateKeyProfile", model);		
	}

	@Override
	public int insertKeyProfile(KeyProfileModel model) {
		
		return sqlSessionTemplate.insert("profile.insertKeyProfile", model);
		
	}

	@Override
	public int insertKeyProfileRevisionHistory(KeyProfileRevisionModel model) {
		
		return sqlSessionTemplate.insert("profile.insertKeyProfileRevisionHistory", model);
	}

	@Override
	public int updateKeyValue(KeyProfileModel model) {
		return sqlSessionTemplate.update("profile.updateKeyValue", model);
	}
	
	@Override
	public int updateTransferFlag(KeyProfileModel model) {
		return sqlSessionTemplate.update("profile.updateTransferFlag", model);
	}

	@Override
	public int deleteKeyProfile(KeyProfileModel model) {
		return sqlSessionTemplate.update("profile.deleteKeyProfile", model);
	}	

	@Override
	public int deleteKeyProfilePermanent(KeyProfileModel model) {
//		sqlSessionTemplate.delete("deleteKeyProfileRevisionList", model.getKeyProfileID());
		return sqlSessionTemplate.delete("profile.deleteKeyProfilePermanant", model);		
	}

	@Override
	public String getKeyProfileSequnce() {
		return sqlSessionTemplate.selectOne("profile.getKeyProfileSequence");
	}

	@Override
	public List<KeyProfileRevisionModel> getProfileResvisionList(String id, String version) {
		ConcurrentHashMap<String, Object> values = new ConcurrentHashMap<String, Object>();
		
		values.put("keyProfileID", id);
//		values.put("keyProfileVersion", version);
		if (version != null){
			values.put("keyProfileVersion", version);
		}
		
		return sqlSessionTemplate.selectList("profile.getKeyProfileRevisionList", values);
		
	}

	@Override
	public int checkKeyLabelUnique(String keyLabel) {
		
		return sqlSessionTemplate.selectOne("profile.checkKeyLabelUnique", keyLabel);
	}

	@Override
	public int checkKeyIndexUnique(int keyIndex) {
		return sqlSessionTemplate.selectOne("profile.checkKeyIndexUnique", keyIndex);
	}

	@Override
	public List<KeyProfileJobModel> getKeyProfilesToTransfer() {
		return sqlSessionTemplate.selectList("profile.getKeyProfilesToTransfer");
	}
	
	@Override
	public KeyProfileJobModel getKeyProfileToTransfer(String keyProfileID, String keyProfileVersion) {
		ConcurrentHashMap<String, Object> values = new ConcurrentHashMap<String, Object>();
		
		values.put("keyProfileID", keyProfileID);
		if (keyProfileVersion != null){
			values.put("keyProfileVersion", keyProfileVersion);
		}
		return sqlSessionTemplate.selectOne("profile.getKeyProfileToTransfer", values);
	}
	

	@Override
	public int deleteKeyProfileJob(KeyProfileJobModel job) {
//		return sqlSessionTemplate.delete("profile.deleteKeyProfileJob", job.getSequence());	
		//modify by shifei 2014-06-18
		return sqlSessionTemplate.delete("profile.deleteKeyProfileJob", job.getKeySubject());
	}

	@Override
	public void logTransferException(KeyProfileJobModel job) {
		sqlSessionTemplate.update("profile.logTransferException", job);		
	}

	@Override
	public int insertKeyProfileJob(KeyProfileJobModel job) {
		
		return sqlSessionTemplate.insert("profile.insertKeyProfileJob", job);
	}

}
