
package org.globalplatform.namespaces.systems_profiles._1_1;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>secureElementProtocol에 대한 Java 클래스입니다.
 * 
 * <p>다음 스키마 단편이 이 클래스에 포함되는 필요한 콘텐츠를 지정합니다.
 * <p>
 * <pre>
 * &lt;simpleType name="secureElementProtocol">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="T0"/>
 *     &lt;enumeration value="T1"/>
 *     &lt;enumeration value="T14"/>
 *     &lt;enumeration value="TYPEA"/>
 *     &lt;enumeration value="TYPEB"/>
 *     &lt;enumeration value="OTHER"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "secureElementProtocol")
@XmlEnum
public enum SecureElementProtocol {

    @XmlEnumValue("T0")
    T_0("T0"),
    @XmlEnumValue("T1")
    T_1("T1"),
    @XmlEnumValue("T14")
    T_14("T14"),
    TYPEA("TYPEA"),
    TYPEB("TYPEB"),
    OTHER("OTHER");
    private final String value;

    SecureElementProtocol(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static SecureElementProtocol fromValue(String v) {
        for (SecureElementProtocol c: SecureElementProtocol.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
