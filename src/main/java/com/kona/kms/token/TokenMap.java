/**
 * 
 */
package com.kona.kms.token;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.kona.kms.token.dao.KeyInfoDao;
import com.kona.kms.token.model.LTokenModel;

public class TokenMap {
	private static final Logger logger = LoggerFactory.getLogger(TokenMap.class);
	
	@Autowired
	KeyInfoDao keyInfoDao;
	
	static TokenMap m_instance;
	public static TokenMap getInstance() {
		logger.debug("TokenMap::getInstance: invoked....");
		if (m_instance == null) {
			m_instance = new TokenMap();
		}
		return m_instance;
	}
	private TokenMap() {
		logger.debug("TokenMap::TokenMap: invoked....");
		tmap = new ConcurrentHashMap<String, LTokenModel>();
	}
	
	static ConcurrentHashMap<String,LTokenModel> tmap;

	public void add(LTokenModel tinfo) {
		tmap.put(tinfo.getTokenLabel(), tinfo);
	}
	
	public void delete(String tokenLabel) {
		tmap.remove(tokenLabel);
	}
	
	public LTokenModel find(String tokenLabel) {
		logger.debug("LTokenModel::find: [{}]", tokenLabel);
		return tmap.get(tokenLabel);
	}
	
	public void load() {
		logger.debug("TokenMap::load: start.....");
		
/*
 * 		물리적인 각각의 HSM의 SLOT 및 Token , Key의 정보는 동일하다.
 * 		, 마스터 HSM의 개념을 적용하기 때문에 첫번째 정보만 Get(0)한다.   
 * */
		List<LTokenModel> tokenInfolist = KeyManager.getInstance().lhsmConf.get(0).getLtokenModel();
		
		if(tokenInfolist != null && !tokenInfolist.isEmpty()){
			logger.debug("TokenMap::load: done getLtokenModel");
			for(LTokenModel tokenInfo : tokenInfolist) {
				if(tokenInfo.getCompanyID() != null)
					add(tokenInfo);
			}
		}		

		printTokenInfo();
		
	}
	
	public void printTokenInfo() {
		logger.debug("TokenMap::printKeyInfo: Sart ---------------------");

		Iterator<String> iterator = tmap.keySet().iterator();
		while (iterator.hasNext()) {
			String key = (String) iterator.next();
		    logger.debug("TokenMap::printTokenInfo: {} : {}", key, tmap.get(key));
		}
		
		logger.debug("TokenMap::printKeyInfo:  End---------------------");
	}
	
}
