package com.kona.kms.crypto.tcp.model;

import java.nio.ByteBuffer;

public class MSGWrapModel {

	private int inTypeLen = 1;
	private int wrapKeyLabelLen = 256;
	private int keyLabelLen = 256;
	private int mechLen = 32;
	private int mechParaLenLen = 4;
	private int outTypeLen = 1;

	private byte[] inputType = null;
	private byte[] wrapKeyLabel = null;
	private byte[] keyLabel = null;
	private byte[] mechanism = null;
	private byte[] mechParaLen = null;
	private byte[] mechPara = null;
	private byte[] outputType = null;

	public byte[] getInputType(ByteBuffer msgBodyBuffer) {
		msgBodyBuffer.get(inputType, 0, inTypeLen);
		return inputType;
	}

	public byte[] getWrapKeyLabel(ByteBuffer msgBodyBuffer) {
		msgBodyBuffer.get(wrapKeyLabel, inTypeLen, wrapKeyLabelLen);
		return wrapKeyLabel;
	}

	public byte[] getKeyLabel(ByteBuffer msgBodyBuffer) {
		msgBodyBuffer.get(keyLabel, inTypeLen + wrapKeyLabelLen, keyLabelLen);
		return keyLabel;
	}

	public byte[] getMechanism(ByteBuffer msgBodyBuffer) {
		msgBodyBuffer.get(mechanism, inTypeLen + wrapKeyLabelLen + keyLabelLen, mechLen);
		return mechanism;
	}

	public byte[] getMechParaLen(ByteBuffer msgBodyBuffer) {
		msgBodyBuffer.get(mechanism, inTypeLen + wrapKeyLabelLen + keyLabelLen + mechLen, mechParaLenLen);
		return mechParaLen;
	}

	public byte[] getMechPara(ByteBuffer msgBodyBuffer) {
		msgBodyBuffer.get(mechPara, inTypeLen + wrapKeyLabelLen + keyLabelLen + mechLen + mechParaLenLen, Integer.parseInt(mechParaLen.toString()));
		return mechPara;
	}

	public byte[] getOutputType(ByteBuffer msgBodyBuffer) {
		msgBodyBuffer.get(outputType, inTypeLen + wrapKeyLabelLen + keyLabelLen + mechLen + mechParaLenLen + Integer.parseInt(mechParaLen.toString()), outTypeLen);
		return outputType;
	}

}
