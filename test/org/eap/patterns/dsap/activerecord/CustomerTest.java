package org.eap.patterns.dsap.activerecord;

import static org.junit.Assert.*;

import java.sql.Date;

import org.eap.dao.datasource.DB;
import org.eap.dao.datasource.SQLite;
import org.eap.patterns.domainlogic.domainmodel.DiscountBillingStrategy;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class CustomerTest {

	@Before
	public void setUp() throws Exception 
	{
		DB.setDataSource(new SQLite());
	}
	
	@After
	public void teaDown() 
	{
		DB.closeConnection();
	}

	@Test
	public void test() throws Exception 
	{
		double cost = 0.00;
		int productID = Product.insert(1, 99.99, "Sample Product", "Sample product for testing", false);
		int customerID = Customer.insert("Jack", "Sparrow", "Core valley", "Bermuda", "Bermuda City", "", "Bermuda", "555-5555", "sparrow@nomail.com");
		int orderItemID = OrderItem.insert(productID, 1, 0.0, Date.valueOf("2013-12-25"), Date.valueOf("2013-12-25"), false);

		OrderItem orderItem = OrderItem.find(orderItemID);
		Customer customer = Customer.find(customerID);
		customer.insertCustomerOrder(orderItem);
		
		DiscountBillingStrategy strategy = new DiscountBillingStrategy();
		cost = strategy.getCustomerOrdersCost(customerID);

		assertEquals("The total cost of the customer order must be correct", 99.99, cost, 0);
	}

}
