package org.eap.patterns.domainlogic.transactionscript;

import static org.junit.Assert.*;

import java.sql.SQLException;

import org.eap.dao.datasource.DB;
import org.eap.dao.datasource.SQLite;
import org.eap.patterns.dsap.rowdatagateway.ProductGateway;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ProductInventoryServiceTest {
	Integer testSupplierID = 2535;

	@Before
	public void setUp() throws SQLException {
		DB.setDataSource(new SQLite());

		ProductGateway.insert(testSupplierID, 950.00, "Electric Stove", "Silver Electric Stove", true);
		ProductGateway.insert(testSupplierID, 950.00, "Electric Stove", "White Electric Stove", true);
	}
	
	@After
	public void tearDown() 
	{
		DB.closeConnection();
	}

	@Test
	public void testCalculateCostOfProductsBySupplier() throws Exception
	{
		double cost = 0.00;
		ProductService service = new ProductInventoryService();
		cost = service.calculateCostOfProductsBySupplier(testSupplierID);

		assertEquals("The cost of the products must be correct", 1900.00, cost, 0);
	}
}