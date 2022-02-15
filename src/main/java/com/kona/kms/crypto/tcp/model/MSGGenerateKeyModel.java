package com.kona.kms.crypto.tcp.model;

import java.nio.ByteBuffer;

import com.kona.kms.KMSCode;
import com.kona.kms.KmsException;
import com.kona.kms.crypto.KeyAttributes;
import com.kona.kms.crypto.tcp.keyAttrisModel.KeyAttrisGenKeyModel;
import com.kona.kms.crypto.tcp.utility.MSGModelConstant;

public class MSGGenerateKeyModel {

	private ByteBuffer msgBodyBuffer = null;
	private int msgBodyLen;

	private String inputTypeString;
	private String tokenLabelString;
	private String mechanismString;
	private String outputTypeString;
	private KeyAttributes keyAttributes;

	private byte[] inputType = new byte[MSGModelConstant.BODY_IN_TYPE_LEN];
	private byte[] tokenLabel = new byte[MSGModelConstant.BODY_TOKENLABEL_LEN];
	private byte[] mechanism = new byte[MSGModelConstant.BODY_MECH_LEN];
	private byte[] outputType = new byte[MSGModelConstant.BODY_OUT_TYPE_LEN];
	private byte[] keyAttrisArray;

	public MSGGenerateKeyModel(ByteBuffer msgBodyBuffer) throws KmsException {
		this.msgBodyBuffer = msgBodyBuffer;
		parseMsgBoday(msgBodyBuffer);
	}

	public void parseMsgBoday(ByteBuffer msgHeadBuffer) throws KmsException {
		
		try{
			msgBodyBuffer.get(inputType, 0, MSGModelConstant.BODY_IN_TYPE_LEN);
			inputTypeString = new String(inputType);
	
			Integer keyAttrisLen = MSGModelConstant.KEY_ATTRS_KEYCLASS_LEN+MSGModelConstant.KEY_ATTRS_KEYTYPE_LEN+MSGModelConstant.KEY_ATTRS_BOOLEAN_LEN*13+MSGModelConstant.KEY_ATTRS_KEYLABEL_LEN+MSGModelConstant.KEY_ATTRS_DATE_LEN*2+MSGModelConstant.KEY_ATTRS_KEYVAL_LEN_LEN;
			keyAttrisArray = new byte[keyAttrisLen];
			
			msgBodyBuffer.get(tokenLabel, 0, 256);
			String tokenLabelStringTemp = new String(tokenLabel);
			tokenLabelString = tokenLabelStringTemp.trim();
	
			msgBodyBuffer.get(mechanism, 0, MSGModelConstant.BODY_MECH_LEN);
			String mechanismStringTemp = new String(mechanism);
			mechanismString = mechanismStringTemp.trim();
	
			msgBodyBuffer.get(outputType, 0, MSGModelConstant.BODY_OUT_TYPE_LEN);
			outputTypeString = new String(outputType);
	
			msgBodyBuffer.get(keyAttrisArray, 0, keyAttrisLen);
			
			KeyAttrisGenKeyModel keyAttrisGenKeyModel = new KeyAttrisGenKeyModel(keyAttrisArray);
			keyAttributes = keyAttrisGenKeyModel.getKeyAttributes();
		
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

	public String getMechanismString() {
		return mechanismString;
	}

	public void setMechanismString(String mechanismString) {
		this.mechanismString = mechanismString;
	}

	public String getOutputTypeString() {
		return outputTypeString;
	}

	public void setOutputTypeString(String outputTypeString) {
		this.outputTypeString = outputTypeString;
	}

	public byte[] getInputType() {
		return inputType;
	}

	public void setInputType(byte[] inputType) {
		this.inputType = inputType;
	}

	public byte[] getMechanism() {
		return mechanism;
	}

	public void setMechanism(byte[] mechanism) {
		this.mechanism = mechanism;
	}

	public byte[] getOutputType() {
		return outputType;
	}

	public void setOutputType(byte[] outputType) {
		this.outputType = outputType;
	}

	public String getTokenLabelString() {
		return tokenLabelString;
	}

	public void setTokenLabelString(String tokenLabelString) {
		this.tokenLabelString = tokenLabelString;
	}

	public KeyAttributes getKeyAttributes() {
		return keyAttributes;
	}

	public void setKeyAttributes(KeyAttributes keyAttributes) {
		this.keyAttributes = keyAttributes;
	}

	public byte[] getTokenLabel() {
		return tokenLabel;
	}

	public void setTokenLabel(byte[] tokenLabel) {
		this.tokenLabel = tokenLabel;
	}

	public byte[] getKeyAttrisArray() {
		return keyAttrisArray;
	}

	public void setKeyAttrisArray(byte[] keyAttrisArray) {
		this.keyAttrisArray = keyAttrisArray;
	}

	
	

}
