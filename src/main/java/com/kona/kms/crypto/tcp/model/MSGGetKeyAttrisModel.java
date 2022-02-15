package com.kona.kms.crypto.tcp.model;

import java.nio.ByteBuffer;

import com.kona.kms.KMSCode;
import com.kona.kms.KmsException;
import com.kona.kms.crypto.tcp.utility.MSGModelConstant;

public class MSGGetKeyAttrisModel {

	private ByteBuffer msgBodyBuffer = null;
	private int msgBodyLen;

	private String inputTypeString;
	private String keyLabelString;
	
	private byte[] inputType = new byte[MSGModelConstant.BODY_IN_TYPE_LEN];
	private byte[] keyLabel = new byte[MSGModelConstant.BODY_KEYLABEL_LEN];

	public MSGGetKeyAttrisModel(ByteBuffer msgBodyBuffer) throws KmsException {
		this.msgBodyBuffer = msgBodyBuffer;
		parseMsgBoday(msgBodyBuffer);
	}

	public void parseMsgBoday(ByteBuffer msgHeadBuffer) throws KmsException {
		
		try{
			msgBodyBuffer.get(inputType, 0, MSGModelConstant.BODY_IN_TYPE_LEN);
			inputTypeString = new String(inputType);
	
			msgBodyBuffer.get(keyLabel, 0, MSGModelConstant.BODY_KEYLABEL_LEN);
			String keyLabelStringTemp = new String(keyLabel);
			keyLabelString = keyLabelStringTemp.trim();
		} catch (Exception e){
			e.printStackTrace();
			throw new KmsException(KMSCode.KR_PARAMETER_INVALID);	
		}

	}

	public ByteBuffer getMsgBodyBuffer() {
		return msgBodyBuffer;
	}

	public void setMsgBodyBuffer(ByteBuffer msgBodyBuffer) {
		this.msgBodyBuffer = msgBodyBuffer;
	}

	public int getMsgBodyLen() {
		return msgBodyLen;
	}

	public void setMsgBodyLen(int msgBodyLen) {
		this.msgBodyLen = msgBodyLen;
	}

	public String getInputTypeString() {
		return inputTypeString;
	}

	public void setInputTypeString(String inputTypeString) {
		this.inputTypeString = inputTypeString;
	}

	public String getKeyLabelString() {
		return keyLabelString;
	}

	public void setKeyLabelString(String keyLabelString) {
		this.keyLabelString = keyLabelString;
	}

	public byte[] getInputType() {
		return inputType;
	}

	public void setInputType(byte[] inputType) {
		this.inputType = inputType;
	}

	public byte[] getKeyLabel() {
		return keyLabel;
	}

	public void setKeyLabel(byte[] keyLabel) {
		this.keyLabel = keyLabel;
	}

	
	

}
