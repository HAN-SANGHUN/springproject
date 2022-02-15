
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
 *       &lt;attribute name="Encrypt" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *       &lt;attribute name="Decrypt" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *       &lt;attribute name="DecryptEncrypt" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *       &lt;attribute name="Sign" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *       &lt;attribute name="Verify" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *       &lt;attribute name="Wrap" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *       &lt;attribute name="Unwrap" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *       &lt;attribute name="UnwrapWrap" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *       &lt;attribute name="Derive" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "")
@XmlRootElement(name = "Usage")
public class Usage {

    @XmlAttribute(name = "Encrypt")
    protected Boolean encrypt;
    @XmlAttribute(name = "Decrypt")
    protected Boolean decrypt;
    @XmlAttribute(name = "DecryptEncrypt")
    protected Boolean decryptEncrypt;
    @XmlAttribute(name = "Sign")
    protected Boolean sign;
    @XmlAttribute(name = "Verify")
    protected Boolean verify;
    @XmlAttribute(name = "Wrap")
    protected Boolean wrap;
    @XmlAttribute(name = "Unwrap")
    protected Boolean unwrap;
    @XmlAttribute(name = "UnwrapWrap")
    protected Boolean unwrapWrap;
    @XmlAttribute(name = "Derive")
    protected Boolean derive;

    /**
     * encrypt �Ӽ��� ���� �����ɴϴ�.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isEncrypt() {
        return encrypt;
    }

    /**
     * encrypt �Ӽ��� ���� �����մϴ�.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setEncrypt(Boolean value) {
        this.encrypt = value;
    }

    /**
     * decrypt �Ӽ��� ���� �����ɴϴ�.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isDecrypt() {
        return decrypt;
    }

    /**
     * decrypt �Ӽ��� ���� �����մϴ�.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setDecrypt(Boolean value) {
        this.decrypt = value;
    }

    /**
     * decryptEncrypt �Ӽ��� ���� �����ɴϴ�.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isDecryptEncrypt() {
        return decryptEncrypt;
    }

    /**
     * decryptEncrypt �Ӽ��� ���� �����մϴ�.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setDecryptEncrypt(Boolean value) {
        this.decryptEncrypt = value;
    }

    /**
     * sign �Ӽ��� ���� �����ɴϴ�.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isSign() {
        return sign;
    }

    /**
     * sign �Ӽ��� ���� �����մϴ�.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setSign(Boolean value) {
        this.sign = value;
    }

    /**
     * verify �Ӽ��� ���� �����ɴϴ�.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isVerify() {
        return verify;
    }

    /**
     * verify �Ӽ��� ���� �����մϴ�.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setVerify(Boolean value) {
        this.verify = value;
    }

    /**
     * wrap �Ӽ��� ���� �����ɴϴ�.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isWrap() {
        return wrap;
    }

    /**
     * wrap �Ӽ��� ���� �����մϴ�.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setWrap(Boolean value) {
        this.wrap = value;
    }

    /**
     * unwrap �Ӽ��� ���� �����ɴϴ�.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isUnwrap() {
        return unwrap;
    }

    /**
     * unwrap �Ӽ��� ���� �����մϴ�.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setUnwrap(Boolean value) {
        this.unwrap = value;
    }

    /**
     * unwrapWrap �Ӽ��� ���� �����ɴϴ�.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isUnwrapWrap() {
        return unwrapWrap;
    }

    /**
     * unwrapWrap �Ӽ��� ���� �����մϴ�.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setUnwrapWrap(Boolean value) {
        this.unwrapWrap = value;
    }

    /**
     * derive �Ӽ��� ���� �����ɴϴ�.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isDerive() {
        return derive;
    }

    /**
     * derive �Ӽ��� ���� �����մϴ�.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setDerive(Boolean value) {
        this.derive = value;
    }
    
    @Override
	public String toString() {
		return "Usage [encrypt=" + encrypt + ", decrypt=" + decrypt
				+ ", decryptEncrypt=" + decryptEncrypt + ", sign=" + sign
				+ ", verify=" + verify + ", wrap=" + wrap + ", unwrap="
				+ unwrap + ", unwrapWrap=" + unwrapWrap + ", derive=" + derive
				+ "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((decrypt == null) ? 0 : decrypt.hashCode());
		result = prime * result
				+ ((decryptEncrypt == null) ? 0 : decryptEncrypt.hashCode());
		result = prime * result + ((derive == null) ? 0 : derive.hashCode());
		result = prime * result + ((encrypt == null) ? 0 : encrypt.hashCode());
		result = prime * result + ((sign == null) ? 0 : sign.hashCode());
		result = prime * result + ((unwrap == null) ? 0 : unwrap.hashCode());
		result = prime * result
				+ ((unwrapWrap == null) ? 0 : unwrapWrap.hashCode());
		result = prime * result + ((verify == null) ? 0 : verify.hashCode());
		result = prime * result + ((wrap == null) ? 0 : wrap.hashCode());
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
		
		Usage other = (Usage) obj;
		
		if (decrypt == null) {
			if (other.decrypt != null)
				return false;
		} else if (!decrypt.equals(other.decrypt))
			return false;
		
		if (decryptEncrypt == null) {
			if (other.decryptEncrypt != null)
				return false;
		} else if (!decryptEncrypt.equals(other.decryptEncrypt))
			return false;
		
		if (derive == null) {
			if (other.derive != null)
				return false;
		} else if (!derive.equals(other.derive))
			return false;
		
		if (encrypt == null) {
			if (other.encrypt != null)
				return false;
		} else if (!encrypt.equals(other.encrypt))
			return false;
		
		if (sign == null) {
			if (other.sign != null)
				return false;
		} else if (!sign.equals(other.sign))
			return false;
		
		if (unwrap == null) {
			if (other.unwrap != null)
				return false;
		} else if (!unwrap.equals(other.unwrap))
			return false;
		
		if (unwrapWrap == null) {
			if (other.unwrapWrap != null)
				return false;
		} else if (!unwrapWrap.equals(other.unwrapWrap))
			return false;
		
		if (verify == null) {
			if (other.verify != null)
				return false;
		} else if (!verify.equals(other.verify))
			return false;
		
		if (wrap == null) {
			if (other.wrap != null)
				return false;
		} else if (!wrap.equals(other.wrap))
			return false;
		
		return true;
	}
	
	@Override
	public Object clone() throws CloneNotSupportedException {
		
		Usage us = new Usage();
		
		us.encrypt = this.encrypt;
		us.decrypt = this.decrypt;
		us.decryptEncrypt = this.decryptEncrypt;
		us.sign = this.sign;
		us.verify = this.verify;
		
		us.wrap = this.wrap;
		us.unwrap = this.unwrap;
		us.unwrapWrap = this.unwrapWrap;
		us.derive = this.derive;
		
	    return us;
	}

}
