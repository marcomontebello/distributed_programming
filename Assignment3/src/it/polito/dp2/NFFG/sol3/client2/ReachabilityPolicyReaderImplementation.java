package it.polito.dp2.NFFG.sol3.client2;

import it.polito.dp2.NFFG.NffgVerifierException;
import it.polito.dp2.NFFG.NodeReader;
import it.polito.dp2.NFFG.ReachabilityPolicyReader;


public class ReachabilityPolicyReaderImplementation extends PolicyReaderImplementation implements ReachabilityPolicyReader{
	
	private String srcNode;
	private String dstNode;
		
	public ReachabilityPolicyReaderImplementation(Policy policy, Nffg nffg) throws NffgVerifierException {
	  		
		super(policy, nffg);
			
		try {
				
		this.srcNode=policy.getSrcNode().getName();	
		this.dstNode=policy.getDstNode().getName();
				
		} catch (Exception e) {

		e.printStackTrace();
			
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
