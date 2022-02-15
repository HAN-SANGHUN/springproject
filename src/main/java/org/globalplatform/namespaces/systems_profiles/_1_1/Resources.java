
package org.globalplatform.namespaces.systems_profiles._1_1;

import java.math.BigInteger;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>anonymous complex type�� ���� Java Ŭ�����Դϴ�.
 * 
 * <p>���� ��Ű�� ������ �� Ŭ������ ���ԵǴ� �ʿ��� �������� �����մϴ�.
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
     * unit �Ӽ��� ���� �����ɴϴ�.
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
     * unit �Ӽ��� ���� �����մϴ�.
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
     * rom �Ӽ��� ���� �����ɴϴ�.
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
     * rom �Ӽ��� ���� �����մϴ�.
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
     * ram �Ӽ��� ���� �����ɴϴ�.
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
     * ram �Ӽ��� ���� �����մϴ�.
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
     * eeprom �Ӽ��� ���� �����ɴϴ�.
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
     * eeprom �Ӽ��� ���� �����մϴ�.
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
     * flash �Ӽ��� ���� �����ɴϴ�.
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
     * flash �Ӽ��� ���� �����մϴ�.
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
     * garbageCollection �Ӽ��� ���� �����ɴϴ�.
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
     * garbageCollection �Ӽ��� ���� �����մϴ�.
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
