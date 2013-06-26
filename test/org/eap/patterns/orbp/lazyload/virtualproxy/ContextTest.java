package org.eap.patterns.orbp.lazyload.virtualproxy;

import static org.junit.Assert.*;

import org.eap.dao.datasource.DB;
import org.eap.dao.datasource.SQLite;
import org.eap.dao.domainobject.Product;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ContextTest
{
	@Before
	public void setUp() throws Exception 
	{
		DB.setDataSource(new SQLite());
	}

	@After
	public void tearDown() 
	{
		DB.closeConnection();
	}

	@Test
	public void testVirtualLoad() throws Exception
	{
		int productID = ProductMapper.insert(111, 99.99, "Virtual Product", "Virtual Product Test", false);

		Context.Product products = new Context.Product();
		VirtualList<Product> result = products.getProductsByID(productID);

		Product product = result.getFirst();

		assertNotNull("Product found must not be null", product);
	}
}