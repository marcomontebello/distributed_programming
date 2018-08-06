package it.polito.dp2.NFFG.sol3.service;

public class NotWellFormedException extends Exception {
	
	public NotWellFormedException(){
		
		super("The body of the Request is not Well Formed");
		
	}

}
