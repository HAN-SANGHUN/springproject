package com.kona.kms.crypto.bo;

import com.kona.kms.framework.ScmsResponse;
import com.kona.kms.crypto.KeyAttributes;

//public class CryptoResponse extends ScmsResponse {
public class CryptoResponse {

	public long 			slotIx = -1;

//shhan append start
	public long 			hKey = 0;
//shhan append end
	
	public int 				retStatus = 0;

	public KeyAttributes keyAttr 			= null;
	public KeyAttributes PubKeyAttr	 	= null;
	public KeyAttributes PriKeyAttr 		= null;

	public String 			mechanism 				= null;
	public String[] 		mechanismParameters = null;
	public String 			keyLabel 					= null;
	public String 			wrappingKeyLabel 		= null;
	public byte[] 			data 							= null;
	
	public byte[] 			dataEncrypted 			= null;
	public byte[] 			dataDecrypted 			= null;
	public byte[] 			keyWrapped 				= null;
	public byte[] 			keyUnwrapped 			= null;
	public byte[] 			signature 					= null;
	public byte[] 			dataDigest 					= null;
	public boolean 		verified 						= false;
	public String[] 		keyLabelPair 				= null;
	public String 			generatedKeyLabel 		= null;
	public String[] 		attrs 							= null;
	public byte[] 			randomData 				= null;

	public CryptoResponse() {
		keyLabelPair 				= new String[2];
	}

	public CryptoResponse(CryptoRequest req) {
		slotIx 						= req.slotIx;
		keyAttr 						= req.keyAttr;
		PubKeyAttr 				= req.PubKeyAttr;
		PriKeyAttr 					= req.PriKeyAttr;
		mechanism 				= req.mechanism;
		mechanismParameters = req.mechanismParameters;
		keyLabel 					= req.keyLabel;
		wrappingKeyLabel 		= req.wrappingKeyLabel;
		data 							= req.data;
		signature 					= req.signature;
	}
			

}
