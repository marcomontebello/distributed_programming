package it.polito.dp2.NFFG.sol3.service;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;

import javax.ws.rs.ext.Provider;

import java.io.IOException;
import java.util.concurrent.atomic.AtomicInteger;


@Provider
public class NffgServiceFilter implements ContainerRequestFilter {
 
	
	private static AtomicInteger requestNumber=new AtomicInteger(0);

	
	public static int getRequestNumber(){
		
		return requestNumber.get();
	}
	
	
	public void incrementRequestNumber(){
		
		requestNumber.incrementAndGet();
		
	}
	
	@Override
	public void filter(ContainerRequestContext requestContext) throws IOException {
		
		if (requestContext.getMethod().equals("GET") ||
			requestContext.getMethod().equals("POST")||
			requestContext.getMethod().equals("PUT") ||
			requestContext.getMethod().equals("DELETE") 
			)
			
			incrementRequestNumber();	
		
	}
	


}
