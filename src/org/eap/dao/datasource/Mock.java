package org.eap.dao.datasource;

import java.util.Date;

import org.eap.dao.Result;
import org.eap.dao.businessobject.Order;
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
	
	public static Order createOrder( int orderID, int productID, int quanty, double discount, Date orderDate, Date shippingDate, boolean delivered)
	{
		Order order			= new Order();
		order.OrderID 		= orderID;
		order.ProductID 	= productID;
		order.Quanty 		= quanty;
		order.Discount 		= discount;
		order.OrderDate 	= orderDate;
		order.ShippingDate 	= shippingDate;
		order.Delivered 	= delivered;

		return order;
	}

	public static Result<Product> getProductByID(int productID) {
		Result<Product> result = new Result<Product>();
		
		Product product = createProduct(1, 1, 950.00, "Electric Stove", "Silver Electric Stove", true);
		result.Items.add(product);

		return result;
	}
	
	public static Result<Order> getOrdersByCustomer(int customerID) {
		Result<Order> result = new Result<Order>();

		Order order1 = createOrder(1, customerID, 10, 50.00, new Date(), new Date(), false);
		Order order2 = createOrder(2, customerID, 10, 50.00, new Date(), new Date(), false);
		Order order3 = createOrder(3, customerID, 10, 50.00, new Date(), new Date(), false);
		
		result.Items.add(order1);
		result.Items.add(order2);
		result.Items.add(order3);

		return result;
	}
	
	public static Result<Order> getOrderByOrderID(int orderID)
	{
		Result<Order> result = new Result<Order>();

		Order order = createOrder(1, orderID, 10, 50.00, new Date(), new Date(), false);

		result.Items.add(order);

		return result;
	}
}
