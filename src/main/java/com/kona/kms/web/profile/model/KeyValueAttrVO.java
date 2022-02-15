package com.kona.kms.web.profile.model;

import java.io.Serializable;

public class KeyValueAttrVO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6584227388816869726L;
	
	public boolean isImportable;
	
	public boolean isExportable;
	
	public boolean isSensitive;
	
	public boolean isEncrypt;
	
	public boolean isDecrypt;
	
	public boolean isEncryptDecrypt;
	
	public boolean isWrap;
	
	public boolean isUnwrap;
	
	public boolean isWrapUnwrap;
	
	public boolean isSign;
	
	public boolean isVerify;
	
	public boolean isDerive;
	
	public KeyValueAttrVO(String attrString){
		
		/*		 
		 * [생성규칙] Encrypt-Decrypt-DecryptEncrypt-Sign-Verify-Wrap-Unwrap-UnwrapWrap-Derive-Importable-Exportable-Sensitive
		 * 
		 * True(0) / False(1)
		 */
		char[] attrs = attrString.toCharArray();
		
		this.isEncrypt = (attrs[0] == '0') ? true :false;
		this.isDecrypt = (attrs[1] == '0') ? true :false;
		this.isEncryptDecrypt = (attrs[2] == '0') ? true :false;
		this.isSign = (attrs[3] == '0') ? true :false;
		this.isVerify = (attrs[4] == '0') ? true :false;
		this.isWrap = (attrs[5] == '0') ? true :false;
		this.isUnwrap = (attrs[6] == '0') ? true :false;
		this.isWrapUnwrap = (attrs[7] == '0') ? true :false;
		this.isDerive = (attrs[8] == '0') ? true :false;
		
		this.isImportable = (attrs[9] == '0') ? true :false;
		this.isExportable = (attrs[10] == '0') ? true :false;
		this.isSensitive = (attrs[11] == '0') ? true :false;		
	}

	public boolean isImportable() {
		return isImportable;
	}

	public boolean isExportable() {
		return isExportable;
	}

	public boolean isSensitive() {
		return isSensitive;
	}

	public boolean isEncrypt() {
		return isEncrypt;
	}

	public boolean isDecrypt() {
		return isDecrypt;
	}

	public boolean isEncryptDecrypt() {
		return isEncryptDecrypt;
	}

	public boolean isWrap() {
		return isWrap;
	}

	public boolean isUnwrap() {
		return isUnwrap;
	}

	public boolean isWrapUnwrap() {
		return isWrapUnwrap;
	}

	public boolean isSign() {
		return isSign;
	}

	public boolean isVerify() {
		return isVerify;
	}

	public boolean isDerive() {
		return isDerive;
	}

}
