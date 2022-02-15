
package org.globalplatform.namespaces.systems_profiles._1_1;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.HexBinaryAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


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
 *         &lt;element ref="{http://namespaces.globalplatform.org/systems-profiles/1.1.2}OpenSecureChannel" minOccurs="0"/>
 *         &lt;element ref="{http://namespaces.globalplatform.org/systems-profiles/1.1.2}CloseSecureChannel" minOccurs="0"/>
 *         &lt;element ref="{http://namespaces.globalplatform.org/systems-profiles/1.1.2}Wrap" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="SecureChannel" use="required" type="{http://namespaces.globalplatform.org/systems-profiles/1.1.2}secureChannelProtocol" />
 *       &lt;attribute name="Option" type="{http://www.w3.org/2001/XMLSchema}hexBinary" />
 *       &lt;attribute name="SecurityLevel" type="{http://namespaces.globalplatform.org/systems-profiles/1.1.2}securityLevel" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "openSecureChannel",
    "closeSecureChannel",
    "wrap"
})
@XmlRootElement(name = "SecureChannel")
public class SecureChannel {

    @XmlElement(name = "OpenSecureChannel")
    protected OpenSecureChannel openSecureChannel;
    @XmlElement(name = "CloseSecureChannel")
    protected CloseSecureChannel closeSecureChannel;
    @XmlElement(name = "Wrap")
    protected Wrap wrap;
    @XmlAttribute(name = "SecureChannel", required = true)
    protected SecureChannelProtocol secureChannel;
    @XmlAttribute(name = "Option")
    @XmlJavaTypeAdapter(HexBinaryAdapter.class)
    @XmlSchemaType(name = "hexBinary")
    protected byte[] option;
    @XmlAttribute(name = "SecurityLevel")
    protected SecurityLevel securityLevel;

    /**
     * openSecureChannel �Ӽ��� ���� �����ɴϴ�.
     * 
     * @return
     *     possible object is
     *     {@link OpenSecureChannel }
     *     
     */
    public OpenSecureChannel getOpenSecureChannel() {
        return openSecureChannel;
    }

    /**
     * openSecureChannel �Ӽ��� ���� �����մϴ�.
     * 
     * @param value
     *     allowed object is
     *     {@link OpenSecureChannel }
     *     
     */
    public void setOpenSecureChannel(OpenSecureChannel value) {
        this.openSecureChannel = value;
    }

    /**
     * closeSecureChannel �Ӽ��� ���� �����ɴϴ�.
     * 
     * @return
     *     possible object is
     *     {@link CloseSecureChannel }
     *     
     */
    public CloseSecureChannel getCloseSecureChannel() {
        return closeSecureChannel;
    }

    /**
     * closeSecureChannel �Ӽ��� ���� �����մϴ�.
     * 
     * @param value
     *     allowed object is
     *     {@link CloseSecureChannel }
     *     
     */
    public void setCloseSecureChannel(CloseSecureChannel value) {
        this.closeSecureChannel = value;
    }

    /**
     * wrap �Ӽ��� ���� �����ɴϴ�.
     * 
     * @return
     *     possible object is
     *     {@link Wrap }
     *     
     */
    public Wrap getWrap() {
        return wrap;
    }

    /**
     * wrap �Ӽ��� ���� �����մϴ�.
     * 
     * @param value
     *     allowed object is
     *     {@link Wrap }
     *     
     */
    public void setWrap(Wrap value) {
        this.wrap = value;
    }

    /**
     * secureChannel �Ӽ��� ���� �����ɴϴ�.
     * 
     * @return
     *     possible object is
     *     {@link SecureChannelProtocol }
     *     
     */
    public SecureChannelProtocol getSecureChannel() {
        return secureChannel;
    }

    /**
     * secureChannel �Ӽ��� ���� �����մϴ�.
     * 
     * @param value
     *     allowed object is
     *     {@link SecureChannelProtocol }
     *     
     */
    public void setSecureChannel(SecureChannelProtocol value) {
        this.secureChannel = value;
    }

    /**
     * option �Ӽ��� ���� �����ɴϴ�.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public byte[] getOption() {
        return option;
    }

    /**
     * option �Ӽ��� ���� �����մϴ�.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOption(byte[] value) {
        this.option = value;
    }

    /**
     * securityLevel �Ӽ��� ���� �����ɴϴ�.
     * 
     * @return
     *     possible object is
     *     {@link SecurityLevel }
     *     
     */
    public SecurityLevel getSecurityLevel() {
        return securityLevel;
    }

    /**
     * securityLevel �Ӽ��� ���� �����մϴ�.
     * 
     * @param value
     *     allowed object is
     *     {@link SecurityLevel }
     *     
     */
    public void setSecurityLevel(SecurityLevel value) {
        this.securityLevel = value;
    }

}
