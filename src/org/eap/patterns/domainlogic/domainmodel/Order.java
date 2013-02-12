package org.eap.patterns.domainlogic.domainmodel;

import org.eap.dao.Result;
import org.eap.dao.datasource.mock.ProductRepository;
import org.eap.dao.businessobject.Product;

public class Order extends org.eap.dao.businessobject.Order
{
	public Product Product;
	
	public Order(int orderID)
	{
		this.OrderID = orderID;
		this.load();
	}
	
	private void load()
	{
		ProductRepository product = new ProductRepository();
		Result<Product> result = product.GetProductsByID(1);

		this.Product = result.Items.get(0);
	}
}
