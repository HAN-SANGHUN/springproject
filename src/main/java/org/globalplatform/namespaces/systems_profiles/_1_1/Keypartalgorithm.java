
package org.globalplatform.namespaces.systems_profiles._1_1;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>keypartalgorithm에 대한 Java 클래스입니다.
 * 
 * <p>다음 스키마 단편이 이 클래스에 포함되는 필요한 콘텐츠를 지정합니다.
 * <p>
 * <pre>
 * &lt;simpleType name="keypartalgorithm">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="XOR"/>
 *     &lt;enumeration value="CONCATE"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "keypartalgorithm")
@XmlEnum
public enum Keypartalgorithm {

    XOR,
    CONCATE;

    public String value() {
        return name();
    }

    public static Keypartalgorithm fromValue(String v) {
        return valueOf(v);
    }

}
