
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
 *         &lt;element ref="{http://namespaces.globalplatform.org/systems-profiles/1.1.2}CardManufacturer"/>
 *         &lt;element ref="{http://namespaces.globalplatform.org/systems-profiles/1.1.2}Chip"/>
 *         &lt;element ref="{http://namespaces.globalplatform.org/systems-profiles/1.1.2}Platform"/>
 *       &lt;/sequence>
 *       &lt;attribute name="Name" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="Version" use="required" type="{http://namespaces.globalplatform.org/systems-profiles/1.1.2}version" />
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
    "cardManufacturer",
    "chip",
    "platform"
})
@XmlRootElement(name = "CardManufacturerProduct")
public class CardManufacturerProduct {

    @XmlElement(name = "Description")
    protected String description;
    @XmlElement(name = "CardManufacturer", required = true)
    protected CardManufacturer cardManufacturer;
    @XmlElement(name = "Chip", required = true)
    protected Chip chip;
    @XmlElement(name = "Platform", required = true)
    protected Platform platform;
    @XmlAttribute(name = "Name", required = true)
    protected String name;
    @XmlAttribute(name = "Version", required = true)
    protected String version;

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
     * cardManufacturer 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link CardManufacturer }
     *     
     */
    public CardManufacturer getCardManufacturer() {
        return cardManufacturer;
    }

    /**
     * cardManufacturer 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link CardManufacturer }
     *     
     */
    public void setCardManufacturer(CardManufacturer value) {
        this.cardManufacturer = value;
    }

    /**
     * chip 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link Chip }
     *     
     */
    public Chip getChip() {
        return chip;
    }

    /**
     * chip 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link Chip }
     *     
     */
    public void setChip(Chip value) {
        this.chip = value;
    }

    /**
     * platform 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link Platform }
     *     
     */
    public Platform getPlatform() {
        return platform;
    }

    /**
     * platform 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link Platform }
     *     
     */
    public void setPlatform(Platform value) {
        this.platform = value;
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

}
