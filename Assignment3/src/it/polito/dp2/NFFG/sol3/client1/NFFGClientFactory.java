package it.polito.dp2.NFFG.sol3.client1;

import it.polito.dp2.NFFG.lab3.NFFGClient;
import it.polito.dp2.NFFG.lab3.NFFGClientException;
import it.polito.dp2.NFFG.sol3.client1.NFFGClientLibrary;

public class NFFGClientFactory extends it.polito.dp2.NFFG.lab3.NFFGClientFactory {

	@Override
	public NFFGClient newNFFGClient() throws NFFGClientException {
				
			return new NFFGClientLibrary() ;			
	}
}
