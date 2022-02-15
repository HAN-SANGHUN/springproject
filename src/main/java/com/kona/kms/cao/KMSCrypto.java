package com.kona.kms.cao;


import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.kona.kms.KMSCode;
import com.kona.kms.KmsException;
import com.kona.kms.crypto.CryptoManager;
import com.kona.kms.crypto.KeyAttrMgmt;
import com.kona.kms.crypto.KeyAttributes;
import com.kona.kms.crypto.MechMgmt;
import com.kona.kms.crypto.TokenSession;
import com.kona.kms.crypto.bo.CryptoResponse;
import com.kona.kms.crypto.mech.MechGeneric;
import com.kona.kms.crypto.mech.MechPkcs11;
import com.kona.kms.crypto.mech.MechRSA;
import com.kona.kms.utils.Util;
import com.kona.kms.token.model.KeyInfo;
import com.kona.kms.token.KeyManager;
import com.kona.kms.token.KeyMap;


public class KMSCrypto {
	private static final Logger logger = LoggerFactory.getLogger(KMSCrypto.class);
	
	static KMSCrypto m_instance;
	KeyMap kmap;

	public static KMSCrypto getInstance() {
		logger.debug("KMSCrypto::getInstance: invoked....");
		if (m_instance == null) {
			m_instance = new KMSCrypto();
		}
		return m_instance;
	}
	

	private KMSCrypto() {
		logger.debug("KMSCrypto::KMSCrypto: invoked....");
		kmap = KeyMap.getInstance();
	}
		
	// get Key Attributes
	public String[] getKeyAttributes(String tokenLabel, String keyLabel) throws KmsException, Exception {
		logger.debug("KmsCrypto::getKeyAttributes: Start..... tokenLabel [{}]", tokenLabel);

		CryptoResponse cryptoResponse = new CryptoResponse();

		String[] ret = null;
		long ptokenLoc = KeyManager.getInstance().getTokenIndexforTokenLabel(tokenLabel);
		if (ptokenLoc <0)
			throw new KmsException(KMSCode.KR_TOKEN_NOT_FOUND);
			
		logger.debug("KmsCrypto::getKeyAttributes: ptokenLoc : [{}]", ptokenLoc);
//		ret = MechGeneric.getInstance().getAttributeValue(ptokenLoc, keyLabel);

		CryptoManager provider = CryptoManager.getInstance();
		TokenSession tokenSession = provider.getTokenSession(ptokenLoc);

		cryptoResponse = MechGeneric.getInstance().getAttributeValue(ptokenLoc, keyLabel,tokenSession);
		
		tokenSession.CloseSession();
//		cryptoResponse = MechGeneric.getInstance().getAttributeValue(ptokenLoc, keyLabel);

		ret = cryptoResponse.attrs;
		return ret;
	}
	
	//add by shifei
	// get Key Attributes
	public String[] getKeyAttributesModulus(String tokenLabel, String keyLabel) throws KmsException {
		logger.debug("KmsCrypto::getKeyAttributes: Start..... tokenLabel [{}]", tokenLabel);

		CryptoResponse cryptoResponse = new CryptoResponse();

		String[] ret = null;
		long ptokenLoc = KeyManager.getInstance().getTokenIndexforTokenLabel(tokenLabel);
		if (ptokenLoc <0)
			throw new KmsException(KMSCode.KR_TOKEN_NOT_FOUND);
			
		logger.debug("KmsCrypto::getKeyAttributes: ptokenLoc : [{}]", ptokenLoc);
//		ret = MechGeneric.getInstance().getAttributeValue(ptokenLoc, keyLabel);

		CryptoManager provider = CryptoManager.getInstance();
		TokenSession tokenSession = provider.getTokenSession(ptokenLoc);

		cryptoResponse = MechGeneric.getInstance().getAttributeValueModulus(ptokenLoc, keyLabel,tokenSession);
		
		tokenSession.CloseSession();
//		cryptoResponse = MechGeneric.getInstance().getAttributeValue(ptokenLoc, keyLabel);

		ret = cryptoResponse.attrs;
		return ret;
	}
	
	// generate secret key
	public String generateSecretKey(String mechanism, int keySizeBits, String tokenLabel, String[] keyAttr) throws KmsException, Exception {
		logger.debug("KmsCrypto::generateSecretKey: Start..... tokenLabel [{}]", tokenLabel);
		
		CryptoResponse cryptoResponse = new CryptoResponse();
		MechPkcs11 mech=null;

		String redefineMechanism = "";
		if(mechanism.equalsIgnoreCase("DES") == true && keySizeBits == 64) {
//			mech = MechDes.getInstance();
			redefineMechanism = "DES_KEY_GEN";
		}
		else if(mechanism.equalsIgnoreCase("DES") == true && keySizeBits == 128) {
//			mech = MechDoubleDes.getInstance();
			redefineMechanism = "DES2_KEY_GEN";
		}
		else if(mechanism.equalsIgnoreCase("DES") == true && keySizeBits == 192) {
//			mech = MechTripleDes.getInstance();
			redefineMechanism = "DES3_KEY_GEN";
		}
		else if (mechanism.equalsIgnoreCase("AES") == true) {
//			mech = MechAES.getInstance();
			redefineMechanism = "AES_KEY_GEN";
		}
		else if (mechanism.equalsIgnoreCase("SEED") == true) {
//			mech = MechSeed.getInstance();
			redefineMechanism = "SEED_KEY_GEN";
		}
		else {
			throw new KmsException(KMSCode.KR_MECHANISM_INVALID);
		}

		mech = MechMgmt.getInstance().getMechanism(redefineMechanism);

		
		String keyLabel = null;
		List<Long> listPTokenLoc = KeyManager.getInstance().findTokenIndexforTokenLabel(tokenLabel);
		if(listPTokenLoc != null) {
			KeyAttributes attrs = KeyAttrMgmt.getInstance().parseUsageParams(keyAttr);
			for(int idx = 0; idx < listPTokenLoc.size(); idx++) {
				Long ptokenLoc = listPTokenLoc.get(idx);
				logger.debug("KmsCrypto::generateSecretKey: ptokenLoc : [{}]", ptokenLoc);

				CryptoManager provider = CryptoManager.getInstance();
				TokenSession tokenSession = provider.getTokenSession(ptokenLoc);
				
//				cryptoResponse = mech.importKey(ptokenLoc, attrs,tokenSession);
//				cryptoResponse = mech.importKey(ptokenLoc, attrs, tokenSession, mechanism);
				cryptoResponse = mech.importKey(ptokenLoc, attrs, tokenSession, redefineMechanism);
				
				tokenSession.CloseSession();
//				cryptoResponse = mech.importKey(ptokenLoc, attrs);
				keyLabel = cryptoResponse.keyLabel;
			}
			// add to KeyMap
			KeyInfo kinfo = new KeyInfo(keyLabel, "PUBLIC", "SECRET", keySizeBits, tokenLabel);
			kmap.add(kinfo);
		}
		else
			throw new KmsException(KMSCode.KR_TOKEN_NOT_FOUND);
			
		logger.debug("KmsCrypto::generateSecretKey: ret keyLabel [{}]", keyLabel);
		return keyLabel;
	}
	
	// generate public key
	public String generatePublicKey(String tokenLabel, String[] keyAttr, int keySizeBits) throws KmsException, Exception {
		logger.debug("KmsCrypto::generatePublicKey: Start..... tokenLabel [{}]", tokenLabel);

		CryptoResponse cryptoResponse = new CryptoResponse();
		
		String keyLabel = null;
		List<Long> listPTokenLoc = KeyManager.getInstance().findTokenIndexforTokenLabel(tokenLabel);
		if(listPTokenLoc != null) {
			KeyAttributes attrs = KeyAttrMgmt.getInstance().parseUsageParams(keyAttr);
			for(int idx = 0; idx < listPTokenLoc.size(); idx++) {
				Long ptokenLoc = listPTokenLoc.get(idx);
				logger.debug("KmsCrypto::generatePublicKey: ptokenLoc : [{}]", ptokenLoc);

				CryptoManager provider = CryptoManager.getInstance();
				TokenSession tokenSession = provider.getTokenSession(ptokenLoc);

//				cryptoResponse = MechRSA.getInstance().importPubKey(ptokenLoc, attrs);
				cryptoResponse = MechGeneric.getInstance().importPubKey(ptokenLoc, attrs,tokenSession);

				tokenSession.CloseSession();
				
				keyLabel = cryptoResponse.keyLabel;
			}
			// add to KeyMap
			KeyInfo kinfo = new KeyInfo(keyLabel, "PUBLIC", "RSA", keySizeBits, tokenLabel);
			kmap.add(kinfo);
		}
		else
			throw new KmsException(KMSCode.KR_TOKEN_NOT_FOUND);
		
		logger.debug("KmsCrypto::generatePublicKey: ret keyLabel [{}]", keyLabel);
		return keyLabel;
	}
	
	// generate private key
	public String generatePrivateKey(String tokenLabel, String[] keyAttr, int keySizeBits) throws KmsException, Exception {
		logger.debug("KmsCrypto::generatePrivateKey: Start..... tokenLabel [{}]", tokenLabel);
		
		CryptoResponse cryptoResponse = new CryptoResponse();

		String keyLabel = null;
		List<Long> listPTokenLoc = KeyManager.getInstance().findTokenIndexforTokenLabel(tokenLabel);
		if(listPTokenLoc != null) {
			KeyAttributes attrs = KeyAttrMgmt.getInstance().parseUsageParams(keyAttr);
			for(int idx = 0; idx < listPTokenLoc.size(); idx++) {
				Long ptokenLoc = listPTokenLoc.get(idx);
				logger.debug("KmsCrypto::generatePrivateKey: ptokenLoc : [{}]", ptokenLoc);
//				cryptoResponse = MechRSA.getInstance().importPriKey(ptokenLoc, attrs);
				CryptoManager provider = CryptoManager.getInstance();
				TokenSession tokenSession = provider.getTokenSession(ptokenLoc);

				cryptoResponse = MechGeneric.getInstance().importPriKey(ptokenLoc, attrs,tokenSession);

				tokenSession.CloseSession();

				keyLabel = cryptoResponse.keyLabel;
			}
			// add to KeyMap
			KeyInfo kinfo = new KeyInfo(keyLabel, "PRIVATE", "RSA", keySizeBits, tokenLabel);
			kmap.add(kinfo);
		}
		else
			throw new KmsException(KMSCode.KR_TOKEN_NOT_FOUND);
		
		logger.debug("KmsCrypto::generatePrivateKey: ret keyLabel [{}]", keyLabel);
		return keyLabel;
	}
	
	// generate 3 components secret key 
	public String[] generateKeyComponents(String mechanism, int keySizeBits, String tokenLabel, String[] keyAttr, String[] components) throws KmsException, Exception {
		logger.debug("KmsCrypto::generateKeyComponents: Start.....");
		logger.debug("KmsCrypto::generateKeyComponents: mechanism:keySizeBits : [{}:{}]", mechanism, keySizeBits);

		CryptoResponse cryptoResponse = new CryptoResponse();
		String[] ret = new String[4];
		MechPkcs11 mech=null;
		String redefineMechanism = "";

		for(String attr : keyAttr){
			logger.debug("keyAttr: " + attr);
		}

		if(mechanism.equalsIgnoreCase("DES") == true && keySizeBits == 64) {
			redefineMechanism = "DES_KEY_GEN";
		}
		else if(mechanism.equalsIgnoreCase("DES") == true && keySizeBits == 128) {
			redefineMechanism = "DES2_KEY_GEN";
		}
		else if(mechanism.equalsIgnoreCase("DES") == true && keySizeBits == 192) {
			redefineMechanism = "DES3_KEY_GEN";
			
			if(components[0].length() == 32)
				components[0] = components[0] + components[0].substring(0,16);
			if(components[1].length() == 32)
				components[1] = components[1] + components[1].substring(0,16);
			if(components[2].length() == 32)
				components[2] = components[2] + components[2].substring(0,16);
		}
		else if (mechanism.equalsIgnoreCase("AES") == true) {
			redefineMechanism = "AES_KEY_GEN";
		}
		else if (mechanism.equalsIgnoreCase("SEED") == true) {
			redefineMechanism = "SEED_KEY_GEN";
		}
		else {
			throw new KmsException(KMSCode.KR_MECHANISM_INVALID);
		}

		mech = MechMgmt.getInstance().getMechanism(redefineMechanism);

		
		
		String keyValue = getKeyValue(components);
		logger.debug("KmsCrypto::generateKeyComponents: keyValue   : [{}]", keyValue);

		String[] cpKeyAttr = new String[keyAttr.length + 1];
		System.arraycopy(keyAttr, 0, cpKeyAttr, 0, keyAttr.length);


		cpKeyAttr[keyAttr.length] = String.format("VALUE=%s", keyValue);

		
		
		KeyAttributes attrs = KeyAttrMgmt.getInstance().parseUsageParams(cpKeyAttr);
		
		List<Long> listPTokenLoc = KeyManager.getInstance().findTokenIndexforTokenLabel(tokenLabel);
		logger.debug("KeyManager.getInstance().findTokenIndexforTokenLabel({})",tokenLabel);
		if(listPTokenLoc != null) {
			String generatedKeyLabel = null;
			for(int idx = 0; idx < listPTokenLoc.size(); idx++) {
				Long ptokenLoc = listPTokenLoc.get(idx);
				
				logger.debug("KmsCrypto::generateKeyComponents: ptokenLoc : [{}]", ptokenLoc);

				CryptoManager provider = CryptoManager.getInstance();
				TokenSession tokenSession = provider.getTokenSession(ptokenLoc);

//				cryptoResponse= mech.importKey(ptokenLoc, attrs);
//				cryptoResponse= mech.importKey(ptokenLoc, attrs,tokenSession,mechanism);
				cryptoResponse= mech.importKey(ptokenLoc, attrs, tokenSession, redefineMechanism);
				tokenSession.CloseSession();
				
//				generatedKeyLabel =  cryptoResponse.generatedKeyLabel;
				generatedKeyLabel =  cryptoResponse.keyLabel;
				if (generatedKeyLabel != null) {
					logger.debug("KmsCrypto::generateKeyComponents: keyLabel : [{}]", generatedKeyLabel);
				}
				else {
					logger.debug("KmsCrypto::generateKeyComponents: keyLabel is null");
				}
			}
			BcCryptoUtil cryptoFuncs = BcCryptoUtil.getInstance();
				
			ret[0] = cryptoFuncs.cptKeyCheckValue(mechanism, keySizeBits, components[0]).substring(0, 6);
			ret[1] = cryptoFuncs.cptKeyCheckValue(mechanism, keySizeBits, components[1]).substring(0, 6);
			ret[2] = cryptoFuncs.cptKeyCheckValue(mechanism, keySizeBits, components[2]).substring(0, 6);
			ret[3] = cryptoFuncs.cptKeyCheckValue(mechanism, keySizeBits, keyValue).substring(0, 6);
		
			// add to KeyMap
			KeyInfo kinfo = new KeyInfo(generatedKeyLabel, "SECRET", mechanism, keySizeBits, tokenLabel);
			kmap.add(kinfo);
		}
		else
			throw new KmsException(KMSCode.KR_TOKEN_NOT_FOUND);
		
		logger.debug("KmsCrypto::generateKeyComponents: keyValue KCV : [{}]", ret[3]);
		
		return ret;
	}
	
	// generate key pair
	public String[] generateKeyPair(String tokenLabel, String[] sPubAttr, String[] sPriAttr, int keySizeBits) throws KmsException, Exception {
		logger.debug("KmsCrypto::generateKeyPair: Start...........");
		
		CryptoResponse cryptoResponse = new CryptoResponse();
		String[] ret = null;
		List<Long> listPTokenLoc = KeyManager.getInstance().findTokenIndexforTokenLabel(tokenLabel);
		if(listPTokenLoc != null) {
			KeyAttributes pubAttrs = KeyAttrMgmt.getInstance().parseUsageParams(sPubAttr);
			KeyAttributes priAttrs = KeyAttrMgmt.getInstance().parseUsageParams(sPriAttr);
			for(int idx = 0; idx < listPTokenLoc.size(); idx++) {
				Long ptokenLoc = listPTokenLoc.get(idx);

				CryptoManager provider = CryptoManager.getInstance();
				TokenSession tokenSession = provider.getTokenSession(ptokenLoc);

				logger.debug("KmsCrypto::generateKeyPair: ptokenLoc : [{}]", ptokenLoc);
//				cryptoResponse = MechRSA.getInstance().generateKeyPair(ptokenLoc, pubAttrs, priAttrs);
				cryptoResponse = MechGeneric.getInstance().generateKeyPair(ptokenLoc, pubAttrs, priAttrs,tokenSession);
				
				tokenSession.CloseSession();

				ret = cryptoResponse.keyLabelPair;
			}
			
			// add to KeyMap
			KeyInfo kinfoPub = new KeyInfo(ret[0], "PUBLIC", "RSA", keySizeBits, tokenLabel);
			kmap.add(kinfoPub);
			KeyInfo kinfoPri = new KeyInfo(ret[1], "PRIVATE", "RSA", keySizeBits, tokenLabel);
			kmap.add(kinfoPri);
		}	
		else
			throw new KmsException(KMSCode.KR_TOKEN_NOT_FOUND);
		
		logger.debug("KmsCrypto::generateKeyPair: ret pub:pri [{}:{}]", ret[0], ret[1]);
		return ret;
	}
	
	public String[] getAttributeValue(String keyLabel) throws KmsException, Exception {
		logger.debug("KmsCrypto::getAttributeValue: Start.....");
		
		CryptoResponse cryptoResponse = new CryptoResponse();
		String[] keyAttr = null;
		long ptokenLoc = KeyManager.getInstance().getTokenIndexforKeyLabel(keyLabel);
		if (ptokenLoc > 0){
			logger.debug("KmsCrypto::getAttributeValue: ptokenLoc : [{}]", ptokenLoc);

			CryptoManager provider = CryptoManager.getInstance();
			TokenSession tokenSession = provider.getTokenSession(ptokenLoc);

			cryptoResponse= MechGeneric.getInstance().getAttributeValue(ptokenLoc, keyLabel,tokenSession);
			tokenSession.CloseSession();
			
			keyAttr = cryptoResponse.attrs;
		}	
		else
			throw new KmsException(KMSCode.KR_KEY_NOT_FOUND);
		
		return keyAttr;	
	}

	public String[] getAttributeCertValue(String tokenLabel, String keyLabel) throws KmsException, Exception {
		logger.debug("KmsCrypto::getAttributeCertValue: Start..... tokenLabel[{}]", tokenLabel);
		
		String[] keyAttr = null;
		String[] retAttr = new String[5];
		String modulus = null;
		int modulusLen = 0;
		int exponentLen = 0;

		keyAttr = this.getKeyAttributes(tokenLabel, keyLabel);

		for (String attr: keyAttr) {
			String[] type = attr.split("=");
			logger.debug("KmsCrypto::getAttributeCertValue: type:len [{}:{}]", type[0], type[1].trim().length());
			if(type[0].trim().equalsIgnoreCase("MODULUS")) {
				retAttr[0] = attr;
				modulus = type[1].trim();
				// if modulus_bit == 1024, modulus_len == 256
				modulusLen = type[1].trim().length();
				retAttr[1] = "MODULUS_LEN = " + modulusLen;
			}
			else if(type[0].trim().equalsIgnoreCase("PUBLIC_EXPONENT")) {
				retAttr[2] = attr;
				exponentLen = type[1].trim().length();
				retAttr[3] = "EXPONENT_LEN = " + exponentLen;
			}
		}
		
		// get LEFTMOST
		//String strPkLeftmost = Util.byteArray2hexString((byte[]) templateAttr[0].pValue).substring(0, (nPkLen-(39+nPkExpLen))*2); 
		String leftMost = modulus.substring(0, (modulusLen - ((39*2) + exponentLen) ) );
		retAttr[4] = "LEFTMOST = " + leftMost;
		
		logger.debug("KmsCrypto::getAttributeCertValue: end...........");
		return retAttr;	
	}	
	
	public int destroyKey(String keyLabel) throws KmsException, Exception {
		logger.debug("KmsCrypto::destroyKey: Start.....");
		
		int ret = -1 ;
		List<Long> listPTokenLoc = KeyManager.getInstance().findTokenIndexforKeyLabel(keyLabel);	
		if(listPTokenLoc != null) {
			for(int idx = 0; idx < listPTokenLoc.size(); idx++) {
				Long ptokenLoc = listPTokenLoc.get(idx);	
				logger.debug("KmsCrypto::destroyKey: ptokenLoc : [{}]", ptokenLoc);

				CryptoManager provider = CryptoManager.getInstance();;
				TokenSession tokenSession = provider.getTokenSession(ptokenLoc);

//				MechGeneric.getInstance().destroyKey(ptokenLoc, keyLabel);
				MechGeneric.getInstance().destroyKey(ptokenLoc, keyLabel,tokenSession);
				tokenSession.CloseSession();
				
			}
			kmap.delete(keyLabel);
			ret = 0;
		} 
		else
			throw new KmsException(KMSCode.KR_KEY_NOT_FOUND);

		return ret;
	}
	
	public String wrapKey(String mechanism, String[] mechanismParameters, String wrappingKeyLabel, String keyLabel) throws KmsException, Exception{
		logger.debug("KmsCrypto::wrapKey: Start........... ");
		logger.debug("KmsCrypto::wrapKey: keyLabel->wrappingKeyLabel : [{}->{}]", keyLabel, wrappingKeyLabel);
	
		MechPkcs11 mech=null;
		CryptoResponse cryptoResponse = new CryptoResponse();

		mech = MechMgmt.getInstance().getMechanism(mechanism);

		long ptokenLoc = KeyManager.getInstance().getTokenIndexforKeyLabel(keyLabel);
		if (ptokenLoc < 0)
			throw new KmsException(KMSCode.KR_KEY_NOT_FOUND);
			
		logger.debug("KmsCrypto::wrapKey: ptokenLoc : [{}]", ptokenLoc);

		CryptoManager provider = CryptoManager.getInstance();;
		TokenSession tokenSession = provider.getTokenSession(ptokenLoc);

		cryptoResponse= mech.wrapKey(ptokenLoc, mechanism, mechanismParameters, wrappingKeyLabel, keyLabel,tokenSession);
		
		tokenSession.CloseSession();
		
		byte[] wrappedKey = cryptoResponse.keyWrapped;
		logger.debug("KmsCrypto::wrapKey: wrappedKey [{}]", Util.byteArray2hexString(wrappedKey));

		return Util.byteArray2hexString(wrappedKey);
	}
	
	public String unwrapKey(String mechanism, String[] mechanismParameters, String unWrappingKeyLabel, String wrappedKey, String[] keyAttr) throws KmsException, Exception{
		logger.debug("KmsCrypto::unwrapKey: Start.....");

		MechPkcs11 mech=null;
		CryptoResponse cryptoResponse = new CryptoResponse();

		mech = MechMgmt.getInstance().getMechanism(mechanism);
		
		KeyAttributes attrs = KeyAttrMgmt.getInstance().parseUsageParams(keyAttr);
		
		long ptokenLoc = KeyManager.getInstance().getTokenIndexforKeyLabel(unWrappingKeyLabel);
		if (ptokenLoc < 0)
			throw new KmsException(KMSCode.KR_KEY_NOT_FOUND);
		
		logger.debug("KmsCrypto::unwrapKey: ptokenLoc : [{}]", ptokenLoc);

		CryptoManager provider = CryptoManager.getInstance();;
		TokenSession tokenSession = provider.getTokenSession(ptokenLoc);
		
		cryptoResponse = mech.unwrapKey(ptokenLoc, mechanism, mechanismParameters, unWrappingKeyLabel, Util.hexString2byteArray(wrappedKey), attrs,tokenSession);
		tokenSession.CloseSession();
		
		String unwrappedKey = cryptoResponse.generatedKeyLabel;
		/*
		KeyInfo kinfo = new KeyInfo(unwrappedKey, "SECRET", mechanism, keySizeBits, tokenLabel);
		KeyMap.getInstance().add(kinfo);
		*/
		
		logger.debug("KmsCrypto::unwrapKey: unwrapKey [{}]", unwrappedKey);
		return unwrappedKey;
	}
	
	public String encrypt(String mechanism, String[] mechanismParameters, String keyLabel, String data) throws KmsException {	
		logger.debug("KmsCrypto::encrypt: start..... data.length [{}]", data.length());
		
		CryptoResponse cryptoResponse = new CryptoResponse();
		if (mechanism.equalsIgnoreCase("RSA_X_509") == false) {
			throw new KmsException(KMSCode.KR_MECHANISM_INVALID);
		}
		
		long ptokenLoc = KeyManager.getInstance().getTokenIndexforKeyLabel(keyLabel);
		if (ptokenLoc < 0)
			throw new KmsException(KMSCode.KR_KEY_NOT_FOUND);
		
		byte[] bData = Util.hexCommand2byteArray(data);
		
		cryptoResponse = MechRSA.getInstance().encrypt(ptokenLoc, mechanism, mechanismParameters, keyLabel, bData);
		 byte[] bEncrypted = cryptoResponse.dataEncrypted;
		 
		logger.debug("KmsCrypto::encrypt: encrypt ret [{}]", Util.byteArray2hexString(bEncrypted));
		return Util.byteArray2hexString(bEncrypted);
	}
	
	public String digest(String tokenLabel, String mechanism, String[] mechanismParameters, String data) throws KmsException, Exception {
		logger.debug("KmsCrypto::digest: start..... tokenLabel [{}]", tokenLabel);
		CryptoResponse cryptoResponse = new CryptoResponse();
		long ptokenLoc = KeyManager.getInstance().getTokenIndexforTokenLabel(tokenLabel);
		if (ptokenLoc < 0)
			throw new KmsException(KMSCode.KR_KEY_NOT_FOUND);
		
		byte[] bData = Util.hexCommand2byteArray(data);

		CryptoManager provider = CryptoManager.getInstance();;
		TokenSession tokenSession = provider.getTokenSession(ptokenLoc);

		cryptoResponse = MechGeneric.getInstance().digest(ptokenLoc, mechanism, mechanismParameters, bData,tokenSession);
		tokenSession.CloseSession();
		byte[] bDigest = cryptoResponse.dataDigest;
		logger.debug("KmsCrypto::digest: digest ret [{}]", Util.byteArray2hexString(bDigest));
		return Util.byteArray2hexString(bDigest);
	}
	
	public int byteXfer(String type, byte[] inBytes) {
		int ret = -1;
		try {
//			ret = remoteService.dataXfer(type, inBytes);
			System.out.format("KmsCrypto::byteXfer: dataXfer ret : %d\n", ret);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ret;
	}

	public String getKCV(String mechanism, int keySizeBits, String text) throws KmsException{
		String value = BcCryptoUtil.getInstance().cptKeyCheckValue(mechanism, keySizeBits, text);
		if(value == null) throw new KmsException(null, "Value is null");
		
		return value.substring(0, 6);
	}
	
	public String getKeyValue(String[] components) throws KmsException{
		byte[] bComp1 = Util.hexStringToByteArray(components[0]);
		byte[] bComp2 = Util.hexStringToByteArray(components[1]);
		byte[] bComp3 = Util.hexStringToByteArray(components[2]);
		byte[] bCompTemp1 = Util.xor(bComp1, bComp2);
		byte[] bCompTemp2 = Util.xor(bCompTemp1, bComp3);

		return  Util.byteArray2hexString(bCompTemp2);
	}
	
}
