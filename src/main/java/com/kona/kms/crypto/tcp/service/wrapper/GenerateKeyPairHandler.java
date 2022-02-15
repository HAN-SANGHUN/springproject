package com.kona.kms.crypto.tcp.service.wrapper;

import java.nio.ByteBuffer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.kona.kms.KMSCode;
import com.kona.kms.KmsException;
import com.kona.kms.crypto.KeyAttributes;
import com.kona.kms.crypto.tcp.keyAttrisModel.GenKeyAttrisModel;
import com.kona.kms.crypto.tcp.keyAttrisModel.GenPriKeyAttrisModel;
import com.kona.kms.crypto.tcp.keyAttrisModel.GenPubKeyAttrisModel;

import com.kona.kms.crypto.tcp.model.MSGGenerateKeyPairModel;
import com.kona.kms.crypto.tcp.model.MSGHeaderModel;
import com.kona.kms.crypto.tcp.model.OutputModel;
import com.kona.kms.crypto.tcp.model.ServerMsgModel;
import com.kona.kms.crypto.tcp.service.controller.Reactor;
import com.kona.kms.crypto.tcp.service.implement.KmsTCPCryptoService;
import com.kona.kms.crypto.tcp.service.implement.KmsTCPCryptoServiceImpl;
import com.kona.kms.crypto.tcp.utility.MSGModelConstant;
import com.kona.kms.crypto.tcp.utility.ResponseCode;
import com.kona.kms.utils.Util;

public class GenerateKeyPairHandler implements HandlerInterface {
	private static final Logger logger = LoggerFactory.getLogger(GenerateKeyPairHandler.class);
	
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
		
		KeyAttributes pubKeyAttributes;
		KeyAttributes priKeyAttributes;
		KeyAttributes[] pairKeyAttributesOut;
		KeyAttributes pubKeyAttributesOut;
		KeyAttributes priKeyAttributesOut;
		
		byte[] outputData = null;
		String outputDataHex;
		int loop;
		
		Integer outputDataLenInt;
		String outputDataLentemp;
		byte[] outputDataLenArray;
		ServerMsgModel serverMsgModel;
		
		
		byte[] pairKeyAttributesArray;
		Integer pairKeyAttributesArrayLenInt;
		
		byte[] pubKeyAttributesArray;
		Integer pubKeyAttributesArrayLenInt;
		
		byte[] priKeyAttributesArray;
		Integer priKeyAttributesArrayLenInt;
		
		
		MSGGenerateKeyPairModel msgGenerateKeyPairModel = new MSGGenerateKeyPairModel(msgBodyBuffer);
		KmsTCPCryptoService kmsCryptoService = new KmsTCPCryptoServiceImpl();
		
		pubKeyAttributes = msgGenerateKeyPairModel.getPubKeyAttributes();
		priKeyAttributes = msgGenerateKeyPairModel.getPriKeyAttributes();
		
		
		try{
			pairKeyAttributesOut = kmsCryptoService.GenerateKeyPair(msgGenerateKeyPairModel.getTokenLabelString(), msgGenerateKeyPairModel.getMechanismString(), pubKeyAttributes, priKeyAttributes);
			
		}catch(KmsException e){
			resCodeString = e.getMsgcode();
			logger.error(e.toString());
			e.printStackTrace();
			outputDataString = "";
			pairKeyAttributesOut = new KeyAttributes[2];
			pairKeyAttributesOut[0] = new KeyAttributes();
			pairKeyAttributesOut[1] = new KeyAttributes();
			throw new KmsException(KMSCode.KR_ERROR);
		}catch(Exception ex){
			resCodeString = "2222";
			ex.printStackTrace();
			outputDataString = "";
			pairKeyAttributesOut = new KeyAttributes[2];
			pairKeyAttributesOut[0] = new KeyAttributes();
			pairKeyAttributesOut[1] = new KeyAttributes();
			throw new KmsException(KMSCode.KR_ERROR);
		}
		
		pubKeyAttributesOut = pairKeyAttributesOut[0];
		priKeyAttributesOut = pairKeyAttributesOut[1];
				
		GenPubKeyAttrisModel genPubKeyAttrisModel = new GenPubKeyAttrisModel(pubKeyAttributesOut);
		GenPriKeyAttrisModel genPriKeyAttrisModel = new GenPriKeyAttrisModel(priKeyAttributesOut);
		
		pubKeyAttributesArray = genPubKeyAttrisModel.getKeyAttrisArray();
		pubKeyAttributesArrayLenInt = genPubKeyAttrisModel.getKeyAttrisLen();
		
		priKeyAttributesArray = genPriKeyAttrisModel.getKeyAttrisArray();
		priKeyAttributesArrayLenInt = genPriKeyAttrisModel.getKeyAttrisLen();
		
		
		pairKeyAttributesArrayLenInt = pubKeyAttributesArrayLenInt + priKeyAttributesArrayLenInt;
		pairKeyAttributesArray = new byte[pairKeyAttributesArrayLenInt];
		System.arraycopy(pubKeyAttributesArray, 0, pairKeyAttributesArray, 0, pubKeyAttributesArrayLenInt);
		System.arraycopy(priKeyAttributesArray, 0, pairKeyAttributesArray, pubKeyAttributesArrayLenInt, priKeyAttributesArrayLenInt);
		
		msgBodyBuffer.clear();
				
//		outputDataLenInt = keyAttributesArrayLenInt;
		outputDataLenInt = pairKeyAttributesArrayLenInt * 2; //******* here may be problem. due to the length of hexstring of some field in key attributes.
		outputDataLentemp = outputDataLenInt.toString();
		loop = outputDataLentemp.length();
		for (int i=0; i < MSGModelConstant.MSG_FIELD_LEN - loop; i++){
			outputDataLentemp = "0" + outputDataLentemp;
		}
		outputDataLenArray = outputDataLentemp.getBytes();
		outputBufferSize = MSGModelConstant.HEADER_TOTAL_LEN + MSGModelConstant.BODY_OUT_TYPE_LEN + MSGModelConstant.BODY_OUT_DATA_LEN + outputDataLenInt;
		outputData = pairKeyAttributesArray;
			
		String totalSize = String.format("%04d", outputBufferSize);
		outputModel.setTotalLen(totalSize.getBytes());
		
//		outputModel.setTotalLen(msgHeaderModel.getTotalLenString().getBytes());
		outputModel.setTrCode(msgHeaderModel.getTrCodeString().getBytes());
		outputModel.setResCode(resCodeString.getBytes());
		outputModel.setReserved(msgHeaderModel.getReservedString().getBytes());
		outputModel.setOutputType(msgGenerateKeyPairModel.getOutputType());
		outputModel.setOutputLen(outputDataLenArray);
		outputModel.setOutPutData(outputData);
		outputModel.setOutPutSize(outputBufferSize);
		
		serverMsgModel = new ServerMsgModel(outputModel);
		outputModel.setServerMsg(serverMsgModel.getServerMsg());
		
		return outputModel;
	}
}
