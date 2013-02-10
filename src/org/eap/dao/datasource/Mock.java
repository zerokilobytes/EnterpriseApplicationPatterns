package org.eap.dao.datasource;

import org.eap.dao.Result;
import org.eap.dao.businessobject.Product;



public class Mock
{
	public static Result<Product> getProducts()
	{
		Result<Product> result = new Result<Product>();
		result.Items.add(initProduct(10003, 1, 950.00, "Electric Stove", "Silver Electric Stove", true));
		result.Items.add(initProduct(10004, 1, 4000.00, "Toster", "Black Electric Toster", false));
		result.Items.add(initProduct(10005, 2, 200.00, "Speaker", "Large Speakers", true));
		result.Items.add(initProduct(10006, 2, 150.00, "Washig Machine", "4X Washig Machine", false));
		result.Items.add(initProduct(10007, 3, 150.00, "Dish Washer", "Small Dish Washer", true));

		return result;
	}
	
	public static Product initProduct( int productID, int supplierID, double price, String productName, String productDescription, Boolean inStock)
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
}
