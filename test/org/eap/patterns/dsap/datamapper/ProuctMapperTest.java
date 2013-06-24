package org.eap.patterns.dsap.datamapper;

import static org.junit.Assert.*;

import org.eap.dao.datasource.DB;
import org.eap.dao.datasource.SQLite;
import org.eap.dao.domainobject.Product;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ProuctMapperTest {

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
	public void testFindProuct() throws Exception 
	{
		Product product = new Product();
		product.SupplierID = 1;
		product.Price = 99.99;
		product.ProductName = "Sample Product";
		product.ProductDescription = "Sample product for testing";
		product.InStock = false;

		ProuctMapper mapper = new ProuctMapper();
		int productID = mapper.insert(product);

		Product productFound = mapper.findProuct(productID);
		assertTrue("Prroduct found must not be the producted that was inserted", productFound.ProductID == productID);
	}
}
