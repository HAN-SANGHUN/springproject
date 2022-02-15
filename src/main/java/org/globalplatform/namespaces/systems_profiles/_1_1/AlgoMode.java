
package org.globalplatform.namespaces.systems_profiles._1_1;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>algoMode에 대한 Java 클래스입니다.
 * 
 * <p>다음 스키마 단편이 이 클래스에 포함되는 필요한 콘텐츠를 지정합니다.
 * <p>
 * <pre>
 * &lt;simpleType name="algoMode">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="ECB"/>
 *     &lt;enumeration value="ECB_PAD"/>
 *     &lt;enumeration value="CBC"/>
 *     &lt;enumeration value="CBC_IV8"/>
 *     &lt;enumeration value="PUBLIC_KEY"/>
 *     &lt;enumeration value="PRIVATE_KEY"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "algoMode")
@XmlEnum
public enum AlgoMode {

    ECB("ECB"),
    ECB_PAD("ECB_PAD"),
    CBC("CBC"),
    @XmlEnumValue("CBC_IV8")
    CBC_IV_8("CBC_IV8"),
    PUBLIC_KEY("PUBLIC_KEY"),
    PRIVATE_KEY("PRIVATE_KEY");
    private final String value;

    AlgoMode(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static AlgoMode fromValue(String v) {
        for (AlgoMode c: AlgoMode.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
