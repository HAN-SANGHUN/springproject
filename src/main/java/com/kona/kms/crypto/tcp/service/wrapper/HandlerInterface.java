package com.kona.kms.crypto.tcp.service.wrapper;

import java.nio.ByteBuffer;

import com.kona.kms.KmsException;
import com.kona.kms.crypto.tcp.model.MSGHeaderModel;
import com.kona.kms.crypto.tcp.model.OutputModel;


public interface HandlerInterface {

	public OutputModel  processRequest(MSGHeaderModel msgHeaderModel, ByteBuffer msgBodyBuffer) throws KmsException;
	
}
