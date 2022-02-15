package com.kona.kms.web.profile.model;

import java.io.Serializable;

/**
 * Secret Key 인 경우 Component 수 표현
 * @author 정호
 *
 */
public class KeyPartModel implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6272021174213171218L;
	
	private String keyProfileID;
	
	private String keyProfileVersion;
	
	private int numberOfParts;
	
	private int partNumber;
	
	/** XOR or CONCATENATE */
	private String algorithmCode;
	
	/** kcv1, kcv2, kcv3, ..., combined kcv value with ',' */
	private String kcvValues;

	public String getKeyProfileID() {
		return keyProfileID;
	}

	public void setKeyProfileID(String keyProfileID) {
		this.keyProfileID = keyProfileID;
	}

	public String getKeyProfileVersion() {
		return keyProfileVersion;
	}

	public void setKeyProfileVersion(String keyProfileVersion) {
		this.keyProfileVersion = keyProfileVersion;
	}

	public int getNumberOfParts() {
		return numberOfParts;
	}

	public void setNumberOfParts(int numberOfParts) {
		this.numberOfParts = numberOfParts;
	}

	public int getPartNumber() {
		return partNumber;
	}

	public void setPartNumber(int partNumber) {
		this.partNumber = partNumber;
	}

	public String getAlgorithmCode() {
		return algorithmCode;
	}

	public void setAlgorithmCode(String algorithmCode) {
		this.algorithmCode = algorithmCode;
	}

	public String getKcvValues() {
		return kcvValues;
	}

	public void setKcvValues(String kcvValues) {
		this.kcvValues = kcvValues;
	}

}
