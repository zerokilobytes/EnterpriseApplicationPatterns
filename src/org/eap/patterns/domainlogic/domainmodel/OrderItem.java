package org.eap.patterns.domainlogic.domainmodel;

import org.eap.dao.Result;
import org.eap.dao.datasource.mock.OrderItemRepository;
import org.eap.dao.datasource.mock.ProductRepository;
import org.eap.dao.businessobject.Product;

/**
 * Order class
 * @author zerobytes
 */
public class OrderItem extends org.eap.dao.businessobject.OrderItem
{
	public Product Product;

	public OrderItem(int orderID)
	{
		this.OrderItemID = orderID;
		this.load();
	}

	/**
	 * Load ordered Product
	 */
	private void load()
	{
		OrderItemRepository ordeRepo = new OrderItemRepository();
		Result<org.eap.dao.businessobject.OrderItem> orderResult = ordeRepo.getOrderItemByID(this.OrderItemID);
		org.eap.dao.businessobject.OrderItem order = orderResult.Items.get(0);

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