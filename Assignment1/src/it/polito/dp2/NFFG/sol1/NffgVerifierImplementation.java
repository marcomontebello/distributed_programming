package it.polito.dp2.NFFG.sol1;

import java.io.File;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.UnmarshalException;
import javax.xml.bind.Unmarshaller;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;

import org.xml.sax.SAXException;

import it.polito.dp2.NFFG.NffgReader;
import it.polito.dp2.NFFG.NffgVerifier;
import it.polito.dp2.NFFG.NffgVerifierException;
import it.polito.dp2.NFFG.PolicyReader;

import it.polito.dp2.NFFG.sol1.jaxb.NetworkServices;
import it.polito.dp2.NFFG.sol1.jaxb.NffgType;
import it.polito.dp2.NFFG.sol1.jaxb.Policy;
import it.polito.dp2.NFFG.sol1.jaxb.PolicyKindType;


public class NffgVerifierImplementation implements NffgVerifier  {
	
	private NetworkServices ns;
	private HashSet<PolicyReaderImplementation> policySet;
	private HashSet<NffgReaderImplementation> nffgSet;
	
	public NffgVerifierImplementation() throws NffgVerifierException{
		
	    String xmlInputFile = System.getProperty("it.polito.dp2.NFFG.sol1.NffgInfo.file");
		nffgSet=new HashSet<NffgReaderImplementation>();
		policySet=new HashSet<PolicyReaderImplementation>();
		
		try{ 
			
			JAXBContext jc = JAXBContext.newInstance( "it.polito.dp2.NFFG.sol1.jaxb" );
			Unmarshaller u = jc.createUnmarshaller();
		
			SchemaFactory sf = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
			Schema schema = sf.newSchema(new File("./xsd/nffgInfo.xsd"));
			
            u.setSchema(schema);
 
		    this.ns=  (NetworkServices) u.unmarshal( new File(xmlInputFile) );
		    fill(ns);
		  	    
		  } catch(SAXException se) {
	          System.out.println("Unable to validate due to following error.");
	          se.printStackTrace();
	          
	      }catch(UnmarshalException ue) {	  
			  
		      System.out.println( "Caught UnmarshalException" );
		      ue.printStackTrace();
		      
		  } catch( JAXBException je ) {
			  
		      System.out.println( "JAXB Exception caught" );
		      je.printStackTrace();
		      
		  }	catch( ClassCastException cce ) {
			  
		      System.out.println( "Problem occured during Casting " );
		      cce.printStackTrace();	      
		  }	
	}
	
	public void fill(NetworkServices ns) throws NffgVerifierException {
		
		List<NffgType> nffgList=ns.getNffg();
		List<Policy> policyList=ns.getPolicy();
	
		if (nffgList!=null)
			for(NffgType actualNffg : nffgList)
				nffgSet.add(new NffgReaderImplementation(actualNffg));		
		
		if (policyList!= null)	{	
			
			
			for(Policy actualPolicy: policyList){
				NffgType policyNffg=null;
				for(NffgType actualNffg : nffgList){
					if(actualNffg.getName().equals(actualPolicy.getNffgRef()))
						policyNffg=actualNffg;	
				}
				if(actualPolicy.getPolicyKind()==PolicyKindType.TRAVERSAL)
					policySet.add(new TraversalPolicyReaderImplementation(actualPolicy,policyNffg));
										
				else policySet.add(new ReachabilityPolicyReaderImplementation(actualPolicy,policyNffg));	
			}
		}
	}

	@Override
	public NffgReader getNffg(String nameOfnffg) {
					
		Iterator<NffgReaderImplementation> i = nffgSet.iterator();
		
		while (i.hasNext()) {
			NffgReaderImplementation actual_nffg = i.next();
			if (actual_nffg.getName().equals(nameOfnffg))
				return actual_nffg;		
		}
		return null;		
	}

	@Override
	public Set<NffgReader> getNffgs()  {
		
		if (nffgSet != null)
			return (HashSet<NffgReader>) new HashSet<NffgReader>(nffgSet);
		
		return null;
	}

	@Override
	public Set<PolicyReader> getPolicies() {
		
		if (nffgSet != null)
				return (HashSet<PolicyReader>) new HashSet<PolicyReader>(policySet);
		
		return  null;
	}

	@Override
	public Set<PolicyReader> getPolicies(String nameOfNffg) {

		HashSet<PolicyReaderImplementation> policySetToReturn= new HashSet<PolicyReaderImplementation>();
		
		for(PolicyReaderImplementation policy: policySet){			
			if (policy.getNffg().getName().equals(nameOfNffg))			
				policySetToReturn.add(policy);
		}	
		if (policySetToReturn.size()==0)	
			return null;
		
		return (HashSet<PolicyReader>) new HashSet<PolicyReader>(policySetToReturn);		
	}

	@Override
	public Set<PolicyReader> getPolicies(Calendar arg0) {

		HashSet<PolicyReaderImplementation> policySetToReturn= new HashSet<PolicyReaderImplementation>();	
		
		for(PolicyReaderImplementation policy: policySet){
			
		try {
				
			if (policy.getResult().getVerificationTime().compareTo(arg0) > 0)
				policySetToReturn.add(policy);
				
			} catch (NullPointerException e) {	
			e.printStackTrace();		
			}catch (IllegalArgumentException e) {		
			e.printStackTrace();
			}	
		}
		
		if (policySetToReturn.size()==0)	
				return null;
		
		return (HashSet<PolicyReader>) new HashSet<PolicyReader>(policySetToReturn);
	
	}
	
	
	public PolicyReader getPolicy(String nameOfPolicy) {
				
		Iterator<PolicyReaderImplementation> i = policySet.iterator();
		while (i.hasNext()) {
				
			PolicyReaderImplementation actual_policy = i.next();
				
			if (actual_policy.getName().equals(nameOfPolicy))
				return actual_policy;
			}		
		return null;		
	}
}