package it.polito.dp2.NFFG.sol3.service;


import java.net.URI;
import javax.ws.rs.BadRequestException;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.ForbiddenException;
import javax.ws.rs.GET;
import javax.ws.rs.InternalServerErrorException;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;


import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiResponse;
import com.wordnik.swagger.annotations.ApiResponses;

/** Resource class hosted at the URI relative path "/policies"
 */
@Path("/policies")
@Api(value = "/policies", description = "a collection of policies objects")
public class PoliciesResource {
	
	// create an instance of the object that can execute operations
	private PoliciesService service;
	    
	public PoliciesResource() throws InternalServerErrorException{
			
		try {
		    	
			this.service = new PoliciesService();
				
			} catch (NffgServiceException e) {			
			throw new InternalServerErrorException();					
			}
		}
    
	@GET
	@ApiOperation(value="get the set of policies loaded into NEO4JXML",notes="xml and json formats")
	@ApiResponse(code=200,message="OK")
	@Produces({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
	public Policies getPoliciesXML(){
	
		Policies policyList=new Policies();
		policyList.getPolicy().addAll(service.getPolicies());	
		return  policyList; 	
		
	}

	@GET
	@Path("{id}")
	@ApiOperation(	value = "get a specific policy object", notes = "xml format"
	)
	@ApiResponses(value = {
		    @ApiResponse(code=200,message="OK"),
			@ApiResponse(code = 404, message = "Not Found"),
			@ApiResponse(code = 500, message = "Internal Server Error")})
	@Produces({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
	public Response getPolicyXMLJSON(@PathParam("id") String id){
	
		Policy response=service.getPolicy(id);
		if(response==null)
			throw new NotFoundException();
		
		return Response.ok().entity(response).build();
	
	}


	@POST
    @ApiOperation(	value = "create a new policy object", notes = "xml format")
    @ApiResponses(value = {
    		@ApiResponse(code = 201, message = "Created"),
    		@ApiResponse(code = 400, message = "Bad request, something missing"),	
    		@ApiResponse(code = 403, message = "Forbidden request,constraint violated"),
    		@ApiResponse(code = 500, message = "Internal Server Error")})
    @Produces({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
    public Response postAddPolicy(Policy policy, @Context UriInfo uriInfo) {
    				
      	Policy created;
      	
      	try{		
      	//get the lock of the policyDB 'cause i don't want someone try to load a policy with the same name
      	// in the meanwhile I execute the instruction and checks to follow to load Policy
      	//The Id is choosed by the client not bye the Service-->Race Condition
      		synchronized (service.getSynchPolicyDB()){
      			
      			created = service.loadPolicy(policy);
      			
      		}
      	//System.out.println(created.getName());      		
		}catch(ConstraintViolatedException e){
			
			throw new ForbiddenException();
			
		}catch(NotWellFormedException e){
			
			throw new BadRequestException();
			
		}
	
	  	if (created!=null) { // success
	  		
	     	UriBuilder builder = uriInfo.getAbsolutePathBuilder();
	     	URI u = builder.path(policy.getName()).build();
	     	return Response.created(u).entity(created).build();
	     	
	  	} else  		
	 		throw new BadRequestException();				
	}
    
    
    @PUT
	@Path("{id}")
	@ApiOperation(	value = "update a policy object", notes = "xml format")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "OK"),
			@ApiResponse(code = 403, message = "Forbidden"),
			@ApiResponse(code = 404, message = "Not found"),
			@ApiResponse(code = 500, message = "Internal Server Error")})
	@Produces({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
	@Consumes({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
	public Response  putPolicyXMLJSON(@PathParam("id") String id, Policy policy, @Context UriInfo uriInfo) {

    	
		if (!id.equals(policy.name))
			throw new ForbiddenException("error");	
		
		else{
			
			try{	
				synchronized (service.getSynchPolicyDB()){
					
					Policy newP = service.updatePolicy(policy);
					return Response.ok(newP).build();
				}	
				
			}catch(ConstraintViolatedException e){
				
			throw new ForbiddenException();			
				
			}catch(NotWellFormedException e){	
				
			throw new BadRequestException();			
			
			}				
		}		
	}

	@DELETE
    @Path("{id}")
    @ApiOperation(	value = "remove a policy object", notes = "xml format"
	)
    @ApiResponses(value = {
    		@ApiResponse(code = 204, message = "NO CONTENT"),
    		@ApiResponse(code = 404, message = "Not Found"),
    		@ApiResponse(code = 500, message = "Internal Server Error")})
    @Produces({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
	
    public Response deletePolicyXMLJSON(@PathParam("id") String id){
     	    	
    	synchronized (service.getSynchPolicyDB()){
    	
    		service.deletePolicy(id);
    	
    	}	
    	return Response.noContent().build();   	
    }
}
