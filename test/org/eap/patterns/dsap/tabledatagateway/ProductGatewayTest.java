package org.eap.patterns.dsap.tabledatagateway;

import static org.junit.Assert.*;

import java.sql.SQLException;

import org.eap.dao.Result;
import org.eap.dao.businessobject.Product;
import org.eap.dao.datasource.DB;
import org.eap.dao.datasource.SQLite;
import org.junit.Before;
import org.junit.Test;

public class ProductGatewayTest {
	@Before
	public void setUp() {
		DB.setDataSource(new SQLite());
	}
	
	@Test
	public void testAddProduct() throws SQLException
	{
		ProductGateway gateway = new ProductGateway();
		boolean result = gateway.addProduct(45646556, 1000.00, "Electric Stove", "Silver Electric Stove", true);
		
		assertTrue("Insert method for product must return true", result);
	}
	
	@Test
	public void testFindAll() throws SQLException 
	{
		ProductGateway gateway = new ProductGateway();
		
		gateway.addProduct(0, 0.00, "Test Product", "First Product", true);
		Result<Product> products = gateway.findAll();
		
		assertNotNull("Find method must return products when proucts are added", products);
	}


}
