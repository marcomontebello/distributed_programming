package it.polito.dp2.NFFG.sol3.client2;


import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriBuilder;

class ServiceInteractions {
	
	private Client client;
	private String urlstring;

	
	public  ServiceInteractions(){
		
		this.client=ClientBuilder.newClient();	
		String temp=System.getProperty("it.polito.dp2.NFFG.lab3.URL");
		urlstring=(temp==null? "http://localhost:8080/NffgService/rest": temp);	
		
	}

	public Nffgs performGetNffgs(){
		
		Nffgs result=null;
	//	System.out.println("--- Performing GET "+urlstring+"/nffgs/--- \n");
		result = this.client.target(UriBuilder.fromUri(this.urlstring).build()+"/nffgs")
                .request(MediaType.APPLICATION_XML)
	            .get(Nffgs.class);
		
		return result;	
		
	} 	
	
	
	public Policies performGetPolicies(){
		
		Policies result=null;
		//System.out.println("--- Performing GET "+urlstring+"/nffgs/--- \n");
		result = this.client.target(UriBuilder.fromUri(this.urlstring).build()+"/policies")
                .request(MediaType.APPLICATION_XML)
	            .get(Policies.class);
		
		return result;	
		
	} 	
	

}
