package org.eap.patterns.dsap.datamapper;

import java.util.HashMap;
import java.util.Map;

public abstract class AbstractMapper<T>
{
	protected Map<Integer, T> loadedMap = new HashMap<Integer, T>();
	
	protected T get(int id) throws Exception
	{
		T result = loadedMap.get(id);
	      if (result != null)
	    	  return result;
	      else
	    	  return find(id);
	}
	abstract protected T find(int id) throws Exception;
}