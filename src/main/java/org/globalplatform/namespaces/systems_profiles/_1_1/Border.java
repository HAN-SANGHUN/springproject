
package org.globalplatform.namespaces.systems_profiles._1_1;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Border complex type에 대한 Java 클래스입니다.
 * 
 * <p>다음 스키마 단편이 이 클래스에 포함되는 필요한 콘텐츠를 지정합니다.
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
     * width 속성의 값을 가져옵니다.
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
     * width 속성의 값을 설정합니다.
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
     * lineStyle 속성의 값을 가져옵니다.
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
     * lineStyle 속성의 값을 설정합니다.
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
     * roundCorner 속성의 값을 가져옵니다.
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
     * roundCorner 속성의 값을 설정합니다.
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
     * cornerStyle 속성의 값을 가져옵니다.
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
     * cornerStyle 속성의 값을 설정합니다.
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
