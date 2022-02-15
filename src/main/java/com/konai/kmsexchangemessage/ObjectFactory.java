
package com.konai.kmsexchangemessage;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.konai.kmsexchangemessage package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _CompanyInfoResponse_QNAME = new QName("http://www.konai.com/KmsExchangeMessage", "CompanyInfoResponse");
    private final static QName _KeyInfoResponse_QNAME = new QName("http://www.konai.com/KmsExchangeMessage", "KeyInfoResponse");
    private final static QName _CompanyInfoRequest_QNAME = new QName("http://www.konai.com/KmsExchangeMessage", "CompanyInfoRequest");
    private final static QName _RegisteredBINCertificationResponse_QNAME = new QName("http://www.konai.com/KmsExchangeMessage", "RegisteredBINCertificationResponse");
    private final static QName _RequestedBINCertificationResponse_QNAME = new QName("http://www.konai.com/KmsExchangeMessage", "RequestedBINCertificationResponse");
    private final static QName _KeyInfoRequest_QNAME = new QName("http://www.konai.com/KmsExchangeMessage", "KeyInfoRequest");
    private final static QName _BinNO_QNAME = new QName("http://www.konai.com/KmsExchangeMessage", "binNO");
    private final static QName _TrackingNo_QNAME = new QName("http://www.konai.com/KmsExchangeMessage", "trackingNo");
    private final static QName _BrandTypeCd_QNAME = new QName("http://www.konai.com/KmsExchangeMessage", "brandTypeCd");
    private final static QName _CompanyID_QNAME = new QName("http://www.konai.com/KmsExchangeMessage", "companyID");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.konai.kmsexchangemessage
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link RegisteredBINCertification }
     * 
     */
    public RegisteredBINCertification createRegisteredBINCertification() {
        return new RegisteredBINCertification();
    }

    /**
     * Create an instance of {@link KeyInfo }
     * 
     */
    public KeyInfo createKeyInfo() {
        return new KeyInfo();
    }

    /**
     * Create an instance of {@link CompanyRequestType }
     * 
     */
    public CompanyRequestType createCompanyRequestType() {
        return new CompanyRequestType();
    }

    /**
     * Create an instance of {@link RequestedBINCertification }
     * 
     */
    public RequestedBINCertification createRequestedBINCertification() {
        return new RequestedBINCertification();
    }

    /**
     * Create an instance of {@link KeyInfos }
     * 
     */
    public KeyInfos createKeyInfos() {
        return new KeyInfos();
    }

    /**
     * Create an instance of {@link CompanyInfo }
     * 
     */
    public CompanyInfo createCompanyInfo() {
        return new CompanyInfo();
    }

    /**
     * Create an instance of {@link KeyInfoRequestType }
     * 
     */
    public KeyInfoRequestType createKeyInfoRequestType() {
        return new KeyInfoRequestType();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ResponseType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.konai.com/KmsExchangeMessage", name = "CompanyInfoResponse")
    public JAXBElement<ResponseType> createCompanyInfoResponse(ResponseType value) {
        return new JAXBElement<ResponseType>(_CompanyInfoResponse_QNAME, ResponseType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ResponseType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.konai.com/KmsExchangeMessage", name = "KeyInfoResponse")
    public JAXBElement<ResponseType> createKeyInfoResponse(ResponseType value) {
        return new JAXBElement<ResponseType>(_KeyInfoResponse_QNAME, ResponseType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CompanyRequestType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.konai.com/KmsExchangeMessage", name = "CompanyInfoRequest")
    public JAXBElement<CompanyRequestType> createCompanyInfoRequest(CompanyRequestType value) {
        return new JAXBElement<CompanyRequestType>(_CompanyInfoRequest_QNAME, CompanyRequestType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ResponseType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.konai.com/KmsExchangeMessage", name = "RegisteredBINCertificationResponse")
    public JAXBElement<ResponseType> createRegisteredBINCertificationResponse(ResponseType value) {
        return new JAXBElement<ResponseType>(_RegisteredBINCertificationResponse_QNAME, ResponseType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ResponseType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.konai.com/KmsExchangeMessage", name = "RequestedBINCertificationResponse")
    public JAXBElement<ResponseType> createRequestedBINCertificationResponse(ResponseType value) {
        return new JAXBElement<ResponseType>(_RequestedBINCertificationResponse_QNAME, ResponseType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link KeyInfoRequestType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.konai.com/KmsExchangeMessage", name = "KeyInfoRequest")
    public JAXBElement<KeyInfoRequestType> createKeyInfoRequest(KeyInfoRequestType value) {
        return new JAXBElement<KeyInfoRequestType>(_KeyInfoRequest_QNAME, KeyInfoRequestType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.konai.com/KmsExchangeMessage", name = "binNO")
    public JAXBElement<String> createBinNO(String value) {
        return new JAXBElement<String>(_BinNO_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.konai.com/KmsExchangeMessage", name = "trackingNo")
    public JAXBElement<String> createTrackingNo(String value) {
        return new JAXBElement<String>(_TrackingNo_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.konai.com/KmsExchangeMessage", name = "brandTypeCd")
    public JAXBElement<String> createBrandTypeCd(String value) {
        return new JAXBElement<String>(_BrandTypeCd_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.konai.com/KmsExchangeMessage", name = "companyID")
    public JAXBElement<String> createCompanyID(String value) {
        return new JAXBElement<String>(_CompanyID_QNAME, String.class, null, value);
    }

}
