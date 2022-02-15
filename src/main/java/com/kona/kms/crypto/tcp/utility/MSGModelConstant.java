package com.kona.kms.crypto.tcp.utility;

public class MSGModelConstant {
	
	/*
	 *  Message header length 
	 */
	public static int TR_TOTAL_LEN = 4;
	public static int TR_CODE_LEN = 4;
	public static int TR_RES_LEN = 4;
	public static int TR_FILLER_LEN = 32;
	
	public static int HEADER_TOTAL_LEN = 44;

	/*
	 *  Request Message body length 
	 */
	public static int BODY_IN_TYPE_LEN = 1;
	public static int BODY_KEYLABEL_LEN = 256;
	public static int BODY_MECH_LEN = 32;
	public static int BODY_MECH_PARA_LEN_LEN = 4;
	public static int BODY_OUT_TYPE_LEN = 1;
	public static int BODY_IN_DATA_LEN_LEN = 4;
	public static int BODY_SIGN_LEN_LEN = 4;
	public static int BODY_TOKENLABEL_LEN = 256;
	public static int BODY_RANDOM_LEN = 256;
	
	/*
	 *  Response Message body length 
	 */
	public static int BODY_OUT_DATA_LEN = 4;
	
	
	/*
	 *  Key Attributes length  
	 */
	public static int KEY_ATTRS_BOOLEAN_LEN = 1;
	public static int KEY_ATTRS_KEYCLASS_LEN = 32;
	public static int KEY_ATTRS_KEYTYPE_LEN = 32;
	public static int KEY_ATTRS_KEYLABEL_LEN = 256;
	public static int KEY_ATTRS_DATE_LEN = 8;
	public static int KEY_ATTRS_KEYVAL_LEN_LEN = 32;
	
	
	/*
	 *  Common length  
	 */
	public static int MSG_FIELD_LEN = 4;
	
}
