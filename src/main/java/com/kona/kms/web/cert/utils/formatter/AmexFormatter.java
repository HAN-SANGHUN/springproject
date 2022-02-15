package com.kona.kms.web.cert.utils.formatter;

import com.kona.kms.utils.Util;
import com.kona.kms.web.cert.model.CertificateModel;
import com.kona.kms.web.cert.utils.ICertiReqFormatter;

public class AmexFormatter implements ICertiReqFormatter{
	
	private static final String serviceIdentifier = "00000000";
	
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
	
	public AmexFormatter(CertificateModel model){
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
		
		// Header
		ext.append("22"); 
		// N Length
		ext.append(this.ipkSize); 
		 // N
		ext.append(modulus);
		// E Length
		ext.append(this.expSize);
		// E
		ext.append(exponent); 
		 // Tracking Number
		ext.append(model.getTrackingNumber());
		
		// Self Sign Data
		StringBuffer ssd = new StringBuffer();
		
		// Header
		ssd.append("23"); 
		// Service Identifier
		ssd.append(AmexFormatter.serviceIdentifier);
		// Certificate Format
		ssd.append("02");  
		// BIN + "FF"
		ssd.append(model.getBinNumber() +"ff"); 
		// Expire Date: yyyy-MM-dd
		ssd.append(model.getExpireDateStr().substring(5,7)+model.getExpireDateStr().substring(2,4));  
		// Tracking Number
		ssd.append(model.getTrackingNumber()); 
		// Hash algorithm
		ssd.append("01"); 
		// Public Key algorithm
		ssd.append("01"); 
		// N Length
		ssd.append(this.ipkSize); 
		 // E Length
		ssd.append(this.expSize); 
		// Lefemost
		ssd.append(leftmost);  
		// Exponent
		ssd.append(exponent);  
		
		this.extentionData = ext.toString();
		this.selfSignData = ssd.toString();
	}

	@Override
	public String getSelfSignData() {
		
		return this.selfSignData;
	}

	@Override
	public String getCrytoData(String hexDigestStr) {
		this.hexDigestStr = hexDigestStr;
		
		this.cryptoData = new StringBuffer().append(this.selfSignData).append(hexDigestStr).toString();
		
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
		
		return this.hexDigestStr;
	}

	@Override
	public String getCertiInfoFileName() {
		StringBuffer sb = new StringBuffer();
		if(model.getAmexFileType().equals("PRODUCTION")){
			sb.append("PIK");
		}else{
			sb.append("TIK");
		}
		
		sb.append(model.getTrackingNumber().substring(1));
		sb.append(".DAT");
		
		return sb.toString();
	}

	@Override
	public String getCertiHashValueFileName() {
		StringBuffer sb = new StringBuffer();
		
		if(model.getAmexFileType().equals("PRODUCTION")){
			sb.append("PHI");
		}else{
			sb.append("THI");
		}
		
		sb.append(model.getTrackingNumber().substring(1));
		sb.append(".TXT");
		
		return sb.toString();
	}

	@Override
	public String getHashValueFileType() {
		return "HEX_STRING";
	}

	@Override
	public String toString() {
		return "AmexFormatter [modulus=" + modulus + ", exponent=" + exponent
				+ ", ipkSize=" + ipkSize + ", expSize=" + expSize
				+ ", leftmost=" + leftmost + ", extentionData=" + extentionData
				+ ", selfSignData=" + selfSignData + ", cryptoData="
				+ cryptoData + ", hexDigestStr=" + hexDigestStr + "]";
	}
}
