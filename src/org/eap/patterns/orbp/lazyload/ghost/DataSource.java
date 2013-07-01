package org.eap.patterns.orbp.lazyload.ghost;

public interface DataSource
{
	void load (DomainObject obj) throws Exception;
}