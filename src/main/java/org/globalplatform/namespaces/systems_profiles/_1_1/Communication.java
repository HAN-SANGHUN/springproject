
package org.globalplatform.namespaces.systems_profiles._1_1;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
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
 *         &lt;element ref="{http://namespaces.globalplatform.org/systems-profiles/1.1.2}Contact" minOccurs="0"/>
 *         &lt;element ref="{http://namespaces.globalplatform.org/systems-profiles/1.1.2}Contactless" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "contact",
    "contactless"
})
@XmlRootElement(name = "Communication")
public class Communication {

    @XmlElement(name = "Contact")
    protected Contact contact;
    @XmlElement(name = "Contactless")
    protected Contactless contactless;

    /**
     * contact 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link Contact }
     *     
     */
    public Contact getContact() {
        return contact;
    }

    /**
     * contact 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link Contact }
     *     
     */
    public void setContact(Contact value) {
        this.contact = value;
    }

    /**
     * contactless 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link Contactless }
     *     
     */
    public Contactless getContactless() {
        return contactless;
    }

    /**
     * contactless 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link Contactless }
     *     
     */
    public void setContactless(Contactless value) {
        this.contactless = value;
    }

}
