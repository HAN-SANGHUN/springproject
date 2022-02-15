package com.kona.kms.crypto.tcp.service.wrapper;

import java.nio.ByteBuffer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.kona.kms.KMSCode;
import com.kona.kms.KmsException;
import com.kona.kms.crypto.tcp.model.MSGDestroyKeyModel;
import com.kona.kms.crypto.tcp.model.MSGHeaderModel;
import com.kona.kms.crypto.tcp.model.OutputModel;
import com.kona.kms.crypto.tcp.model.ServerMsgModel;
import com.kona.kms.crypto.tcp.service.controller.Reactor;
import com.kona.kms.crypto.tcp.service.implement.KmsTCPCryptoService;
import com.kona.kms.crypto.tcp.service.implement.KmsTCPCryptoServiceImpl;
import com.kona.kms.crypto.tcp.utility.MSGModelConstant;
import com.kona.kms.crypto.tcp.utility.ResponseCode;
import com.kona.kms.utils.Util;

public class DestroyKeyHandler implements HandlerInterface {
	private static final Logger logger = LoggerFactory.getLogger(DestroyKeyHandler.class);
	
	/**
	 * 
	 * 1) Parse requese message; 2) call API; 3) generate response message. 
	 * @param msgHeaderModel
	 * @param msgBodyBuffer
	 * @throws KmsException
	 */
	synchronized public OutputModel processRequest(MSGHeaderModel msgHeaderModel, ByteBuffer msgBodyBuffer) throws KmsException{
		
		int outputBufferSize = 0;
		OutputModel	outputModel		= new OutputModel();
		String resCodeString = ResponseCode.KMS_RESPONSE_SUCCESS;
		byte[] outputDataLenArray;
		
		ServerMsgModel serverMsgModel;
		
		MSGDestroyKeyModel msgDestroyKeyModel = new MSGDestroyKeyModel(msgBodyBuffer);
		KmsTCPCryptoService kmsCryptoService = new KmsTCPCryptoServiceImpl();
		
		try{
			kmsCryptoService.C_DestroyKey(msgDestroyKeyModel.getKeyLabelString());
		}catch(KmsException e){
//			resCodeString = ResponseCode.KMS_RESPONSE_FAIL;
			resCodeString = e.getMsgcode();
			logger.error(e.toString());
			e.printStackTrace();
//			return null;
			throw new KmsException(KMSCode.KR_ERROR);
		}catch(Exception ex){
			resCodeString = "2222";
			ex.printStackTrace();
			throw new KmsException(KMSCode.KR_ERROR);
		}
		
		msgBodyBuffer.clear();
				
		outputDataLenArray = "0000".getBytes();
		outputBufferSize = MSGModelConstant.HEADER_TOTAL_LEN + MSGModelConstant.BODY_OUT_TYPE_LEN + MSGModelConstant.BODY_OUT_DATA_LEN + 0;
				
		String totalSize = String.format("%04d", outputBufferSize);
		outputModel.setTotalLen(totalSize.getBytes());
		
//		outputModel.setTotalLen(msgHeaderModel.getTotalLenString().getBytes());
		outputModel.setTrCode(msgHeaderModel.getTrCodeString().getBytes());
		outputModel.setResCode(resCodeString.getBytes());
		outputModel.setReserved(msgHeaderModel.getReservedString().getBytes());
//		outputModel.setOutputType(msgDestroyKeyModel.getOutputType());
		outputModel.setOutputType("V".getBytes());
		outputModel.setOutputLen(outputDataLenArray);
//		outputModel.setOutPutData(outputData);
		outputModel.setOutPutSize(outputBufferSize);
		
		serverMsgModel = new ServerMsgModel(outputModel);
		outputModel.setServerMsg(serverMsgModel.getServerMsg());
		
		msgBodyBuffer.clear();
		
		return outputModel;
	}
}
