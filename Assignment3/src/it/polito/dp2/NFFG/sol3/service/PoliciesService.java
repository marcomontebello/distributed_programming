package it.polito.dp2.NFFG.sol3.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.ws.rs.InternalServerErrorException;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.WebApplicationException;

public class PoliciesService {

	private Map<String, Policy> policyDB=PolicyDB.getPolicyDB();
	private Neo4JClient neo4j=new Neo4JClient();

	public PoliciesService() throws NffgServiceException{
		
		if(NffgServiceFilter.getRequestNumber()==0){		
		try {		
			neo4j.performDeleteAllNodes();		
			} catch (WebApplicationException e) {
				
			throw new NffgServiceException();			
			}
		}		
	}
	
	
	public List<Policy> getPolicies() {
		
		List<Policy> policyList=new ArrayList<Policy>();	
		
		for(Policy policy: policyDB.values())	
			policyList.add(policy);
		return  policyList;
	}

	
	public Policy getPolicy(String id) {
		return PolicyDB.getPolicy(id);
		
	}


	public Policy loadPolicy(Policy policy) throws NotWellFormedException, ConstraintViolatedException, InternalServerErrorException{
		
		String namePolicy=policy.getName();
		if(!isWellFormed(policy))
			throw new NotWellFormedException();
		
		if	(policyDB.containsKey(namePolicy) 
			|| isForbidden(policy)
			)throw new ConstraintViolatedException();		
		return PolicyDB.storePolicy(policy);
		
	}


	public Policy updatePolicy(Policy policy) throws NotWellFormedException, ConstraintViolatedException, InternalServerErrorException{
		
		if(PolicyDB.getPolicy(policy.getName())==null)
    		throw new NotFoundException();
		
		if(!isWellFormed(policy))	
			throw new NotWellFormedException();
		
		if(isForbidden(policy))
			throw new ConstraintViolatedException();
		return PolicyDB.updatePolicy(policy);
	
	}
	
	public void deletePolicy(String namePolicy) throws NotFoundException{
		
			PolicyDB.deletePolicy(namePolicy);		
	}

	public static boolean isWellFormed(Policy policy){
		
		if 	(	policy.getName()==null
			||	policy.getNffgRef()==null
			||	policy.getSrcNode()==null
			||	policy.getDstNode()==null
			||	policy.getSrcNode().getName()==null
			||	policy.getDstNode().getName()==null
			||	policy.getPolicyLogic()==null 
			||	policy.getPolicyKind()==null
			||	(policy.getPolicyKind()==PolicyKindType.TRAVERSAL && policy.getTraversedFuncType().isEmpty())
			|| !isVerificationResultWellFormed(policy.getVerificationResult())
			)	return false;
		
		return true;
	}
	
	public static boolean isVerificationResultWellFormed(Policy.VerificationResult vR){
		
		if(	vR	!=null	)	
			if	(	vR.getResult()==null
				|| 	vR.getResultMessage()==null
				|| 	vR.getVerificationTime()==null
				)	return false;

		return true;
	}

	
	public static boolean isForbidden(Policy policy){
		
		ArrayList<String> nodeNames=new ArrayList<String>();
		Pattern p = Pattern.compile("[A-Za-z][A-Za-z0-9]+$");
		Matcher m = p.matcher(policy.getName());
		
		if (!m.matches())
			return true;
		
		if (!NffgsCache.contains(policy.getNffgRef()))
			return true;

		for(NodeType node: (NffgsCache.getNFFG(policy.getNffgRef()).getNode()))	
			nodeNames.add(node.getName());	
		
		if	(	!(nodeNames.contains(policy.getSrcNode().getName()))		
			||	!(nodeNames.contains(policy.getDstNode().getName()))
			|| 	(!policy.getPolicyLogic().equals(PolicyLogicType.POSITIVE) && (!policy.getPolicyLogic().equals(PolicyLogicType.NEGATIVE)))
			|| 	(!policy.getPolicyKind().equals(PolicyKindType.TRAVERSAL) && (!policy.getPolicyKind().equals(PolicyKindType.REACHABILITY)))	
			)	return true;
		
		return false;	
		
	}
	
	
	public Object getSynchPolicyDB(){
			return policyDB;		
	}		
}