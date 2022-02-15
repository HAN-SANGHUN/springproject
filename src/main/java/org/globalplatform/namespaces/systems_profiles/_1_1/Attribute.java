
package org.globalplatform.namespaces.systems_profiles._1_1;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
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
 *       &lt;attribute name="Importable" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *       &lt;attribute name="Exportable" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *       &lt;attribute name="Sensitive" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "")
@XmlRootElement(name = "Attribute")
public class Attribute {

    @XmlAttribute(name = "Importable")
    protected Boolean importable;
    @XmlAttribute(name = "Exportable")
    protected Boolean exportable;
    @XmlAttribute(name = "Sensitive")
    protected Boolean sensitive;

    /**
     * importable �Ӽ��� ���� �����ɴϴ�.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isImportable() {
        return importable;
    }

    /**
     * importable �Ӽ��� ���� �����մϴ�.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setImportable(Boolean value) {
        this.importable = value;
    }

    /**
     * exportable �Ӽ��� ���� �����ɴϴ�.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isExportable() {
        return exportable;
    }

    /**
     * exportable �Ӽ��� ���� �����մϴ�.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setExportable(Boolean value) {
        this.exportable = value;
    }

    /**
     * sensitive �Ӽ��� ���� �����ɴϴ�.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isSensitive() {
        return sensitive;
    }

    /**
     * sensitive �Ӽ��� ���� �����մϴ�.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setSensitive(Boolean value) {
        this.sensitive = value;
    }

    @Override
	public String toString() {
		return "Attribute [importable=" + importable + ", exportable="
				+ exportable + ", sensitive=" + sensitive + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((exportable == null) ? 0 : exportable.hashCode());
		result = prime * result
				+ ((importable == null) ? 0 : importable.hashCode());
		result = prime * result
				+ ((sensitive == null) ? 0 : sensitive.hashCode());
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
		
		Attribute other = (Attribute) obj;
		
		if (exportable == null) {
			if (other.exportable != null)
				return false;
		} else if (!exportable.equals(other.exportable))
			return false;
		
		if (importable == null) {
			if (other.importable != null)
				return false;
		} else if (!importable.equals(other.importable))
			return false;
		
		if (sensitive == null) {
			if (other.sensitive != null)
				return false;
		} else if (!sensitive.equals(other.sensitive))
			return false;
		
		return true;
	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		
		Attribute ab = new Attribute();
		
		ab.importable = this.importable;
		ab.exportable = this.exportable;
		ab.sensitive = this.sensitive;
		
		return ab;
	}
	
}
