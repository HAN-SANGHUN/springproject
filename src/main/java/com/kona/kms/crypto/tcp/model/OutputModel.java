package com.kona.kms.crypto.tcp.model;

public class OutputModel {

	private byte[] totalLen = null;
	private byte[] trCode = null;
	private byte[] resCode = null;
	private byte[] reserved = null;
	private byte[] outputType = null;
	private byte[] outputLen = null;
	private byte[] outPutData = null;
	private int outPutSize = 0;

	private byte[] serverMsg = null;

	public OutputModel() {
		// this.generateServerMsg();
	}

	public byte[] getOutPutData() {
		return outPutData;
	}

	public void setOutPutData(byte[] outPutData) {
		this.outPutData = outPutData;
	}

	public int getOutPutSize() {
		return outPutSize;
	}

	public void setOutPutSize(int outPutSize) {
		this.outPutSize = outPutSize;
	}

	public byte[] getTotalLen() {
		return totalLen;
	}

	public void setTotalLen(byte[] totalLen) {
		this.totalLen = totalLen;
	}

	public byte[] getTrCode() {
		return trCode;
	}

	public void setTrCode(byte[] trCode) {
		this.trCode = trCode;
	}

	public byte[] getResCode() {
		return resCode;
	}

	public void setResCode(byte[] resCode) {
		this.resCode = resCode;
	}

	public byte[] getReserved() {
		return reserved;
	}

	public void setReserved(byte[] reserved) {
		this.reserved = reserved;
	}

	public byte[] getOutputType() {
		return outputType;
	}

	public void setOutputType(byte[] outputType) {
		this.outputType = outputType;
	}

	public byte[] getOutputLen() {
		return outputLen;
	}

	public void setOutputLen(byte[] outputLen) {
		this.outputLen = outputLen;
	}

	public byte[] getServerMsg() {
		return serverMsg;
	}

	public void setServerMsg(byte[] serverMsg) {
		this.serverMsg = serverMsg;
	}

}
