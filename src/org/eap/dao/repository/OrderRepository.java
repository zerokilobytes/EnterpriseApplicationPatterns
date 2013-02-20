package org.eap.dao.repository;


import org.eap.dao.Result;
import org.eap.dao.businessobject.Order;

/**
 * OrderRepository class
 * @author Markel Mairs
 */
public interface OrderRepository
{
	public Result<Order> getOrdersByCustomer(int customerID);

	public  Result<Order> getOrderByOrderID(int orderID);
}