
package org.globalplatform.namespaces.systems_profiles._1_1;

import java.math.BigInteger;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.HexBinaryAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * <p>anonymous complex type에 대한 Java 클래스입니다.
 * 
 * <p>다음 스키마 단편이 이 클래스에 포함되는 필요한 콘텐츠를 지정합니다.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;attribute name="ProfileID" use="required" type="{http://namespaces.globalplatform.org/systems-profiles/1.1.2}uniqueID" />
 *       &lt;attribute name="Label" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="LifeCycle" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="Masked" use="required" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *       &lt;attribute name="SecurityDomainAid" type="{http://www.w3.org/2001/XMLSchema}hexBinary" />
 *       &lt;attribute name="Order" type="{http://www.w3.org/2001/XMLSchema}integer" />
 *       &lt;attribute name="NonVolatileCodeSpaceLimit" type="{http://www.w3.org/2001/XMLSchema}integer" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "")
@XmlRootElement(name = "LoadFileInstance")
public class LoadFileInstance {

    @XmlAttribute(name = "ProfileID", required = true)
    @XmlJavaTypeAdapter(HexBinaryAdapter.class)
    protected byte[] profileID;
    @XmlAttribute(name = "Label")
    protected String label;
    @XmlAttribute(name = "LifeCycle", required = true)
    protected String lifeCycle;
    @XmlAttribute(name = "Masked", required = true)
    protected boolean masked;
    @XmlAttribute(name = "SecurityDomainAid")
    @XmlJavaTypeAdapter(HexBinaryAdapter.class)
    @XmlSchemaType(name = "hexBinary")
    protected byte[] securityDomainAid;
    @XmlAttribute(name = "Order")
    protected BigInteger order;
    @XmlAttribute(name = "NonVolatileCodeSpaceLimit")
    protected BigInteger nonVolatileCodeSpaceLimit;

    /**
     * profileID 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public byte[] getProfileID() {
        return profileID;
    }

    /**
     * profileID 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProfileID(byte[] value) {
        this.profileID = value;
    }

    /**
     * label 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLabel() {
        return label;
    }

    /**
     * label 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLabel(String value) {
        this.label = value;
    }

    /**
     * lifeCycle 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLifeCycle() {
        return lifeCycle;
    }

    /**
     * lifeCycle 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLifeCycle(String value) {
        this.lifeCycle = value;
    }

    /**
     * masked 속성의 값을 가져옵니다.
     * 
     */
    public boolean isMasked() {
        return masked;
    }

    /**
     * masked 속성의 값을 설정합니다.
     * 
     */
    public void setMasked(boolean value) {
        this.masked = value;
    }

    /**
     * securityDomainAid 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public byte[] getSecurityDomainAid() {
        return securityDomainAid;
    }

    /**
     * securityDomainAid 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSecurityDomainAid(byte[] value) {
        this.securityDomainAid = value;
    }

    /**
     * order 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getOrder() {
        return order;
    }

    /**
     * order 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setOrder(BigInteger value) {
        this.order = value;
    }

    /**
     * nonVolatileCodeSpaceLimit 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getNonVolatileCodeSpaceLimit() {
        return nonVolatileCodeSpaceLimit;
    }

    /**
     * nonVolatileCodeSpaceLimit 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setNonVolatileCodeSpaceLimit(BigInteger value) {
        this.nonVolatileCodeSpaceLimit = value;
    }

}
