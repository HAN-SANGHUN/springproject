
package org.globalplatform.namespaces.systems_profiles._1_1;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>keyCategory에 대한 Java 클래스입니다.
 * 
 * <p>다음 스키마 단편이 이 클래스에 포함되는 필요한 콘텐츠를 지정합니다.
 * <p>
 * <pre>
 * &lt;simpleType name="keyCategory">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="SECRET"/>
 *     &lt;enumeration value="PRIVATE"/>
 *     &lt;enumeration value="PUBLIC"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "keyCategory")
@XmlEnum
public enum KeyCategory {

    SECRET,
    PRIVATE,
    PUBLIC;

    public String value() {
        return name();
    }

    public static KeyCategory fromValue(String v) {
        return valueOf(v);
    }

}
