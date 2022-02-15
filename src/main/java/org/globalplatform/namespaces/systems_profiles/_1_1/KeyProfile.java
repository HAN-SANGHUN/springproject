
package org.globalplatform.namespaces.systems_profiles._1_1;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Arrays;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
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
 *       &lt;sequence>
 *         &lt;element ref="{http://namespaces.globalplatform.org/systems-profiles/1.1.2}Description" minOccurs="0"/>
 *         &lt;element ref="{http://namespaces.globalplatform.org/systems-profiles/1.1.2}Revisions"/>
 *         &lt;element ref="{http://namespaces.globalplatform.org/systems-profiles/1.1.2}KeyInfo"/>
 *         &lt;element ref="{http://namespaces.globalplatform.org/systems-profiles/1.1.2}Attribute"/>
 *         &lt;element ref="{http://namespaces.globalplatform.org/systems-profiles/1.1.2}Usage"/>
 *         &lt;element ref="{http://namespaces.globalplatform.org/systems-profiles/1.1.2}Value" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="UniqueID" use="required" type="{http://namespaces.globalplatform.org/systems-profiles/1.1.2}uniqueID" />
 *       &lt;attribute name="ProfileVersion" use="required" type="{http://namespaces.globalplatform.org/systems-profiles/1.1.2}version" />
 *       &lt;attribute name="ErrataVersion" type="{http://www.w3.org/2001/XMLSchema}integer" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "description",
    "revisions",
    "keyInfo",
    "attribute",
    "usage",
    "value"
})
@XmlRootElement(name = "KeyProfile")
public class KeyProfile 
implements Serializable
{

    @XmlElement(name = "Description")
    protected String description;
    @XmlElement(name = "Revisions", required = true)
    protected Revisions revisions;
    @XmlElement(name = "KeyInfo", required = true)
    protected KeyInfo keyInfo;
    @XmlElement(name = "Attribute", required = true)
    protected Attribute attribute;
    @XmlElement(name = "Usage", required = true)
    protected Usage usage;
    @XmlElement(name = "Value")
    protected Value value;
    @XmlAttribute(name = "UniqueID", required = true)
    @XmlJavaTypeAdapter(HexBinaryAdapter.class)
    protected byte[] uniqueID;
    @XmlAttribute(name = "ProfileVersion", required = true)
    protected String profileVersion;
    @XmlAttribute(name = "ErrataVersion")
    protected BigInteger errataVersion;
    
    @XmlTransient
    private String kmsLabel;
    @XmlTransient
	private String xmlFile;

    /**
     * description �Ӽ��� ���� �����ɴϴ�.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescription() {
        return description;
    }

    /**
     * description �Ӽ��� ���� �����մϴ�.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescription(String value) {
        this.description = value;
    }

    /**
     * revisions �Ӽ��� ���� �����ɴϴ�.
     * 
     * @return
     *     possible object is
     *     {@link Revisions }
     *     
     */
    public Revisions getRevisions() {
        return revisions;
    }

    /**
     * revisions �Ӽ��� ���� �����մϴ�.
     * 
     * @param value
     *     allowed object is
     *     {@link Revisions }
     *     
     */
    public void setRevisions(Revisions value) {
        this.revisions = value;
    }

    /**
     * keyInfo �Ӽ��� ���� �����ɴϴ�.
     * 
     * @return
     *     possible object is
     *     {@link KeyInfo }
     *     
     */
    public KeyInfo getKeyInfo() {
        return keyInfo;
    }

    /**
     * keyInfo �Ӽ��� ���� �����մϴ�.
     * 
     * @param value
     *     allowed object is
     *     {@link KeyInfo }
     *     
     */
    public void setKeyInfo(KeyInfo value) {
        this.keyInfo = value;
    }

    /**
     * attribute �Ӽ��� ���� �����ɴϴ�.
     * 
     * @return
     *     possible object is
     *     {@link Attribute }
     *     
     */
    public Attribute getAttribute() {
        return attribute;
    }

    /**
     * attribute �Ӽ��� ���� �����մϴ�.
     * 
     * @param value
     *     allowed object is
     *     {@link Attribute }
     *     
     */
    public void setAttribute(Attribute value) {
        this.attribute = value;
    }

    /**
     * usage �Ӽ��� ���� �����ɴϴ�.
     * 
     * @return
     *     possible object is
     *     {@link Usage }
     *     
     */
    public Usage getUsage() {
        return usage;
    }

    /**
     * usage �Ӽ��� ���� �����մϴ�.
     * 
     * @param value
     *     allowed object is
     *     {@link Usage }
     *     
     */
    public void setUsage(Usage value) {
        this.usage = value;
    }

    /**
     * value �Ӽ��� ���� �����ɴϴ�.
     * 
     * @return
     *     possible object is
     *     {@link Value }
     *     
     */
    public Value getValue() {
        return value;
    }

    /**
     * value �Ӽ��� ���� �����մϴ�.
     * 
     * @param value
     *     allowed object is
     *     {@link Value }
     *     
     */
    public void setValue(Value value) {
        this.value = value;
    }

    /**
     * uniqueID �Ӽ��� ���� �����ɴϴ�.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public byte[] getUniqueID() {
        return uniqueID;
    }

    /**
     * uniqueID �Ӽ��� ���� �����մϴ�.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUniqueID(byte[] value) {
        this.uniqueID = value;
    }

    /**
     * profileVersion �Ӽ��� ���� �����ɴϴ�.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProfileVersion() {
        return profileVersion;
    }

    /**
     * profileVersion �Ӽ��� ���� �����մϴ�.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProfileVersion(String value) {
        this.profileVersion = value;
    }

    /**
     * errataVersion �Ӽ��� ���� �����ɴϴ�.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getErrataVersion() {
        return errataVersion;
    }

    /**
     * errataVersion �Ӽ��� ���� �����մϴ�.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setErrataVersion(BigInteger value) {
        this.errataVersion = value;
    }

    
    
    @Override
	public String toString() {
		String returnValue = "KeyProfile [description=" + description + ", revisions=";
		
		returnValue = refactoringToString(returnValue, revisions);

		returnValue = returnValue + ", keyInfo=";
		
		returnValue = refactoringToString(returnValue, keyInfo);
		
		returnValue = returnValue + ", attribute=";
		
		returnValue = refactoringToString(returnValue, attribute);

		returnValue = returnValue + ", usage=";
		
		returnValue = refactoringToString(returnValue, usage);
		
		returnValue = returnValue + ", value=";
		
		returnValue = refactoringToString(returnValue, value);
		
		returnValue = returnValue + ", uniqueID=";
		
		if(uniqueID == null)
			returnValue = returnValue + uniqueID;
		else
			returnValue = returnValue + StringUtil.toHexString(uniqueID);
		
		returnValue = returnValue + ", profileVersion=" + profileVersion + ", errataVersion="
				+ errataVersion + "]";
		
		
		
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
				+ ((attribute == null) ? 0 : attribute.hashCode());
		result = prime * result
				+ ((description == null) ? 0 : description.hashCode());
		result = prime * result
				+ ((errataVersion == null) ? 0 : errataVersion.hashCode());
		result = prime * result + ((keyInfo == null) ? 0 : keyInfo.hashCode());
		result = prime * result
				+ ((profileVersion == null) ? 0 : profileVersion.hashCode());
		result = prime * result
				+ ((revisions == null) ? 0 : revisions.hashCode());
		result = prime * result + Arrays.hashCode(uniqueID);
		result = prime * result + ((usage == null) ? 0 : usage.hashCode());
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
		
		KeyProfile other = (KeyProfile) obj;
		
		if (attribute == null) {
			if (other.attribute != null)
				return false;
		} else if (!attribute.equals(other.attribute))
			return false;
		
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		
		if (errataVersion == null) {
			if (other.errataVersion != null)
				return false;
		} else if (!errataVersion.equals(other.errataVersion))
			return false;
		
		if (keyInfo == null) {
			if (other.keyInfo != null)
				return false;
		} else if (!keyInfo.equals(other.keyInfo))
			return false;
	
		
		if (profileVersion == null) {
			if (other.profileVersion != null)
				return false;
		} else if (!profileVersion.equals(other.profileVersion))
			return false;
		
		if (revisions == null) {
			if (other.revisions != null)
				return false;
		} else if (!revisions.equals(other.revisions))
			return false;
		
		if (!Arrays.equals(uniqueID, other.uniqueID))
			return false;
		
		if (usage == null) {
			if (other.usage != null)
				return false;
		} else if (!usage.equals(other.usage))
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
		
		KeyProfile kp = new KeyProfile();
		
		kp.description = this.description;
		
		if(this.revisions != null)
			kp.revisions = (Revisions) this.revisions.clone();
		else
			kp.revisions = this.revisions;
		
		if(this.keyInfo != null)
			kp.keyInfo = (KeyInfo) this.keyInfo.clone();
		else
			kp.keyInfo = this.keyInfo;
		
		if(this.attribute != null)
			kp.attribute = (Attribute) this.attribute.clone();
		else
			kp.attribute = this.attribute;
		
		if(this.usage != null)
			kp.usage = (Usage) this.usage.clone();
		else
			kp.usage = this.usage;
		
		if(this.value != null)
			kp.value = (Value) this.value.clone();
		else
			kp.value = this.value;
		
		kp.uniqueID = this.uniqueID;
		kp.profileVersion = this.profileVersion;
		kp.errataVersion = this.errataVersion;
		kp.kmsLabel = this.kmsLabel;
		kp.xmlFile = this.xmlFile;
	
	    
		return kp;
	}

	public String getKmsLabel() {
		return kmsLabel;
	}

	public void setKmsLabel(String kmsLabel) {
		this.kmsLabel = kmsLabel;
	}

	public String getXmlFile() {
		return xmlFile;
	}

	public void setXmlFile(String xmlFile) {
		this.xmlFile = xmlFile;
	}
	
	
	
}
