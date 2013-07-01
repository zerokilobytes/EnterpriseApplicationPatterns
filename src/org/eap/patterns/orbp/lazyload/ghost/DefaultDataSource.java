package org.eap.patterns.orbp.lazyload.ghost;

public class DefaultDataSource implements DataSource
{
	public void load(DomainObject obj) throws Exception
	{
		(new MapperRegistry()).load(obj);
	}
}