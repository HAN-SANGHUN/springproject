
package org.globalplatform.namespaces.systems_profiles._1_1;

import java.util.Arrays;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
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
 *       &lt;attribute name="ID" type="{http://namespaces.globalplatform.org/systems-profiles/1.1.2}uniqueID" />
 *       &lt;attribute name="Name" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="Owner" type="{http://www.w3.org/2001/XMLSchema}hexBinary" />
 *       &lt;attribute name="Version" type="{http://www.w3.org/2001/XMLSchema}hexBinary" />
 *       &lt;attribute name="Algorithm" type="{http://namespaces.globalplatform.org/systems-profiles/1.1.2}tkalgorithm" />
 *       &lt;attribute name="AlgorithmParameters" type="{http://www.w3.org/2001/XMLSchema}hexBinary" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "")
@XmlRootElement(name = "TransportKey")
public class TransportKey {

    @XmlAttribute(name = "ID")
    @XmlJavaTypeAdapter(HexBinaryAdapter.class)
    protected byte[] id;
    @XmlAttribute(name = "Name")
    protected String name;
    @XmlAttribute(name = "Owner")
    @XmlJavaTypeAdapter(HexBinaryAdapter.class)
    @XmlSchemaType(name = "hexBinary")
    protected byte[] owner;
    @XmlAttribute(name = "Version")
    @XmlJavaTypeAdapter(HexBinaryAdapter.class)
    @XmlSchemaType(name = "hexBinary")
    protected byte[] version;
    @XmlAttribute(name = "Algorithm")
    protected Tkalgorithm algorithm;
    @XmlAttribute(name = "AlgorithmParameters")
    @XmlJavaTypeAdapter(HexBinaryAdapter.class)
    @XmlSchemaType(name = "hexBinary")
    protected byte[] algorithmParameters;

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
     * algorithm �Ӽ��� ���� �����ɴϴ�.
     * 
     * @return
     *     possible object is
     *     {@link Tkalgorithm }
     *     
     */
    public Tkalgorithm getAlgorithm() {
        return algorithm;
    }

    /**
     * algorithm �Ӽ��� ���� �����մϴ�.
     * 
     * @param value
     *     allowed object is
     *     {@link Tkalgorithm }
     *     
     */
    public void setAlgorithm(Tkalgorithm value) {
        this.algorithm = value;
    }

    /**
     * algorithmParameters �Ӽ��� ���� �����ɴϴ�.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public byte[] getAlgorithmParameters() {
        return algorithmParameters;
    }

    /**
     * algorithmParameters �Ӽ��� ���� �����մϴ�.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAlgorithmParameters(byte[] value) {
        this.algorithmParameters = value;
    }

    @Override
	public String toString() {
		String returnValue = "TransportKey [id=";
		
		if(id == null)
			returnValue = returnValue + id;
		else
			returnValue = returnValue + StringUtil.toHexString(id);
		
		returnValue = returnValue + ", name=" + name
				+ ", owner=";
		
		if(owner == null)
			returnValue = returnValue + owner;
		else
			returnValue = returnValue + StringUtil.toHexString(owner);
		
		returnValue = returnValue + ", version=";
		
		if(version == null)
			returnValue = returnValue + version;
		else
			returnValue = returnValue + StringUtil.toHexString(version);
		
		returnValue = returnValue + ", algorithm=";
		
		if(algorithm == null)
			returnValue = returnValue + algorithm;
		else
			returnValue = returnValue + algorithm.value();
		
		returnValue = returnValue + ", algorithmParameters=";
		
		if(algorithmParameters == null)
			returnValue = returnValue + algorithmParameters;
		else
			returnValue = returnValue + StringUtil.toHexString(algorithmParameters);
		
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
		result = prime * result
				+ ((algorithm == null) ? 0 : algorithm.hashCode());
		result = prime * result + Arrays.hashCode(algorithmParameters);
		result = prime * result + Arrays.hashCode(id);
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + Arrays.hashCode(owner);
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
		
		TransportKey other = (TransportKey) obj;
		
		if (algorithm != other.algorithm)
			return false;
		
		if (!Arrays.equals(algorithmParameters, other.algorithmParameters))
			return false;
		
		if (!Arrays.equals(id, other.id))
			return false;
		
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		
		if (!Arrays.equals(owner, other.owner))
			return false;
		
		if (!Arrays.equals(version, other.version))
			return false;
		
		return true;
	}
	
	@Override
	public Object clone() throws CloneNotSupportedException {
		
		TransportKey tp = new TransportKey();
		
		tp.id = this.id;
		tp.name = this.name;
		tp.owner = this.owner;
		tp.version = this.version;
		tp.algorithm = this.algorithm;
		
		tp.algorithmParameters = this.algorithmParameters;
		
		
	    return tp;
	}
	
}
