
package org.globalplatform.namespaces.systems_profiles._1_1;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>anonymous complex type에 대한 Java 클래스입니다.
 * 
 * <p>다음 스키마 단편이 이 클래스에 포함되는 필요한 콘텐츠를 지정합니다.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element ref="{http://namespaces.globalplatform.org/systems-profiles/1.1.2}KeyDeclaration" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element ref="{http://namespaces.globalplatform.org/systems-profiles/1.1.2}Script"/>
 *       &lt;/sequence>
 *       &lt;attribute name="Param" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "keyDeclaration",
    "script"
})
@XmlRootElement(name = "OpenSecureChannel")
public class OpenSecureChannel {

    @XmlElement(name = "KeyDeclaration")
    protected List<KeyDeclaration> keyDeclaration;
    @XmlElement(name = "Script", required = true)
    protected Object script;
    @XmlAttribute(name = "Param", required = true)
    protected String param;

    /**
     * Gets the value of the keyDeclaration property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the keyDeclaration property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getKeyDeclaration().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link KeyDeclaration }
     * 
     * 
     */
    public List<KeyDeclaration> getKeyDeclaration() {
        if (keyDeclaration == null) {
            keyDeclaration = new ArrayList<KeyDeclaration>();
        }
        return this.keyDeclaration;
    }

    /**
     * script 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link Object }
     *     
     */
    public Object getScript() {
        return script;
    }

    /**
     * script 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link Object }
     *     
     */
    public void setScript(Object value) {
        this.script = value;
    }

    /**
     * param 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getParam() {
        return param;
    }

    /**
     * param 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setParam(String value) {
        this.param = value;
    }

}
