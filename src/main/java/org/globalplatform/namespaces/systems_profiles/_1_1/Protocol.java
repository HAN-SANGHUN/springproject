
package org.globalplatform.namespaces.systems_profiles._1_1;

import java.math.BigInteger;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>anonymous complex type에 대한 Java 클래스입니다.
 * 
 * <p>다음 스키마 단편이 이 클래스에 포함되는 필요한 콘텐츠를 지정합니다.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;attribute name="Type" use="required" type="{http://namespaces.globalplatform.org/systems-profiles/1.1.2}secureElementProtocol" />
 *       &lt;attribute name="MaxApduLen" use="required" type="{http://www.w3.org/2001/XMLSchema}integer" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "")
@XmlRootElement(name = "Protocol")
public class Protocol {

    @XmlAttribute(name = "Type", required = true)
    protected SecureElementProtocol type;
    @XmlAttribute(name = "MaxApduLen", required = true)
    protected BigInteger maxApduLen;

    /**
     * type 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link SecureElementProtocol }
     *     
     */
    public SecureElementProtocol getType() {
        return type;
    }

    /**
     * type 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link SecureElementProtocol }
     *     
     */
    public void setType(SecureElementProtocol value) {
        this.type = value;
    }

    /**
     * maxApduLen 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getMaxApduLen() {
        return maxApduLen;
    }

    /**
     * maxApduLen 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setMaxApduLen(BigInteger value) {
        this.maxApduLen = value;
    }

}
