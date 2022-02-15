package com.kona.kms.crypto.mech;
import java.math.BigInteger;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import sun.security.pkcs11.wrapper.*;

import com.kona.kms.crypto.CryptoManager;
import com.kona.kms.KMSCode;
import com.kona.kms.KmsException;
import com.kona.kms.crypto.KeyAttributes;
import com.kona.kms.crypto.TokenSession;
import com.kona.kms.crypto.bo.CryptoResponse;
import com.kona.kms.utils.Util;

@SuppressWarnings("restriction")

public class MechRSA extends MechPkcs11 {

	private static final Logger logger = LoggerFactory.getLogger(MechRSA.class);
	static MechRSA m_instance;
	PKCS11 p11;
	CryptoManager provider;

	public static MechRSA getInstance() {
		logger.debug("MechRSA::getInstance: invoked....");
		if (m_instance == null) {
			m_instance = new MechRSA();
		}
		return m_instance;
	}
	private MechRSA() {
		logger.debug("MechRSA::MechRSA: invoked....");
	}

	public void setProvider(CryptoManager provider) {
		logger.debug("MechRSA::setProvider: invoked....");
		this.provider = provider;
		p11 = provider.getPkcs11Intf();
	}
	
	
	public CryptoResponse importPubKey(long slotIx, KeyAttributes keyAttr) throws KmsException {
		logger.debug("MechRSA::importPubKey: enter...");
		CryptoResponse cryptoResponse = new CryptoResponse();

		CK_ATTRIBUTE[] pTemplate;
		long hKey = 0;
		
		TokenSession ts = provider.getTokenSession(slotIx);
		
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

			hKey = p11.C_CreateObject(ts.hSession, pTemplate);
			logger.debug("C_CreateObject ret {}", hKey);
		}
		catch (PKCS11Exception e) {
			e.printStackTrace();
			logger.debug("PKCS11 Error Message::" + e.getMessage());
			throw new KmsException(KMSCode.KR_CRYPTO_ERROR);
		}
		finally {
			ts.retSession();
		}

		cryptoResponse.hKey =  hKey;
		cryptoResponse.keyLabel.format("%s", new String(keyAttr.cka_label));
		return cryptoResponse;
	
//		return new String(keyAttr.cka_label);
	}
	
	public CryptoResponse importPriKey(long slotIx, KeyAttributes keyAttr) throws KmsException {
		logger.debug("MechRSA::importPriKey: enter...");
		CryptoResponse cryptoResponse = new CryptoResponse();
		
		CK_ATTRIBUTE[] pTemplate;
		long hKey = 0;
		
		TokenSession ts = provider.getTokenSession(slotIx);
		
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
			
			hKey = p11.C_CreateObject(ts.hSession, pTemplate);
			logger.debug("C_CreateObject ret {}", hKey);
		}
		catch (PKCS11Exception e) {
			e.printStackTrace();
			throw new KmsException(KMSCode.KR_CRYPTO_ERROR);
		}
		finally {
			ts.retSession();
		}

		cryptoResponse.hKey =  hKey;
		cryptoResponse.keyLabel.format("%s", new String(keyAttr.cka_label));
		return cryptoResponse;
				
//		return new String(keyAttr.cka_label);
	}

	public CryptoResponse generateKeyPair(long slotIx, KeyAttributes publicKeyAttr, KeyAttributes PrivateKeyAttr) throws KmsException {
		logger.debug("MechRSA::generateKeyPair: enter...");
		CryptoResponse cryptoResponse = new CryptoResponse();

		CK_MECHANISM pMechanism;
		CK_ATTRIBUTE[] pPubKeyTemplate;
		CK_ATTRIBUTE[] pPriKeyTemplate;
		long[] hKeys = null;
		
		TokenSession ts = provider.getTokenSession(slotIx);
		
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

			hKeys = p11.C_GenerateKeyPair(ts.hSession, pMechanism, pPubKeyTemplate, pPriKeyTemplate);
			logger.debug("C_GenerateKeyPair ret {}:{}", hKeys[0], hKeys[1]);
		}
		catch (PKCS11Exception e) {
			e.printStackTrace();
			throw new KmsException(KMSCode.KR_CRYPTO_ERROR);
		}
		finally {
			ts.retSession();
		}
		
		String[] labels = new String[2];
		labels[0] = new String(publicKeyAttr.cka_label);
		labels[1] = new String(PrivateKeyAttr.cka_label);
		cryptoResponse.keyLabelPair[0] = labels[0]; 
		cryptoResponse.keyLabelPair[1] = labels[1]; 
		return cryptoResponse;
//		return labels;
	}

	public CryptoResponse encrypt(long slotIx, String mechanism, String[] mechanismParameters, String keyLabel, byte[] data) throws KmsException {
		logger.debug("MechRSA::encrypt: enter...");
		CryptoResponse cryptoResponse = new CryptoResponse();

		if (mechanism.equalsIgnoreCase("RSA_PKCS") == false && mechanism.equalsIgnoreCase("RSA_X_509") == false) {
			throw new KmsException(KMSCode.KR_MECHANISM_INVALID);
		}
		
		TokenSession ts = provider.getTokenSession(slotIx);

		int blockSize = 8;
		int iDataLen;
		byte[] iData;
		byte[] encryptedText = null;
		int encryptedLen;
		
		try {
			CK_ATTRIBUTE[] pTemplate = new CK_ATTRIBUTE[1];
			pTemplate[0] = new CK_ATTRIBUTE(PKCS11Constants.CKA_LABEL, keyLabel);
			p11.C_FindObjectsInit(ts.hSession, pTemplate);
			long[] found = p11.C_FindObjects(ts.hSession, 10);
			if (found == null || found.length == 0) {
				throw new KmsException(KMSCode.KR_KEY_NOT_FOUND);
			}
			long hKey = found[0];
			logger.debug("MechRSA::C_FindObjects ret {}:{}", found.length, hKey);
			
			CK_MECHANISM pMechanism;
			if (mechanism.equalsIgnoreCase("RSA_PKCS") == true) {
				pMechanism = new CK_MECHANISM(PKCS11Constants.CKM_RSA_PKCS);
			}
			else { // "RSA_X_509"
				pMechanism = new CK_MECHANISM(PKCS11Constants.CKM_RSA_X_509);
			}

			blockSize = 8;
			if (data.length % blockSize == 0) {
				iDataLen = data.length;
				iData = data;
			}
			else {
				iDataLen = data.length;
				iData = data;
				/*
				iDataLen = ((data.length/blockSize) + 1) * blockSize;
				iData = new byte[iDataLen];
				byte[] space = "          ".getBytes();
				System.arraycopy(data, 0, iData, 0, data.length);
				System.arraycopy(space, 0, iData, data.length, iDataLen-data.length);
				*/
			}

			int eBufLen = iDataLen*100;
			byte[] encryptedBuf = new byte[eBufLen];
			p11.C_EncryptInit(ts.hSession, pMechanism, hKey);
			encryptedLen = p11.C_Encrypt(ts.hSession, iData, 0, iDataLen, encryptedBuf, 0, eBufLen);
			encryptedText = new byte[encryptedLen];
			System.arraycopy(encryptedBuf, 0, encryptedText, 0, encryptedLen);
			logger.debug("encrypt: C_Encrypt ret text [{}:{}]", encryptedLen, Functions.toHexString(encryptedText));
		}
		catch (PKCS11Exception e) {
			e.printStackTrace();
			throw new KmsException(KMSCode.KR_CRYPTO_ERROR);
		}
		finally {
			ts.retSession();
		}

		cryptoResponse.dataEncrypted = encryptedText;
		return cryptoResponse;

//		return encryptedText;
	}

	public CryptoResponse decrypt(long slotIx, String mechanism, String[] mechanismParameters, String keyLabel, byte[] edata) throws KmsException {
		logger.debug("MechRSA::decrypt: enter...");
		//long mechId = Functions.getMechanismId(mechanism);
		CryptoResponse cryptoResponse = new CryptoResponse();

		if (mechanism.equalsIgnoreCase("RSA_PKCS") == false && mechanism.equalsIgnoreCase("RSA_X_509") == false) {
			throw new KmsException(KMSCode.KR_MECHANISM_INVALID);
		}
		
		TokenSession ts = provider.getTokenSession(slotIx);

		int blockSize = 8;
		int iDataLen;
		byte[] iData;
		byte[] decryptedText = null;
		
		try {
			CK_ATTRIBUTE[] pTemplate = new CK_ATTRIBUTE[1];
			pTemplate[0] = new CK_ATTRIBUTE(PKCS11Constants.CKA_LABEL, keyLabel);
			p11.C_FindObjectsInit(ts.hSession, pTemplate);
			long[] found = p11.C_FindObjects(ts.hSession, 10);
			if (found == null || found.length == 0) {
				throw new KmsException(KMSCode.KR_KEY_NOT_FOUND);
			}
			long hKey = found[0];
			logger.debug("C_FindObjects ret {}:{}", found.length, hKey);
			
			CK_MECHANISM pMechanism;
			if (mechanism.equalsIgnoreCase("RSA_PKCS") == true) {
				pMechanism = new CK_MECHANISM(PKCS11Constants.CKM_RSA_PKCS);
			}
			else { // "RSA_X_509"
				pMechanism = new CK_MECHANISM(PKCS11Constants.CKM_RSA_X_509);
			}

			if (edata.length % blockSize == 0) {
				iDataLen = edata.length;
				iData = edata;
			}
			else {
				/*
				iDataLen = ((edata.length/blockSize) + 1) * blockSize;
				iData = new byte[iDataLen];
				byte[] space = "          ".getBytes();
				System.arraycopy(edata, 0, iData, 0, edata.length);
				System.arraycopy(space, 0, iData, edata.length, iDataLen-edata.length);
				*/
				iDataLen = edata.length;
				iData = edata;
			}
logger.debug("decrypt: data len [{}]", iDataLen);
			decryptedText = new byte[iDataLen];
			p11.C_DecryptInit(ts.hSession, pMechanism, hKey);
			int decryptedLen = p11.C_Decrypt(ts.hSession, iData, 0, iDataLen, decryptedText, 0, iDataLen);
			logger.debug("decrypt: C_Decrypt ret text [{}:{}]", decryptedLen, Functions.toHexString(decryptedText));
		}
		catch (PKCS11Exception e) {
			e.printStackTrace();
			throw new KmsException(KMSCode.KR_CRYPTO_ERROR);
		}
		finally {
			ts.retSession();
		}

		cryptoResponse.dataDecrypted = decryptedText;
		return cryptoResponse;

//		return decryptedText;
	}

}
