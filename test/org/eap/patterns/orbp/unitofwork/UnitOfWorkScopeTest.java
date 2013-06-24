package org.eap.patterns.orbp.unitofwork;

import static org.junit.Assert.*;

import java.sql.SQLException;

import org.eap.dao.datasource.DB;
import org.eap.dao.datasource.SQLite;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class UnitOfWorkScopeTest {

	@Before
	public void setUp() {
		DB.setDataSource(new SQLite());
	}
	
	@After
	public void tearDown() 
	{
		DB.closeConnection();
	}

	@Test
	public void testUnitOfWorkScopeForNewRecordComitted() throws SQLException
	{
		UnitOfWorkScope.newCurrent();
		Supplier newSupplier = new Supplier("General Supplier");
		newSupplier.setSupplierName("Electronic Supplier");
		UnitOfWorkScope.getCurrent().commit();

		Supplier findSupplier = Supplier.find(newSupplier.getSupplierID());
		assertTrue("The supplier comitted must be the same as the supplier found", newSupplier.getSupplierID() == findSupplier.getSupplierID());
	}
	
	@Test
	public void testUnitOfWorkScopeForRecordDeleted() throws SQLException
	{
		// Part 1
		UnitOfWorkScope.newCurrent();
		Supplier newSupplier = new Supplier("General Supplier");
		UnitOfWorkScope.getCurrent().commit();

		// Part 2
		UnitOfWorkScope.newCurrent();
		Supplier findSupplier = Supplier.find(newSupplier.getSupplierID());
		findSupplier.delete();
		UnitOfWorkScope.getCurrent().commit();

		Supplier findeleteSupplier = Supplier.find(newSupplier.getSupplierID());
		assertNull("The deleted supplier must not be found in the datasource", findeleteSupplier);
	}
	
	@Test
	public void testUnitOfWorkScopeForUpatedRecordComitted() throws SQLException
	{
		UnitOfWorkScope.newCurrent();
		Supplier newSupplier = new Supplier("General Supplier");
		UnitOfWorkScope.getCurrent().commit();

		UnitOfWorkScope.newCurrent();
		Supplier supplierToUpdate = Supplier.find(newSupplier.getSupplierID());
		supplierToUpdate.setSupplierName("Electronic Supplier");
		UnitOfWorkScope.getCurrent().commit();

		Supplier updatedSupplier = Supplier.find(supplierToUpdate.getSupplierID());

		assertEquals("The supplier name upatedd must be correct", updatedSupplier.getSupplierName(), "Electronic Supplier");
	}
}