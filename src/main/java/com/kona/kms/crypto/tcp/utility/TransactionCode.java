package com.kona.kms.crypto.tcp.utility;

public enum TransactionCode {

	KMS_ENCRYPT("1000"),
	KMS_DECRYPT("1010"),
	KMS_DIGEST("1020"),
	KMS_SIGN("1030"),
	KMS_VERIFY("1040"),
	KMS_WRAP("1050"),
	KMS_UNWRAP("1060"),
	KMS_DERIVE("1070"),
	KMS_DESTROY("1080"),
	KMS_GET_KEY_ATTR("1090"),
	KMS_GEN_KEY("1100"),
	KMS_GEN_KEY_PAIR("1110"),
	KMS_IMPORT_SECRET_KEY("1120"),
	KMS_IMPORT_PUB_KEY("1130"),
	KMS_IMPORT_PRI_KEY("1140"),
	KMS_GEN_RAN("1150"),
	KMS_GET_SYS_INFO("8000");
	
	private String TR_CODE_VALE;
	
	/**
	 * 
	 * @param trCode
	 */
	private TransactionCode(String trCode){
		this.TR_CODE_VALE= trCode;
	}
	
	
	/**
	 * 
	 * @return
	 */
	public String getValue(){
		return this.TR_CODE_VALE;
	}
	
	public static TransactionCode getEnum(String trCode){
		
		if(trCode == null || trCode.isEmpty() == true){
			return null;
		}
		
		
		if(KMS_ENCRYPT.getValue().compareTo(trCode) ==0){
			return KMS_ENCRYPT;
		}
		
		if(KMS_DECRYPT.getValue().compareTo(trCode) ==0){
			return KMS_DECRYPT;
		}
			
		if(KMS_DIGEST.getValue().compareTo(trCode) ==0){
			return KMS_DIGEST;
		}
		
		if(KMS_SIGN.getValue().compareTo(trCode) ==0){
			return KMS_SIGN;
		}
		
		if(KMS_VERIFY.getValue().compareTo(trCode) ==0){
			return KMS_VERIFY;
		}
			
		if(KMS_WRAP.getValue().compareTo(trCode) ==0){
			return KMS_WRAP;
		}
		
		if(KMS_UNWRAP.getValue().compareTo(trCode) ==0){
			return KMS_UNWRAP;
		}
		
		if(KMS_DERIVE.getValue().compareTo(trCode) ==0){
			return KMS_DERIVE;
		}
		
		if(KMS_DESTROY.getValue().compareTo(trCode) ==0){
			return KMS_DESTROY;
		}
		
		if(KMS_GET_KEY_ATTR.getValue().compareTo(trCode) ==0){
			return KMS_GET_KEY_ATTR;
		}
		
		if(KMS_GEN_KEY.getValue().compareTo(trCode) ==0){
			return KMS_GEN_KEY;
		}
		
		if(KMS_GEN_KEY_PAIR.getValue().compareTo(trCode) ==0){
			return KMS_GEN_KEY_PAIR;
		}
		
		if(KMS_IMPORT_SECRET_KEY.getValue().compareTo(trCode) ==0){
			return KMS_IMPORT_SECRET_KEY;
		}
		
		if(KMS_IMPORT_PUB_KEY.getValue().compareTo(trCode) ==0){
			return KMS_IMPORT_PUB_KEY;
		}
		
		if(KMS_IMPORT_PRI_KEY.getValue().compareTo(trCode) ==0){
			return KMS_IMPORT_PRI_KEY;
		}
		
		if(KMS_GEN_RAN.getValue().compareTo(trCode) ==0){
			return KMS_GEN_RAN;
		}
		
		if(KMS_GET_SYS_INFO.getValue().compareTo(trCode) ==0){
			return KMS_GET_SYS_INFO;
		}
		
		
		
		return null;
	}
}
