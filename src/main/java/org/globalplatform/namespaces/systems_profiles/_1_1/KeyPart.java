
package org.globalplatform.namespaces.systems_profiles._1_1;

import java.io.Serializable;
import java.math.BigInteger;
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
 *       &lt;attribute name="NumberOfParts" type="{http://www.w3.org/2001/XMLSchema}integer" />
 *       &lt;attribute name="PartNumber" type="{http://www.w3.org/2001/XMLSchema}integer" />
 *       &lt;attribute name="Algorithm" type="{http://namespaces.globalplatform.org/systems-profiles/1.1.2}keypartalgorithm" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "")
@XmlRootElement(name = "KeyPart")
public class KeyPart 
implements Serializable
{

    @XmlAttribute(name = "NumberOfParts")
    protected BigInteger numberOfParts;
    @XmlAttribute(name = "PartNumber")
    protected BigInteger partNumber;
    @XmlAttribute(name = "Algorithm")
    protected Keypartalgorithm algorithm;

    /**
     * numberOfParts �Ӽ��� ���� �����ɴϴ�.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getNumberOfParts() {
        return numberOfParts;
    }

    /**
     * numberOfParts �Ӽ��� ���� �����մϴ�.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setNumberOfParts(BigInteger value) {
        this.numberOfParts = value;
    }

    /**
     * partNumber �Ӽ��� ���� �����ɴϴ�.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getPartNumber() {
        return partNumber;
    }

    /**
     * partNumber �Ӽ��� ���� �����մϴ�.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setPartNumber(BigInteger value) {
        this.partNumber = value;
    }

    /**
     * algorithm �Ӽ��� ���� �����ɴϴ�.
     * 
     * @return
     *     possible object is
     *     {@link Keypartalgorithm }
     *     
     */
    public Keypartalgorithm getAlgorithm() {
        return algorithm;
    }

    /**
     * algorithm �Ӽ��� ���� �����մϴ�.
     * 
     * @param value
     *     allowed object is
     *     {@link Keypartalgorithm }
     *     
     */
    public void setAlgorithm(Keypartalgorithm value) {
        this.algorithm = value;
    }
    
    @Override
	public String toString() {
		String returnValue = "KeyPart [numberOfParts=" + numberOfParts + ", partNumber="
				+ partNumber + ", algorithm=";
		
		if(algorithm == null)
			returnValue = returnValue + algorithm;
		else
			returnValue = returnValue + algorithm.value();
		
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
		result = prime * result
				+ ((numberOfParts == null) ? 0 : numberOfParts.hashCode());
		result = prime * result
				+ ((partNumber == null) ? 0 : partNumber.hashCode());
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
		
		KeyPart other = (KeyPart) obj;
		
		if (algorithm != other.algorithm)
			return false;
		
		if (numberOfParts == null) {
			if (other.numberOfParts != null)
				return false;
		} else if (!numberOfParts.equals(other.numberOfParts))
			return false;
		
		if (partNumber == null) {
			if (other.partNumber != null)
				return false;
		} else if (!partNumber.equals(other.partNumber))
			return false;
		
		return true;
	}
	
	@Override
	public Object clone() throws CloneNotSupportedException {
		
		KeyPart kp = new KeyPart();
		
		kp.numberOfParts = this.numberOfParts;
		kp.partNumber = this.partNumber;
		kp.algorithm = this.algorithm;
		
		return kp;
	}

}
