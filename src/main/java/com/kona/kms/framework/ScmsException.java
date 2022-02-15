package com.kona.kms.framework;
 

public class ScmsException extends RuntimeException {

	int m_status = -1;
	String m_msgcode;
	String m_message;
	Object m_param = null;
	
	
	public ScmsException(String msgcode) {
		m_msgcode = msgcode;
		m_message = null;
	}
	
	public ScmsException(String msgcode, String emsg) {
		super(emsg);
		m_message = emsg;
		m_msgcode = msgcode;
	}
	
	public ScmsException(String msgcode, Object param) {
		m_msgcode = msgcode;
		m_param = param;
	}

	public String getMsgcode() {
		return m_msgcode;
	}
	
	public Object getParam() {
		return m_param;
	}
	
	public String getScmsMessage() {
		return (MsgManager.getInstance()).getMessage(m_msgcode);
	}

	public String getMessage() {
		if (m_msgcode == null) return null;
		if (m_message != null) return m_message;
		return getScmsMessage();
	}
	
	// not implemented - ?�당 ?�무 분류(category)명을 리턴?�다.
	public String getMsgCategory() {
		return m_msgcode;
	}
}

