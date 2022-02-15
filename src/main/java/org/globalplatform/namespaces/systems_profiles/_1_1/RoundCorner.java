
package org.globalplatform.namespaces.systems_profiles._1_1;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>RoundCorner complex type�� ���� Java Ŭ�����Դϴ�.
 * 
 * <p>���� ��Ű�� ������ �� Ŭ������ ���ԵǴ� �ʿ��� �������� �����մϴ�.
 * 
 * <pre>
 * &lt;complexType name="RoundCorner">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="CornerRadius" type="{http://namespaces.globalplatform.org/systems-profiles/1.1.2}CornerRadius"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "RoundCorner", propOrder = {
    "cornerRadius"
})
public class RoundCorner {

    @XmlElement(name = "CornerRadius", required = true)
    protected CornerRadius cornerRadius;

    /**
     * cornerRadius �Ӽ��� ���� �����ɴϴ�.
     * 
     * @return
     *     possible object is
     *     {@link CornerRadius }
     *     
     */
    public CornerRadius getCornerRadius() {
        return cornerRadius;
    }

    /**
     * cornerRadius �Ӽ��� ���� �����մϴ�.
     * 
     * @param value
     *     allowed object is
     *     {@link CornerRadius }
     *     
     */
    public void setCornerRadius(CornerRadius value) {
        this.cornerRadius = value;
    }

}
