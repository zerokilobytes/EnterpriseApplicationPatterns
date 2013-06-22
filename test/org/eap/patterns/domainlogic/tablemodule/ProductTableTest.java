package org.eap.patterns.domainlogic.tablemodule;

import static org.junit.Assert.*;
import java.sql.SQLException;
import org.junit.*;

import org.eap.dao.DataSet;
import org.eap.dao.datasource.DB;
import org.eap.dao.datasource.SQLite;
import org.eap.patterns.dsap.tabledatagateway.ProductGateway;

public class ProductTableTest
{
	@Before
	public void setUp() {
		DB.setDataSource(new SQLite());
	}
	
	@After
	public void teaDown() 
	{
		DB.closeConnection();
	}

	@Test
	public void testGetCostOfProduct() throws SQLException
	{
		ProductGateway gateway 	= new ProductGateway();
		DataSet ds 				= null;
		ProductTable product 	= null;

		Double cost 			= null;
		Integer productID 		= null;

		ds = new DataSet();
		productID = gateway.addProduct(1001, 99.99, "Orange", "First Product", true);

		ds.addTable("Product", gateway.findAll());
		product = new ProductTable(ds);
		cost = product.getCostOfProduct(productID);

		assertEquals("The cost of the product must be correct", cost, Double.valueOf(99.99));
	}
}