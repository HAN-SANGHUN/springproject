package com.kona.kms.web.profile.model;

import java.io.Serializable;

public class TransportKeyModel implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4897915881459045260L;
	
	private String keyID;
	
	private String keyVersion;
	
	private String keyName;
	
	private String owner;	
	
	private String algorithm;
	
	private String algorithmParameters;

	public String getKeyID() {
		return keyID;
	}

	public void setKeyID(String keyID) {
		this.keyID = keyID;
	}

	public String getKeyVersion() {
		return keyVersion;
	}

	public void setKeyVersion(String keyVersion) {
		this.keyVersion = keyVersion;
	}

	public String getKeyName() {
		return keyName;
	}

	public void setKeyName(String keyName) {
		this.keyName = keyName;
	}

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public String getAlgorithm() {
		return algorithm;
	}

	public void setAlgorithm(String algorithm) {
		this.algorithm = algorithm;
	}

	public String getAlgorithmParameters() {
		return algorithmParameters;
	}

	public void setAlgorithmParameters(String algorithmParameters) {
		this.algorithmParameters = algorithmParameters;
	}
}
