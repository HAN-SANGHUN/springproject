package com.kona.kms.web.profile.model;

import java.util.Date;

public class RSAKeyModel {

	private String modulus;
	private String public_exponent;
	private String private_exponent;
	private String prime_p;
	private String prime_q;
	private String prime_exponent_p;
	private String prime_exponent_q;
	private String crt_coefficient;
	
	private String status;
	
	
	public String getModulus() {
		return modulus;
	}
	public void setModulus(String modulus) {
		this.modulus = modulus;
	}
	public String getPublic_exponent() {
		return public_exponent;
	}
	public void setPublic_exponent(String public_exponent) {
		this.public_exponent = public_exponent;
	}
	public String getPrivate_exponent() {
		return private_exponent;
	}
	public void setPrivate_exponent(String private_exponent) {
		this.private_exponent = private_exponent;
	}
	public String getPrime_p() {
		return prime_p;
	}
	public void setPrime_p(String prime_p) {
		this.prime_p = prime_p;
	}
	public String getPrime_q() {
		return prime_q;
	}
	public void setPrime_q(String prime_q) {
		this.prime_q = prime_q;
	}
	public String getPrime_exponent_p() {
		return prime_exponent_p;
	}
	public void setPrime_exponent_p(String prime_exponent_p) {
		this.prime_exponent_p = prime_exponent_p;
	}
	public String getPrime_exponent_q() {
		return prime_exponent_q;
	}
	public void setPrime_exponent_q(String prime_exponent_q) {
		this.prime_exponent_q = prime_exponent_q;
	}
	public String getCrt_coefficient() {
		return crt_coefficient;
	}
	public void setCrt_coefficient(String crt_coefficient) {
		this.crt_coefficient = crt_coefficient;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}

	
}
