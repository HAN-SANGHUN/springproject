
package org.globalplatform.namespaces.systems_profiles._1_1;

import java.math.BigInteger;
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
 * <p>anonymous complex type에 대한 Java 클래스입니다.
 * 
 * <p>다음 스키마 단편이 이 클래스에 포함되는 필요한 콘텐츠를 지정합니다.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element ref="{http://namespaces.globalplatform.org/systems-profiles/1.1.2}Privileges" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="ProfileID" use="required" type="{http://namespaces.globalplatform.org/systems-profiles/1.1.2}uniqueID" />
 *       &lt;attribute name="Label" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="AID" use="required" type="{http://www.w3.org/2001/XMLSchema}hexBinary" />
 *       &lt;attribute name="LifeCycle" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="SecurityDomain" type="{http://www.w3.org/2001/XMLSchema}hexBinary" />
 *       &lt;attribute name="VolatileDataSpaceLimit" type="{http://www.w3.org/2001/XMLSchema}integer" />
 *       &lt;attribute name="NonVolatileDataSpaceLimit" type="{http://www.w3.org/2001/XMLSchema}integer" />
 *       &lt;attribute name="AppSpecificInstallParams" type="{http://www.w3.org/2001/XMLSchema}hexBinary" />
 *       &lt;attribute name="Order" type="{http://www.w3.org/2001/XMLSchema}integer" />
 *       &lt;attribute name="LoadFileID" use="required" type="{http://www.w3.org/2001/XMLSchema}hexBinary" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "privileges"
})
@XmlRootElement(name = "ApplicationInstance")
public class ApplicationInstance {

    @XmlElement(name = "Privileges")
    protected Privileges privileges;
    @XmlAttribute(name = "ProfileID", required = true)
    @XmlJavaTypeAdapter(HexBinaryAdapter.class)
    protected byte[] profileID;
    @XmlAttribute(name = "Label")
    protected String label;
    @XmlAttribute(name = "AID", required = true)
    @XmlJavaTypeAdapter(HexBinaryAdapter.class)
    @XmlSchemaType(name = "hexBinary")
    protected byte[] aid;
    @XmlAttribute(name = "LifeCycle", required = true)
    protected String lifeCycle;
    @XmlAttribute(name = "SecurityDomain")
    @XmlJavaTypeAdapter(HexBinaryAdapter.class)
    @XmlSchemaType(name = "hexBinary")
    protected byte[] securityDomain;
    @XmlAttribute(name = "VolatileDataSpaceLimit")
    protected BigInteger volatileDataSpaceLimit;
    @XmlAttribute(name = "NonVolatileDataSpaceLimit")
    protected BigInteger nonVolatileDataSpaceLimit;
    @XmlAttribute(name = "AppSpecificInstallParams")
    @XmlJavaTypeAdapter(HexBinaryAdapter.class)
    @XmlSchemaType(name = "hexBinary")
    protected byte[] appSpecificInstallParams;
    @XmlAttribute(name = "Order")
    protected BigInteger order;
    @XmlAttribute(name = "LoadFileID", required = true)
    @XmlJavaTypeAdapter(HexBinaryAdapter.class)
    @XmlSchemaType(name = "hexBinary")
    protected byte[] loadFileID;

    /**
     * privileges 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link Privileges }
     *     
     */
    public Privileges getPrivileges() {
        return privileges;
    }

    /**
     * privileges 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link Privileges }
     *     
     */
    public void setPrivileges(Privileges value) {
        this.privileges = value;
    }

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
     * aid 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public byte[] getAID() {
        return aid;
    }

    /**
     * aid 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAID(byte[] value) {
        this.aid = value;
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
     * securityDomain 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public byte[] getSecurityDomain() {
        return securityDomain;
    }

    /**
     * securityDomain 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSecurityDomain(byte[] value) {
        this.securityDomain = value;
    }

    /**
     * volatileDataSpaceLimit 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getVolatileDataSpaceLimit() {
        return volatileDataSpaceLimit;
    }

    /**
     * volatileDataSpaceLimit 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setVolatileDataSpaceLimit(BigInteger value) {
        this.volatileDataSpaceLimit = value;
    }

    /**
     * nonVolatileDataSpaceLimit 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getNonVolatileDataSpaceLimit() {
        return nonVolatileDataSpaceLimit;
    }

    /**
     * nonVolatileDataSpaceLimit 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setNonVolatileDataSpaceLimit(BigInteger value) {
        this.nonVolatileDataSpaceLimit = value;
    }

    /**
     * appSpecificInstallParams 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public byte[] getAppSpecificInstallParams() {
        return appSpecificInstallParams;
    }

    /**
     * appSpecificInstallParams 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAppSpecificInstallParams(byte[] value) {
        this.appSpecificInstallParams = value;
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
     * loadFileID 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public byte[] getLoadFileID() {
        return loadFileID;
    }

    /**
     * loadFileID 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLoadFileID(byte[] value) {
        this.loadFileID = value;
    }

}
