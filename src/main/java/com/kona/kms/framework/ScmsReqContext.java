package com.kona.kms.framework;

import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class ScmsReqContext {
	
	private static final Logger logger = LoggerFactory.getLogger(ScmsReqContext.class);
	
	Object	 				m_inputStream;
	/*----------------------------------*/
	ScmsReqContext		m_relatedTo 		= null;
	ScmsReqContext 		m_subsequent 	= null;
	String					m_sessionId 		= null;
	ScmsRequest			m_scmsRequest 	= null;
	ScmsResponse 		m_scmsResponse = null;
	byte[] 					m_xmlInput 		= null;
	byte[] 					m_xmlOutput 		= null;
	/*----------------------------------*/
	RqRpSyncQueue 		comWithWorker;
	int 						m_noWkrCalls = 0;
	/*----------------------------------*/
	RqRpSyncQueue 		comWithOta;
	int m_noOtaCalls = 0;
	/*----------------------------------*/
	IOMsgFormatter m_msgFormatter = null;
	
	String m_msgcode = null;
	String m_message = null;
	int m_status = 0;
	Date m_date = null;
	String m_lang = null;
	String m_encoding = null;
	// tradeSeq
	long m_tradeSeq;
	// logging
	long m_ctime;	// creation time
	String m_subj1;	//
	String m_svcname, m_usrid, m_cltip;
	
    public ScmsReqContext(Object inputStream) {
    	m_inputStream = inputStream;
        
    	m_relatedTo = this;
    	m_subsequent = this;
        comWithWorker = new RqRpSyncQueue();
        comWithOta = new RqRpSyncQueue();

		m_date = new Date();
		m_ctime = System.currentTimeMillis();	// creation time in milis
    }
    
    public Object getInputStream() {
    	return m_inputStream;
    }

    public void setNewSession(String sid) {
		m_sessionId = sid;
	}
	public void setSessionId(String sid) {
    	m_sessionId = sid;
    }
    public String getSessionId() {
    	return m_sessionId;
    }
    public void setRelatedReqCtx(ScmsReqContext relatedTo) {
    	m_relatedTo = relatedTo;
    	relatedTo.setSubsequent(this);
    }
	public void setSubsequent(ScmsReqContext sub) {
		m_subsequent = sub;
	}
	public ScmsReqContext getSubsequent() {
		return m_subsequent;
    }
        
    public RqRpSyncQueue getComWithWorker() {
    	if (m_relatedTo == this) {
    		/* session owner */
    		return this.comWithWorker;
    	}
    	else {
    		/* session owner's comWithWorker */
    		return m_relatedTo.getComWithWorker();
    	}
    }
    public RqRpSyncQueue getComWithOta() {
    	if (m_relatedTo == this) {
    		/* session owner */
    		return this.comWithOta;
    	}
    	else {
    		/* session owner's comWithOta */
    		return m_relatedTo.getComWithOta();
    	}
    }
    public ScmsResponse callWorker(ScmsRequest req) {
    	getSessReqCtx().incNoWkrCalls();
        return (ScmsResponse) getComWithWorker().requestNwaitforRp(req);    	
    }
    public boolean isSessionOwner() {
    	if (m_relatedTo == this) {
    		return true;
    	}
    	return false;
    }
    public boolean isSessionAlreadyStarted() {
    	return !isSessionOwner();	// if not SessionOwner, Already Started !!!
    }
    public int getNoWkrCalls() {
    	if (isSessionOwner())
    		return m_noWkrCalls;
    	else
    		return getSessReqCtx().m_noWkrCalls;
    }
    public void incNoWkrCalls() {
    	m_noWkrCalls++;
    }
		
    public void setMsgFormatter(IOMsgFormatter msgFmtr) {
    	m_msgFormatter = msgFmtr;
    }
    public IOMsgFormatter getMsgFormatter() {
    	return m_msgFormatter;
    }

    public String getCommand() {
    	if (m_msgFormatter != null) {
    		m_svcname = m_msgFormatter.extCommand();
    		return m_svcname;
    	}
    	return null;
    }
    
    public ScmsReqContext getSessReqCtx() {
    	return m_relatedTo;
    }
    public String extSessionId() {
    	/* 보완 ?�요 */
    	if (m_msgFormatter != null) {
    		return m_msgFormatter.extSession();
    	}
    	return null;
    }
    
	public boolean isSessionToBeClosed() {
    	if (m_msgFormatter != null) {
    		return m_msgFormatter.isLastPayload();
    	}
    	return true;
    }

    // parse data & setScmsRequest();
    public ScmsRequest parseRequest() {
    	if (m_msgFormatter != null) {
			m_scmsRequest = m_msgFormatter.parseRequestMsg(getInputStream());
    		return m_scmsRequest;
    	}
    	return null;
    }
    // generate output data
    public byte[] fmtResponse() {
    	if (m_msgFormatter != null) {
    		m_xmlOutput = m_msgFormatter.fmtResponseMsg(m_scmsResponse);
    		return m_xmlOutput;
    	}
    	return null;
    }
    // generate scms error output data
    public byte[] fmtScmsErrResponse(ScmsException se) {
    	if (m_msgFormatter != null) {
    		m_xmlOutput = m_msgFormatter.fmtErrResponseMsg(se);
    		return m_xmlOutput;
    	}
    	return null;
    }
    // generate unknown error output data
    public byte[] fmtUnknownErrResponse(Exception e) {
    	if (m_msgFormatter != null) {
    		m_xmlOutput = m_msgFormatter.fmtUnknownErrResponseMsg(e);
    		return m_xmlOutput;
    	}
    	return null;
    }
  
    //public void setScmsRequest(ScmsRequest scmsRequest) { m_scmsRequest = scmsRequest; }
    public ScmsRequest getScmsRequest() {
    	return m_scmsRequest;
    }
    public void setScmsResponse(ScmsResponse scmsResponse) {
    	m_scmsResponse = scmsResponse;	
    }
    public ScmsResponse getScmsResponse() {
    	return m_scmsResponse;
    }

	public byte[] getOutputData() {
		return m_xmlOutput;
	}
	public byte[] getInputData() {
		return m_xmlInput;
	}
	public String getOutputStr() {
		if (m_xmlOutput == null)
			return "";
		return m_xmlOutput.toString();
	}
	public String getInputStr() {
		if (m_xmlInput == null)
			return "";
		return m_xmlInput.toString();
	}

	public long getElspTm() {
		return ( System.currentTimeMillis() - m_ctime );
    }
	    
	public void setEncoding(String encoding) {
		m_encoding = encoding;
	}
	public String getEncoding() {
		return m_encoding;
	}
	
	/* returns "yyyyMMddkkmmssSSS" */
	public String getDateTime() {
		SimpleDateFormat SDF = new SimpleDateFormat("yyyyMMddkkmmssSSS");
		return SDF.format(m_date);
	}
	/* returns "yyyyMMdd" */
	public String getDate() {
		SimpleDateFormat SDF = new SimpleDateFormat("yyyyMMdd");
		return SDF.format(m_date);
	}
	
    public String getUserid() {
		return m_usrid;
    }
    public String getCltip() {
		return m_cltip;
    }
    public String getSvcname() {
		return m_svcname;
    }
    public String getSubject() {
		return m_subj1;
    }
    public void setSubject(String subject) {
		m_subj1 = subject;
    }
    
    public String getMessage() {
    	if (m_message != null)
    		return m_message;
    	if (m_msgcode != null) {
    		m_message = MsgManager.getInstance().getMessage(m_msgcode);
    	}
    	return m_message;
    }
    public String getMsgcode() {
		return m_msgcode;
    }
    public void setMsgcode(String msgcode) {
    	m_msgcode = msgcode;
        m_message = MsgManager.getInstance().getMessage(msgcode);
        if (m_message == null || m_message.length() == 0) {
        	m_message = "unknown message-code";
        }
		System.out.println("===> ScmsReqContext::setMsgcode: msgcode="+msgcode+", message="+m_message);
    }
    public void setMsgcode(String msgcode, String message) {
    	m_msgcode = msgcode;
        m_message = message;
		System.out.println("===> ScmsReqContext::setMsgcode: msgcode="+msgcode+", user message="+m_message);
    }
    public void setStatus(int status) {
		m_status = status;
    }
    public int getStatus() {
		return m_status;
    }
    public boolean isErrStatus() {
		if (m_status != 0) return true;
		return false;
    }
    
    /* trade seq */
	public void setTradeSeq(long seq) {
		m_tradeSeq = seq;
	}
	public long getTradeSeq() {
		return m_tradeSeq;
	}
	
	// application specific data
	String APC, CIN, CRN, PAN;
	public void setAppData(String apc, String cin, String crn, String pan) {
		APC = apc; CIN = cin; CRN = crn; PAN = pan; m_usrid = cin;
	}
	public String getApc() { return APC; }
	public String getCin() { return CIN; }
	public String getCrn() { return CRN; }
	public String getPan() { return PAN; }
}
