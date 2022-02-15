
package org.globalplatform.namespaces.systems_profiles._1_1;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Arrays;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.HexBinaryAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import javax.xml.datatype.XMLGregorianCalendar;


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
 *       &lt;sequence>
 *         &lt;element ref="{http://namespaces.globalplatform.org/systems-profiles/1.1.2}KeyPart" minOccurs="0"/>
 *         &lt;element ref="{http://namespaces.globalplatform.org/systems-profiles/1.1.2}TransportKey" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="Name" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="ID" type="{http://www.w3.org/2001/XMLSchema}hexBinary" />
 *       &lt;attribute name="Type" use="required" type="{http://namespaces.globalplatform.org/systems-profiles/1.1.2}keyCategory" />
 *       &lt;attribute name="SubType" use="required" type="{http://namespaces.globalplatform.org/systems-profiles/1.1.2}keysubtype" />
 *       &lt;attribute name="Size" type="{http://www.w3.org/2001/XMLSchema}integer" />
 *       &lt;attribute name="Exponent" type="{http://www.w3.org/2001/XMLSchema}integer" />
 *       &lt;attribute name="Owner" type="{http://www.w3.org/2001/XMLSchema}hexBinary" />
 *       &lt;attribute name="Version" type="{http://www.w3.org/2001/XMLSchema}hexBinary" />
 *       &lt;attribute name="StartDate" type="{http://www.w3.org/2001/XMLSchema}date" />
 *       &lt;attribute name="EndDate" type="{http://www.w3.org/2001/XMLSchema}date" />
 *       &lt;attribute name="RecovationDate" type="{http://www.w3.org/2001/XMLSchema}date" />
 *       &lt;attribute name="KCVAlgorithm" type="{http://namespaces.globalplatform.org/systems-profiles/1.1.2}kcvalgorithmtype" />
 *       &lt;attribute name="KCVSize" type="{http://www.w3.org/2001/XMLSchema}integer" />
 *       &lt;attribute name="Mode" type="{http://namespaces.globalplatform.org/systems-profiles/1.1.2}mode" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "keyPart",
    "transportKey"
})
@XmlRootElement(name = "KeyInfo")
public class KeyInfo 
implements Serializable
{

    @XmlElement(name = "KeyPart")
    protected KeyPart keyPart;
    @XmlElement(name = "TransportKey")
    protected TransportKey transportKey;
    @XmlAttribute(name = "Name")
    protected String name;
    @XmlAttribute(name = "ID")
    @XmlJavaTypeAdapter(HexBinaryAdapter.class)
    @XmlSchemaType(name = "hexBinary")
    protected byte[] id;
    @XmlAttribute(name = "Type", required = true)
    protected KeyCategory type;
    @XmlAttribute(name = "SubType", required = true)
    protected Keysubtype subType;
    @XmlAttribute(name = "Size")
    protected BigInteger size;
    @XmlAttribute(name = "Exponent")
    protected BigInteger exponent;
    @XmlAttribute(name = "Owner")
    @XmlJavaTypeAdapter(HexBinaryAdapter.class)
    @XmlSchemaType(name = "hexBinary")
    protected byte[] owner;
    @XmlAttribute(name = "Version")
    @XmlJavaTypeAdapter(HexBinaryAdapter.class)
    @XmlSchemaType(name = "hexBinary")
    protected byte[] version;
    @XmlAttribute(name = "StartDate")
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar startDate;
    @XmlAttribute(name = "EndDate")
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar endDate;
    @XmlAttribute(name = "RecovationDate")
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar recovationDate;
    @XmlAttribute(name = "KCVAlgorithm")
    protected Kcvalgorithmtype kcvAlgorithm;
    @XmlAttribute(name = "KCVSize")
    protected BigInteger kcvSize;
    @XmlAttribute(name = "Mode")
    protected Mode mode;

    /**
     * keyPart �Ӽ��� ���� �����ɴϴ�.
     * 
     * @return
     *     possible object is
     *     {@link KeyPart }
     *     
     */
    public KeyPart getKeyPart() {
        return keyPart;
    }

    /**
     * keyPart �Ӽ��� ���� �����մϴ�.
     * 
     * @param value
     *     allowed object is
     *     {@link KeyPart }
     *     
     */
    public void setKeyPart(KeyPart value) {
        this.keyPart = value;
    }

    /**
     * transportKey �Ӽ��� ���� �����ɴϴ�.
     * 
     * @return
     *     possible object is
     *     {@link TransportKey }
     *     
     */
    public TransportKey getTransportKey() {
        return transportKey;
    }

    /**
     * transportKey �Ӽ��� ���� �����մϴ�.
     * 
     * @param value
     *     allowed object is
     *     {@link TransportKey }
     *     
     */
    public void setTransportKey(TransportKey value) {
        this.transportKey = value;
    }

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
     * id �Ӽ��� ���� �����ɴϴ�.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public byte[] getID() {
        return id;
    }

    /**
     * id �Ӽ��� ���� �����մϴ�.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setID(byte[] value) {
        this.id = value;
    }

    /**
     * type �Ӽ��� ���� �����ɴϴ�.
     * 
     * @return
     *     possible object is
     *     {@link KeyCategory }
     *     
     */
    public KeyCategory getType() {
        return type;
    }

    /**
     * type �Ӽ��� ���� �����մϴ�.
     * 
     * @param value
     *     allowed object is
     *     {@link KeyCategory }
     *     
     */
    public void setType(KeyCategory value) {
        this.type = value;
    }

    /**
     * subType �Ӽ��� ���� �����ɴϴ�.
     * 
     * @return
     *     possible object is
     *     {@link Keysubtype }
     *     
     */
    public Keysubtype getSubType() {
        return subType;
    }

    /**
     * subType �Ӽ��� ���� �����մϴ�.
     * 
     * @param value
     *     allowed object is
     *     {@link Keysubtype }
     *     
     */
    public void setSubType(Keysubtype value) {
        this.subType = value;
    }

    /**
     * size �Ӽ��� ���� �����ɴϴ�.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getSize() {
        return size;
    }

    /**
     * size �Ӽ��� ���� �����մϴ�.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setSize(BigInteger value) {
        this.size = value;
    }

    /**
     * exponent �Ӽ��� ���� �����ɴϴ�.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getExponent() {
        return exponent;
    }

    /**
     * exponent �Ӽ��� ���� �����մϴ�.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setExponent(BigInteger value) {
        this.exponent = value;
    }

    /**
     * owner �Ӽ��� ���� �����ɴϴ�.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public byte[] getOwner() {
        return owner;
    }

    /**
     * owner �Ӽ��� ���� �����մϴ�.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOwner(byte[] value) {
        this.owner = value;
    }

    /**
     * version �Ӽ��� ���� �����ɴϴ�.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public byte[] getVersion() {
        return version;
    }

    /**
     * version �Ӽ��� ���� �����մϴ�.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVersion(byte[] value) {
        this.version = value;
    }

    /**
     * startDate �Ӽ��� ���� �����ɴϴ�.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getStartDate() {
        return startDate;
    }

    /**
     * startDate �Ӽ��� ���� �����մϴ�.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setStartDate(XMLGregorianCalendar value) {
        this.startDate = value;
    }

    /**
     * endDate �Ӽ��� ���� �����ɴϴ�.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getEndDate() {
        return endDate;
    }

    /**
     * endDate �Ӽ��� ���� �����մϴ�.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setEndDate(XMLGregorianCalendar value) {
        this.endDate = value;
    }

    /**
     * recovationDate �Ӽ��� ���� �����ɴϴ�.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getRecovationDate() {
        return recovationDate;
    }

    /**
     * recovationDate �Ӽ��� ���� �����մϴ�.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setRecovationDate(XMLGregorianCalendar value) {
        this.recovationDate = value;
    }

    /**
     * kcvAlgorithm �Ӽ��� ���� �����ɴϴ�.
     * 
     * @return
     *     possible object is
     *     {@link Kcvalgorithmtype }
     *     
     */
    public Kcvalgorithmtype getKCVAlgorithm() {
        return kcvAlgorithm;
    }

    /**
     * kcvAlgorithm �Ӽ��� ���� �����մϴ�.
     * 
     * @param value
     *     allowed object is
     *     {@link Kcvalgorithmtype }
     *     
     */
    public void setKCVAlgorithm(Kcvalgorithmtype value) {
        this.kcvAlgorithm = value;
    }

    /**
     * kcvSize �Ӽ��� ���� �����ɴϴ�.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getKCVSize() {
        return kcvSize;
    }

    /**
     * kcvSize �Ӽ��� ���� �����մϴ�.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setKCVSize(BigInteger value) {
        this.kcvSize = value;
    }

    /**
     * mode �Ӽ��� ���� �����ɴϴ�.
     * 
     * @return
     *     possible object is
     *     {@link Mode }
     *     
     */
    public Mode getMode() {
        return mode;
    }

    /**
     * mode �Ӽ��� ���� �����մϴ�.
     * 
     * @param value
     *     allowed object is
     *     {@link Mode }
     *     
     */
    public void setMode(Mode value) {
        this.mode = value;
    }

    
    @Override
	public String toString() {
		String returnValue = "KeyInfo [keyPart=";
		
		returnValue = refactoringToString(returnValue, keyPart);
		
		returnValue = returnValue + ", transportKey=";
		
		returnValue = refactoringToString(returnValue, transportKey);
		
		returnValue = returnValue + ", name=" + name + ", id=";
		
		if(id == null)
			returnValue = returnValue + id;
		else
			returnValue = returnValue + StringUtil.toHexString(id);
		
		returnValue = returnValue + ", type=";
				
		if(type == null)
			returnValue = returnValue + type;
		else
			returnValue = returnValue + type.value();
		
		returnValue = returnValue + ", subType=";
		
		if(subType == null)
			returnValue = returnValue + subType;
		else
			returnValue = returnValue + subType.value();
		
		returnValue = returnValue + ", size=" + size
				+ ", exponent=" + exponent + ", owner=";
		
		if(owner == null)
			returnValue = returnValue + owner;
		else
			returnValue = returnValue + StringUtil.toHexString(owner);
		
		returnValue = returnValue + ", version=";
		
		if(version == null)
			returnValue = returnValue + version;
		else
			returnValue = returnValue + StringUtil.toHexString(version);

		returnValue = returnValue + ", startDate=" + startDate
				+ ", endDate=" + endDate + ", recovationDate=" + recovationDate
				+ ", kcvAlgorithm=";
		
		if(kcvAlgorithm == null)
			returnValue = returnValue + kcvAlgorithm;
		else
			returnValue = returnValue + kcvAlgorithm.value();
		
		returnValue = returnValue + ", kcvSize=" + kcvSize
				+ ", mode=";
		
		if(mode == null)
			returnValue = returnValue + mode;
		else
			returnValue = returnValue + mode.value();
		
		returnValue = returnValue + "]";
		
		return returnValue;
	}

	public String refactoringToString(String returnValue, Object appendValue){
		if(appendValue == null)
			returnValue = returnValue + appendValue;
		else
			returnValue = returnValue + appendValue.toString();
		
		return returnValue;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((endDate == null) ? 0 : endDate.hashCode());
		result = prime * result
				+ ((exponent == null) ? 0 : exponent.hashCode());
		result = prime * result + Arrays.hashCode(id);
		result = prime * result
				+ ((kcvAlgorithm == null) ? 0 : kcvAlgorithm.hashCode());
		result = prime * result + ((kcvSize == null) ? 0 : kcvSize.hashCode());
		result = prime * result + ((keyPart == null) ? 0 : keyPart.hashCode());
		result = prime * result + ((mode == null) ? 0 : mode.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + Arrays.hashCode(owner);
		result = prime * result
				+ ((recovationDate == null) ? 0 : recovationDate.hashCode());
		result = prime * result + ((size == null) ? 0 : size.hashCode());
		result = prime * result
				+ ((startDate == null) ? 0 : startDate.hashCode());
		result = prime * result + ((subType == null) ? 0 : subType.hashCode());
		result = prime * result
				+ ((transportKey == null) ? 0 : transportKey.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		result = prime * result + Arrays.hashCode(version);
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
		
		KeyInfo other = (KeyInfo) obj;
		
		if (endDate == null) {
			if (other.endDate != null)
				return false;
		} else if (!endDate.equals(other.endDate))
			return false;
		
		if (exponent == null) {
			if (other.exponent != null)
				return false;
		} else if (!exponent.equals(other.exponent))
			return false;
		
		if (!Arrays.equals(id, other.id))
			return false;
		
		if (kcvAlgorithm != other.kcvAlgorithm)
			return false;
		
		if (kcvSize == null) {
			if (other.kcvSize != null)
				return false;
		} else if (!kcvSize.equals(other.kcvSize))
			return false;
		
		if (keyPart == null) {
			if (other.keyPart != null)
				return false;
		} else if (!keyPart.equals(other.keyPart))
			return false;
		
		if (mode != other.mode)
			return false;
		
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		
		if (!Arrays.equals(owner, other.owner))
			return false;
		
		if (recovationDate == null) {
			if (other.recovationDate != null)
				return false;
		} else if (!recovationDate.equals(other.recovationDate))
			return false;
		
		if (size == null) {
			if (other.size != null)
				return false;
		} else if (!size.equals(other.size))
			return false;
		
		if (startDate == null) {
			if (other.startDate != null)
				return false;
		} else if (!startDate.equals(other.startDate))
			return false;
		
		if (subType != other.subType)
			return false;
		
		if (transportKey == null) {
			if (other.transportKey != null)
				return false;
		} else if (!transportKey.equals(other.transportKey))
			return false;
		
		if (type != other.type)
			return false;
		
		if (!Arrays.equals(version, other.version))
			return false;
		
		return true;
	}
	
	@Override
	public Object clone() throws CloneNotSupportedException {
		
		KeyInfo ki = new KeyInfo();
		
		if(this.keyPart != null)
			ki.keyPart = (KeyPart) this.keyPart.clone();
		else
			ki.keyPart = this.keyPart;
		
		if(this.transportKey != null)
			ki.transportKey = (TransportKey) this.transportKey.clone();
		else
			ki.transportKey = this.transportKey;
		
		ki.name = this.name;
		ki.id = this.id;
		ki.type = this.type;
		ki.subType = this.subType;
		ki.size = this.size;
		ki.exponent = this.exponent;
		ki.owner = this.owner;
		ki.version = this.version;
		ki.startDate = this.startDate;
		ki.endDate = this.endDate;
		ki.recovationDate = this.recovationDate;
		ki.kcvAlgorithm = this.kcvAlgorithm;
		ki.kcvSize = this.kcvSize;
		ki.mode = this.mode;
		
		return ki;
	}
	
}
