
package org.globalplatform.namespaces.systems_profiles._1_1;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.HexBinaryAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * <p>anonymous complex type에 대한 Java 클래스입니다.
 * 
 * <p>다음 스키마 단편이 이 클래스에 포함되는 필요한 콘텐츠를 지정합니다.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element ref="{http://namespaces.globalplatform.org/systems-profiles/1.1.2}Description" minOccurs="0"/>
 *         &lt;element ref="{http://namespaces.globalplatform.org/systems-profiles/1.1.2}Revisions"/>
 *         &lt;element ref="{http://namespaces.globalplatform.org/systems-profiles/1.1.2}ConflictRules"/>
 *         &lt;element ref="{http://namespaces.globalplatform.org/systems-profiles/1.1.2}ApplicationInfo"/>
 *         &lt;element ref="{http://namespaces.globalplatform.org/systems-profiles/1.1.2}Key" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element ref="{http://namespaces.globalplatform.org/systems-profiles/1.1.2}DataElement" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element ref="{http://namespaces.globalplatform.org/systems-profiles/1.1.2}Function" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element ref="{http://namespaces.globalplatform.org/systems-profiles/1.1.2}SecureChannel" minOccurs="0"/>
 *         &lt;element ref="{http://namespaces.globalplatform.org/systems-profiles/1.1.2}ScriptFragment" maxOccurs="unbounded" minOccurs="0"/>
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
    "applicationInfo",
    "key",
    "dataElement",
    "function",
    "secureChannel",
    "scriptFragment"
})
@XmlRootElement(name = "ApplicationProfile")
public class ApplicationProfile {

    @XmlElement(name = "Description")
    protected String description;
    @XmlElement(name = "Revisions", required = true)
    protected Revisions revisions;
    @XmlElement(name = "ConflictRules", required = true)
    protected ConflictRules conflictRules;
    @XmlElement(name = "ApplicationInfo", required = true)
    protected ApplicationInfo applicationInfo;
    @XmlElement(name = "Key")
    protected List<Key> key;
    @XmlElement(name = "DataElement")
    protected List<DataElement> dataElement;
    @XmlElement(name = "Function")
    protected List<Function> function;
    @XmlElement(name = "SecureChannel")
    protected SecureChannel secureChannel;
    @XmlElement(name = "ScriptFragment")
    protected List<ScriptFragment> scriptFragment;
    @XmlAttribute(name = "UniqueID", required = true)
    @XmlJavaTypeAdapter(HexBinaryAdapter.class)
    protected byte[] uniqueID;
    @XmlAttribute(name = "ProfileVersion", required = true)
    protected String profileVersion;
    @XmlAttribute(name = "ErrataVersion")
    protected BigInteger errataVersion;

    /**
     * description 속성의 값을 가져옵니다.
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
     * description 속성의 값을 설정합니다.
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
     * revisions 속성의 값을 가져옵니다.
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
     * revisions 속성의 값을 설정합니다.
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
     * conflictRules 속성의 값을 가져옵니다.
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
     * conflictRules 속성의 값을 설정합니다.
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
     * applicationInfo 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link ApplicationInfo }
     *     
     */
    public ApplicationInfo getApplicationInfo() {
        return applicationInfo;
    }

    /**
     * applicationInfo 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link ApplicationInfo }
     *     
     */
    public void setApplicationInfo(ApplicationInfo value) {
        this.applicationInfo = value;
    }

    /**
     * Gets the value of the key property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the key property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getKey().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Key }
     * 
     * 
     */
    public List<Key> getKey() {
        if (key == null) {
            key = new ArrayList<Key>();
        }
        return this.key;
    }

    /**
     * Gets the value of the dataElement property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the dataElement property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getDataElement().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link DataElement }
     * 
     * 
     */
    public List<DataElement> getDataElement() {
        if (dataElement == null) {
            dataElement = new ArrayList<DataElement>();
        }
        return this.dataElement;
    }

    /**
     * Gets the value of the function property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the function property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getFunction().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Function }
     * 
     * 
     */
    public List<Function> getFunction() {
        if (function == null) {
            function = new ArrayList<Function>();
        }
        return this.function;
    }

    /**
     * secureChannel 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link SecureChannel }
     *     
     */
    public SecureChannel getSecureChannel() {
        return secureChannel;
    }

    /**
     * secureChannel 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link SecureChannel }
     *     
     */
    public void setSecureChannel(SecureChannel value) {
        this.secureChannel = value;
    }

    /**
     * Gets the value of the scriptFragment property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the scriptFragment property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getScriptFragment().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ScriptFragment }
     * 
     * 
     */
    public List<ScriptFragment> getScriptFragment() {
        if (scriptFragment == null) {
            scriptFragment = new ArrayList<ScriptFragment>();
        }
        return this.scriptFragment;
    }

    /**
     * uniqueID 속성의 값을 가져옵니다.
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
     * uniqueID 속성의 값을 설정합니다.
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
     * profileVersion 속성의 값을 가져옵니다.
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
     * profileVersion 속성의 값을 설정합니다.
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
     * errataVersion 속성의 값을 가져옵니다.
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
     * errataVersion 속성의 값을 설정합니다.
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
