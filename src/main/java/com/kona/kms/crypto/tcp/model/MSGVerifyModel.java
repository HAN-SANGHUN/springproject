package com.kona.kms.crypto.tcp.model;

import java.nio.ByteBuffer;

import com.kona.kms.KMSCode;
import com.kona.kms.KmsException;
import com.kona.kms.crypto.tcp.utility.*;

public class MSGVerifyModel {

	private ByteBuffer msgBodyBuffer = null;
	private int msgBodyLen;

	private String inputTypeString;
	private String keyLabelString;
	private String mechanismString;
	private String mechParaLenString;
	private String mechParaString;
	private String signLenString;
	private String signString;
	private String outputTypeString;
	private String inputDataLenString;
	private String inputDataString;
	private byte[] outputDataTo;

	private byte[] outputSignTo;

	private byte[] inputType = new byte[MSGModelConstant.BODY_IN_TYPE_LEN];
	private byte[] keyLabel = new byte[MSGModelConstant.BODY_KEYLABEL_LEN];
	private byte[] mechanism = new byte[MSGModelConstant.BODY_MECH_LEN];
	private byte[] mechParaLen = new byte[MSGModelConstant.BODY_MECH_PARA_LEN_LEN];
	private byte[] signLen = new byte[MSGModelConstant.BODY_SIGN_LEN_LEN];
	private byte[] outputType = new byte[MSGModelConstant.BODY_OUT_TYPE_LEN];
	private byte[] inputDataLen = new byte[MSGModelConstant.BODY_IN_DATA_LEN_LEN];

	private int mechPara_Len;
	private int inputData_Len;
	private int sign_Len;

	public MSGVerifyModel(ByteBuffer msgBodyBuffer) throws KmsException {
		this.msgBodyBuffer = msgBodyBuffer;
		parseMsgBoday(msgBodyBuffer);
	}

	public void parseMsgBoday(ByteBuffer msgHeadBuffer) throws KmsException {
		
		try {
			msgBodyBuffer.get(inputType, 0, MSGModelConstant.BODY_IN_TYPE_LEN);
			inputTypeString = new String(inputType);
	
			msgBodyBuffer.get(keyLabel, 0, MSGModelConstant.BODY_KEYLABEL_LEN);
	
			String keyLabelStringTemp = new String(keyLabel);
//			keyLabelString = keyLabelStringTemp.replaceAll(" ", "");
			keyLabelString = keyLabelStringTemp.trim();
	
			msgBodyBuffer.get(mechanism, 0, MSGModelConstant.BODY_MECH_LEN);
			String mechanismStringTemp = new String(mechanism);
			mechanismString = mechanismStringTemp.trim();
	
			msgBodyBuffer.get(mechParaLen, 0, MSGModelConstant.BODY_MECH_PARA_LEN_LEN);
			mechParaLenString = new String(mechParaLen);
	
			mechPara_Len = Integer.parseInt(mechParaLenString);
			byte[] mechPara = new byte[mechPara_Len];
			msgBodyBuffer.get(mechPara, 0, mechPara_Len);
			mechParaString = new String(mechPara);
	
			msgBodyBuffer.get(outputType, 0, MSGModelConstant.BODY_OUT_TYPE_LEN);
			outputTypeString = new String(outputType);
	
			msgBodyBuffer.get(inputDataLen, 0, MSGModelConstant.BODY_IN_DATA_LEN_LEN);
			inputDataLenString = new String(inputDataLen);
	
			inputData_Len = Integer.parseInt(inputDataLenString);
			byte[] inputData = new byte[inputData_Len];
			msgBodyBuffer.get(inputData, 0, inputData_Len);
			inputDataString = new String(inputData);
			outputDataTo = inputData;
	
			msgBodyBuffer.get(signLen, 0, MSGModelConstant.BODY_SIGN_LEN_LEN);
			signLenString = new String(signLen);
	
			sign_Len = Integer.parseInt(signLenString);
			byte[] sign = new byte[sign_Len];
			msgBodyBuffer.get(sign, 0, sign_Len);
			signString = new String(sign);
			outputSignTo = sign;
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

	public String getMechanismString() {
		return mechanismString;
	}

	public void setMechanismString(String mechanismString) {
		this.mechanismString = mechanismString;
	}

	public String getMechParaLenString() {
		return mechParaLenString;
	}

	public void setMechParaLenString(String mechParaLenString) {
		this.mechParaLenString = mechParaLenString;
	}

	public String getMechParaString() {
		return mechParaString;
	}

	public void setMechParaString(String mechParaString) {
		this.mechParaString = mechParaString;
	}

	public String getSignLenString() {
		return signLenString;
	}

	public void setSignLenString(String signLenString) {
		this.signLenString = signLenString;
	}

	public String getSignString() {
		return signString;
	}

	public void setSignString(String signString) {
		this.signString = signString;
	}

	public String getOutputTypeString() {
		return outputTypeString;
	}

	public void setOutputTypeString(String outputTypeString) {
		this.outputTypeString = outputTypeString;
	}

	public String getInputDataLenString() {
		return inputDataLenString;
	}

	public void setInputDataLenString(String inputDataLenString) {
		this.inputDataLenString = inputDataLenString;
	}

	public String getInputDataString() {
		return inputDataString;
	}

	public void setInputDataString(String inputDataString) {
		this.inputDataString = inputDataString;
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

	public byte[] getMechanism() {
		return mechanism;
	}

	public void setMechanism(byte[] mechanism) {
		this.mechanism = mechanism;
	}

	public byte[] getMechParaLen() {
		return mechParaLen;
	}

	public void setMechParaLen(byte[] mechParaLen) {
		this.mechParaLen = mechParaLen;
	}

	public byte[] getSignLen() {
		return signLen;
	}

	public void setSignLen(byte[] signLen) {
		this.signLen = signLen;
	}

	public byte[] getOutputType() {
		return outputType;
	}

	public void setOutputType(byte[] outputType) {
		this.outputType = outputType;
	}

	public byte[] getInputDataLen() {
		return inputDataLen;
	}

	public void setInputDataLen(byte[] inputDataLen) {
		this.inputDataLen = inputDataLen;
	}

	public int getMechPara_Len() {
		return mechPara_Len;
	}

	public void setMechPara_Len(int mechPara_Len) {
		this.mechPara_Len = mechPara_Len;
	}

	public int getInputData_Len() {
		return inputData_Len;
	}

	public void setInputData_Len(int inputData_Len) {
		this.inputData_Len = inputData_Len;
	}

	public int getSign_Len() {
		return sign_Len;
	}

	public void setSign_Len(int sign_Len) {
		this.sign_Len = sign_Len;
	}

	public byte[] getOutputDataTo() {
		return outputDataTo;
	}

	public void setOutputDataTo(byte[] outputDataTo) {
		this.outputDataTo = outputDataTo;
	}

	public byte[] getOutputSignTo() {
		return outputSignTo;
	}

	public void setOutputSignTo(byte[] outputSignTo) {
		this.outputSignTo = outputSignTo;
	}

}
