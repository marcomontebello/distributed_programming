package it.polito.dp2.NFFG.sol3.service;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.ws.rs.WebApplicationException;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import it.polito.dp2.NFFG.sol3.service.Labels;
import it.polito.dp2.NFFG.sol3.service.Node;
import it.polito.dp2.NFFG.sol3.service.Property; 

class NffgsService {
	
	//NffgService performs like a client for the NEO4JXML WS. It is similar as a proxy.
	private Nffgs cache=NffgsCache.getCache();
	private Map<String ,Map<String,String>> mappingDB=NffgsCache.getMappingDB();
	private Neo4JClient neo4j=new Neo4JClient();
	
	//the constructor deals with a counter that garantues to initialize the datas at every deployment of the service
	public NffgsService() throws NffgServiceException {
		
		if(NffgServiceFilter.getRequestNumber()==0){	
		try {
			neo4j.performDeleteAllNodes();			
			} catch (WebApplicationException e) {	
			throw new NffgServiceException();
			}
		}		
	}
		
	public Nffg loadNFFG(Nffg nffg) throws  NffgServiceException, NotWellFormedException, ConstraintViolatedException{
		
		Integer id_nffg;
		Integer id_node;			
		String nffgToLoad=nffg.getName();		
		if(!isWellFormed(nffg))
			throw new NotWellFormedException();	
		
		if	(	NffgsCache.contains(nffg.getName()) 
			|| 	!nffgAdmitted(nffg)
			)throw new ConstraintViolatedException();			
		//CREATING NODE 'NFFG' ON NEO4JXML
		Node nffgNode=new Node();
		Property nffgProperty=new Property();
		nffgProperty.setName("name");
		nffgProperty.setValue(nffg.getName());
		nffgNode.getProperty().add(nffgProperty);
		id_nffg=neo4j.performPostCreateNode(nffgNode);
		//CREATING LABEL OF THE NODE JUST LOADED
		Labels nffgLabel=new Labels();
		nffgLabel.getValue().add("NFFG");
		nffgNode.setLabels(nffgLabel);
		neo4j.performPostCreateLabels(id_nffg,nffgLabel);
		//CREATING NODES AND LABELS FOR EACH NODE OF THE NFFG ON NEO4JXML
		List<NodeType> nodeSet = nffg.getNode();
		HashMap<String,String> NffgNodeNames= new HashMap<String,String>();
			
		for (NodeType nr: nodeSet) {				
				
			Node actualNode=new Node();
			Property nodeProperty=new Property();
				
			nodeProperty.setName("name");
			nodeProperty.setValue(nr.getName());
			actualNode.getProperty().add(nodeProperty);	
		
			id_node=neo4j.performPostCreateNode(actualNode);	
				
			NffgNodeNames.put(nr.getName(), id_node.toString());
							
			Labels actNodeLabels=new Labels();
			actNodeLabels.getValue().add(nr.getFunction().value());
			actualNode.setLabels(actNodeLabels);
				
			neo4j.performPostCreateLabels(id_node,actNodeLabels);			
		}				
		//MANTAINING MAPPING BETWEEN names and the ids->erased in NffgsResource if occurs error before completing adding nffg
		NffgsCache.addID(nffgToLoad, NffgNodeNames);		
		
		//CREATING LINKS BETWEWN NODES AND BETEWEEN NFFG ROOT AND NODES
		
		for (NodeType nr: nodeSet) { 
			
			if(!nr.getName().equals(nffgToLoad)){
									
				ArrayList<String> linkRelationshipIdList=new ArrayList<String>();			
				String idSrcNode=NffgsCache.getID(nffgToLoad, nr.getName());				
				neo4j.performPostCreateRelationship(idSrcNode,id_nffg.toString(),"belongs");
					
				List<LinkTypeRef> linkSet = nr.getLinkRef();
				
				for (LinkTypeRef lr: linkSet){	
									
					String idDstNode=NffgsCache.getID(nffgToLoad, lr.getDstNode());
					String linkIdRelationship=neo4j.performPostCreateRelationship(idSrcNode,idDstNode,"Link");
					linkRelationshipIdList.add(linkIdRelationship);
				}
			}
		}
				
		Nffg toStoreInCache=new Nffg();	
		
	try {		
		toStoreInCache.setName(nffgToLoad);
		toStoreInCache.setUpdateTime(getXMLGregorianCalendarNow());
		toStoreInCache.setNumberOfNodes(BigInteger.valueOf(nodeSet.size()));
		toStoreInCache.getNode().addAll(nodeSet);
				
		} catch (DatatypeConfigurationException e) {
				
		throw new NffgServiceException();
		}
			
		NffgsCache.addToNffgsCahce(toStoreInCache);
		return toStoreInCache;			
	}
	
	public Nffgs getNffgs() {	
			
		return this.cache;
	}
	
	public Nffg getNffg(String id) {
		
		return NffgsCache.getNFFG(id);
		
	}

	public boolean nffgAdmitted(Nffg nffg){
		
		ArrayList<String> nodeNames=new ArrayList<String>();
		ArrayList<String> linkNames=new ArrayList<String>();
		Pattern p = Pattern.compile("[A-Za-z][A-Za-z0-9]+$");
		Matcher m = p.matcher(nffg.getName());

		if (!m.matches())
			return false;

		for(NodeType node: nffg.getNode()){		
			if(nodeNames.contains(node.getName()))
				return false;
			
			m = p.matcher(node.getName());
			if (m.matches())
				nodeNames.add(node.getName());		
			else return false;		
		}
		
		for(NodeType node: nffg.getNode()){
			
			List<LinkTypeRef> nodeLInks=node.getLinkRef();
			
			for(LinkTypeRef link:nodeLInks){
				
				if	(	linkNames.contains(link.getName())	
					|| !link.getSrcNode().equals(node.getName())
					|| !nodeNames.contains(link.getSrcNode()) 
					|| !nodeNames.contains(link.getDstNode())	
					)	return false;			
				m = p.matcher(link.getName());
				
				if (m.matches())
					linkNames.add(link.getName());
				
				else return false;		
			}
		}	
		return true;
	}

	
	public boolean isWellFormed(Nffg nffg){
			
		if 		(	nffg.getName()==null 
				||	nffg.getUpdateTime()==null
				||	nffg.getNode().size()==0
				)	return false;
		
		if(nffg.getNode().size()>0)	{	
			for(NodeType node: nffg.getNode()){		
				if	(	node.getName()==null 
					|| 	node.getFunction()==null 
					)	return false;	
				
				List<LinkTypeRef> nodeLInks=node.getLinkRef();		
				for(LinkTypeRef link:nodeLInks){			
					if	(	link.getName()==null	
						|| 	link.getSrcNode()==null	
						||	link.getDstNode()==null	
						) 	return false;						
				}
			}	
		}
		return true;
	}

	
	public Object getSynchNameMapping(){	
		return this.mappingDB;		
	}
	
	public Object getSynchNffgCache(){	
		return this.cache;		
	}
	
	XMLGregorianCalendar getXMLGregorianCalendarNow() throws DatatypeConfigurationException{
		
	    GregorianCalendar gregorianCalendar = new GregorianCalendar();
	    DatatypeFactory datatypeFactory = DatatypeFactory.newInstance();
	    XMLGregorianCalendar now = 
	        datatypeFactory.newXMLGregorianCalendar(gregorianCalendar);
	    return now;
	}
}
