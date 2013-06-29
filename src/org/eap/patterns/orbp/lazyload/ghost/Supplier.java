package org.eap.patterns.orbp.lazyload.ghost;


public class Supplier extends DomainObject
{
	String name;

	public Supplier(Integer key)
	{
		super(key);
	}
	public String getName() throws Exception
	{
		load();
		return name;
	}
	
	
	public void setName(String name) throws Exception
	{
		load();
		this.name = name;
	}

	@Override
	protected void load() throws Exception
	{
		if (isGhost())
		{
			//SupplierMapper mapper = new SupplierMapper();
			//mapper.findSupplier(key);
		}
	}
}