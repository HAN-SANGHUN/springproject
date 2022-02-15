
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
 *         &lt;element ref="{http://namespaces.globalplatform.org/systems-profiles/1.1.2}ApplicationInstance" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="arrayElement" use="required" type="{http://namespaces.globalplatform.org/systems-profiles/1.1.2}arrayList" />
 *       &lt;attribute name="arrayIndex" use="required" type="{http://namespaces.globalplatform.org/systems-profiles/1.1.2}indexList" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "applicationInstance"
})
@XmlRootElement(name = "ApplicationInstances")
public class ApplicationInstances {

    @XmlElement(name = "ApplicationInstance")
    protected List<ApplicationInstance> applicationInstance;
    @XmlAttribute(name = "arrayElement", required = true)
    protected List<String> arrayElement;
    @XmlAttribute(name = "arrayIndex", required = true)
    protected List<String> arrayIndex;

    /**
     * Gets the value of the applicationInstance property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the applicationInstance property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getApplicationInstance().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ApplicationInstance }
     * 
     * 
     */
    public List<ApplicationInstance> getApplicationInstance() {
        if (applicationInstance == null) {
            applicationInstance = new ArrayList<ApplicationInstance>();
        }
        return this.applicationInstance;
    }

    /**
     * Gets the value of the arrayElement property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the arrayElement property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getArrayElement().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getArrayElement() {
        if (arrayElement == null) {
            arrayElement = new ArrayList<String>();
        }
        return this.arrayElement;
    }

    /**
     * Gets the value of the arrayIndex property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the arrayIndex property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getArrayIndex().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getArrayIndex() {
        if (arrayIndex == null) {
            arrayIndex = new ArrayList<String>();
        }
        return this.arrayIndex;
    }

}
