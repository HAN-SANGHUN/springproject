package com.kona.kms.crypto.tcp.model;

import java.nio.ByteBuffer;

import com.kona.kms.KMSCode;
import com.kona.kms.KmsException;
import com.kona.kms.crypto.tcp.utility.MSGModelConstant;

public class MSGGenRanModel {

	private ByteBuffer msgBodyBuffer = null;
	private int msgBodyLen;

	private String inputTypeString;
	private String randomSizeString;
	private int randomSizeInt;
	
	private int outputDataTo;
	
	private byte[] inputType = new byte[MSGModelConstant.BODY_IN_TYPE_LEN];
	private byte[] randomSize = new byte[MSGModelConstant.BODY_RANDOM_LEN];
	

	public MSGGenRanModel(ByteBuffer msgBodyBuffer) throws KmsException {
		this.msgBodyBuffer = msgBodyBuffer;
		parseMsgBoday(msgBodyBuffer);
	}

	public void parseMsgBoday(ByteBuffer msgHeadBuffer) throws KmsException {
		
		try{	
			msgBodyBuffer.get(inputType, 0, MSGModelConstant.BODY_IN_TYPE_LEN);
			inputTypeString = new String(inputType);
	
			msgBodyBuffer.get(randomSize, 0, MSGModelConstant.BODY_RANDOM_LEN);
			String randomSizeStringTemp = new String(randomSize);
			randomSizeString = randomSizeStringTemp.trim();
			randomSizeInt = Integer.parseInt(randomSizeString);
			
			outputDataTo = randomSizeInt;

		
		} catch (Exception e){
			e.printStackTrace();
			throw new KmsException(KMSCode.KR_PARAMETER_INVALID);
		}
		
		//String[] mechParaArray = new String[] {mechParaString};
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

	public String getRandomSizeString() {
		return randomSizeString;
	}

	public void setRandomSizeString(String randomSizeString) {
		this.randomSizeString = randomSizeString;
	}

	public int getRandomSizeInt() {
		return randomSizeInt;
	}

	public void setRandomSizeInt(int randomSizeInt) {
		this.randomSizeInt = randomSizeInt;
	}

	public int getOutputDataTo() {
		return outputDataTo;
	}

	public void setOutputDataTo(int outputDataTo) {
		this.outputDataTo = outputDataTo;
	}

	public byte[] getInputType() {
		return inputType;
	}

	public void setInputType(byte[] inputType) {
		this.inputType = inputType;
	}

	public byte[] getRandomSize() {
		return randomSize;
	}

	public void setRandomSize(byte[] randomSize) {
		this.randomSize = randomSize;
	}

	
	
}