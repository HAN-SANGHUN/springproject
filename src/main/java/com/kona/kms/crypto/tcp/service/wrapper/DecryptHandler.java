package com.kona.kms.crypto.tcp.service.wrapper;

import java.nio.ByteBuffer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.kona.kms.KMSCode;
import com.kona.kms.KmsException;
import com.kona.kms.crypto.KeyAttributes;
import com.kona.kms.crypto.tcp.model.MSGDecryptModel;
import com.kona.kms.crypto.tcp.model.MSGHeaderModel;
import com.kona.kms.crypto.tcp.model.OutputModel;
import com.kona.kms.crypto.tcp.model.ServerMsgModel;
import com.kona.kms.crypto.tcp.service.controller.Reactor;
import com.kona.kms.crypto.tcp.service.implement.KmsTCPCryptoService;
import com.kona.kms.crypto.tcp.service.implement.KmsTCPCryptoServiceImpl;
import com.kona.kms.crypto.tcp.utility.MSGModelConstant;
import com.kona.kms.crypto.tcp.utility.ResponseCode;
import com.kona.kms.utils.Util;

public class DecryptHandler implements HandlerInterface {
	private static final Logger logger = LoggerFactory.getLogger(DecryptHandler.class);
	
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
		OutputModel	outputModel		= new OutputModel();
		String resCodeString = ResponseCode.KMS_RESPONSE_SUCCESS;
		String mechParaString;
		String[] mechParaArray = new String[1];
		byte[] inputData = null;
		byte[] outputData = null;
		String outputDataHex;
		int loop;
		
		KeyAttributes keyAttributes;
		
		Integer outputDataLenInt;
		String outputDataLentemp;
		byte[] outputDataLenArray;
		ServerMsgModel serverMsgModel;
		
		MSGDecryptModel msgDecryptModel = new MSGDecryptModel(msgBodyBuffer);
		KmsTCPCryptoService kmsCryptoService = new KmsTCPCryptoServiceImpl();
		
		if(msgDecryptModel.getMechParaString() == null){
			mechParaArray = null;
		} else{
			mechParaString = msgDecryptModel.getMechParaString();
			mechParaArray[0] = mechParaString;
		}
		
		inputData = msgDecryptModel.getOutputDataTo();
		
		keyAttributes = msgDecryptModel.getKeyAttributes();
		
		try{
			outputData = kmsCryptoService.C_Decrypt(msgDecryptModel.getMechanismString(), mechParaArray, msgDecryptModel.getKeyLabelString(), inputData, keyAttributes);
			outputDataHex = Util.byteArray2hexString(outputData);
			outputDataString = outputDataHex;
			
		}catch(KmsException e){
			resCodeString = e.getMsgcode();
			logger.error(e.toString());
			e.printStackTrace();
			outputDataString = "";
			throw new KmsException(KMSCode.KR_ERROR);
		}catch(Exception ex){
			resCodeString = "2222";
			ex.printStackTrace();
			outputDataString = "";
			throw new KmsException(KMSCode.KR_ERROR);
		}
		
		msgBodyBuffer.clear();
				
		outputDataLenInt = outputDataString.length();
		outputDataLentemp = outputDataLenInt.toString();
		loop = outputDataLentemp.length();
		for (int i=0; i < MSGModelConstant.MSG_FIELD_LEN - loop; i++){
			outputDataLentemp = "0" + outputDataLentemp;
		}
		
		outputDataLenArray = outputDataLentemp.getBytes();
		outputBufferSize = MSGModelConstant.HEADER_TOTAL_LEN + MSGModelConstant.BODY_OUT_TYPE_LEN + MSGModelConstant.BODY_OUT_DATA_LEN + outputDataString.length();
				
		String totalSize = String.format("%04d", outputBufferSize);
		outputModel.setTotalLen(totalSize.getBytes());
		
//		outputModel.setTotalLen(msgHeaderModel.getTotalLenString().getBytes());
		outputModel.setTrCode(msgHeaderModel.getTrCodeString().getBytes());
		outputModel.setResCode(resCodeString.getBytes());
		outputModel.setReserved(msgHeaderModel.getReservedString().getBytes());
		outputModel.setOutputType(msgDecryptModel.getOutputType());
		outputModel.setOutputLen(outputDataLenArray);
		outputModel.setOutPutData(outputData);
		outputModel.setOutPutSize(outputBufferSize);
		
		serverMsgModel = new ServerMsgModel(outputModel);
		outputModel.setServerMsg(serverMsgModel.getServerMsg());
		
		msgBodyBuffer.clear();
		
		return outputModel;
	}
}
