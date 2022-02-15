package com.kona.kms.crypto.tcp.service.wrapper;

import java.nio.ByteBuffer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.kona.kms.KMSCode;
import com.kona.kms.KmsException;
import com.kona.kms.crypto.tcp.model.MSGHeaderModel;
import com.kona.kms.crypto.tcp.model.MSGVerifyModel;
import com.kona.kms.crypto.tcp.model.OutputModel;
import com.kona.kms.crypto.tcp.model.ServerMsgModel;
import com.kona.kms.crypto.tcp.service.implement.*;
import com.kona.kms.crypto.tcp.utility.MSGModelConstant;
import com.kona.kms.crypto.tcp.utility.ResponseCode;
import com.kona.kms.utils.Util;


public class VerifyHandler implements HandlerInterface{
	private static final Logger logger = LoggerFactory.getLogger(VerifyHandler.class);
	
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
//		byte[] outputData = null;
		String outputDataHex;
		int loop;
		byte[] signArray;
		boolean outputData = true;
		byte[] outputDataTFArray = new byte[1]; 
		int outputDataInt = 0;
		
		Integer outputDataLenInt;
		String outputDataLentemp;
		byte[] outputDataLenArray;
		ServerMsgModel serverMsgModel;
		
		MSGVerifyModel msgVerifyModel = new MSGVerifyModel(msgBodyBuffer);
		KmsTCPCryptoService kmsCryptoService = new KmsTCPCryptoServiceImpl();
		
		if(msgVerifyModel.getMechParaString() == null){
			mechParaArray = null;
		} else{
			mechParaString = msgVerifyModel.getMechParaString();
			mechParaArray[0] = mechParaString;
		}
		
		inputData 	= msgVerifyModel.getOutputDataTo();

		signArray = msgVerifyModel.getOutputSignTo();
    
		byte[] outputDataArray = new byte[1];
		
		try{
			outputData = kmsCryptoService.C_Verify(msgVerifyModel.getMechanismString(), mechParaArray, msgVerifyModel.getKeyLabelString(), inputData, signArray);
		  
			if (outputData == true){
//				outputDataInt = 1;
				outputDataArray[0] = 49;
			} else{
//				outputDataInt = 0;
				outputDataArray[0] = 48;
			}
//			outputDataHex = Util.intToHex(outputDataInt);
//			outputDataString = outputDataHex;
			
			outputDataHex = Util.byteArray2hexString(outputDataArray);
			outputDataString = outputDataHex;
			
//			System.out.println("outputDataString: " + outputDataString);
			
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
		
//		if (outputData == true){
//			outputDataTFArray[0] = '1';
//		} else if (outputData == false){
//			outputDataTFArray[0] = '0';
//		}
		
//		System.out.println("Server::outputData " + outputData);
		
	
//		byte[] outputDataTFArray = outputDataTF.getBytes();
		
		//byte[] outputDataTFArray = outputDataTF.getBytes();
		
		msgBodyBuffer.clear();
	
		outputDataLenInt = outputDataString.length();
		outputDataLentemp = outputDataLenInt.toString();
		loop = outputDataLentemp.length();
		for (int i=0; i < MSGModelConstant.MSG_FIELD_LEN - loop; i++){
			outputDataLentemp = "0" + outputDataLentemp;
		}
		
		outputDataLenArray = outputDataLentemp.getBytes();
		outputBufferSize = MSGModelConstant.HEADER_TOTAL_LEN + MSGModelConstant.BODY_OUT_TYPE_LEN + MSGModelConstant.BODY_OUT_DATA_LEN + outputDataString.length();
		
//		System.out.println("outputDataLentemp: " + outputDataLentemp);
		
		
		// 3. Encode to get the outputData.			
//		String outputDataLen 		= "0001";		
//		outputDataLenArray 	= outputDataLen.getBytes();
	
		//int bufferSize = msgVerifyModel.getOutputType().length + outputDataLenArray.length + outputDataTFArray.length;
		
		//byte[] outputToBuffer = new byte[bufferSize];
		
//		outputBufferSize = 44 + 1 + 4 + 1;
		
//		String outputStringTemp = new String(outputToBuffer);
	//	outputString = outputStringTemp;
		//System.out.println("outputString: " + outputString);
		
		//outputModel.setOutPutData(outputDataTFArray);
		
//		outputModel.setOutPutData(outputDataTFArray);
//
//		outputModel.setOutPutSize(outputDataTFArray.length);
		
		
//		outputModel.setTotalLen(msgHeaderModel.getTotalLenString().getBytes());
//		outputModel.setTrCode(msgHeaderModel.getTrCodeString().getBytes());
//		outputModel.setResCode(resCodeString.getBytes());
//		outputModel.setReserved(msgHeaderModel.getReservedString().getBytes());
//		outputModel.setOutputType(msgVerifyModel.getOutputType());
//		outputModel.setOutputLen(outputDataLenArray);
//		outputModel.setOutPutData(outputDataTFArray);
//		outputModel.setOutPutSize(outputBufferSize);
		
		String totalSize = String.format("%04d", outputBufferSize);
		outputModel.setTotalLen(totalSize.getBytes());
		
//		outputModel.setTotalLen(msgHeaderModel.getTotalLenString().getBytes());
		outputModel.setTrCode(msgHeaderModel.getTrCodeString().getBytes());
		outputModel.setResCode(resCodeString.getBytes());
		outputModel.setReserved(msgHeaderModel.getReservedString().getBytes());
		outputModel.setOutputType(msgVerifyModel.getOutputType());
		outputModel.setOutputLen(outputDataLenArray);
//		outputModel.setOutPutData(outputDataString.getBytes());
		
		outputModel.setOutPutData(outputDataArray);
		outputModel.setOutPutSize(outputBufferSize);
		
		serverMsgModel = new ServerMsgModel(outputModel);
		outputModel.setServerMsg(serverMsgModel.getServerMsg());
		
		msgBodyBuffer.clear();
		
		return outputModel;
	}
}





//package com.kona.kms.crypto.tcp.service.wrapper;
//
//import java.nio.ByteBuffer;
//
//import com.kona.kms.KmsException;
//import com.kona.kms.crypto.tcp.model.MSGHeaderModel;
//import com.kona.kms.crypto.tcp.model.MSGVerifyModel;
//import com.kona.kms.crypto.tcp.model.OutputModel;
//import com.kona.kms.crypto.tcp.service.implement.*;
//
//
//public class VerifyHandler implements HandlerInterface{
//
//	static int outputBufferSize = 0;
//	static String outputString = "";
//	
//	/**
//	 * 
//	 * @param trCodeString
//	 * @param msgBodyBuffer
//	 * @throws KmsException
//	 */
//	synchronized public OutputModel processRequest(MSGHeaderModel msgHeaderModel, ByteBuffer msgBodyBuffer){
//		
//		
//		System.out.println("-----------KMS_VERIFY Start-----------");
//		
//		OutputModel	outputModel		= new OutputModel();
//		
//		// 1. Parse the message body
//		MSGVerifyModel msgVerifyModel = new MSGVerifyModel(msgBodyBuffer);
//	
//		// 2. Process the corresponding crypto operation.
//		KmsTCPCryptoService kmsCryptoService = new KmsTCPCryptoServiceImpl();
//		String[] mechParaArray = new String[] {msgVerifyModel.getMechParaString()};
//		//byte[] inputData = msgVerifyModel.getInputDataString().getBytes();
//		byte[] inputData 	= msgVerifyModel.getOutputDataTo();
////		byte[] signArray = msgVerifyModel.getSignString().getBytes();
//		byte[] signArray = msgVerifyModel.getOutputSignTo();
//		boolean outputData = true;
//		String resCodeString = "0000";
//		
//		try{
//			outputData = kmsCryptoService.C_Verify(msgVerifyModel.getMechanismString(), mechParaArray, msgVerifyModel.getKeyLabelString(), inputData, signArray);
//		}catch(KmsException e){
//			resCodeString = "1111";
//			e.printStackTrace();
//			return null;
//		}
//		
//		System.out.println("outputData " + outputData);
//		
//		//String outputDataTF = "1";
//		
//		byte[] outputDataTFArray = new byte[1]; 
//		if (outputData == true){
//			outputDataTFArray[0] = '1';
//		} else if (outputData == false){
//			outputDataTFArray[0] = '0';
//		}
//	
////		byte[] outputDataTFArray = outputDataTF.getBytes();
//		
//		//byte[] outputDataTFArray = outputDataTF.getBytes();
//		
//		msgBodyBuffer.clear();
//	
//		// 3. Encode to get the outputData.			
//		String outputDataLen 		= "0001";		
//		byte[] outputDataLenArray 	= outputDataLen.getBytes();
//	
//		int bufferSize = msgVerifyModel.getOutputType().length + outputDataLenArray.length + outputDataTFArray.length;
//		
//		byte[] outputToBuffer = new byte[bufferSize];
//		
//		outputBufferSize = 44 + msgVerifyModel.getOutputType().length + outputDataLenArray.length + outputDataTFArray.length;
//		
//		//System.arraycopy(msgVerifyModel.getOutputType(), 0, outputToBuffer, 0, msgVerifyModel.getOutputType().length);
//		//System.arraycopy(outputDataLenArray, 0, outputToBuffer, msgVerifyModel.getOutputType().length, outputDataLenArray.length);
//		//System.arraycopy(outputData, 0, outputToBuffer, msgVerifyModel.getOutputType().length + outputDataLenArray.length, outputDataTFArray.length);
//		
//		String outputStringTemp = new String(outputToBuffer);
//		outputString = outputStringTemp;
//		System.out.println("outputString: " + outputString);
//		
//		//outputModel.setOutPutData(outputDataTFArray);
//		
////		outputModel.setOutPutData(outputDataTFArray);
////
////		outputModel.setOutPutSize(outputDataTFArray.length);
//		
//		outputModel.setTotalLen(msgHeaderModel.getTotalLenString().getBytes());
//		outputModel.setTrCode(msgHeaderModel.getTrCodeString().getBytes());
////		outputModel.setResCode(msgHeaderModel.getResCodeString().getBytes());
//		outputModel.setResCode(resCodeString.getBytes());
//		outputModel.setReserved(msgHeaderModel.getReservedString().getBytes());
//		outputModel.setOutputType(msgVerifyModel.getOutputType());
//		outputModel.setOutputType(msgVerifyModel.getOutputType());
//		outputModel.setOutPutData(outputDataTFArray);
//		//outputModel.setOutPutData(outputString);
//		outputModel.setOutPutSize(outputBufferSize);
//		
//		msgBodyBuffer.clear();
//		
//		return outputModel;
//	}
//}
