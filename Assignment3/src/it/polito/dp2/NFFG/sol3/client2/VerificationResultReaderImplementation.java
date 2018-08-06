package it.polito.dp2.NFFG.sol3.client2;

import java.util.Calendar;

import javax.xml.datatype.XMLGregorianCalendar;

import it.polito.dp2.NFFG.NffgVerifierException;
import it.polito.dp2.NFFG.PolicyReader;
import it.polito.dp2.NFFG.sol3.client2.Policy.VerificationResult;
import it.polito.dp2.NFFG.VerificationResultReader;

public class VerificationResultReaderImplementation implements VerificationResultReader {
	
	private Boolean result=new Boolean(null);
	private String resultMsg;
	private XMLGregorianCalendar verificationTime;
	private Policy pol;
	private Nffg nffgRef;
	
	public VerificationResultReaderImplementation(VerificationResult vrt,Policy policy, Nffg nffg) {
		
		this.pol=policy;	
		this.nffgRef=nffg;

		if(vrt==null){
			
			this.resultMsg=null;
			this.verificationTime=null;
			this.result=null;
			
		}
		
		else{
			
			if(vrt.getResult().equals("SATISFIED"))
				this.result=true;
					
			if(vrt.getResult().equals("NOTSATISFIED"))
				this.result=false;
							
			this.resultMsg=vrt.getResultMessage();
			this.verificationTime=vrt.getVerificationTime();		
		}
	}

	
	@Override
	public PolicyReader getPolicy() {
		try {
			
		if(pol.getPolicyKind()==PolicyKindType.TRAVERSAL)
			return new TraversalPolicyReaderImplementation(pol,nffgRef);
			
					
		if(pol.getPolicyKind()==PolicyKindType.REACHABILITY)
			return new ReachabilityPolicyReaderImplementation(pol,nffgRef);						
		
		} catch (NffgVerifierException e) {
			e.printStackTrace();
			return null;
		}
		return null;
	}

	@Override
	public Boolean getVerificationResult() {
			return this.result;
			
	}

	@Override
	public String getVerificationResultMsg() {
			return this.resultMsg;
	}

	@Override
	public Calendar getVerificationTime() {
			
			Calendar calendar;
			
			try {
				
			calendar = this.verificationTime.toGregorianCalendar();
			return calendar;
				
			} catch (Exception e) {

			e.printStackTrace();
			return null;
			
			}	
	}

}
