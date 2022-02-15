package com.kona.kms;


public class KMSCode {

	//modify by shifei
	public static final String KR_ERROR					= "0101";
	public static final String KR_CRYPTO_ERROR			= "0110";
	public static final String KR_KEY_NOT_FOUND			= "0114";
	public static final String KR_TOKEN_NOT_FOUND		= "0111";
	public static final String KR_CLIENT_INTF_ERROR		= "0201";
	public static final String KR_PARAMETER_INVALID		= "0202";

	public static final String KR_ATTRIBUTE_INVALID		= "0012";
	public static final String KR_MECHANISM_INVALID		= "0070";
	
	public static final String KR_UNKNOWN_ERROR			= "2222";
		 
	
	/*************** KMS User If Error ************************/
	
	public static String USER_CREATE_SUCCESS 			= "UC00";
	public static String USER_CREATE_FAIL 					= "UC01";
	public static String USER_ID_NOT_UNIQUE 				= "UC02";
	
	public static String USER_EDIT_SUCCESS 				= "UE00";
	public static String USER_EDIT_FAIL 						= "UE01";
	
	public static String USER_DELETE_SUCCESS 			= "UD00";
	public static String USER_DELETE_FAIL 					= "UD01";
	
	
	/*************** KMS Key Profile If Error ************************/
	 
	
	public static String KEY_CREATE_SUCCESS 				= "KC00";
	public static String KEY_CREATE_FAIL 						= "KC01";
	public static String KEY_ALEADY_EXISTS 					= "KC02";
	public static String KEY_LABEL_NOT_UNIQUE           = "KC03";
	public static String PRI_KEY_LABEL_NOT_UNIQUE     = "KC04";
	public static String PUB_KEY_LABEL_NOT_UNIQUE    = "KC05";
	public static String KEY_INDEX_NOT_UNIQUE          = "KC06";

	public static String KEY_EDIT_SUCCESS 					= "KE00";
	public static String KEY_EDIT_FAIL 							= "KE01";
	
	public static String KEY_DELETE_SUCCESS 				= "KD00";
	public static String KEY_DELETE_FAIL 						= "KD01";
	
	public static String KEY_VALUE_CREATE_SUCCESS 	= "KV00";
	public static String KEY_VALUE_CREATE_FAIL 			= "KV01";
	
	public static String KEY_KCV_CREATE_SUCCESS       = "KCV00";
	public static String KEY_KCV_CREATE_FAIL            	= "KCV01";
	
	public static String KEY_IMPORT_SUCCESS             			= "KI00";
	public static String KEY_IMPORT_FILE_EMPTY 		    		= "KI01";
	public static String KEY_IMPORT_FILE_NOTSUPPORTED   	= "KI02";
	public static String KEY_IMPORT_CRYPTO_FAIL         		= "KI03";
	public static String KEY_IMPORT_NOT_SECRET          		= "KI04";
	public static String KEY_IMPORT_NOT_PRIVATE         		= "KI05";
	public static String KEY_IMPORT_NOT_PUBLIC          		= "KI06";
	public static String KEY_IMPORT_FAIL                				= "KI07";

	public static String KEY_EXPORT_SUCCESS						= "KE00";
	public static String KEY_EXPORT_FAIL								= "KE01";
	public static String KEY_EXPORT_CRYPTO_FAIL         		= "KE02";
	
	public static String KEY_PROFILE_PARSING_FAIL       			= "KP01";

	/*************** COMPANY If Error ************************/
		
	public static String COMPANY_EDIT_SUCCESS 		= "CE00";
	public static String COMPANY_EDIT_FAIL 				= "CE01";
	public static String COMPANY_ALREADY_EXISTS      = "CE02";
	
	public static String COMPANY_DELETE_SUCCESS 		= "CD00";
	public static String COMPANY_DELETE_FAIL 			= "CD01";
	
	/*************** HSMMETA If Error ************************/
	
	public static String HSM_CREATE_SUCCESS 		= "HC00";
	public static String HSM_CREATE_FAIL 				= "HC01";
	public static String HSM_ALREADY_EXIST 			= "HC02";
	public static String HSM_HEALTH_CHECK_FAIL     = "HC03";
	
	public static String HSM_EDIT_SUCCESS 				= "HE00";
	public static String HSM_EDIT_FAIL 					= "HE01";
	
	public static String HSM_DELETE_SUCCESS 			= "HD00";
	public static String HSM_DELETE_FAIL 				= "HD01";
	
	public static String TOKEN_ACTIVATE_SUCCESS 		= "TA00";
	public static String TOKEN_ACTIVATE_FAIL 				= "TA01";
	public static String TOKEN_IS_FULL		 					= "TA02";
	
	public static String TOKEN_EDIT_SUCCESS 		= "TE00";
	public static String TOKEN_EDIT_FAIL 			    = "TE01";
	
	public static String HSM_ACTIVATE_SUCCESS       = "HA00";
	public static String HSM_ACTIVATE_FAIL              = "HA01";
	public static String PHSYICAL_HSM_EMPTY           = "HA02";
	
	/*************** CertReq If Error ************************/
	
	public static String CERT_REQ_SUCCESS					= "CRC00";
	public static String CERT_REQ_FAIL							= "CRC01";
	public static String CERT_BIN_NOT_UNIQUE            = "CRC02";
	public static String CERT_IPKINDEX_NOT_UNIQUE    = "CRC03";
	public static String CERTI_BRAND_TYPE_UNKNOWN  = "CRC04";
	
	public static String CERT_EDIT_SUCCESS 				= "CRE00";
	public static String CERT_EDIT_FAIL 						= "CRE01";
	
	public static String CERT_DELETE_SUCCESS 			= "CRD00";
	public static String CERT_DELETE_FAIL 					= "CRD01";
	
	public static String CERTI_REGISTER_SUCCESS         = "CRR00";
	public static String CERTI_REGISTER_FAIL            		= "CRR01";
	public static String CERTI_REGISTER_FILE_EMPTY      = "CRR02";
	public static String CERTI_INVALID                  		= "CRR03";
	public static String CERTI_FILENAME_INVALID         = "CRR04";
	
	
	
	/*************** Send KeyInfo to TSM If Error ************************/
	
	public static String TSM_TRANSFER_FAIL              = "TSM01";
	
	/*************** Login/Logout ************************/
	
	public static String LOGIN_FAILED                   = "LOGIN01";	
	public static String LOGOUT_SUCCESS                 = "LOGOUT00";
}
