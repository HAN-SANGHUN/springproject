
package com.konai.kmsexchangemessage;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>anonymous complex type�� ���� Java Ŭ�����Դϴ�.
 * 
 * <p>���� ��Ű�� ������ �� Ŭ������ ���ԵǴ� �ʿ��� �������� �����մϴ�.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element ref="{http://www.konai.com/KmsExchangeMessage}keyInfo" maxOccurs="2"/>
 *         &lt;element ref="{http://www.konai.com/KmsExchangeMessage}companyID"/>
 *         &lt;element name="workCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "keyInfo",
    "companyID",
    "workCode"
})
@XmlRootElement(name = "keyInfos")
public class KeyInfos {

    @XmlElement(namespace = "http://www.konai.com/KmsExchangeMessage", required = true)
    protected List<KeyInfo> keyInfo;
    @XmlElement(namespace = "http://www.konai.com/KmsExchangeMessage", required = true)
    protected String companyID;
    @XmlElement(required = true)
    protected String workCode;

    /**
     * Gets the value of the keyInfo property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the keyInfo property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getKeyInfo().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link KeyInfo }
     * 
     * 
     */
    public List<KeyInfo> getKeyInfo() {
        if (keyInfo == null) {
            keyInfo = new ArrayList<KeyInfo>();
        }
        return this.keyInfo;
    }

    /**
     * companyID �Ӽ��� ���� �����ɴϴ�.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCompanyID() {
        return companyID;
    }

    /**
     * companyID �Ӽ��� ���� �����մϴ�.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCompanyID(String value) {
        this.companyID = value;
    }

    /**
     * workCode �Ӽ��� ���� �����ɴϴ�.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getWorkCode() {
        return workCode;
    }

    /**
     * workCode �Ӽ��� ���� �����մϴ�.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setWorkCode(String value) {
        this.workCode = value;
    }

}
