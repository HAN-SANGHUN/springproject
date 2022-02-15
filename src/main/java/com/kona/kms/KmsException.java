package com.kona.kms;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


public class KmsException extends Exception {

	private static final long serialVersionUID = 8727476636593744696L;
	
	String m_msgcode;
	String m_message = null;
	Object m_param = null;
	
    private static final ConcurrentHashMap<String, String> errorMap; 

    static {
	
		String[] errorCodes = new String[] {
			KMSCode.KR_ERROR,
			KMSCode.KR_CRYPTO_ERROR,
			KMSCode.KR_KEY_NOT_FOUND,
			KMSCode.KR_TOKEN_NOT_FOUND,
			KMSCode.KR_CLIENT_INTF_ERROR,
			KMSCode.KR_PARAMETER_INVALID,
			KMSCode.KR_ATTRIBUTE_INVALID,
			KMSCode.KR_MECHANISM_INVALID,
			KMSCode.KR_UNKNOWN_ERROR
		};
	
		String[] errorMessages = new String[] {
			"KR_ERROR",
			"KR_CRYPTO_ERROR",
			"KR_KEY_NOT_FOUND",
			"KR_TOKEN_NOT_FOUND",
			"KR_CLIENT_INTF_ERROR",
			"KR_PARAMETER_INVALID",
			"KR_ATTRIBUTE_INVALID",
			"KR_MECHANISM_INVALID",
			"KR_UNKNOWN_ERROR"
	   };

		errorMap = new ConcurrentHashMap<String,String>();

		for (int i = 0; i < errorCodes.length; i++) {
			errorMap.put(errorCodes[i], errorMessages[i]);
		}
    }
	
	public KmsException(String msgcode) {
		m_msgcode = msgcode;
		m_message = getMessage();
	}
	
	public KmsException(String msgcode, String emsg) {
		m_msgcode = msgcode;
		m_message = emsg;
	}
	
	public KmsException(String msgcode, Object param) {
		m_msgcode = msgcode;
		m_param = param;
	}

	public String getMsgcode() {
		return m_msgcode;
	}
	
	public Object getParam() {
		return m_param;
	}
		
    public String getMessage() {

		if (m_message != null) return m_message;

		String message = errorMap.get(m_msgcode);
        if (message == null) {
            message = "0x" + m_msgcode;
        }
        return message;
    }
	
	public long getErrorCode() {
		return Long.valueOf(m_msgcode);
	}
	
}
