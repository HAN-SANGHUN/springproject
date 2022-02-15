
package org.globalplatform.namespaces.systems_profiles._1_1;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>encoding에 대한 Java 클래스입니다.
 * 
 * <p>다음 스키마 단편이 이 클래스에 포함되는 필요한 콘텐츠를 지정합니다.
 * <p>
 * <pre>
 * &lt;simpleType name="encoding">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="HEX"/>
 *     &lt;enumeration value="ASCII"/>
 *     &lt;enumeration value="BASE64"/>
 *     &lt;enumeration value="UTF8"/>
 *     &lt;enumeration value="CN"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "encoding")
@XmlEnum
public enum Encoding {

    HEX("HEX"),
    ASCII("ASCII"),
    @XmlEnumValue("BASE64")
    BASE_64("BASE64"),
    @XmlEnumValue("UTF8")
    UTF_8("UTF8"),
    CN("CN");
    private final String value;

    Encoding(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static Encoding fromValue(String v) {
        for (Encoding c: Encoding.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
