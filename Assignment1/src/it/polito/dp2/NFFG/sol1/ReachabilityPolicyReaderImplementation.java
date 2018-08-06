package it.polito.dp2.NFFG.sol1;

import it.polito.dp2.NFFG.NodeReader;
import it.polito.dp2.NFFG.ReachabilityPolicyReader;
import it.polito.dp2.NFFG.sol1.jaxb.Policy;
import it.polito.dp2.NFFG.sol1.jaxb.NffgType;
import it.polito.dp2.NFFG.NffgVerifierException;


public class ReachabilityPolicyReaderImplementation  extends PolicyReaderImplementation implements ReachabilityPolicyReader{
	
	private String srcNode;
	private String dstNode;
		
	public ReachabilityPolicyReaderImplementation(Policy policy,NffgType nffg)throws NffgVerifierException {
	  		
		super(policy,nffg);
			
	try {		
	
		this.srcNode=policy.getSrcNode().getName();	
		this.dstNode=policy.getDstNode().getName();				
		
		} catch (Exception e) {
		e.printStackTrace();
		throw new NffgVerifierException();
		}	
	}	

		@Override
	public NodeReader getDestinationNode() {
			
		return this.getNffg().getNode(srcNode);
	}

	@Override
	public NodeReader getSourceNode() {

	return this.getNffg().getNode(dstNode);
			
	}
}
