package com.kona.kms.crypto;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import sun.security.pkcs11.wrapper.*;

import com.kona.kms.KMSCode;
import com.kona.kms.KmsException;


@SuppressWarnings("restriction")

public class PToken {

	private static final Logger logger = LoggerFactory.getLogger(PToken.class);
	
	public PKCS11 	p11;
	public String 		hsmId;
	public long 		slotIx;
	public String 		tokenLabel;
	public long 		hSession;
	char[] pin 			= null;
	
	public PToken(PKCS11 p11Intf, String hsm_id, long slot_ix, String token_label) {
		p11 		= p11Intf;
		hsmId 	= hsm_id;
		slotIx 	= slot_ix;
		tokenLabel 	= token_label;
		hSession 		= 0;

		logger.debug("PToken::PToken: token[{}:{}] attached", hsmId, token_label);
	}
	
	public TokenSession getSession() {
		TokenSession ts = new TokenSession(p11, slotIx, getPin());
		return ts;
	}
	
	void setPin(String ptext) {
		pin = ptext.toCharArray();
	}
	
	char[] getPin() {
		return pin;
	}
	
	public long findKeyByLabel(String keyLabel) throws KmsException {
		
		logger.debug("findKeyByLabel: enter.... slotIx [{}]", this.slotIx);		
		long ret = -1;
		TokenSession ts = getSession();
		
		try {
			CK_ATTRIBUTE[] pTemplate = new CK_ATTRIBUTE[1];
			pTemplate[0] = new CK_ATTRIBUTE(PKCS11Constants.CKA_LABEL, keyLabel);

			p11.C_FindObjectsInit(ts.hSession, pTemplate);
			long[] found = p11.C_FindObjects(ts.hSession, 10);

			if (found == null || found.length == 0) {
				throw new KmsException(KMSCode.KR_KEY_NOT_FOUND);
			}

			ret = this.slotIx;
			logger.debug("findKeyByLabel: {} Found at slotIx [{}]", keyLabel, this.slotIx);		
		}
		catch (PKCS11Exception e) {
			logger.debug("findKeyByLabel: {} not found at slotIx [{}]", keyLabel, this.slotIx);		
			e.printStackTrace();
			throw new KmsException(KMSCode.KR_CRYPTO_ERROR);
		}
		catch (KmsException e) {
			logger.debug("findKeyByLabel: {} not found at slotIx [{}]", keyLabel, this.slotIx);		
			ret = -1;
		}
		finally {
			ts.retSession();
		}

		return ret;
	}
	
}
