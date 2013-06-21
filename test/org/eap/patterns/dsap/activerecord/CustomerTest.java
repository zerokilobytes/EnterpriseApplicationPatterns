package org.eap.patterns.dsap.activerecord;

import static org.junit.Assert.*;

import java.sql.Date;

import org.eap.dao.datasource.DB;
import org.eap.dao.datasource.SQLite;
import org.junit.Before;
import org.junit.Test;

public class CustomerTest {

	@Before
	public void setUp() throws Exception 
	{
		DB.setDataSource(new SQLite());
	}

	@Test
	public void test() throws Exception 
	{
		int productID = Product.insert(1, 99.99, "Sample Product", "Sample product for testing", false);
		int customerID = Customer.insert("Jack", "Sparrow", "Core valley", "Bermuda", "Bermuda City", "", "Bermuda", "555-5555", "sparrow@nomail.com");
		int orderItemID = OrderItem.insert(productID, 1, 0.0, Date.valueOf("2013-12-25"), Date.valueOf("2013-12-25"), false);
		
		OrderItem orderItem = OrderItem.find(orderItemID);
		Customer customer = Customer.find(customerID);
		customer.insertCustomerOrder(orderItem);

		assertNotNull("",customer);
	}

}
