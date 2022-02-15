
package org.globalplatform.namespaces.systems_profiles._1_1;

import java.math.BigDecimal;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the org.globalplatform.namespaces.systems_profiles._1_1 package. 
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

    private final static QName _Value_QNAME = new QName("http://namespaces.globalplatform.org/systems-profiles/1.1.2", "value");
    private final static QName _Side_QNAME = new QName("http://namespaces.globalplatform.org/systems-profiles/1.1.2", "Side");
    private final static QName _CheckDigit_QNAME = new QName("http://namespaces.globalplatform.org/systems-profiles/1.1.2", "CheckDigit");
    private final static QName _Blue_QNAME = new QName("http://namespaces.globalplatform.org/systems-profiles/1.1.2", "Blue");
    private final static QName _CardDesignOrientation_QNAME = new QName("http://namespaces.globalplatform.org/systems-profiles/1.1.2", "CardDesignOrientation");
    private final static QName _Style_QNAME = new QName("http://namespaces.globalplatform.org/systems-profiles/1.1.2", "Style");
    private final static QName _ConfigurationID_QNAME = new QName("http://namespaces.globalplatform.org/systems-profiles/1.1.2", "ConfigurationID");
    private final static QName _LineStyle_QNAME = new QName("http://namespaces.globalplatform.org/systems-profiles/1.1.2", "LineStyle");
    private final static QName _Green_QNAME = new QName("http://namespaces.globalplatform.org/systems-profiles/1.1.2", "Green");
    private final static QName _AnchorPosition_QNAME = new QName("http://namespaces.globalplatform.org/systems-profiles/1.1.2", "AnchorPosition");
    private final static QName _WriteLevelStandard_QNAME = new QName("http://namespaces.globalplatform.org/systems-profiles/1.1.2", "WriteLevelStandard");
    private final static QName _DPI_QNAME = new QName("http://namespaces.globalplatform.org/systems-profiles/1.1.2", "DPI");
    private final static QName _HumanReadableText_QNAME = new QName("http://namespaces.globalplatform.org/systems-profiles/1.1.2", "HumanReadableText");
    private final static QName _PersonalizationOrderConstraint_QNAME = new QName("http://namespaces.globalplatform.org/systems-profiles/1.1.2", "PersonalizationOrderConstraint");
    private final static QName _Underline_QNAME = new QName("http://namespaces.globalplatform.org/systems-profiles/1.1.2", "Underline");
    private final static QName _HumanReadableStartDigit_QNAME = new QName("http://namespaces.globalplatform.org/systems-profiles/1.1.2", "HumanReadableStartDigit");
    private final static QName _Unit_QNAME = new QName("http://namespaces.globalplatform.org/systems-profiles/1.1.2", "Unit");
    private final static QName _CardDesignOrigin_QNAME = new QName("http://namespaces.globalplatform.org/systems-profiles/1.1.2", "CardDesignOrigin");
    private final static QName _Material_QNAME = new QName("http://namespaces.globalplatform.org/systems-profiles/1.1.2", "Material");
    private final static QName _Type_QNAME = new QName("http://namespaces.globalplatform.org/systems-profiles/1.1.2", "Type");
    private final static QName _HumanReadableCheckDigit_QNAME = new QName("http://namespaces.globalplatform.org/systems-profiles/1.1.2", "HumanReadableCheckDigit");
    private final static QName _CardStockID_QNAME = new QName("http://namespaces.globalplatform.org/systems-profiles/1.1.2", "CardStockID");
    private final static QName _TrackTypeID_QNAME = new QName("http://namespaces.globalplatform.org/systems-profiles/1.1.2", "TrackTypeID");
    private final static QName _ZPosition_QNAME = new QName("http://namespaces.globalplatform.org/systems-profiles/1.1.2", "ZPosition");
    private final static QName _Name_QNAME = new QName("http://namespaces.globalplatform.org/systems-profiles/1.1.2", "Name");
    private final static QName _MaterialID_QNAME = new QName("http://namespaces.globalplatform.org/systems-profiles/1.1.2", "MaterialID");
    private final static QName _QuietZone_QNAME = new QName("http://namespaces.globalplatform.org/systems-profiles/1.1.2", "QuietZone");
    private final static QName _Description_QNAME = new QName("http://namespaces.globalplatform.org/systems-profiles/1.1.2", "Description");
    private final static QName _Red_QNAME = new QName("http://namespaces.globalplatform.org/systems-profiles/1.1.2", "Red");
    private final static QName _Topping_QNAME = new QName("http://namespaces.globalplatform.org/systems-profiles/1.1.2", "Topping");
    private final static QName _Script_QNAME = new QName("http://namespaces.globalplatform.org/systems-profiles/1.1.2", "Script");
    private final static QName _FontSize_QNAME = new QName("http://namespaces.globalplatform.org/systems-profiles/1.1.2", "Size");
    private final static QName _FontForegroundColor_QNAME = new QName("http://namespaces.globalplatform.org/systems-profiles/1.1.2", "ForegroundColor");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: org.globalplatform.namespaces.systems_profiles._1_1
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link KeyProfile }
     * 
     */
    public KeyProfile createKeyProfile() {
        return new KeyProfile();
    }

    /**
     * Create an instance of {@link Revisions }
     * 
     */
    public Revisions createRevisions() {
        return new Revisions();
    }

    /**
     * Create an instance of {@link Revision }
     * 
     */
    public Revision createRevision() {
        return new Revision();
    }

    /**
     * Create an instance of {@link Signature }
     * 
     */
    public Signature createSignature() {
        return new Signature();
    }

    /**
     * Create an instance of {@link KeyInfo }
     * 
     */
    public KeyInfo createKeyInfo() {
        return new KeyInfo();
    }

    /**
     * Create an instance of {@link KeyPart }
     * 
     */
    public KeyPart createKeyPart() {
        return new KeyPart();
    }

    /**
     * Create an instance of {@link TransportKey }
     * 
     */
    public TransportKey createTransportKey() {
        return new TransportKey();
    }

    /**
     * Create an instance of {@link Attribute }
     * 
     */
    public Attribute createAttribute() {
        return new Attribute();
    }

    /**
     * Create an instance of {@link Usage }
     * 
     */
    public Usage createUsage() {
        return new Usage();
    }

    /**
     * Create an instance of {@link Value }
     * 
     */
    public Value createValue() {
        return new Value();
    }

    /**
     * Create an instance of {@link Component }
     * 
     */
    public Component createComponent() {
        return new Component();
    }

    /**
     * Create an instance of {@link Key }
     * 
     */
    public Key createKey() {
        return new Key();
    }

    /**
     * Create an instance of {@link Protocols }
     * 
     */
    public Protocols createProtocols() {
        return new Protocols();
    }

    /**
     * Create an instance of {@link Protocol }
     * 
     */
    public Protocol createProtocol() {
        return new Protocol();
    }

    /**
     * Create an instance of {@link SquareCorner }
     * 
     */
    public SquareCorner createSquareCorner() {
        return new SquareCorner();
    }

    /**
     * Create an instance of {@link Privileges }
     * 
     */
    public Privileges createPrivileges() {
        return new Privileges();
    }

    /**
     * Create an instance of {@link Communication }
     * 
     */
    public Communication createCommunication() {
        return new Communication();
    }

    /**
     * Create an instance of {@link Contact }
     * 
     */
    public Contact createContact() {
        return new Contact();
    }

    /**
     * Create an instance of {@link Contactless }
     * 
     */
    public Contactless createContactless() {
        return new Contactless();
    }

    /**
     * Create an instance of {@link CardPrintingProfile }
     * 
     */
    public CardPrintingProfile createCardPrintingProfile() {
        return new CardPrintingProfile();
    }

    /**
     * Create an instance of {@link Emboss }
     * 
     */
    public Emboss createEmboss() {
        return new Emboss();
    }

    /**
     * Create an instance of {@link Label }
     * 
     */
    public Label createLabel() {
        return new Label();
    }

    /**
     * Create an instance of {@link MagneticEncodingType }
     * 
     */
    public MagneticEncodingType createMagneticEncodingType() {
        return new MagneticEncodingType();
    }

    /**
     * Create an instance of {@link Overlay }
     * 
     */
    public Overlay createOverlay() {
        return new Overlay();
    }

    /**
     * Create an instance of {@link Barcode }
     * 
     */
    public Barcode createBarcode() {
        return new Barcode();
    }

    /**
     * Create an instance of {@link Text }
     * 
     */
    public Text createText() {
        return new Text();
    }

    /**
     * Create an instance of {@link Image }
     * 
     */
    public Image createImage() {
        return new Image();
    }

    /**
     * Create an instance of {@link Clock }
     * 
     */
    public Clock createClock() {
        return new Clock();
    }

    /**
     * Create an instance of {@link Module }
     * 
     */
    public Module createModule() {
        return new Module();
    }

    /**
     * Create an instance of {@link CardManufacturerProduct }
     * 
     */
    public CardManufacturerProduct createCardManufacturerProduct() {
        return new CardManufacturerProduct();
    }

    /**
     * Create an instance of {@link CardManufacturer }
     * 
     */
    public CardManufacturer createCardManufacturer() {
        return new CardManufacturer();
    }

    /**
     * Create an instance of {@link Chip }
     * 
     */
    public Chip createChip() {
        return new Chip();
    }

    /**
     * Create an instance of {@link Resources }
     * 
     */
    public Resources createResources() {
        return new Resources();
    }

    /**
     * Create an instance of {@link Powers }
     * 
     */
    public Powers createPowers() {
        return new Powers();
    }

    /**
     * Create an instance of {@link Power }
     * 
     */
    public Power createPower() {
        return new Power();
    }

    /**
     * Create an instance of {@link CryptoEngine }
     * 
     */
    public CryptoEngine createCryptoEngine() {
        return new CryptoEngine();
    }

    /**
     * Create an instance of {@link KeyTypes }
     * 
     */
    public KeyTypes createKeyTypes() {
        return new KeyTypes();
    }

    /**
     * Create an instance of {@link KeyType }
     * 
     */
    public KeyType createKeyType() {
        return new KeyType();
    }

    /**
     * Create an instance of {@link Algorithm }
     * 
     */
    public Algorithm createAlgorithm() {
        return new Algorithm();
    }

    /**
     * Create an instance of {@link Platform }
     * 
     */
    public Platform createPlatform() {
        return new Platform();
    }

    /**
     * Create an instance of {@link CloseSecureChannel }
     * 
     */
    public CloseSecureChannel createCloseSecureChannel() {
        return new CloseSecureChannel();
    }

    /**
     * Create an instance of {@link KeyDeclaration }
     * 
     */
    public KeyDeclaration createKeyDeclaration() {
        return new KeyDeclaration();
    }

    /**
     * Create an instance of {@link LoadFileInstance }
     * 
     */
    public LoadFileInstance createLoadFileInstance() {
        return new LoadFileInstance();
    }

    /**
     * Create an instance of {@link Wrap }
     * 
     */
    public Wrap createWrap() {
        return new Wrap();
    }

    /**
     * Create an instance of {@link ResourcesAvailable }
     * 
     */
    public ResourcesAvailable createResourcesAvailable() {
        return new ResourcesAvailable();
    }

    /**
     * Create an instance of {@link ApplicationInstance }
     * 
     */
    public ApplicationInstance createApplicationInstance() {
        return new ApplicationInstance();
    }

    /**
     * Create an instance of {@link ApplicationProfile }
     * 
     */
    public ApplicationProfile createApplicationProfile() {
        return new ApplicationProfile();
    }

    /**
     * Create an instance of {@link ConflictRules }
     * 
     */
    public ConflictRules createConflictRules() {
        return new ConflictRules();
    }

    /**
     * Create an instance of {@link ConflictRule }
     * 
     */
    public ConflictRule createConflictRule() {
        return new ConflictRule();
    }

    /**
     * Create an instance of {@link ApplicationInfo }
     * 
     */
    public ApplicationInfo createApplicationInfo() {
        return new ApplicationInfo();
    }

    /**
     * Create an instance of {@link LifeCycles }
     * 
     */
    public LifeCycles createLifeCycles() {
        return new LifeCycles();
    }

    /**
     * Create an instance of {@link LifeCycle }
     * 
     */
    public LifeCycle createLifeCycle() {
        return new LifeCycle();
    }

    /**
     * Create an instance of {@link DataElement }
     * 
     */
    public DataElement createDataElement() {
        return new DataElement();
    }

    /**
     * Create an instance of {@link Function }
     * 
     */
    public Function createFunction() {
        return new Function();
    }

    /**
     * Create an instance of {@link SecureChannel }
     * 
     */
    public SecureChannel createSecureChannel() {
        return new SecureChannel();
    }

    /**
     * Create an instance of {@link OpenSecureChannel }
     * 
     */
    public OpenSecureChannel createOpenSecureChannel() {
        return new OpenSecureChannel();
    }

    /**
     * Create an instance of {@link ScriptFragment }
     * 
     */
    public ScriptFragment createScriptFragment() {
        return new ScriptFragment();
    }

    /**
     * Create an instance of {@link Declaration }
     * 
     */
    public Declaration createDeclaration() {
        return new Declaration();
    }

    /**
     * Create an instance of {@link CardInfo }
     * 
     */
    public CardInfo createCardInfo() {
        return new CardInfo();
    }

    /**
     * Create an instance of {@link LoadFileProfile }
     * 
     */
    public LoadFileProfile createLoadFileProfile() {
        return new LoadFileProfile();
    }

    /**
     * Create an instance of {@link LoadFileInfo }
     * 
     */
    public LoadFileInfo createLoadFileInfo() {
        return new LoadFileInfo();
    }

    /**
     * Create an instance of {@link Modules }
     * 
     */
    public Modules createModules() {
        return new Modules();
    }

    /**
     * Create an instance of {@link ApplicationCode }
     * 
     */
    public ApplicationCode createApplicationCode() {
        return new ApplicationCode();
    }

    /**
     * Create an instance of {@link CardProfile }
     * 
     */
    public CardProfile createCardProfile() {
        return new CardProfile();
    }

    /**
     * Create an instance of {@link LoadFileInstances }
     * 
     */
    public LoadFileInstances createLoadFileInstances() {
        return new LoadFileInstances();
    }

    /**
     * Create an instance of {@link ApplicationInstances }
     * 
     */
    public ApplicationInstances createApplicationInstances() {
        return new ApplicationInstances();
    }

    /**
     * Create an instance of {@link BackgroundColor }
     * 
     */
    public BackgroundColor createBackgroundColor() {
        return new BackgroundColor();
    }

    /**
     * Create an instance of {@link RoundCorner }
     * 
     */
    public RoundCorner createRoundCorner() {
        return new RoundCorner();
    }

    /**
     * Create an instance of {@link XPositionType }
     * 
     */
    public XPositionType createXPositionType() {
        return new XPositionType();
    }

    /**
     * Create an instance of {@link RGB }
     * 
     */
    public RGB createRGB() {
        return new RGB();
    }

    /**
     * Create an instance of {@link CornerStyle }
     * 
     */
    public CornerStyle createCornerStyle() {
        return new CornerStyle();
    }

    /**
     * Create an instance of {@link Font }
     * 
     */
    public Font createFont() {
        return new Font();
    }

    /**
     * Create an instance of {@link Width }
     * 
     */
    public Width createWidth() {
        return new Width();
    }

    /**
     * Create an instance of {@link Size }
     * 
     */
    public Size createSize() {
        return new Size();
    }

    /**
     * Create an instance of {@link CornerRadius }
     * 
     */
    public CornerRadius createCornerRadius() {
        return new CornerRadius();
    }

    /**
     * Create an instance of {@link Border }
     * 
     */
    public Border createBorder() {
        return new Border();
    }

    /**
     * Create an instance of {@link Height }
     * 
     */
    public Height createHeight() {
        return new Height();
    }

    /**
     * Create an instance of {@link YPositionType }
     * 
     */
    public YPositionType createYPositionType() {
        return new YPositionType();
    }

    /**
     * Create an instance of {@link ForegroundColor }
     * 
     */
    public ForegroundColor createForegroundColor() {
        return new ForegroundColor();
    }

    /**
     * Create an instance of {@link PersoData }
     * 
     */
    public PersoData createPersoData() {
        return new PersoData();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://namespaces.globalplatform.org/systems-profiles/1.1.2", name = "value")
    public JAXBElement<BigDecimal> createValue(BigDecimal value) {
        return new JAXBElement<BigDecimal>(_Value_QNAME, BigDecimal.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://namespaces.globalplatform.org/systems-profiles/1.1.2", name = "Side")
    public JAXBElement<String> createSide(String value) {
        return new JAXBElement<String>(_Side_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://namespaces.globalplatform.org/systems-profiles/1.1.2", name = "CheckDigit")
    public JAXBElement<String> createCheckDigit(String value) {
        return new JAXBElement<String>(_CheckDigit_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Short }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://namespaces.globalplatform.org/systems-profiles/1.1.2", name = "Blue")
    public JAXBElement<Short> createBlue(Short value) {
        return new JAXBElement<Short>(_Blue_QNAME, Short.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://namespaces.globalplatform.org/systems-profiles/1.1.2", name = "CardDesignOrientation")
    public JAXBElement<String> createCardDesignOrientation(String value) {
        return new JAXBElement<String>(_CardDesignOrientation_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://namespaces.globalplatform.org/systems-profiles/1.1.2", name = "Style")
    public JAXBElement<String> createStyle(String value) {
        return new JAXBElement<String>(_Style_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://namespaces.globalplatform.org/systems-profiles/1.1.2", name = "ConfigurationID")
    public JAXBElement<String> createConfigurationID(String value) {
        return new JAXBElement<String>(_ConfigurationID_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://namespaces.globalplatform.org/systems-profiles/1.1.2", name = "LineStyle")
    public JAXBElement<String> createLineStyle(String value) {
        return new JAXBElement<String>(_LineStyle_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Short }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://namespaces.globalplatform.org/systems-profiles/1.1.2", name = "Green")
    public JAXBElement<Short> createGreen(Short value) {
        return new JAXBElement<Short>(_Green_QNAME, Short.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://namespaces.globalplatform.org/systems-profiles/1.1.2", name = "AnchorPosition")
    public JAXBElement<String> createAnchorPosition(String value) {
        return new JAXBElement<String>(_AnchorPosition_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://namespaces.globalplatform.org/systems-profiles/1.1.2", name = "WriteLevelStandard")
    public JAXBElement<String> createWriteLevelStandard(String value) {
        return new JAXBElement<String>(_WriteLevelStandard_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Short }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://namespaces.globalplatform.org/systems-profiles/1.1.2", name = "DPI")
    public JAXBElement<Short> createDPI(Short value) {
        return new JAXBElement<Short>(_DPI_QNAME, Short.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Boolean }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://namespaces.globalplatform.org/systems-profiles/1.1.2", name = "HumanReadableText")
    public JAXBElement<Boolean> createHumanReadableText(Boolean value) {
        return new JAXBElement<Boolean>(_HumanReadableText_QNAME, Boolean.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://namespaces.globalplatform.org/systems-profiles/1.1.2", name = "PersonalizationOrderConstraint")
    public JAXBElement<String> createPersonalizationOrderConstraint(String value) {
        return new JAXBElement<String>(_PersonalizationOrderConstraint_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://namespaces.globalplatform.org/systems-profiles/1.1.2", name = "Underline")
    public JAXBElement<String> createUnderline(String value) {
        return new JAXBElement<String>(_Underline_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Boolean }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://namespaces.globalplatform.org/systems-profiles/1.1.2", name = "HumanReadableStartDigit")
    public JAXBElement<Boolean> createHumanReadableStartDigit(Boolean value) {
        return new JAXBElement<Boolean>(_HumanReadableStartDigit_QNAME, Boolean.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://namespaces.globalplatform.org/systems-profiles/1.1.2", name = "Unit")
    public JAXBElement<String> createUnit(String value) {
        return new JAXBElement<String>(_Unit_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://namespaces.globalplatform.org/systems-profiles/1.1.2", name = "CardDesignOrigin")
    public JAXBElement<String> createCardDesignOrigin(String value) {
        return new JAXBElement<String>(_CardDesignOrigin_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://namespaces.globalplatform.org/systems-profiles/1.1.2", name = "Material")
    public JAXBElement<String> createMaterial(String value) {
        return new JAXBElement<String>(_Material_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://namespaces.globalplatform.org/systems-profiles/1.1.2", name = "Type")
    public JAXBElement<String> createType(String value) {
        return new JAXBElement<String>(_Type_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Boolean }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://namespaces.globalplatform.org/systems-profiles/1.1.2", name = "HumanReadableCheckDigit")
    public JAXBElement<Boolean> createHumanReadableCheckDigit(Boolean value) {
        return new JAXBElement<Boolean>(_HumanReadableCheckDigit_QNAME, Boolean.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://namespaces.globalplatform.org/systems-profiles/1.1.2", name = "CardStockID")
    public JAXBElement<String> createCardStockID(String value) {
        return new JAXBElement<String>(_CardStockID_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://namespaces.globalplatform.org/systems-profiles/1.1.2", name = "TrackTypeID")
    public JAXBElement<String> createTrackTypeID(String value) {
        return new JAXBElement<String>(_TrackTypeID_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Byte }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://namespaces.globalplatform.org/systems-profiles/1.1.2", name = "ZPosition")
    public JAXBElement<Byte> createZPosition(Byte value) {
        return new JAXBElement<Byte>(_ZPosition_QNAME, Byte.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://namespaces.globalplatform.org/systems-profiles/1.1.2", name = "Name")
    public JAXBElement<String> createName(String value) {
        return new JAXBElement<String>(_Name_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://namespaces.globalplatform.org/systems-profiles/1.1.2", name = "MaterialID")
    public JAXBElement<String> createMaterialID(String value) {
        return new JAXBElement<String>(_MaterialID_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://namespaces.globalplatform.org/systems-profiles/1.1.2", name = "QuietZone")
    public JAXBElement<String> createQuietZone(String value) {
        return new JAXBElement<String>(_QuietZone_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://namespaces.globalplatform.org/systems-profiles/1.1.2", name = "Description")
    public JAXBElement<String> createDescription(String value) {
        return new JAXBElement<String>(_Description_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Short }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://namespaces.globalplatform.org/systems-profiles/1.1.2", name = "Red")
    public JAXBElement<Short> createRed(Short value) {
        return new JAXBElement<Short>(_Red_QNAME, Short.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://namespaces.globalplatform.org/systems-profiles/1.1.2", name = "Topping")
    public JAXBElement<String> createTopping(String value) {
        return new JAXBElement<String>(_Topping_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Object }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://namespaces.globalplatform.org/systems-profiles/1.1.2", name = "Script")
    public JAXBElement<Object> createScript(Object value) {
        return new JAXBElement<Object>(_Script_QNAME, Object.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Size }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://namespaces.globalplatform.org/systems-profiles/1.1.2", name = "Size", scope = Font.class)
    public JAXBElement<Size> createFontSize(Size value) {
        return new JAXBElement<Size>(_FontSize_QNAME, Size.class, Font.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ForegroundColor }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://namespaces.globalplatform.org/systems-profiles/1.1.2", name = "ForegroundColor", scope = Font.class)
    public JAXBElement<ForegroundColor> createFontForegroundColor(ForegroundColor value) {
        return new JAXBElement<ForegroundColor>(_FontForegroundColor_QNAME, ForegroundColor.class, Font.class, value);
    }

}
