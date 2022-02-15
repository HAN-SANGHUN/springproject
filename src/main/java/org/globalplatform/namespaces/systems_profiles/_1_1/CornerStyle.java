
package org.globalplatform.namespaces.systems_profiles._1_1;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>CornerStyle complex type에 대한 Java 클래스입니다.
 * 
 * <p>다음 스키마 단편이 이 클래스에 포함되는 필요한 콘텐츠를 지정합니다.
 * 
 * <pre>
 * &lt;complexType name="CornerStyle">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;choice>
 *         &lt;element ref="{http://namespaces.globalplatform.org/systems-profiles/1.1.2}SquareCorner"/>
 *         &lt;element name="RoundCorner" type="{http://namespaces.globalplatform.org/systems-profiles/1.1.2}RoundCorner"/>
 *       &lt;/choice>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CornerStyle", propOrder = {
    "squareCorner",
    "roundCorner"
})
public class CornerStyle {

    @XmlElement(name = "SquareCorner")
    protected SquareCorner squareCorner;
    @XmlElement(name = "RoundCorner")
    protected RoundCorner roundCorner;

    /**
     * squareCorner 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link SquareCorner }
     *     
     */
    public SquareCorner getSquareCorner() {
        return squareCorner;
    }

    /**
     * squareCorner 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link SquareCorner }
     *     
     */
    public void setSquareCorner(SquareCorner value) {
        this.squareCorner = value;
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

}
