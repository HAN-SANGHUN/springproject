
package com.konai.kmsexchangemessage;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.HexBinaryAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>anonymous complex type에 대한 Java 클래스입니다.
 * 
 * <p>다음 스키마 단편이 이 클래스에 포함되는 필요한 콘텐츠를 지정합니다.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.konai.com/KmsExchangeMessage}BINCertMessage">
 *       &lt;sequence>
 *         &lt;element ref="{http://www.konai.com/KmsExchangeMessage}trackingNo"/>
 *         &lt;element name="issuerPublicKeySize" type="{http://www.konai.com/KmsExchangeMessage}KeySize"/>
 *         &lt;element name="certificationRequestFileName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="certificationRequestBinaryFile" type="{http://www.w3.org/2001/XMLSchema}hexBinary"/>
 *         &lt;element name="certificationRequestDate" type="{http://www.w3.org/2001/XMLSchema}date"/>
 *         &lt;element name="issuerPublicKeyExponentValue" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="certificationHashValue">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;minLength value="1"/>
 *               &lt;maxLength value="256"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="binStatus" type="{http://www.konai.com/KmsExchangeMessage}BinStatusCd"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "trackingNo",
    "issuerPublicKeySize",
    "certificationRequestFileName",
    "certificationRequestBinaryFile",
    "certificationRequestDate",
    "issuerPublicKeyExponentValue",
    "certificationHashValue",
    "binStatus"
})
@XmlRootElement(name = "RequestedBINCertification")
public class RequestedBINCertification
    extends BINCertMessage
{

    @XmlElement(namespace = "http://www.konai.com/KmsExchangeMessage", required = true)
    protected String trackingNo;
    protected int issuerPublicKeySize;
    @XmlElement(required = true)
    protected String certificationRequestFileName;
    @XmlElement(required = true, type = String.class)
    @XmlJavaTypeAdapter(HexBinaryAdapter.class)
    @XmlSchemaType(name = "hexBinary")
    protected byte[] certificationRequestBinaryFile;
    @XmlElement(required = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar certificationRequestDate;
    @XmlElement(required = true)
    protected String issuerPublicKeyExponentValue;
    @XmlElement(required = true)
    protected String certificationHashValue;
    @XmlElement(required = true)
    protected BinStatusCd binStatus;

    /**
     * trackingNo 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTrackingNo() {
        return trackingNo;
    }

    /**
     * trackingNo 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTrackingNo(String value) {
        this.trackingNo = value;
    }

    /**
     * issuerPublicKeySize 속성의 값을 가져옵니다.
     * 
     */
    public int getIssuerPublicKeySize() {
        return issuerPublicKeySize;
    }

    /**
     * issuerPublicKeySize 속성의 값을 설정합니다.
     * 
     */
    public void setIssuerPublicKeySize(int value) {
        this.issuerPublicKeySize = value;
    }

    /**
     * certificationRequestFileName 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCertificationRequestFileName() {
        return certificationRequestFileName;
    }

    /**
     * certificationRequestFileName 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCertificationRequestFileName(String value) {
        this.certificationRequestFileName = value;
    }

    /**
     * certificationRequestBinaryFile 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public byte[] getCertificationRequestBinaryFile() {
        return certificationRequestBinaryFile;
    }

    /**
     * certificationRequestBinaryFile 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCertificationRequestBinaryFile(byte[] value) {
        this.certificationRequestBinaryFile = value;
    }

    /**
     * certificationRequestDate 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getCertificationRequestDate() {
        return certificationRequestDate;
    }

    /**
     * certificationRequestDate 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setCertificationRequestDate(XMLGregorianCalendar value) {
        this.certificationRequestDate = value;
    }

    /**
     * issuerPublicKeyExponentValue 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIssuerPublicKeyExponentValue() {
        return issuerPublicKeyExponentValue;
    }

    /**
     * issuerPublicKeyExponentValue 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIssuerPublicKeyExponentValue(String value) {
        this.issuerPublicKeyExponentValue = value;
    }

    /**
     * certificationHashValue 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCertificationHashValue() {
        return certificationHashValue;
    }

    /**
     * certificationHashValue 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCertificationHashValue(String value) {
        this.certificationHashValue = value;
    }

    /**
     * binStatus 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link BinStatusCd }
     *     
     */
    public BinStatusCd getBinStatus() {
        return binStatus;
    }

    /**
     * binStatus 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link BinStatusCd }
     *     
     */
    public void setBinStatus(BinStatusCd value) {
        this.binStatus = value;
    }

}
