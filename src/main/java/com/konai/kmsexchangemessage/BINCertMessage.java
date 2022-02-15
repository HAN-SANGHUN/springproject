
package com.konai.kmsexchangemessage;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>BINCertMessage complex type�� ���� Java Ŭ�����Դϴ�.
 * 
 * <p>���� ��Ű�� ������ �� Ŭ������ ���ԵǴ� �ʿ��� �������� �����մϴ�.
 * 
 * <pre>
 * &lt;complexType name="BINCertMessage">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element ref="{http://www.konai.com/KmsExchangeMessage}companyID"/>
 *         &lt;element ref="{http://www.konai.com/KmsExchangeMessage}brandTypeCd"/>
 *         &lt;element ref="{http://www.konai.com/KmsExchangeMessage}binNO"/>
 *         &lt;element name="issuerPublicKeyIndex" type="{http://www.konai.com/KmsExchangeMessage}KeyIndex"/>
 *         &lt;element name="expireDate" type="{http://www.w3.org/2001/XMLSchema}date"/>
 *         &lt;element name="workCode" type="{http://www.konai.com/KmsExchangeMessage}WorkCode"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "BINCertMessage", propOrder = {
    "companyID",
    "brandTypeCd",
    "binNO",
    "issuerPublicKeyIndex",
    "expireDate",
    "workCode"
})
@XmlSeeAlso({
    RequestedBINCertification.class,
    RegisteredBINCertification.class
})
public abstract class BINCertMessage {

    @XmlElement(namespace = "http://www.konai.com/KmsExchangeMessage", required = true)
    protected String companyID;
    @XmlElement(namespace = "http://www.konai.com/KmsExchangeMessage", required = true)
    protected String brandTypeCd;
    @XmlElement(namespace = "http://www.konai.com/KmsExchangeMessage", required = true)
    protected String binNO;
    protected int issuerPublicKeyIndex;
    @XmlElement(required = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar expireDate;
    @XmlElement(required = true)
    protected WorkCode workCode;

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
     * brandTypeCd �Ӽ��� ���� �����ɴϴ�.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBrandTypeCd() {
        return brandTypeCd;
    }

    /**
     * brandTypeCd �Ӽ��� ���� �����մϴ�.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBrandTypeCd(String value) {
        this.brandTypeCd = value;
    }

    /**
     * binNO �Ӽ��� ���� �����ɴϴ�.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBinNO() {
        return binNO;
    }

    /**
     * binNO �Ӽ��� ���� �����մϴ�.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBinNO(String value) {
        this.binNO = value;
    }

    /**
     * issuerPublicKeyIndex �Ӽ��� ���� �����ɴϴ�.
     * 
     */
    public int getIssuerPublicKeyIndex() {
        return issuerPublicKeyIndex;
    }

    /**
     * issuerPublicKeyIndex �Ӽ��� ���� �����մϴ�.
     * 
     */
    public void setIssuerPublicKeyIndex(int value) {
        this.issuerPublicKeyIndex = value;
    }

    /**
     * expireDate �Ӽ��� ���� �����ɴϴ�.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getExpireDate() {
        return expireDate;
    }

    /**
     * expireDate �Ӽ��� ���� �����մϴ�.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setExpireDate(XMLGregorianCalendar value) {
        this.expireDate = value;
    }

    /**
     * workCode �Ӽ��� ���� �����ɴϴ�.
     * 
     * @return
     *     possible object is
     *     {@link WorkCode }
     *     
     */
    public WorkCode getWorkCode() {
        return workCode;
    }

    /**
     * workCode �Ӽ��� ���� �����մϴ�.
     * 
     * @param value
     *     allowed object is
     *     {@link WorkCode }
     *     
     */
    public void setWorkCode(WorkCode value) {
        this.workCode = value;
    }

}
