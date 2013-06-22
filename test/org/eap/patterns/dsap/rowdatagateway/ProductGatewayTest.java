package org.eap.patterns.dsap.rowdatagateway;

import static org.junit.Assert.*;
import java.sql.SQLException;

import org.eap.dao.datasource.DB;
import org.eap.dao.datasource.SQLite;
import org.eap.patterns.dsap.rowdatagateway.ProductGateway;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ProductGatewayTest 
{
	@Before
	public void setUp() 
	{
		DB.setDataSource(new SQLite());
	}
	
	@After
	public void teaDown() 
	{
		DB.closeConnection();
	}

	@Test
	public void testDelete() throws SQLException
	{
		int productId = ProductGateway.insert(1001, 0.00, "Test Product", "Test product to be deleted", false);
		ProductGateway gateway = ProductGateway.find(productId);
		boolean result = gateway.delete();

		assertTrue("DELETE method for product must return true after existing product has been deleted", result);
	}

	@Test
	public void testUpdate() throws SQLException 
	{
		int productID = ProductGateway.insert(1001, 0.00, "Test Product", "Test Product for updating", true);
		ProductGateway gateway = ProductGateway.find(productID);
		
		gateway.setProductName("Updated Product");
		gateway.setProductDescription("Test Product that has been updated");

		boolean result = gateway.update();
		assertTrue("UPDATE method for product must return true", result);
	}

	@Test
	public void testFind() throws SQLException
	{
		int productID = ProductGateway.insert(1001, 0.00, "Sample Product", "Test Product for finding", true);
		ProductGateway gateway = ProductGateway.find(productID);

		assertEquals("[find()] method must return the correct product searched when the product exists", gateway.getProductName(), "Sample Product");
	}

	@Test
	public void testInsert() throws SQLException
	{
		int productID = ProductGateway.insert(1001, 0.00, "Sample Product", "Test Product for finding", true);
		assertTrue("Insert method for product must return the ID of the inserted product", productID > 0);
	}

	@Test
	public void testFindAll() throws SQLException {
		ProductGateway.insert(1001, 0.00, "Sample Product", "Test Product for finding", true);
		ProductGateway[] products = ProductGateway.findAll();

		assertNotNull("[findAll()] method must return an array of products when products exists", products);
	}
}