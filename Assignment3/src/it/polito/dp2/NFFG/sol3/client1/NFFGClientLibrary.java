package it.polito.dp2.NFFG.sol3.client1;

import java.math.BigInteger;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Set;

import javax.ws.rs.ProcessingException;
import javax.ws.rs.WebApplicationException;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import it.polito.dp2.NFFG.FactoryConfigurationError;
import it.polito.dp2.NFFG.FunctionalType;
import it.polito.dp2.NFFG.LinkReader;
import it.polito.dp2.NFFG.NffgReader;
import it.polito.dp2.NFFG.NffgVerifier;
import it.polito.dp2.NFFG.NffgVerifierException;
import it.polito.dp2.NFFG.NffgVerifierFactory;
import it.polito.dp2.NFFG.NodeReader;
import it.polito.dp2.NFFG.PolicyReader;
import it.polito.dp2.NFFG.ReachabilityPolicyReader;
import it.polito.dp2.NFFG.TraversalPolicyReader;
import it.polito.dp2.NFFG.lab3.AlreadyLoadedException;
import it.polito.dp2.NFFG.lab3.NFFGClient;
import it.polito.dp2.NFFG.lab3.NFFGClientException;
import it.polito.dp2.NFFG.lab3.ServiceException;
import it.polito.dp2.NFFG.lab3.UnknownNameException;
import it.polito.dp2.NFFG.sol3.client1.Policy.DstNode;
import it.polito.dp2.NFFG.sol3.client1.Policy.SrcNode;

public class NFFGClientLibrary implements NFFGClient {
	
	private NffgVerifier monitor;
	private ServiceInteractions interaction;
	
	
	NFFGClientLibrary() throws NFFGClientException{
		
		try {
			
			NffgVerifierFactory factory = NffgVerifierFactory.newInstance();
			this.monitor = factory.newNffgVerifier();
			
		} catch (FactoryConfigurationError | NffgVerifierException e) {
			
			e.printStackTrace();
			System.out.println("exception occurred creating a new NFFGClientLIbrary");
			e.printStackTrace();
			throw new NFFGClientException();
			
		} 
			
		this.interaction=new ServiceInteractions();		
						
	}

	@Override
	public void loadNFFG(String name) throws UnknownNameException, AlreadyLoadedException, ServiceException {

		Nffg nffgToLoad = new Nffg();
		NffgReader nffgToRead=this.monitor.getNffg(name);	
		
		if	(nffgToRead==null) 	
			throw new UnknownNameException("---Nffg requested does not exist locally---\n");			

		Set<NodeReader> nodeReaderSet=nffgToRead.getNodes();
		
		Nffg alreadyLoaded = interaction.performGetNffg(name);
		if(alreadyLoaded!=null)
			throw new AlreadyLoadedException(); 
		
		nffgToLoad.setName(name);
		
		try{
			
		nffgToLoad.setUpdateTime(toXMLGregorianCalendar(nffgToRead.getUpdateTime()));
			
		} catch (DatatypeConfigurationException e) {
			
		e.printStackTrace();
		throw new ServiceException("Problem occurred processing nffg updateTime");

		}	catch (Exception e) {
			
		e.printStackTrace();
		throw new ServiceException("Generic Problem occurred processing nffg updateTime");

		}
		
		nffgToLoad.setNumberOfNodes(BigInteger.valueOf(nodeReaderSet.size()));

		try{
			
			
		for(NodeReader node: nodeReaderSet){
				
			NodeType actualNode=new NodeType();
				
			actualNode.setName(node.getName());
			actualNode.setFunction(FunctionType.fromValue(node.getFuncType().toString()));
				
			Set<LinkReader> linkSet= node.getLinks();
			//System.out.println("trying to load nffg:"+nffgToLoad.getName()+"node:"+node.getName());
			nffgToLoad.getNode().add(actualNode);
	
			for(LinkReader link_r : linkSet){
				//System.out.println("srcNode:"+link_r.getSourceNode().getName());
				LinkTypeRef actualLink=new LinkTypeRef();
	
				actualLink.setName(link_r.getName());
				actualLink.setSrcNode(link_r.getSourceNode().getName());
				actualLink.setDstNode(link_r.getDestinationNode().getName());
				actualNode.getLinkRef().add(actualLink);				
			}		
		}	
		
		interaction.performPostCreateNffg(nffgToLoad);
			
		} catch (ProcessingException e1) {
			
		e1.printStackTrace();
		throw new ServiceException("System not well configured or error during JAX-RS sending request/request processing");	
			
		}catch (WebApplicationException  e2) {
			
		throw new ServiceException("Server returned some error");
		
		}catch (Exception  e3) {
			
		e3.printStackTrace();
		throw new ServiceException("Unexpected exception");
	
		}	
	}

	@Override
	public void loadAll() throws AlreadyLoadedException, ServiceException {	
		
		Set<NffgReader> setNffg = monitor.getNffgs();
		
		for (NffgReader nr: setNffg) {
			
			try {
				
			loadNFFG(nr.getName());
				
			} catch (UnknownNameException e) {
				
			e.printStackTrace();
				
			} catch(AlreadyLoadedException e){
					
			throw new AlreadyLoadedException();
			
			}catch(ServiceException e){
			
			throw new ServiceException();
			}
		}	
			
	try{
			
		Set<PolicyReader> setPolicy=monitor.getPolicies();
		
		for (PolicyReader pr: setPolicy) {
			
			Policy policyToLoad;	
			Policy.VerificationResult ver=new Policy.VerificationResult();
			PolicyLogicType logic=(pr.isPositive() ? PolicyLogicType.POSITIVE : PolicyLogicType.NEGATIVE);
			String srcNode=((ReachabilityPolicyReader) pr).getSourceNode().getName();
			String dstNode=(((ReachabilityPolicyReader) pr).getDestinationNode().getName());

			if (pr instanceof TraversalPolicyReader){	
				
				policyToLoad = fillPolicyHeader(pr.getName(), pr.getNffg().getName(),PolicyKindType.TRAVERSAL, logic, srcNode, dstNode);
							
				Set<FunctionalType> FunctionSet = ((TraversalPolicyReader) pr).getTraversedFuctionalTypes();
				
				for (FunctionalType f: FunctionSet)	
					policyToLoad.getTraversedFuncType().add(f.value());					
			}
	
			else 				
				policyToLoad = fillPolicyHeader(pr.getName(), pr.getNffg().getName(),PolicyKindType.REACHABILITY, logic, srcNode, dstNode);
			
			if(pr.getResult()!=null){
	
				String resultValue;
					
				if(pr.getResult().getVerificationResult()==null)		
						resultValue=null;
						
				else
						resultValue= ( pr.getResult().getVerificationResult() ?  "SATISFIED" : "NOTSATISFIED" );
		
				ver.setResult(resultValue);
				ver.setResultMessage(pr.getResult().getVerificationResultMsg());
				ver.setVerificationTime(toXMLGregorianCalendar(pr.getResult().getVerificationTime()));
			
				policyToLoad.setVerificationResult(ver);
					//System.out.printf(policyToLoad.getVerificationResult().getResult()+"\n");
			}
			//CHECK IF POLICY ALREADY EXISTS ON THE SERVICE
			//IF YES OVERWRITE IT.
			if(interaction.performPUTPolicy(policyToLoad)==null){
				interaction.performPOSTpolicy(policyToLoad);	
			}
		}
		
		}catch (ProcessingException e1) {
			
		e1.printStackTrace();
		throw new ServiceException("System not well configured or error during JAX-RS sending POST request/request processing");	
				
		}catch (WebApplicationException  e2) {
				
		throw new ServiceException("Server returned some error");
			
		}catch (Exception e3) {
				
		e3.printStackTrace();
		throw new ServiceException("Unexpected exception");
		}								
	}
		

	@Override
	public void loadReachabilityPolicy(String name, String nffgName, boolean isPositive, String srcNodeName,
			String dstNodeName) throws UnknownNameException, ServiceException {
		
		boolean unknownSrcNode=true;
		boolean unknownDstNode=true;
		
		NffgReader nffgToRead=this.monitor.getNffg(nffgName);		
		
		if	(nffgToRead==null) 
		//System.out.println("Nffg requested does not exist locally");
			throw new UnknownNameException("---Nffg requested does not exist locally---\n");	
		
		Set<NodeReader> nodeReaderSet=nffgToRead.getNodes();
		
		for(NodeReader n:nodeReaderSet){
			if (n.getName().equals(srcNodeName))
				unknownSrcNode=false;
		}
		for(NodeReader n:nodeReaderSet){
			if (n.getName().equals(dstNodeName))
				unknownDstNode=false;
		}
	
		if(unknownSrcNode || unknownDstNode){
		//System.out.println("non riconosciuto srcNode"+srcNodeName+" o dstNode"+dstNodeName);
			throw new UnknownNameException();
		}
		
		PolicyLogicType logic=(isPositive ? PolicyLogicType.POSITIVE : PolicyLogicType.NEGATIVE);
		PolicyKindType kind=PolicyKindType.REACHABILITY;
		
		Policy policyToLoad=fillPolicyHeader(name,nffgName,kind,logic,srcNodeName,dstNodeName);

		try{
			
		//CHECK IF POLICY ALREADY EXISTS ON THE SERVICE
		//IF YES OVERWRITE IT.
		if(interaction.performPUTPolicy(policyToLoad)==null)
				interaction.performPOSTpolicy(policyToLoad);	
			
		} catch (ProcessingException e1) {
			
		e1.printStackTrace();
		throw new ServiceException("System not well configured or error during JAX-RS sending request/request processing");	
			
		}catch (WebApplicationException  e2) {
			
		throw new ServiceException("Server returned some error");
		
		}catch (Exception e3) {
			
		e3.printStackTrace();
		throw new ServiceException("Unexpected exception");
		}		
	}

	@Override
	public void unloadReachabilityPolicy(String name) throws UnknownNameException, ServiceException {
			
		try{
			
		interaction.performDELETEpolicy(name);
			
		}catch(UnknownNameException e){
			
		throw new UnknownNameException("the NFFG to which the policy refers is not a known nffg, or srcNodeName and dstNodeName are not both nodes belonging to the known NFFG ");
			
		}catch (ProcessingException e1) {
			
		e1.printStackTrace();
		throw new ServiceException("System not well configured or error during JAX-RS sending request/request processing");	
			
		}catch (WebApplicationException  e2) {
			
		throw new ServiceException("Server returned some error");
		
		}catch (Exception e3) {
			
		e3.printStackTrace();
		throw new ServiceException("Unexpected exception");
		}		
	}

	@Override
	public boolean testReachabilityPolicy(String name) throws UnknownNameException, ServiceException {
		
			try{
				
			Policy result=interaction.performGETVerification(name);
			return (result.getVerificationResult().getResult().equals("SATISFIED") ? true : false);
				
			}catch(UnknownNameException e){
				
				throw new UnknownNameException("the policy name passed as argument does not correspond to a reachability policy already loaded in the remote service");
				
			}catch (ProcessingException e1) {
				
				e1.printStackTrace();
				throw new ServiceException("System not well configured or error during JAX-RS sending request/request processing");	
				
			}catch (WebApplicationException  e2) {
				
				throw new ServiceException("Server returned some error");
			
			}catch (Exception e3) {
				
				e3.printStackTrace();
				throw new ServiceException("Unexpected exception");
			}
	}

	
	public static XMLGregorianCalendar toXMLGregorianCalendar(Calendar c) throws DatatypeConfigurationException{
		
		 GregorianCalendar gc = new GregorianCalendar();
		 gc.setTimeInMillis(c.getTimeInMillis());
		 XMLGregorianCalendar xc=null;
		 xc = DatatypeFactory.newInstance().newXMLGregorianCalendar(gc);
		 return xc;
	}
	
	public Policy fillPolicyHeader(String policyName, String nffg, PolicyKindType kind, PolicyLogicType logic, String srcNode,String dstNode){
		
		SrcNode src=new SrcNode();
		DstNode dst=new DstNode();
		Policy policyToLoad=new Policy();

		policyToLoad.setName(policyName);
		policyToLoad.setNffgRef(nffg);
		policyToLoad.setPolicyLogic(logic);
		policyToLoad.setPolicyKind(kind);
		policyToLoad.setSrcNode(src);
		policyToLoad.setDstNode(dst);	
		policyToLoad.getSrcNode().setName(srcNode);
		policyToLoad.getDstNode().setName(dstNode);
			
		return policyToLoad;		
	}
}
