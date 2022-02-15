package com.kona.kms.crypto.tcp.service.wrapper;

import java.nio.ByteBuffer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.kona.kms.KMSCode;
import com.kona.kms.KmsException;
import com.kona.kms.crypto.KeyAttributes;
import com.kona.kms.crypto.tcp.model.MSGEncryptModel;
import com.kona.kms.crypto.tcp.model.MSGGenerateKeyModel;
import com.kona.kms.crypto.tcp.model.MSGHeaderModel;
import com.kona.kms.crypto.tcp.model.OutputModel;
import com.kona.kms.crypto.tcp.model.ServerMsgModel;
import com.kona.kms.crypto.tcp.service.implement.*;
import com.kona.kms.crypto.tcp.utility.MSGModelConstant;
import com.kona.kms.crypto.tcp.utility.ResponseCode;
import com.kona.kms.utils.Util;

public class EncryptHandler implements HandlerInterface{
	private static final Logger logger = LoggerFactory.getLogger(EncryptHandler.class);
	
	/**
	 * 
	 * 1) Parse requese message; 2) call API; 3) generate response message. 
	 * @param msgHeaderModel
	 * @param msgBodyBuffer
	 * @throws KmsException
	 */
	synchronized public OutputModel processRequest(MSGHeaderModel msgHeaderModel, ByteBuffer msgBodyBuffer) throws KmsException{
		
		int outputBufferSize = 0;
		String outputDataString = null;
		int loop;
		KeyAttributes keyAttributes;
		
		//Request Message parsing
		MSGEncryptModel 	msgEncryptModel 	= new MSGEncryptModel(msgBodyBuffer);
		KmsTCPCryptoService kmsCryptoService 	= new KmsTCPCryptoServiceImpl();
		OutputModel	outputModel		= new OutputModel();
		
		if(msgHeaderModel == null ||	msgBodyBuffer == null ){
			logger.debug("msgHeaderModel == null ||	msgBodyBuffer == null");
//			return null;
		}
		
		String[] mechParaArray = new String[1];
		
		if(msgEncryptModel.getMechParaString() == null){
			mechParaArray = null;
		} else{
			String mechParaString = msgEncryptModel.getMechParaString();
//			String[] mechParaArray = new String[1];
			mechParaArray[0] = mechParaString;
//			mechParaArray = mechParaString.split("");
		}
		
		byte[] inputData 	= msgEncryptModel.getOutputDataTo();
		byte[] outputData 	= null;
		String resCodeString = ResponseCode.KMS_RESPONSE_SUCCESS;
		
		
		keyAttributes = msgEncryptModel.getKeyAttributes();
		
		try{
			outputData	= kmsCryptoService.C_Encrypt(msgEncryptModel.getMechanismString(), mechParaArray, msgEncryptModel.getKeyLabelString(), inputData, keyAttributes);

			String outputData1 = Util.byteArray2hexString(outputData);
			outputDataString = outputData1;
		}catch(KmsException e){
			resCodeString = e.getMsgcode();
			logger.error(e.toString());
			e.printStackTrace();
			outputDataString = "";
			//add 20150902
			throw new KmsException(KMSCode.KR_ERROR);
		}catch(Exception ex){
			resCodeString = "2222";
			ex.printStackTrace();
			outputDataString = "";
			//add 20150902
			throw new KmsException(KMSCode.KR_ERROR);
		}
		
		msgBodyBuffer.clear();
	
		Integer outputDataLenInt = outputDataString.length();
		String outputDataLentemp = outputDataLenInt.toString();

		loop = outputDataLentemp.length();
		for (int i=0; i < MSGModelConstant.MSG_FIELD_LEN - loop; i++){
			outputDataLentemp = "0" + outputDataLentemp;
		}
		
		byte[] outputDataLenArray = outputDataLentemp.getBytes();
		
		outputBufferSize = MSGModelConstant.HEADER_TOTAL_LEN + MSGModelConstant.BODY_OUT_TYPE_LEN + MSGModelConstant.BODY_OUT_DATA_LEN + outputDataString.length();
		
		String totalSize = String.format("%04d", outputBufferSize);
		
		logger.debug("totalSize" + totalSize);
		
		outputModel.setTotalLen(totalSize.getBytes());
		
//		outputModel.setTotalLen(msgHeaderModel.getTotalLenString().getBytes());
		outputModel.setTrCode(msgHeaderModel.getTrCodeString().getBytes());
//		outputModel.setResCode(msgHeaderModel.getResCodeString().getBytes());
		outputModel.setResCode(resCodeString.getBytes());
		outputModel.setReserved(msgHeaderModel.getReservedString().getBytes());
		outputModel.setOutputType(msgEncryptModel.getOutputType());
		outputModel.setOutputLen(outputDataLenArray);
		outputModel.setOutPutData(outputData);
		//outputModel.setOutPutData(outputString);
		outputModel.setOutPutSize(outputBufferSize);
		
		ServerMsgModel serverMsgModel = new ServerMsgModel(outputModel);
		
		outputModel.setServerMsg(serverMsgModel.getServerMsg());
		msgBodyBuffer.clear();
		
		return outputModel;
	}
}
