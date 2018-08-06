
package it.polito.dp2.NFFG.sol3.client2;

import javax.xml.bind.annotation.XmlRegistry;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the it.polito.dp2.NFFG.sol3.client2 package. 
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


    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: it.polito.dp2.NFFG.sol3.client2
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link Policy }
     * 
     */
    public Policy createPolicy() {
        return new Policy();
    }

    /**
     * Create an instance of {@link Nffgs }
     * 
     */
    public Nffgs createNffgs() {
        return new Nffgs();
    }

    /**
     * Create an instance of {@link Nffg }
     * 
     */
    public Nffg createNffg() {
        return new Nffg();
    }

    /**
     * Create an instance of {@link NodeType }
     * 
     */
    public NodeType createNodeType() {
        return new NodeType();
    }

    /**
     * Create an instance of {@link Policies }
     * 
     */
    public Policies createPolicies() {
        return new Policies();
    }

    /**
     * Create an instance of {@link Policy.SrcNode }
     * 
     */
    public Policy.SrcNode createPolicySrcNode() {
        return new Policy.SrcNode();
    }

    /**
     * Create an instance of {@link Policy.DstNode }
     * 
     */
    public Policy.DstNode createPolicyDstNode() {
        return new Policy.DstNode();
    }

    /**
     * Create an instance of {@link Policy.VerificationResult }
     * 
     */
    public Policy.VerificationResult createPolicyVerificationResult() {
        return new Policy.VerificationResult();
    }

    /**
     * Create an instance of {@link LinkTypeRef }
     * 
     */
    public LinkTypeRef createLinkTypeRef() {
        return new LinkTypeRef();
    }

}
