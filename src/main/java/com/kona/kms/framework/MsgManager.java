package com.kona.kms.framework;


import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.NoSuchMessageException;
import org.springframework.context.support.MessageSourceAccessor;


public class MsgManager {
	
	private static final Logger logger = LoggerFactory.getLogger(MsgManager.class);
	public static final String DEFALUT_MSG = "NoSuchMessageException";
	
	@Autowired
	MessageSourceAccessor messageSourceAccessor;
	
	static MsgManager m_instance;
	public static MsgManager getInstance() {
		logger.debug("MsgManager::getInstance: invoked....");

		if (m_instance == null) {
			m_instance = new MsgManager();
		}
		return m_instance;
	}

	private MsgManager() {
		logger.debug("MsgManager::MsgManager: invoked....");
	}
	
	public String getMessage(String msgCode) {
		String msg;
		try {
			logger.debug("MsgManager::getMessage: %s",msgCode);
			msg = messageSourceAccessor.getMessage(msgCode);
		}
		catch (NoSuchMessageException em){
        	//logger.error("MsgManager::getMessage: msgCode {} not found [{}]", msgCode, em.toString());
			msg = DEFALUT_MSG;
		}
		return msg;
	}
	
	public String getMessage(String msgCode, String[] anchors) {
		String msg;
		try {
			logger.debug("MsgManager::getMessage: %s, String[] anchors: %s",msgCode,anchors[0]);
			msg = messageSourceAccessor.getMessage(msgCode, anchors);
		}
		catch (NoSuchMessageException em){
			msg = DEFALUT_MSG;
		}
		return msg;
	}

	public String getMessage(String msgCode, Locale locale) {
		String msg;
		try {
			msg = messageSourceAccessor.getMessage(msgCode, locale);
		}
		catch (NoSuchMessageException em){
			msg = DEFALUT_MSG;
		}
		return msg;
	}
	
	public String getMessage(String msgCode, String[] anchors, Locale locale) {
		String msg;
		try {
			msg = messageSourceAccessor.getMessage(msgCode, anchors, locale);
		}
		catch (NoSuchMessageException em){
			msg = DEFALUT_MSG;
		}
		return msg;
	}

}
