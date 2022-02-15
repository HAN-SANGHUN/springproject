
package org.globalplatform.namespaces.systems_profiles._1_1;

import java.math.BigInteger;
import java.util.Arrays;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.HexBinaryAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;



import com.kona.kms.utils.StringUtil;


/**
 * <p>anonymous complex type�� ���� Java Ŭ�����Դϴ�.
 * 
 * <p>���� ��Ű�� ������ �� Ŭ������ ���ԵǴ� �ʿ��� �������� �����մϴ�.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;attribute name="Name" type="{http://namespaces.globalplatform.org/systems-profiles/1.1.2}componentName" />
 *       &lt;attribute name="Order" type="{http://www.w3.org/2001/XMLSchema}integer" />
 *       &lt;attribute name="Value" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="Encoding" type="{http://namespaces.globalplatform.org/systems-profiles/1.1.2}encoding" />
 *       &lt;attribute name="KCV" type="{http://www.w3.org/2001/XMLSchema}hexBinary" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "")
@XmlRootElement(name = "Component")
public class Component {

    @XmlAttribute(name = "Name")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String name;
    @XmlAttribute(name = "Order")
    protected BigInteger order;
    @XmlAttribute(name = "Value")
    protected String value;
    @XmlAttribute(name = "Encoding")
    protected Encoding encoding;
    @XmlAttribute(name = "KCV")
    @XmlJavaTypeAdapter(HexBinaryAdapter.class)
    @XmlSchemaType(name = "hexBinary")
    protected byte[] kcv;

    /**
     * name �Ӽ��� ���� �����ɴϴ�.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getName() {
        return name;
    }

    /**
     * name �Ӽ��� ���� �����մϴ�.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setName(String value) {
        this.name = value;
    }

    /**
     * order �Ӽ��� ���� �����ɴϴ�.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getOrder() {
        return order;
    }

    /**
     * order �Ӽ��� ���� �����մϴ�.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setOrder(BigInteger value) {
        this.order = value;
    }

    /**
     * value �Ӽ��� ���� �����ɴϴ�.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getValue() {
        return value;
    }

    /**
     * value �Ӽ��� ���� �����մϴ�.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setValue(String value) {
        this.value = value;
    }

    /**
     * encoding �Ӽ��� ���� �����ɴϴ�.
     * 
     * @return
     *     possible object is
     *     {@link Encoding }
     *     
     */
    public Encoding getEncoding() {
        return encoding;
    }

    /**
     * encoding �Ӽ��� ���� �����մϴ�.
     * 
     * @param value
     *     allowed object is
     *     {@link Encoding }
     *     
     */
    public void setEncoding(Encoding value) {
        this.encoding = value;
    }

    /**
     * kcv �Ӽ��� ���� �����ɴϴ�.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public byte[] getKCV() {
        return kcv;
    }

    /**
     * kcv �Ӽ��� ���� �����մϴ�.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setKCV(byte[] value) {
        this.kcv = value;
    }
    
    @Override
	public String toString() {
		String returnValue = "Component [name=" + name + ", order=" + order + ", value="
				+ value + ", encoding=";
		
		if(encoding == null)
			returnValue = returnValue + encoding;
		else
			returnValue = returnValue + encoding.value();
		
		returnValue = returnValue + ", kcv=";
		
		if(kcv == null)
			returnValue = returnValue + kcv;
		else
			returnValue = returnValue + StringUtil.toHexString(kcv) + "]";
		return returnValue;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((encoding == null) ? 0 : encoding.hashCode());
		result = prime * result + Arrays.hashCode(kcv);
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((order == null) ? 0 : order.hashCode());
		result = prime * result + ((value == null) ? 0 : value.hashCode());
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
		
		Component other = (Component) obj;
		
		if (encoding != other.encoding)
			return false;
		
		if (!Arrays.equals(kcv, other.kcv))
			return false;
		
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		
		if (order == null) {
			if (other.order != null)
				return false;
		} else if (!order.equals(other.order))
			return false;
		
		if (value == null) {
			if (other.value != null)
				return false;
		} else if (!value.equals(other.value))
			return false;
		
		return true;
	}
	
	@Override
	public Object clone() throws CloneNotSupportedException {
		
		Component co = new Component();
		
		co.name = this.name;
		co.order = this.order;
		co.value = this.value;
		co.encoding = this.encoding;
	    co.kcv = this.kcv;
	    
	    return co;
	}


}
