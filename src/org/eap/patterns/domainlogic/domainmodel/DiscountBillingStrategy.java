package org.eap.patterns.domainlogic.domainmodel;

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
	 */
	public double getCustomerOrdersCost(int customerID)  
	{  
		Customer customer = new Customer(customerID);
		return customer.getTotalCostOfOrders();
	}
}