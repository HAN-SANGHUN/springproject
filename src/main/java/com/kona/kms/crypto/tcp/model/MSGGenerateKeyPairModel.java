package com.kona.kms.crypto.tcp.model;

import java.nio.ByteBuffer;

import com.kona.kms.KMSCode;
import com.kona.kms.KmsException;
import com.kona.kms.crypto.KeyAttributes;
import com.kona.kms.crypto.tcp.keyAttrisModel.KeyAttrisGenKeyPairModel;
import com.kona.kms.crypto.tcp.utility.MSGModelConstant;

public class MSGGenerateKeyPairModel {

	private ByteBuffer msgBodyBuffer = null;
	private int msgBodyLen;

	private String inputTypeString;
	private String tokenLabelString;
	private String mechanismString;
	private String outputTypeString;
	
	private Integer pairKeyAttrisLenInt;
	private Integer pubKeyAttrisLenInt;
	private Integer priKeyAttrisLenInt;
	
	private KeyAttributes pubKeyAttributes;
	private KeyAttributes priKeyAttributes;

	private byte[] inputType = new byte[MSGModelConstant.BODY_IN_TYPE_LEN];
	private byte[] tokenLabel = new byte[MSGModelConstant.BODY_TOKENLABEL_LEN];
	private byte[] mechanism = new byte[MSGModelConstant.BODY_MECH_LEN];
	private byte[] outputType = new byte[MSGModelConstant.BODY_OUT_TYPE_LEN];
	
	private byte[] pairKeyAttrisLen = new byte[MSGModelConstant.MSG_FIELD_LEN];
	private byte[] pubKeyAttrisLen = new byte[MSGModelConstant.MSG_FIELD_LEN];
	private byte[] priKeyAttrisLen = new byte[MSGModelConstant.MSG_FIELD_LEN];
	
	private byte[] pubKeyAttrisArray;
	private byte[] priKeyAttrisArray;
	private byte[] pairKeyAttrisArray;

	public MSGGenerateKeyPairModel(ByteBuffer msgBodyBuffer) throws KmsException {
		this.msgBodyBuffer = msgBodyBuffer;
		parseMsgBoday(msgBodyBuffer);
	}

	public void parseMsgBoday(ByteBuffer msgHeadBuffer) throws KmsException {
		
		try{
		msgBodyBuffer.get(inputType, 0, MSGModelConstant.BODY_IN_TYPE_LEN);
		inputTypeString = new String(inputType);
		
		msgBodyBuffer.get(tokenLabel, 0, 256);
		String tokenLabelStringTemp = new String(tokenLabel);
		tokenLabelString = tokenLabelStringTemp.trim();

		msgBodyBuffer.get(mechanism, 0, MSGModelConstant.BODY_MECH_LEN);
		String mechanismStringTemp = new String(mechanism);
		mechanismString = mechanismStringTemp.trim();

		msgBodyBuffer.get(outputType, 0, MSGModelConstant.BODY_OUT_TYPE_LEN);
		outputTypeString = new String(outputType);

		msgBodyBuffer.get(pairKeyAttrisLen, 0, MSGModelConstant.MSG_FIELD_LEN);
		String pairKeyAttrisLenStr = new String(pairKeyAttrisLen);
		pairKeyAttrisLenInt = Integer.parseInt(pairKeyAttrisLenStr);
		
		msgBodyBuffer.get(pubKeyAttrisLen, 0, MSGModelConstant.MSG_FIELD_LEN);
		String pubKeyAttrisLenStr = new String(pubKeyAttrisLen);
		pubKeyAttrisLenInt = Integer.parseInt(pubKeyAttrisLenStr);
		
		msgBodyBuffer.get(priKeyAttrisLen, 0, MSGModelConstant.MSG_FIELD_LEN);
		String priKeyAttrisLenStr = new String(priKeyAttrisLen);
		priKeyAttrisLenInt = Integer.parseInt(priKeyAttrisLenStr);
		
		pairKeyAttrisArray = new byte[pairKeyAttrisLenInt];
		
		msgBodyBuffer.get(pairKeyAttrisArray, 0, pairKeyAttrisLenInt);
		
		KeyAttrisGenKeyPairModel keyAttrisGenKeyPairModel = new KeyAttrisGenKeyPairModel(pairKeyAttrisArray);
		pubKeyAttributes = keyAttrisGenKeyPairModel.getPubKeyAttributes();
		priKeyAttributes = keyAttrisGenKeyPairModel.getPriKeyAttributes();
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

	public Integer getPairKeyAttrisLenInt() {
		return pairKeyAttrisLenInt;
	}

	public void setPairKeyAttrisLenInt(Integer pairKeyAttrisLenInt) {
		this.pairKeyAttrisLenInt = pairKeyAttrisLenInt;
	}

	public Integer getPubKeyAttrisLenInt() {
		return pubKeyAttrisLenInt;
	}

	public void setPubKeyAttrisLenInt(Integer pubKeyAttrisLenInt) {
		this.pubKeyAttrisLenInt = pubKeyAttrisLenInt;
	}

	public Integer getPriKeyAttrisLenInt() {
		return priKeyAttrisLenInt;
	}

	public void setPriKeyAttrisLenInt(Integer priKeyAttrisLenInt) {
		this.priKeyAttrisLenInt = priKeyAttrisLenInt;
	}

	public KeyAttributes getPubKeyAttributes() {
		return pubKeyAttributes;
	}

	public void setPubKeyAttributes(KeyAttributes pubKeyAttributes) {
		this.pubKeyAttributes = pubKeyAttributes;
	}

	public KeyAttributes getPriKeyAttributes() {
		return priKeyAttributes;
	}

	public void setPriKeyAttributes(KeyAttributes priKeyAttributes) {
		this.priKeyAttributes = priKeyAttributes;
	}

	public byte[] getPairKeyAttrisLen() {
		return pairKeyAttrisLen;
	}

	public void setPairKeyAttrisLen(byte[] pairKeyAttrisLen) {
		this.pairKeyAttrisLen = pairKeyAttrisLen;
	}

	public byte[] getPubKeyAttrisLen() {
		return pubKeyAttrisLen;
	}

	public void setPubKeyAttrisLen(byte[] pubKeyAttrisLen) {
		this.pubKeyAttrisLen = pubKeyAttrisLen;
	}

	public byte[] getPriKeyAttrisLen() {
		return priKeyAttrisLen;
	}

	public void setPriKeyAttrisLen(byte[] priKeyAttrisLen) {
		this.priKeyAttrisLen = priKeyAttrisLen;
	}

	public byte[] getPubKeyAttrisArray() {
		return pubKeyAttrisArray;
	}

	public void setPubKeyAttrisArray(byte[] pubKeyAttrisArray) {
		this.pubKeyAttrisArray = pubKeyAttrisArray;
	}

	public byte[] getPriKeyAttrisArray() {
		return priKeyAttrisArray;
	}

	public void setPriKeyAttrisArray(byte[] priKeyAttrisArray) {
		this.priKeyAttrisArray = priKeyAttrisArray;
	}

	public byte[] getPairKeyAttrisArray() {
		return pairKeyAttrisArray;
	}

	public void setPairKeyAttrisArray(byte[] pairKeyAttrisArray) {
		this.pairKeyAttrisArray = pairKeyAttrisArray;
	}

	

	
	

}
