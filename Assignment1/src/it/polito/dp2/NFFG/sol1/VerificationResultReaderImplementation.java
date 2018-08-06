package it.polito.dp2.NFFG.sol1;

import java.util.Calendar;

import javax.xml.datatype.XMLGregorianCalendar;

import it.polito.dp2.NFFG.NffgVerifierException;
import it.polito.dp2.NFFG.PolicyReader;
import it.polito.dp2.NFFG.VerificationResultReader;
import it.polito.dp2.NFFG.sol1.jaxb.Policy;
import it.polito.dp2.NFFG.sol1.jaxb.PolicyKindType;

import it.polito.dp2.NFFG.sol1.jaxb.ResultType;
import it.polito.dp2.NFFG.sol1.jaxb.VerificationresultType;
import it.polito.dp2.NFFG.sol1.jaxb.NffgType;


public class VerificationResultReaderImplementation implements VerificationResultReader{

	private Policy pol;	
	private Boolean result;
	private String resultMsg;
	private XMLGregorianCalendar verificationTime;
	private NffgType nffgRef;
	
	public VerificationResultReaderImplementation(Policy pol,VerificationresultType verificationResult, NffgType nffg){
		
		this.pol=pol;
		this.nffgRef=nffg;

		if(verificationResult.getResult().equals(ResultType.SATISFIED))
			this.result=true;
			
		if(verificationResult.getResult().equals(ResultType.NOTSATISFIED))	
			this.result=false;

		if (verificationResult.getResult().equals(ResultType.UNVERIFIED))	{

			this.result=null;
			this.resultMsg=null;
			this.verificationTime=null;
			return;
		}
		
		this.resultMsg=verificationResult.getResultMessage();
		this.verificationTime=verificationResult.getVerificationTime();
		
	}

	@Override
	public PolicyReader getPolicy() {
		
	try{
		
		if(pol.getPolicyKind()==PolicyKindType.TRAVERSAL)
			return new TraversalPolicyReaderImplementation(pol,nffgRef);
								
		else return new ReachabilityPolicyReaderImplementation(pol,nffgRef);
		
	}catch(Exception e){
		
		return null;	
	}
	
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

		Calendar calendar = this.verificationTime.toGregorianCalendar();
			return calendar;		
	}
}