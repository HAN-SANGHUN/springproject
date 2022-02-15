/**
 * 
 */
package com.kona.kms.token;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.kona.kms.token.dao.KeyInfoDao;
import com.kona.kms.token.model.KeyInfo;

import java.util.concurrent.ConcurrentHashMap;

public class KeyMap {
	private static final Logger logger = LoggerFactory.getLogger(KeyMap.class);
	
	@Autowired
	KeyInfoDao keyInfoDao;

	static ConcurrentHashMap<String,KeyInfo> kmap;
	static KeyMap m_instance;
	
	public static KeyMap getInstance() {
		logger.debug("KeyMap::getInstance: invoked....");
		if (m_instance == null) {
			m_instance = new KeyMap();
		}
		return m_instance;
	}
	
	private KeyMap() {
		logger.debug("KeyMap::KeyMap: invoked....");
		kmap = new ConcurrentHashMap<String, KeyInfo>();
	}
	
//		
	public void load() {
		logger.debug("KeyMap::load: start.....");

//	keyprofile table과 ltoken 테이블의 조인을 통해 company id인 tokenlabel이 생성된 keylabel을 select한다.
		List<KeyInfo> keyInfolist = keyInfoDao.getKeyInfoAll();

		for(KeyInfo keyInfo : keyInfolist) {
			add(keyInfo);
		}
		printKeyInfo();
	}

	public void add(KeyInfo kinfo) {
		kmap.put(kinfo.getKeyLabel(), kinfo);
	}
	
	public void delete(String keyLabel) {
		kmap.remove(keyLabel);
		printKeyInfo();
	}
	
	public KeyInfo find(String keyLabel) {
		return kmap.get(keyLabel);
	}
	
	// Add by ChengYanwei 20150727
		// Clear all element.
	public void clear() {
		kmap.clear();
	}
	
	public void printKeyInfo() {
		logger.debug("KeyMap::printKeyInfo: ---------------------");

		Iterator<String> iterator = kmap.keySet().iterator();
		while (iterator.hasNext()) {
			String key = (String) iterator.next();
		    logger.debug("KeyMap::printKeyInfo: {} : {}", key, kmap.get(key));
		}
	}
	
}
