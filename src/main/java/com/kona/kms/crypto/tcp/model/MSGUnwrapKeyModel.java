package com.kona.kms.crypto.tcp.model;

import java.nio.ByteBuffer;

import com.kona.kms.KMSCode;
import com.kona.kms.KmsException;
import com.kona.kms.crypto.KeyAttributes;
import com.kona.kms.crypto.tcp.keyAttrisModel.KeyAttrisGenKeyModel;
import com.kona.kms.crypto.tcp.keyAttrisModel.KeyAttrisGenKeyPairModel;
import com.kona.kms.crypto.tcp.keyAttrisModel.KeyAttrisUnwrapKeyPriModel;
import com.kona.kms.crypto.tcp.utility.MSGModelConstant;

public class MSGUnwrapKeyModel {

	private ByteBuffer msgBodyBuffer = null;
	private int msgBodyLen;
	private Integer keyAttrisLenInt;

	private String inputTypeString;
	private String unWrappingKeyLabelString;
	private String mechanismString;
	private String mechParaLenString;
	private String mechParaString;
	private String inputDataLenString;
	private String inputDataString;
	private String outputTypeString;
	private KeyAttributes keyAttributes;
	private byte[] outputDataTo;

	private byte[] inputType = new byte[MSGModelConstant.BODY_IN_TYPE_LEN];
	private byte[] unWrappingKeyLabel = new byte[MSGModelConstant.BODY_KEYLABEL_LEN];
	private byte[] mechanism = new byte[MSGModelConstant.BODY_MECH_LEN];
	private byte[] mechParaLen = new byte[MSGModelConstant.BODY_MECH_PARA_LEN_LEN];
	private byte[] outputType = new byte[MSGModelConstant.BODY_OUT_TYPE_LEN];
	private byte[] inputDataLen = new byte[MSGModelConstant.BODY_IN_DATA_LEN_LEN];
	private byte[] keyAttrisArray;
	
	private int mechPara_Len;
	private int inputData_Len;
	
	private byte[] keyAttrisLen = new byte[MSGModelConstant.MSG_FIELD_LEN];
		

	public MSGUnwrapKeyModel(ByteBuffer msgBodyBuffer) throws KmsException {
		this.msgBodyBuffer = msgBodyBuffer;
		parseMsgBoday(msgBodyBuffer);
	}

	public void parseMsgBoday(ByteBuffer msgHeadBuffer) throws KmsException {
		
		try{
			msgBodyBuffer.get(inputType, 0, MSGModelConstant.BODY_IN_TYPE_LEN);
			inputTypeString = new String(inputType);
			
			msgBodyBuffer.get(unWrappingKeyLabel, 0, MSGModelConstant.BODY_KEYLABEL_LEN);
			String unWrappingKeyLabelStringTemp = new String(unWrappingKeyLabel);
			unWrappingKeyLabelString = unWrappingKeyLabelStringTemp.trim();
			
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
	
			msgBodyBuffer.get(inputDataLen, 0,	MSGModelConstant.BODY_IN_DATA_LEN_LEN);
			inputDataLenString = new String(inputDataLen);
			
			inputData_Len = Integer.parseInt(inputDataLenString);
			byte[] inputData = new byte[inputData_Len];
			msgBodyBuffer.get(inputData, 0, inputData_Len);
			outputDataTo = inputData;
			
			if(inputTypeString.equalsIgnoreCase("V")){
				Integer keyAttrisLen = MSGModelConstant.KEY_ATTRS_KEYCLASS_LEN+MSGModelConstant.KEY_ATTRS_KEYTYPE_LEN+MSGModelConstant.KEY_ATTRS_BOOLEAN_LEN*13+MSGModelConstant.KEY_ATTRS_KEYLABEL_LEN+MSGModelConstant.KEY_ATTRS_DATE_LEN*2+MSGModelConstant.KEY_ATTRS_KEYVAL_LEN_LEN;
				keyAttrisArray = new byte[keyAttrisLen];
		
				msgBodyBuffer.get(keyAttrisArray, 0, keyAttrisLen);
				
				KeyAttrisGenKeyModel keyAttrisGenKeyModel = new KeyAttrisGenKeyModel(keyAttrisArray);
				keyAttributes = keyAttrisGenKeyModel.getKeyAttributes();
				
			}else if(inputTypeString.equalsIgnoreCase("I")){
				msgBodyBuffer.get(keyAttrisLen, 0, MSGModelConstant.MSG_FIELD_LEN);
				String keyAttrisLenStr = new String(keyAttrisLen);
				keyAttrisLenInt = Integer.parseInt(keyAttrisLenStr);
				
				keyAttrisArray = new byte[keyAttrisLenInt];
				
				msgBodyBuffer.get(keyAttrisArray, 0, keyAttrisLenInt);
				
				KeyAttrisUnwrapKeyPriModel keyAttrisUnwrapKeyPriModel = new KeyAttrisUnwrapKeyPriModel(keyAttrisArray);
				keyAttributes = keyAttrisUnwrapKeyPriModel.getPriKeyAttributes();
				
			}else{
				throw new KmsException(KMSCode.KR_PARAMETER_INVALID);
			}
			
				
		
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

	public KeyAttributes getKeyAttributes() {
		return keyAttributes;
	}

	public void setKeyAttributes(KeyAttributes keyAttributes) {
		this.keyAttributes = keyAttributes;
	}



	public byte[] getKeyAttrisArray() {
		return keyAttrisArray;
	}

	public void setKeyAttrisArray(byte[] keyAttrisArray) {
		this.keyAttrisArray = keyAttrisArray;
	}

	public String getUnWrappingKeyLabelString() {
		return unWrappingKeyLabelString;
	}

	public void setUnWrappingKeyLabelString(String unWrappingKeyLabelString) {
		this.unWrappingKeyLabelString = unWrappingKeyLabelString;
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

	public byte[] getOutputDataTo() {
		return outputDataTo;
	}

	public void setOutputDataTo(byte[] outputDataTo) {
		this.outputDataTo = outputDataTo;
	}

	public byte[] getUnWrappingKeyLabel() {
		return unWrappingKeyLabel;
	}

	public void setUnWrappingKeyLabel(byte[] unWrappingKeyLabel) {
		this.unWrappingKeyLabel = unWrappingKeyLabel;
	}

	public byte[] getMechParaLen() {
		return mechParaLen;
	}

	public void setMechParaLen(byte[] mechParaLen) {
		this.mechParaLen = mechParaLen;
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

	public Integer getKeyAttrisLenInt() {
		return keyAttrisLenInt;
	}

	public void setKeyAttrisLenInt(Integer keyAttrisLenInt) {
		this.keyAttrisLenInt = keyAttrisLenInt;
	}

	public byte[] getKeyAttrisLen() {
		return keyAttrisLen;
	}

	public void setKeyAttrisLen(byte[] keyAttrisLen) {
		this.keyAttrisLen = keyAttrisLen;
	}

	
	

}
