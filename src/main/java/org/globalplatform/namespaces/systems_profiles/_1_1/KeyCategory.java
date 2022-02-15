
package org.globalplatform.namespaces.systems_profiles._1_1;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>keyCategory�� ���� Java Ŭ�����Դϴ�.
 * 
 * <p>���� ��Ű�� ������ �� Ŭ������ ���ԵǴ� �ʿ��� �������� �����մϴ�.
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
