
package it.polito.dp2.NFFG.sol3.client1;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="srcNode">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                 &lt;/sequence>
 *                 &lt;attribute name="name" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="dstNode">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                 &lt;/sequence>
 *                 &lt;attribute name="name" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="verificationResult" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="resultMessage" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="result" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                 &lt;/sequence>
 *                 &lt;attribute name="verificationTime" type="{http://www.w3.org/2001/XMLSchema}dateTime" />
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="traversedFuncType" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="name" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="policyLogic" use="required" type="{http://www.example.org/Neo4J}policyLogicType" />
 *       &lt;attribute name="nffg_ref" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="policyKind" use="required" type="{http://www.example.org/Neo4J}policyKindType" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "srcNode",
    "dstNode",
    "verificationResult",
    "traversedFuncType"
})
@XmlRootElement(name = "policy")
public class Policy {

    @XmlElement(required = true)
    protected Policy.SrcNode srcNode;
    @XmlElement(required = true)
    protected Policy.DstNode dstNode;
    protected Policy.VerificationResult verificationResult;
    @XmlElement(nillable = true)
    protected List<String> traversedFuncType;
    @XmlAttribute(name = "name", required = true)
    protected String name;
    @XmlAttribute(name = "policyLogic", required = true)
    protected PolicyLogicType policyLogic;
    @XmlAttribute(name = "nffg_ref", required = true)
    protected String nffgRef;
    @XmlAttribute(name = "policyKind", required = true)
    protected PolicyKindType policyKind;

    /**
     * Gets the value of the srcNode property.
     * 
     * @return
     *     possible object is
     *     {@link Policy.SrcNode }
     *     
     */
    public Policy.SrcNode getSrcNode() {
        return srcNode;
    }

    /**
     * Sets the value of the srcNode property.
     * 
     * @param value
     *     allowed object is
     *     {@link Policy.SrcNode }
     *     
     */
    public void setSrcNode(Policy.SrcNode value) {
        this.srcNode = value;
    }

    /**
     * Gets the value of the dstNode property.
     * 
     * @return
     *     possible object is
     *     {@link Policy.DstNode }
     *     
     */
    public Policy.DstNode getDstNode() {
        return dstNode;
    }

    /**
     * Sets the value of the dstNode property.
     * 
     * @param value
     *     allowed object is
     *     {@link Policy.DstNode }
     *     
     */
    public void setDstNode(Policy.DstNode value) {
        this.dstNode = value;
    }

    /**
     * Gets the value of the verificationResult property.
     * 
     * @return
     *     possible object is
     *     {@link Policy.VerificationResult }
     *     
     */
    public Policy.VerificationResult getVerificationResult() {
        return verificationResult;
    }

    /**
     * Sets the value of the verificationResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link Policy.VerificationResult }
     *     
     */
    public void setVerificationResult(Policy.VerificationResult value) {
        this.verificationResult = value;
    }

    /**
     * Gets the value of the traversedFuncType property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the traversedFuncType property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getTraversedFuncType().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getTraversedFuncType() {
        if (traversedFuncType == null) {
            traversedFuncType = new ArrayList<String>();
        }
        return this.traversedFuncType;
    }

    /**
     * Gets the value of the name property.
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
     * Sets the value of the name property.
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
     * Gets the value of the policyLogic property.
     * 
     * @return
     *     possible object is
     *     {@link PolicyLogicType }
     *     
     */
    public PolicyLogicType getPolicyLogic() {
        return policyLogic;
    }

    /**
     * Sets the value of the policyLogic property.
     * 
     * @param value
     *     allowed object is
     *     {@link PolicyLogicType }
     *     
     */
    public void setPolicyLogic(PolicyLogicType value) {
        this.policyLogic = value;
    }

    /**
     * Gets the value of the nffgRef property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNffgRef() {
        return nffgRef;
    }

    /**
     * Sets the value of the nffgRef property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNffgRef(String value) {
        this.nffgRef = value;
    }

    /**
     * Gets the value of the policyKind property.
     * 
     * @return
     *     possible object is
     *     {@link PolicyKindType }
     *     
     */
    public PolicyKindType getPolicyKind() {
        return policyKind;
    }

    /**
     * Sets the value of the policyKind property.
     * 
     * @param value
     *     allowed object is
     *     {@link PolicyKindType }
     *     
     */
    public void setPolicyKind(PolicyKindType value) {
        this.policyKind = value;
    }


    /**
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;sequence>
     *       &lt;/sequence>
     *       &lt;attribute name="name" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "")
    public static class DstNode {

        @XmlAttribute(name = "name", required = true)
        protected String name;

        /**
         * Gets the value of the name property.
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
         * Sets the value of the name property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setName(String value) {
            this.name = value;
        }

    }


    /**
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;sequence>
     *       &lt;/sequence>
     *       &lt;attribute name="name" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "")
    public static class SrcNode {

        @XmlAttribute(name = "name", required = true)
        protected String name;

        /**
         * Gets the value of the name property.
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
         * Sets the value of the name property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setName(String value) {
            this.name = value;
        }

    }


    /**
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;sequence>
     *         &lt;element name="resultMessage" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="result" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *       &lt;/sequence>
     *       &lt;attribute name="verificationTime" type="{http://www.w3.org/2001/XMLSchema}dateTime" />
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "resultMessage",
        "result"
    })
    public static class VerificationResult {

        @XmlElement(required = true)
        protected String resultMessage;
        @XmlElement(required = true)
        protected String result;
        @XmlAttribute(name = "verificationTime")
        @XmlSchemaType(name = "dateTime")
        protected XMLGregorianCalendar verificationTime;

        /**
         * Gets the value of the resultMessage property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getResultMessage() {
            return resultMessage;
        }

        /**
         * Sets the value of the resultMessage property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setResultMessage(String value) {
            this.resultMessage = value;
        }

        /**
         * Gets the value of the result property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getResult() {
            return result;
        }

        /**
         * Sets the value of the result property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setResult(String value) {
            this.result = value;
        }

        /**
         * Gets the value of the verificationTime property.
         * 
         * @return
         *     possible object is
         *     {@link XMLGregorianCalendar }
         *     
         */
        public XMLGregorianCalendar getVerificationTime() {
            return verificationTime;
        }

        /**
         * Sets the value of the verificationTime property.
         * 
         * @param value
         *     allowed object is
         *     {@link XMLGregorianCalendar }
         *     
         */
        public void setVerificationTime(XMLGregorianCalendar value) {
            this.verificationTime = value;
        }

    }

}
