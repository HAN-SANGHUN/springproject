
package org.globalplatform.namespaces.systems_profiles._1_1;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
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
 *         &lt;element ref="{http://namespaces.globalplatform.org/systems-profiles/1.1.2}CardDesignOrientation"/>
 *         &lt;element ref="{http://namespaces.globalplatform.org/systems-profiles/1.1.2}CardDesignOrigin"/>
 *         &lt;element ref="{http://namespaces.globalplatform.org/systems-profiles/1.1.2}ConfigurationID" minOccurs="0"/>
 *         &lt;element ref="{http://namespaces.globalplatform.org/systems-profiles/1.1.2}CardStockID" minOccurs="0"/>
 *         &lt;element ref="{http://namespaces.globalplatform.org/systems-profiles/1.1.2}PersonalizationOrderConstraint" maxOccurs="unbounded"/>
 *         &lt;element name="Emboss" type="{http://namespaces.globalplatform.org/systems-profiles/1.1.2}Emboss" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="Label" type="{http://namespaces.globalplatform.org/systems-profiles/1.1.2}Label" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="MagneticEncoding" type="{http://namespaces.globalplatform.org/systems-profiles/1.1.2}MagneticEncodingType" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="Overlay" type="{http://namespaces.globalplatform.org/systems-profiles/1.1.2}Overlay" minOccurs="0"/>
 *         &lt;element name="Barcode" type="{http://namespaces.globalplatform.org/systems-profiles/1.1.2}Barcode" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="Text" type="{http://namespaces.globalplatform.org/systems-profiles/1.1.2}Text" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="Image" type="{http://namespaces.globalplatform.org/systems-profiles/1.1.2}Image" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "cardDesignOrientation",
    "cardDesignOrigin",
    "configurationID",
    "cardStockID",
    "personalizationOrderConstraint",
    "emboss",
    "label",
    "magneticEncoding",
    "overlay",
    "barcode",
    "text",
    "image"
})
@XmlRootElement(name = "CardPrintingProfile")
public class CardPrintingProfile {

    @XmlElement(name = "CardDesignOrientation", required = true)
    protected String cardDesignOrientation;
    @XmlElement(name = "CardDesignOrigin", required = true)
    protected String cardDesignOrigin;
    @XmlElement(name = "ConfigurationID")
    protected String configurationID;
    @XmlElement(name = "CardStockID")
    protected String cardStockID;
    @XmlElement(name = "PersonalizationOrderConstraint", required = true)
    protected List<String> personalizationOrderConstraint;
    @XmlElement(name = "Emboss")
    protected List<Emboss> emboss;
    @XmlElement(name = "Label")
    protected List<Label> label;
    @XmlElement(name = "MagneticEncoding")
    protected List<MagneticEncodingType> magneticEncoding;
    @XmlElement(name = "Overlay")
    protected Overlay overlay;
    @XmlElement(name = "Barcode")
    protected List<Barcode> barcode;
    @XmlElement(name = "Text")
    protected List<Text> text;
    @XmlElement(name = "Image")
    protected List<Image> image;

    /**
     * cardDesignOrientation 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCardDesignOrientation() {
        return cardDesignOrientation;
    }

    /**
     * cardDesignOrientation 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCardDesignOrientation(String value) {
        this.cardDesignOrientation = value;
    }

    /**
     * cardDesignOrigin 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCardDesignOrigin() {
        return cardDesignOrigin;
    }

    /**
     * cardDesignOrigin 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCardDesignOrigin(String value) {
        this.cardDesignOrigin = value;
    }

    /**
     * configurationID 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getConfigurationID() {
        return configurationID;
    }

    /**
     * configurationID 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setConfigurationID(String value) {
        this.configurationID = value;
    }

    /**
     * cardStockID 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCardStockID() {
        return cardStockID;
    }

    /**
     * cardStockID 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCardStockID(String value) {
        this.cardStockID = value;
    }

    /**
     * Gets the value of the personalizationOrderConstraint property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the personalizationOrderConstraint property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPersonalizationOrderConstraint().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getPersonalizationOrderConstraint() {
        if (personalizationOrderConstraint == null) {
            personalizationOrderConstraint = new ArrayList<String>();
        }
        return this.personalizationOrderConstraint;
    }

    /**
     * Gets the value of the emboss property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the emboss property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getEmboss().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Emboss }
     * 
     * 
     */
    public List<Emboss> getEmboss() {
        if (emboss == null) {
            emboss = new ArrayList<Emboss>();
        }
        return this.emboss;
    }

    /**
     * Gets the value of the label property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the label property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getLabel().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Label }
     * 
     * 
     */
    public List<Label> getLabel() {
        if (label == null) {
            label = new ArrayList<Label>();
        }
        return this.label;
    }

    /**
     * Gets the value of the magneticEncoding property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the magneticEncoding property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getMagneticEncoding().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link MagneticEncodingType }
     * 
     * 
     */
    public List<MagneticEncodingType> getMagneticEncoding() {
        if (magneticEncoding == null) {
            magneticEncoding = new ArrayList<MagneticEncodingType>();
        }
        return this.magneticEncoding;
    }

    /**
     * overlay 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link Overlay }
     *     
     */
    public Overlay getOverlay() {
        return overlay;
    }

    /**
     * overlay 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link Overlay }
     *     
     */
    public void setOverlay(Overlay value) {
        this.overlay = value;
    }

    /**
     * Gets the value of the barcode property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the barcode property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getBarcode().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Barcode }
     * 
     * 
     */
    public List<Barcode> getBarcode() {
        if (barcode == null) {
            barcode = new ArrayList<Barcode>();
        }
        return this.barcode;
    }

    /**
     * Gets the value of the text property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the text property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getText().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Text }
     * 
     * 
     */
    public List<Text> getText() {
        if (text == null) {
            text = new ArrayList<Text>();
        }
        return this.text;
    }

    /**
     * Gets the value of the image property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the image property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getImage().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Image }
     * 
     * 
     */
    public List<Image> getImage() {
        if (image == null) {
            image = new ArrayList<Image>();
        }
        return this.image;
    }

}
