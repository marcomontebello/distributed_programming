package it.polito.dp2.NFFG.sol1;

import it.polito.dp2.NFFG.NffgReader;
import it.polito.dp2.NFFG.NffgVerifierException;
import it.polito.dp2.NFFG.PolicyReader;
import it.polito.dp2.NFFG.VerificationResultReader;
import it.polito.dp2.NFFG.sol1.jaxb.Policy;
import it.polito.dp2.NFFG.sol1.jaxb.PolicyLogicType;
import it.polito.dp2.NFFG.sol1.jaxb.PolicyKindType;
import it.polito.dp2.NFFG.sol1.jaxb.NffgType;



import it.polito.dp2.NFFG.sol1.jaxb.ResultType;
import it.polito.dp2.NFFG.sol1.jaxb.VerificationresultType;


public class PolicyReaderImplementation extends NamedEntityReaderImplementation implements PolicyReader {

	protected PolicyLogicType policyLogic;
	protected PolicyKindType policyKind;
	protected VerificationresultType vrt;
    protected NffgType ref;
    protected NffgReaderImplementation nffgReader;
	protected VerificationResultReaderImplementation cvrr;	
	
	public PolicyReaderImplementation(Policy policy, NffgType nffg) throws NffgVerifierException{
		 
		try {
		
		this.name=policy.getName();
		this.ref=nffg;
		this.policyLogic=policy.getPolicyLogic();
		this.policyKind=policy.getPolicyKind();
		this.vrt=policy.getVerificationResult();
		this.cvrr=new VerificationResultReaderImplementation(policy,vrt,ref);
				
		} catch (Exception e) {
			
		e.printStackTrace();
		throw new NffgVerifierException();	

		}
    }   
	        
	@Override
	public NffgReader getNffg() {	
		
	try{
		
		if(this.ref==null)
			return null;

		this.nffgReader=new NffgReaderImplementation(ref);
		
	} catch (NffgVerifierException e) {

		e.printStackTrace();
		return null;
	}
	
	return nffgReader;
	}

	@Override
	public VerificationResultReader getResult() {
				
		if  ( vrt.getResult().equals(ResultType.UNVERIFIED) || vrt==null )		
			return null;
			
		else	
			return this.cvrr;				
	}			
	
	
	@Override
	public Boolean isPositive() {
		
		Boolean returnValue = ((this.policyLogic.value().equals("POSITIVE")) ?  true :  false);
		return returnValue;
	}
}