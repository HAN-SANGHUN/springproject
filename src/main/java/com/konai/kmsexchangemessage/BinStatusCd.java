
package com.konai.kmsexchangemessage;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;

import org.springframework.util.Assert;

//import com.konai.platform.kms.message.BinStatusCd;


/**
 * <p>BinStatusCd�� ���� Java Ŭ�����Դϴ�.
 * 
 * <p>���� ��Ű�� ������ �� Ŭ������ ���ԵǴ� �ʿ��� �������� �����մϴ�.
 * <p>
 * <pre>
 * &lt;simpleType name="BinStatusCd">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="DRAFT"/>
 *     &lt;enumeration value="USE"/>
 *     &lt;enumeration value="DISPOSAL"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "BinStatusCd")
@XmlEnum
public enum BinStatusCd {

	//modify by shifei 20140925
//    DRAFT,
//    USE,
//    DISPOSAL;
//
//    public String value() {
//        return name();
//    }
//
//    public static BinStatusCd fromValue(String v) {
//        return valueOf(v);
//    }
	
	DRAFT("00"),
    USE("30"),
    DISPOSAL("50");

    private String code;
    
    BinStatusCd(String code) {
    	this.code = code;
	}
    
    public String getCode() {
    	return this.code;
    }
    
    public static BinStatusCd fromCode(String code) {
    	for (BinStatusCd bsc: BinStatusCd.values()) {
    		if (bsc.getCode().equals(code))
    			return bsc;
    	}

    	Assert.state(false, "Unknown active status code: " + code);
    	return null;
    }

    
    public String value() {
        return name();
    }
    
    public static BinStatusCd fromValue(String v) {
        return valueOf(v);
    }

}
