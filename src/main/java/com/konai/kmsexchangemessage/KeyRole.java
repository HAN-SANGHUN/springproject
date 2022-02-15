
package com.konai.kmsexchangemessage;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>KeyRole�� ���� Java Ŭ�����Դϴ�.
 * 
 * <p>���� ��Ű�� ������ �� Ŭ������ ���ԵǴ� �ʿ��� �������� �����մϴ�.
 * <p>
 * <pre>
 * &lt;simpleType name="KeyRole">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="SD_KEY"/>
 *     &lt;enumeration value="TRANSPORT_KEY"/>
 *     &lt;enumeration value="ISSUERRSA_KEY"/>
 *     &lt;enumeration value="APPLICATION_KEY"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "KeyRole")
@XmlEnum
public enum KeyRole {

    SD_KEY,
    TRANSPORT_KEY,
    ISSUERRSA_KEY,
    APPLICATION_KEY;

    public String value() {
        return name();
    }

    public static KeyRole fromValue(String v) {
        return valueOf(v);
    }

}
