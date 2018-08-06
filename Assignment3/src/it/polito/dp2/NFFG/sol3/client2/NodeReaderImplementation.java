package it.polito.dp2.NFFG.sol3.client2;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import it.polito.dp2.NFFG.FunctionalType;
import it.polito.dp2.NFFG.LinkReader;
import it.polito.dp2.NFFG.NffgVerifierException;

public class NodeReaderImplementation extends NamedEntityReaderImplementation implements it.polito.dp2.NFFG.NodeReader{

	 private List<LinkTypeRef> links;
	 private FunctionType function;
	 private Set <LinkReaderImplementation> linkSet;

	 public NodeReaderImplementation(Nffg nffg,NodeType node) throws NffgVerifierException{
	    	
	try{
	    this.name=node.getName();
    	this.function=node.getFunction();
    	this.links=node.getLinkRef();
		    	
    	}catch(Exception exc){
	    		
		exc.printStackTrace();
		throw new NffgVerifierException();
    	}
	    	
		linkSet=new HashSet<LinkReaderImplementation>();

    	for(LinkTypeRef actLink : links)
    		linkSet.add(new LinkReaderImplementation(nffg,actLink));	  		
	    }

	@Override
	public FunctionalType getFuncType() {
		return FunctionalType.valueOf(function.value());
	}

	@Override
	public Set<LinkReader> getLinks() {
		if (linkSet != null)
				return (HashSet<LinkReader>) new HashSet<LinkReader>(linkSet);	
		return  new HashSet<LinkReader>();
	}

}
