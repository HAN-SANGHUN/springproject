
package org.globalplatform.namespaces.systems_profiles._1_1;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>tkalgorithm에 대한 Java 클래스입니다.
 * 
 * <p>다음 스키마 단편이 이 클래스에 포함되는 필요한 콘텐츠를 지정합니다.
 * <p>
 * <pre>
 * &lt;simpleType name="tkalgorithm">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="CBC"/>
 *     &lt;enumeration value="ECB"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "tkalgorithm")
@XmlEnum
public enum Tkalgorithm {

    CBC,
    ECB;

    public String value() {
        return name();
    }

    public static Tkalgorithm fromValue(String v) {
        return valueOf(v);
    }

}
