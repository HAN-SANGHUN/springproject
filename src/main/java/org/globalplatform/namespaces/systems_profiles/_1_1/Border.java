
package org.globalplatform.namespaces.systems_profiles._1_1;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Border complex type�� ���� Java Ŭ�����Դϴ�.
 * 
 * <p>���� ��Ű�� ������ �� Ŭ������ ���ԵǴ� �ʿ��� �������� �����մϴ�.
 * 
 * <pre>
 * &lt;complexType name="Border">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Width" type="{http://namespaces.globalplatform.org/systems-profiles/1.1.2}Width"/>
 *         &lt;element ref="{http://namespaces.globalplatform.org/systems-profiles/1.1.2}LineStyle"/>
 *         &lt;element name="RoundCorner" type="{http://namespaces.globalplatform.org/systems-profiles/1.1.2}RoundCorner" minOccurs="0"/>
 *         &lt;element name="CornerStyle" type="{http://namespaces.globalplatform.org/systems-profiles/1.1.2}CornerStyle" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Border", propOrder = {
    "width",
    "lineStyle",
    "roundCorner",
    "cornerStyle"
})
public class Border {

    @XmlElement(name = "Width", required = true)
    protected Width width;
    @XmlElement(name = "LineStyle", required = true)
    protected String lineStyle;
    @XmlElement(name = "RoundCorner")
    protected RoundCorner roundCorner;
    @XmlElement(name = "CornerStyle")
    protected CornerStyle cornerStyle;

    /**
     * width �Ӽ��� ���� �����ɴϴ�.
     * 
     * @return
     *     possible object is
     *     {@link Width }
     *     
     */
    public Width getWidth() {
        return width;
    }

    /**
     * width �Ӽ��� ���� �����մϴ�.
     * 
     * @param value
     *     allowed object is
     *     {@link Width }
     *     
     */
    public void setWidth(Width value) {
        this.width = value;
    }

    /**
     * lineStyle �Ӽ��� ���� �����ɴϴ�.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLineStyle() {
        return lineStyle;
    }

    /**
     * lineStyle �Ӽ��� ���� �����մϴ�.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLineStyle(String value) {
        this.lineStyle = value;
    }

    /**
     * roundCorner �Ӽ��� ���� �����ɴϴ�.
     * 
     * @return
     *     possible object is
     *     {@link RoundCorner }
     *     
     */
    public RoundCorner getRoundCorner() {
        return roundCorner;
    }

    /**
     * roundCorner �Ӽ��� ���� �����մϴ�.
     * 
     * @param value
     *     allowed object is
     *     {@link RoundCorner }
     *     
     */
    public void setRoundCorner(RoundCorner value) {
        this.roundCorner = value;
    }

    /**
     * cornerStyle �Ӽ��� ���� �����ɴϴ�.
     * 
     * @return
     *     possible object is
     *     {@link CornerStyle }
     *     
     */
    public CornerStyle getCornerStyle() {
        return cornerStyle;
    }

    /**
     * cornerStyle �Ӽ��� ���� �����մϴ�.
     * 
     * @param value
     *     allowed object is
     *     {@link CornerStyle }
     *     
     */
    public void setCornerStyle(CornerStyle value) {
        this.cornerStyle = value;
    }

}
