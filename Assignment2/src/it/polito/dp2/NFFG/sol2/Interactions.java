package it.polito.dp2.NFFG.sol2;

import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriBuilder;

import it.polito.dp2.NFFG.lab2.ServiceException;

public class Interactions {
	
	
	private Client client;
	private String urlstring;
	
	public Interactions(){
		
		this.client=ClientBuilder.newClient();
	    this.urlstring= System.getProperty("it.polito.dp2.NFFG.lab2.URL");
		
	}

	public Integer performPostCreateNode(Node actualNode){
		
		Node response=this.client.target(UriBuilder.fromUri(this.urlstring).build()+"/resource/node/")
							.request(MediaType.APPLICATION_XML)
							.post(Entity.entity(actualNode,MediaType.APPLICATION_XML),Node.class);
		return Integer.parseInt(response.getId());
	
	}

	public void performPostCreateLabels(Integer idnode, Labels actLabels){
				
		this.client.target(UriBuilder.fromUri(this.urlstring).build()+"/resource/node/"+idnode+"/label")
	               .request(MediaType.APPLICATION_XML)
	               .post(Entity.entity(actLabels,MediaType.APPLICATION_XML),String.class);
	
	}
	
	
	public void performPostCreateRelationship(String idSrcNode,String idDstNode){
		
		Relationship rl=new Relationship();
		rl.setDstNode(idDstNode);
		rl.setType("Link");
		this.client.target(UriBuilder.fromUri(this.urlstring).build()+"/resource/node/"+idSrcNode+"/relationship")
				.request()
				.post(Entity.entity(rl, MediaType.APPLICATION_XML),Relationship.class);
		
	}
	
	public List<Node> performGetAllNodes() throws ServiceException{
		
		List <Node> result=null;
		result = this.client.target(UriBuilder.fromUri(this.urlstring).build()+"/resource/nodes")
                .request(MediaType.APPLICATION_XML)
	            .get(new GenericType<List<Node>>() {});	
		return result;			
	} 	
	
	
	public Paths performGetPaths(String srcId,String destId) throws ServiceException {
		
		Paths result=this.client.target(UriBuilder.fromUri(this.urlstring).build()+"/resource/node/"+srcId+"/paths")
					.queryParam("dst",destId)
	                .request(MediaType.APPLICATION_XML)
		            .get(Paths.class);		
		return result;
	}


	public void performDeleteAllNodes(){
		
		this.client.target(UriBuilder.fromUri(urlstring).build()+"/resource/nodes")
			.request(MediaType.APPLICATION_XML)
			.delete(String.class);			
	}	
}