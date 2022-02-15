
package com.konai.kmsexchangemessage;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import org.globalplatform.namespaces.systems_profiles._1_1.KeyProfile;


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
 *         &lt;element ref="{http://namespaces.globalplatform.org/systems-profiles/1.1.2}KeyProfile"/>
 *         &lt;element name="profileUniqueID" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="profileVersion" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="profileName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="desc" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="keyRole" type="{http://www.konai.com/KmsExchangeMessage}KeyRole"/>
 *         &lt;element name="keyName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="keyTypeCd" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="keySubTypeCd" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="keyVersion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="keyIdentfier" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="pairProfileUniqueID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="pairProfileVersion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="keyKMSLabelName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="keySize" type="{http://www.konai.com/KmsExchangeMessage}KeySize" minOccurs="0"/>
 *         &lt;element name="keyIndex" type="{http://www.konai.com/KmsExchangeMessage}KeyIndex" minOccurs="0"/>
 *         &lt;element name="startDate" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="endDate" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "keyProfile",
    "profileUniqueID",
    "profileVersion",
    "profileName",
    "desc",
    "keyRole",
    "keyName",
    "keyTypeCd",
    "keySubTypeCd",
    "keyVersion",
    "keyIdentfier",
    "pairProfileUniqueID",
    "pairProfileVersion",
    "keyKMSLabelName",
    "keySize",
    "keyIndex",
    "startDate",
    "endDate"
})
@XmlRootElement(name = "keyInfo")
public class KeyInfo {

    @XmlElement(name = "KeyProfile", namespace = "http://namespaces.globalplatform.org/systems-profiles/1.1.2", required = true)
    protected KeyProfile keyProfile;
    @XmlElement(required = true)
    protected String profileUniqueID;
    @XmlElement(required = true)
    protected String profileVersion;
    protected String profileName;
    protected String desc;
    @XmlElement(required = true)
    protected KeyRole keyRole;
    protected String keyName;
    protected String keyTypeCd;
    protected String keySubTypeCd;
    protected String keyVersion;
    protected String keyIdentfier;
    protected String pairProfileUniqueID;
    protected String pairProfileVersion;
    protected String keyKMSLabelName;
    protected Integer keySize;
    protected Integer keyIndex;
    protected String startDate;
    protected String endDate;

    /**
     * keyProfile �Ӽ��� ���� �����ɴϴ�.
     * 
     * @return
     *     possible object is
     *     {@link KeyProfile }
     *     
     */
    public KeyProfile getKeyProfile() {
        return keyProfile;
    }

    /**
     * keyProfile �Ӽ��� ���� �����մϴ�.
     * 
     * @param value
     *     allowed object is
     *     {@link KeyProfile }
     *     
     */
    public void setKeyProfile(KeyProfile value) {
        this.keyProfile = value;
    }

    /**
     * profileUniqueID �Ӽ��� ���� �����ɴϴ�.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProfileUniqueID() {
        return profileUniqueID;
    }

    /**
     * profileUniqueID �Ӽ��� ���� �����մϴ�.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProfileUniqueID(String value) {
        this.profileUniqueID = value;
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
     * profileName �Ӽ��� ���� �����ɴϴ�.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProfileName() {
        return profileName;
    }

    /**
     * profileName �Ӽ��� ���� �����մϴ�.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProfileName(String value) {
        this.profileName = value;
    }

    /**
     * desc �Ӽ��� ���� �����ɴϴ�.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDesc() {
        return desc;
    }

    /**
     * desc �Ӽ��� ���� �����մϴ�.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDesc(String value) {
        this.desc = value;
    }

    /**
     * keyRole �Ӽ��� ���� �����ɴϴ�.
     * 
     * @return
     *     possible object is
     *     {@link KeyRole }
     *     
     */
    public KeyRole getKeyRole() {
        return keyRole;
    }

    /**
     * keyRole �Ӽ��� ���� �����մϴ�.
     * 
     * @param value
     *     allowed object is
     *     {@link KeyRole }
     *     
     */
    public void setKeyRole(KeyRole value) {
        this.keyRole = value;
    }

    /**
     * keyName �Ӽ��� ���� �����ɴϴ�.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getKeyName() {
        return keyName;
    }

    /**
     * keyName �Ӽ��� ���� �����մϴ�.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setKeyName(String value) {
        this.keyName = value;
    }

    /**
     * keyTypeCd �Ӽ��� ���� �����ɴϴ�.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getKeyTypeCd() {
        return keyTypeCd;
    }

    /**
     * keyTypeCd �Ӽ��� ���� �����մϴ�.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setKeyTypeCd(String value) {
        this.keyTypeCd = value;
    }

    /**
     * keySubTypeCd �Ӽ��� ���� �����ɴϴ�.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getKeySubTypeCd() {
        return keySubTypeCd;
    }

    /**
     * keySubTypeCd �Ӽ��� ���� �����մϴ�.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setKeySubTypeCd(String value) {
        this.keySubTypeCd = value;
    }

    /**
     * keyVersion �Ӽ��� ���� �����ɴϴ�.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getKeyVersion() {
        return keyVersion;
    }

    /**
     * keyVersion �Ӽ��� ���� �����մϴ�.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setKeyVersion(String value) {
        this.keyVersion = value;
    }

    /**
     * keyIdentfier �Ӽ��� ���� �����ɴϴ�.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getKeyIdentfier() {
        return keyIdentfier;
    }

    /**
     * keyIdentfier �Ӽ��� ���� �����մϴ�.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setKeyIdentfier(String value) {
        this.keyIdentfier = value;
    }

    /**
     * pairProfileUniqueID �Ӽ��� ���� �����ɴϴ�.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPairProfileUniqueID() {
        return pairProfileUniqueID;
    }

    /**
     * pairProfileUniqueID �Ӽ��� ���� �����մϴ�.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPairProfileUniqueID(String value) {
        this.pairProfileUniqueID = value;
    }

    /**
     * pairProfileVersion �Ӽ��� ���� �����ɴϴ�.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPairProfileVersion() {
        return pairProfileVersion;
    }

    /**
     * pairProfileVersion �Ӽ��� ���� �����մϴ�.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPairProfileVersion(String value) {
        this.pairProfileVersion = value;
    }

    /**
     * keyKMSLabelName �Ӽ��� ���� �����ɴϴ�.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getKeyKMSLabelName() {
        return keyKMSLabelName;
    }

    /**
     * keyKMSLabelName �Ӽ��� ���� �����մϴ�.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setKeyKMSLabelName(String value) {
        this.keyKMSLabelName = value;
    }

    /**
     * keySize �Ӽ��� ���� �����ɴϴ�.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getKeySize() {
        return keySize;
    }

    /**
     * keySize �Ӽ��� ���� �����մϴ�.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setKeySize(Integer value) {
        this.keySize = value;
    }

    /**
     * keyIndex �Ӽ��� ���� �����ɴϴ�.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getKeyIndex() {
        return keyIndex;
    }

    /**
     * keyIndex �Ӽ��� ���� �����մϴ�.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setKeyIndex(Integer value) {
        this.keyIndex = value;
    }

    /**
     * startDate �Ӽ��� ���� �����ɴϴ�.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStartDate() {
        return startDate;
    }

    /**
     * startDate �Ӽ��� ���� �����մϴ�.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStartDate(String value) {
        this.startDate = value;
    }

    /**
     * endDate �Ӽ��� ���� �����ɴϴ�.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEndDate() {
        return endDate;
    }

    /**
     * endDate �Ӽ��� ���� �����մϴ�.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEndDate(String value) {
        this.endDate = value;
    }

}
