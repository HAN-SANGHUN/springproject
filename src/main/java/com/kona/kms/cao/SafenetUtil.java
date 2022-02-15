package com.kona.kms.cao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import safenet.jcprov.CK_SESSION_HANDLE;
import safenet.jcprov.Cryptoki;
import safenet.jcprov.constants.CK_RV;
import safenet.jcprov.constants.CK_USER_TYPE;


public class SafenetUtil {

	private static final Logger logger = LoggerFactory.getLogger(BcCryptoUtil.class);
		
	static SafenetUtil m_instance;
	public static SafenetUtil getInstance() {
		logger.debug("SafenetUtil::getInstance: invoked....");
		if (m_instance == null) {
			m_instance = new SafenetUtil();
		}
		return m_instance;
	}
	
	private SafenetUtil() {
		logger.debug("SafenetUtil::SafenetUtil: invoked....");
		
	}
	
	public void initToken(long slotIx, String soPin, String userPin, String tokenLabel) {
		logger.debug("SafenetUtil::initToken: start slotIx:tokenLabel [{}:{}]", slotIx, tokenLabel);
		
		CK_RV rv = null;

/*
		CK_C_INITIALIZE_ARGS	cArgs;
		cArgs = new CK_C_INITIALIZE_ARGS(0);
		rv = Cryptoki.C_Initialize(cArgs);
    	if(rv.intValue()!=0){
    		//logger.error("C_Initialize error " + Util.intToHex(rv.intValue()));
    		//return Util.intToHex(rv.intValue());
    	}
*/    	
    	// SOPin.len >= 4
    	byte[] bSoPin= soPin.getBytes();

    	logger.debug("SafenetUtil::initToken: C_InitToken soPin = {}", soPin);
    	
     	rv = Cryptoki.C_InitToken(slotIx, bSoPin, bSoPin.length, tokenLabel.getBytes());
    	logger.debug("SafenetUtil::initToken: C_InitToken rv = {}", rv);
    	
    	this.initUserPin(slotIx, soPin, userPin);
	}	

	public void initUserPin(long slotIx, String soPin, String userPin) {
		logger.debug("SafenetUtil::initUserPin: start slotIx [{}]", slotIx);
		CK_RV rv = new CK_RV();
		CK_SESSION_HANDLE hSession = new CK_SESSION_HANDLE();
		
		this.getSession(slotIx, hSession, "SO", soPin);
		     	
    	byte[] pin= userPin.getBytes();
     	rv = Cryptoki.C_InitPIN(hSession, pin, pin.length);
    	logger.debug("SafenetUtil::initUserPin: C_InitPIN rv = {}", rv);
    	
    	this.closeSession(hSession);
	}		
	
	public void setUserPin(long slotIx, String oldPin, String newPin) {
		logger.debug("SafenetUtil::setUserPin: start slotIx [{}]", slotIx);
		CK_RV rv = new CK_RV();
		CK_SESSION_HANDLE hSession = new CK_SESSION_HANDLE();
		
		this.getSession(slotIx, hSession, "USER", oldPin);

		byte[] bOldPin= oldPin.getBytes();
    	byte[] bNewPin= newPin.getBytes();
     	rv = Cryptoki.C_SetPIN(hSession, bOldPin, bOldPin.length, bNewPin, bNewPin.length);
    	logger.debug("SafenetUtil::setUserPin: C_SetPIN rv = {}", rv);
    	
    	this.closeSession(hSession);
	}	
	
	public void getSession(long slotIx, CK_SESSION_HANDLE hSession, String sUserType, String pin) {
		logger.debug("SafenetUtil::getSession: start slotIx [{}]", slotIx);
		CK_RV rv = null;
		CK_USER_TYPE userType = new CK_USER_TYPE();
		
		rv = Cryptoki.C_OpenSession(slotIx, safenet.jcprov.constants.CKF.RW_SESSION, null, null, hSession);
		logger.debug("SafenetUtil::getSession: C_OpenSession rv = {}", rv);
		
		if(sUserType.equalsIgnoreCase("SO"))
			userType = safenet.jcprov.constants.CKU.SO;
		else
			userType = safenet.jcprov.constants.CKU.USER;
		
		byte[] bPin= pin.getBytes();
     	rv = Cryptoki.C_Login(hSession, userType, bPin, bPin.length);
     	logger.debug("SafenetUtil::getSession: C_Login rv = {}", rv);
	}
	
	public void closeSession(CK_SESSION_HANDLE hSession) {
		CK_RV rv = Cryptoki.C_CloseSession(hSession);
		logger.debug("SafenetUtil::closeSession: C_CloseSession rv = {}", rv);
	}

}
