package it.polito.dp2.NFFG.sol1;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import it.polito.dp2.NFFG.FunctionalType;
import it.polito.dp2.NFFG.TraversalPolicyReader;
import it.polito.dp2.NFFG.sol1.jaxb.*;
import it.polito.dp2.NFFG.NffgVerifierException;


public class TraversalPolicyReaderImplementation extends ReachabilityPolicyReaderImplementation implements TraversalPolicyReader {

	private List<FunctionType> functionList;
	
	
	public TraversalPolicyReaderImplementation(Policy policy,NffgType nffg) throws NffgVerifierException {
		
		super(policy,nffg);
		try{
		
		this.functionList=policy.getTraversedFuncType();

		} catch (Exception e) {
			e.printStackTrace();
			throw new NffgVerifierException();
		}	
		
	}

	@Override
	public Set<FunctionalType> getTraversedFuctionalTypes() {
		
		Set<FunctionalType> functionSet= new HashSet<FunctionalType>();
			
			for(FunctionType actFunction : this.functionList){
				
				functionSet.add(FunctionalType.fromValue(actFunction.value()));
			
			}
		
		return functionSet;
	}
}