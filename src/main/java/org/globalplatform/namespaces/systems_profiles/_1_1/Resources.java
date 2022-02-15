
package org.globalplatform.namespaces.systems_profiles._1_1;

import java.math.BigInteger;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
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
 *       &lt;attribute name="Unit" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="ROM" use="required" type="{http://www.w3.org/2001/XMLSchema}integer" />
 *       &lt;attribute name="RAM" use="required" type="{http://www.w3.org/2001/XMLSchema}integer" />
 *       &lt;attribute name="EEPROM" use="required" type="{http://www.w3.org/2001/XMLSchema}integer" />
 *       &lt;attribute name="Flash" use="required" type="{http://www.w3.org/2001/XMLSchema}integer" />
 *       &lt;attribute name="GarbageCollection" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "")
@XmlRootElement(name = "Resources")
public class Resources {

    @XmlAttribute(name = "Unit", required = true)
    protected String unit;
    @XmlAttribute(name = "ROM", required = true)
    protected BigInteger rom;
    @XmlAttribute(name = "RAM", required = true)
    protected BigInteger ram;
    @XmlAttribute(name = "EEPROM", required = true)
    protected BigInteger eeprom;
    @XmlAttribute(name = "Flash", required = true)
    protected BigInteger flash;
    @XmlAttribute(name = "GarbageCollection")
    protected Boolean garbageCollection;

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
     * rom 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getROM() {
        return rom;
    }

    /**
     * rom 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setROM(BigInteger value) {
        this.rom = value;
    }

    /**
     * ram 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getRAM() {
        return ram;
    }

    /**
     * ram 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setRAM(BigInteger value) {
        this.ram = value;
    }

    /**
     * eeprom 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getEEPROM() {
        return eeprom;
    }

    /**
     * eeprom 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setEEPROM(BigInteger value) {
        this.eeprom = value;
    }

    /**
     * flash 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getFlash() {
        return flash;
    }

    /**
     * flash 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setFlash(BigInteger value) {
        this.flash = value;
    }

    /**
     * garbageCollection 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isGarbageCollection() {
        return garbageCollection;
    }

    /**
     * garbageCollection 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setGarbageCollection(Boolean value) {
        this.garbageCollection = value;
    }

}
