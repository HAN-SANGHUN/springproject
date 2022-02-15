package com.kona.kms.web.cert;

public enum BrandType {

	VISA(210, "VISA VSDC"),
	
	MASTER(220, "EMV M/Chip4"),
	
	JCB(230, "EMV Jsmart"),
	
	VISA_LOCAL(240, "EMV Visa Local"),
	
	MASTER_LOCAL(241, "EMV Master Local"),
	
	AMEX(250, "Amex"),
	
	DINERS(260, "Diners"),
	
	PBOC(270, "PBOC");
	
	private int code;
	
	private String name;
	
	private BrandType(int code, String name){
		this.code = code;
		this.name = name;
	}
	
	public int getCode(){
		return this.code;
	}
	
	public String getName(){
		return this.name;
	}
	
	public static BrandType codeFor(int code){
		switch(code){
			case 210: return VISA;
			case 220: return MASTER;
			case 230: return JCB;
			case 240: return VISA_LOCAL;
			case 241: return MASTER_LOCAL;
			case 250: return AMEX;
			case 260: return DINERS;
			case 270: return PBOC;
		}
		return null;
	}

	public static BrandType nameFor(String name) {
		if(name == null) return null;
		
		if(name.equals(BrandType.VISA.getName())) return VISA;
		if(name.equals(BrandType.MASTER.getName())) return MASTER;
		if(name.equals(BrandType.JCB.getName())) return JCB;
		if(name.equals(BrandType.VISA_LOCAL.getName())) return VISA_LOCAL;
		if(name.equals(BrandType.MASTER_LOCAL.getName())) return MASTER_LOCAL;
		if(name.equals(BrandType.AMEX.getName())) return AMEX;
		if(name.equals(BrandType.DINERS.getName())) return DINERS;		
		
		return null;
	}
}
