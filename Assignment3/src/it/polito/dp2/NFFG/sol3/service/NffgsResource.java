package it.polito.dp2.NFFG.sol3.service;

import java.net.URI;
import javax.ws.rs.BadRequestException;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.InternalServerErrorException;


import javax.ws.rs.Consumes;
import javax.ws.rs.ForbiddenException;
import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.POST;
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


/** Resource class hosted at the URI relative path "/nffgs"
 */
@Path("/nffgs")
@Api(value = "/nffgs", description = "a collection of nffg objects")
public class NffgsResource {
	
	private NffgsService service;

	// create an instance of the object that can execute operations
	public NffgsResource() throws InternalServerErrorException{
		
	try{    	
		this.service = new NffgsService();
			
		} catch (NffgServiceException e) {
			
		throw new InternalServerErrorException();	
		}	    
	}
    
    @GET
	@ApiOperation(value="get the set of nffgs loaded into NEO4JXML",notes="xml and json formats")
	@ApiResponse(code=200,message="OK")
	@Produces({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
	public Nffgs getNffgsXML() throws WebApplicationException{
	
		Nffgs responseList=service.getNffgs();
		return responseList;
		 	
	}

	@GET
	@Path("{id}")
	@ApiOperation(	value = "get a specific nffg object", notes = "json and xml formats")
	@ApiResponses(value = {
		    @ApiResponse(code=200,message="OK"),
			@ApiResponse(code = 404, message = "Not Found"),
			@ApiResponse(code = 500, message = "Internal Server Error")})
	@Produces({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
	public Response getNffgXML(@PathParam("id") String id){
	
		Nffg response=service.getNffg(id);
		if(response==null)
			throw new NotFoundException();
		return Response.ok().entity(response).build();
	}
   
    @POST
    @ApiOperation(	value = "create a new nffg object", notes = "json and xml formats"
	)
    @ApiResponses(value = {
    		@ApiResponse(code = 201, message = "Created"),
    		@ApiResponse(code = 400, message = "Bad request, something missing"),
    		@ApiResponse(code = 403, message = "Forbidden, operation not permitted"),
    		@ApiResponse(code = 500, message = "Internal Server Error")})
    @Produces({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
    public Response postNffgXML( Nffg nffg, @Context UriInfo uriInfo) {
    				
      	Nffg created;   	
		try {
			
			synchronized(service.getSynchNffgCache()){
				
				synchronized(service.getSynchNameMapping()){
					
					created = service.loadNFFG(nffg);
				}
			}
		    		
		UriBuilder builder = uriInfo.getAbsolutePathBuilder();
	    URI u = builder.path(created.getName()).build();
	
	    return Response.created(u).entity((Nffg)created).build();
	    	
		} catch	(ConstraintViolatedException e ){
			e.printStackTrace();
			throw new ForbiddenException();
				
		}catch(NotWellFormedException e){
			
		e.printStackTrace();
		throw new BadRequestException("serviceException cathced");	
			
		}catch(NffgServiceException e){
			
		e.printStackTrace();
		//if some error occurs during the load of Nffg, delete the local data stored(about this loading)
		//until the launching of the exception 
		NffgsCache.getMappingDB().remove(nffg.getName());
		throw new InternalServerErrorException("serviceException cathced");	
		
		}	
	}  
}
