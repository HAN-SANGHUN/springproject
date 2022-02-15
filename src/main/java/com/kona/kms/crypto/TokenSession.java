package com.kona.kms.crypto;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.kona.kms.KmsNotify;

import sun.security.pkcs11.wrapper.*;

@SuppressWarnings("restriction")

public class TokenSession {

	private static final Logger logger = LoggerFactory.getLogger(TokenSession.class);
	
	public PKCS11 p11;
	public String hsmId;
	public long slotIx;
	public String tokenLabel;
	public long hSession;
		
	public TokenSession(PKCS11 p11Intf, long sIx, char[] pin) {

		p11 = p11Intf;
		slotIx = sIx;
		hSession = 0;
		long userType = 0;
		char[] SlotUserPin = null;

		try {
			long flags = PKCS11Constants.CKF_RW_SESSION;
			Object pApplication = this;
			CK_NOTIFY Notify = new KmsNotify();

			hSession = p11.C_OpenSession(slotIx, flags, pApplication, Notify);

			logger.debug("C_OpenSession ret [{}]", hSession);

			userType = PKCS11Constants.CKU_USER;
			SlotUserPin = pin;
			p11.C_Login(hSession, userType,  SlotUserPin);

			logger.debug("C_Login success");
			
			CK_SESSION_INFO sessInfo = p11.C_GetSessionInfo(hSession);

			logger.debug("Session {}:{}", hSession, sessInfo.toString());
		} catch (PKCS11Exception e) {
	
			logger.error("TokenSession", e);
			
			if(e.getErrorCode() == PKCS11Constants.CKR_USER_ALREADY_LOGGED_IN){
// 이미 로그인 되 있는 상태는 Error가 아니다. 
				logger.debug("C_Login: CKR_USER_ALREADY_LOGGED_IN");
			}else{
				
				throw new RuntimeException(e.getMessage());
			}
		}
	}

	public void retSession() {
		try {
			p11.C_CloseSession(hSession);
			logger.debug("C_CloseSession success");
		} catch (PKCS11Exception e) {
			e.printStackTrace();
		}
	}

	public void CloseSession() {
		try {
			p11.C_CloseSession(hSession);
			logger.debug("C_CloseSession success");
		} catch (PKCS11Exception e) {
			e.printStackTrace();
		}
	}

}
