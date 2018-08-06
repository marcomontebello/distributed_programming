package it.polito.dp2.NFFG.sol1;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NamedEntityReaderImplementation implements it.polito.dp2.NFFG.NamedEntityReader{
	
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
