package com.kona.kms.crypto.tcp.service.wrapper;

import java.nio.ByteBuffer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.kona.kms.KMSCode;
import com.kona.kms.KmsException;
import com.kona.kms.crypto.KeyAttributes;
import com.kona.kms.crypto.tcp.keyAttrisModel.ImportPriKeyAttrisModel;
import com.kona.kms.crypto.tcp.model.MSGHeaderModel;
import com.kona.kms.crypto.tcp.model.MSGImportPriKeyModel;
import com.kona.kms.crypto.tcp.model.OutputModel;
import com.kona.kms.crypto.tcp.model.ServerMsgModel;
import com.kona.kms.crypto.tcp.service.controller.Reactor;
import com.kona.kms.crypto.tcp.service.implement.KmsTCPCryptoService;
import com.kona.kms.crypto.tcp.service.implement.KmsTCPCryptoServiceImpl;
import com.kona.kms.crypto.tcp.utility.MSGModelConstant;
import com.kona.kms.crypto.tcp.utility.ResponseCode;
import com.kona.kms.utils.Util;

public class ImportPriKeyHandler implements HandlerInterface {
	private static final Logger logger = LoggerFactory.getLogger(ImportPriKeyHandler.class);
	
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
		OutputModel	outputModel	= new OutputModel();
		String resCodeString = ResponseCode.KMS_RESPONSE_SUCCESS;
		KeyAttributes keyAttributes;
		KeyAttributes keyAttributesOut;
		
		byte[] outputData = null;
		String outputDataHex;
		int loop;
		
		Integer outputDataLenInt;
		String outputDataLentemp;
		byte[] outputDataLenArray;
		ServerMsgModel serverMsgModel;
		
		
		byte[] keyAttributesArray;
		Integer keyAttributesArrayLenInt;
		
		
		MSGImportPriKeyModel msgImportPriKeyModel = new MSGImportPriKeyModel(msgBodyBuffer);
		KmsTCPCryptoService kmsCryptoService = new KmsTCPCryptoServiceImpl();
		
		keyAttributes = msgImportPriKeyModel.getKeyAttributes();
		
		
		try{
			keyAttributesOut = kmsCryptoService.ImportPrivateKey(msgImportPriKeyModel.getTokenLabelString(), msgImportPriKeyModel.getMechanismString(), keyAttributes);
			
		}catch(KmsException e){
			resCodeString = e.getMsgcode();
			logger.error(e.toString());
			e.printStackTrace();
			outputDataString = "";
			keyAttributesOut = new KeyAttributes();
			throw new KmsException(KMSCode.KR_ERROR);
		}catch(Exception ex){
			resCodeString = "2222";
			ex.printStackTrace();
			outputDataString = "";
			keyAttributesOut = new KeyAttributes();
			throw new KmsException(KMSCode.KR_ERROR);
		}
		
		ImportPriKeyAttrisModel importPriKeyAttrisModel = new ImportPriKeyAttrisModel(keyAttributesOut);
		
		keyAttributesArray = importPriKeyAttrisModel.getKeyAttrisArray();
		keyAttributesArrayLenInt = importPriKeyAttrisModel.getKeyAttrisLen();
		
		msgBodyBuffer.clear();
				
//		outputDataLenInt = keyAttributesArrayLenInt;
		if(keyAttributesArray != null){
			outputDataLenInt = keyAttributesArray.length * 2;	
		}else{
			outputDataLenInt = 0;
		}
		
		outputDataLentemp = outputDataLenInt.toString();
		loop = outputDataLentemp.length();
		for (int i=0; i < MSGModelConstant.MSG_FIELD_LEN - loop; i++){
			outputDataLentemp = "0" + outputDataLentemp;
		}
		
		outputDataLenArray = outputDataLentemp.getBytes();
		outputBufferSize = MSGModelConstant.HEADER_TOTAL_LEN + MSGModelConstant.BODY_OUT_TYPE_LEN + MSGModelConstant.BODY_OUT_DATA_LEN + outputDataLenInt;
		outputData = keyAttributesArray;
		
		String totalSize = String.format("%04d", outputBufferSize);
		outputModel.setTotalLen(totalSize.getBytes());
		
//		outputModel.setTotalLen(msgHeaderModel.getTotalLenString().getBytes());
		outputModel.setTrCode(msgHeaderModel.getTrCodeString().getBytes());
		outputModel.setResCode(resCodeString.getBytes());
		outputModel.setReserved(msgHeaderModel.getReservedString().getBytes());
		outputModel.setOutputType(msgImportPriKeyModel.getOutputType());
		outputModel.setOutputLen(outputDataLenArray);
		outputModel.setOutPutData(outputData);
		outputModel.setOutPutSize(outputBufferSize);
		
		serverMsgModel = new ServerMsgModel(outputModel);
		outputModel.setServerMsg(serverMsgModel.getServerMsg());
		
		return outputModel;
	}
}
