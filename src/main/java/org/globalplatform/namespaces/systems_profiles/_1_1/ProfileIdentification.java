
package org.globalplatform.namespaces.systems_profiles._1_1;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;attGroup ref="{http://namespaces.globalplatform.org/systems-messaging/2.1.0}SpecificCardID"/>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "")
@XmlRootElement(name = "ProfileIdentification")
public class ProfileIdentification
    implements Serializable
{

    @XmlAttribute(name = "CRN")
    @XmlSchemaType(name = "anySimpleType")
    protected String crn;
    @XmlAttribute(name = "AID")
    @XmlSchemaType(name = "anySimpleType")
    protected String aid;
    @XmlAttribute(name = "KeyID")
    @XmlSchemaType(name = "anySimpleType")
    protected String keyID;
    @XmlAttribute(name = "LoadFileName")
    @XmlSchemaType(name = "anySimpleType")
    protected String loadFileName;
    @XmlAttribute(name = "OtherIDFieldLabel")
    @XmlSchemaType(name = "anySimpleType")
    protected String otherIDFieldLabel;
    @XmlAttribute(name = "OtherIDFieldValue")
    @XmlSchemaType(name = "anySimpleType")
    protected String otherIDFieldValue;

    /**
     * Gets the value of the crn property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCRN() {
        return crn;
    }

    /**
     * Sets the value of the crn property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCRN(String value) {
        this.crn = value;
    }

    /**
     * Gets the value of the aid property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAID() {
        return aid;
    }

    /**
     * Sets the value of the aid property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAID(String value) {
        this.aid = value;
    }

    /**
     * Gets the value of the keyID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getKeyID() {
        return keyID;
    }

    /**
     * Sets the value of the keyID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setKeyID(String value) {
        this.keyID = value;
    }

    /**
     * Gets the value of the loadFileName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLoadFileName() {
        return loadFileName;
    }

    /**
     * Sets the value of the loadFileName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLoadFileName(String value) {
        this.loadFileName = value;
    }

    /**
     * Gets the value of the otherIDFieldLabel property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOtherIDFieldLabel() {
        return otherIDFieldLabel;
    }

    /**
     * Sets the value of the otherIDFieldLabel property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOtherIDFieldLabel(String value) {
        this.otherIDFieldLabel = value;
    }

    /**
     * Gets the value of the otherIDFieldValue property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOtherIDFieldValue() {
        return otherIDFieldValue;
    }

    /**
     * Sets the value of the otherIDFieldValue property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOtherIDFieldValue(String value) {
        this.otherIDFieldValue = value;
    }

}
