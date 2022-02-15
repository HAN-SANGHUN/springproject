
package org.globalplatform.namespaces.systems_profiles._1_1;

import java.math.BigInteger;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
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
 *       &lt;attribute name="Name" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="Label" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="External" use="required" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *       &lt;attribute name="Type" use="required" type="{http://namespaces.globalplatform.org/systems-profiles/1.1.2}dataType" />
 *       &lt;attribute name="Encoding" type="{http://namespaces.globalplatform.org/systems-profiles/1.1.2}encoding" />
 *       &lt;attribute name="FixedLength" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *       &lt;attribute name="Length" type="{http://www.w3.org/2001/XMLSchema}integer" />
 *       &lt;attribute name="Value" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="Tag" type="{http://www.w3.org/2001/XMLSchema}hexBinary" />
 *       &lt;attribute name="TagEncoding" type="{http://namespaces.globalplatform.org/systems-profiles/1.1.2}tagEncoding" />
 *       &lt;attribute name="ReadWrite" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *       &lt;attribute name="Update" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *       &lt;attribute name="Optional" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *       &lt;attribute name="MandatoryAudit" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "")
@XmlRootElement(name = "DataElement")
public class DataElement {

    @XmlAttribute(name = "Name", required = true)
    protected String name;
    @XmlAttribute(name = "Label")
    protected String label;
    @XmlAttribute(name = "External", required = true)
    protected boolean external;
    @XmlAttribute(name = "Type", required = true)
    protected DataType type;
    @XmlAttribute(name = "Encoding")
    protected Encoding encoding;
    @XmlAttribute(name = "FixedLength")
    protected Boolean fixedLength;
    @XmlAttribute(name = "Length")
    protected BigInteger length;
    @XmlAttribute(name = "Value")
    protected String value;
    @XmlAttribute(name = "Tag")
    @XmlJavaTypeAdapter(HexBinaryAdapter.class)
    @XmlSchemaType(name = "hexBinary")
    protected byte[] tag;
    @XmlAttribute(name = "TagEncoding")
    protected TagEncoding tagEncoding;
    @XmlAttribute(name = "ReadWrite")
    protected Boolean readWrite;
    @XmlAttribute(name = "Update")
    protected Boolean update;
    @XmlAttribute(name = "Optional")
    protected Boolean optional;
    @XmlAttribute(name = "MandatoryAudit")
    protected Boolean mandatoryAudit;

    /**
     * name �Ӽ��� ���� �����ɴϴ�.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getName() {
        return name;
    }

    /**
     * name �Ӽ��� ���� �����մϴ�.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setName(String value) {
        this.name = value;
    }

    /**
     * label �Ӽ��� ���� �����ɴϴ�.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLabel() {
        return label;
    }

    /**
     * label �Ӽ��� ���� �����մϴ�.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLabel(String value) {
        this.label = value;
    }

    /**
     * external �Ӽ��� ���� �����ɴϴ�.
     * 
     */
    public boolean isExternal() {
        return external;
    }

    /**
     * external �Ӽ��� ���� �����մϴ�.
     * 
     */
    public void setExternal(boolean value) {
        this.external = value;
    }

    /**
     * type �Ӽ��� ���� �����ɴϴ�.
     * 
     * @return
     *     possible object is
     *     {@link DataType }
     *     
     */
    public DataType getType() {
        return type;
    }

    /**
     * type �Ӽ��� ���� �����մϴ�.
     * 
     * @param value
     *     allowed object is
     *     {@link DataType }
     *     
     */
    public void setType(DataType value) {
        this.type = value;
    }

    /**
     * encoding �Ӽ��� ���� �����ɴϴ�.
     * 
     * @return
     *     possible object is
     *     {@link Encoding }
     *     
     */
    public Encoding getEncoding() {
        return encoding;
    }

    /**
     * encoding �Ӽ��� ���� �����մϴ�.
     * 
     * @param value
     *     allowed object is
     *     {@link Encoding }
     *     
     */
    public void setEncoding(Encoding value) {
        this.encoding = value;
    }

    /**
     * fixedLength �Ӽ��� ���� �����ɴϴ�.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isFixedLength() {
        return fixedLength;
    }

    /**
     * fixedLength �Ӽ��� ���� �����մϴ�.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setFixedLength(Boolean value) {
        this.fixedLength = value;
    }

    /**
     * length �Ӽ��� ���� �����ɴϴ�.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getLength() {
        return length;
    }

    /**
     * length �Ӽ��� ���� �����մϴ�.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setLength(BigInteger value) {
        this.length = value;
    }

    /**
     * value �Ӽ��� ���� �����ɴϴ�.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getValue() {
        return value;
    }

    /**
     * value �Ӽ��� ���� �����մϴ�.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setValue(String value) {
        this.value = value;
    }

    /**
     * tag �Ӽ��� ���� �����ɴϴ�.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public byte[] getTag() {
        return tag;
    }

    /**
     * tag �Ӽ��� ���� �����մϴ�.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTag(byte[] value) {
        this.tag = value;
    }

    /**
     * tagEncoding �Ӽ��� ���� �����ɴϴ�.
     * 
     * @return
     *     possible object is
     *     {@link TagEncoding }
     *     
     */
    public TagEncoding getTagEncoding() {
        return tagEncoding;
    }

    /**
     * tagEncoding �Ӽ��� ���� �����մϴ�.
     * 
     * @param value
     *     allowed object is
     *     {@link TagEncoding }
     *     
     */
    public void setTagEncoding(TagEncoding value) {
        this.tagEncoding = value;
    }

    /**
     * readWrite �Ӽ��� ���� �����ɴϴ�.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isReadWrite() {
        return readWrite;
    }

    /**
     * readWrite �Ӽ��� ���� �����մϴ�.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setReadWrite(Boolean value) {
        this.readWrite = value;
    }

    /**
     * update �Ӽ��� ���� �����ɴϴ�.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isUpdate() {
        return update;
    }

    /**
     * update �Ӽ��� ���� �����մϴ�.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setUpdate(Boolean value) {
        this.update = value;
    }

    /**
     * optional �Ӽ��� ���� �����ɴϴ�.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isOptional() {
        return optional;
    }

    /**
     * optional �Ӽ��� ���� �����մϴ�.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setOptional(Boolean value) {
        this.optional = value;
    }

    /**
     * mandatoryAudit �Ӽ��� ���� �����ɴϴ�.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isMandatoryAudit() {
        return mandatoryAudit;
    }

    /**
     * mandatoryAudit �Ӽ��� ���� �����մϴ�.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setMandatoryAudit(Boolean value) {
        this.mandatoryAudit = value;
    }

}
