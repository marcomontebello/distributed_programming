package it.polito.dp2.NFFG.sol3.service;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class NffgsCache {

	private static Nffgs nffgsCache = new Nffgs();
	private static Map<String ,Map<String,String>> nameIdMap = new ConcurrentHashMap<String , Map<String,String>>();

	public static Nffgs getCache(){
		
		return nffgsCache;	
	}

	public static Map<String ,Map<String,String>> getMappingDB(){
		
		return nameIdMap;	
	}

	public static void addToNffgsCahce(Nffg nffg) throws NffgServiceException{
		
	try {	
		
		nffgsCache.getNffg().add(nffg);
			
		} catch (Exception e) {

		e.printStackTrace();	
		throw new NffgServiceException();
		}	
	}

	public static Nffg getNFFG(String name){
		
		for(Nffg nffg: nffgsCache.getNffg()){		
			if(nffg.getName().equals(name))
				return nffg;
		}	
		return null;			
	}

	public static boolean contains(String nffgName){
		
		for(Nffg nffg: nffgsCache.getNffg()){			
			if(nffg.getName().equals(nffgName))
				return true;				
		}		
		return false;
	}
	
	public static String getID(String nffg, String node) throws NffgServiceException{
		
		try {
			
		return nameIdMap.get(nffg).get(node);
			
		} catch (Exception e) {
		
		e.printStackTrace();
		throw new NffgServiceException();
		}				
	}
	
	public static void addID(String nffg,HashMap<String,String> map ) throws NffgServiceException{
		
		try {
			
		nameIdMap.put(nffg, map);
			
		} catch (Exception e) {

		e.printStackTrace();	
		throw new NffgServiceException();
		}		
	}
}
