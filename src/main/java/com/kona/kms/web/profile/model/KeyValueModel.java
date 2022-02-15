package com.kona.kms.web.profile.model;

import java.io.Serializable;
import java.util.List;

public class KeyValueModel implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6584227388816869726L;
	
	private String keyProfileID;
	
	private String keyProfileVersion;
	
	private String formatCode;
	
	private List<ValueComponent> components;
	
	public String getKeyProfileID() {
		return keyProfileID;
	}

	public void setKeyProfileID(String keyProfileID) {
		this.keyProfileID = keyProfileID;
	}

	public String getKeyProfileVersion() {
		return keyProfileVersion;
	}

	public void setKeyProfileVersion(String keyProfileVersion) {
		this.keyProfileVersion = keyProfileVersion;
	}

	public String getFormatCode() {
		return formatCode;
	}

	public void setFormatCode(String formatCode) {
		this.formatCode = formatCode;
	}	
	
	public List<ValueComponent> getComponents() {
		return components;
	}

	public void setComponents(List<ValueComponent> components) {
		this.components = components;
	}
	
	public class ValueComponent{
		
		private String name;
		
		private String encoding;
		
		private String value;
		
		private int order;
		
		private String kcv;
		
		public ValueComponent(){
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getEncoding() {
			return encoding;
		}

		public void setEncoding(String encoding) {
			this.encoding = encoding;
		}

		public String getValue() {
			return value;
		}

		public void setValue(String value) {
			this.value = value;
		}

		public int getOrder() {
			return order;
		}

		public void setOrder(int order) {
			this.order = order;
		}

		public String getKcv() {
			return kcv;
		}

		public void setKcv(String kcv) {
			this.kcv = kcv;
		}			
	}

	@Override
	public String toString() {
		return "KeyValueModel [keyProfileID=" + keyProfileID
				+ ", keyProfileVersion=" + keyProfileVersion + ", formatCode="
				+ formatCode + ", components=" + components + "]";
	}	
}
