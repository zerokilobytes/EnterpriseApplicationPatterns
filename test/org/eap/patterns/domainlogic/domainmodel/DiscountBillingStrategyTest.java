package org.eap.patterns.domainlogic.domainmodel;

import static org.junit.Assert.*;

import java.util.Date;

import org.eap.dao.datasource.Mock;
import org.junit.Before;
import org.junit.Test;

public class DiscountBillingStrategyTest {
	int testCustomerID = 1; 
	@Before
	public void setUp() {
		Mock.clearCustomers();
		Mock.addCustomer(testCustomerID, "Mary", "Bennet", "Great Mount", "Orange Street", "London", null, "Britain", "5555555", "mary@mail.com");

		Mock.addProduct(10001, 45646556, 1000.00, "Electric Stove", "Silver Electric Stove", true);
		Mock.addProduct(10002, 45646556, 2000.00, "Electric Stove", "White Electric Stove", true);

		Mock.addOrderItem(901, 10001, 1, 0.20, new Date(), null, false); // 20% off $1000.00
		Mock.addOrderItem(902, 10002, 1, 0.50, new Date(), null, false); // 50% off $2000.00

		Mock.addCustomerOrder(10, 901, testCustomerID);
		Mock.addCustomerOrder(11, 902, testCustomerID);
	}

	@Test
	public void testGetCustomerOrdersCost() {
		double cost = 0.00;

		DiscountBillingStrategy strategy = new DiscountBillingStrategy();
		try {
			cost = strategy.getCustomerOrdersCost(testCustomerID);
		} catch (Exception e) {
			e.printStackTrace();
		}

		assertEquals("The billing must be correct after the discount is applied", 1800.00, cost, 0);
	}
}