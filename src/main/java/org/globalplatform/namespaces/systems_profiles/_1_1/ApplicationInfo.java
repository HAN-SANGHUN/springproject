
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
 *         &lt;element ref="{http://namespaces.globalplatform.org/systems-profiles/1.1.2}Privileges"/>
 *         &lt;element ref="{http://namespaces.globalplatform.org/systems-profiles/1.1.2}LifeCycles"/>
 *       &lt;/sequence>
 *       &lt;attribute name="Version" use="required" type="{http://namespaces.globalplatform.org/systems-profiles/1.1.2}version" />
 *       &lt;attribute name="Type" use="required" type="{http://namespaces.globalplatform.org/systems-profiles/1.1.2}application" />
 *       &lt;attribute name="Subtype" use="required" type="{http://namespaces.globalplatform.org/systems-profiles/1.1.2}applicationsubtype" />
 *       &lt;attribute name="Owner" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="Developer" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="Provider" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="Domain" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="VolatileDataSpaceMin" use="required" type="{http://www.w3.org/2001/XMLSchema}integer" />
 *       &lt;attribute name="NonVolatileDataSpaceMin" use="required" type="{http://www.w3.org/2001/XMLSchema}integer" />
 *       &lt;attribute name="VolatileDataSpaceMax" type="{http://www.w3.org/2001/XMLSchema}integer" />
 *       &lt;attribute name="NonVolatileDataSpaceMax" type="{http://www.w3.org/2001/XMLSchema}integer" />
 *       &lt;attribute name="AppSpecificInstallParams" type="{http://www.w3.org/2001/XMLSchema}hexBinary" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "privileges",
    "lifeCycles"
})
@XmlRootElement(name = "ApplicationInfo")
public class ApplicationInfo {

    @XmlElement(name = "Privileges", required = true)
    protected Privileges privileges;
    @XmlElement(name = "LifeCycles", required = true)
    protected LifeCycles lifeCycles;
    @XmlAttribute(name = "Version", required = true)
    protected String version;
    @XmlAttribute(name = "Type", required = true)
    protected Application type;
    @XmlAttribute(name = "Subtype", required = true)
    protected Applicationsubtype subtype;
    @XmlAttribute(name = "Owner", required = true)
    protected String owner;
    @XmlAttribute(name = "Developer", required = true)
    protected String developer;
    @XmlAttribute(name = "Provider", required = true)
    protected String provider;
    @XmlAttribute(name = "Domain", required = true)
    protected String domain;
    @XmlAttribute(name = "VolatileDataSpaceMin", required = true)
    protected BigInteger volatileDataSpaceMin;
    @XmlAttribute(name = "NonVolatileDataSpaceMin", required = true)
    protected BigInteger nonVolatileDataSpaceMin;
    @XmlAttribute(name = "VolatileDataSpaceMax")
    protected BigInteger volatileDataSpaceMax;
    @XmlAttribute(name = "NonVolatileDataSpaceMax")
    protected BigInteger nonVolatileDataSpaceMax;
    @XmlAttribute(name = "AppSpecificInstallParams")
    @XmlJavaTypeAdapter(HexBinaryAdapter.class)
    @XmlSchemaType(name = "hexBinary")
    protected byte[] appSpecificInstallParams;

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
     * lifeCycles 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link LifeCycles }
     *     
     */
    public LifeCycles getLifeCycles() {
        return lifeCycles;
    }

    /**
     * lifeCycles 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link LifeCycles }
     *     
     */
    public void setLifeCycles(LifeCycles value) {
        this.lifeCycles = value;
    }

    /**
     * version 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVersion() {
        return version;
    }

    /**
     * version 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVersion(String value) {
        this.version = value;
    }

    /**
     * type 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link Application }
     *     
     */
    public Application getType() {
        return type;
    }

    /**
     * type 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link Application }
     *     
     */
    public void setType(Application value) {
        this.type = value;
    }

    /**
     * subtype 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link Applicationsubtype }
     *     
     */
    public Applicationsubtype getSubtype() {
        return subtype;
    }

    /**
     * subtype 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link Applicationsubtype }
     *     
     */
    public void setSubtype(Applicationsubtype value) {
        this.subtype = value;
    }

    /**
     * owner 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOwner() {
        return owner;
    }

    /**
     * owner 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOwner(String value) {
        this.owner = value;
    }

    /**
     * developer 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDeveloper() {
        return developer;
    }

    /**
     * developer 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDeveloper(String value) {
        this.developer = value;
    }

    /**
     * provider 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProvider() {
        return provider;
    }

    /**
     * provider 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProvider(String value) {
        this.provider = value;
    }

    /**
     * domain 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDomain() {
        return domain;
    }

    /**
     * domain 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDomain(String value) {
        this.domain = value;
    }

    /**
     * volatileDataSpaceMin 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getVolatileDataSpaceMin() {
        return volatileDataSpaceMin;
    }

    /**
     * volatileDataSpaceMin 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setVolatileDataSpaceMin(BigInteger value) {
        this.volatileDataSpaceMin = value;
    }

    /**
     * nonVolatileDataSpaceMin 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getNonVolatileDataSpaceMin() {
        return nonVolatileDataSpaceMin;
    }

    /**
     * nonVolatileDataSpaceMin 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setNonVolatileDataSpaceMin(BigInteger value) {
        this.nonVolatileDataSpaceMin = value;
    }

    /**
     * volatileDataSpaceMax 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getVolatileDataSpaceMax() {
        return volatileDataSpaceMax;
    }

    /**
     * volatileDataSpaceMax 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setVolatileDataSpaceMax(BigInteger value) {
        this.volatileDataSpaceMax = value;
    }

    /**
     * nonVolatileDataSpaceMax 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getNonVolatileDataSpaceMax() {
        return nonVolatileDataSpaceMax;
    }

    /**
     * nonVolatileDataSpaceMax 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setNonVolatileDataSpaceMax(BigInteger value) {
        this.nonVolatileDataSpaceMax = value;
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

}
