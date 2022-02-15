package com.kona.kms.crypto.tcp.model;

import java.nio.ByteBuffer;

import com.kona.kms.KMSCode;
import com.kona.kms.KmsException;
import com.kona.kms.crypto.tcp.utility.MSGModelConstant;
import com.kona.kms.utils.Util;

public class MSGHeaderModel{

//	private ByteBuffer msgHeadBuffer = null;
	private int msgBodyLen;
	private String totalLenString;
	private String trCodeString;
	private String resCodeString;
	private String reservedString;

	private byte[] totalLen = new byte[MSGModelConstant.TR_TOTAL_LEN];
	private byte[] trCode = new byte[MSGModelConstant.TR_CODE_LEN];
	private byte[] resCode = new byte[MSGModelConstant.TR_RES_LEN];
	private byte[] reserved = new byte[MSGModelConstant.TR_FILLER_LEN];
	
	public MSGHeaderModel(ByteBuffer msgHeadBuffer) {
//		this.msgHeadBuffer = msgHeadBuffer;
	//	parseMsgHeader(msgHeadBuffer);
	}
	
	

	public void parseMsgHeader(ByteBuffer msgHeadBuffer) throws KmsException {

		try {
		msgHeadBuffer.get(totalLen, 0, MSGModelConstant.TR_TOTAL_LEN);
	
		totalLenString = new String(totalLen);
	
		msgHeadBuffer.get(trCode, 0, MSGModelConstant.TR_CODE_LEN);
		trCodeString = new String(trCode);

		msgHeadBuffer.get(resCode, 0, MSGModelConstant.TR_RES_LEN);
		resCodeString = new String(resCode);
		
		msgHeadBuffer.get(reserved, 0, MSGModelConstant.TR_FILLER_LEN);
		reservedString = new String(reserved);
		
		msgBodyLen =  Integer.parseInt(totalLenString) - MSGModelConstant.TR_TOTAL_LEN
				- MSGModelConstant.TR_CODE_LEN - MSGModelConstant.TR_RES_LEN - MSGModelConstant.TR_FILLER_LEN;
		} catch (Exception e){
			e.printStackTrace();
			throw new KmsException(KMSCode.KR_PARAMETER_INVALID);
		}
	}

//	public ByteBuffer getMsgHeadBuffer() {
//		return msgHeadBuffer;
//	}
//
//	public void setMsgHeadBuffer(ByteBuffer msgHeadBuffer) {
//		this.msgHeadBuffer = msgHeadBuffer;
//	}

	public int getMsgBodyLen() {
		return msgBodyLen;
	}

	public void setMsgBodyLen(int msgBodyLen) {
		this.msgBodyLen = msgBodyLen;
	}

	public String getTotalLenString() {
		return totalLenString;
	}

	public void setTotalLenString(String totalLenString) {
		this.totalLenString = totalLenString;
	}

	public String getTrCodeString() {
		return trCodeString;
	}

	public void setTrCodeString(String trCodeString) {
		this.trCodeString = trCodeString;
	}

	public String getResCodeString() {
		return resCodeString;
	}

	public void setResCodeString(String resCodeString) {
		this.resCodeString = resCodeString;
	}

	public String getReservedString() {
		return reservedString;
	}

	public void setReservedString(String reservedString) {
		this.reservedString = reservedString;
	}
	
	
	
}
