package org.eap.patterns.dsap.datamapper;

import java.util.HashMap;
import java.util.Map;

public abstract class AbstractMapper 
{
	@SuppressWarnings("rawtypes")
	protected Map loadedMap = new HashMap();
	
	protected Object get(int id) throws Exception
	{
		Object result = loadedMap.get(id);
	      if (result != null)
	    	  return result;
	      else
	    	  return find(id);
	}
	abstract protected Object find(int id) throws Exception;
}