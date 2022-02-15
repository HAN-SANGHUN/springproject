package com.kona.kms.web.cert.utils.formatter;

import java.util.Calendar;

import com.kona.kms.utils.Util;
import com.kona.kms.web.cert.model.CertificateModel;
import com.kona.kms.web.cert.utils.ICertiReqFormatter;

/**
 * 
 * Input File: Extension Data + Self-Signed Certificate +  HashResult + "BC"
 *
 */
public class JcbFormatter implements ICertiReqFormatter{

	private static final String serviceIdentifier = "10100000";
	
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
	
	public JcbFormatter(CertificateModel model){
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
		
		ext.append(model.getBinNumber()+"FF");
		ext.append(model.getIpkIndex());
		ext.append("01");
		ext.append(this.ipkSize);
		ext.append(this.expSize);
		ext.append(modulus);
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
		// Hash Algorithm: SHA-1
		ssd.append("01");
		// Issuer Public Key Algorithm: RSA
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
		
		return this.hexDigestStr;
	}

	@Override
	public String getCertiInfoFileName() {
		Calendar cal = Calendar.getInstance();
		
		int yyyy = cal.get(Calendar.YEAR);
		int mm = cal.get(Calendar.MONTH) + 1;
		int dd = cal.get(Calendar.DAY_OF_MONTH);
		
		StringBuffer sb = new StringBuffer();
		
		if(yyyy == 2013){
			sb.append("KD");
		}else if(yyyy == 2014){
			sb.append("KE");
		}else if(yyyy == 2015){
			sb.append("KF");
		}
		
		sb.append(mm);
		sb.append(dd);
		sb.append(String.valueOf(model.getIpkIndex()).substring(4, 6));
		sb.append("99");
		
		return sb.toString();
	}

	@Override
	public String getCertiHashValueFileName() {
		Calendar cal = Calendar.getInstance();
		
		int yyyy = cal.get(Calendar.YEAR);
		int mm = cal.get(Calendar.MONTH) + 1;
		int dd = cal.get(Calendar.DAY_OF_MONTH);
		
		StringBuffer sb = new StringBuffer();
		
		sb.append("K");
		
		if(yyyy == 2013){
			sb.append("D");
		}else if(yyyy == 2014){
			sb.append("E");
		}else if(yyyy == 2015){
			sb.append("F");
		}
		
		if(mm >= 10){
			sb.append(mm);
		}else{
			sb.append("0").append(mm);
		}
		
		sb.append(dd);
		sb.append(String.valueOf(model.getIpkIndex()).substring(4, 6));
		sb.append("99");
		sb.append("_HashValue");
		
		return sb.toString();
	}
	
	@Override
	public String getHashValueFileType() {
		return "BYTE_ARRAY";
	}


	@Override
	public String toString() {
		return "JcbFormatter [modulus=" + modulus + ", exponent=" + exponent
				+ ", ipkSize=" + ipkSize + ", expSize=" + expSize
				+ ", leftmost=" + leftmost + ", extentionData=" + extentionData
				+ ", selfSignData=" + selfSignData + ", cryptoData="
				+ cryptoData + ", hexDigestStr=" + hexDigestStr + "]";
	}
}
