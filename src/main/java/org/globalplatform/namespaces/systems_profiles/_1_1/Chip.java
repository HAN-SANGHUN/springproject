
package org.globalplatform.namespaces.systems_profiles._1_1;

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
 *         &lt;element ref="{http://namespaces.globalplatform.org/systems-profiles/1.1.2}Description" minOccurs="0"/>
 *         &lt;element ref="{http://namespaces.globalplatform.org/systems-profiles/1.1.2}Resources"/>
 *         &lt;element ref="{http://namespaces.globalplatform.org/systems-profiles/1.1.2}Powers"/>
 *         &lt;element ref="{http://namespaces.globalplatform.org/systems-profiles/1.1.2}Clock" minOccurs="0"/>
 *         &lt;element ref="{http://namespaces.globalplatform.org/systems-profiles/1.1.2}Communication"/>
 *         &lt;element ref="{http://namespaces.globalplatform.org/systems-profiles/1.1.2}CryptoEngine" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="Name" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="Model" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="Version" use="required" type="{http://namespaces.globalplatform.org/systems-profiles/1.1.2}version" />
 *       &lt;attribute name="ChipManufacturer" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "description",
    "resources",
    "powers",
    "clock",
    "communication",
    "cryptoEngine"
})
@XmlRootElement(name = "Chip")
public class Chip {

    @XmlElement(name = "Description")
    protected String description;
    @XmlElement(name = "Resources", required = true)
    protected Resources resources;
    @XmlElement(name = "Powers", required = true)
    protected Powers powers;
    @XmlElement(name = "Clock")
    protected Clock clock;
    @XmlElement(name = "Communication", required = true)
    protected Communication communication;
    @XmlElement(name = "CryptoEngine")
    protected CryptoEngine cryptoEngine;
    @XmlAttribute(name = "Name", required = true)
    protected String name;
    @XmlAttribute(name = "Model", required = true)
    protected String model;
    @XmlAttribute(name = "Version", required = true)
    protected String version;
    @XmlAttribute(name = "ChipManufacturer", required = true)
    protected String chipManufacturer;

    /**
     * description 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescription() {
        return description;
    }

    /**
     * description 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescription(String value) {
        this.description = value;
    }

    /**
     * resources 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link Resources }
     *     
     */
    public Resources getResources() {
        return resources;
    }

    /**
     * resources 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link Resources }
     *     
     */
    public void setResources(Resources value) {
        this.resources = value;
    }

    /**
     * powers 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link Powers }
     *     
     */
    public Powers getPowers() {
        return powers;
    }

    /**
     * powers 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link Powers }
     *     
     */
    public void setPowers(Powers value) {
        this.powers = value;
    }

    /**
     * clock 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link Clock }
     *     
     */
    public Clock getClock() {
        return clock;
    }

    /**
     * clock 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link Clock }
     *     
     */
    public void setClock(Clock value) {
        this.clock = value;
    }

    /**
     * communication 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link Communication }
     *     
     */
    public Communication getCommunication() {
        return communication;
    }

    /**
     * communication 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link Communication }
     *     
     */
    public void setCommunication(Communication value) {
        this.communication = value;
    }

    /**
     * cryptoEngine 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link CryptoEngine }
     *     
     */
    public CryptoEngine getCryptoEngine() {
        return cryptoEngine;
    }

    /**
     * cryptoEngine 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link CryptoEngine }
     *     
     */
    public void setCryptoEngine(CryptoEngine value) {
        this.cryptoEngine = value;
    }

    /**
     * name 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getName() {
        return name;
    }

    /**
     * name 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setName(String value) {
        this.name = value;
    }

    /**
     * model 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getModel() {
        return model;
    }

    /**
     * model 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setModel(String value) {
        this.model = value;
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
     * chipManufacturer 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getChipManufacturer() {
        return chipManufacturer;
    }

    /**
     * chipManufacturer 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setChipManufacturer(String value) {
        this.chipManufacturer = value;
    }

}
