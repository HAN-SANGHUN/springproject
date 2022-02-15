package com.kona.kms.crypto.bo;

//import com.kona.kms.framework.ScmsRequest;
import com.kona.kms.crypto.KeyAttributes;

//왜 ScmsRequest를 상속받아야 하는 모르겠다.. 그래서 상속을 
//public class CryptoRequest extends ScmsRequest {
public class CryptoRequest {
	
	public long 				slotIx 	= -1;

	public KeyAttributes 	keyAttr 	= null;
	public KeyAttributes 	PubKeyAttr 	= null;
	public KeyAttributes 	PriKeyAttr	 	= null;

	public String 				mechanism		= null;
	public String[] 			mechanismParameters = null;
	public String 				keyLabel	 					= null;
	public String 				wrappingKeyLabel 		= null;
	public byte[] 				data 							= null;
	public byte[] 				signature 					= null;
	public String 				tokenLabel 				= null;
	public Object 				rsvd;
	public int size = 8;

	public CryptoRequest() {
	
	}
}
