
package com.konai.kmsexchangemessage;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>ResponseType에 대한 Java 클래스입니다.
 * 
 * <p>다음 스키마 단편이 이 클래스에 포함되는 필요한 콘텐츠를 지정합니다.
 * <p>
 * <pre>
 * &lt;simpleType name="ResponseType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="SUCCESS"/>
 *     &lt;enumeration value="ALREDY_REG"/>
 *     &lt;enumeration value="FAIL"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "ResponseType")
@XmlEnum
public enum ResponseType {

    SUCCESS,
    ALREDY_REG,
    FAIL;

    public String value() {
        return name();
    }

    public static ResponseType fromValue(String v) {
        return valueOf(v);
    }

}
