package com.kona.kms.crypto.mech;

import com.kona.kms.KmsException;
import com.kona.kms.KMSCode;
import com.kona.kms.crypto.KeyAttributes;
import com.kona.kms.crypto.TokenSession;
import com.kona.kms.crypto.bo.CryptoResponse;

abstract public class MechPkcs11 {

	public  CryptoResponse generateKey(long slotIx, KeyAttributes keyAttr, TokenSession tokenSession, String mechanism) throws KmsException, Exception {
		throw new KmsException(KMSCode.KR_ERROR, "KMS Error!, Function Not Implemented!");
	}
	public CryptoResponse importKey(long slotIx, KeyAttributes keyAttr,TokenSession tokenSession, String mechanism) throws KmsException, Exception {
		throw new KmsException(KMSCode.KR_ERROR, "KMS Error!, Function Not Implemented!");
	}
	public CryptoResponse encrypt(long slotIx, String mechanism, String[] mechanismParameters, String keyLabel, byte[] data, TokenSession tokenSession) throws KmsException {
		throw new KmsException(KMSCode.KR_ERROR, "KMS Error!, Function Not Implemented!");
	}
	public CryptoResponse decrypt(long slotIx, String mechanism, String[] mechanismParameters, String keyLabel, byte[] edata,TokenSession tokenSession) throws KmsException {
		throw new KmsException(KMSCode.KR_ERROR, "KMS Error!, Function Not Implemented!");
	}
	public CryptoResponse  wrapKey(long slotIx, String mechanism, String[] mechanismParameters, String wrappingKeyLabel, String keyLabel ,TokenSession tokenSession) throws KmsException, Exception {
		throw new KmsException(KMSCode.KR_ERROR, "KMS Error!, Function Not Implemented!");
	}
	public CryptoResponse unwrapKey(long slotIx, String mechanism, String[] mechanismParameters, String unWrappingKeyLabel, byte[] wrappedKey, KeyAttributes keyAttr, TokenSession tokenSession) throws KmsException, Exception {
		throw new KmsException(KMSCode.KR_ERROR, "KMS Error!, Function Not Implemented!");
	}
	public CryptoResponse deriveKey(long slotIx, String mechanism, String[] mechanismParameters, String baseKeyLabel, KeyAttributes keyAttr,TokenSession tokenSession) throws KmsException, Exception {
		throw new KmsException(KMSCode.KR_ERROR, "KMS Error!, Function Not Implemented!");
	}
	public CryptoResponse digest(long slotIx, String mechanism, String[] mechanismParameters, byte[] data,TokenSession tokenSession) throws KmsException, Exception {
		throw new KmsException(KMSCode.KR_ERROR, "KMS Error!, Function Not Implemented!");
	}
	public CryptoResponse generateKeyPair(long slotIx, KeyAttributes publicKeyAttr, KeyAttributes PrivateKeyAttr,TokenSession tokenSession) throws KmsException, Exception {
		throw new KmsException(KMSCode.KR_ERROR, "KMS Error!, Function Not Implemented!");
	}
	public CryptoResponse importKeyPair(long slotIx, KeyAttributes publicKeyAttr, KeyAttributes PrivateKeyAttr,TokenSession tokenSession) throws KmsException {
		throw new KmsException(KMSCode.KR_ERROR, "KMS Error!, Function Not Implemented!");
	}
	public CryptoResponse importPubKey(long slotIx, KeyAttributes keyAttr,TokenSession tokenSession) throws KmsException, Exception {
		throw new KmsException(KMSCode.KR_ERROR, "KMS Error!, Function Not Implemented!");
	}
	public CryptoResponse importPriKey(long slotIx, KeyAttributes keyAttr,TokenSession tokenSession) throws KmsException, Exception {
		throw new KmsException(KMSCode.KR_ERROR, "KMS Error!, Function Not Implemented!");
	}
	public byte[] sign(long slotIx, String mechanism, String[] mechanismParameters, String keyLabel, byte[] data,TokenSession tokenSession) throws KmsException, Exception {
		throw new KmsException(KMSCode.KR_ERROR, "KMS Error!, Function Not Implemented!");
	}
	public boolean verify(long slotIx, String mechanism, String[] mechanismParameters, String keyLabel, byte[] data, byte[] signature,TokenSession tokenSession) throws KmsException, Exception {
		throw new KmsException(KMSCode.KR_ERROR, "KMS Error!, Function Not Implemented!");
	}

//test 함수
	public CryptoResponse SEncrypt(long slotIx, String mechanism, String[] mechanismParameters, String keyLabel, byte[] data, TokenSession tokenSession,long hKey) throws KmsException, Exception {
		throw new KmsException(KMSCode.KR_ERROR, "KMS Error!, Function Not Implemented!");
	}

	public CryptoResponse SDecrypt(long slotIx, String mechanism, String[] mechanismParameters, String keyLabel, byte[] data, TokenSession tokenSession,long hKey) throws KmsException, Exception {
		throw new KmsException(KMSCode.KR_ERROR, "KMS Error!, Function Not Implemented!");
	}
	
	public void destroySessionKey(long hKey, TokenSession tokenSession) throws KmsException, Exception {
		throw new KmsException(KMSCode.KR_ERROR, "KMS Error!, Function Not Implemented!");
	}
}
