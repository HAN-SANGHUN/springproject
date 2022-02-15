
package org.globalplatform.namespaces.systems_profiles._1_1;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>application에 대한 Java 클래스입니다.
 * 
 * <p>다음 스키마 단편이 이 클래스에 포함되는 필요한 콘텐츠를 지정합니다.
 * <p>
 * <pre>
 * &lt;simpleType name="application">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="GP"/>
 *     &lt;enumeration value="MULTOS"/>
 *     &lt;enumeration value="W4SC"/>
 *     &lt;enumeration value="OTHER"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "application")
@XmlEnum
public enum Application {

    GP("GP"),
    MULTOS("MULTOS"),
    @XmlEnumValue("W4SC")
    W_4_SC("W4SC"),
    OTHER("OTHER");
    private final String value;

    Application(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static Application fromValue(String v) {
        for (Application c: Application.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
