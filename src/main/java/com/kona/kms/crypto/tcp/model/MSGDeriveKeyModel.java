package com.kona.kms.crypto.tcp.model;

import java.nio.ByteBuffer;

import com.kona.kms.KMSCode;
import com.kona.kms.KmsException;
import com.kona.kms.crypto.KeyAttributes;
import com.kona.kms.crypto.tcp.keyAttrisModel.KeyAttrisDeriveKeyPubModel;
import com.kona.kms.crypto.tcp.keyAttrisModel.KeyAttrisGenKeyModel;
import com.kona.kms.crypto.tcp.keyAttrisModel.KeyAttrisGenKeyPairModel;
import com.kona.kms.crypto.tcp.keyAttrisModel.KeyAttrisUnwrapKeyPriModel;
import com.kona.kms.crypto.tcp.utility.MSGModelConstant;

public class MSGDeriveKeyModel {

	private ByteBuffer msgBodyBuffer = null;
	private int msgBodyLen;
	private Integer keyAttrisLenInt;

	private String inputTypeString;
	private String baseKeyLabelString;
	private String mechanismString;
	private String mechParaLenString;
	private String mechParaString;
	private String outputTypeString;
	private KeyAttributes keyAttributes;

	private byte[] inputType = new byte[MSGModelConstant.BODY_IN_TYPE_LEN];
	private byte[] baseKeyLabel = new byte[MSGModelConstant.BODY_KEYLABEL_LEN];
	private byte[] mechanism = new byte[MSGModelConstant.BODY_MECH_LEN];
	private byte[] mechParaLen = new byte[MSGModelConstant.BODY_MECH_PARA_LEN_LEN];
	private byte[] outputType = new byte[MSGModelConstant.BODY_OUT_TYPE_LEN];
	private byte[] keyAttrisArray;
	
	private int mechPara_Len;
	
	private byte[] keyAttrisLen = new byte[MSGModelConstant.MSG_FIELD_LEN];
		

	public MSGDeriveKeyModel(ByteBuffer msgBodyBuffer) throws KmsException {
		this.msgBodyBuffer = msgBodyBuffer;
		parseMsgBoday(msgBodyBuffer);
	}

	public void parseMsgBoday(ByteBuffer msgHeadBuffer) throws KmsException {
		
		try{
			msgBodyBuffer.get(inputType, 0, MSGModelConstant.BODY_IN_TYPE_LEN);
			inputTypeString = new String(inputType);
			
			msgBodyBuffer.get(baseKeyLabel, 0, MSGModelConstant.BODY_KEYLABEL_LEN);
			String baseKeyLabelStringTemp = new String(baseKeyLabel);
			baseKeyLabelString = baseKeyLabelStringTemp.trim();
			
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
				
			}else if(inputTypeString.equalsIgnoreCase("U")){
				msgBodyBuffer.get(keyAttrisLen, 0, MSGModelConstant.MSG_FIELD_LEN);
				String keyAttrisLenStr = new String(keyAttrisLen);
				keyAttrisLenInt = Integer.parseInt(keyAttrisLenStr);
				
				keyAttrisArray = new byte[keyAttrisLenInt];
				
				msgBodyBuffer.get(keyAttrisArray, 0, keyAttrisLenInt);
				
				KeyAttrisDeriveKeyPubModel keyAttrisDeriveKeyPriModel = new KeyAttrisDeriveKeyPubModel(keyAttrisArray);
				keyAttributes = keyAttrisDeriveKeyPriModel.getPubKeyAttributes();
				
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

	public byte[] getMechParaLen() {
		return mechParaLen;
	}

	public void setMechParaLen(byte[] mechParaLen) {
		this.mechParaLen = mechParaLen;
	}

	public int getMechPara_Len() {
		return mechPara_Len;
	}

	public void setMechPara_Len(int mechPara_Len) {
		this.mechPara_Len = mechPara_Len;
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

	public String getBaseKeyLabelString() {
		return baseKeyLabelString;
	}

	public void setBaseKeyLabelString(String baseKeyLabelString) {
		this.baseKeyLabelString = baseKeyLabelString;
	}

	public byte[] getBaseKeyLabel() {
		return baseKeyLabel;
	}

	public void setBaseKeyLabel(byte[] baseKeyLabel) {
		this.baseKeyLabel = baseKeyLabel;
	}

	
	

}
