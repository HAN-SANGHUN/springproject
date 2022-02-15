
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
 *         &lt;element ref="{http://namespaces.globalplatform.org/systems-profiles/1.1.2}ResourcesAvailable"/>
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
    "resourcesAvailable"
})
@XmlRootElement(name = "CardInfo")
public class CardInfo {

    @XmlElement(name = "ResourcesAvailable", required = true)
    protected ResourcesAvailable resourcesAvailable;

    /**
     * resourcesAvailable 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link ResourcesAvailable }
     *     
     */
    public ResourcesAvailable getResourcesAvailable() {
        return resourcesAvailable;
    }

    /**
     * resourcesAvailable 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link ResourcesAvailable }
     *     
     */
    public void setResourcesAvailable(ResourcesAvailable value) {
        this.resourcesAvailable = value;
    }

}
