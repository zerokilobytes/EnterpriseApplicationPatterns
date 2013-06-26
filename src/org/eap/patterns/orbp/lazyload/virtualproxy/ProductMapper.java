package org.eap.patterns.orbp.lazyload.virtualproxy;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.eap.dao.DataTable;
import org.eap.dao.datasource.DB;
import org.eap.dao.domainobject.Product;

public class ProductMapper
{
	public static ProductMapper create()
	{
		// TODO Auto-generated method stub
		return new ProductMapper();
	}

	private ProductMapper()
	{
		
	}

	public synchronized DataTable<Product> getProductByID(Integer productID) throws SQLException
	{
		DataTable<Product> result = new DataTable<Product>();
		Connection connection = null;
		PreparedStatement  prepStmt = null;

		try
		{
			connection = DB.getConnection();
			connection.setAutoCommit(true);

			prepStmt = connection.prepareStatement("SELECT * FROM Product WHERE ProductID = ?;");
			prepStmt.setInt(1, productID);

			ResultSet rs = prepStmt.executeQuery();

			while (rs.next()) 
			{
				Product product = new Product();

				product.ProductID 			= rs.getInt("ProductID");
				product.SupplierID 			= rs.getInt("SupplierID");
				product.Price 				= rs.getDouble("Price");
				product.ProductName 		= rs.getString("ProductName");
				product.ProductDescription 	= rs.getString("ProductDescription");
				product.InStock 			= rs.getBoolean("InStock"); 

				result.addRow(product);
			}

			rs.close();
			prepStmt.close();
			DB.closeConnection();
			return result;
		}
		catch (Exception e) 
		{
	    	throw e;
		}
	}

	public synchronized static int insert(Integer supplierID, double price, String productName, String productDescription, Boolean inStock) throws SQLException
	{
		Connection connection = null;
		PreparedStatement  prepStmt = null;

		try 
		{
	    	connection = DB.getConnection();
	    	connection.setAutoCommit(true);

	    	int insertedID = -1;

	    	String sql = "INSERT INTO Product (SupplierID, Price, ProductName, ProductDescription, InStock) VALUES (?,?,?,?,?);";
	    	prepStmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

	    	prepStmt.setInt(1, supplierID);
	    	prepStmt.setDouble(2, price);
	    	prepStmt.setString(3, productName);
	    	prepStmt.setString(4, productDescription);
	    	prepStmt.setBoolean(5, inStock);

	    	prepStmt.executeUpdate();

	    	ResultSet rs = prepStmt.getGeneratedKeys();

	    	if (rs.next())
	    	{
	    		insertedID = rs.getInt(1);
	    	}

			prepStmt.close();
			DB.closeConnection();
			return insertedID;
		}
		catch(Exception e)
		{
			throw e;
		}
	}
}
