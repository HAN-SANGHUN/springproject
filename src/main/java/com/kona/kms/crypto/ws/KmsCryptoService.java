package com.kona.kms.crypto.ws;

import javax.jws.WebService;

import com.kona.kms.KmsException;
import com.kona.kms.crypto.Key;
import com.kona.kms.crypto.KeyAttributes;
import com.kona.kms.token.LogicalToken;


@WebService
public interface KmsCryptoService {
	
	//1. secret key generation - ret generated-key label
	String C_GenerateKey(String tokenLabel, String mechanism, String[] keyAttributes) throws KmsException;
 
	//2. public/private key pair generation - ret generated-key label pair
	String[] C_GenerateKeyPair(String tokenLabel, String mechanism, String[] pubKeyAttrs, String[] priKeyAttrs) throws KmsException;

	//3. 
	String C_ImportSecretKey(String tokenLabel, String mechanism, String[] keyAttrs) throws KmsException;
	String C_ImportPublicKey(String tokenLabel, String mechanism, String[] keyAttrs) throws KmsException;
	String C_ImportPrivateKey(String tokenLabel, String mechanism, String[] keyAttrs) throws KmsException;
 
	//4. encrypt - ret encrypted data
//	byte[] C_Encrypt(String mechanism, String[] mechnismParameters, String keyLabel, byte[] data) throws KmsException;
 
	//4-1. encrypt - Session KEY ret encrypted data
	//shhan append WS_APPEND TEST
	byte[] C_Encrypt(String mechanism, String[] mechnismParameters, String keyLabel, byte[] data, String[] keyAttrs) throws KmsException;

	//5. decrypt - ret decrypted data
//	byte[] C_Decrypt(String mechanism, String[] mechnismParameters, String keyLabel, byte[] edata) throws KmsException;
	byte[] C_Decrypt(String mechanism, String[] mechnismParameters, String keyLabel, byte[] edata,String[] keyAttrs) throws KmsException;
	
	
	//6. digest - ret digest data
	byte[] C_Digest(String mechanism, String[] mechnismParameters, byte[] data) throws KmsException;
 
	//7. sign - ret signature for the data
	byte[] C_Sign(String mechanism, String[] mechnismParameters, String keyLabel, byte[] data) throws KmsException;
 
	//8. verify - verification success:true, fail:false
	boolean C_Verify(String mechanism, String[] mechnismParameters, String keyLabel, byte[] data, byte[] signature) throws KmsException;
 
	//9. wrap a private or secret key
	byte[] C_WrapKey(String mechanism, String[] mechnismParameters, String wrappingKeyLabel, String keyLabel) throws KmsException;
 
	//10. unwrap a wrapped key
	String C_UnwrapKey(String mechanism, String[] mechnismParameters, String unWrappingKeyLabel, byte[] wrappedKey, String[] keyAttrs) throws KmsException;
 
	//11. derive a key
	String C_DeriveKey(String mechanism, String[] mechnismParameters, String baseKeyLabel, String[] keyAttrs) throws KmsException;

	//12.
	String[] C_GetAttributeValue(String keyLabel) throws KmsException;
	
	//13. 
	void C_DestroyKey(String keyLabel) throws KmsException;
	
	//14. get token list
	LogicalToken[] getTokens();
	
	// temporary
	int dataXfer(String type, byte[] data);
		
	////////////////////////////////////////////////////////////////////////////////////
	KeyAttributes GenerateKey(String tokenLabel, String mechanism, KeyAttributes keyAttrs) throws KmsException;
	KeyAttributes[] GenerateKeyPair(String tokenLabel, String mechanism, KeyAttributes pubKeyAttrs, KeyAttributes priKeyAttrs) throws KmsException;
	KeyAttributes ImportSecretKey(String tokenLabel, String mechanism, KeyAttributes keyAttrs) throws KmsException;
	KeyAttributes ImportPublicKey(String tokenLabel, String mechanism, KeyAttributes keyAttrs) throws KmsException;
	KeyAttributes ImportPrivateKey(String tokenLabel, String mechanism, KeyAttributes keyAttrs) throws KmsException;
	KeyAttributes UnwrapKey(String mechanism, String[] mechnismParameters, String unWrappingKeyLabel, byte[] wrappedKey, KeyAttributes keyAttrs) throws KmsException;
	KeyAttributes DeriveKey(String mechanism, String[] mechnismParameters, String baseKeyLabel, KeyAttributes keyAttrs) throws KmsException;
	KeyAttributes GetKeyAttribute(String keyLabel) throws KmsException;

	byte[] generateRandom(int size) throws KmsException;

	
	////////////////////////////////////////////////////////////////////////////////////
	public Key C_GenerateKey3(String tokenLabel, String mechanism, Key key) throws KmsException;
	public Key[] C_GenerateKeyPair3(String tokenLabel, String mechanism, Key pubKey, Key priKey) throws KmsException;
	public Key C_ImportSecretKey3(String tokenLabel, String mechanism, Key key) throws KmsException;
	public Key C_ImportPublicKey3(String tokenLabel, String mechanism, Key key) throws KmsException;
	public Key C_ImportPrivateKey3(String tokenLabel, String mechanism, Key key) throws KmsException;
	public Key C_UnwrapKey3(String mechanism, String[] mechanismParameters, String unWrappingKeyLabel, byte[] wrappedKey, Key key) throws KmsException;
	public Key C_DeriveKey3(String mechanism, String[] mechanismParameters, String baseKeyLabel, Key key) throws KmsException;
	public Key C_GetKeyInfo3(String keyLabel) throws KmsException;
	////////////////////////////////////////////////////////////////////////////////////	
	
	
	byte[] C_Encrypt_v2(String mechanism, String[] mechnismParameters, String keyLabel, byte[] data, String tokenLabel) throws KmsException;

}
