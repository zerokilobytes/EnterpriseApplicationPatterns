package org.eap.patterns.domainlogic.domainmodel;

import java.sql.SQLException;

import org.eap.patterns.dsap.activerecord.Customer;

/**
 * DiscountBillingStrategy class
 * @author zerobytes
 */
public class DiscountBillingStrategy 
{
	/**
	 * Get total cost for customer
	 * @param customerID
	 * @return
	 * @throws SQLException 
	 */
	public double getCustomerOrdersCost(int customerID) throws SQLException  
	{  
		Customer customer = Customer.find(customerID);
		return customer.getTotalCostOfOrders();
	}
}