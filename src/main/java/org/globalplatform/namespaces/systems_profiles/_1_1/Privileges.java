
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
 *       &lt;attribute name="SecurityDomain" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *       &lt;attribute name="DAPVerification" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *       &lt;attribute name="DelegatedManagement" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *       &lt;attribute name="CardManagerLock" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *       &lt;attribute name="CardLock" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *       &lt;attribute name="CardTerminate" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *       &lt;attribute name="DefaultSelected" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *       &lt;attribute name="CardReset" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *       &lt;attribute name="CVMChange" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *       &lt;attribute name="CVMManagement" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *       &lt;attribute name="ManadatedDAPVerification" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *       &lt;attribute name="TrustedPath" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *       &lt;attribute name="AuthorizedManagement" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *       &lt;attribute name="TokenVerification" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *       &lt;attribute name="GlobalDelete" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *       &lt;attribute name="GlobalLock" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *       &lt;attribute name="GlobalRegistry" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *       &lt;attribute name="FinalApplication" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *       &lt;attribute name="GlobalService" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *       &lt;attribute name="ReceiptGeneration" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *       &lt;attribute name="CipheredLoadFileDataBlock" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *       &lt;attribute name="ContactlessActivation" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *       &lt;attribute name="ContactlessSelfActivation" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "")
@XmlRootElement(name = "Privileges")
public class Privileges {

    @XmlAttribute(name = "SecurityDomain")
    protected Boolean securityDomain;
    @XmlAttribute(name = "DAPVerification")
    protected Boolean dapVerification;
    @XmlAttribute(name = "DelegatedManagement")
    protected Boolean delegatedManagement;
    @XmlAttribute(name = "CardManagerLock")
    protected Boolean cardManagerLock;
    @XmlAttribute(name = "CardLock")
    protected Boolean cardLock;
    @XmlAttribute(name = "CardTerminate")
    protected Boolean cardTerminate;
    @XmlAttribute(name = "DefaultSelected")
    protected Boolean defaultSelected;
    @XmlAttribute(name = "CardReset")
    protected Boolean cardReset;
    @XmlAttribute(name = "CVMChange")
    protected Boolean cvmChange;
    @XmlAttribute(name = "CVMManagement")
    protected Boolean cvmManagement;
    @XmlAttribute(name = "ManadatedDAPVerification")
    protected Boolean manadatedDAPVerification;
    @XmlAttribute(name = "TrustedPath")
    protected Boolean trustedPath;
    @XmlAttribute(name = "AuthorizedManagement")
    protected Boolean authorizedManagement;
    @XmlAttribute(name = "TokenVerification")
    protected Boolean tokenVerification;
    @XmlAttribute(name = "GlobalDelete")
    protected Boolean globalDelete;
    @XmlAttribute(name = "GlobalLock")
    protected Boolean globalLock;
    @XmlAttribute(name = "GlobalRegistry")
    protected Boolean globalRegistry;
    @XmlAttribute(name = "FinalApplication")
    protected Boolean finalApplication;
    @XmlAttribute(name = "GlobalService")
    protected Boolean globalService;
    @XmlAttribute(name = "ReceiptGeneration")
    protected Boolean receiptGeneration;
    @XmlAttribute(name = "CipheredLoadFileDataBlock")
    protected Boolean cipheredLoadFileDataBlock;
    @XmlAttribute(name = "ContactlessActivation")
    protected Boolean contactlessActivation;
    @XmlAttribute(name = "ContactlessSelfActivation")
    protected Boolean contactlessSelfActivation;

    /**
     * securityDomain �Ӽ��� ���� �����ɴϴ�.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isSecurityDomain() {
        return securityDomain;
    }

    /**
     * securityDomain �Ӽ��� ���� �����մϴ�.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setSecurityDomain(Boolean value) {
        this.securityDomain = value;
    }

    /**
     * dapVerification �Ӽ��� ���� �����ɴϴ�.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isDAPVerification() {
        return dapVerification;
    }

    /**
     * dapVerification �Ӽ��� ���� �����մϴ�.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setDAPVerification(Boolean value) {
        this.dapVerification = value;
    }

    /**
     * delegatedManagement �Ӽ��� ���� �����ɴϴ�.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isDelegatedManagement() {
        return delegatedManagement;
    }

    /**
     * delegatedManagement �Ӽ��� ���� �����մϴ�.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setDelegatedManagement(Boolean value) {
        this.delegatedManagement = value;
    }

    /**
     * cardManagerLock �Ӽ��� ���� �����ɴϴ�.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isCardManagerLock() {
        return cardManagerLock;
    }

    /**
     * cardManagerLock �Ӽ��� ���� �����մϴ�.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setCardManagerLock(Boolean value) {
        this.cardManagerLock = value;
    }

    /**
     * cardLock �Ӽ��� ���� �����ɴϴ�.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isCardLock() {
        return cardLock;
    }

    /**
     * cardLock �Ӽ��� ���� �����մϴ�.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setCardLock(Boolean value) {
        this.cardLock = value;
    }

    /**
     * cardTerminate �Ӽ��� ���� �����ɴϴ�.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isCardTerminate() {
        return cardTerminate;
    }

    /**
     * cardTerminate �Ӽ��� ���� �����մϴ�.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setCardTerminate(Boolean value) {
        this.cardTerminate = value;
    }

    /**
     * defaultSelected �Ӽ��� ���� �����ɴϴ�.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isDefaultSelected() {
        return defaultSelected;
    }

    /**
     * defaultSelected �Ӽ��� ���� �����մϴ�.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setDefaultSelected(Boolean value) {
        this.defaultSelected = value;
    }

    /**
     * cardReset �Ӽ��� ���� �����ɴϴ�.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isCardReset() {
        return cardReset;
    }

    /**
     * cardReset �Ӽ��� ���� �����մϴ�.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setCardReset(Boolean value) {
        this.cardReset = value;
    }

    /**
     * cvmChange �Ӽ��� ���� �����ɴϴ�.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isCVMChange() {
        return cvmChange;
    }

    /**
     * cvmChange �Ӽ��� ���� �����մϴ�.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setCVMChange(Boolean value) {
        this.cvmChange = value;
    }

    /**
     * cvmManagement �Ӽ��� ���� �����ɴϴ�.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isCVMManagement() {
        return cvmManagement;
    }

    /**
     * cvmManagement �Ӽ��� ���� �����մϴ�.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setCVMManagement(Boolean value) {
        this.cvmManagement = value;
    }

    /**
     * manadatedDAPVerification �Ӽ��� ���� �����ɴϴ�.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isManadatedDAPVerification() {
        return manadatedDAPVerification;
    }

    /**
     * manadatedDAPVerification �Ӽ��� ���� �����մϴ�.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setManadatedDAPVerification(Boolean value) {
        this.manadatedDAPVerification = value;
    }

    /**
     * trustedPath �Ӽ��� ���� �����ɴϴ�.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isTrustedPath() {
        return trustedPath;
    }

    /**
     * trustedPath �Ӽ��� ���� �����մϴ�.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setTrustedPath(Boolean value) {
        this.trustedPath = value;
    }

    /**
     * authorizedManagement �Ӽ��� ���� �����ɴϴ�.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isAuthorizedManagement() {
        return authorizedManagement;
    }

    /**
     * authorizedManagement �Ӽ��� ���� �����մϴ�.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setAuthorizedManagement(Boolean value) {
        this.authorizedManagement = value;
    }

    /**
     * tokenVerification �Ӽ��� ���� �����ɴϴ�.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isTokenVerification() {
        return tokenVerification;
    }

    /**
     * tokenVerification �Ӽ��� ���� �����մϴ�.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setTokenVerification(Boolean value) {
        this.tokenVerification = value;
    }

    /**
     * globalDelete �Ӽ��� ���� �����ɴϴ�.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isGlobalDelete() {
        return globalDelete;
    }

    /**
     * globalDelete �Ӽ��� ���� �����մϴ�.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setGlobalDelete(Boolean value) {
        this.globalDelete = value;
    }

    /**
     * globalLock �Ӽ��� ���� �����ɴϴ�.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isGlobalLock() {
        return globalLock;
    }

    /**
     * globalLock �Ӽ��� ���� �����մϴ�.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setGlobalLock(Boolean value) {
        this.globalLock = value;
    }

    /**
     * globalRegistry �Ӽ��� ���� �����ɴϴ�.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isGlobalRegistry() {
        return globalRegistry;
    }

    /**
     * globalRegistry �Ӽ��� ���� �����մϴ�.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setGlobalRegistry(Boolean value) {
        this.globalRegistry = value;
    }

    /**
     * finalApplication �Ӽ��� ���� �����ɴϴ�.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isFinalApplication() {
        return finalApplication;
    }

    /**
     * finalApplication �Ӽ��� ���� �����մϴ�.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setFinalApplication(Boolean value) {
        this.finalApplication = value;
    }

    /**
     * globalService �Ӽ��� ���� �����ɴϴ�.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isGlobalService() {
        return globalService;
    }

    /**
     * globalService �Ӽ��� ���� �����մϴ�.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setGlobalService(Boolean value) {
        this.globalService = value;
    }

    /**
     * receiptGeneration �Ӽ��� ���� �����ɴϴ�.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isReceiptGeneration() {
        return receiptGeneration;
    }

    /**
     * receiptGeneration �Ӽ��� ���� �����մϴ�.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setReceiptGeneration(Boolean value) {
        this.receiptGeneration = value;
    }

    /**
     * cipheredLoadFileDataBlock �Ӽ��� ���� �����ɴϴ�.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isCipheredLoadFileDataBlock() {
        return cipheredLoadFileDataBlock;
    }

    /**
     * cipheredLoadFileDataBlock �Ӽ��� ���� �����մϴ�.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setCipheredLoadFileDataBlock(Boolean value) {
        this.cipheredLoadFileDataBlock = value;
    }

    /**
     * contactlessActivation �Ӽ��� ���� �����ɴϴ�.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isContactlessActivation() {
        return contactlessActivation;
    }

    /**
     * contactlessActivation �Ӽ��� ���� �����մϴ�.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setContactlessActivation(Boolean value) {
        this.contactlessActivation = value;
    }

    /**
     * contactlessSelfActivation �Ӽ��� ���� �����ɴϴ�.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isContactlessSelfActivation() {
        return contactlessSelfActivation;
    }

    /**
     * contactlessSelfActivation �Ӽ��� ���� �����մϴ�.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setContactlessSelfActivation(Boolean value) {
        this.contactlessSelfActivation = value;
    }

}
