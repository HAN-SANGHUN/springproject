package com.kona.kms.web.cert.utils.formatter;

import com.kona.kms.utils.Util;
import com.kona.kms.web.cert.model.CertificateModel;
import com.kona.kms.web.cert.utils.ICertiReqFormatter;

public class MasterFormatter implements ICertiReqFormatter{
	
	private CertificateModel model;
	
	private String modulus;
	
	private String exponent;
	
	private String ipkSize;
	
	private String expSize;
	
	private String leftmost;
																																	
	private String extentionData;
																																																																																											
	private String selfSignData;
	
	private String cryptoData;
	
	private String hexDigestStr;
	
	public MasterFormatter(CertificateModel model){
		this.model = model;
	}
	
	@Override
	public void format(String modulus, String exponent, String ipkSize, String expSize, String leftmost) {
		this.modulus = modulus;
		this.exponent = exponent;
		this.ipkSize = Util.intToHex(Integer.valueOf(ipkSize)/2);
		this.expSize = Util.intToHex(Integer.valueOf(expSize)/2);
		this.leftmost = leftmost;
		
		// Extension Data
		StringBuffer ext = new StringBuffer();
		
		// BIN Number
		ext.append(model.getBinNumber()+"FF");		
		// IPK File Index
		ext.append(model.getIpkIndex());		
		// IPK Alg Indicator
		ext.append("01");
		// N Length
		ext.append(this.ipkSize); 
		// E Length
		ext.append(this.expSize);
		// N
		ext.append(modulus);
		// E
		ext.append(exponent); 
				
		// Self Sign Data
		StringBuffer ssd = new StringBuffer();
		
		// Header
		ssd.append("6A"); 
		// Certificate Format
		ssd.append("11");
		// BIN + "FF"
		ssd.append(model.getBinNumber() +"FF");
		// Expire Date: yyyy-MM-dd
		ssd.append(model.getExpireDateStr().substring(5,7)+model.getExpireDateStr().substring(2,4)); 
		// Tracking Number
		ssd.append(model.getTrackingNumber());	
		// Hash Alg
		ssd.append("01");
		// Issuer Key Alg
		ssd.append("01");
		// N Length
		ssd.append(this.ipkSize); 
		// E Length
		ssd.append(this.expSize);
		// N
		ssd.append(modulus);
		// E
		ssd.append(exponent); 
				
		this.extentionData = ext.toString();
		this.selfSignData = ssd.toString();
	}

	@Override
	public String getSelfSignData() {
		
		System.out.println("this.selfSignData.substring(2): " + this.selfSignData.substring(2));
		
		return this.selfSignData.substring(2);     
	}

	@Override
	public String getCrytoData(String hexDigestStr) {
		this.hexDigestStr = hexDigestStr;
		
		StringBuffer sb = new StringBuffer();
		
		sb.append(this.selfSignData.substring(0, this.selfSignData.length()-(36+Integer.valueOf(this.expSize))*2));
		sb.append(hexDigestStr);
		sb.append("BC");
		
		this.cryptoData = sb.toString();
		
		return this.cryptoData;																																																																																																																																																												
	}

	@Override
	public String getCertiInfo(String hexCryptoResult) {
		StringBuffer sb = new StringBuffer();
		
		sb.append(this.extentionData);
		sb.append(hexCryptoResult);
		
		return sb.toString();
	}

	@Override
	public String getCertiHashValue() {
		
		StringBuffer sb = new StringBuffer();
		sb.append(model.getBinNumber()+"FF");
		sb.append(model.getIpkIndex());
		sb.append("01");
		sb.append(this.hexDigestStr);
		
		return sb.toString();
	}

	@Override																																																																																																																																																																																																																																																																																
	public String getCertiInfoFileName() {
		StringBuffer sb = new StringBuffer();
		
		sb.append(model.getBinNumber());
		sb.append("-");
		sb.append(model.getIpkIndex());
		sb.append(".sip");
		
		return sb.toString();
	}

	@Override
	public String getCertiHashValueFileName() {
		StringBuffer sb = new StringBuffer();
		
		sb.append(model.getBinNumber());
		sb.append("-");
		sb.append(model.getIpkIndex());
		sb.append(".hip");
		
		return sb.toString();
	}

	@Override
	public String getHashValueFileType() {
		return "BYTE_ARRAY";
	}

	@Override																																																																																																																																																																														
	public String toString() {
		return "MasterFormatter [modulus=" + modulus + ", exponent=" + exponent
				+ ", ipkSize=" + ipkSize + ", expSize=" + expSize
				+ ", leftmost=" + leftmost + ", extentionData=" + extentionData
				+ ", selfSignData=" + selfSignData + ", cryptoData="
				+ cryptoData + ", hexDigestStr=" + hexDigestStr + "]";
	}
}
