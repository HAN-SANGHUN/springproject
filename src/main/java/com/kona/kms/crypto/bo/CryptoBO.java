package com.kona.kms.crypto.bo;

import com.kona.kms.KmsException;


public interface CryptoBO {
	
	//1. secret key generation - ret generated-key label
	CryptoResponse generateKey(CryptoRequest request) throws KmsException, Exception;
	//2. public/private key pair generation - ret generated-key label pair
	CryptoResponse generateKeyPair(CryptoRequest request) throws KmsException, Exception;
	//3. public/private key pair generation - ret generated-key label pair
	CryptoResponse importSecretKey(CryptoRequest request) throws KmsException, Exception;
	CryptoResponse importPublicKey(CryptoRequest request) throws KmsException, Exception;
	CryptoResponse importPrivateKey(CryptoRequest request) throws KmsException, Exception;
	//4. encrypt - ret encrypted data
	CryptoResponse encrypt(CryptoRequest request) throws KmsException, Exception;
	//5. decrypt - ret decrypted data
	CryptoResponse decrypt(CryptoRequest request) throws KmsException, Exception;
	//6. digest - ret digest data
	CryptoResponse digest(CryptoRequest request) throws KmsException, Exception;
	//7. sign - ret signature for the data
	CryptoResponse sign(CryptoRequest request) throws KmsException, Exception;
	//8. verify - verification success:true, fail:false
	CryptoResponse verify(CryptoRequest request) throws KmsException, Exception;
	//9. wrap a private or secret key
	CryptoResponse wrapKey(CryptoRequest request) throws KmsException, Exception;
	//10. unwrap a wrapped key
	CryptoResponse unwrapKey(CryptoRequest request) throws KmsException, Exception;
	//11. derive a key
	CryptoResponse deriveKey(CryptoRequest request) throws KmsException, Exception;
	//12.
	CryptoResponse getAttributeValue(CryptoRequest request) throws KmsException, Exception;
	//13.
	CryptoResponse destroyKey(CryptoRequest request) throws KmsException, Exception;
	
	CryptoResponse generateRandom(CryptoRequest request) throws KmsException, Exception;
	
	CryptoResponse encrypt_v2(CryptoRequest request) throws KmsException;

	//test Code . encrypt - ret encrypted data
//	CryptoResponse SessionKey_encrypt(CryptoRequest request) throws KmsException;

}
