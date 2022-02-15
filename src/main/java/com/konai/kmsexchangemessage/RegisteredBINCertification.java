
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
 * <p>anonymous complex type�� ���� Java Ŭ�����Դϴ�.
 * 
 * <p>���� ��Ű�� ������ �� Ŭ������ ���ԵǴ� �ʿ��� �������� �����մϴ�.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.konai.com/KmsExchangeMessage}BINCertMessage">
 *       &lt;sequence>
 *         &lt;element name="certificationResponseFileName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="certificationResponseBinaryFile" type="{http://www.w3.org/2001/XMLSchema}hexBinary"/>
 *         &lt;element name="certificationResponseDate" type="{http://www.w3.org/2001/XMLSchema}date"/>
 *         &lt;element name="caPublicKeySize" type="{http://www.konai.com/KmsExchangeMessage}KeySize"/>
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
    "certificationResponseFileName",
    "certificationResponseBinaryFile",
    "certificationResponseDate",
    "caPublicKeySize",
    "binStatus"
})
@XmlRootElement(name = "RegisteredBINCertification")
public class RegisteredBINCertification
    extends BINCertMessage
{

    @XmlElement(required = true)
    protected String certificationResponseFileName;
    @XmlElement(required = true, type = String.class)
    @XmlJavaTypeAdapter(HexBinaryAdapter.class)
    @XmlSchemaType(name = "hexBinary")
    protected byte[] certificationResponseBinaryFile;
    @XmlElement(required = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar certificationResponseDate;
    protected int caPublicKeySize;
    @XmlElement(required = true)
    protected BinStatusCd binStatus;

    /**
     * certificationResponseFileName �Ӽ��� ���� �����ɴϴ�.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCertificationResponseFileName() {
        return certificationResponseFileName;
    }

    /**
     * certificationResponseFileName �Ӽ��� ���� �����մϴ�.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCertificationResponseFileName(String value) {
        this.certificationResponseFileName = value;
    }

    /**
     * certificationResponseBinaryFile �Ӽ��� ���� �����ɴϴ�.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public byte[] getCertificationResponseBinaryFile() {
        return certificationResponseBinaryFile;
    }

    /**
     * certificationResponseBinaryFile �Ӽ��� ���� �����մϴ�.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCertificationResponseBinaryFile(byte[] value) {
        this.certificationResponseBinaryFile = value;
    }

    /**
     * certificationResponseDate �Ӽ��� ���� �����ɴϴ�.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getCertificationResponseDate() {
        return certificationResponseDate;
    }

    /**
     * certificationResponseDate �Ӽ��� ���� �����մϴ�.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setCertificationResponseDate(XMLGregorianCalendar value) {
        this.certificationResponseDate = value;
    }

    /**
     * caPublicKeySize �Ӽ��� ���� �����ɴϴ�.
     * 
     */
    public int getCaPublicKeySize() {
        return caPublicKeySize;
    }

    /**
     * caPublicKeySize �Ӽ��� ���� �����մϴ�.
     * 
     */
    public void setCaPublicKeySize(int value) {
        this.caPublicKeySize = value;
    }

    /**
     * binStatus �Ӽ��� ���� �����ɴϴ�.
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
     * binStatus �Ӽ��� ���� �����մϴ�.
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
