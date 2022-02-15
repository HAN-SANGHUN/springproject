
package com.konai.kmsexchangemessage;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>ActiveStatusCd에 대한 Java 클래스입니다.
 * 
 * <p>다음 스키마 단편이 이 클래스에 포함되는 필요한 콘텐츠를 지정합니다.
 * <p>
 * <pre>
 * &lt;simpleType name="ActiveStatusCd">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="ACTIVE"/>
 *     &lt;enumeration value="INACTIVE"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "ActiveStatusCd")
@XmlEnum
public enum ActiveStatusCd {

    ACTIVE,
    INACTIVE;

    public String value() {
        return name();
    }

    public static ActiveStatusCd fromValue(String v) {
        return valueOf(v);
    }

}
