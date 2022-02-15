
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
 *       &lt;attribute name="AID" use="required" type="{http://www.w3.org/2001/XMLSchema}hexBinary" />
 *       &lt;attribute name="Version" use="required" type="{http://namespaces.globalplatform.org/systems-profiles/1.1.2}version" />
 *       &lt;attribute name="NonVolatileCodeSpaceLimit" type="{http://www.w3.org/2001/XMLSchema}integer" />
 *       &lt;attribute name="NonVolatileDataSpaceMin" type="{http://www.w3.org/2001/XMLSchema}integer" />
 *       &lt;attribute name="VolatileDataSpaceMin" type="{http://www.w3.org/2001/XMLSchema}integer" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "")
@XmlRootElement(name = "LoadFileInfo")
public class LoadFileInfo {

    @XmlAttribute(name = "AID", required = true)
    @XmlJavaTypeAdapter(HexBinaryAdapter.class)
    @XmlSchemaType(name = "hexBinary")
    protected byte[] aid;
    @XmlAttribute(name = "Version", required = true)
    protected String version;
    @XmlAttribute(name = "NonVolatileCodeSpaceLimit")
    protected BigInteger nonVolatileCodeSpaceLimit;
    @XmlAttribute(name = "NonVolatileDataSpaceMin")
    protected BigInteger nonVolatileDataSpaceMin;
    @XmlAttribute(name = "VolatileDataSpaceMin")
    protected BigInteger volatileDataSpaceMin;

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

}
