
package org.globalplatform.namespaces.systems_profiles._1_1;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>RGB complex type에 대한 Java 클래스입니다.
 * 
 * <p>다음 스키마 단편이 이 클래스에 포함되는 필요한 콘텐츠를 지정합니다.
 * 
 * <pre>
 * &lt;complexType name="RGB">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element ref="{http://namespaces.globalplatform.org/systems-profiles/1.1.2}Red"/>
 *         &lt;element ref="{http://namespaces.globalplatform.org/systems-profiles/1.1.2}Green"/>
 *         &lt;element ref="{http://namespaces.globalplatform.org/systems-profiles/1.1.2}Blue"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "RGB", propOrder = {
    "red",
    "green",
    "blue"
})
public class RGB {

    @XmlElement(name = "Red")
    protected short red;
    @XmlElement(name = "Green")
    protected short green;
    @XmlElement(name = "Blue")
    protected short blue;

    /**
     * red 속성의 값을 가져옵니다.
     * 
     */
    public short getRed() {
        return red;
    }

    /**
     * red 속성의 값을 설정합니다.
     * 
     */
    public void setRed(short value) {
        this.red = value;
    }

    /**
     * green 속성의 값을 가져옵니다.
     * 
     */
    public short getGreen() {
        return green;
    }

    /**
     * green 속성의 값을 설정합니다.
     * 
     */
    public void setGreen(short value) {
        this.green = value;
    }

    /**
     * blue 속성의 값을 가져옵니다.
     * 
     */
    public short getBlue() {
        return blue;
    }

    /**
     * blue 속성의 값을 설정합니다.
     * 
     */
    public void setBlue(short value) {
        this.blue = value;
    }

}
