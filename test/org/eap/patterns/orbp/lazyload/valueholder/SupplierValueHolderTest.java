package org.eap.patterns.orbp.lazyload.valueholder;

import static org.junit.Assert.*;

import java.sql.SQLException;

import org.eap.dao.datasource.DB;
import org.eap.dao.datasource.SQLite;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class SupplierValueHolderTest
{
	@Before
	public void setUp() 
	{
		DB.setDataSource(new SQLite());
	}

	@After
	public void tearDown() 
	{
		DB.closeConnection();
	}

	@Test
	public void testGetSuppliers() throws SQLException
	{
		SupplierMapper mapper = new SupplierMapper();
		int supplierID = mapper.insert("Test supplier");

		Supplier supplier = new Supplier(supplierID);
		org.eap.dao.domainobject.Supplier supplierValue = supplier.getSupplier();
		
		assertNotNull("Supplier must not be empty", supplierValue);
	}
}