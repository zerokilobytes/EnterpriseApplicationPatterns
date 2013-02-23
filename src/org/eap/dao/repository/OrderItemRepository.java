package org.eap.dao.repository;


import org.eap.dao.Result;
import org.eap.dao.businessobject.OrderItem;

/**
 * OrderRepository class
 * @author Markel Mairs
 */
public interface OrderItemRepository
{
	public Result<OrderItem> getOrderItemsByCustomer(int customerID);

	public  Result<OrderItem> getOrderItemByID(int orderID);
}