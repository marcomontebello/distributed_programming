
package it.polito.dp2.NFFG.sol3.client2;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for nodeType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="nodeType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="link_ref" type="{http://www.example.org/Neo4J}linkTypeRef" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="name" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="function" use="required" type="{http://www.example.org/Neo4J}functionType" />
 *       &lt;attribute name="numberOfLinks" use="required" type="{http://www.w3.org/2001/XMLSchema}nonNegativeInteger" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "nodeType", propOrder = {
    "linkRef"
})
public class NodeType {

    @XmlElement(name = "link_ref")
    protected List<LinkTypeRef> linkRef;
    @XmlAttribute(name = "name", required = true)
    protected String name;
    @XmlAttribute(name = "function", required = true)
    protected FunctionType function;
    @XmlAttribute(name = "numberOfLinks", required = true)
    @XmlSchemaType(name = "nonNegativeInteger")
    protected BigInteger numberOfLinks;

    /**
     * Gets the value of the linkRef property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the linkRef property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getLinkRef().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link LinkTypeRef }
     * 
     * 
     */
    public List<LinkTypeRef> getLinkRef() {
        if (linkRef == null) {
            linkRef = new ArrayList<LinkTypeRef>();
        }
        return this.linkRef;
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
     * Gets the value of the function property.
     * 
     * @return
     *     possible object is
     *     {@link FunctionType }
     *     
     */
    public FunctionType getFunction() {
        return function;
    }

    /**
     * Sets the value of the function property.
     * 
     * @param value
     *     allowed object is
     *     {@link FunctionType }
     *     
     */
    public void setFunction(FunctionType value) {
        this.function = value;
    }

    /**
     * Gets the value of the numberOfLinks property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getNumberOfLinks() {
        return numberOfLinks;
    }

    /**
     * Sets the value of the numberOfLinks property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setNumberOfLinks(BigInteger value) {
        this.numberOfLinks = value;
    }

}
