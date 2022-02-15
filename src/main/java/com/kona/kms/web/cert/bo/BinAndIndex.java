package com.kona.kms.web.cert.bo;

import java.util.Date;

public class BinAndIndex {

	private String bin;
	
	private int ipkIndex;
	
	private Date expireDate;
	
	public BinAndIndex(String bin, int ipkIndex, Date expireDate){
		this.bin = bin;
		this.ipkIndex = ipkIndex;
		this.expireDate = expireDate;
	}
	
	public boolean equals(Object obj){
		if(obj == null) return false;
		
		if(this.getClass() != obj.getClass()) return false;
		
		if(!this.bin.equals(((BinAndIndex)obj).bin)) return false;
		
		if(this.ipkIndex != ((BinAndIndex)obj).ipkIndex) return false;
		
		if(!this.expireDate.equals(((BinAndIndex)obj).expireDate)) return false;
		
		return true;		
	}
}
