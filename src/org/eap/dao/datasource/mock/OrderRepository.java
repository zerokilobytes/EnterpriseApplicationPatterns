/**
 * 
 */
package org.eap.dao.datasource.mock;

import org.eap.dao.Result;
import org.eap.dao.businessobject.Order;
import org.eap.dao.datasource.Mock;

/**
 * @author SNOW
 *
 */
public class OrderRepository implements org.eap.dao.repository.OrderRepository
{

	@Override
	public Result<Order> getOrdersByCustomer(int customerID) {
		Result<org.eap.dao.businessobject.Order> result = Mock.getOrdersByCustomer(customerID);
		return result;
	}

	@Override
	public Result<Order> getOrderByOrderID(int orderID) {
		Result<org.eap.dao.businessobject.Order> result = Mock.getOrderByOrderID(orderID);
		return result;
	}

}
