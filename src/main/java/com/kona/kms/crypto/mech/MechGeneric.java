package com.kona.kms.crypto.mech;

//import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import sun.security.pkcs11.wrapper.CK_ATTRIBUTE;
import sun.security.pkcs11.wrapper.CK_MECHANISM;
import sun.security.pkcs11.wrapper.Functions;
import sun.security.pkcs11.wrapper.PKCS11;
import sun.security.pkcs11.wrapper.PKCS11Constants;
import sun.security.pkcs11.wrapper.PKCS11Exception;

import com.kona.kms.crypto.CryptoManager;
import com.kona.kms.crypto.KeyAttributes;
import com.kona.kms.crypto.TokenSession;
import com.kona.kms.crypto.bo.CryptoResponse;
import com.kona.kms.utils.Util;
import com.kona.kms.KMSCode;
import com.kona.kms.KmsException;

@SuppressWarnings("restriction")

public class MechGeneric extends MechPkcs11 {
	
	private static final Logger logger = LoggerFactory.getLogger(MechGeneric.class);
	static MechGeneric m_instance;
	PKCS11 p11;
	CryptoManager provider;

	public static MechGeneric getInstance() {
		logger.debug("MechGeneric::getInstance: invoked....");
		if (m_instance == null) {
			m_instance = new MechGeneric();
		}
		return m_instance;
	}
	private MechGeneric() {
		logger.debug("MechGeneric::MechGeneric: invoked....");
	}


	public void setProvider(CryptoManager provider) {
		logger.debug("MechGeneric::setProvider: invoked....");
		this.provider = provider;
		p11 = provider.getPkcs11Intf();
	}
	
	
	// digest - ret digest data
	public CryptoResponse digest(long slotIx, String mechanism, String[] mechanismParameters, byte[] data,TokenSession tokenSession) throws KmsException, Exception {
		logger.debug("MechGeneric::digest: start.....");
		CryptoResponse cryptoResponse = new CryptoResponse();

		try{
			if(mechanism.equalsIgnoreCase("SHA_1") == false && mechanism.equalsIgnoreCase("MD5") == false) {
				throw new KmsException(KMSCode.KR_MECHANISM_INVALID);
			}	
		}catch (KmsException ke) {
			ke.printStackTrace();
			throw new KmsException(KMSCode.KR_MECHANISM_INVALID);
		}
		
		byte[] digestBuf = new byte[data.length + 1000];
		byte[] dataDigest = null;
		int digestLen = 0;
		
		try {
			CK_MECHANISM pMechanism;
			if (mechanism.equalsIgnoreCase("SHA_1") == true) {
				pMechanism = new CK_MECHANISM(PKCS11Constants.CKM_SHA_1);
			}
			else {
				pMechanism = new CK_MECHANISM(PKCS11Constants.CKM_MD5);
			}

			p11.C_DigestInit(tokenSession.hSession, pMechanism);
			digestLen = p11.C_DigestSingle(tokenSession.hSession, pMechanism, data, 0, data.length, digestBuf, 0, data.length + 1000);
			
			dataDigest = new byte[digestLen];
			System.arraycopy(digestBuf, 0, dataDigest, 0, digestLen);
			logger.debug("digest: C_DigestSingle ret text [{}:{}]", digestLen, Functions.toHexString(dataDigest));
		}
		catch (PKCS11Exception e) {
			e.printStackTrace();
			throw new KmsException(KMSCode.KR_CRYPTO_ERROR);
		}
		catch(Exception ex){
			ex.printStackTrace();
			throw new KmsException(ex.getMessage());
		}
		finally {

		}
		cryptoResponse.dataDigest = dataDigest;
		return cryptoResponse;
		//		return dataDigest;
	}
	
	public CryptoResponse getAttributeValue(long slotIx, String keyLabel,TokenSession tokenSession) throws KmsException, Exception {
		logger.debug("MechGeneric::getAttributeValue: start.....");
		CryptoResponse cryptoResponse = new CryptoResponse();

		long[] found = null;
		CK_ATTRIBUTE[] pTemplate = null;
		String[] ret = null;
	
		try {
			pTemplate = new CK_ATTRIBUTE[1];
			pTemplate[0] = new CK_ATTRIBUTE(PKCS11Constants.CKA_LABEL, keyLabel);

			p11.C_FindObjectsInit(tokenSession.hSession, pTemplate);
			found = p11.C_FindObjects(tokenSession.hSession, 10);
			
			try{
				if(found == null || found.length == 0) {
					throw new KmsException(KMSCode.KR_KEY_NOT_FOUND);
				}	
			}catch (KmsException ke) {
				ke.printStackTrace();
				throw new KmsException(KMSCode.KR_KEY_NOT_FOUND);
			}
			
			long hKey = found[0];
			logger.debug("C_FindObjects ret {}:{}", found.length, hKey);

			int attrCnt = 2;
			pTemplate = new CK_ATTRIBUTE[attrCnt];
			pTemplate[0] = new CK_ATTRIBUTE(PKCS11Constants.CKA_CLASS);		// PKCS11Constants.CKO_PUBLIC_KEY/CKO_PRIVATE_KEY/CKO_SECRET_KEY
			pTemplate[1] = new CK_ATTRIBUTE(PKCS11Constants.CKA_KEY_TYPE);	//PKCS11Constants.CKK_RSA/CKK_DES/CKK_DES3
			p11.C_GetAttributeValue(tokenSession.hSession, hKey, pTemplate);

			if (pTemplate[0].getLong() == PKCS11Constants.CKO_SECRET_KEY) {
				attrCnt = 13;
				pTemplate = new CK_ATTRIBUTE[attrCnt];
				pTemplate[0] = new CK_ATTRIBUTE(PKCS11Constants.CKA_LABEL);
				pTemplate[1] = new CK_ATTRIBUTE(PKCS11Constants.CKA_VALUE);
				pTemplate[2] = new CK_ATTRIBUTE(PKCS11Constants.CKA_TOKEN);	// token or session object? token
				pTemplate[3] = new CK_ATTRIBUTE(PKCS11Constants.CKA_PRIVATE);
				pTemplate[4] = new CK_ATTRIBUTE(PKCS11Constants.CKA_SENSITIVE);
				pTemplate[5] = new CK_ATTRIBUTE(PKCS11Constants.CKA_EXTRACTABLE);
				pTemplate[6] = new CK_ATTRIBUTE(PKCS11Constants.CKA_ENCRYPT);
				pTemplate[7] = new CK_ATTRIBUTE(PKCS11Constants.CKA_DECRYPT);
				pTemplate[8] = new CK_ATTRIBUTE(PKCS11Constants.CKA_WRAP);
				pTemplate[9] = new CK_ATTRIBUTE(PKCS11Constants.CKA_UNWRAP);
				pTemplate[10] = new CK_ATTRIBUTE(PKCS11Constants.CKA_SIGN);
				pTemplate[11] = new CK_ATTRIBUTE(PKCS11Constants.CKA_VERIFY);
				pTemplate[12] = new CK_ATTRIBUTE(PKCS11Constants.CKA_DERIVE);
				//pTemplate[12] = new CK_ATTRIBUTE(PKCS11Constants.CKA_SIGN_RECOVER);
				//pTemplate[13] = new CK_ATTRIBUTE(PKCS11Constants.CKA_VERIFY_RECOVER);
				//pTemplate[15] = new CK_ATTRIBUTE(PKCS11Constants.CKA_START_DATE);
				//pTemplate[16] = new CK_ATTRIBUTE(PKCS11Constants.CKA_END_DATE);
			}
			else if (pTemplate[0].getLong() == PKCS11Constants.CKO_PUBLIC_KEY) {
				logger.debug("C_GetAttributeValue: {} - CKO_PUBLIC_KEY", keyLabel);
				attrCnt = 18;
				pTemplate = new CK_ATTRIBUTE[attrCnt];
				pTemplate[0] = new CK_ATTRIBUTE(PKCS11Constants.CKA_CLASS);
				pTemplate[1] = new CK_ATTRIBUTE(PKCS11Constants.CKA_KEY_TYPE);
				pTemplate[2] = new CK_ATTRIBUTE(PKCS11Constants.CKA_TOKEN);
				pTemplate[3] = new CK_ATTRIBUTE(PKCS11Constants.CKA_PRIVATE);
				pTemplate[4] = new CK_ATTRIBUTE(PKCS11Constants.CKA_EXTRACTABLE);
				pTemplate[5] = new CK_ATTRIBUTE(PKCS11Constants.CKA_SENSITIVE);
				pTemplate[6] = new CK_ATTRIBUTE(PKCS11Constants.CKA_ENCRYPT);
				pTemplate[7] = new CK_ATTRIBUTE(PKCS11Constants.CKA_DECRYPT);		/* V */
				pTemplate[8] = new CK_ATTRIBUTE(PKCS11Constants.CKA_SIGN);			/* V */
				pTemplate[9] = new CK_ATTRIBUTE(PKCS11Constants.CKA_VERIFY);
				pTemplate[10] = new CK_ATTRIBUTE(PKCS11Constants.CKA_WRAP);
				pTemplate[11] = new CK_ATTRIBUTE(PKCS11Constants.CKA_UNWRAP);		/* V */
				pTemplate[12] = new CK_ATTRIBUTE(PKCS11Constants.CKA_DERIVE);
				pTemplate[13] = new CK_ATTRIBUTE(PKCS11Constants.CKA_LABEL);
				pTemplate[14] = new CK_ATTRIBUTE(PKCS11Constants.CKA_SUBJECT);
				
				pTemplate[15] = new CK_ATTRIBUTE(PKCS11Constants.CKA_MODULUS);
				pTemplate[16] = new CK_ATTRIBUTE(PKCS11Constants.CKA_PUBLIC_EXPONENT);
				pTemplate[17] = new CK_ATTRIBUTE(PKCS11Constants.CKA_MODULUS_BITS);

				//pTemplate[12] = new CK_ATTRIBUTE(PKCS11Constants.CKA_SIGN_RECOVER);	/* V */
				//pTemplate[13] = new CK_ATTRIBUTE(PKCS11Constants.CKA_VERIFY_RECOVER);
				//pTemplate[20] = new CK_ATTRIBUTE(PKCS11Constants.CKA_START_DATE);
				//pTemplate[21] = new CK_ATTRIBUTE(PKCS11Constants.CKA_END_DATE);
			}
			else if (pTemplate[0].getLong() == PKCS11Constants.CKO_PRIVATE_KEY) {
				logger.debug("C_GetAttributeValue: {} - CKO_PRIVATE_KEY", keyLabel);
				
				attrCnt = 23;
				pTemplate = new CK_ATTRIBUTE[attrCnt];
				pTemplate[0] = new CK_ATTRIBUTE(PKCS11Constants.CKA_CLASS);
				pTemplate[1] = new CK_ATTRIBUTE(PKCS11Constants.CKA_KEY_TYPE);	// rsa
				pTemplate[2] = new CK_ATTRIBUTE(PKCS11Constants.CKA_TOKEN);	// token or session object? token
				pTemplate[3] = new CK_ATTRIBUTE(PKCS11Constants.CKA_PRIVATE);
				pTemplate[4] = new CK_ATTRIBUTE(PKCS11Constants.CKA_EXTRACTABLE);
				pTemplate[5] = new CK_ATTRIBUTE(PKCS11Constants.CKA_SENSITIVE);
				pTemplate[6] = new CK_ATTRIBUTE(PKCS11Constants.CKA_ENCRYPT);			// V 
				pTemplate[7] = new CK_ATTRIBUTE(PKCS11Constants.CKA_DECRYPT);
				pTemplate[8] = new CK_ATTRIBUTE(PKCS11Constants.CKA_SIGN);
				pTemplate[9] = new CK_ATTRIBUTE(PKCS11Constants.CKA_VERIFY);			// V 
				pTemplate[10] = new CK_ATTRIBUTE(PKCS11Constants.CKA_WRAP);				// V 
				pTemplate[11] = new CK_ATTRIBUTE(PKCS11Constants.CKA_UNWRAP);
				pTemplate[12] = new CK_ATTRIBUTE(PKCS11Constants.CKA_DERIVE);
				pTemplate[13] = new CK_ATTRIBUTE(PKCS11Constants.CKA_LABEL);
				pTemplate[14] = new CK_ATTRIBUTE(PKCS11Constants.CKA_SUBJECT);
				
				pTemplate[15] = new CK_ATTRIBUTE(PKCS11Constants.CKA_MODULUS);
				pTemplate[16] = new CK_ATTRIBUTE(PKCS11Constants.CKA_PRIVATE_EXPONENT);
				pTemplate[17] = new CK_ATTRIBUTE(PKCS11Constants.CKA_PRIME_1);
				pTemplate[18] = new CK_ATTRIBUTE(PKCS11Constants.CKA_PRIME_2);
				pTemplate[19] = new CK_ATTRIBUTE(PKCS11Constants.CKA_EXPONENT_1);
				pTemplate[20] = new CK_ATTRIBUTE(PKCS11Constants.CKA_EXPONENT_2);
				pTemplate[21] = new CK_ATTRIBUTE(PKCS11Constants.CKA_COEFFICIENT);
				pTemplate[22] = new CK_ATTRIBUTE(PKCS11Constants.CKA_MODULUS_BITS);
				
				//pTemplate[23] = new CK_ATTRIBUTE(PKCS11Constants.CKA_PUBLIC_EXPONENT);

				//pTemplate[12] = new CK_ATTRIBUTE(PKCS11Constants.CKA_SIGN_RECOVER);
				//pTemplate[13] = new CK_ATTRIBUTE(PKCS11Constants.CKA_VERIFY_RECOVER);	/* V */
				//pTemplate[17] = new CK_ATTRIBUTE(PKCS11Constants.CKA_START_DATE);
				//pTemplate[18] = new CK_ATTRIBUTE(PKCS11Constants.CKA_END_DATE);
			}
			else {
				logger.debug("C_GetAttributeValue: something wrong!, unknown key-class!");
				throw new KmsException(KMSCode.KR_KEY_NOT_FOUND);
			}
			
			p11.C_GetAttributeValue(tokenSession.hSession, hKey, pTemplate);

			ret = new String[attrCnt];
			for (int i=0; i<attrCnt; i++) {
				String attrValue = pTemplate[i].toString().trim();
				int bIx = attrValue.indexOf("_");
				if (pTemplate[i] != null) ret[i] = (pTemplate[i].toString()).substring(bIx+1);
			}
			logger.debug("MechGeneric::getAttributeValue: end key [{}]", keyLabel);
		}
		catch (PKCS11Exception e) {
			e.printStackTrace();
			throw new KmsException(KMSCode.KR_CRYPTO_ERROR);
		}
		catch (KmsException ke) {
			ke.printStackTrace();
			throw new KmsException(KMSCode.KR_KEY_NOT_FOUND);
		}catch(Exception ex){
			ex.printStackTrace();
			throw new KmsException(ex.getMessage());
		}
		finally {
		}
		cryptoResponse.attrs = ret;
		return cryptoResponse;

	}
	
	public CryptoResponse getAttributeValueModulus(long slotIx, String keyLabel,TokenSession tokenSession) throws KmsException {
		logger.debug("MechGeneric::getAttributeValue: start.....");
		CryptoResponse cryptoResponse = new CryptoResponse();

		long[] found = null;
		CK_ATTRIBUTE[] pTemplate = null;
		String[] ret = null;
	
		try {
			pTemplate = new CK_ATTRIBUTE[1];
			pTemplate[0] = new CK_ATTRIBUTE(PKCS11Constants.CKA_LABEL, keyLabel);

			p11.C_FindObjectsInit(tokenSession.hSession, pTemplate);
			found = p11.C_FindObjects(tokenSession.hSession, 10);
			
			try{
				if(found == null || found.length == 0) {
					throw new KmsException(KMSCode.KR_KEY_NOT_FOUND);
				}	
			}catch (KmsException ke) {
				ke.printStackTrace();
				throw new KmsException(KMSCode.KR_KEY_NOT_FOUND);
			}
			
			long hKey = found[0];
			logger.debug("C_FindObjects ret {}:{}", found.length, hKey);

			int attrCnt = 2;
			pTemplate = new CK_ATTRIBUTE[attrCnt];
			pTemplate[0] = new CK_ATTRIBUTE(PKCS11Constants.CKA_CLASS);		// PKCS11Constants.CKO_PUBLIC_KEY/CKO_PRIVATE_KEY/CKO_SECRET_KEY
			pTemplate[1] = new CK_ATTRIBUTE(PKCS11Constants.CKA_KEY_TYPE);	//PKCS11Constants.CKK_RSA/CKK_DES/CKK_DES3
			p11.C_GetAttributeValue(tokenSession.hSession, hKey, pTemplate);

			if (pTemplate[0].getLong() == PKCS11Constants.CKO_SECRET_KEY) {
				attrCnt = 13;
				pTemplate = new CK_ATTRIBUTE[attrCnt];
				pTemplate[0] = new CK_ATTRIBUTE(PKCS11Constants.CKA_LABEL);
				pTemplate[1] = new CK_ATTRIBUTE(PKCS11Constants.CKA_VALUE);
				pTemplate[2] = new CK_ATTRIBUTE(PKCS11Constants.CKA_TOKEN);	// token or session object? token
				pTemplate[3] = new CK_ATTRIBUTE(PKCS11Constants.CKA_PRIVATE);
				pTemplate[4] = new CK_ATTRIBUTE(PKCS11Constants.CKA_SENSITIVE);
				pTemplate[5] = new CK_ATTRIBUTE(PKCS11Constants.CKA_EXTRACTABLE);
				pTemplate[6] = new CK_ATTRIBUTE(PKCS11Constants.CKA_ENCRYPT);
				pTemplate[7] = new CK_ATTRIBUTE(PKCS11Constants.CKA_DECRYPT);
				pTemplate[8] = new CK_ATTRIBUTE(PKCS11Constants.CKA_WRAP);
				pTemplate[9] = new CK_ATTRIBUTE(PKCS11Constants.CKA_UNWRAP);
				pTemplate[10] = new CK_ATTRIBUTE(PKCS11Constants.CKA_SIGN);
				pTemplate[11] = new CK_ATTRIBUTE(PKCS11Constants.CKA_VERIFY);
				pTemplate[12] = new CK_ATTRIBUTE(PKCS11Constants.CKA_DERIVE);
				//pTemplate[12] = new CK_ATTRIBUTE(PKCS11Constants.CKA_SIGN_RECOVER);
				//pTemplate[13] = new CK_ATTRIBUTE(PKCS11Constants.CKA_VERIFY_RECOVER);
				//pTemplate[15] = new CK_ATTRIBUTE(PKCS11Constants.CKA_START_DATE);
				//pTemplate[16] = new CK_ATTRIBUTE(PKCS11Constants.CKA_END_DATE);
			}
			else if (pTemplate[0].getLong() == PKCS11Constants.CKO_PUBLIC_KEY) {
				logger.debug("C_GetAttributeValue: {} - CKO_PUBLIC_KEY", keyLabel);
				attrCnt = 18;
				pTemplate = new CK_ATTRIBUTE[attrCnt];
				pTemplate[0] = new CK_ATTRIBUTE(PKCS11Constants.CKA_CLASS);
				pTemplate[1] = new CK_ATTRIBUTE(PKCS11Constants.CKA_KEY_TYPE);
				pTemplate[2] = new CK_ATTRIBUTE(PKCS11Constants.CKA_TOKEN);
				pTemplate[3] = new CK_ATTRIBUTE(PKCS11Constants.CKA_PRIVATE);
				pTemplate[4] = new CK_ATTRIBUTE(PKCS11Constants.CKA_EXTRACTABLE);
				pTemplate[5] = new CK_ATTRIBUTE(PKCS11Constants.CKA_SENSITIVE);
				pTemplate[6] = new CK_ATTRIBUTE(PKCS11Constants.CKA_ENCRYPT);
				pTemplate[7] = new CK_ATTRIBUTE(PKCS11Constants.CKA_DECRYPT);		/* V */
				pTemplate[8] = new CK_ATTRIBUTE(PKCS11Constants.CKA_SIGN);			/* V */
				pTemplate[9] = new CK_ATTRIBUTE(PKCS11Constants.CKA_VERIFY);
				pTemplate[10] = new CK_ATTRIBUTE(PKCS11Constants.CKA_WRAP);
				pTemplate[11] = new CK_ATTRIBUTE(PKCS11Constants.CKA_UNWRAP);		/* V */
				pTemplate[12] = new CK_ATTRIBUTE(PKCS11Constants.CKA_DERIVE);
				pTemplate[13] = new CK_ATTRIBUTE(PKCS11Constants.CKA_LABEL);
				pTemplate[14] = new CK_ATTRIBUTE(PKCS11Constants.CKA_SUBJECT);
				
				pTemplate[15] = new CK_ATTRIBUTE(PKCS11Constants.CKA_MODULUS);
				pTemplate[16] = new CK_ATTRIBUTE(PKCS11Constants.CKA_PUBLIC_EXPONENT);
				pTemplate[17] = new CK_ATTRIBUTE(PKCS11Constants.CKA_MODULUS_BITS);

				//pTemplate[12] = new CK_ATTRIBUTE(PKCS11Constants.CKA_SIGN_RECOVER);	/* V */
				//pTemplate[13] = new CK_ATTRIBUTE(PKCS11Constants.CKA_VERIFY_RECOVER);
				//pTemplate[20] = new CK_ATTRIBUTE(PKCS11Constants.CKA_START_DATE);
				//pTemplate[21] = new CK_ATTRIBUTE(PKCS11Constants.CKA_END_DATE);
			}
			else if (pTemplate[0].getLong() == PKCS11Constants.CKO_PRIVATE_KEY) {
				logger.debug("C_GetAttributeValue: {} - CKO_PRIVATE_KEY", keyLabel);
				
				attrCnt = 1;
				pTemplate = new CK_ATTRIBUTE[attrCnt];
				
				pTemplate[0] = new CK_ATTRIBUTE(PKCS11Constants.CKA_MODULUS);

				
				//pTemplate[23] = new CK_ATTRIBUTE(PKCS11Constants.CKA_PUBLIC_EXPONENT);

				//pTemplate[12] = new CK_ATTRIBUTE(PKCS11Constants.CKA_SIGN_RECOVER);
				//pTemplate[13] = new CK_ATTRIBUTE(PKCS11Constants.CKA_VERIFY_RECOVER);	/* V */
				//pTemplate[17] = new CK_ATTRIBUTE(PKCS11Constants.CKA_START_DATE);
				//pTemplate[18] = new CK_ATTRIBUTE(PKCS11Constants.CKA_END_DATE);
			}
			else {
				logger.debug("C_GetAttributeValue: something wrong!, unknown key-class!");
				throw new KmsException(KMSCode.KR_KEY_NOT_FOUND);
			}
			
			p11.C_GetAttributeValue(tokenSession.hSession, hKey, pTemplate);

			ret = new String[attrCnt];
			for (int i=0; i<attrCnt; i++) {
				String attrValue = pTemplate[i].toString().trim();
				int bIx = attrValue.indexOf("_");
				if (pTemplate[i] != null) ret[i] = (pTemplate[i].toString()).substring(bIx+1);
			}
			logger.debug("MechGeneric::getAttributeValue: end key [{}]", keyLabel);
		}
		catch (PKCS11Exception e) {
			e.printStackTrace();
			throw new KmsException(KMSCode.KR_CRYPTO_ERROR);
		}
		catch (KmsException ke) {
			ke.printStackTrace();
			throw new KmsException(KMSCode.KR_KEY_NOT_FOUND);
		}
		finally {
		}
		cryptoResponse.attrs = ret;
		return cryptoResponse;

	}
	
	public void destroyKey(long slotIx, String keyLabel,TokenSession tokenSession) throws KmsException, Exception {
		logger.debug("MechGeneric::destroyKey: start.....");
		TokenSession ts = provider.getTokenSession(slotIx);

		long[] found = null;
		CK_ATTRIBUTE[] pTemplate = null;
	
		try {
			pTemplate = new CK_ATTRIBUTE[1];
			pTemplate[0] = new CK_ATTRIBUTE(PKCS11Constants.CKA_LABEL, keyLabel);
			p11.C_FindObjectsInit(ts.hSession, pTemplate);
			found = p11.C_FindObjects(ts.hSession, 10);
			if (found == null || found.length == 0) {
				logger.debug("destroyKey: key [{}] not found", keyLabel);
				throw new KmsException(KMSCode.KR_KEY_NOT_FOUND);
			}
			long hKey = found[0];
			logger.debug("C_FindObjects ret {}:{}", found.length, hKey);

			p11.C_DestroyObject(ts.hSession, hKey);
			logger.debug("MechGeneric::destroyKey: destroyed key [{}]", keyLabel);
		}
		catch (PKCS11Exception e) {
			e.printStackTrace();
			throw new KmsException(KMSCode.KR_CRYPTO_ERROR);
		}
		catch (KmsException ke) {
			ke.printStackTrace();
			throw new KmsException(KMSCode.KR_KEY_NOT_FOUND);
		}
		catch(Exception ex){
			ex.printStackTrace();
			throw new KmsException(ex.getMessage());
		}
		finally {
//			ts.retSession();
		}
	}
	
	public void destroySessionKey(long hKey, TokenSession tokenSession) throws KmsException, Exception {
		logger.debug("MechGeneric::destroySessionKey: start.....");

		long[] found = null;
		CK_ATTRIBUTE[] pTemplate = null;
	
		try {
			pTemplate = new CK_ATTRIBUTE[1];
			//modify by shifei 2014-07-07
//			String s = "Object" + " " + String.valueOf(hKey);
			pTemplate[0] = new CK_ATTRIBUTE(PKCS11Constants.CKA_OBJECT_ID, hKey);
//			pTemplate[0] = new CK_ATTRIBUTE(PKCS11Constants.CKA_VALUE, hKey);
			p11.C_FindObjectsInit(tokenSession.hSession, pTemplate);
//			found = p11.C_FindObjects(tokenSession.hSession, 10);
			found = p11.C_FindObjects(tokenSession.hSession, 10);
			if (found == null || found.length == 0) {
				logger.debug("destroyKey: key value [{}] not found", hKey);
//				throw new KmsException(KMSCode.KR_KEY_NOT_FOUND);
//				return;
			}

			p11.C_DestroyObject(tokenSession.hSession, hKey);
			logger.debug("MechGeneric::destroySessionKey: destroyed key value [{}]", hKey);
		}
		catch (PKCS11Exception e) {
			e.printStackTrace();
			throw new KmsException(KMSCode.KR_CRYPTO_ERROR);
		}catch(Exception ex){
			ex.printStackTrace();
			throw new KmsException(ex.getMessage());
		}
		finally {
//			ts.retSession();
		}
	}
	
	public CryptoResponse generateRandom(long slotIx, int size, TokenSession tokenSession) throws KmsException, Exception {
		logger.debug("MechGeneric::generateRandom: start..... slotIx [{}]", slotIx);
		CryptoResponse cryptoResponse = new CryptoResponse();
		
		TokenSession ts = provider.getTokenSession(slotIx);
		
		byte[] bTemp = new byte[size];
		try {
			p11.C_GenerateRandom(ts.hSession, bTemp);
			logger.debug("MechGeneric::generateRandom: randomData [{}]", Util.byteArray2hexString(bTemp));
		}
		catch (PKCS11Exception e) {
			e.printStackTrace();
			throw new KmsException(KMSCode.KR_CRYPTO_ERROR);
		}catch(Exception ex){
			ex.printStackTrace();
			throw new Exception(ex.getMessage());
		}
		finally {
//			ts.retSession();
		}
		cryptoResponse.randomData = bTemp;
		return cryptoResponse;
	}

	public CryptoResponse importKey(long slotIx, KeyAttributes keyAttr,TokenSession tokenSession, String mechanism) throws KmsException, Exception {

		logger.debug("MechGeneric::importKey: enter...");

		CryptoResponse cryptoResponse = new CryptoResponse();
		CK_ATTRIBUTE[] pTemplate;
		long hKey = 0;

		try {

			pTemplate = new CK_ATTRIBUTE[19];
			pTemplate[0] = new CK_ATTRIBUTE(PKCS11Constants.CKA_CLASS, PKCS11Constants.CKO_SECRET_KEY);
			
			long mechanismValue =getMechanism(mechanism); 
			if (getMechanism(mechanism) == -1){
				throw new KmsException(KMSCode.KR_MECHANISM_INVALID);
			}

			pTemplate[1] = new CK_ATTRIBUTE(PKCS11Constants.CKA_KEY_TYPE, mechanismValue);
			pTemplate[2] = new CK_ATTRIBUTE(PKCS11Constants.CKA_TOKEN,keyAttr.cka_token); // token or session object? token
			pTemplate[3] = new CK_ATTRIBUTE(PKCS11Constants.CKA_PRIVATE,keyAttr.cka_private);
			pTemplate[4] = new CK_ATTRIBUTE(PKCS11Constants.CKA_SENSITIVE,keyAttr.cka_sensitive);
			pTemplate[5] = new CK_ATTRIBUTE(PKCS11Constants.CKA_EXTRACTABLE,keyAttr.cka_extractable);
			pTemplate[6] = new CK_ATTRIBUTE(PKCS11Constants.CKA_ENCRYPT,keyAttr.cka_encrypt);
			pTemplate[7] = new CK_ATTRIBUTE(PKCS11Constants.CKA_DECRYPT,keyAttr.cka_decrypt);
			pTemplate[8] = new CK_ATTRIBUTE(PKCS11Constants.CKA_WRAP,keyAttr.cka_wrap);
			pTemplate[9] = new CK_ATTRIBUTE(PKCS11Constants.CKA_UNWRAP,keyAttr.cka_unwrap);
			pTemplate[10] = new CK_ATTRIBUTE(PKCS11Constants.CKA_SIGN,keyAttr.cka_sign);
			pTemplate[11] = new CK_ATTRIBUTE(PKCS11Constants.CKA_VERIFY,keyAttr.cka_verify);
			pTemplate[12] = new CK_ATTRIBUTE(PKCS11Constants.CKA_SIGN_RECOVER,keyAttr.cka_signRecover);
			pTemplate[13] = new CK_ATTRIBUTE(PKCS11Constants.CKA_VERIFY_RECOVER,keyAttr.cka_verifyRecover);
			pTemplate[14] = new CK_ATTRIBUTE(PKCS11Constants.CKA_DERIVE,keyAttr.cka_derive);
			pTemplate[15] = new CK_ATTRIBUTE(PKCS11Constants.CKA_LABEL,keyAttr.cka_label);
			pTemplate[16] = new CK_ATTRIBUTE(PKCS11Constants.CKA_VALUE,keyAttr.cka_value);
			pTemplate[17] = new CK_ATTRIBUTE(PKCS11Constants.CKA_START_DATE,keyAttr.cka_startDate);
			pTemplate[18] = new CK_ATTRIBUTE(PKCS11Constants.CKA_END_DATE,keyAttr.cka_endDate);

			hKey = p11.C_CreateObject(tokenSession.hSession, pTemplate);

		} catch (PKCS11Exception e) {
			e.printStackTrace();
			throw new KmsException(KMSCode.KR_CRYPTO_ERROR);
		} 
		catch (KmsException ke) {
			ke.printStackTrace();
			throw new KmsException(KMSCode.KR_MECHANISM_INVALID);
		}catch(Exception ex){
			ex.printStackTrace();
			throw new Exception(ex.getMessage());
		}
		finally {
		}

		cryptoResponse.hKey = hKey;

		if (keyAttr.cka_label != null)
			cryptoResponse.keyLabel = new String(keyAttr.cka_label);
		else
			cryptoResponse.keyLabel = "";
		
		logger.debug("MechGeneric::importKey: end... keyLabel: " + cryptoResponse.keyLabel);
		
		return cryptoResponse;
	}

	public CryptoResponse generateKey(long slotIx, KeyAttributes keyAttr,TokenSession tokenSession , String mechanism) throws KmsException, Exception {
		logger.debug("MechGeneric::generateKey: enter...");

		CryptoResponse cryptoResponse = new CryptoResponse();

		CK_MECHANISM pMechanism;
		CK_ATTRIBUTE[] pTemplate;

		long hKey = 0;
		byte[] pSeed;

		try {
			/* C_SeedRandom */
			pSeed = keyAttr.cka_seed; 
			// p11.C_SeedRandom(ts.hSession, pSeed);
			p11.C_SeedRandom(tokenSession.hSession, pSeed);

			pMechanism = new CK_MECHANISM();
			pMechanism.mechanism = PKCS11Constants.CKM_DES3_KEY_GEN;

			pTemplate = new CK_ATTRIBUTE[18];
			pTemplate[0] = new CK_ATTRIBUTE(PKCS11Constants.CKA_CLASS,PKCS11Constants.CKO_SECRET_KEY);

			long mechanismValue =getMechanism(mechanism); 
			
			try{
				if (getMechanism(mechanism) == -1){
					throw new KmsException(KMSCode.KR_MECHANISM_INVALID);
				}
			}catch (KmsException ke) {
				ke.printStackTrace();
				throw new KmsException(KMSCode.KR_MECHANISM_INVALID);
			}
			
			pTemplate[1] = new CK_ATTRIBUTE(PKCS11Constants.CKA_KEY_TYPE, mechanismValue);
			pTemplate[2] = new CK_ATTRIBUTE(PKCS11Constants.CKA_TOKEN,keyAttr.cka_token); // token or session object? token
			pTemplate[3] = new CK_ATTRIBUTE(PKCS11Constants.CKA_PRIVATE,keyAttr.cka_private);
			pTemplate[4] = new CK_ATTRIBUTE(PKCS11Constants.CKA_SENSITIVE,keyAttr.cka_sensitive);
			pTemplate[5] = new CK_ATTRIBUTE(PKCS11Constants.CKA_EXTRACTABLE,keyAttr.cka_extractable);
			pTemplate[6] = new CK_ATTRIBUTE(PKCS11Constants.CKA_ENCRYPT,keyAttr.cka_encrypt);
			pTemplate[7] = new CK_ATTRIBUTE(PKCS11Constants.CKA_DECRYPT,keyAttr.cka_decrypt);
			pTemplate[8] = new CK_ATTRIBUTE(PKCS11Constants.CKA_WRAP,keyAttr.cka_wrap);
			pTemplate[9] = new CK_ATTRIBUTE(PKCS11Constants.CKA_UNWRAP,keyAttr.cka_unwrap);
			pTemplate[10] = new CK_ATTRIBUTE(PKCS11Constants.CKA_SIGN,keyAttr.cka_sign);
			pTemplate[11] = new CK_ATTRIBUTE(PKCS11Constants.CKA_VERIFY,keyAttr.cka_verify);
			pTemplate[12] = new CK_ATTRIBUTE(PKCS11Constants.CKA_SIGN_RECOVER,keyAttr.cka_signRecover);
			pTemplate[13] = new CK_ATTRIBUTE(PKCS11Constants.CKA_VERIFY_RECOVER,keyAttr.cka_verifyRecover);
			pTemplate[14] = new CK_ATTRIBUTE(PKCS11Constants.CKA_DERIVE,keyAttr.cka_derive);
			pTemplate[15] = new CK_ATTRIBUTE(PKCS11Constants.CKA_LABEL,keyAttr.cka_label);
			pTemplate[16] = new CK_ATTRIBUTE(PKCS11Constants.CKA_START_DATE,keyAttr.cka_startDate);
			pTemplate[17] = new CK_ATTRIBUTE(PKCS11Constants.CKA_END_DATE,keyAttr.cka_endDate);

			hKey = p11.C_GenerateKey(tokenSession.hSession, pMechanism,pTemplate);

			logger.debug("C_GenerateKey ret : {}", hKey);

		} catch (PKCS11Exception e) {
			e.printStackTrace();
			throw new KmsException(KMSCode.KR_CRYPTO_ERROR);
		} catch(Exception ex){
			ex.printStackTrace();
			throw new KmsException(ex.getMessage());
		}finally {
			// ts.retSession();
		}

		cryptoResponse.hKey = hKey;
		cryptoResponse.generatedKeyLabel = cryptoResponse.generatedKeyLabel.format("%s", new String(keyAttr.cka_label));
		return cryptoResponse;
	}

/*	public CryptoResponse encrypt(long slotIx, 
												String mechanism,
												String[] mechanismParameters, 
												String keyLabel, 
												byte[] data,
												TokenSession tokenSession, 
												long KeyHandle) throws KmsException {
*/
	public CryptoResponse SEncrypt(long slotIx, 
			String mechanism,
			String[] mechanismParameters, 
			String keyLabel, 
			byte[] data,
			TokenSession tokenSession, 
			long KeyHandle) throws KmsException, Exception {
		logger.debug("MechGeneric::encrypt: enter...");

		CryptoResponse cryptoResponse = new CryptoResponse();

		int blockSize;
		int iDataLen;
		int encryptedLen;
		long[] found = null;
		byte[] iData;
		byte[] encryptedText = null;

		iDataLen 		= 0;
		encryptedLen 	= 0;
		iData 			= null;
		
		try {

			if (KeyHandle == 0) { // 임시키인 경우에는 import key 후에 keyHandle값을 넘겨준다.
				CK_ATTRIBUTE[] pTemplate = new CK_ATTRIBUTE[1];
				pTemplate[0] = new CK_ATTRIBUTE(PKCS11Constants.CKA_LABEL,keyLabel);

				p11.C_FindObjectsInit(tokenSession.hSession, pTemplate);
				found = p11.C_FindObjects(tokenSession.hSession, 10);

				try{
					if (found == null || found.length == 0) {
						throw new KmsException(KMSCode.KR_KEY_NOT_FOUND);
					}
	
				}catch (KmsException ke) {
					ke.printStackTrace();
					throw new KmsException(KMSCode.KR_KEY_NOT_FOUND);
				}
				
				KeyHandle = found[0];

			}

			CK_MECHANISM pMechanism;
			
			long mechanismValue =getMechanism(mechanism); 

			try{
				if (mechanismValue == -1)
				{
					logger.debug("Invalid Mechanism" );
					throw new KmsException(KMSCode.KR_MECHANISM_INVALID);
				}	
			}catch (KmsException ke) {
				ke.printStackTrace();
				throw new KmsException(KMSCode.KR_MECHANISM_INVALID);
			}
			
			pMechanism = new CK_MECHANISM(mechanismValue);
			
//CBC Mode Check
			if (mechanismParameters != null && mechanismParameters[0] != null){
				logger.debug("Encrypt CBC MODE" );
				pMechanism.pParameter = Util.hexStringToByteArray(mechanismParameters[0]);
			}else{
				pMechanism.pParameter = 0;
			}
			blockSize = 8;

			if (data.length % blockSize == 0) 
			{
				iDataLen = data.length;
				iData = data;
			}
			else 
			{ 			// Block size에 맞지 않는 데이터가 오면 padding을 하지 않는다.... padding
						// 업무를 하는 쪽에서 채우는것이 맞는 것임.. padding의 규칙은 다름.
				cryptoResponse.dataEncrypted = null;
				return cryptoResponse;
			}

			encryptedText = new byte[iDataLen];

//			logger.debug("encrypt: Start C_Encrypt ret text [{}:{}]", encryptedLen,Util.byteArray2hexString(encryptedText));

			logger.debug("encrypt: Start C_Encrypt ret text [{}:{}]", encryptedLen,Util.byteArray2hexString(iData));
			
			p11.C_EncryptInit(tokenSession.hSession, pMechanism, KeyHandle);
			encryptedLen = p11.C_Encrypt(tokenSession.hSession, iData, 0,iDataLen, encryptedText, 0, iDataLen);

			logger.debug("encrypt: C_Encrypt ret text [{}:{}]", encryptedLen,Util.byteArray2hexString(encryptedText));
		} 
		catch (PKCS11Exception e) 
		{
			
			logger.error("PKCS11Exception", e);
			throw new KmsException(KMSCode.KR_CRYPTO_ERROR);
		} 
		catch(Exception ex){
			logger.error("Exception", ex);
			throw new KmsException(ex.getMessage());
		}
		finally {
			// ts.retSession();
		}
		cryptoResponse.dataEncrypted = encryptedText;
		return cryptoResponse;
	}

	public CryptoResponse SDecrypt(long slotIx,
												String mechanism,
												String[] mechanismParameters, 
												String keyLabel, 
												byte[] edata,
												TokenSession tokenSession,
												long KeyHandle) throws KmsException, Exception {

		logger.debug("MechGeneric::decrypt: enter...");

		CryptoResponse cryptoResponse = new CryptoResponse();

		int blockSize = 8;
		int iDataLen;
		byte[] iData;
		byte[] decryptedText = null;
		int decryptedLen;
		long[] found = null;

		iDataLen = 0;
		iData = null;
		decryptedLen = 0;

		try {
			if (KeyHandle == 0) { // 임시키인 경우에는 import key 후에 keyHandle값을 넘겨준다.

				CK_ATTRIBUTE[] pTemplate = new CK_ATTRIBUTE[1];
				pTemplate[0] = new CK_ATTRIBUTE(PKCS11Constants.CKA_LABEL,keyLabel);
				p11.C_FindObjectsInit(tokenSession.hSession, pTemplate);
				found = p11.C_FindObjects(tokenSession.hSession, 10);
				try{
					if (found == null || found.length == 0) {
						throw new KmsException(KMSCode.KR_KEY_NOT_FOUND);
					}
				}catch (KmsException ke) {
					ke.printStackTrace();
					throw new KmsException(KMSCode.KR_KEY_NOT_FOUND);
				}
				

				KeyHandle = found[0];
			}

			CK_MECHANISM pMechanism;

			long mechanismValue =getMechanism(mechanism); 
			try{
				if (mechanismValue == -1)
				{
					throw new KmsException(KMSCode.KR_MECHANISM_INVALID);
				}
			}catch (KmsException ke) {
				ke.printStackTrace();
				throw new KmsException(KMSCode.KR_MECHANISM_INVALID);
			}
			
			pMechanism = new CK_MECHANISM(mechanismValue);
			
//CBC Mode Check
			if (mechanismParameters != null && mechanismParameters[0] != null)
				pMechanism.pParameter = Util.hexStringToByteArray(mechanismParameters[0]);
			else
				pMechanism.pParameter = 0;

			if (edata.length % blockSize == 0) {
				iDataLen = edata.length;
				iData = edata;
			} else { // Block size에 맞지 않는 데이터가 오면 padding을 하지 않는다.... padding
						// 업무를 하는 쪽에서 채우는것이 맞는 것임.. padding의 규칙은 다름.
				cryptoResponse.dataDecrypted = null;
				return cryptoResponse;
			}

			decryptedText = new byte[iDataLen];

			p11.C_DecryptInit(tokenSession.hSession, pMechanism, KeyHandle);
			decryptedLen = p11.C_Decrypt(tokenSession.hSession, iData, 0, iDataLen, decryptedText, 0, iDataLen);
			logger.debug("decrypt: C_Decrypt ret text [{}:{}]", decryptedLen, Util.byteArray2hexString(decryptedText));
		} catch (PKCS11Exception e) {
			e.printStackTrace();
			throw new KmsException(KMSCode.KR_CRYPTO_ERROR);
		}
		catch(Exception ex){
			ex.printStackTrace();
			throw new KmsException(ex.getMessage());
		}
		finally {
			// ts.retSession();
		}

		cryptoResponse.dataDecrypted = decryptedText;
		return cryptoResponse;
	}

	// wrap a private or secret key
	public CryptoResponse wrapKey(long slotIx, 
												String mechanism,
												String[] mechanismParameters, 
												String wrappingKeyLabel,
												String keyLabel, 
												TokenSession tokenSession) throws KmsException, Exception {
		logger.debug("MechGeneric::wrapKey: enter...");
		CryptoResponse cryptoResponse = new CryptoResponse();

/*		if (mechanism.equalsIgnoreCase("DES3_ECB") == false
				&& mechanism.equalsIgnoreCase("DES3_CBC") == false) {
			throw new KmsException(KMSCode.KR_MECHANISM_INVALID);
		}
*/
		long hKey, hWrappingKey;
		long[] found;
		CK_ATTRIBUTE[] pTemplate;
		byte[] wrappedKey = null;

		try {

			pTemplate = new CK_ATTRIBUTE[1];
			pTemplate[0] = new CK_ATTRIBUTE(PKCS11Constants.CKA_LABEL, keyLabel);

			p11.C_FindObjectsInit(tokenSession.hSession, pTemplate);
			found = p11.C_FindObjects(tokenSession.hSession, 10);

			try{
				if (found == null || found.length == 0) {
					throw new KmsException(KMSCode.KR_KEY_NOT_FOUND);
				}	
			}catch (KmsException ke) {
				ke.printStackTrace();
				throw new KmsException(KMSCode.KR_KEY_NOT_FOUND);
			}

			hKey = found[0];

			pTemplate = new CK_ATTRIBUTE[1];
			pTemplate[0] = new CK_ATTRIBUTE(PKCS11Constants.CKA_LABEL,wrappingKeyLabel);

			p11.C_FindObjectsInit(tokenSession.hSession, pTemplate);
			found = p11.C_FindObjects(tokenSession.hSession, 10);

			try{
				if (found == null || found.length == 0) {
					throw new KmsException(KMSCode.KR_KEY_NOT_FOUND);
				}	
			}catch (KmsException ke) {
				ke.printStackTrace();
				throw new KmsException(KMSCode.KR_KEY_NOT_FOUND);
			}

			hWrappingKey = found[0];

/*			if (mechanism.equalsIgnoreCase("DES3_ECB") == true) {
				pMechanism = new CK_MECHANISM(PKCS11Constants.CKM_DES3_ECB);
			} else { // "DES3_CBC"
				pMechanism = new CK_MECHANISM(PKCS11Constants.CKM_DES3_CBC);
				if (mechanismParameters != null
						&& mechanismParameters[0] != null)
					pMechanism.pParameter = Util
							.hexStringToByteArray(mechanismParameters[0]);
				else
					pMechanism.pParameter = 0;
			}
*/
			CK_MECHANISM pMechanism;
			long mechanismValue =getMechanism(mechanism); 
			
			try{
				if (mechanismValue == -1)
				{
					throw new KmsException(KMSCode.KR_MECHANISM_INVALID);
				}	
			}catch (KmsException ke) {
				ke.printStackTrace();
				throw new KmsException(KMSCode.KR_MECHANISM_INVALID);
			}
			

			pMechanism = new CK_MECHANISM(mechanismValue);
			
//CBC Mode Check
			if (mechanismParameters != null && mechanismParameters[0] != null)
				pMechanism.pParameter = Util.hexStringToByteArray(mechanismParameters[0]);
			else
				pMechanism.pParameter = 0;
			
			wrappedKey = p11.C_WrapKey(tokenSession.hSession, pMechanism, hWrappingKey, hKey);
			logger.debug("C_WrapKey ret text [{}:{}]", wrappedKey.length, Util.byteArray2hexString(wrappedKey));
		} catch (PKCS11Exception e) {
			e.printStackTrace();
			throw new KmsException(KMSCode.KR_CRYPTO_ERROR);
		} catch (KmsException ke) {
			ke.printStackTrace();
			throw new KmsException(ke.getMsgcode());
		} catch(Exception ex){
			ex.printStackTrace();
			throw new KmsException(ex.getMessage());
		}
		finally {
			// ts.retSession();
		}

		cryptoResponse.keyWrapped = wrappedKey;
		return cryptoResponse;
	}

	// unwrapkey는 구현하지 않는다.
//	public CryptoResponse unwrapKey(long slotIx, String mechanism,
//			String[] mechanismParameters, String unWrappingKeyLabel,
//			byte[] wrappedKey, KeyAttributes keyAttr, TokenSession tokenSession)
//			throws KmsException {
//		logger.debug("MechGeneric::unwrapKey: enter...");
//		CryptoResponse cryptoResponse = new CryptoResponse();
//
//		// long mechId = Functions.getMechanismId(mechanism);
//		if (mechanism.equalsIgnoreCase("DES3_ECB") == false
//				&& mechanism.equalsIgnoreCase("DES3_CBC") == false) {
//			throw new KmsException(KMSCode.KR_MECHANISM_INVALID);
//		}
//
//		long hKey, hUnwrappingKey;
//		long[] found;
//		CK_ATTRIBUTE[] pTemplate;
//
//		try {
//			pTemplate = new CK_ATTRIBUTE[1];
//			pTemplate[0] = new CK_ATTRIBUTE(PKCS11Constants.CKA_LABEL,
//					unWrappingKeyLabel);
//
//			p11.C_FindObjectsInit(tokenSession.hSession, pTemplate);
//			found = p11.C_FindObjects(tokenSession.hSession, 10);
//
//			if (found == null || found.length == 0) {
//				throw new KmsException(KMSCode.KR_KEY_NOT_FOUND);
//			}
//			hUnwrappingKey = found[0];
//
//			CK_MECHANISM pMechanism;
//			if (mechanism.equalsIgnoreCase("DES3_ECB") == true) {
//				pMechanism = new CK_MECHANISM(PKCS11Constants.CKM_DES3_ECB);
//			} else { // "DES3_CBC"
//				pMechanism = new CK_MECHANISM(PKCS11Constants.CKM_DES3_CBC);
//				if (mechanismParameters != null
//						&& mechanismParameters[0] != null)
//					pMechanism.pParameter = Util
//							.hexStringToByteArray(mechanismParameters[0]);
//				else
//					pMechanism.pParameter = 0;
//			}
//
//			pTemplate = new CK_ATTRIBUTE[16];
//			pTemplate[0] = new CK_ATTRIBUTE(PKCS11Constants.CKA_CLASS,
//					PKCS11Constants.CKO_SECRET_KEY);
//			pTemplate[1] = new CK_ATTRIBUTE(PKCS11Constants.CKA_KEY_TYPE,
//					PKCS11Constants.CKK_DES3); // des3
//			pTemplate[2] = new CK_ATTRIBUTE(PKCS11Constants.CKA_TOKEN,
//					keyAttr.cka_token); // token or session object? token
//			pTemplate[3] = new CK_ATTRIBUTE(PKCS11Constants.CKA_PRIVATE,
//					keyAttr.cka_private);
//			pTemplate[4] = new CK_ATTRIBUTE(PKCS11Constants.CKA_SENSITIVE,
//					keyAttr.cka_sensitive);
//			pTemplate[5] = new CK_ATTRIBUTE(PKCS11Constants.CKA_EXTRACTABLE,
//					keyAttr.cka_extractable);
//			pTemplate[6] = new CK_ATTRIBUTE(PKCS11Constants.CKA_ENCRYPT,
//					keyAttr.cka_encrypt);
//			pTemplate[7] = new CK_ATTRIBUTE(PKCS11Constants.CKA_DECRYPT,
//					keyAttr.cka_decrypt);
//			pTemplate[8] = new CK_ATTRIBUTE(PKCS11Constants.CKA_WRAP,
//					keyAttr.cka_wrap);
//			pTemplate[9] = new CK_ATTRIBUTE(PKCS11Constants.CKA_UNWRAP,
//					keyAttr.cka_unwrap);
//			pTemplate[10] = new CK_ATTRIBUTE(PKCS11Constants.CKA_SIGN,
//					keyAttr.cka_sign);
//			pTemplate[11] = new CK_ATTRIBUTE(PKCS11Constants.CKA_VERIFY,
//					keyAttr.cka_verify);
//			pTemplate[12] = new CK_ATTRIBUTE(PKCS11Constants.CKA_SIGN_RECOVER,
//					keyAttr.cka_signRecover);
//			pTemplate[13] = new CK_ATTRIBUTE(
//					PKCS11Constants.CKA_VERIFY_RECOVER,
//					keyAttr.cka_verifyRecover);
//			pTemplate[14] = new CK_ATTRIBUTE(PKCS11Constants.CKA_DERIVE,
//					keyAttr.cka_derive);
//			pTemplate[15] = new CK_ATTRIBUTE(PKCS11Constants.CKA_LABEL,
//					keyAttr.cka_label);
//
//			hKey = p11.C_UnwrapKey(tokenSession.hSession, pMechanism,
//					hUnwrappingKey, wrappedKey, pTemplate);
//
//			logger.debug("C_UnwrapKey ret text [{}]", hKey);
//		} catch (PKCS11Exception e) {
//			e.printStackTrace();
//			throw new KmsException(KMSCode.KR_CRYPTO_ERROR);
//		} finally {
//			// ts.retSession();
//		}
//
//		cryptoResponse.generatedKeyLabel.format("%s", new String(keyAttr.cka_label));
//		return cryptoResponse;
//		// return new String(keyAttr.cka_label);
//	}
	
	public CryptoResponse unwrapKey(long slotIx, String mechanism, String[] mechanismParameters, String unWrappingKeyLabel,	byte[] wrappedKey, KeyAttributes keyAttr, TokenSession tokenSession) throws KmsException, Exception {
		logger.debug("MechGeneric::unwrapKey: enter...");
		CryptoResponse cryptoResponse = new CryptoResponse();

		long hKey, hUnwrappingKey;
		long[] found;
		CK_ATTRIBUTE[] pTemplate;

		try {
			pTemplate = new CK_ATTRIBUTE[1];
			pTemplate[0] = new CK_ATTRIBUTE(PKCS11Constants.CKA_LABEL, unWrappingKeyLabel);

			p11.C_FindObjectsInit(tokenSession.hSession, pTemplate);
			found = p11.C_FindObjects(tokenSession.hSession, 10);

			if (found == null || found.length == 0) {
				throw new KmsException(KMSCode.KR_KEY_NOT_FOUND);
			}
			hUnwrappingKey = found[0];
			
			
			CK_MECHANISM pMechanism;
			long mechanismValue =getMechanism(mechanism); 
			
			try{
				if (mechanismValue == -1)
				{
					throw new KmsException(KMSCode.KR_MECHANISM_INVALID);
				}	
			}catch (KmsException ke) {
				ke.printStackTrace();
				throw new KmsException(KMSCode.KR_MECHANISM_INVALID);
			}
			
			pMechanism = new CK_MECHANISM(mechanismValue);
			
			if (mechanismParameters != null && mechanismParameters[0] != null)
				pMechanism.pParameter = Util.hexStringToByteArray(mechanismParameters[0]);
			else
				pMechanism.pParameter = 0;
			
			
			/*if(mechanism.equalsIgnoreCase("DES3_ECB")||mechanism.equalsIgnoreCase("DES2_ECB")||mechanism.equalsIgnoreCase("DES_ECB")||
					mechanism.equalsIgnoreCase("SEED_CBC")||mechanism.equalsIgnoreCase("AES_ECB")||mechanism.equalsIgnoreCase("DES3_CBC")||
					mechanism.equalsIgnoreCase("DES2_ECB")||mechanism.equalsIgnoreCase("DES_ECB")||mechanism.equalsIgnoreCase("SEED_ECB")||
					mechanism.equalsIgnoreCase("AES_ECB")||mechanism.equalsIgnoreCase("DES3_CBC")||mechanism.equalsIgnoreCase("DES2_CBC")||
					mechanism.equalsIgnoreCase("DES_CBC")||mechanism.equalsIgnoreCase("SEED_CBC")||mechanism.equalsIgnoreCase("AES_CBC")||
					mechanism.equalsIgnoreCase("SEED_MAC")||mechanism.equalsIgnoreCase("DES_MAC")||mechanism.equalsIgnoreCase("DES3_MAC")||
					mechanism.equalsIgnoreCase("AES_MAC"))*/
			
			if(keyAttr.cka_keyClass == PKCS11Constants.CKO_SECRET_KEY){
				pTemplate = new CK_ATTRIBUTE[18];
				pTemplate[0] = new CK_ATTRIBUTE(PKCS11Constants.CKA_CLASS, PKCS11Constants.CKO_SECRET_KEY);
				pTemplate[1] = new CK_ATTRIBUTE(PKCS11Constants.CKA_KEY_TYPE, keyAttr.cka_keyType); //
				
//				if(keyAttr.cka_keyType == 19){
//					pTemplate[1] = new CK_ATTRIBUTE(PKCS11Constants.CKA_KEY_TYPE, PKCS11Constants.CKK_DES3); // temp	
//				}else{
//					pTemplate[1] = new CK_ATTRIBUTE(PKCS11Constants.CKA_KEY_TYPE, keyAttr.cka_keyType); //
//				}
				
				
			}else if(keyAttr.cka_keyClass == PKCS11Constants.CKO_PRIVATE_KEY){
				pTemplate = new CK_ATTRIBUTE[19];
				pTemplate[0] = new CK_ATTRIBUTE(PKCS11Constants.CKA_CLASS, PKCS11Constants.CKO_PRIVATE_KEY);
				pTemplate[1] = new CK_ATTRIBUTE(PKCS11Constants.CKA_KEY_TYPE, PKCS11Constants.CKK_RSA);	// rsa
				pTemplate[18] = new CK_ATTRIBUTE(PKCS11Constants.CKA_SUBJECT, keyAttr.cka_subject);
			}else{
				throw new KmsException(KMSCode.KR_ATTRIBUTE_INVALID);
			}
			
			pTemplate[2] = new CK_ATTRIBUTE(PKCS11Constants.CKA_TOKEN, keyAttr.cka_token); // token or session object? token
			pTemplate[3] = new CK_ATTRIBUTE(PKCS11Constants.CKA_PRIVATE, keyAttr.cka_private);
			pTemplate[4] = new CK_ATTRIBUTE(PKCS11Constants.CKA_SENSITIVE, keyAttr.cka_sensitive);
			pTemplate[5] = new CK_ATTRIBUTE(PKCS11Constants.CKA_EXTRACTABLE, keyAttr.cka_extractable);
			pTemplate[6] = new CK_ATTRIBUTE(PKCS11Constants.CKA_ENCRYPT, keyAttr.cka_encrypt);
			pTemplate[7] = new CK_ATTRIBUTE(PKCS11Constants.CKA_DECRYPT, keyAttr.cka_decrypt);
			pTemplate[8] = new CK_ATTRIBUTE(PKCS11Constants.CKA_WRAP, keyAttr.cka_wrap);
			pTemplate[9] = new CK_ATTRIBUTE(PKCS11Constants.CKA_UNWRAP, keyAttr.cka_unwrap);
			pTemplate[10] = new CK_ATTRIBUTE(PKCS11Constants.CKA_SIGN, keyAttr.cka_sign);
			pTemplate[11] = new CK_ATTRIBUTE(PKCS11Constants.CKA_VERIFY, keyAttr.cka_verify);
			pTemplate[12] = new CK_ATTRIBUTE(PKCS11Constants.CKA_SIGN_RECOVER, keyAttr.cka_signRecover);
			pTemplate[13] = new CK_ATTRIBUTE(PKCS11Constants.CKA_VERIFY_RECOVER, keyAttr.cka_verifyRecover);
			pTemplate[14] = new CK_ATTRIBUTE(PKCS11Constants.CKA_DERIVE, keyAttr.cka_derive);
			pTemplate[15] = new CK_ATTRIBUTE(PKCS11Constants.CKA_LABEL, keyAttr.cka_label);
			pTemplate[16] = new CK_ATTRIBUTE(PKCS11Constants.CKA_START_DATE,keyAttr.cka_startDate);
			pTemplate[17] = new CK_ATTRIBUTE(PKCS11Constants.CKA_END_DATE,keyAttr.cka_endDate);
			
			hKey = p11.C_UnwrapKey(tokenSession.hSession, pMechanism, hUnwrappingKey, wrappedKey, pTemplate);
			logger.debug("C_UnwrapKey ret text [{}]", hKey);
			
		} catch (PKCS11Exception e) {
			e.printStackTrace();
			throw new KmsException(KMSCode.KR_CRYPTO_ERROR);
		}catch(Exception ex){
			ex.printStackTrace();
			throw new KmsException(ex.getMessage());
		} 
		finally {
			// ts.retSession();
		}

		cryptoResponse.hKey = hKey;
		cryptoResponse.generatedKeyLabel = cryptoResponse.generatedKeyLabel.format("%s", new String(keyAttr.cka_label));
		return cryptoResponse;
	}

//	// derive a key는 구현하지 않는다.
//	public CryptoResponse deriveKey(long slotIx, String mechanism, String[] mechanismParameters, String baseKeyLabel, KeyAttributes keyAttr, TokenSession tokenSession) throws KmsException {
//		logger.debug("MechGeneric::deriveKey: enter...");
//
//		CryptoResponse cryptoResponse = new CryptoResponse();
//		// long mechId = Functions.getMechanismId(mechanism);
//		if (mechanism.equalsIgnoreCase("DES3_ECB") == false
//				&& mechanism.equalsIgnoreCase("DES3_CBC") == false) {
//			throw new KmsException(KMSCode.KR_MECHANISM_INVALID);
//		}
//
//		TokenSession ts = provider.getTokenSession(slotIx);
//
//		long hKey, hBasegKey;
//		long[] found;
//		CK_ATTRIBUTE[] pTemplate;
//
//		try {
//			pTemplate = new CK_ATTRIBUTE[1];
//			pTemplate[0] = new CK_ATTRIBUTE(PKCS11Constants.CKA_LABEL, baseKeyLabel);
//			p11.C_FindObjectsInit(ts.hSession, pTemplate);
//			found = p11.C_FindObjects(ts.hSession, 10);
//			if (found == null || found.length == 0) {
//				throw new KmsException(KMSCode.KR_KEY_NOT_FOUND);
//			}
//			hBasegKey = found[0];
//
//			logger.debug("C_FindObjects ret: [{}:{}] for deriveKey", hBasegKey, baseKeyLabel);
//
//			CK_MECHANISM pMechanism;
//			if (mechanism.equalsIgnoreCase("DES3_ECB") == true) {
//				pMechanism = new CK_MECHANISM(PKCS11Constants.CKM_DES3_ECB);
//			} else { // "DES3_CBC"
//				pMechanism = new CK_MECHANISM(PKCS11Constants.CKM_DES3_CBC);
//				if (mechanismParameters != null
//						&& mechanismParameters[0] != null)
//					pMechanism.pParameter = Util.hexStringToByteArray(mechanismParameters[0]);
//				else
//					pMechanism.pParameter = 0;
//			}
//
//			pTemplate = new CK_ATTRIBUTE[16];
//			pTemplate[0] = new CK_ATTRIBUTE(PKCS11Constants.CKA_CLASS, PKCS11Constants.CKO_SECRET_KEY);
//			pTemplate[1] = new CK_ATTRIBUTE(PKCS11Constants.CKA_KEY_TYPE, PKCS11Constants.CKK_DES3); // des3
//			pTemplate[2] = new CK_ATTRIBUTE(PKCS11Constants.CKA_TOKEN, keyAttr.cka_token); // token or session object? token
//			pTemplate[3] = new CK_ATTRIBUTE(PKCS11Constants.CKA_PRIVATE, keyAttr.cka_private);
//			pTemplate[4] = new CK_ATTRIBUTE(PKCS11Constants.CKA_SENSITIVE, keyAttr.cka_sensitive);
//			pTemplate[5] = new CK_ATTRIBUTE(PKCS11Constants.CKA_EXTRACTABLE, keyAttr.cka_extractable);
//			pTemplate[6] = new CK_ATTRIBUTE(PKCS11Constants.CKA_ENCRYPT, keyAttr.cka_encrypt);
//			pTemplate[7] = new CK_ATTRIBUTE(PKCS11Constants.CKA_DECRYPT, keyAttr.cka_decrypt);
//			pTemplate[8] = new CK_ATTRIBUTE(PKCS11Constants.CKA_WRAP, keyAttr.cka_wrap);
//			pTemplate[9] = new CK_ATTRIBUTE(PKCS11Constants.CKA_UNWRAP,	keyAttr.cka_unwrap);
//			pTemplate[10] = new CK_ATTRIBUTE(PKCS11Constants.CKA_SIGN, keyAttr.cka_sign);
//			pTemplate[11] = new CK_ATTRIBUTE(PKCS11Constants.CKA_VERIFY, keyAttr.cka_verify);
//			pTemplate[12] = new CK_ATTRIBUTE(PKCS11Constants.CKA_SIGN_RECOVER, keyAttr.cka_signRecover);
//			pTemplate[13] = new CK_ATTRIBUTE(PKCS11Constants.CKA_VERIFY_RECOVER, keyAttr.cka_verifyRecover);
//			pTemplate[14] = new CK_ATTRIBUTE(PKCS11Constants.CKA_DERIVE, keyAttr.cka_derive);
//			pTemplate[15] = new CK_ATTRIBUTE(PKCS11Constants.CKA_LABEL, keyAttr.cka_label);
//
//			hKey = p11.C_DeriveKey(ts.hSession, pMechanism, hBasegKey, pTemplate);
//
//			logger.debug("C_DeriveKey ret handle [{}]", hKey);
//		} catch (PKCS11Exception e) {
//			e.printStackTrace();
//			throw new KmsException(KMSCode.KR_CRYPTO_ERROR);
//		} finally {
//			// ts.retSession();
//		}
//
//		cryptoResponse.generatedKeyLabel.format("%s", new String(keyAttr.cka_label));
//		return cryptoResponse;
//	}
	
	public CryptoResponse deriveKey(long slotIx, String mechanism, String[] mechanismParameters, String baseKeyLabel, KeyAttributes keyAttr, TokenSession tokenSession) throws KmsException, Exception {
		logger.debug("MechGeneric::deriveKey: enter...");

		CryptoResponse cryptoResponse = new CryptoResponse();
		TokenSession ts = provider.getTokenSession(slotIx);

		long hKey, hBasegKey;
		long[] found;
		CK_ATTRIBUTE[] pTemplate;

		try {
			pTemplate = new CK_ATTRIBUTE[1];
			pTemplate[0] = new CK_ATTRIBUTE(PKCS11Constants.CKA_LABEL, baseKeyLabel);
			p11.C_FindObjectsInit(ts.hSession, pTemplate);
			found = p11.C_FindObjects(ts.hSession, 10);
			if (found == null || found.length == 0) {
				throw new KmsException(KMSCode.KR_KEY_NOT_FOUND);
			}
			hBasegKey = found[0];

			logger.debug("C_FindObjects ret: [{}:{}] for deriveKey", hBasegKey, baseKeyLabel);

			
			CK_MECHANISM pMechanism;
			long mechanismValue =getMechanism(mechanism); 
			
			try{
				if (mechanismValue == -1)
				{
					throw new KmsException(KMSCode.KR_MECHANISM_INVALID);
				}	
			}catch (KmsException ke) {
				ke.printStackTrace();
				throw new KmsException(KMSCode.KR_MECHANISM_INVALID);
			}
			
			pMechanism = new CK_MECHANISM(mechanismValue);
			
			if (mechanismParameters != null && mechanismParameters[0] != null)
				pMechanism.pParameter = Util.hexStringToByteArray(mechanismParameters[0]);
			else
				pMechanism.pParameter = 0;
			

			if(keyAttr.cka_keyClass == PKCS11Constants.CKO_SECRET_KEY){
				pTemplate = new CK_ATTRIBUTE[18];
				pTemplate[0] = new CK_ATTRIBUTE(PKCS11Constants.CKA_CLASS, PKCS11Constants.CKO_SECRET_KEY);
				pTemplate[1] = new CK_ATTRIBUTE(PKCS11Constants.CKA_KEY_TYPE, keyAttr.cka_keyType); // 
				
			}else if(keyAttr.cka_keyClass == PKCS11Constants.CKO_PRIVATE_KEY){
				pTemplate = new CK_ATTRIBUTE[19];
				pTemplate[0] = new CK_ATTRIBUTE(PKCS11Constants.CKA_CLASS, PKCS11Constants.CKO_PRIVATE_KEY);
				pTemplate[1] = new CK_ATTRIBUTE(PKCS11Constants.CKA_KEY_TYPE, PKCS11Constants.CKK_RSA);	// rsa
				pTemplate[18] = new CK_ATTRIBUTE(PKCS11Constants.CKA_SUBJECT, keyAttr.cka_subject);
			}else if(keyAttr.cka_keyClass == PKCS11Constants.CKO_PUBLIC_KEY){
				pTemplate = new CK_ATTRIBUTE[21];
				pTemplate[0] = new CK_ATTRIBUTE(PKCS11Constants.CKA_CLASS, PKCS11Constants.CKO_PUBLIC_KEY);
				pTemplate[1] = new CK_ATTRIBUTE(PKCS11Constants.CKA_KEY_TYPE, PKCS11Constants.CKK_RSA);	// rsa
				pTemplate[18] = new CK_ATTRIBUTE(PKCS11Constants.CKA_SUBJECT, keyAttr.cka_subject);
				pTemplate[19] = new CK_ATTRIBUTE(PKCS11Constants.CKA_MODULUS_BITS, keyAttr.cka_modulus_bits);
				pTemplate[20] = new CK_ATTRIBUTE(PKCS11Constants.CKA_PUBLIC_EXPONENT, keyAttr.cka_public_exponent);
			}else{
				throw new KmsException(KMSCode.KR_ATTRIBUTE_INVALID);
			}
			
			pTemplate[2] = new CK_ATTRIBUTE(PKCS11Constants.CKA_TOKEN, keyAttr.cka_token); // token or session object? token
			pTemplate[3] = new CK_ATTRIBUTE(PKCS11Constants.CKA_PRIVATE, keyAttr.cka_private);
			pTemplate[4] = new CK_ATTRIBUTE(PKCS11Constants.CKA_SENSITIVE, keyAttr.cka_sensitive);
			pTemplate[5] = new CK_ATTRIBUTE(PKCS11Constants.CKA_EXTRACTABLE, keyAttr.cka_extractable);
			pTemplate[6] = new CK_ATTRIBUTE(PKCS11Constants.CKA_ENCRYPT, keyAttr.cka_encrypt);
			pTemplate[7] = new CK_ATTRIBUTE(PKCS11Constants.CKA_DECRYPT, keyAttr.cka_decrypt);
			pTemplate[8] = new CK_ATTRIBUTE(PKCS11Constants.CKA_WRAP, keyAttr.cka_wrap);
			pTemplate[9] = new CK_ATTRIBUTE(PKCS11Constants.CKA_UNWRAP,	keyAttr.cka_unwrap);
			pTemplate[10] = new CK_ATTRIBUTE(PKCS11Constants.CKA_SIGN, keyAttr.cka_sign);
			pTemplate[11] = new CK_ATTRIBUTE(PKCS11Constants.CKA_VERIFY, keyAttr.cka_verify);
			pTemplate[12] = new CK_ATTRIBUTE(PKCS11Constants.CKA_SIGN_RECOVER, keyAttr.cka_signRecover);
			pTemplate[13] = new CK_ATTRIBUTE(PKCS11Constants.CKA_VERIFY_RECOVER, keyAttr.cka_verifyRecover);
			pTemplate[14] = new CK_ATTRIBUTE(PKCS11Constants.CKA_DERIVE, keyAttr.cka_derive);
			pTemplate[15] = new CK_ATTRIBUTE(PKCS11Constants.CKA_LABEL, keyAttr.cka_label);
			pTemplate[16] = new CK_ATTRIBUTE(PKCS11Constants.CKA_START_DATE,keyAttr.cka_startDate);
			pTemplate[17] = new CK_ATTRIBUTE(PKCS11Constants.CKA_END_DATE,keyAttr.cka_endDate);
			
			hKey = p11.C_DeriveKey(ts.hSession, pMechanism, hBasegKey, pTemplate);

			logger.debug("C_DeriveKey ret handle [{}]", hKey);
		} catch (PKCS11Exception e) {
			e.printStackTrace();
			throw new KmsException(KMSCode.KR_CRYPTO_ERROR);
		} catch(Exception ex){
			ex.printStackTrace();
			throw new KmsException(ex.getMessage());
		} finally {
			// ts.retSession();
		}

		cryptoResponse.hKey = hKey;
		cryptoResponse.generatedKeyLabel = cryptoResponse.generatedKeyLabel.format("%s", new String(keyAttr.cka_label));
		return cryptoResponse;
	}

	public CryptoResponse importPubKey(long slotIx, KeyAttributes keyAttr, TokenSession tokenSession) throws KmsException, Exception {
		logger.debug("MechRSA::importPubKey: enter...");
		CryptoResponse cryptoResponse = new CryptoResponse();

		CK_ATTRIBUTE[] pTemplate;
		long hKey = 0;
		
//		TokenSession ts = provider.getTokenSession(slotIx);
		
		try {
			pTemplate = new CK_ATTRIBUTE[21];
			pTemplate[0] = new CK_ATTRIBUTE(PKCS11Constants.CKA_CLASS, PKCS11Constants.CKO_PUBLIC_KEY);
			pTemplate[1] = new CK_ATTRIBUTE(PKCS11Constants.CKA_KEY_TYPE, PKCS11Constants.CKK_RSA);	// rsa
			pTemplate[2] = new CK_ATTRIBUTE(PKCS11Constants.CKA_TOKEN, keyAttr.cka_token);	// token or session object? token
			pTemplate[3] = new CK_ATTRIBUTE(PKCS11Constants.CKA_PRIVATE, keyAttr.cka_private);
			pTemplate[4] = new CK_ATTRIBUTE(PKCS11Constants.CKA_EXTRACTABLE, keyAttr.cka_extractable);
			pTemplate[5] = new CK_ATTRIBUTE(PKCS11Constants.CKA_SENSITIVE, keyAttr.cka_sensitive);
			pTemplate[6] = new CK_ATTRIBUTE(PKCS11Constants.CKA_ENCRYPT, keyAttr.cka_encrypt);
			pTemplate[7] = new CK_ATTRIBUTE(PKCS11Constants.CKA_DECRYPT, keyAttr.cka_decrypt);
			pTemplate[8] = new CK_ATTRIBUTE(PKCS11Constants.CKA_SIGN, keyAttr.cka_sign);
			pTemplate[9] = new CK_ATTRIBUTE(PKCS11Constants.CKA_VERIFY, keyAttr.cka_verify);
			pTemplate[10] = new CK_ATTRIBUTE(PKCS11Constants.CKA_WRAP, keyAttr.cka_wrap);
			pTemplate[11] = new CK_ATTRIBUTE(PKCS11Constants.CKA_UNWRAP, keyAttr.cka_unwrap);
			pTemplate[12] = new CK_ATTRIBUTE(PKCS11Constants.CKA_SIGN_RECOVER, keyAttr.cka_signRecover);
			pTemplate[13] = new CK_ATTRIBUTE(PKCS11Constants.CKA_VERIFY_RECOVER, keyAttr.cka_verifyRecover);
			pTemplate[14] = new CK_ATTRIBUTE(PKCS11Constants.CKA_DERIVE, keyAttr.cka_derive);
			pTemplate[15] = new CK_ATTRIBUTE(PKCS11Constants.CKA_LABEL, keyAttr.cka_label);
			pTemplate[16] = new CK_ATTRIBUTE(PKCS11Constants.CKA_SUBJECT, keyAttr.cka_subject);
			pTemplate[17] = new CK_ATTRIBUTE(PKCS11Constants.CKA_START_DATE, keyAttr.cka_startDate);
			pTemplate[18] = new CK_ATTRIBUTE(PKCS11Constants.CKA_END_DATE, keyAttr.cka_endDate);
			////////
			pTemplate[19] = new CK_ATTRIBUTE(PKCS11Constants.CKA_MODULUS, keyAttr.cka_modulus);
			pTemplate[20] = new CK_ATTRIBUTE(PKCS11Constants.CKA_PUBLIC_EXPONENT, keyAttr.cka_public_exponent);

			hKey = p11.C_CreateObject(tokenSession.hSession, pTemplate);
			logger.debug("C_CreateObject ret {}", hKey);
		}
		catch (PKCS11Exception e) {
			e.printStackTrace();
			logger.debug("PKCS11 Error Message::" + e.getMessage());
			throw new KmsException(KMSCode.KR_CRYPTO_ERROR);
		}catch(Exception ex){
			ex.printStackTrace();
			throw new KmsException(ex.getMessage());
		}
		finally {
//			ts.retSession();
		}

		cryptoResponse.hKey =  hKey;
		cryptoResponse.keyLabel = new String(keyAttr.cka_label);
		return cryptoResponse;
	
//		return new String(keyAttr.cka_label);
	}
	
	public CryptoResponse importPriKey(long slotIx, KeyAttributes keyAttr,TokenSession tokenSession) throws KmsException, Exception {
		logger.debug("MechRSA::importPriKey: enter...");
		CryptoResponse cryptoResponse = new CryptoResponse();
		
		CK_ATTRIBUTE[] pTemplate;
		long hKey = 0;
		
//		TokenSession ts = provider.getTokenSession(slotIx);
		
		logger.debug("MechRSA::importPriKey: CRT value length sum [{}]", keyAttr.cka_prime_1.length + keyAttr.cka_prime_2.length + keyAttr.cka_exponent_1.length + keyAttr.cka_exponent_2.length + keyAttr.cka_coefficient.length);
		
		try {
			/*
			if (keyAttr.cka_prime_1 != null && keyAttr.cka_prime_2 != null && 
					keyAttr.cka_exponent_1 != null && keyAttr.cka_exponent_2 != null && 
					keyAttr.cka_coefficient != null) {
			*/
			if ( (keyAttr.cka_prime_1.length + keyAttr.cka_prime_2.length + keyAttr.cka_exponent_1.length + keyAttr.cka_exponent_2.length + keyAttr.cka_coefficient.length) > 0) {
				pTemplate = new CK_ATTRIBUTE[26];
				
				pTemplate[19] = new CK_ATTRIBUTE(PKCS11Constants.CKA_MODULUS, keyAttr.cka_modulus);
				pTemplate[20] = new CK_ATTRIBUTE(PKCS11Constants.CKA_PRIVATE_EXPONENT, keyAttr.cka_private_exponent);
				
				pTemplate[21] = new CK_ATTRIBUTE(PKCS11Constants.CKA_PRIME_1, keyAttr.cka_prime_1);
				pTemplate[22] = new CK_ATTRIBUTE(PKCS11Constants.CKA_PRIME_2, keyAttr.cka_prime_2);
				pTemplate[23] = new CK_ATTRIBUTE(PKCS11Constants.CKA_EXPONENT_1, keyAttr.cka_exponent_1);
				pTemplate[24] = new CK_ATTRIBUTE(PKCS11Constants.CKA_EXPONENT_2, keyAttr.cka_exponent_2);
				pTemplate[25] = new CK_ATTRIBUTE(PKCS11Constants.CKA_COEFFICIENT, keyAttr.cka_coefficient);
				logger.debug("MechRSA::importPriKey: CRT format attributes...");
			}
			else if (keyAttr.cka_value != null && keyAttr.cka_value.length > 0) {
				pTemplate = new CK_ATTRIBUTE[20];
				
				pTemplate[19] = new CK_ATTRIBUTE(PKCS11Constants.CKA_VALUE, keyAttr.cka_value);
				logger.debug("MechRSA::importPriKey: value format attributes...");
			}
			else {	/* mod-exp */
				pTemplate = new CK_ATTRIBUTE[21];
				
				pTemplate[19] = new CK_ATTRIBUTE(PKCS11Constants.CKA_MODULUS, keyAttr.cka_modulus);
				pTemplate[20] = new CK_ATTRIBUTE(PKCS11Constants.CKA_PRIVATE_EXPONENT, keyAttr.cka_private_exponent);
				logger.debug("MechRSA::importPriKey: mod_exp format attributes...");
			}
			pTemplate[0] = new CK_ATTRIBUTE(PKCS11Constants.CKA_CLASS, PKCS11Constants.CKO_PRIVATE_KEY);
			pTemplate[1] = new CK_ATTRIBUTE(PKCS11Constants.CKA_KEY_TYPE, PKCS11Constants.CKK_RSA);	// rsa
			pTemplate[2] = new CK_ATTRIBUTE(PKCS11Constants.CKA_TOKEN, keyAttr.cka_token);	// token or session object? token
			pTemplate[3] = new CK_ATTRIBUTE(PKCS11Constants.CKA_PRIVATE, keyAttr.cka_private);
			pTemplate[4] = new CK_ATTRIBUTE(PKCS11Constants.CKA_EXTRACTABLE, keyAttr.cka_extractable);
			pTemplate[5] = new CK_ATTRIBUTE(PKCS11Constants.CKA_SENSITIVE, keyAttr.cka_sensitive);
			pTemplate[6] = new CK_ATTRIBUTE(PKCS11Constants.CKA_ENCRYPT, keyAttr.cka_encrypt);
			pTemplate[7] = new CK_ATTRIBUTE(PKCS11Constants.CKA_DECRYPT, keyAttr.cka_decrypt);
			pTemplate[8] = new CK_ATTRIBUTE(PKCS11Constants.CKA_SIGN, keyAttr.cka_sign);
			pTemplate[9] = new CK_ATTRIBUTE(PKCS11Constants.CKA_VERIFY, keyAttr.cka_verify);
			pTemplate[10] = new CK_ATTRIBUTE(PKCS11Constants.CKA_WRAP, keyAttr.cka_wrap);
			pTemplate[11] = new CK_ATTRIBUTE(PKCS11Constants.CKA_UNWRAP, keyAttr.cka_unwrap);
			pTemplate[12] = new CK_ATTRIBUTE(PKCS11Constants.CKA_SIGN_RECOVER, keyAttr.cka_signRecover);
			pTemplate[13] = new CK_ATTRIBUTE(PKCS11Constants.CKA_VERIFY_RECOVER, keyAttr.cka_verifyRecover);
			pTemplate[14] = new CK_ATTRIBUTE(PKCS11Constants.CKA_DERIVE, keyAttr.cka_derive);
			pTemplate[15] = new CK_ATTRIBUTE(PKCS11Constants.CKA_LABEL, keyAttr.cka_label);
			pTemplate[16] = new CK_ATTRIBUTE(PKCS11Constants.CKA_SUBJECT, keyAttr.cka_subject);
			pTemplate[17] = new CK_ATTRIBUTE(PKCS11Constants.CKA_START_DATE, keyAttr.cka_startDate);
			pTemplate[18] = new CK_ATTRIBUTE(PKCS11Constants.CKA_END_DATE, keyAttr.cka_endDate);
			//pTemplate[17] = new CK_ATTRIBUTE(PKCS11Constants.CKA_ID, Util.hexStringToByteArray("0123"));
			
			hKey = p11.C_CreateObject(tokenSession.hSession, pTemplate);
			logger.debug("C_CreateObject ret {}", hKey);
		}
		catch (PKCS11Exception e) {
			e.printStackTrace();
			throw new KmsException(KMSCode.KR_CRYPTO_ERROR);
		}catch(Exception ex){
			ex.printStackTrace();
			throw new KmsException(ex.getMessage());
		}
		finally {
//			ts.retSession();
		}

		cryptoResponse.hKey =  hKey;
		cryptoResponse.keyLabel = new String(keyAttr.cka_label);
//		cryptoResponse.keyLabel.format("%s", new String(keyAttr.cka_label));
//		return new String(keyAttr.cka_label);
		return cryptoResponse;
				
	}

	public CryptoResponse generateKeyPair(long slotIx, KeyAttributes publicKeyAttr, KeyAttributes PrivateKeyAttr,TokenSession tokenSession) throws KmsException, Exception {
		logger.debug("MechRSA::generateKeyPair: enter...");
		CryptoResponse cryptoResponse = new CryptoResponse();

		CK_MECHANISM pMechanism;
		CK_ATTRIBUTE[] pPubKeyTemplate;
		CK_ATTRIBUTE[] pPriKeyTemplate;
		long[] hKeys = null;
		
//		TokenSession ts = provider.getTokenSession(slotIx);
		
		try {
			/* C_SeedRandom */
			//pSeed = keyAttr.cka_seed;	// hexStringToByteArray("b0bfd020ea3a6910a2d808002b30309de04fd020ea3a6b10");
			//p11.C_SeedRandom(ts.hSession, pSeed);
			/* C_GenerateRandom? */

			/***/

			pMechanism = new CK_MECHANISM();
			pMechanism.mechanism = PKCS11Constants.CKM_RSA_PKCS_KEY_PAIR_GEN;
			
			pPubKeyTemplate = new CK_ATTRIBUTE[21];
			pPubKeyTemplate[0] = new CK_ATTRIBUTE(PKCS11Constants.CKA_CLASS, PKCS11Constants.CKO_PUBLIC_KEY);
			pPubKeyTemplate[1] = new CK_ATTRIBUTE(PKCS11Constants.CKA_KEY_TYPE, PKCS11Constants.CKK_RSA);	// rsa
			pPubKeyTemplate[2] = new CK_ATTRIBUTE(PKCS11Constants.CKA_TOKEN, publicKeyAttr.cka_token);	// token or session object? token
			pPubKeyTemplate[3] = new CK_ATTRIBUTE(PKCS11Constants.CKA_PRIVATE, publicKeyAttr.cka_private);
			pPubKeyTemplate[4] = new CK_ATTRIBUTE(PKCS11Constants.CKA_EXTRACTABLE, publicKeyAttr.cka_extractable);
			pPubKeyTemplate[5] = new CK_ATTRIBUTE(PKCS11Constants.CKA_SENSITIVE, publicKeyAttr.cka_sensitive);
			pPubKeyTemplate[6] = new CK_ATTRIBUTE(PKCS11Constants.CKA_ENCRYPT, publicKeyAttr.cka_encrypt);
			pPubKeyTemplate[7] = new CK_ATTRIBUTE(PKCS11Constants.CKA_DECRYPT, publicKeyAttr.cka_decrypt);
			pPubKeyTemplate[8] = new CK_ATTRIBUTE(PKCS11Constants.CKA_SIGN, publicKeyAttr.cka_sign);
			pPubKeyTemplate[9] = new CK_ATTRIBUTE(PKCS11Constants.CKA_VERIFY, publicKeyAttr.cka_verify);
			pPubKeyTemplate[10] = new CK_ATTRIBUTE(PKCS11Constants.CKA_WRAP, publicKeyAttr.cka_wrap);
			pPubKeyTemplate[11] = new CK_ATTRIBUTE(PKCS11Constants.CKA_UNWRAP, publicKeyAttr.cka_unwrap);
			pPubKeyTemplate[12] = new CK_ATTRIBUTE(PKCS11Constants.CKA_SIGN_RECOVER, publicKeyAttr.cka_signRecover);
			pPubKeyTemplate[13] = new CK_ATTRIBUTE(PKCS11Constants.CKA_VERIFY_RECOVER, publicKeyAttr.cka_verifyRecover);
			pPubKeyTemplate[14] = new CK_ATTRIBUTE(PKCS11Constants.CKA_DERIVE, publicKeyAttr.cka_derive);
			pPubKeyTemplate[15] = new CK_ATTRIBUTE(PKCS11Constants.CKA_LABEL, publicKeyAttr.cka_label);
			pPubKeyTemplate[16] = new CK_ATTRIBUTE(PKCS11Constants.CKA_SUBJECT, publicKeyAttr.cka_subject);
			pPubKeyTemplate[17] = new CK_ATTRIBUTE(PKCS11Constants.CKA_START_DATE, publicKeyAttr.cka_startDate);
			pPubKeyTemplate[18] = new CK_ATTRIBUTE(PKCS11Constants.CKA_END_DATE, publicKeyAttr.cka_endDate);
			////////
			pPubKeyTemplate[19] = new CK_ATTRIBUTE(PKCS11Constants.CKA_MODULUS_BITS,
										publicKeyAttr.cka_modulus_bits);
			pPubKeyTemplate[20] = new CK_ATTRIBUTE(PKCS11Constants.CKA_PUBLIC_EXPONENT,
										publicKeyAttr.cka_public_exponent);

			/////////////////////////////////////////////////
			pPriKeyTemplate = new CK_ATTRIBUTE[19];
			pPriKeyTemplate[0] = new CK_ATTRIBUTE(PKCS11Constants.CKA_CLASS, PKCS11Constants.CKO_PRIVATE_KEY);
			pPriKeyTemplate[1] = new CK_ATTRIBUTE(PKCS11Constants.CKA_KEY_TYPE, PKCS11Constants.CKK_RSA);	// rsa
			pPriKeyTemplate[2] = new CK_ATTRIBUTE(PKCS11Constants.CKA_TOKEN, PrivateKeyAttr.cka_token);	// token or session object? token
			pPriKeyTemplate[3] = new CK_ATTRIBUTE(PKCS11Constants.CKA_PRIVATE, PrivateKeyAttr.cka_private);
			pPriKeyTemplate[4] = new CK_ATTRIBUTE(PKCS11Constants.CKA_EXTRACTABLE, PrivateKeyAttr.cka_extractable);
			pPriKeyTemplate[5] = new CK_ATTRIBUTE(PKCS11Constants.CKA_SENSITIVE, PrivateKeyAttr.cka_sensitive);
			pPriKeyTemplate[6] = new CK_ATTRIBUTE(PKCS11Constants.CKA_ENCRYPT, PrivateKeyAttr.cka_encrypt);
			pPriKeyTemplate[7] = new CK_ATTRIBUTE(PKCS11Constants.CKA_DECRYPT, PrivateKeyAttr.cka_decrypt);
			pPriKeyTemplate[8] = new CK_ATTRIBUTE(PKCS11Constants.CKA_SIGN, PrivateKeyAttr.cka_sign);
			pPriKeyTemplate[9] = new CK_ATTRIBUTE(PKCS11Constants.CKA_VERIFY, PrivateKeyAttr.cka_verify);
			pPriKeyTemplate[10] = new CK_ATTRIBUTE(PKCS11Constants.CKA_WRAP, PrivateKeyAttr.cka_wrap);
			pPriKeyTemplate[11] = new CK_ATTRIBUTE(PKCS11Constants.CKA_UNWRAP, PrivateKeyAttr.cka_unwrap);
			pPriKeyTemplate[12] = new CK_ATTRIBUTE(PKCS11Constants.CKA_SIGN_RECOVER, PrivateKeyAttr.cka_signRecover);
			pPriKeyTemplate[13] = new CK_ATTRIBUTE(PKCS11Constants.CKA_VERIFY_RECOVER, PrivateKeyAttr.cka_verifyRecover);
			pPriKeyTemplate[14] = new CK_ATTRIBUTE(PKCS11Constants.CKA_DERIVE, PrivateKeyAttr.cka_derive);
			pPriKeyTemplate[15] = new CK_ATTRIBUTE(PKCS11Constants.CKA_LABEL, PrivateKeyAttr.cka_label);
			pPriKeyTemplate[16] = new CK_ATTRIBUTE(PKCS11Constants.CKA_SUBJECT, PrivateKeyAttr.cka_subject);
			pPriKeyTemplate[17] = new CK_ATTRIBUTE(PKCS11Constants.CKA_START_DATE, PrivateKeyAttr.cka_startDate);
			pPriKeyTemplate[18] = new CK_ATTRIBUTE(PKCS11Constants.CKA_END_DATE, PrivateKeyAttr.cka_endDate);
			//pPriKeyTemplate[17] = new CK_ATTRIBUTE(PKCS11Constants.CKA_ID, Util.hexStringToByteArray("0123"));

			hKeys = p11.C_GenerateKeyPair(tokenSession.hSession, pMechanism, pPubKeyTemplate, pPriKeyTemplate);
			logger.debug("C_GenerateKeyPair ret {}:{}", hKeys[0], hKeys[1]);
		}
		catch (PKCS11Exception e) {
			e.printStackTrace();
			throw new KmsException(KMSCode.KR_CRYPTO_ERROR);
		}catch(Exception ex){
			ex.printStackTrace();
			throw new KmsException(ex.getMessage());
		}
		finally {
//			ts.retSession();
		}
		
		String[] labels = new String[2];
		labels[0] = new String(publicKeyAttr.cka_label);
		labels[1] = new String(PrivateKeyAttr.cka_label);
		cryptoResponse.keyLabelPair[0] = labels[0]; 
		cryptoResponse.keyLabelPair[1] = labels[1]; 
		return cryptoResponse;
//		return labels;
	}

	// sign - ret signature for the data
	public byte[] sign(long slotIx, String mechanism, String[] mechanismParameters, String keyLabel, byte[] data, TokenSession tokenSession) throws KmsException, Exception {

		logger.debug("MechGeneric::sign: start.....");
		
		byte[] signature;
		long hKey;

		long mechanismValue =getMechanism(mechanism); 
		
		try{
			if (getMechanism(mechanism) == -1){
				throw new KmsException(KMSCode.KR_MECHANISM_INVALID);
			}	
		}catch (KmsException ke) {
			ke.printStackTrace();
			throw new KmsException(KMSCode.KR_MECHANISM_INVALID);
		}
		

		try {
			CK_ATTRIBUTE[] pTemplate = new CK_ATTRIBUTE[1];
			pTemplate[0] = new CK_ATTRIBUTE(PKCS11Constants.CKA_LABEL, keyLabel);
			p11.C_FindObjectsInit(tokenSession.hSession, pTemplate);
			long[] found = p11.C_FindObjects(tokenSession.hSession, 10);
			
			try{
				if (found == null || found.length == 0) {
					throw new KmsException(KMSCode.KR_KEY_NOT_FOUND);
				}	
			}catch (KmsException ke) {
				ke.printStackTrace();
				throw new KmsException(KMSCode.KR_KEY_NOT_FOUND);
			}
			
			hKey = found[0];
			logger.debug("sign:C_FindObjects ret {}:{}", found.length, hKey);
			
			CK_MECHANISM pMechanism;
			
			pMechanism = new CK_MECHANISM(mechanismValue);
			
			p11.C_SignInit(tokenSession.hSession, pMechanism, hKey);
			signature = p11.C_Sign(tokenSession.hSession, data);

			logger.debug("C_Sign ret text [{}:{}]", signature.length, Functions.toHexString(signature));
		}
		catch (PKCS11Exception e) {
			e.printStackTrace();
			throw new KmsException(KMSCode.KR_CRYPTO_ERROR);
		}
		catch(Exception ex){
			ex.printStackTrace();
			throw new KmsException(ex.getMessage());
		}
		finally {

		}

		return signature;
	}
 
	// verify - verification success:true, fail:false
	public boolean verify(long slotIx, String mechanism, String[] mechanismParameters, String keyLabel, byte[] data, byte[] signature, TokenSession tokenSession) throws KmsException, Exception {
		logger.debug("MechDes::verify: start.....");
		
		logger.debug("verify: signature [{}], data [{}]", Functions.toHexString(signature), Functions.toHexString(data));

		long hKey;
		boolean verifySuccess = false;
		
		long mechanismValue =getMechanism(mechanism); 
		
		try{
			if (getMechanism(mechanism) == -1){
				throw new KmsException(KMSCode.KR_MECHANISM_INVALID);
			}	
		}catch (KmsException ke) {
			ke.printStackTrace();
			throw new KmsException(KMSCode.KR_MECHANISM_INVALID);
		}
		
		try {
			CK_ATTRIBUTE[] pTemplate = new CK_ATTRIBUTE[1];
			pTemplate[0] = new CK_ATTRIBUTE(PKCS11Constants.CKA_LABEL, keyLabel);
			p11.C_FindObjectsInit(tokenSession.hSession, pTemplate);
			long[] found = p11.C_FindObjects(tokenSession.hSession, 10);
			
			try{
				if (found == null || found.length == 0) {
					throw new KmsException(KMSCode.KR_KEY_NOT_FOUND);
				}	
			}catch (KmsException ke) {
				ke.printStackTrace();
				throw new KmsException(KMSCode.KR_KEY_NOT_FOUND);
			}
			
			hKey = found[0];
			logger.debug("sign:C_FindObjects ret {}:{}", found.length, hKey);
			
			CK_MECHANISM pMechanism;
			pMechanism = new CK_MECHANISM(mechanismValue);
			
			p11.C_VerifyInit(tokenSession.hSession, pMechanism, hKey);
			p11.C_Verify(tokenSession.hSession, data, signature);
			verifySuccess = true;

			logger.debug("verify:C_Verify ret [{}]", verifySuccess);
		}
		catch (PKCS11Exception e) {
			//add by shifei 2014-04-16
			if (e.getLocalizedMessage() == "CKR_SIGNATURE_INVALID") {
				verifySuccess = false;
				logger.debug("verify:C_Verify ret [{}]", verifySuccess);
				return verifySuccess;
			}else{
				e.printStackTrace();
				throw new KmsException(KMSCode.KR_CRYPTO_ERROR);
			}
//			e.printStackTrace();
//			throw new KmsException(KMSCode.KR_CRYPTO_ERROR);
		}catch(Exception ex){
			ex.printStackTrace();
			throw new KmsException(ex.getMessage());
		}
		finally {
			
		}

		return verifySuccess;
	}
	
	
	
	
	public long getMechanism(String strMechanism){

		long mechanism = -1;

		logger.debug("getMechanism strMechanism : [{}]", strMechanism);
		
		if(strMechanism.equalsIgnoreCase("DES_KEY_GEN"))
		{ 
			mechanism = PKCS11Constants.CKK_DES; // des3
		}
		else if(strMechanism.equalsIgnoreCase("DES2_KEY_GEN"))
		{ 
			mechanism = PKCS11Constants.CKK_DES2; // des2
		}
		else if(strMechanism.equalsIgnoreCase("DES3_KEY_GEN"))
		{ 
			mechanism = PKCS11Constants.CKK_DES3; // des3
		}
		else if(strMechanism.equalsIgnoreCase("SEED_KEY_GEN"))
		{ 
			mechanism = PKCS11Constants.CKA_VENDOR_DEFINED + 0x203; // des3
		}
		else if(strMechanism.equalsIgnoreCase("AES_KEY_GEN"))
		{ 
			mechanism = PKCS11Constants.CKK_AES; // des3
		}
		else if (strMechanism.equalsIgnoreCase("DES3_ECB")) 
		{
			mechanism = PKCS11Constants.CKM_DES3_ECB;
		}
		else if (strMechanism.equalsIgnoreCase("DES2_ECB"))
		{
			mechanism = PKCS11Constants.CKM_DES3_ECB;
		}
		else if (strMechanism.equalsIgnoreCase("DES_ECB"))
		{
			mechanism = PKCS11Constants.CKM_DES_ECB;
		}
		else if (strMechanism.equalsIgnoreCase("SEED_ECB"))
		{
			mechanism = PKCS11Constants.CKM_VENDOR_DEFINED + 0x9d1;
		}
		else if (strMechanism.equalsIgnoreCase("AES_ECB"))
		{
			mechanism = PKCS11Constants.CKM_AES_ECB;
		}
		else if (strMechanism.equalsIgnoreCase("DES3_CBC")) 
		{
			mechanism = PKCS11Constants.CKM_DES3_CBC;
		}
		else if (strMechanism.equalsIgnoreCase("DES2_CBC"))
		{
			mechanism = PKCS11Constants.CKM_DES3_CBC;
		}
		else if (strMechanism.equalsIgnoreCase("DES_CBC"))
		{
			mechanism = PKCS11Constants.CKM_DES_CBC;
		}
		else if (strMechanism.equalsIgnoreCase("SEED_CBC"))
		{
			mechanism = PKCS11Constants.CKM_VENDOR_DEFINED + 0x9d2;
		}
		else if (strMechanism.equalsIgnoreCase("AES_CBC"))
		{
			mechanism = PKCS11Constants.CKM_AES_CBC;
		}
		else if (strMechanism.equalsIgnoreCase("RSA_PKCS_KEY_PAIR_GEN"))
		{
			mechanism = PKCS11Constants.CKM_RSA_PKCS_KEY_PAIR_GEN;
		}
		else if (strMechanism.equalsIgnoreCase("RSA_PKCS"))
		{
			mechanism = PKCS11Constants.CKM_RSA_PKCS;
		}
		else if (strMechanism.equalsIgnoreCase("RSA_X_509"))
		{
			mechanism = PKCS11Constants.CKM_RSA_X_509;
		}
		else if (strMechanism.equalsIgnoreCase("SEED_MAC"))
		{
			mechanism = PKCS11Constants.CKM_VENDOR_DEFINED + 0x9d3;
		}
		else if (strMechanism.equalsIgnoreCase("DES_MAC"))
		{
			mechanism = PKCS11Constants.CKM_DES_MAC;
		}
		else if (strMechanism.equalsIgnoreCase("DES3_MAC"))
		{
			mechanism = PKCS11Constants.CKM_DES3_MAC;
		}
		else if (strMechanism.equalsIgnoreCase("AES_MAC"))
		{
			mechanism = PKCS11Constants.CKM_AES_MAC;
		}
		
		
		//test
		else if (strMechanism.equalsIgnoreCase("SHA1_KEY_DERIVATION"))
		{
			mechanism = PKCS11Constants.CKM_SHA1_KEY_DERIVATION;
		}
		else if (strMechanism.equalsIgnoreCase("SHA224_KEY_DERIVATION"))
		{
//			mechanism = PKCS11Constants.CKM_SHA224_KEY_DERIVATION;
		}
		else if (strMechanism.equalsIgnoreCase("SHA256_KEY_DERIVATION"))
		{
//			mechanism = PKCS11Constants.CKM_SHA256_KEY_DERIVATION;
		}
		else if (strMechanism.equalsIgnoreCase("SHA384_KEY_DERIVATION"))
		{
//			mechanism = PKCS11Constants.CKM_SHA384_KEY_DERIVATION;
		}
		else if (strMechanism.equalsIgnoreCase("SHA512_KEY_DERIVATION"))
		{
//			mechanism = PKCS11Constants.CKM_SHA512_KEY_DERIVATION;
		}
		
		
		else if (strMechanism.equalsIgnoreCase("XOR_BASE_AND_DATA"))
		{
			mechanism = PKCS11Constants.CKM_XOR_BASE_AND_DATA;
		}
		else if (strMechanism.equalsIgnoreCase("EXTRACT_KEY_FROM_KEY"))
		{
			mechanism = PKCS11Constants.CKM_EXTRACT_KEY_FROM_KEY;
		}
		else if (strMechanism.equalsIgnoreCase("CONCATENATE_BASE_AND_DATA"))
		{
			mechanism = PKCS11Constants.CKM_CONCATENATE_BASE_AND_DATA;
		}
		else if (strMechanism.equalsIgnoreCase("CONCATENATE_BASE_AND_KEY"))
		{
			mechanism = PKCS11Constants.CKM_CONCATENATE_BASE_AND_KEY;
		}
		else if (strMechanism.equalsIgnoreCase("CONCATENATE_DATA_AND_BASE"))
		{
			mechanism = PKCS11Constants.CKM_CONCATENATE_DATA_AND_BASE;
		}
		

	
		
		
		
		logger.debug("getMechanism mechanism value : [{}]", mechanism);

		return mechanism;
	}	
}
