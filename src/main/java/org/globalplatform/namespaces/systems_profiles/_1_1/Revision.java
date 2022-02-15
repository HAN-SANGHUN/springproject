
package org.globalplatform.namespaces.systems_profiles._1_1;

import java.io.Serializable;
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
import com.kona.kms.utils.Util;



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
 *         &lt;element ref="{http://namespaces.globalplatform.org/systems-profiles/1.1.2}Signature" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="ProfileID" type="{http://namespaces.globalplatform.org/systems-profiles/1.1.2}uniqueID" />
 *       &lt;attribute name="Version" use="required" type="{http://namespaces.globalplatform.org/systems-profiles/1.1.2}version" />
 *       &lt;attribute name="Date" use="required" type="{http://www.w3.org/2001/XMLSchema}date" />
 *       &lt;attribute name="Time" use="required" type="{http://www.w3.org/2001/XMLSchema}time" />
 *       &lt;attribute name="By" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="Digest" use="required" type="{http://www.w3.org/2001/XMLSchema}hexBinary" />
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
    "signature"
})
@XmlRootElement(name = "Revision")
public class Revision implements Serializable{

    @XmlElement(name = "Description")
    protected String description;
    @XmlElement(name = "Signature")
    protected Signature signature;
    @XmlAttribute(name = "ProfileID")
    @XmlJavaTypeAdapter(HexBinaryAdapter.class)
    protected byte[] profileID;
    @XmlAttribute(name = "Version", required = true)
    protected String version;
    @XmlAttribute(name = "Date", required = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar date;
    @XmlAttribute(name = "Time", required = true)
    @XmlSchemaType(name = "time")
    protected XMLGregorianCalendar time;
    @XmlAttribute(name = "By", required = true)
    protected String by;
    @XmlAttribute(name = "Digest", required = true)
    @XmlJavaTypeAdapter(HexBinaryAdapter.class)
    @XmlSchemaType(name = "hexBinary")
    protected byte[] digest;

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
     * signature �Ӽ��� ���� �����ɴϴ�.
     * 
     * @return
     *     possible object is
     *     {@link Signature }
     *     
     */
    public Signature getSignature() {
        return signature;
    }

    /**
     * signature �Ӽ��� ���� �����մϴ�.
     * 
     * @param value
     *     allowed object is
     *     {@link Signature }
     *     
     */
    public void setSignature(Signature value) {
        this.signature = value;
    }

    /**
     * profileID �Ӽ��� ���� �����ɴϴ�.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public byte[] getProfileID() {
        return profileID;
    }

    /**
     * profileID �Ӽ��� ���� �����մϴ�.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProfileID(byte[] value) {
        this.profileID = value;
    }

    /**
     * version �Ӽ��� ���� �����ɴϴ�.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVersion() {
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
    public void setVersion(String value) {
        this.version = value;
    }

    /**
     * date �Ӽ��� ���� �����ɴϴ�.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDate() {
        return date;
    }

    /**
     * date �Ӽ��� ���� �����մϴ�.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDate(XMLGregorianCalendar value) {
        this.date = value;
    }

    /**
     * time �Ӽ��� ���� �����ɴϴ�.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getTime() {
        return time;
    }

    /**
     * time �Ӽ��� ���� �����մϴ�.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setTime(XMLGregorianCalendar value) {
        this.time = value;
    }

    /**
     * by �Ӽ��� ���� �����ɴϴ�.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBy() {
        return by;
    }

    /**
     * by �Ӽ��� ���� �����մϴ�.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBy(String value) {
        this.by = value;
    }

    /**
     * digest �Ӽ��� ���� �����ɴϴ�.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public byte[] getDigest() {
        return digest;
    }

    /**
     * digest �Ӽ��� ���� �����մϴ�.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDigest(byte[] value) {
        this.digest = value;
    }
    
    @Override
	public String toString() {
		String returnValue = "Revision [description=" + description + ", signature="
				+ signature + ", profileID=";
		
		if(profileID == null)
			returnValue = returnValue + profileID;
		else
			returnValue = returnValue + StringUtil.toHexString(profileID);
		
		returnValue = returnValue + ", version=" + version + ", date=" + date + ", time=" + time
				+ ", by=" + by + ", digest=";
		
		if(digest == null)
			returnValue = returnValue + digest;
		else
			returnValue = returnValue + StringUtil.toHexString(digest);
		
		returnValue = returnValue + "]";
		
		
		
		return returnValue;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((by == null) ? 0 : by.hashCode());
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result
				+ ((description == null) ? 0 : description.hashCode());
		result = prime * result + Arrays.hashCode(digest);
		result = prime * result + Arrays.hashCode(profileID);
		result = prime * result
				+ ((signature == null) ? 0 : signature.hashCode());
		result = prime * result + ((time == null) ? 0 : time.hashCode());
		result = prime * result + ((version == null) ? 0 : version.hashCode());
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
		
		Revision other = (Revision) obj;
		
		if (by == null) {
			if (other.by != null)
				return false;
		} else if (!by.equals(other.by))
			return false;
		
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		
		if (!Arrays.equals(digest, other.digest))
			return false;
		
		if (!Arrays.equals(profileID, other.profileID))
			return false;
		
		if (signature == null) {
			if (other.signature != null)
				return false;
		} else if (!signature.equals(other.signature))
			return false;
		
		if (time == null) {
			if (other.time != null)
				return false;
		} else if (!time.equals(other.time))
			return false;
		
		if (version == null) {
			if (other.version != null)
				return false;
		} else if (!version.equals(other.version))
			return false;
		
		return true;
	}
	
	@Override
	public Object clone() throws CloneNotSupportedException {
		
		Revision re = new Revision();
		
		re.description = this.description;
		re.signature = this.signature;
		re.profileID = this.profileID;
		re.version = this.version;
		re.date = this.date;
		
		re.time = this.time;
		re.by = this.by;
		re.digest = this.digest;
		
	    return re;
	}

}
