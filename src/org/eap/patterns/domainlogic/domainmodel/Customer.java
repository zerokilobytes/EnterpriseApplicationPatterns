package org.eap.patterns.domainlogic.domainmodel;

import java.util.ArrayList;
import java.util.List;



public class Customer extends org.eap.dao.businessobject.Customer
{
	public List<Order> Orders;
	
	public Customer(int customerID)
	{
		Orders = new ArrayList<Order>();
		this.load();
	}

	/**
	 * Get the total cost of customer orders
	 * @return
	 */
	public double getTotalCostOfOrders()
	{
		double total = 0.00;

		for(Order order : Orders)
		{
			total += order.Product.Price * order.Quanty;
		}
		return total;
	}
	
	private void load()
	{

	}
}