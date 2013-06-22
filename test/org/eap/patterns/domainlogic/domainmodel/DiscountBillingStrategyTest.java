package org.eap.patterns.domainlogic.domainmodel;

import static org.junit.Assert.*;

import java.sql.Date;

import org.eap.dao.datasource.DB;
import org.eap.dao.datasource.SQLite;
import org.eap.patterns.dsap.activerecord.Customer;
import org.eap.patterns.dsap.activerecord.OrderItem;
import org.eap.patterns.dsap.activerecord.Product;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class DiscountBillingStrategyTest 
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
	public void testGetCustomerOrdersCost() throws Exception 
	{
		double cost = 0.00;
		
		int productID = Product.insert(1, 500.0, "Sample Product", "Sample product for testing", false);
		int customerID = Customer.insert("Jack", "Sparrow", "Core valley", "Bermuda", "Bermuda City", "", "Bermuda", "555-5555", "sparrow@nomail.com");
		int orderItemID = OrderItem.insert(productID, 3, 0.50, Date.valueOf("2013-12-25"), Date.valueOf("2013-12-25"), false);

		OrderItem orderItem = OrderItem.find(orderItemID);
		Customer customer = Customer.find(customerID);
		customer.insertCustomerOrder(orderItem);

		DiscountBillingStrategy strategy = new DiscountBillingStrategy();
		cost = strategy.getCustomerOrdersCost(customerID);

		assertEquals("The billing must be correct after the discount is applied", 750.00, cost, 0);
	}
}