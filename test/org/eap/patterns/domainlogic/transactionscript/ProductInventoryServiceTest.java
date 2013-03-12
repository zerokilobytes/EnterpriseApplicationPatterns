package org.eap.patterns.domainlogic.transactionscript;

import static org.junit.Assert.*;

import org.eap.dao.datasource.Mock;
import org.junit.Before;
import org.junit.Test;

public class ProductInventoryServiceTest {
	int testSupplierID = 2535;

	@Before
	public void setUp() {
		Mock.clearProducts();

		Mock.addProduct(1, testSupplierID, 950.00, "Electric Stove", "Silver Electric Stove", true);
		Mock.addProduct(2, testSupplierID, 950.00, "Electric Stove", "White Electric Stove", true);
	}

	@Test
	public void testCalculateCostOfProductsBySupplier() {
		double cost = 0.00;

		// Create instance of gate way
		Gateway gateway = new ProductGateway();

		ProductInventoryService service = new ProductInventoryService(gateway);
		try {
			cost = service.calculateCostOfProductsBySupplier(testSupplierID);
		} catch (Exception e) {
			e.printStackTrace();
		}

		assertEquals(1900.00, cost, 0);
	}
	
	@Test
	public void testgetProductNameByProductID()
	{
		int productID = 1;
		String productName = null;
		
		// Create instance of gate way
		Gateway gateway = new ProductGateway();
		
		ProductInventoryService service = new ProductInventoryService(gateway);
		try {
			productName = service.getProductNameByProductID(productID);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		assertEquals("Electric Stove", productName);
	}
}
