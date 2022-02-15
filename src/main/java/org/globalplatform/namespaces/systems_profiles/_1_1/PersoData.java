
package org.globalplatform.namespaces.systems_profiles._1_1;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlValue;


/**
 * <p>PersoData complex type에 대한 Java 클래스입니다.
 * 
 * <p>다음 스키마 단편이 이 클래스에 포함되는 필요한 콘텐츠를 지정합니다.
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
     * value 속성의 값을 가져옵니다.
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
     * value 속성의 값을 설정합니다.
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
     * field 속성의 값을 가져옵니다.
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
     * field 속성의 값을 설정합니다.
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
