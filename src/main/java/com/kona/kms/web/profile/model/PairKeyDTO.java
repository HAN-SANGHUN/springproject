package com.kona.kms.web.profile.model;

import java.util.List;

public class PairKeyDTO {

	private KeyProfileModel publicKey;
	
	private KeyProfileModel privateKey;
	
	public PairKeyDTO(List<KeyProfileModel> profiles){
		for(KeyProfileModel profile : profiles){
			if(profile.getKeyTypeCode().equals("PRIVATE")){
				this.privateKey = profile;
			}else{
				this.publicKey = profile;
			}
		}
	}

	public KeyProfileModel getPublicKey() {
		return publicKey;
	}

	public void setPublicKey(KeyProfileModel publicKey) {
		this.publicKey = publicKey;
	}

	public KeyProfileModel getPrivateKey() {
		return privateKey;
	}

	public void setPrivateKey(KeyProfileModel privateKey) {
		this.privateKey = privateKey;
	}	
}
