package it.polito.dp2.NFFG.sol3.service;

import java.util.GregorianCalendar;
import java.util.Map;

import javax.ws.rs.WebApplicationException;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import it.polito.dp2.NFFG.sol3.service.Policy.VerificationResult;

public class VerificationService {

	private Neo4JClient neo4j=new Neo4JClient();
	private Map<String, Policy> policyDB=PolicyDB.getPolicyDB();

	
	public VerificationService() throws NffgServiceException{
		
		if(NffgServiceFilter.getRequestNumber()==0){
			
		try {				
			neo4j.performDeleteAllNodes();
			
			} catch (WebApplicationException e) {
				
			throw new NffgServiceException();
			
				}
			}		
	}

	public Policy execute(String policyName) throws  NffgServiceException {
		
		Policy policyToVerify = new Policy();
		VerificationResult verification = new Policy.VerificationResult();
		boolean pathExistance = false;
		
	try{
		
		String nffg=policyDB.get(policyName).getNffgRef();
		String idSrc=NffgsCache.getID(nffg, policyDB.get(policyName).getSrcNode().getName());
		String idDst=NffgsCache.getID(nffg, policyDB.get(policyName).getDstNode().getName());
	
		Paths result = neo4j.performGetPaths(idSrc,idDst);
	
		if (result.getPath().size()>0)
				
		  for(Path p: result.getPath()){
			  //System.out.println(p.srcNode+"<-src  "+p.dstNode+"<-dst lng->  "+p.length);	
			  if(p.length>0){
					  
				  pathExistance=true; 	  
				  break;	  
			  }
		  }
			  
		policyToVerify=policyDB.get(policyName);
		verification.verificationTime=getXMLGregorianCalendarNow();

		if 	((pathExistance==false && policyToVerify.getPolicyLogic()==PolicyLogicType.POSITIVE)			
			||(pathExistance==true && policyToVerify.getPolicyLogic()==PolicyLogicType.NEGATIVE)) {
			
			verification.resultMessage="Verification executed on the Server: THE POLICY IS NOT SATISFIED";
			verification.result="NOTSATISFIED";			
			
		}
		
		else{	
			
			verification.resultMessage="Verification executed on the Server: THE POLICY IS SATISFIED";
			verification.result="SATISFIED";
			
			}
				
		policyToVerify.setVerificationResult(verification);
		
		return policyToVerify;
				
		} catch(DatatypeConfigurationException e){
			
			throw new NffgServiceException("problem due to datatype exeption");
			
		}catch(Exception e){
			
			throw new NffgServiceException("generic exeption due to verification's operation");
			
		}
	}
	
	XMLGregorianCalendar getXMLGregorianCalendarNow() throws DatatypeConfigurationException
	
    {
		
        GregorianCalendar gregorianCalendar = new GregorianCalendar();
        DatatypeFactory datatypeFactory = DatatypeFactory.newInstance();
       
        XMLGregorianCalendar now = 
            datatypeFactory.newXMLGregorianCalendar(gregorianCalendar);
        
        return now;
        
    }
}
