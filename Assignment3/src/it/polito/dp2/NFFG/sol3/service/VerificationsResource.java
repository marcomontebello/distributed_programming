package it.polito.dp2.NFFG.sol3.service;


import java.net.HttpURLConnection;
import java.util.List;

import javax.ws.rs.BadRequestException;
import javax.ws.rs.GET;
import javax.ws.rs.InternalServerErrorException;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Context;

import javax.ws.rs.core.Response;
import javax.servlet.http.HttpServletRequest;


import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiResponse;
import com.wordnik.swagger.annotations.ApiResponses;


/** Resource class hosted at the URI relative path "/verifications"
 */
@Path("/verifications")
@Api(value = "/verifications", description = "root resource to execute verifications")
public class VerificationsResource {
	
    private VerificationService service;

    public VerificationsResource() throws InternalServerErrorException{
		
	try {	
	    this.service = new VerificationService();
			
		} catch (NffgServiceException e) {
			
		throw new InternalServerErrorException();		
		}    
	}

	 @GET
	 @ApiOperation(value="get the set of nffgs loaded into NEO4JXML",notes="xml and json formats")

	 @ApiResponses(value = { 		
	    	@ApiResponse(code = 200,message="OK"),
	    	@ApiResponse(code = 400, message = "Bad request, something missing"),
	    	@ApiResponse(code = 404, message = "Not found, something wrong"),
	    	@ApiResponse(code = 500, message = "Internal Server Error")})
	
	 @Produces({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
	 public Policies executeVerifications( @Context HttpServletRequest request, @QueryParam("policyName") List <String> policyName) {	  
		 
		 if (null == request.getParameter("policyName")) {
			    
			 throw new BadRequestException(Response.status(HttpURLConnection.HTTP_BAD_REQUEST)
				 		.entity("policyName parameter is mandatory")
				 		.build());
		 }
		
		//check if all the policies requested are valid 
		for(String policy:policyName){
			if(!PolicyDB.getPolicyDB().containsKey(policy))
				throw new NotFoundException();							
		}
		
		
	try {	
		Policies result=new Policies();
		for(String policy:policyName){
			//to prevent modification on PolicyDB during verification
			synchronized((Object) PolicyDB.getPolicyDB()){
					
					Policy singleResult=service.execute(policy);	
					result.getPolicy().add(singleResult);	
			}	
		}
				
		return result;
				
	} catch(NffgServiceException e) {
			
		e.printStackTrace();
		throw new InternalServerErrorException();
		
		}		    	
    }	
}
