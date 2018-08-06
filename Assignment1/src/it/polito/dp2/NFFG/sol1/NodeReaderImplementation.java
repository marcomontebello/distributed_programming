package it.polito.dp2.NFFG.sol1;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import it.polito.dp2.NFFG.FunctionalType;
import it.polito.dp2.NFFG.LinkReader;
import it.polito.dp2.NFFG.NodeReader;
import it.polito.dp2.NFFG.sol1.jaxb.FunctionType;
import it.polito.dp2.NFFG.sol1.jaxb.LinkType;
import it.polito.dp2.NFFG.sol1.jaxb.NffgType;
import it.polito.dp2.NFFG.sol1.jaxb.NodeType;
import it.polito.dp2.NFFG.NffgVerifierException;


public class NodeReaderImplementation extends NamedEntityReaderImplementation implements NodeReader {
	
    private List<LinkType> links;
    private FunctionType function;
    private NffgType nffg;
    
    public NodeReaderImplementation(NffgType nffg,NodeType node) throws NffgVerifierException{
    	
    	try{
    		
    	this.name=node.getName();
    	this.function=node.getFunction();
    	this.links=node.getLinkRef();
    	this.nffg=nffg;
	    	
    	}catch(Exception exc){
    		
    	exc.printStackTrace(); 
		throw new NffgVerifierException();

    	}    			
    }
   
	@Override
	public FunctionalType getFuncType() {

		return FunctionalType.valueOf(function.value());
	}

	@Override
	public Set<LinkReader> getLinks() {
		
		Set<LinkReaderImplementation> linkSet=new HashSet<LinkReaderImplementation>();
		
		for(LinkType actLink : links)	
			linkSet.add(new LinkReaderImplementation(nffg,actLink));
				
		return new HashSet<LinkReader>(linkSet);			
	}
}