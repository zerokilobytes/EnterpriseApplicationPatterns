package org.eap.patterns.orbp.lazyload.lazyinitialization;

import static org.junit.Assert.*;

import java.sql.SQLException;

import org.eap.dao.DataTable;
import org.eap.dao.datasource.DB;
import org.eap.dao.datasource.SQLite;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ProductTest
{
	@Before
	public void setUp() 
	{
		DB.setDataSource(new SQLite());
		DB.executeQuery("INSERT INTO Product (SupplierID, Price, ProductName, ProductDescription, Instock) VALUES (111,0,'Test Product','Test Product', 0)");
	}

	@After
	public void tearDown() 
	{
		DB.closeConnection();
	}

	@Test
	public void testLazyInitialization() throws SQLException
	{
		Product product = new Product();
		DataTable<Product> products = product.getProductsBySupplier(111);

		assertTrue("Products must be loaded", products.size() > 0);
	}
}