package it.polito.dp2.NFFG.sol3.client2;

import java.util.Calendar;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;


import it.polito.dp2.NFFG.NffgReader;
import it.polito.dp2.NFFG.NffgVerifier;
import it.polito.dp2.NFFG.NffgVerifierException;
import it.polito.dp2.NFFG.PolicyReader;


public class NffgVerifierImplementation implements NffgVerifier {
	
	private ServiceInteractions interaction;
	private HashSet<PolicyReaderImplementation> policySet;
	private HashSet<NffgReaderImplementation> nffgSet;

	
	public NffgVerifierImplementation() throws NffgVerifierException{
		
		this.interaction=new ServiceInteractions();	
		nffgSet=new HashSet<NffgReaderImplementation>();
		policySet=new HashSet<PolicyReaderImplementation>();

	try {
		Nffgs nffgsResponse=interaction.performGetNffgs();
		for(Nffg actNffg: nffgsResponse.getNffg())
			nffgSet.add(new NffgReaderImplementation(actNffg));
				
		for(Policy actPolicy: interaction.performGetPolicies().getPolicy()){
			Nffg policyNffg=null;
			for(Nffg actNffg: nffgsResponse.getNffg())
				if(actNffg.getName().equals(actPolicy.getNffgRef()))
					policyNffg=actNffg;
							
			if(actPolicy.getPolicyKind()==PolicyKindType.TRAVERSAL)
				policySet.add(new TraversalPolicyReaderImplementation(actPolicy,policyNffg));
						
			if(actPolicy.getPolicyKind()==PolicyKindType.REACHABILITY)
				policySet.add(new ReachabilityPolicyReaderImplementation(actPolicy,policyNffg));				
		}
		
	}catch (Exception e3) {
			
	e3.printStackTrace();
	throw new NffgVerifierException("some error occurred creating a new nffgVerifier instance");
	}	
	
	}
	
	@Override
	public NffgReader getNffg(String arg0) {
		
		Iterator<NffgReaderImplementation> i = nffgSet.iterator();
			
		while (i.hasNext()) {
				
			NffgReaderImplementation actual_nffg = i.next();
			if (actual_nffg.getName().equals(arg0))
				return actual_nffg;
		}
		return null;
	}

	@Override
	public Set<NffgReader> getNffgs() {
		
		if (nffgSet != null)		
			return (HashSet<NffgReader>) new HashSet<NffgReader>(nffgSet);
		return  new HashSet<NffgReader>();
		
	}

	@Override
	public Set<PolicyReader> getPolicies() {
		
		if (policySet != null)	
			return (HashSet<PolicyReader>) new HashSet<PolicyReader>(policySet);
		
		return  new HashSet<PolicyReader>();
		
	}

	
	@Override
	public Set<PolicyReader> getPolicies(String arg0) {
		
		HashSet<PolicyReaderImplementation> policySetToReturn= new HashSet<PolicyReaderImplementation>();
		
		for(PolicyReaderImplementation policy: policySet){
			if (policy.getNffg().getName().equals(arg0))	
				policySetToReturn.add(policy);
		}
		
		if (policySetToReturn.size()==0)
			return null;

		return (HashSet<PolicyReader>) new HashSet<PolicyReader>(policySetToReturn);
	
	}

	@Override
	public Set<PolicyReader> getPolicies(Calendar arg0) {
		
		HashSet<PolicyReaderImplementation> policySetToReturn= new HashSet<PolicyReaderImplementation>();
		
		for(PolicyReaderImplementation policy: policySet){
			
	try {
				
			if (policy.getResult().getVerificationTime().compareTo(arg0)>0)
				policySetToReturn.add(policy);
				
			} catch (NullPointerException e) {
				
			e.printStackTrace();
			return null;
				
			}catch (IllegalArgumentException e) {
				
			e.printStackTrace();
			return null;
			}			
		}
		
		if (policySetToReturn.size()==0)
			return null;
		
		return (HashSet<PolicyReader>) new HashSet<PolicyReader>(policySetToReturn);

	}

	
	public PolicyReader getPolicy(String nameOfPolicy) {
				
			Iterator<PolicyReaderImplementation> i = policySet.iterator();
			while (i.hasNext()) {
				
				PolicyReaderImplementation actual_policy = i.next();
				if (actual_policy.getName().equals(nameOfPolicy))
					return actual_policy;
			}
			return null;
		}	
}
