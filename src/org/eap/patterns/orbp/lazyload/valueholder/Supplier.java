package org.eap.patterns.orbp.lazyload.valueholder;

import java.sql.SQLException;

public class Supplier
{
	private ValueHolder<org.eap.dao.domainobject.Supplier> suppliers;
	
	public Supplier(int supplierID)
	{
		ValueLoader<org.eap.dao.domainobject.Supplier> loader = new SupplierLoader(supplierID);
		suppliers = new ValueHolder<org.eap.dao.domainobject.Supplier>(loader); 
	}

	public org.eap.dao.domainobject.Supplier getSupplier() throws SQLException
	{
		return suppliers.getValue().getRows().get(0);
	}
}