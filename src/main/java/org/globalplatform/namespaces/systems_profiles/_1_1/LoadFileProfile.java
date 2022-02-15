
package org.globalplatform.namespaces.systems_profiles._1_1;

import java.math.BigInteger;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.HexBinaryAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


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
 *         &lt;element ref="{http://namespaces.globalplatform.org/systems-profiles/1.1.2}ConflictRules"/>
 *         &lt;element ref="{http://namespaces.globalplatform.org/systems-profiles/1.1.2}LoadFileInfo"/>
 *         &lt;element ref="{http://namespaces.globalplatform.org/systems-profiles/1.1.2}Modules"/>
 *         &lt;element ref="{http://namespaces.globalplatform.org/systems-profiles/1.1.2}ApplicationCode" minOccurs="0"/>
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
    "conflictRules",
    "loadFileInfo",
    "modules",
    "applicationCode"
})
@XmlRootElement(name = "LoadFileProfile")
public class LoadFileProfile {

    @XmlElement(name = "Description")
    protected String description;
    @XmlElement(name = "Revisions", required = true)
    protected Revisions revisions;
    @XmlElement(name = "ConflictRules", required = true)
    protected ConflictRules conflictRules;
    @XmlElement(name = "LoadFileInfo", required = true)
    protected LoadFileInfo loadFileInfo;
    @XmlElement(name = "Modules", required = true)
    protected Modules modules;
    @XmlElement(name = "ApplicationCode")
    protected ApplicationCode applicationCode;
    @XmlAttribute(name = "UniqueID", required = true)
    @XmlJavaTypeAdapter(HexBinaryAdapter.class)
    protected byte[] uniqueID;
    @XmlAttribute(name = "ProfileVersion", required = true)
    protected String profileVersion;
    @XmlAttribute(name = "ErrataVersion")
    protected BigInteger errataVersion;

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
     * conflictRules �Ӽ��� ���� �����ɴϴ�.
     * 
     * @return
     *     possible object is
     *     {@link ConflictRules }
     *     
     */
    public ConflictRules getConflictRules() {
        return conflictRules;
    }

    /**
     * conflictRules �Ӽ��� ���� �����մϴ�.
     * 
     * @param value
     *     allowed object is
     *     {@link ConflictRules }
     *     
     */
    public void setConflictRules(ConflictRules value) {
        this.conflictRules = value;
    }

    /**
     * loadFileInfo �Ӽ��� ���� �����ɴϴ�.
     * 
     * @return
     *     possible object is
     *     {@link LoadFileInfo }
     *     
     */
    public LoadFileInfo getLoadFileInfo() {
        return loadFileInfo;
    }

    /**
     * loadFileInfo �Ӽ��� ���� �����մϴ�.
     * 
     * @param value
     *     allowed object is
     *     {@link LoadFileInfo }
     *     
     */
    public void setLoadFileInfo(LoadFileInfo value) {
        this.loadFileInfo = value;
    }

    /**
     * modules �Ӽ��� ���� �����ɴϴ�.
     * 
     * @return
     *     possible object is
     *     {@link Modules }
     *     
     */
    public Modules getModules() {
        return modules;
    }

    /**
     * modules �Ӽ��� ���� �����մϴ�.
     * 
     * @param value
     *     allowed object is
     *     {@link Modules }
     *     
     */
    public void setModules(Modules value) {
        this.modules = value;
    }

    /**
     * applicationCode �Ӽ��� ���� �����ɴϴ�.
     * 
     * @return
     *     possible object is
     *     {@link ApplicationCode }
     *     
     */
    public ApplicationCode getApplicationCode() {
        return applicationCode;
    }

    /**
     * applicationCode �Ӽ��� ���� �����մϴ�.
     * 
     * @param value
     *     allowed object is
     *     {@link ApplicationCode }
     *     
     */
    public void setApplicationCode(ApplicationCode value) {
        this.applicationCode = value;
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

}
