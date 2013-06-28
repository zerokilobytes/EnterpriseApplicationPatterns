package org.eap.patterns.orbp.lazyload.valueholder;

import java.sql.SQLException;

import org.eap.dao.DataTable;

public class SupplierLoader implements ValueLoader<org.eap.dao.domainobject.Supplier>
{
	private Integer supplierID;
	public SupplierLoader(Integer supplierID)
	{
		this.supplierID = supplierID;
	}

	public DataTable<org.eap.dao.domainobject.Supplier> load() throws SQLException
	{
		SupplierMapper supplier = new SupplierMapper();
		return supplier.find(supplierID);
	}
}