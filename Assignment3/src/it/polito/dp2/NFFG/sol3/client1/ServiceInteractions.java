package it.polito.dp2.NFFG.sol3.client1;


import javax.ws.rs.ProcessingException;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;

import it.polito.dp2.NFFG.lab3.ServiceException;
import it.polito.dp2.NFFG.lab3.UnknownNameException;

class ServiceInteractions {
	
	private Client client;
	private String urlstring;

	public ServiceInteractions(){
		
		this.client=ClientBuilder.newClient();
		String temp=System.getProperty("it.polito.dp2.NFFG.lab3.URL");
		urlstring=(temp==null? "http://localhost:8080/NffgService/rest": temp);	
	}

	public Nffg performGetNffg(String name) throws ServiceException {
	//System.out.println("--- Performing GET "+urlstring+"/nffgs/"+name+" --- \n");
	try{		
		Response result= this.client.target(UriBuilder.fromUri(this.urlstring).build()+"/nffgs/"+name)
                .request(MediaType.APPLICATION_XML)
	            .get();
		if (result.getStatus() == 404) 
		      return null;
		return result.readEntity(Nffg.class);	

		}catch (ProcessingException | WebApplicationException e1) {
			
		e1.printStackTrace();
		throw new ServiceException();		
		}	
	} 	
	
	public void performPostCreateNffg(Nffg nffgToLoad) {
	//System.out.println("--- Performing POST "+urlstring+"/nffgs --- \n");
		this.client.target(UriBuilder.fromUri(this.urlstring).build()+"/nffgs")
		           .request(MediaType.APPLICATION_XML)
		           .post(Entity.entity(nffgToLoad,MediaType.APPLICATION_XML),Nffg.class);		
	}

	public void performPOSTpolicy(Policy policyToLoad)  {
	//System.out.println("--- Performing POST "+urlstring+"/policies/ \n");

		this.client.target(UriBuilder.fromUri(this.urlstring).build()+"/policies")
	               .request(MediaType.APPLICATION_XML)
	               .post(Entity.entity(policyToLoad,MediaType.APPLICATION_XML),Policy.class);
	}

	public Policy performPUTPolicy(Policy policyToLoad){
	//System.out.println("--- Performing GET "+urlstring+"/verifications?policyName="+policyName);
		Response result = this.client.target(UriBuilder.fromUri(this.urlstring).build()+"/policies/"+policyToLoad.getName())
		            .request(MediaType.APPLICATION_XML)
			        .put(Entity.entity(policyToLoad,MediaType.APPLICATION_XML));
		
		if (result.getStatus() == 404)
			return null;
		return result.readEntity(Policy.class);	
	}

	public void performDELETEpolicy(String name) throws UnknownNameException {
	//System.out.println("----performing a DELETE On the WebService-----");
		Response respDelete=client.target(UriBuilder.fromUri(urlstring).build()+"/policies/"+name)
                .request(MediaType.APPLICATION_XML)
                .delete();	
	//System.out.println(respDelete);
		if (respDelete.getStatus() == 404) 
		      throw new UnknownNameException();
	      			
	}

	public Policy performGETVerification(String policyName) throws UnknownNameException{
	//System.out.println("--- Performing GET "+urlstring+"/verifications?policyName="+policyName);
		Response result = this.client.target(UriBuilder.fromUri(this.urlstring).build()+"/verifications")
				.queryParam("policyName",policyName)
	            .request(MediaType.APPLICATION_XML)
		        .get();
			
		if (result.getStatus() == 404) 
			throw new UnknownNameException();
			   
		Policies policies=result.readEntity(Policies.class); 
		return policies.getPolicy().get(0);	
	}
}
