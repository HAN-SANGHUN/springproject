package com.kona.kms.crypto;


import sun.security.pkcs11.wrapper.*;


@SuppressWarnings("restriction")

public class KeyAttributes {

	/* common */
	public long 		cka_keyClass 	= 0;				// CKO_SECRET_KEY / CKO_PUBLIC_KEY / CKO_PRIVATE_KEY
	public long 		cka_keyType 	= 0;				// CKK_DES3 / CKK_RSA
	
	public boolean 	cka_token 			= true;			// token or session ?
	public boolean 	cka_private 		= true;			// private access or not?
	public boolean 	cka_exportable 	= false;		// exportable
	public boolean 	cka_importable 	= true;		// importable
	public boolean 	cka_sensitive 		= true;
	public boolean 	cka_extractable 	= true;
	public boolean 	cka_encrypt 		= true;
	public boolean 	cka_decrypt 		= true;
	public boolean 	cka_decryptEncrypt 	= false;
	public boolean 	cka_unwrapWrap 	= false;
	public boolean 	cka_wrap 				= true;
	public boolean 	cka_unwrap 			= true;
	public boolean 	cka_sign 				= true;
	public boolean 	cka_verify 				= true;
	public boolean 	cka_signRecover 		= false;
	public boolean 	cka_verifyRecover 	= false;
	public boolean 	cka_derive 				= true;
	public byte[] 	  	cka_label = null;
	public byte[] 	  	cka_seed = null;
	/* RSA public key specific */
	public byte[] 		cka_modulus 			= null;				// Modulus n	(BigInteger)
	public long 		cka_modulus_bits 	= 0;				// Length in bits of modulus n
	public byte[] 		cka_public_exponent = null;		// Public exponent e	(BigInteger)
	/* RSA private key specific */
	//public byte[] cka_modulus;					// Modulus n	(BigInteger)
	//public byte[] cka_public_exponent;			// Public exponent e	(BigInteger)
	public byte[] 		cka_private_exponent = null;		// Private exponent e	(BigInteger)
	public byte[] 		cka_prime_1 = null;				// Prime p	(BigInteger)
	public byte[] 		cka_prime_2 = null;				// Prime q	(BigInteger)
	public byte[] 		cka_exponent_1 	= null;			// Private exponent d modulo p-1	(BigInteger)
	public byte[] 		cka_exponent_2 	= null;			// Private exponent d modulo q-1	(BigInteger)
	public byte[] 		cka_coefficient 	= null;			// CRT coefficient q-1 mod p	(BigInteger)
	public byte[] 		cka_value 		= null;
	public long 		cka_value_len 	= 16;					// Length in bytes of key value
	public byte[] 		cka_subject 	= null;
	public byte[] 		cka_id 			= null;					// profile id
	public byte[] 		cka_startDate = "20010101".getBytes();
	public byte[] 		cka_endDate = "20991231".getBytes();
	

	public KeyAttributes() {}
	
	public KeyAttributes(String[] keyAttrs) {}
	
	public KeyAttributes(String type) {
		if (type.equalsIgnoreCase("PUBLIC") == true) {
			cka_keyClass 	= 0;	// PKCS11Constants.CKO_PUBLIC_KEY;
			cka_keyType 	= 0;	// PKCS11Constants.CKK_RSA;
			cka_private 	= false;
			cka_extractable 	= true;
			cka_sensitive 		= true;
			cka_encrypt 		= true;
			cka_decrypt 		= false;
			cka_wrap 			= true;
			cka_unwrap 		= false;
			cka_sign 			= false;
			cka_verify 			= true;
			cka_derive 			= true;
		}
		else if (type.equalsIgnoreCase("PRIVATE") == true) {
			cka_keyClass 	= 0;	// PKCS11Constants.CKO_PRIVATE_KEY;
			cka_keyType 	= 0;	// PKCS11Constants.CKK_RSA;
			cka_private 	= true;
			cka_extractable = true;
			cka_sensitive 	= true;
			cka_encrypt 	= false;
			cka_decrypt 	= true;
			cka_wrap 		= false;
			cka_unwrap 	= true;
			cka_sign	 		= true;
			cka_verify 		= false;
			cka_derive 		= true;
		}
		else {
			cka_keyClass 	= 	0;	// PKCS11Constants.CKO_SECRET_KEY;
			cka_keyType 	= 0;	// PKCS11Constants.CKK_DES3;
		}
		/* else : SECRET KEY */
	}
	
}
