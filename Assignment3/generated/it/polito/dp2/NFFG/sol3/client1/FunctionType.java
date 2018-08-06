
package it.polito.dp2.NFFG.sol3.client1;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for functionType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="functionType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="FW"/>
 *     &lt;enumeration value="DPI"/>
 *     &lt;enumeration value="NAT"/>
 *     &lt;enumeration value="SPAM"/>
 *     &lt;enumeration value="CACHE"/>
 *     &lt;enumeration value="VPN"/>
 *     &lt;enumeration value="WEB_SERVER"/>
 *     &lt;enumeration value="WEB_CLIENT"/>
 *     &lt;enumeration value="MAIL_SERVER"/>
 *     &lt;enumeration value="MAIL_CLIENT"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "functionType")
@XmlEnum
public enum FunctionType {

    FW,
    DPI,
    NAT,
    SPAM,
    CACHE,
    VPN,
    WEB_SERVER,
    WEB_CLIENT,
    MAIL_SERVER,
    MAIL_CLIENT;

    public String value() {
        return name();
    }

    public static FunctionType fromValue(String v) {
        return valueOf(v);
    }

}
