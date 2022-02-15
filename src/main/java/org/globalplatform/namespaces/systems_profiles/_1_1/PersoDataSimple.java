
package org.globalplatform.namespaces.systems_profiles._1_1;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>PersoDataSimple에 대한 Java 클래스입니다.
 * 
 * <p>다음 스키마 단편이 이 클래스에 포함되는 필요한 콘텐츠를 지정합니다.
 * <p>
 * <pre>
 * &lt;simpleType name="PersoDataSimple">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="ACCT"/>
 *     &lt;enumeration value="Image1"/>
 *     &lt;enumeration value="MS"/>
 *     &lt;enumeration value="Text1"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "PersoDataSimple")
@XmlEnum
public enum PersoDataSimple {

    ACCT("ACCT"),
    @XmlEnumValue("Image1")
    IMAGE_1("Image1"),
    MS("MS"),
    @XmlEnumValue("Text1")
    TEXT_1("Text1");
    private final String value;

    PersoDataSimple(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static PersoDataSimple fromValue(String v) {
        for (PersoDataSimple c: PersoDataSimple.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
