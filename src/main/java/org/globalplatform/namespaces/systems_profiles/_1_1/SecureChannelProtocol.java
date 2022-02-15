
package org.globalplatform.namespaces.systems_profiles._1_1;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>secureChannelProtocol�� ���� Java Ŭ�����Դϴ�.
 * 
 * <p>���� ��Ű�� ������ �� Ŭ������ ���ԵǴ� �ʿ��� �������� �����մϴ�.
 * <p>
 * <pre>
 * &lt;simpleType name="secureChannelProtocol">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="NONE"/>
 *     &lt;enumeration value="SCP01"/>
 *     &lt;enumeration value="SCP02"/>
 *     &lt;enumeration value="OTHER"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "secureChannelProtocol")
@XmlEnum
public enum SecureChannelProtocol {

    NONE("NONE"),
    @XmlEnumValue("SCP01")
    SCP_01("SCP01"),
    @XmlEnumValue("SCP02")
    SCP_02("SCP02"),
    OTHER("OTHER");
    private final String value;

    SecureChannelProtocol(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static SecureChannelProtocol fromValue(String v) {
        for (SecureChannelProtocol c: SecureChannelProtocol.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
