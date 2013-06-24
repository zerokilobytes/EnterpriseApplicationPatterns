package org.eap.patterns.orbp.identitymap;

import static org.junit.Assert.*;

import org.eap.dao.datasource.DB;
import org.eap.dao.datasource.SQLite;
import org.eap.patterns.orbp.identitymap.IdentityMap.Product;
import org.eap.patterns.orbp.identitymap.IdentityMap.Supplier;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class IdentityMapTest 
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
	public void testIdentityMapForFindingInsertedProduct() throws Exception
	{
		IdentityMap.Product product = new IdentityMap.Product();
		product.ProductName = "Identity Product";
		product.ProductDescription = "Identity Product Description";
		product.InStock = false;
		Product.addProduct(product);

		IdentityMap.Product productFound = Product.getProduct(product.ProductID);
		assertNotNull("Product must not be null", productFound);
	}

	@Test
	public void testIdentityMapForFindingNonExistingProduct() throws Exception
	{
		IdentityMap.Product productFound = Product.getProduct(0);
		assertNull("Product must be null", productFound);
	}

	@Test
	public void testIdentityMapForFindingInsertedSupplier() throws Exception
	{
		IdentityMap.Supplier supplier = new IdentityMap.Supplier();
		supplier.SupplierName = "Identity Product";
		Supplier.addSupplier(supplier);

		IdentityMap.Supplier supplierFound = Supplier.getSupplier(supplier.SupplierID);
		assertNotNull("Supplier must not be null", supplierFound);
	}

	@Test
	public void testIdentityMapForFindingNonExistingSupplier() throws Exception
	{
		IdentityMap.Supplier supplierFound = Supplier.getSupplier(0);
		assertNull("Supplier must be null", supplierFound);
	}
}