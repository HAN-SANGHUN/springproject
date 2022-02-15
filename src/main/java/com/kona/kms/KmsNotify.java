package com.kona.kms;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import sun.security.pkcs11.wrapper.*;

public class KmsNotify implements CK_NOTIFY {

	private static final Logger logger = LoggerFactory.getLogger(KmsNotify.class);

	@Override
	public void CK_NOTIFY(long hSession, long event, Object pApplication)
			throws PKCS11Exception {
		logger.debug("KmsNotify::CK_NOTIFY: hSession {}, event {}", hSession, event);

	}

}
