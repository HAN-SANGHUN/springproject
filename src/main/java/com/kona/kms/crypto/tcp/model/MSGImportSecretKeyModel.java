package com.kona.kms.crypto.tcp.model;

import java.nio.ByteBuffer;

import com.kona.kms.KMSCode;
import com.kona.kms.KmsException;
import com.kona.kms.crypto.KeyAttributes;
import com.kona.kms.crypto.tcp.keyAttrisModel.KeyAttrisGenKeyPairModel;
import com.kona.kms.crypto.tcp.keyAttrisModel.KeyAttrisImportSecretKeyModel;
import com.kona.kms.crypto.tcp.utility.MSGModelConstant;

public class MSGImportSecretKeyModel {

	private ByteBuffer msgBodyBuffer = null;
	private int msgBodyLen;

	private String inputTypeString;
	private String tokenLabelString;
	private String mechanismString;
	private String outputTypeString;

	private Integer keyAttrisLenInt;

	private KeyAttributes keyAttributes;

	private byte[] inputType = new byte[MSGModelConstant.BODY_IN_TYPE_LEN];
	private byte[] tokenLabel = new byte[MSGModelConstant.BODY_TOKENLABEL_LEN];
	private byte[] mechanism = new byte[MSGModelConstant.BODY_MECH_LEN];
	private byte[] outputType = new byte[MSGModelConstant.BODY_OUT_TYPE_LEN];

	private byte[] keyAttrisLen = new byte[MSGModelConstant.MSG_FIELD_LEN];

	private byte[] keyAttrisArray;

	public MSGImportSecretKeyModel(ByteBuffer msgBodyBuffer) throws KmsException {
		this.msgBodyBuffer = msgBodyBuffer;
		parseMsgBoday(msgBodyBuffer);
	}

	public void parseMsgBoday(ByteBuffer msgHeadBuffer) throws KmsException {

		try{
			msgBodyBuffer.get(inputType, 0, MSGModelConstant.BODY_IN_TYPE_LEN);
			inputTypeString = new String(inputType);
	
			msgBodyBuffer.get(tokenLabel, 0, 256);
			String tokenLabelStringTemp = new String(tokenLabel);
//			tokenLabelString = tokenLabelStringTemp.replaceAll(" ", "");
			tokenLabelString = tokenLabelStringTemp.trim();
	
			msgBodyBuffer.get(mechanism, 0, MSGModelConstant.BODY_MECH_LEN);
			String mechanismStringTemp = new String(mechanism);
			mechanismString = mechanismStringTemp.trim();
	
			msgBodyBuffer.get(outputType, 0, MSGModelConstant.BODY_OUT_TYPE_LEN);
			outputTypeString = new String(outputType);
	
			msgBodyBuffer.get(keyAttrisLen, 0, MSGModelConstant.MSG_FIELD_LEN);
			String keyAttrisLenStr = new String(keyAttrisLen);
			keyAttrisLenInt = Integer.parseInt(keyAttrisLenStr);
	
			keyAttrisArray = new byte[keyAttrisLenInt];
	
			msgBodyBuffer.get(keyAttrisArray, 0, keyAttrisLenInt);
	
			KeyAttrisImportSecretKeyModel keyAttrisImportSecretKeyModel = new KeyAttrisImportSecretKeyModel(keyAttrisArray);
			keyAttributes = keyAttrisImportSecretKeyModel.getKeyAttributes();
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

	public byte[] getTokenLabel() {
		return tokenLabel;
	}

	public void setTokenLabel(byte[] tokenLabel) {
		this.tokenLabel = tokenLabel;
	}

	public Integer getKeyAttrisLenInt() {
		return keyAttrisLenInt;
	}

	public void setKeyAttrisLenInt(Integer keyAttrisLenInt) {
		this.keyAttrisLenInt = keyAttrisLenInt;
	}

	public KeyAttributes getKeyAttributes() {
		return keyAttributes;
	}

	public void setKeyAttributes(KeyAttributes keyAttributes) {
		this.keyAttributes = keyAttributes;
	}

	public byte[] getKeyAttrisLen() {
		return keyAttrisLen;
	}

	public void setKeyAttrisLen(byte[] keyAttrisLen) {
		this.keyAttrisLen = keyAttrisLen;
	}

	public byte[] getKeyAttrisArray() {
		return keyAttrisArray;
	}

	public void setKeyAttrisArray(byte[] keyAttrisArray) {
		this.keyAttrisArray = keyAttrisArray;
	}

}
