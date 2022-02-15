
package com.konai.kmsexchangemessage;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>WorkCode�� ���� Java Ŭ�����Դϴ�.
 * 
 * <p>���� ��Ű�� ������ �� Ŭ������ ���ԵǴ� �ʿ��� �������� �����մϴ�.
 * <p>
 * <pre>
 * &lt;simpleType name="WorkCode">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="INSERT"/>
 *     &lt;enumeration value="DELETE"/>
 *     &lt;enumeration value="UPDATE"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "WorkCode")
@XmlEnum
public enum WorkCode {

    INSERT,
    DELETE,
    UPDATE;

    public String value() {
        return name();
    }

    public static WorkCode fromValue(String v) {
        return valueOf(v);
    }

}
