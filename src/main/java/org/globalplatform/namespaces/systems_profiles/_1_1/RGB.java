
package org.globalplatform.namespaces.systems_profiles._1_1;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>RGB complex type�� ���� Java Ŭ�����Դϴ�.
 * 
 * <p>���� ��Ű�� ������ �� Ŭ������ ���ԵǴ� �ʿ��� �������� �����մϴ�.
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
     * red �Ӽ��� ���� �����ɴϴ�.
     * 
     */
    public short getRed() {
        return red;
    }

    /**
     * red �Ӽ��� ���� �����մϴ�.
     * 
     */
    public void setRed(short value) {
        this.red = value;
    }

    /**
     * green �Ӽ��� ���� �����ɴϴ�.
     * 
     */
    public short getGreen() {
        return green;
    }

    /**
     * green �Ӽ��� ���� �����մϴ�.
     * 
     */
    public void setGreen(short value) {
        this.green = value;
    }

    /**
     * blue �Ӽ��� ���� �����ɴϴ�.
     * 
     */
    public short getBlue() {
        return blue;
    }

    /**
     * blue �Ӽ��� ���� �����մϴ�.
     * 
     */
    public void setBlue(short value) {
        this.blue = value;
    }

}
