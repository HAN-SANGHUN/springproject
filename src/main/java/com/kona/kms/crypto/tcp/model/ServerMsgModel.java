package com.kona.kms.crypto.tcp.model;

import com.kona.kms.KMSCode;
import com.kona.kms.KmsException;
import com.kona.kms.crypto.tcp.utility.MSGModelConstant;

public class ServerMsgModel {

	private byte[] serverMsg = null;

	public ServerMsgModel(OutputModel outputModel) throws KmsException {
		generateServerMsg(outputModel);
	}

	public void generateServerMsg(OutputModel outputModel) throws KmsException {

		byte[] serverMsgTemp = new byte[outputModel.getOutPutSize()];

		// System.out.println("outputModel.getOutPutSize(): " +
		// outputModel.getOutPutSize());
		
		
		String totalLenString_1 = new  String(outputModel.getTotalLen());
//		System.out.println("outputModel.getTotalLen: " + totalLenString_1);

		try {
			System.arraycopy(outputModel.getTotalLen(), 0, serverMsgTemp, 0, MSGModelConstant.TR_TOTAL_LEN);
			System.arraycopy(outputModel.getTrCode(), 0, serverMsgTemp, MSGModelConstant.TR_TOTAL_LEN, MSGModelConstant.TR_CODE_LEN);
			System.arraycopy(outputModel.getResCode(), 0, serverMsgTemp, MSGModelConstant.TR_TOTAL_LEN + MSGModelConstant.TR_CODE_LEN, MSGModelConstant.TR_RES_LEN);
			System.arraycopy(outputModel.getReserved(), 0, serverMsgTemp, MSGModelConstant.TR_TOTAL_LEN + MSGModelConstant.TR_CODE_LEN + MSGModelConstant.TR_RES_LEN, MSGModelConstant.TR_FILLER_LEN);
			System.arraycopy(outputModel.getOutputType(), 0, serverMsgTemp, MSGModelConstant.TR_TOTAL_LEN + MSGModelConstant.TR_CODE_LEN + MSGModelConstant.TR_RES_LEN + MSGModelConstant.TR_FILLER_LEN, MSGModelConstant.BODY_OUT_TYPE_LEN);
			System.arraycopy(outputModel.getOutputLen(), 0, serverMsgTemp, MSGModelConstant.TR_TOTAL_LEN + MSGModelConstant.TR_CODE_LEN + MSGModelConstant.TR_RES_LEN + MSGModelConstant.TR_FILLER_LEN + MSGModelConstant.BODY_OUT_TYPE_LEN, MSGModelConstant.BODY_OUT_DATA_LEN);
	
			String outputLenString = new String(outputModel.getOutputLen()); //data Length
			// System.out.println("OutputModel::generateServerMsg()::outputLenString: "
			// + Integer.parseInt(outputLenString));
			// System.out.println("outputModel.getOutPutData()" +
			// outputModel.getOutPutData());
			
//			System.out.println("outputLenString: " + outputLenString);
	
			if (Integer.parseInt(outputLenString) != 0) {
				System.arraycopy(outputModel.getOutPutData(), 0, serverMsgTemp, MSGModelConstant.TR_TOTAL_LEN + MSGModelConstant.TR_CODE_LEN + MSGModelConstant.TR_RES_LEN + MSGModelConstant.TR_FILLER_LEN + MSGModelConstant.BODY_OUT_TYPE_LEN + MSGModelConstant.BODY_OUT_DATA_LEN, Integer.parseInt(outputLenString) / 2);
				
			}
			serverMsg = serverMsgTemp;
		} catch (Exception e){
			e.printStackTrace();
			throw new KmsException(KMSCode.KR_PARAMETER_INVALID);
		}

	}

	public byte[] getServerMsg() {
		return serverMsg;
	}

	public void setServerMsg(byte[] serverMsg) {
		this.serverMsg = serverMsg;
	}

}
