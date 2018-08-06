package it.polito.dp2.NFFG.sol3.client2;

import java.util.Calendar;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.xml.datatype.XMLGregorianCalendar;

import it.polito.dp2.NFFG.NodeReader;
import it.polito.dp2.NFFG.NffgReader;
import it.polito.dp2.NFFG.NffgVerifierException;

public class NffgReaderImplementation extends NamedEntityReaderImplementation implements NffgReader {
	
	private List<NodeType> nodes;
    private Set <NodeReaderImplementation> nodeSet;
    private XMLGregorianCalendar updateTime;

    public NffgReaderImplementation(Nffg nffg) throws NffgVerifierException{
    
    	try{
		
    	this.name=nffg.getName();
    	this.updateTime=nffg.getUpdateTime();
    	this.nodes=nffg.getNode(); 	
    	this.nodeSet=new HashSet<NodeReaderImplementation>();
	    	
	    }catch(Exception exc){
			
    	exc.printStackTrace();
    	throw new NffgVerifierException();
    	
	    }
    	
    	for(NodeType actNode : nodes)
    		nodeSet.add(new NodeReaderImplementation(nffg,actNode));		
   	}


	@Override
	public NodeReader getNode(String arg0) {
		
		Iterator<NodeReaderImplementation> i = nodeSet.iterator();
		while (i.hasNext()) {
			
			NodeReaderImplementation actual_node = i.next();
			
			if (actual_node.getName().equals(arg0))
				return actual_node;
		}
		return null;	
	}

	@Override
	public Set<NodeReader> getNodes() {
		
		if (nodeSet != null)		
			return (HashSet<NodeReader>) new HashSet<NodeReader>(nodeSet);

		return  new HashSet<NodeReader>();
	}

	@Override
	public Calendar getUpdateTime() {
		
		Calendar calendar = this.updateTime.toGregorianCalendar();
		return calendar;	
	}

}
