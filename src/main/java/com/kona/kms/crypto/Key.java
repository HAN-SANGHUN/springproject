package com.kona.kms.crypto;

import java.io.Serializable;

/**
 * File : Key.java
 *
 * Copyright (C) 2012 - 2013, KONA I Co.Ltd.
 * All rights reserved.
 *
 * This program is a property of KONA I. you can not redistribute it and/or modify it
 * without any permission of KONA I.
 *
 * @author			: Lee,YongHee(yhlee@konai.co.kr)
 */

/**
 * @author Lee,YongHee(yhlee@konai.co.kr)
 *
 */
public class Key implements Serializable {

	/**
	 * 
	 */
	
	private static final long serialVersionUID = -698522376934887821L;

	//ATTRIBUTES
	public static final int EXPORTABLE 	= 0x00000001;
	public static final int IMPORTABLE 	= 0x00000002;
	public static final int SENSITIVE 		= 0x00000004;

	//COMPONENTS
	public static final int CRT_DP1 			= 0x00001000;
	public static final int CRT_DQ1 			= 0x00002000;
	public static final int CRT_P  			= 0x00003000;
	public static final int CRT_PQ  			= 0x0004000;
	public static final int CRT_Q 			= 0x00005000;
	public static final int VALUE 			= 0x00006000;
	public static final int MODULUS 		=  0x00008000;
	public static final int EXPONENT 		= 0x00009000;

	//KEY TYPE
	public static final int PUBLIC 			= 0x00000002;
	public static final int PRIVATE 			= 0x00000003;
	public static final int SECRET 			= 0x00000004;

	//KEY SUB TYPE
	public static final int DES 				= 0x00000001;
	public static final int RSA 				= 0x00000002;
	public static final int RSACRT 			= 0x00000004;
	public static final int EC 					= 0x00000008;
	
	//USAGE
	public static final int ENCRYPT 					= 0x00000001;
	public static final int DECRYPT 					= 0x00000002;
	public static final int DECRYPT_ENCRYPT 	= 0x00000004;
	public static final int DERIVE 					= 0x00000008;
	public static final int SIGN 						= 0x0000000F;
	public static final int UNWRAP 					= 0x00000010;
	public static final int UNWRAP_WRAP 		= 0x00000011;
	public static final int VERIFY 					= 0x00000012;
	public static final int WRAP 						= 0x000000014;
	
	
	private int 		attribute;
	private int 		usage;
	private int 		size;
	private Key 	wrapKey;
	private byte[] start;
	private byte[] end;
	private String kmsLabel;
	private byte[] owner;
	private byte[] profileid;
	private byte[] version;
	private byte[] kcv;
	private int 		type;
	private int 		subType;
	
	private byte[] component_value;
	private byte[] component_crt_dp1;
	private byte[] component_crt_dq1;
	private byte[] component_crt_p;
	private byte[] component_crt_pq;
	private byte[] component_crt_q;
	private byte[] component_modulus;
	private byte[] component_exponent;
	
	/**
	 * @return the attribute
	 */
	public int getAttribute() {
		return attribute;
	}

	/**
	 * EXPORTABLE or IMPORTABLE or SENSITIVE
	 * 상위 값들의 조합으로 값을 설정 할수 있다.
	 * 예) EXPORTABLE | IMPORTABLE
	 * @param attribute the attribute to set
	 */
	public void setAttribute(int attribute) {
		this.attribute = attribute;
	}

	/**
	 * @return the usage
	 */
	public int getUsage() {
		return usage;
	}

	/**
	 * ENCRYPT or DECRYPT or DECRYPT_ENCRYPT or DERIVE or SIGN or UNWRAP or UNWRAP_WRAP or VERIFY or WRAP
	 * 상위 값들의 조합으로 값을 설정 할수 있다.
	 * 예) ENCRYPT | DECRYPT | SIGN
	 * @param usage the usage to set
	 */
	public void setUsage(int usage) {
		this.usage = usage;
	}

	/**
	 * Bit Size
	 * @return the size
	 */
	public int getSize() {
		return size;
	}

	/**
	 * @param size the size to set
	 */
	public void setSize(int size) {
		this.size = size;
	}

	public void setComponent(int type,byte[] value){
		switch(type){
		case Key.EXPONENT:
			this.component_exponent = value.clone();
			break;
		case Key.MODULUS:
			this.component_modulus = value.clone();
			break;
		case Key.VALUE:
			this.component_value = value.clone();
			break;
		case Key.CRT_DP1:
			this.component_crt_dp1 = value.clone();
			break;
		case Key.CRT_DQ1:
			this.component_crt_dq1 = value.clone();
			break;
		case Key.CRT_P:
			this.component_crt_p = value.clone();
			break;
		case Key.CRT_PQ:
			this.component_crt_pq = value.clone();
			break;
		case Key.CRT_Q:
			this.component_crt_q = value.clone();
			break;
		}
	}
	
	public byte[] getComponent(int type){
		byte[] value = null;
		switch(type){
		case Key.EXPONENT:
			value = this.component_exponent.clone();
			break;
		case Key.MODULUS:
			value = this.component_modulus.clone();
			break;
		case Key.VALUE:
			value = this.component_value.clone();
			break;
		case Key.CRT_DP1:
			value = this.component_crt_dp1.clone();
			break;
		case Key.CRT_DQ1:
			value = this.component_crt_dq1.clone();
			break;
		case Key.CRT_P:
			value = this.component_crt_p.clone();
			break;
		case Key.CRT_PQ:
			value = this.component_crt_pq.clone();
			break;
		case Key.CRT_Q:
			value = this.component_crt_q .clone();
			break;
		}
		
		return value;
	}

	/**
	 * @return the wrapKey
	 */
	public Key getWrapKey() {
		return wrapKey;
	}

	/**
	 * @param wrapKey the wrapKey to set
	 */
	public void setWrapKey(Key wrapKey) {
		this.wrapKey = wrapKey;
	}

	/**
	 * @return the start
	 */
	public byte[] getStart() {
		return start;
	}

	/**
	 * @param start the start to set
	 */
	public void setStart(byte[] start) {
		this.start = start;
	}

	/**
	 * @return the end
	 */
	public byte[] getEnd() {
		return end;
	}

	/**
	 * @param end the end to set
	 */
	public void setEnd(byte[] end) {
		this.end = end;
	}

	/**
	 * @return the kmsLabel
	 */
	public String getKmsLabel() {
		return kmsLabel;
	}

	/**
	 * @param kmsLabel the kmsLabel to set
	 */
	public void setKmsLabel(String kmsLabel) {
		this.kmsLabel = kmsLabel;
	}

	/**
	 * @return the version
	 */
	public byte[] getVersion() {
		return version;
	}

	/**
	 * @param version the version to set
	 */
	public void setVersion(byte[] version) {
		this.version = version;
	}

	/**
	 * @return the profileid
	 */
	public byte[] getProfileid() {
		return profileid;
	}

	/**
	 * @param profileid the profileid to set
	 */
	public void setProfileid(byte[] profileid) {
		this.profileid = profileid;
	}

	/**
	 * @return the owner
	 */
	public byte[] getOwner() {
		return owner;
	}

	/**
	 * @param owner the owner to set
	 */
	public void setOwner(byte[] owner) {
		this.owner = owner;
	}

	/**
	 * @return the kcv
	 */
	public byte[] getKcv() {
		return kcv;
	}

	/**
	 * @param kcv the kcv to set
	 */
	public void setKcv(byte[] kcv) {
		this.kcv = kcv;
	}

	/**
	 * @return the type
	 */
	public int getType() {
		return type;
	}

	/**
	 * PUBLIC 또는 PRIVATE 또는 SECRET 중에 하나 리턴
	 * @param type the type to set
	 */
	public void setType(int type) {
		this.type = type;
	}

	/**
	 * @return the subType
	 */
	public int getSubType() {
		return subType;
	}

	/**
	 * @param subType the subType to set
	 */
	public void setSubType(int subType) {
		this.subType = subType;
		
	}
}
