
package org.globalplatform.namespaces.systems_profiles._1_1;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;




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
 *         &lt;element ref="{http://namespaces.globalplatform.org/systems-profiles/1.1.2}Revision" maxOccurs="unbounded"/>
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
    "revision"
})
@XmlRootElement(name = "Revisions")
public class Revisions implements Serializable {
	

    @XmlElement(name = "Revision", required = true)
    protected List<Revision> revision;
    @XmlAttribute(name = "arrayElement", required = true)
    protected List<String> arrayElement;
    @XmlAttribute(name = "arrayIndex", required = true)
    protected List<String> arrayIndex;

    /**
     * Gets the value of the revision property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the revision property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getRevision().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Revision }
     * 
     * 
     */
    public List<Revision> getRevision() {
        if (revision == null) {
            revision = new ArrayList<Revision>();
        }
        return this.revision;
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
    
    
    @Override
	public String toString() {
		String returnValue = "Revisions [revision=";
		
		if(revision != null && revision.size() > 0){
			for(int i=0; i<revision.size(); i++){
				if(i==0)
					returnValue = returnValue + revision.get(i).toString();
				else
					returnValue = returnValue + ", " + revision.get(i).toString();
				
			}
		}else
			returnValue = returnValue + revision; 
		
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
		
		returnValue = returnValue + "]";
			
		
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
				+ ((revision == null) ? 0 : revision.hashCode());
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
		
		Revisions other = (Revisions) obj;
		
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
		
		if (revision == null) {
			if (other.revision != null)
				return false;
		} else if (!revision.equals(other.revision))
			return false;
		
		return true;
	}
    
	@Override
	public Object clone() throws CloneNotSupportedException {
		
		Revisions re = new Revisions();
		
		if(this.revision != null && this.revision.size()>0){
			for(int i=0; i<this.revision.size(); i++)
				re.revision.add((Revision) this.revision.get(i).clone());
		}else
			re.revision = this.revision;
		
		if(this.arrayElement != null && this.arrayElement.size()>0){
			for(int i=0; i<this.arrayElement.size(); i++)
				re.arrayElement.add(this.arrayElement.get(i));
		}else
			re.arrayElement = this.arrayElement;
		
		if(this.arrayIndex != null && this.arrayIndex.size()>0){
			for(int i=0; i<this.arrayIndex.size(); i++)
				re.arrayIndex.add(this.arrayIndex.get(i));
		}else
			re.arrayIndex = this.arrayIndex;
		
		return re;
	}

}
