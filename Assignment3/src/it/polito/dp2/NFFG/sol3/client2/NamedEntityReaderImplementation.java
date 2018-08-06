package it.polito.dp2.NFFG.sol3.client2;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import it.polito.dp2.NFFG.NamedEntityReader;
public class NamedEntityReaderImplementation implements NamedEntityReader{
	
	protected String name;
	

	@Override
	public String getName() {
		
		Pattern p = Pattern.compile("[A-Za-z][A-Za-z0-9]+$");
		Matcher m = p.matcher(name);

		if (m.matches())
			return this.name;
		
		return null;
	}

}
