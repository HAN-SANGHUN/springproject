
package org.globalplatform.namespaces.systems_profiles._1_1;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Barcode complex type에 대한 Java 클래스입니다.
 * 
 * <p>다음 스키마 단편이 이 클래스에 포함되는 필요한 콘텐츠를 지정합니다.
 * 
 * <pre>
 * &lt;complexType name="Barcode">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="XPosition" type="{http://namespaces.globalplatform.org/systems-profiles/1.1.2}XPositionType"/>
 *         &lt;element name="YPosition" type="{http://namespaces.globalplatform.org/systems-profiles/1.1.2}YPositionType"/>
 *         &lt;element ref="{http://namespaces.globalplatform.org/systems-profiles/1.1.2}ZPosition"/>
 *         &lt;element ref="{http://namespaces.globalplatform.org/systems-profiles/1.1.2}Side"/>
 *         &lt;element ref="{http://namespaces.globalplatform.org/systems-profiles/1.1.2}AnchorPosition"/>
 *         &lt;element ref="{http://namespaces.globalplatform.org/systems-profiles/1.1.2}DPI"/>
 *         &lt;element ref="{http://namespaces.globalplatform.org/systems-profiles/1.1.2}Type"/>
 *         &lt;element name="PersoData" type="{http://namespaces.globalplatform.org/systems-profiles/1.1.2}PersoData"/>
 *         &lt;element name="Height" type="{http://namespaces.globalplatform.org/systems-profiles/1.1.2}Height"/>
 *         &lt;element ref="{http://namespaces.globalplatform.org/systems-profiles/1.1.2}HumanReadableText"/>
 *         &lt;element ref="{http://namespaces.globalplatform.org/systems-profiles/1.1.2}HumanReadableStartDigit"/>
 *         &lt;element ref="{http://namespaces.globalplatform.org/systems-profiles/1.1.2}HumanReadableCheckDigit"/>
 *         &lt;element name="Font" type="{http://namespaces.globalplatform.org/systems-profiles/1.1.2}Font"/>
 *         &lt;element ref="{http://namespaces.globalplatform.org/systems-profiles/1.1.2}QuietZone"/>
 *         &lt;element ref="{http://namespaces.globalplatform.org/systems-profiles/1.1.2}CheckDigit"/>
 *         &lt;element name="Border" type="{http://namespaces.globalplatform.org/systems-profiles/1.1.2}Border"/>
 *       &lt;/sequence>
 *       &lt;attribute name="id" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="description" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Barcode", propOrder = {
    "xPosition",
    "yPosition",
    "zPosition",
    "side",
    "anchorPosition",
    "dpi",
    "type",
    "persoData",
    "height",
    "humanReadableText",
    "humanReadableStartDigit",
    "humanReadableCheckDigit",
    "font",
    "quietZone",
    "checkDigit",
    "border"
})
public class Barcode {

    @XmlElement(name = "XPosition", required = true)
    protected XPositionType xPosition;
    @XmlElement(name = "YPosition", required = true)
    protected YPositionType yPosition;
    @XmlElement(name = "ZPosition")
    protected byte zPosition;
    @XmlElement(name = "Side", required = true)
    protected String side;
    @XmlElement(name = "AnchorPosition", required = true)
    protected String anchorPosition;
    @XmlElement(name = "DPI")
    protected short dpi;
    @XmlElement(name = "Type", required = true)
    protected String type;
    @XmlElement(name = "PersoData", required = true)
    protected PersoData persoData;
    @XmlElement(name = "Height", required = true)
    protected Height height;
    @XmlElement(name = "HumanReadableText")
    protected boolean humanReadableText;
    @XmlElement(name = "HumanReadableStartDigit")
    protected boolean humanReadableStartDigit;
    @XmlElement(name = "HumanReadableCheckDigit")
    protected boolean humanReadableCheckDigit;
    @XmlElement(name = "Font", required = true)
    protected Font font;
    @XmlElement(name = "QuietZone", required = true)
    protected String quietZone;
    @XmlElement(name = "CheckDigit", required = true)
    protected String checkDigit;
    @XmlElement(name = "Border", required = true)
    protected Border border;
    @XmlAttribute(name = "id", required = true)
    protected String id;
    @XmlAttribute(name = "description", required = true)
    protected String description;

    /**
     * xPosition 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link XPositionType }
     *     
     */
    public XPositionType getXPosition() {
        return xPosition;
    }

    /**
     * xPosition 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link XPositionType }
     *     
     */
    public void setXPosition(XPositionType value) {
        this.xPosition = value;
    }

    /**
     * yPosition 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link YPositionType }
     *     
     */
    public YPositionType getYPosition() {
        return yPosition;
    }

    /**
     * yPosition 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link YPositionType }
     *     
     */
    public void setYPosition(YPositionType value) {
        this.yPosition = value;
    }

    /**
     * zPosition 속성의 값을 가져옵니다.
     * 
     */
    public byte getZPosition() {
        return zPosition;
    }

    /**
     * zPosition 속성의 값을 설정합니다.
     * 
     */
    public void setZPosition(byte value) {
        this.zPosition = value;
    }

    /**
     * side 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSide() {
        return side;
    }

    /**
     * side 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSide(String value) {
        this.side = value;
    }

    /**
     * anchorPosition 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAnchorPosition() {
        return anchorPosition;
    }

    /**
     * anchorPosition 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAnchorPosition(String value) {
        this.anchorPosition = value;
    }

    /**
     * dpi 속성의 값을 가져옵니다.
     * 
     */
    public short getDPI() {
        return dpi;
    }

    /**
     * dpi 속성의 값을 설정합니다.
     * 
     */
    public void setDPI(short value) {
        this.dpi = value;
    }

    /**
     * type 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getType() {
        return type;
    }

    /**
     * type 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setType(String value) {
        this.type = value;
    }

    /**
     * persoData 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link PersoData }
     *     
     */
    public PersoData getPersoData() {
        return persoData;
    }

    /**
     * persoData 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link PersoData }
     *     
     */
    public void setPersoData(PersoData value) {
        this.persoData = value;
    }

    /**
     * height 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link Height }
     *     
     */
    public Height getHeight() {
        return height;
    }

    /**
     * height 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link Height }
     *     
     */
    public void setHeight(Height value) {
        this.height = value;
    }

    /**
     * humanReadableText 속성의 값을 가져옵니다.
     * 
     */
    public boolean isHumanReadableText() {
        return humanReadableText;
    }

    /**
     * humanReadableText 속성의 값을 설정합니다.
     * 
     */
    public void setHumanReadableText(boolean value) {
        this.humanReadableText = value;
    }

    /**
     * humanReadableStartDigit 속성의 값을 가져옵니다.
     * 
     */
    public boolean isHumanReadableStartDigit() {
        return humanReadableStartDigit;
    }

    /**
     * humanReadableStartDigit 속성의 값을 설정합니다.
     * 
     */
    public void setHumanReadableStartDigit(boolean value) {
        this.humanReadableStartDigit = value;
    }

    /**
     * humanReadableCheckDigit 속성의 값을 가져옵니다.
     * 
     */
    public boolean isHumanReadableCheckDigit() {
        return humanReadableCheckDigit;
    }

    /**
     * humanReadableCheckDigit 속성의 값을 설정합니다.
     * 
     */
    public void setHumanReadableCheckDigit(boolean value) {
        this.humanReadableCheckDigit = value;
    }

    /**
     * font 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link Font }
     *     
     */
    public Font getFont() {
        return font;
    }

    /**
     * font 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link Font }
     *     
     */
    public void setFont(Font value) {
        this.font = value;
    }

    /**
     * quietZone 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getQuietZone() {
        return quietZone;
    }

    /**
     * quietZone 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setQuietZone(String value) {
        this.quietZone = value;
    }

    /**
     * checkDigit 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCheckDigit() {
        return checkDigit;
    }

    /**
     * checkDigit 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCheckDigit(String value) {
        this.checkDigit = value;
    }

    /**
     * border 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link Border }
     *     
     */
    public Border getBorder() {
        return border;
    }

    /**
     * border 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link Border }
     *     
     */
    public void setBorder(Border value) {
        this.border = value;
    }

    /**
     * id 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getId() {
        return id;
    }

    /**
     * id 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setId(String value) {
        this.id = value;
    }

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

}
