package it.polito.dp2.NFFG.sol2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.ws.rs.ProcessingException;
import javax.ws.rs.WebApplicationException;

import it.polito.dp2.NFFG.LinkReader;
import it.polito.dp2.NFFG.NffgReader;
import it.polito.dp2.NFFG.NffgVerifier;
import it.polito.dp2.NFFG.NffgVerifierException;
import it.polito.dp2.NFFG.NffgVerifierFactory;
import it.polito.dp2.NFFG.NodeReader;
import it.polito.dp2.NFFG.lab2.NoGraphException;
import it.polito.dp2.NFFG.lab2.ReachabilityTester;
import it.polito.dp2.NFFG.lab2.ServiceException;
import it.polito.dp2.NFFG.lab2.UnknownNameException;

public class ReachabilityTesterLib implements ReachabilityTester {
	
	private NffgVerifier monitor;
	private Interactions interactions;
	private String lastNffgLoaded;
	private ObjectFactory factoryGenerated =new ObjectFactory();

	public ReachabilityTesterLib(){
		
		try {
			
		this.interactions=new Interactions();
			
		} catch (Exception e) {	
		e.printStackTrace();
		}	
	}

	@Override
	public void loadNFFG(String name) throws UnknownNameException, ServiceException {
		
		Integer id_node=null;
	    
	    try {
	    	
	    NffgVerifierFactory factory = NffgVerifierFactory.newInstance();
		this.monitor = factory.newNffgVerifier();		
			
		} catch (NffgVerifierException e) {
		e.printStackTrace();		
		}
			
		NffgReader nffgToLoad=this.monitor.getNffg(name);
		
		if	(nffgToLoad==null) 		
			throw new UnknownNameException("---Nffg requested does not exist---\n");		
			
		//System.out.println("---resetting NEO4J Db before perform a new post---\n");	
		try {
			
			interactions.performDeleteAllNodes();
			
		} catch (ProcessingException e1) {
			
		e1.printStackTrace();
		throw new ServiceException("processing exception occurred");
			
		}catch (WebApplicationException  e2) {
			
		e2.printStackTrace();
		throw new ServiceException("problem with the server occurred:impossible loading nffg");
		
		}catch (Exception e3) {
			
		e3.printStackTrace();
		throw new ServiceException("general exception occured:impossible loading nffg");

		}
		
		// READING NODES
		Set<NodeReader> nodeSet = nffgToLoad.getNodes();
		//MAP TO MANTAIN CORRENSPONDANCE BETWEEN NODES CREATED AND THEIR NEO4J IDS TO CREATE RELATIONSHIP
	    Map<String,String> mapNameId= new HashMap<String,String>();

	    for (NodeReader nr: nodeSet) {
			
			Node actualNode=factoryGenerated.createNode();
			Property nodeProperty=factoryGenerated.createProperty();
			nodeProperty.setName("name");
			nodeProperty.setValue(nr.getName());
			actualNode.getProperty().add(nodeProperty);
				
		try{
					
			id_node=interactions.performPostCreateNode(actualNode);
			mapNameId.put(nr.getName(), id_node.toString());
					
			Labels actNodeLabels=factoryGenerated.createLabels();
			actNodeLabels.getValue().add(nr.getFuncType().toString());
			interactions.performPostCreateLabels(id_node,actNodeLabels);
					
			} catch (ProcessingException e1) {
					
			e1.printStackTrace();
			throw new ServiceException("processing exception occurred");
					
			}catch (WebApplicationException  e2) {
					
			e2.printStackTrace();
			throw new ServiceException("problem with the server occurred:impossible loading nffg");
				
			}catch (Exception e3) {
					
			e3.printStackTrace();
			throw new ServiceException("general exception occured:impossible loading nffg");
			
			}	
				
		}	
			
		for (NodeReader nr: nodeSet) { 
				
			String idSrcNode=mapNameId.get(nr.getName());
			Set<LinkReader> linkSet = nr.getLinks();
					
			for (LinkReader lr: linkSet){	
						
			try {
			    		
				String idDstNode=mapNameId.get(lr.getDestinationNode().getName());		
				interactions.performPostCreateRelationship(idSrcNode,idDstNode);
						
				} catch (ProcessingException e1) {
						
				e1.printStackTrace();
				throw new ServiceException("processing exception occurred");
						
				}catch (WebApplicationException  e2) {
						
				e2.printStackTrace();
				throw new ServiceException("problem with the server occurred:impossible loading nffg");
					
				}catch (Exception e3) {
						
				e3.printStackTrace();
				throw new ServiceException("general exception occured:impossible loading nffg");
				}	
			}
		 }			

		this.lastNffgLoaded=name;			
	}
				
	@Override
	public boolean testReachability(String srcName, String destName) throws UnknownNameException, ServiceException, NoGraphException {
		
		boolean result = false;
		String idSrc,idDst;
		
		Paths pathsReturned=factoryGenerated.createPaths();
	    List<Node> nodeList;
		
    try {
			
		nodeList = interactions.performGetAllNodes();
			
	    }catch (ProcessingException e1) {
			
		e1.printStackTrace();
		throw new ServiceException("processing exception occurred");
			
		}catch (WebApplicationException  e2) {
			
		e2.printStackTrace();
		throw new ServiceException("problem with the server occurred:impossible loading nffg");
		
		}catch (Exception e3) {
			
		e3.printStackTrace();
		throw new ServiceException("general exception occured:impossible loading nffg");
		}
		
		List<Path> pathList=new ArrayList<Path>();
		Map<String,String> mapNameId=getNameIDCorrelation(nodeList);
	    
			//nodeCollection.getNode returns  a List of Node
		if(this.lastNffgLoaded==null)	
			throw new NoGraphException();	
	    
		if((!mapNameId.containsKey(srcName)) || (!mapNameId.containsKey(destName)))
			throw new UnknownNameException();
		
		idSrc=mapNameId.get(srcName);
		idDst=mapNameId.get(destName);
			
		//System.out.println("*********searching path from"+idSrc+"("+srcName+")"+" to "+idDst+"("+destName+")"+"************");
		//check the presence of at least 1 path
	try{
				
		pathsReturned=interactions.performGetPaths(idSrc,idDst);
		pathList=pathsReturned.getPath();
			
		}catch (ProcessingException e1) {
				
		e1.printStackTrace();
		throw new ServiceException("processing exception occurred");
				
		}catch (WebApplicationException  e2) {
				
		e2.printStackTrace();
		throw new ServiceException("problem with the server occurred:impossible loading nffg");
			
		}catch (Exception e3) {
				
		e3.printStackTrace();
		throw new ServiceException("general exception occured:impossible loading nffg");

		}
			
		if(pathList.size()>0)	
			for(Path p:pathList)
				if(p.length>0){result=true;break;}	  
		 		/* if (result==true)	System.out.printf("----NO PATH----") ; else System.out.printf("----AT LEAST ONE PATH EXISTS!---");*/			
		return result;	  		
	}
	

	@Override
	public String getCurrentGraphName() {
		
		return this.lastNffgLoaded;
		
	}

	private Map<String, String> getNameIDCorrelation(List<Node> nodeCollection) {
		
		Map<String,String> nameIdMap=new HashMap<String,String>();	
		 
		for(Node node : nodeCollection)	  
			nameIdMap.put(node.getProperty().get(0).getValue(),node.getId());
		  	    			 
		return nameIdMap;
	}
}