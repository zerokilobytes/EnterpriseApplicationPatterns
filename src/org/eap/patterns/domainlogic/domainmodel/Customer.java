package org.eap.patterns.domainlogic.domainmodel;

import java.util.ArrayList;
import java.util.List;

import org.eap.dao.Result;
import org.eap.dao.datasource.mock.OrderRepository;

/**
 * Customer class
 * @author Markel Mairs
 */
public class Customer extends org.eap.dao.businessobject.Customer
{
	public List<Order> Orders;

	public Customer(int customerID)
	{
		Orders = new ArrayList<Order>();
		this.CustomerID = customerID;
		this.loadOrders();
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
			total += (order.Product.Price * order.Quanty) - order.Discount;
		}
		return total;
	}

	/**
	 * Load orders for customers
	 */
	private void loadOrders()
	{
		OrderRepository orderRepo = new OrderRepository();
		Result<org.eap.dao.businessobject.Order> result = orderRepo.getOrdersByCustomer(this.CustomerID);

		// Retrieve orders from result
		for(org.eap.dao.businessobject.Order item :  result.Items)
		{
			Order order = new Order(item.OrderID);
			Orders.add(order);
		}
	}
}