package org.eap.patterns.domainlogic.domainmodel;

import java.util.ArrayList;
import java.util.List;

import org.eap.dao.Result;
import org.eap.dao.datasource.mock.OrderItemRepository;

/**
 * Customer class
 * @author Markel Mairs
 */
public class Customer extends org.eap.dao.businessobject.Customer
{
	public List<OrderItem> Orders;

	public Customer(int customerID)
	{
		Orders = new ArrayList<OrderItem>();
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

		for(OrderItem order : Orders)
		{
			total += (order.Product.Price * order.Quanty) * (1.00 - order.Discount);
		}
		return total;
	}

	/**
	 * Load orders for customers
	 */
	private void loadOrders()
	{
		OrderItemRepository orderRepo = new OrderItemRepository();
		Result<org.eap.dao.businessobject.OrderItem> result = orderRepo.getOrderItemsByCustomer(this.CustomerID);

		// Retrieve orders from result
		for(org.eap.dao.businessobject.OrderItem item :  result.Items)
		{
			OrderItem order = new OrderItem(item.OrderItemID);
			Orders.add(order);
		}
	}
}