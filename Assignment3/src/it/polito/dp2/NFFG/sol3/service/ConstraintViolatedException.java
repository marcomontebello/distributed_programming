package it.polito.dp2.NFFG.sol3.service;

@SuppressWarnings("serial")
public class ConstraintViolatedException extends Exception {
	
	public ConstraintViolatedException(){
		
		super("The body of the Request doesn't follow the constraints of the application");
		
	}

}
