package org.eap.patterns.domainlogic.domainmodel;

import org.eap.dao.Result;
import org.eap.dao.datasource.mock.OrderRepository;
import org.eap.dao.datasource.mock.ProductRepository;
import org.eap.dao.businessobject.Product;

/**
 * Order class
 * @author Markel Mairs
 */
public class Order extends org.eap.dao.businessobject.Order
{
	public Product Product;

	public Order(int orderID)
	{
		this.OrderID = orderID;
		this.load();
	}

	/**
	 * Load ordered Product
	 */
	private void load()
	{
		OrderRepository ordeRepo = new OrderRepository();
		Result<org.eap.dao.businessobject.Order> orderResult = ordeRepo.getOrderByOrderID(this.OrderID);
		org.eap.dao.businessobject.Order order = orderResult.Items.get(0);

		ProductRepository product = new ProductRepository();
		Result<Product> result = product.getProductsByID(order.ProductID);

		this.Product 		= result.Items.get(0);
		this.Quanty 		= order.Quanty;
		this.Discount 		= order.Discount;
		this.OrderDate 		= order.OrderDate;
		this.ShippingDate 	= order.ShippingDate;
		this.Delivered 		= order.Delivered;
	}
}