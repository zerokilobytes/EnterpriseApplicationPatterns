package org.eap.patterns.dsap.tabledatagateway;

import java.sql.*;
import org.eap.dao.datasource.*;
import org.eap.dao.*;
import org.eap.dao.businessobject.Product;

/*
 * Product gateway
 */
public class ProductGateway 
{
	public Result<Product> findAll() throws SQLException
	{
		Result<Product> result = new Result<Product>();
		Connection connection = null;
		Statement stmt = null;
		try 
	    	{
			connection = DB.getConnection();
			connection.setAutoCommit(true);

			stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM Product;");

			while (rs.next()) 
			{
				Product product = new Product();

				product.ProductID 			= rs.getInt("ProductID");
				product.SupplierID 			= rs.getInt("SupplierID");
				product.Price 				= rs.getDouble("Price");
				product.ProductName 			= rs.getString("ProductName");
				product.ProductDescription 		= rs.getString("ProductDescription");
				product.InStock 			= rs.getBoolean("InStock"); 

				result.Items.add(product);
			}
			rs.close();
			stmt.close();
			DB.closeConnection();
			return result;
		}
		catch (Exception e) 
		{
	    		throw e;
		}
	}

	public boolean addProduct(int supplierID, double price, String productName, String productDescription, Boolean inStock) throws SQLException
	{
		Connection connection = null;
		PreparedStatement  prepStmt = null;
		try 
	    	{
		    	connection = DB.getConnection();
		    	connection.setAutoCommit(true);
	
		    	String sql = "INSERT INTO Product (SupplierID, Price, ProductName, ProductDescription, InStock) VALUES (?,?,?,?,?);";
		    	prepStmt = connection.prepareStatement(sql);

		    	prepStmt.setInt(1, supplierID);
		    	prepStmt.setDouble(2, price);
		    	prepStmt.setString(3, productName);
		    	prepStmt.setString(4, productDescription);
		    	prepStmt.setBoolean(5, inStock);
		    	
		    	prepStmt.executeUpdate();
	
		    	prepStmt.close();
			DB.closeConnection();
			return true;
		}
		catch(Exception e)
		{
		    throw e;
		}
	}
}
