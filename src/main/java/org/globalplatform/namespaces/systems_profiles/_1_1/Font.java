
package org.globalplatform.namespaces.systems_profiles._1_1;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlElementRefs;
import javax.xml.bind.annotation.XmlMixed;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Font complex type에 대한 Java 클래스입니다.
 * 
 * <p>다음 스키마 단편이 이 클래스에 포함되는 필요한 콘텐츠를 지정합니다.
 * 
 * <pre>
 * &lt;complexType name="Font">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;choice maxOccurs="unbounded" minOccurs="0">
 *         &lt;element name="ForegroundColor" type="{http://namespaces.globalplatform.org/systems-profiles/1.1.2}ForegroundColor"/>
 *         &lt;element ref="{http://namespaces.globalplatform.org/systems-profiles/1.1.2}Name"/>
 *         &lt;element name="Size" type="{http://namespaces.globalplatform.org/systems-profiles/1.1.2}Size"/>
 *         &lt;element ref="{http://namespaces.globalplatform.org/systems-profiles/1.1.2}Style"/>
 *         &lt;element ref="{http://namespaces.globalplatform.org/systems-profiles/1.1.2}BackgroundColor"/>
 *         &lt;element ref="{http://namespaces.globalplatform.org/systems-profiles/1.1.2}Underline"/>
 *       &lt;/choice>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Font", propOrder = {
    "content"
})
public class Font {

    @XmlElementRefs({
        @XmlElementRef(name = "BackgroundColor", namespace = "http://namespaces.globalplatform.org/systems-profiles/1.1.2", type = BackgroundColor.class, required = false),
        @XmlElementRef(name = "Size", namespace = "http://namespaces.globalplatform.org/systems-profiles/1.1.2", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "Name", namespace = "http://namespaces.globalplatform.org/systems-profiles/1.1.2", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "Style", namespace = "http://namespaces.globalplatform.org/systems-profiles/1.1.2", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "Underline", namespace = "http://namespaces.globalplatform.org/systems-profiles/1.1.2", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "ForegroundColor", namespace = "http://namespaces.globalplatform.org/systems-profiles/1.1.2", type = JAXBElement.class, required = false)
    })
    @XmlMixed
    protected List<Object> content;

    /**
     * Gets the value of the content property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the content property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getContent().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link BackgroundColor }
     * {@link JAXBElement }{@code <}{@link Size }{@code >}
     * {@link String }
     * {@link JAXBElement }{@code <}{@link String }{@code >}
     * {@link JAXBElement }{@code <}{@link String }{@code >}
     * {@link JAXBElement }{@code <}{@link String }{@code >}
     * {@link JAXBElement }{@code <}{@link ForegroundColor }{@code >}
     * 
     * 
     */
    public List<Object> getContent() {
        if (content == null) {
            content = new ArrayList<Object>();
        }
        return this.content;
    }

}
