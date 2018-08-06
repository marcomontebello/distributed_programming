package it.polito.dp2.NFFG.sol3.client2;

import it.polito.dp2.NFFG.NffgReader;
import it.polito.dp2.NFFG.NffgVerifierException;
import it.polito.dp2.NFFG.VerificationResultReader;
import it.polito.dp2.NFFG.sol3.client2.Policy.VerificationResult;


public class PolicyReaderImplementation extends NamedEntityReaderImplementation implements it.polito.dp2.NFFG.PolicyReader {

    protected PolicyLogicType policyLogic;
    protected VerificationResult verResult;
    protected PolicyKindType policyKind;
    protected NffgReaderImplementation nffgReader;
    protected Nffg ref;
    protected VerificationResultReaderImplementation verResultReader;
	
	public PolicyReaderImplementation(Policy policy,Nffg nffg) throws NffgVerifierException {
		
		try {
			
			this.name=policy.getName();
			this.policyLogic=policy.getPolicyLogic();
			this.policyKind=policy.getPolicyKind();
			this.ref=nffg;
			this.verResult=policy.getVerificationResult();
			this.verResultReader=new VerificationResultReaderImplementation(this.verResult,policy,nffg);
			
		} catch (Exception e) {

		e.printStackTrace();
		throw new NffgVerifierException();	
		}
	}

	@Override
	public NffgReader getNffg() {
		
	try {
		
		if(this.ref==null)
			return null;

		nffgReader=new NffgReaderImplementation(ref);

		} catch (NffgVerifierException e) {

			e.printStackTrace();
			return null;
		}
	
	return nffgReader;
	}

	@Override
	public VerificationResultReader getResult() {
		
		if(this.verResult==null)		
			
			return null;
			
		return this.verResultReader;
	}

	@Override
	public Boolean isPositive() {
		
		Boolean returnValue = ((this.policyLogic.value().equals("POSITIVE")) ?  true :  false);
		
		return returnValue;
	}



}
