package it.polito.dp2.NFFG.sol3.service;

public class NffgServiceException  extends Exception{

	public NffgServiceException(){
	
		super();		
	}

	public NffgServiceException(String message){
		
		super("The policy searched is not present in the Service");
	
	}
	
}
