
package org.globalplatform.namespaces.systems_profiles._1_1;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>tagEncoding에 대한 Java 클래스입니다.
 * 
 * <p>다음 스키마 단편이 이 클래스에 포함되는 필요한 콘텐츠를 지정합니다.
 * <p>
 * <pre>
 * &lt;simpleType name="tagEncoding">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="DGI"/>
 *     &lt;enumeration value="EMV"/>
 *     &lt;enumeration value="L16"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "tagEncoding")
@XmlEnum
public enum TagEncoding {

    DGI("DGI"),
    EMV("EMV"),
    @XmlEnumValue("L16")
    L_16("L16");
    private final String value;

    TagEncoding(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static TagEncoding fromValue(String v) {
        for (TagEncoding c: TagEncoding.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
