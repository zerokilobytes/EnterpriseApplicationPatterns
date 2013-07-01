package org.eap.patterns.orbp.lazyload.ghost;


public class Supplier extends DomainObject
{
	String SupplierName;

	public Supplier(Integer supplierID)
	{
		super(supplierID);
	}
	public String getName() throws Exception
	{
		load();
		return SupplierName;
	}

	public void setName(String supplierName) throws Exception
	{
		load();
		this.SupplierName = supplierName;
	}

	@Override
	protected void load() throws Exception
	{
		if (isGhost())
		{
			(new DefaultDataSource()).load(this);
		}
	}
}