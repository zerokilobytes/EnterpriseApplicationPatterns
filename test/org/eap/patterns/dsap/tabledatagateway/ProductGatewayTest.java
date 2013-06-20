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
		int result = gateway.addProduct(45646556, 1000.00, "Electric Stove", "Silver Electric Stove", false);
		
		assertTrue("Insert method for product must return the ID of the inserted product", result != -1);
	}
	
	@Test
	public void testFindAll() throws SQLException 
	{
		ProductGateway gateway = new ProductGateway();

		gateway.addProduct(0, 0.00, "Test Product", "First Product", true);
		Result<Product> products = gateway.findAll();

		assertNotNull("Find method must return products when proucts are added", products);
	}

	@Test
	public void testUpdateProduct() throws SQLException
	{
		ProductGateway gateway = new ProductGateway();
		int Id;
		boolean result;

		Id = gateway.addProduct(1001, 0.00, "Test Product", "First Product", true);
		result = gateway.updateProduct(Id, 1001, 999.99, "Television", "Flat Screen TV", true);

		assertTrue("UPDATE method for product must return true", result);
	}

	@Test
	public void testDeleteProduct() throws SQLException
	{
		ProductGateway gateway = new ProductGateway();
		int Id;
		boolean result;

		Id = gateway.addProduct(1001, 0.00, "Test Product", "Test product to be deleted", false);
		result = gateway.deleteProduct(Id);

		assertTrue("DELETE method for product must return true after existing product has been deleted", result);
	}
	
	@Test
	public void testFindByProductName() throws SQLException
	{
		ProductGateway gateway = new ProductGateway();

		gateway.addProduct(1001, 0.00, "Find Product", "Test product to be searched", false);
		Product product = gateway.findByProductName("Find Product").Items.get(0);

		assertNotNull("[FindByProductName()] method must return the product searched when the product exists", product);
	}
	
	@Test
	public void testFindByProductNameWhenProductNotExists() throws SQLException
	{
		ProductGateway gateway = new ProductGateway();

		gateway.addProduct(1001, 0.00, "Existing Product", "Test product to be searched", false);
		Product product = gateway.findByProductName("Not Exists Product").Items.get(0);

		assertNull("[FindByProductName()] method must not return the product searched when the product does not exists", product);
	}
}