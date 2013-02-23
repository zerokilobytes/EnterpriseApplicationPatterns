package org.eap.dao.datasource;

import java.util.Date;

import org.eap.dao.Result;
import org.eap.dao.businessobject.OrderItem;
import org.eap.dao.businessobject.Product;



public class Mock
{
	public static Result<Product> getProducts()
	{
		Result<Product> result = new Result<Product>();
		result.Items.add(createProduct(10003, 1, 950.00, "Electric Stove", "Silver Electric Stove", true));
		result.Items.add(createProduct(10004, 1, 4000.00, "Toster", "Black Electric Toster", false));
		result.Items.add(createProduct(10005, 2, 200.00, "Speaker", "Large Speakers", true));
		result.Items.add(createProduct(10006, 2, 150.00, "Washig Machine", "4X Washig Machine", false));
		result.Items.add(createProduct(10007, 3, 150.00, "Dish Washer", "Small Dish Washer", true));

		return result;
	}
	
	public static Product createProduct( int productID, int supplierID, double price, String productName, String productDescription, Boolean inStock)
	{
		Product product 			= new Product();
		product.ProductID 			= productID;
		product.SupplierID 			= supplierID;
		product.Price 				= price;
		product.ProductName 		= productName;
		product.ProductDescription 	= productDescription;
		product.InStock 			= inStock;
		return product;
	}
	
	public static OrderItem createOrderItem( int OrderItemID, int productID, int quanty, double discount, Date OrderItemDate, Date shippingDate, boolean delivered)
	{
		OrderItem OrderItem			= new OrderItem();
		OrderItem.OrderItemID 		= OrderItemID;
		OrderItem.ProductID 	= productID;
		OrderItem.Quanty 		= quanty;
		OrderItem.Discount 		= discount;
		OrderItem.OrderDate 	= OrderItemDate;
		OrderItem.ShippingDate 	= shippingDate;
		OrderItem.Delivered 	= delivered;

		return OrderItem;
	}

	public static Result<Product> getProductByID(int productID) {
		Result<Product> result = new Result<Product>();
		
		Product product = createProduct(1, 1, 950.00, "Electric Stove", "Silver Electric Stove", true);
		result.Items.add(product);

		return result;
	}
	
	public static Result<OrderItem> getOrderItemsByCustomer(int customerID) {
		Result<OrderItem> result = new Result<OrderItem>();

		OrderItem OrderItem1 = createOrderItem(1, customerID, 10, 50.00, new Date(), new Date(), false);
		OrderItem OrderItem2 = createOrderItem(2, customerID, 10, 50.00, new Date(), new Date(), false);
		OrderItem OrderItem3 = createOrderItem(3, customerID, 10, 50.00, new Date(), new Date(), false);
		
		result.Items.add(OrderItem1);
		result.Items.add(OrderItem2);
		result.Items.add(OrderItem3);

		return result;
	}
	
	public static Result<OrderItem> getOrderItemByID(int OrderItemID)
	{
		Result<OrderItem> result = new Result<OrderItem>();

		OrderItem OrderItem = createOrderItem(1, OrderItemID, 10, 50.00, new Date(), new Date(), false);

		result.Items.add(OrderItem);

		return result;
	}
}
