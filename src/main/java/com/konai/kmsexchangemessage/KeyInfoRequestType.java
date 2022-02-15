
package com.konai.kmsexchangemessage;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>KeyInfoRequestType complex type에 대한 Java 클래스입니다.
 * 
 * <p>다음 스키마 단편이 이 클래스에 포함되는 필요한 콘텐츠를 지정합니다.
 * 
 * <pre>
 * &lt;complexType name="KeyInfoRequestType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element ref="{http://www.konai.com/KmsExchangeMessage}keyInfos"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "KeyInfoRequestType", propOrder = {
    "keyInfos"
})
public class KeyInfoRequestType {

    @XmlElement(namespace = "http://www.konai.com/KmsExchangeMessage", required = true)
    protected KeyInfos keyInfos;

    /**
     * keyInfos 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link KeyInfos }
     *     
     */
    public KeyInfos getKeyInfos() {
        return keyInfos;
    }

    /**
     * keyInfos 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link KeyInfos }
     *     
     */
    public void setKeyInfos(KeyInfos value) {
        this.keyInfos = value;
    }

}
