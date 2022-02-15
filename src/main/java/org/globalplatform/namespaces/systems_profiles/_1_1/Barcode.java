
package org.globalplatform.namespaces.systems_profiles._1_1;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Barcode complex type�� ���� Java Ŭ�����Դϴ�.
 * 
 * <p>���� ��Ű�� ������ �� Ŭ������ ���ԵǴ� �ʿ��� �������� �����մϴ�.
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
     * xPosition �Ӽ��� ���� �����ɴϴ�.
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
     * xPosition �Ӽ��� ���� �����մϴ�.
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
     * yPosition �Ӽ��� ���� �����ɴϴ�.
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
     * yPosition �Ӽ��� ���� �����մϴ�.
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
     * zPosition �Ӽ��� ���� �����ɴϴ�.
     * 
     */
    public byte getZPosition() {
        return zPosition;
    }

    /**
     * zPosition �Ӽ��� ���� �����մϴ�.
     * 
     */
    public void setZPosition(byte value) {
        this.zPosition = value;
    }

    /**
     * side �Ӽ��� ���� �����ɴϴ�.
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
     * side �Ӽ��� ���� �����մϴ�.
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
     * anchorPosition �Ӽ��� ���� �����ɴϴ�.
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
     * anchorPosition �Ӽ��� ���� �����մϴ�.
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
     * dpi �Ӽ��� ���� �����ɴϴ�.
     * 
     */
    public short getDPI() {
        return dpi;
    }

    /**
     * dpi �Ӽ��� ���� �����մϴ�.
     * 
     */
    public void setDPI(short value) {
        this.dpi = value;
    }

    /**
     * type �Ӽ��� ���� �����ɴϴ�.
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
     * type �Ӽ��� ���� �����մϴ�.
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
     * persoData �Ӽ��� ���� �����ɴϴ�.
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
     * persoData �Ӽ��� ���� �����մϴ�.
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
     * height �Ӽ��� ���� �����ɴϴ�.
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
     * height �Ӽ��� ���� �����մϴ�.
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
     * humanReadableText �Ӽ��� ���� �����ɴϴ�.
     * 
     */
    public boolean isHumanReadableText() {
        return humanReadableText;
    }

    /**
     * humanReadableText �Ӽ��� ���� �����մϴ�.
     * 
     */
    public void setHumanReadableText(boolean value) {
        this.humanReadableText = value;
    }

    /**
     * humanReadableStartDigit �Ӽ��� ���� �����ɴϴ�.
     * 
     */
    public boolean isHumanReadableStartDigit() {
        return humanReadableStartDigit;
    }

    /**
     * humanReadableStartDigit �Ӽ��� ���� �����մϴ�.
     * 
     */
    public void setHumanReadableStartDigit(boolean value) {
        this.humanReadableStartDigit = value;
    }

    /**
     * humanReadableCheckDigit �Ӽ��� ���� �����ɴϴ�.
     * 
     */
    public boolean isHumanReadableCheckDigit() {
        return humanReadableCheckDigit;
    }

    /**
     * humanReadableCheckDigit �Ӽ��� ���� �����մϴ�.
     * 
     */
    public void setHumanReadableCheckDigit(boolean value) {
        this.humanReadableCheckDigit = value;
    }

    /**
     * font �Ӽ��� ���� �����ɴϴ�.
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
     * font �Ӽ��� ���� �����մϴ�.
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
     * quietZone �Ӽ��� ���� �����ɴϴ�.
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
     * quietZone �Ӽ��� ���� �����մϴ�.
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
     * checkDigit �Ӽ��� ���� �����ɴϴ�.
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
     * checkDigit �Ӽ��� ���� �����մϴ�.
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
     * border �Ӽ��� ���� �����ɴϴ�.
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
     * border �Ӽ��� ���� �����մϴ�.
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
     * id �Ӽ��� ���� �����ɴϴ�.
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
     * id �Ӽ��� ���� �����մϴ�.
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
     * description �Ӽ��� ���� �����ɴϴ�.
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
     * description �Ӽ��� ���� �����մϴ�.
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
