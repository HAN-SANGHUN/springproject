
package org.globalplatform.namespaces.systems_profiles._1_1;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;



/**
 * <p>anonymous complex type�� ���� Java Ŭ�����Դϴ�.
 * 
 * <p>���� ��Ű�� ������ �� Ŭ������ ���ԵǴ� �ʿ��� �������� �����մϴ�.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element ref="{http://namespaces.globalplatform.org/systems-profiles/1.1.2}Component" maxOccurs="unbounded"/>
 *       &lt;/sequence>
 *       &lt;attribute name="Format" type="{http://namespaces.globalplatform.org/systems-profiles/1.1.2}valueFormat" />
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
    "component"
})
@XmlRootElement(name = "Value")
public class Value {

    @XmlElement(name = "Component", required = true)
    protected List<Component> component;
    @XmlAttribute(name = "Format")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String format;
    @XmlAttribute(name = "arrayElement", required = true)
    protected List<String> arrayElement;
	@XmlAttribute(name = "arrayIndex", required = true)
    protected List<String> arrayIndex;

    /**
     * Gets the value of the component property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the component property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getComponent().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Component }
     * 
     * 
     */
    public List<Component> getComponent() {
        if (component == null) {
            component = new ArrayList<Component>();
        }
        return this.component;
    }

    /**
     * format �Ӽ��� ���� �����ɴϴ�.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFormat() {
        return format;
    }

    /**
     * format �Ӽ��� ���� �����մϴ�.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFormat(String value) {
        this.format = value;
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

    public void setArrayElement(List<String> arrayElement) {
		this.arrayElement = arrayElement;
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

    public void setArrayIndex(List<String> arrayIndex) {
		this.arrayIndex = arrayIndex;
	}
    
    @Override
	public String toString() {
		String returnValue = "Value [component=";
		
		if(component != null && component.size() > 0){
			for(int i=0; i<component.size(); i++){
				if(i==0)
					returnValue = returnValue + component.get(i).toString();
				else
					returnValue = returnValue + ", " + component.get(i).toString();
			}
		}else
			returnValue = returnValue + component;
		
		if(arrayElement != null && arrayElement.size() > 0){
			for(int i=0; i<arrayElement.size(); i++){
				if(i==0)
					returnValue = returnValue + ", arrayElement=" + arrayElement.get(i);
				else
					returnValue = returnValue + ", " + arrayElement.get(i);
			}
		}else
			returnValue = returnValue + arrayElement;
		
		if(arrayIndex != null && arrayIndex.size() > 0){
			for(int i=0; i<arrayIndex.size(); i++){
				if(i==0)
					returnValue = returnValue + ", arrayIndex=" + arrayIndex.get(i);
				else
					returnValue = returnValue + ", " + arrayIndex.get(i);
			}
		}else
			returnValue = returnValue + arrayIndex;
			
		returnValue = returnValue + ", format=" + format + "]";
				
		return returnValue;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((arrayElement == null) ? 0 : arrayElement.hashCode());
		result = prime * result
				+ ((arrayIndex == null) ? 0 : arrayIndex.hashCode());
		result = prime * result
				+ ((component == null) ? 0 : component.hashCode());
		result = prime * result + ((format == null) ? 0 : format.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		
		if (obj == null)
			return false;
		
		if (getClass() != obj.getClass())
			return false;
		
		Value other = (Value) obj;
		
		if (arrayElement == null) {
			if (other.arrayElement != null)
				return false;
		} else if (!arrayElement.equals(other.arrayElement))
			return false;
		
		if (arrayIndex == null) {
			if (other.arrayIndex != null)
				return false;
		} else if (!arrayIndex.equals(other.arrayIndex))
			return false;
		
		if (component == null) {
			if (other.component != null)
				return false;
		} else if (!component.equals(other.component))
			return false;
		
		if (format == null) {
			if (other.format != null)
				return false;
		} else if (!format.equals(other.format))
			return false;
		
		return true;
	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		
		Value va = new Value();
		
		if(this.component != null && this.component.size()>0){
			for(int i=0; i<this.component.size(); i++)
				va.component.add((Component) this.component.get(i).clone());
		}else
			va.component = this.component;
		
		va.format = this.format;
		
		if(this.arrayElement != null && this.arrayElement.size()>0){
			for(int i=0; i<this.arrayElement.size(); i++)
				va.arrayElement.add(this.arrayElement.get(i));
		}else
			va.arrayElement = this.arrayElement;
		
		if(this.arrayIndex != null && this.arrayIndex.size()>0){
			for(int i=0; i<this.arrayIndex.size(); i++)
				va.arrayIndex.add(this.arrayIndex.get(i));
		}else
			va.arrayIndex = this.arrayIndex;
	    
	    return va;
	}
	

}

