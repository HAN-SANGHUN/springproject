
package org.globalplatform.namespaces.systems_profiles._1_1;

import java.math.BigInteger;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


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
 *         &lt;element ref="{http://namespaces.globalplatform.org/systems-profiles/1.1.2}Protocols"/>
 *       &lt;/sequence>
 *       &lt;attribute name="Unit" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="BaudRateMin" type="{http://www.w3.org/2001/XMLSchema}integer" />
 *       &lt;attribute name="BaudRateMax" type="{http://www.w3.org/2001/XMLSchema}integer" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "protocols"
})
@XmlRootElement(name = "Contactless")
public class Contactless {

    @XmlElement(name = "Protocols", required = true)
    protected Protocols protocols;
    @XmlAttribute(name = "Unit")
    protected String unit;
    @XmlAttribute(name = "BaudRateMin")
    protected BigInteger baudRateMin;
    @XmlAttribute(name = "BaudRateMax")
    protected BigInteger baudRateMax;

    /**
     * protocols 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link Protocols }
     *     
     */
    public Protocols getProtocols() {
        return protocols;
    }

    /**
     * protocols 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link Protocols }
     *     
     */
    public void setProtocols(Protocols value) {
        this.protocols = value;
    }

    /**
     * unit 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUnit() {
        return unit;
    }

    /**
     * unit 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUnit(String value) {
        this.unit = value;
    }

    /**
     * baudRateMin 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getBaudRateMin() {
        return baudRateMin;
    }

    /**
     * baudRateMin 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setBaudRateMin(BigInteger value) {
        this.baudRateMin = value;
    }

    /**
     * baudRateMax 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getBaudRateMax() {
        return baudRateMax;
    }

    /**
     * baudRateMax 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setBaudRateMax(BigInteger value) {
        this.baudRateMax = value;
    }

}
