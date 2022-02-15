
package com.konai.kmsexchangemessage;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>CompanyRequestType complex type에 대한 Java 클래스입니다.
 * 
 * <p>다음 스키마 단편이 이 클래스에 포함되는 필요한 콘텐츠를 지정합니다.
 * 
 * <pre>
 * &lt;complexType name="CompanyRequestType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element ref="{http://www.konai.com/KmsExchangeMessage}companyInfo"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CompanyRequestType", propOrder = {
    "companyInfo"
})
public class CompanyRequestType {

    @XmlElement(namespace = "http://www.konai.com/KmsExchangeMessage", required = true)
    protected CompanyInfo companyInfo;

    /**
     * companyInfo 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link CompanyInfo }
     *     
     */
    public CompanyInfo getCompanyInfo() {
        return companyInfo;
    }

    /**
     * companyInfo 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link CompanyInfo }
     *     
     */
    public void setCompanyInfo(CompanyInfo value) {
        this.companyInfo = value;
    }

}
