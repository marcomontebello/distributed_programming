package it.polito.dp2.NFFG.sol3.client2;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import it.polito.dp2.NFFG.FunctionalType;
import it.polito.dp2.NFFG.NffgVerifierException;
import it.polito.dp2.NFFG.TraversalPolicyReader;

public class TraversalPolicyReaderImplementation extends ReachabilityPolicyReaderImplementation implements TraversalPolicyReader {

	private List<FunctionType> functionList=new ArrayList<FunctionType>();
	
	
	public TraversalPolicyReaderImplementation(Policy policy, Nffg nffg) throws NffgVerifierException {
		
		super(policy,nffg);
		
		for(String s: policy.getTraversedFuncType())
			this.functionList.add(FunctionType.valueOf(s));
		
	}

	@Override
	public Set<FunctionalType> getTraversedFuctionalTypes() {
		
		Set<FunctionalType> functionSet= new HashSet<FunctionalType>();
	
		for(FunctionType actFunction : this.functionList)		
			functionSet.add(FunctionalType.fromValue(actFunction.value()));		
						
		return functionSet;
	}
}