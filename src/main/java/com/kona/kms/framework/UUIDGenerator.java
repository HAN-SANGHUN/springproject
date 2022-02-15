package com.kona.kms.framework;

import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



public class UUIDGenerator {
	private static final Logger logger = LoggerFactory.getLogger(UUIDGenerator.class);
	
	static public String getNewId() {
		String uuid = UUID.randomUUID().toString();
		logger.debug("UUIDGenerator::getNewId: len " + uuid.length() + " > " + uuid);
		return uuid;
//		return "1.1.0";
	}
}