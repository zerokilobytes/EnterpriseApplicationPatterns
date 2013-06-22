package org.eap.dao.datasource;

import java.sql.Date;

import org.eap.dao.Result;
import org.eap.dao.domainobject.Customer;
import org.eap.dao.domainobject.CustomerOrder;
import org.eap.dao.domainobject.OrderItem;
import org.eap.dao.domainobject.Product;



public class Mock
{
	private static Result<Product> products = new Result<Product>();
	private static Result<OrderItem> orderItems = new Result<OrderItem>();
	private static Result<Customer> customers = new Result<Customer>();
	private static Result<CustomerOrder> customerOrders = new Result<CustomerOrder>();
	
	public static boolean addProduct(Product product)
	{
		return products.Items.add(product);
	}
	public static Result<Product> getProducts()
	{
		return products;
	}
	public static void clearProducts()
	{
		products.Items.clear();
	}
	
	public static void clearCustomers()
	{
		customers.Items.clear();
	}
	
	public static boolean addCustomer(int customerID, String firstName, String lastName, String addressLine1, String addressLine2, String city, String state, String country, String phoneNumber, String email)
	{
		Customer customer = createCustomer( customerID,  firstName,  lastName,  addressLine1,  addressLine2,  city,  state,  country,  phoneNumber,  email);
		return customers.Items.add(customer); 
	}
	

	public static Customer createCustomer(int customerID, String firstName, String lastName, String addressLine1, String addressLine2, String city, String state, String country, String phoneNumber, String email)
	{
		Customer customer 	= new Customer();
		
		customer.CustomerID 	= customerID;
		customer.FirstName 		= firstName;
		customer.LastName 		= lastName;
		customer.AddressLine1 	= addressLine1;
		customer.AddressLine2 	= addressLine2;
		customer.City 			= city;
		customer.State 			= state;
		customer.Country 		= country;
		customer.PhoneNumber 	= phoneNumber;
		customer.Email 			= email;
		
		return customer;
	}
	
	public static boolean addProduct( int productID, int supplierID, double price, String productName, String productDescription, Boolean inStock)
	{
		Product product = createProduct(  productID,  supplierID,  price,  productName,  productDescription,  inStock);
		return products.Items.add(product); 
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
	
	public static boolean addOrderItem( int orderItemID, int productID, int quanty, double discount, Date orderItemDate, Date shippingDate, boolean delivered)
	{
		OrderItem orderItem = createOrderItem(orderItemID,  productID,  quanty, discount, orderItemDate, shippingDate, delivered);
		return orderItems.Items.add(orderItem);
	}
	
	public static boolean addCustomerOrder(int customerOrderID, int orderItemID, int customerID)
	{
		CustomerOrder customerOrder = createCustomerOrder(customerOrderID, orderItemID,customerID);
		return customerOrders.Items.add(customerOrder);
	}
	
	
	public static CustomerOrder createCustomerOrder(int customerOrderID, int orderItemID, int customerID)
	{
		 CustomerOrder customerOrder = new CustomerOrder();
		 
		 customerOrder.CustomerOrderID 	= customerOrderID;
		 customerOrder.OrderItemID 		= orderItemID;
		 customerOrder.CustomerID 		= customerID;
		 
		 return customerOrder;
	}
	
	public static OrderItem createOrderItem( int orderItemID, int productID, int quantity, double discount, Date orderItemDate, Date shippingDate, boolean delivered)
	{
		OrderItem OrderItem			= new OrderItem();
		OrderItem.OrderItemID 		= orderItemID;
		OrderItem.ProductID 	= productID;
		OrderItem.Quantity 		= quantity;
		OrderItem.Discount 		= discount;
		OrderItem.OrderDate 	= orderItemDate;
		OrderItem.ShippingDate 	= shippingDate;
		OrderItem.Delivered 	= delivered;

		return OrderItem;
	}

	public static Result<Product> getProductByID(int productID) {
		Result<Product> result = new Result<Product>();
		
		for(Product prouct : products.Items)
		{
			if(prouct.ProductID ==productID)
			{
				result.Items.add(prouct);
			}
		}
		return result;
	}
	
	public static Result<OrderItem> getOrderItemsByCustomer(int customerID) 
	{
		Result<OrderItem> customerOrderItems = new Result<OrderItem>();
		
		for(CustomerOrder customerOrder : customerOrders.Items)
		{
			if(customerOrder.CustomerID == customerID)
			{
				for(OrderItem orderItem : orderItems.Items)
				{
					if(orderItem.OrderItemID == customerOrder.OrderItemID)
					{
						customerOrderItems.Items.add(orderItem);
					}
				}
			}
		}

		return orderItems;
	}
	
	public static Result<OrderItem> getOrderItemByID(int orderItemID)
	{
		Result<OrderItem> result = new Result<OrderItem>();

		for(OrderItem orderItem : orderItems.Items)
		{
			if(orderItem.OrderItemID == orderItemID)
			{
				result.Items.add(orderItem);
			}
		}

		return result;
	}
}
