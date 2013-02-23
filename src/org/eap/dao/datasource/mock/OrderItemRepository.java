/**
 * 
 */
package org.eap.dao.datasource.mock;

import org.eap.dao.Result;
import org.eap.dao.businessobject.OrderItem;
import org.eap.dao.datasource.Mock;


public class OrderItemRepository implements org.eap.dao.repository.OrderItemRepository
{

	@Override
	public Result<OrderItem> getOrderItemsByCustomer(int customerID) {
		Result<org.eap.dao.businessobject.OrderItem> result = Mock.getOrderItemsByCustomer(customerID);
		return result;
	}

	@Override
	public Result<OrderItem> getOrderItemByID(int orderID) {
		Result<org.eap.dao.businessobject.OrderItem> result = Mock.getOrderItemByID(orderID);
		return result;
	}

}
