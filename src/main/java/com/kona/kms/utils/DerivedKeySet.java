package com.kona.kms.utils;

public class DerivedKeySet {
	public byte[] ENCKey = null;
	public byte[] MACKey = null;
	public byte[] KEKKey = null;
	
	public DerivedKeySet()	{
		ENCKey = new byte[16];
		MACKey = new byte[16];
		KEKKey = new byte[16];
	}
}