package it.polito.dp2.NFFG.sol3.service;


import javax.ws.rs.WebApplicationException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriBuilder;

class Neo4JClient {
	
	private Client client;
	private String urlstring;

	
	public  Neo4JClient(){
		
		this.client=ClientBuilder.newClient();
		String temp=System.getProperty("it.polito.dp2.NFFG.lab3.NEO4JURL");
		this.urlstring=(temp==null? "http://localhost:8080/Neo4JXML/rest": temp);	
		
	}

	public Integer performPostCreateNode(Node actualNode) throws NffgServiceException{
	//System.out.println("--- Performing POST "+urlstring+"/node/ --- \n");
	
	try{
		Node response = this.client.target(UriBuilder.fromUri(this.urlstring).build()+"/resource/node/")
		               .request(MediaType.APPLICATION_XML)
		               .post(Entity.entity(actualNode,MediaType.APPLICATION_XML),Node.class);
		return Integer.parseInt(response.getId());
		
		}catch(Exception e){		
		throw new NffgServiceException();
		}
	}

	public void performPostCreateLabels(Integer idnode, Labels actLabels) throws NffgServiceException{
		
	try{	
		this.client.target(UriBuilder.fromUri(this.urlstring).build()+"/resource/node/"+idnode+"/label")
	               .request(MediaType.APPLICATION_XML)
	               .post(Entity.entity(actLabels,MediaType.APPLICATION_XML),String.class);
		
		}catch(Exception e){		
		throw new NffgServiceException();
		}
	}
	
	public String performPostCreateRelationship(String idSrcNode,String idDstNode,String type) throws NffgServiceException{
		
		Relationship rl=new Relationship();
		rl.setDstNode(idDstNode);
		rl.setType(type);
	try{
		Relationship result = this.client.target(UriBuilder.fromUri(this.urlstring).build()+"/resource/node/"+idSrcNode+"/relationship")
							.request()
							.post(Entity.entity(rl, MediaType.APPLICATION_XML),Relationship.class);
		return result.getId();	
		
		}catch(Exception e){			
		throw new NffgServiceException();
		}
	}	
	
	public Paths performGetPaths(String srcId,String destId) {
	//System.out.println("--- Performing GET "+urlstring+"/resource/node/"+srcId+"paths?dst="+destId+"--- \n");
		Paths result = this.client.target(UriBuilder.fromUri(this.urlstring).build()+"/resource/node/"+srcId+"/paths")
					.queryParam("dst",destId)
	                .request(MediaType.APPLICATION_XML)
		            .get(Paths.class);			
		return result;			
	}
	
	public  void performDeleteAllNodes() throws WebApplicationException{
	
	try{
		String respDelete=client.target(UriBuilder.fromUri(urlstring).build()+"/resource/nodes")
	                .request(MediaType.APPLICATION_XML)
	                .delete(String.class);			
		}catch(Exception e){
		
		e.printStackTrace();
		throw new WebApplicationException();
		
		}
	}
}