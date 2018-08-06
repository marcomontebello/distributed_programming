
package it.polito.dp2.NFFG.sol3.client1;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for policyLogicType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="policyLogicType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="POSITIVE"/>
 *     &lt;enumeration value="NEGATIVE"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "policyLogicType")
@XmlEnum
public enum PolicyLogicType {

    POSITIVE,
    NEGATIVE;

    public String value() {
        return name();
    }

    public static PolicyLogicType fromValue(String v) {
        return valueOf(v);
    }

}
