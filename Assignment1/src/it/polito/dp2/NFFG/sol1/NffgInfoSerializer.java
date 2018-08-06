package it.polito.dp2.NFFG.sol1;

import java.io.File;
import java.math.BigInteger;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Set;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;

import org.xml.sax.SAXException;

import it.polito.dp2.NFFG.FactoryConfigurationError;
import it.polito.dp2.NFFG.FunctionalType;
import it.polito.dp2.NFFG.LinkReader;
import it.polito.dp2.NFFG.NffgReader;
import it.polito.dp2.NFFG.NffgVerifier;
import it.polito.dp2.NFFG.NffgVerifierException;
import it.polito.dp2.NFFG.NffgVerifierFactory;
import it.polito.dp2.NFFG.NodeReader;
import it.polito.dp2.NFFG.PolicyReader;
import it.polito.dp2.NFFG.TraversalPolicyReader;
import it.polito.dp2.NFFG.ReachabilityPolicyReader;

import it.polito.dp2.NFFG.VerificationResultReader;
import it.polito.dp2.NFFG.sol1.jaxb.* ;


public class NffgInfoSerializer {

	private NffgVerifier monitor;
	private ObjectFactory factory;
	
	public NffgInfoSerializer() throws NffgVerifierException,FactoryConfigurationError {
		
		NffgVerifierFactory verifierFactory=NffgVerifierFactory.newInstance();
		this.monitor = verifierFactory.newNffgVerifier();
		this.factory= new ObjectFactory();				
	}
	
	public static void main(String[] args) {

		if (args.length!=1){			
			printUsage();
			System.exit(0);			
		}
		String output_file=args[0];
		NffgInfoSerializer wf;
			
		try {	
			
		wf = new NffgInfoSerializer();
		wf.run(output_file);
				
		} catch (FactoryConfigurationError e) {
			
		System.err.println("Could not instantiate data generator due to FactoryConfigurationError.");
		e.printStackTrace();
		System.exit(1);
			
		}catch (NffgVerifierException e) {
				
		System.err.println("Could not instantiate data generator.");
		e.printStackTrace();
		System.exit(1);					
		}		
	}
	
	public void run(String outputXMLFile){
		
		NetworkServices ns=factory.createNetworkServices();
		SchemaFactory sf = null;
		Schema schema = null;
		JAXBContext ctx = null;
		Marshaller marshaller = null;
		
		/*processing data from monitor*/
		
		bindNffgs(ns);
		bindPolicies(ns);
		
		/*---------end of processing data retrieved from the random-generator--------*/
		/*Creating a JAXB CONTEXT to provide a Marshaller and marshal data processed */	
		try {
			
		sf = SchemaFactory.newInstance(javax.xml.XMLConstants.W3C_XML_SCHEMA_NS_URI);
		schema = sf.newSchema(new File("./xsd/nffgInfo.xsd"));
		ctx = JAXBContext.newInstance("it.polito.dp2.NFFG.sol1.jaxb");
		marshaller  = ctx.createMarshaller();
		marshaller.setSchema(schema);
	    marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
	    marshaller.marshal(ns, new File(outputXMLFile));
				
		} catch (JAXBException e) {
	        	
        System.out.println("ERROR: unable to create marshaller. Exception follows.");
        System.out.println(e);
        System.exit(2);
	            
	    } catch (SAXException e) {
				
	    System.out.println("WARNING: invalid schema file, unable to validate.");
		e.printStackTrace();
        System.exit(2);        
	    } 	
	    System.out.println("-DONE-");	
	}

	private void bindNffgs(NetworkServices ns) {
		
		// Get the list of NFFGs from monitor
		Set<NffgReader> set = monitor.getNffgs();
		
		for (NffgReader nr: set) {
			
			NffgType nffg=createNffg(nr);
			ns.getNffg().add(nffg);			
		}		
	}

	private NffgType createNffg(NffgReader nffg_r) throws FactoryConfigurationError  {
			
		NffgType nffg=factory.createNffgType();	
		Set<NodeReader> nodeSet = nffg_r.getNodes();
		
		nffg.setName(nffg_r.getName());
		nffg.setNumberOfNodes(BigInteger.valueOf(nffg_r.getNodes().size()));
			
		try {
				
		nffg.setUpdateTime(toXMLGregorianCalendar(nffg_r.getUpdateTime()));
			
		} catch (DatatypeConfigurationException e) {

		e.printStackTrace();
		System.exit(2);		
		}
		
		for (NodeReader node_r: nodeSet)
			nffg.getNode().add(createNodeType(node_r));
		
	return nffg;	
	}

	private NodeType createNodeType(NodeReader node_r) {
			
		NodeType node=factory.createNodeType();
		Set<LinkReader> linkSet= node_r.getLinks();
			
		node.setName(node_r.getName());
		node.setFunction(FunctionType.fromValue(node_r.getFuncType().toString()));
		node.setNumberOfLinks(BigInteger.valueOf(node_r.getLinks().size()));
			
		for(LinkReader link_r : linkSet)	
			node.getLinkRef().add(createLinkType(link_r));
						
		return node;		
	}
	
	private LinkType createLinkType(LinkReader link_r) {
	
		LinkType linkref=factory.createLinkType();		
		linkref.setName(link_r.getName());
		linkref.setSrcNode(link_r.getSourceNode().getName());
		linkref.setDstNode(link_r.getDestinationNode().getName());
		return linkref;	
	}

	private void bindPolicies(NetworkServices ns) {	

		// Get the list of policies
		Set<PolicyReader> set = monitor.getPolicies();	
		// For each policy print related data
		for (PolicyReader pr: set) 
				ns.getPolicy().add(createPolicy(pr));	
	}

	private Policy createPolicy(PolicyReader p){
		
		//CHECK OF POLICY CONSTRAINT NOT CONTROLLED BY SCHEMA!//
		
		NffgReader nffg=p.getNffg();
		if(	nffg.getNode(((ReachabilityPolicyReader) p).getSourceNode().getName())==null
			|| nffg.getNode(((ReachabilityPolicyReader) p).getDestinationNode().getName())==null)
			
			return null;
		//-------END CONTROL--------//
		Policy policy=factory.createPolicy();
		NodeRef srcNode=factory.createNodeRef();
		NodeRef dstNode=factory.createNodeRef();
					
		policy.setName(p.getName());
		policy.setNffgRef(p.getNffg().getName());
		policy.setPolicyLogic((p.isPositive()==true) ? PolicyLogicType.POSITIVE : PolicyLogicType.NEGATIVE );
		policy.setVerificationResult(createVerificationResult(p.getResult()));
			
		srcNode.setName(((ReachabilityPolicyReader) p).getSourceNode().getName());
		dstNode.setName(((ReachabilityPolicyReader) p).getDestinationNode().getName());	
			
		policy.setSrcNode(srcNode);
		policy.setDstNode(dstNode);
			
		if (p instanceof ReachabilityPolicyReader)		
			policy.setPolicyKind(PolicyKindType.REACHABILITY);
				
			
		if (p instanceof TraversalPolicyReader){							
			Set<FunctionalType> FunctionSet = ((TraversalPolicyReader) p).getTraversedFuctionalTypes();	
			policy.setPolicyKind(PolicyKindType.TRAVERSAL);
		
			for (FunctionalType f: FunctionSet)
					policy.getTraversedFuncType().add(FunctionType.fromValue(f.toString()));						
		}					
		return policy;	
	}

	private VerificationresultType createVerificationResult(VerificationResultReader result) {
		
		VerificationresultType resultType=factory.createVerificationresultType();		
		if (result == null) {
			
			resultType.setResult(ResultType.UNVERIFIED);
			resultType.setResultMessage("No verification result for policy");
			resultType.setVerificationTime(null);
			return resultType;
		}
			
		if (result.getVerificationResult())
			resultType.setResult(ResultType.SATISFIED);
			
		else
			resultType.setResult(ResultType.NOTSATISFIED);
			
		resultType.setResultMessage(result.getVerificationResultMsg());
					
		try {	
		
			resultType.setVerificationTime(toXMLGregorianCalendar(result.getVerificationTime()));
				
		} catch (DatatypeConfigurationException e) {

		e.printStackTrace();
		System.exit(2);
		}
		
		return resultType;		
	}

	private static void printUsage() {	
		
		System.out.println("Usage:" +NffgInfoSerializer.class.getName()+ "<output file>");		
	}

	public static XMLGregorianCalendar toXMLGregorianCalendar(Calendar c) throws DatatypeConfigurationException {
		
		 GregorianCalendar gc = new GregorianCalendar();
		 gc.setTimeInMillis(c.getTimeInMillis());
		 XMLGregorianCalendar xc = DatatypeFactory.newInstance().newXMLGregorianCalendar(gc);
		 return xc;			 
	}	
}
