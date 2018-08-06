package it.polito.dp2.NFFG.sol3.client2;

import it.polito.dp2.NFFG.NffgVerifierException;
import it.polito.dp2.NFFG.NodeReader;
import it.polito.dp2.NFFG.LinkReader;
public class LinkReaderImplementation extends NamedEntityReaderImplementation implements LinkReader {
	
	private String srcNode,dstNode;
	private Nffg nffg;
	private NffgReaderImplementation nffgReader;
	
	public LinkReaderImplementation(Nffg nffg,LinkTypeRef link){
	    	
	try{
		    	
		this.name=link.getName();
		this.srcNode=link.getSrcNode();
		this.dstNode=link.getDstNode();
		this.nffg=nffg;
		    	
	}catch(Exception exc){
		    	
	exc.printStackTrace();
				
	}
	 
	}
	@Override
	public NodeReader getDestinationNode() {
		
		
	try {
		this.nffgReader = new NffgReaderImplementation(nffg);
		return nffgReader.getNode(dstNode);
		
	} catch (NffgVerifierException e) {
		
		e.printStackTrace();
		return null;
	}
	
	}

	@Override
	public NodeReader getSourceNode() {
		
	try{	
	
		this.nffgReader = new NffgReaderImplementation(nffg);
		return nffgReader.getNode(srcNode);
	
	} catch (NffgVerifierException e) {
		
		e.printStackTrace();
		return null;
	}
	
		
	}
	

}
