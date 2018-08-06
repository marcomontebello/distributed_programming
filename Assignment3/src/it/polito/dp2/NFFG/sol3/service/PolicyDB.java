package it.polito.dp2.NFFG.sol3.service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.ws.rs.InternalServerErrorException;
import javax.ws.rs.NotFoundException;

public class PolicyDB {
	
	//policyDB contains the mapping of(key=namePolicy,value=policyType containing the policy)
	private static Map<String,Policy> policyDB=new ConcurrentHashMap<String,Policy>();
	
	public static Map<String,Policy> getPolicyDB(){
		
		return policyDB;
		
	}
	
	public static Policy getPolicy(String name){
		
		try{
			
		return policyDB.get(name);	
			
		}catch(NullPointerException|ClassCastException e){
			
		System.out.println("error occurs getting the required policy");
		e.printStackTrace();
		return null;				
		}		
	}

	public static Policy storePolicy(Policy policy) throws InternalServerErrorException {
		
		try {
			
		policyDB.put(policy.getName(),policy);
		
		} catch (Exception e) {

		System.out.println("error occurs loading a new policy");
		e.printStackTrace();
		throw new InternalServerErrorException();
		
		}
		
		return policy;
		
	}

	public static Policy updatePolicy(Policy policy) throws InternalServerErrorException {	
			
		try{	
			
		policyDB.replace(policy.getName(), policy);	
		
		}catch(Exception e){
			
		System.out.println("error occurs updating a new policy");
		throw new InternalServerErrorException();
		
		}	
			
		return policyDB.get(policy.getName());
	}
	
	
	public static void deletePolicy(String namePolicy) throws NotFoundException{
		
		try{
			
		policyDB.remove(namePolicy);
			
		}catch(Exception e){
			
		throw new NotFoundException();		
		
		}		
	}
}
