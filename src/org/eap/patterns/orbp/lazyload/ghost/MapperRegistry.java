package org.eap.patterns.orbp.lazyload.ghost;

import java.util.Hashtable;
import java.util.Map;

public class MapperRegistry implements DataSource
{
	protected static Map<Object, Object> mappers = new Hashtable<Object, Object>();

	@Override
	public void load(DomainObject obj) throws Exception 
	{
		 Mapper(obj.getClass().getName()).load(obj);
	}

	@SuppressWarnings("unchecked")
	public static <T> Mapper<T> Mapper(Object obj)
	{
		return (Mapper<T>) MapperRegistry.mappers.get(obj);
	}
}