
package org.globalplatform.namespaces.systems_profiles._1_1;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlValue;


/**
 * <p>PersoData complex type�� ���� Java Ŭ�����Դϴ�.
 * 
 * <p>���� ��Ű�� ������ �� Ŭ������ ���ԵǴ� �ʿ��� �������� �����մϴ�.
 * 
 * <pre>
 * &lt;complexType name="PersoData">
 *   &lt;simpleContent>
 *     &lt;extension base="&lt;http://namespaces.globalplatform.org/systems-profiles/1.1.2>PersoDataSimple">
 *       &lt;attribute name="field" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *     &lt;/extension>
 *   &lt;/simpleContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PersoData", propOrder = {
    "value"
})
public class PersoData {

    @XmlValue
    protected PersoDataSimple value;
    @XmlAttribute(name = "field")
    protected Boolean field;

    /**
     * value �Ӽ��� ���� �����ɴϴ�.
     * 
     * @return
     *     possible object is
     *     {@link PersoDataSimple }
     *     
     */
    public PersoDataSimple getValue() {
        return value;
    }

    /**
     * value �Ӽ��� ���� �����մϴ�.
     * 
     * @param value
     *     allowed object is
     *     {@link PersoDataSimple }
     *     
     */
    public void setValue(PersoDataSimple value) {
        this.value = value;
    }

    /**
     * field �Ӽ��� ���� �����ɴϴ�.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isField() {
        return field;
    }

    /**
     * field �Ӽ��� ���� �����մϴ�.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setField(Boolean value) {
        this.field = value;
    }

}
