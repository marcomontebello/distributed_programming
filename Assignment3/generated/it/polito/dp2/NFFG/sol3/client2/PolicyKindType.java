
package it.polito.dp2.NFFG.sol3.client2;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for policyKindType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="policyKindType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="REACHABILITY"/>
 *     &lt;enumeration value="TRAVERSAL"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "policyKindType")
@XmlEnum
public enum PolicyKindType {

    REACHABILITY,
    TRAVERSAL;

    public String value() {
        return name();
    }

    public static PolicyKindType fromValue(String v) {
        return valueOf(v);
    }

}
