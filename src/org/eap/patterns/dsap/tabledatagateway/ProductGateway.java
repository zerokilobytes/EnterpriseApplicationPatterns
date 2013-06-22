package org.eap.patterns.dsap.tabledatagateway;

import java.sql.*;
import org.eap.dao.datasource.*;
import org.eap.dao.*;
import org.eap.dao.domainobject.Product;

/**
 * Product gateway
 * @author zerobytes
 *
 */
public class ProductGateway 
{
	/**
	 * @return
	 * @throws SQLException
	 */
	public synchronized DataTable<Product> findAll() throws SQLException
	{
		DataTable<Product> result = new DataTable<Product>();
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
				product.ProductName 		= rs.getString("ProductName");
				product.ProductDescription 	= rs.getString("ProductDescription");
				product.InStock				= rs.getBoolean("InStock"); 

				result.addRow(product);
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
	public synchronized Result<Product> findByProductName(String productName) throws SQLException
	{
		Result<Product> result = new Result<Product>();
		Connection connection = null;
		PreparedStatement  prepStmt = null;
		try 
		{
			connection = DB.getConnection();
			connection.setAutoCommit(true);

			prepStmt = connection.prepareStatement("SELECT * FROM Product WHERE ProductName = ?;");
			prepStmt.setString(1, productName);
			prepStmt.setMaxRows(1); 

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

				result.Items.add(product);
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

	/**
	 * @param supplierID
	 * @param price
	 * @param productName
	 * @param productDescription
	 * @param inStock
	 * @return
	 * @throws SQLException
	 */
	public synchronized int addProduct(Integer supplierID, double price, String productName, String productDescription, Boolean inStock) throws SQLException
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
	    	if (rs.next()){
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

	/**
	 * @param productID
	 * @param supplierID
	 * @param price
	 * @param productName
	 * @param productDescription
	 * @param inStock
	 * @return
	 * @throws SQLException
	 */
	public synchronized boolean updateProduct(Integer productID, Integer supplierID, double price, String productName, String productDescription, Boolean inStock) throws SQLException
	{
		Connection connection = null;
		PreparedStatement  prepStmt = null;
			try 
			{
		    	connection = DB.getConnection();
		    	connection.setAutoCommit(true);
		    	int affectedRows = 0;

		    	String sql = "UPDATE Product SET SupplierID = ?, Price = ?, ProductName = ?, ProductDescription = ?, InStock = ? WHERE ProductID = ?";
		    	prepStmt = connection.prepareStatement(sql);

		    	prepStmt.setInt(1, supplierID);
		    	prepStmt.setDouble(2, price);
		    	prepStmt.setString(3, productName);
		    	prepStmt.setString(4, productDescription);
		    	prepStmt.setBoolean(5, inStock);
		    	prepStmt.setInt(6, productID);

		    	affectedRows = prepStmt.executeUpdate();

				prepStmt.close();
				DB.closeConnection();
				return affectedRows > 0;
		}
		catch(Exception e)
		{
		    throw e;
		}
	}
	
	public synchronized boolean deleteProduct(Integer productID) throws SQLException
	{
		Connection connection = null;
		PreparedStatement  prepStmt = null;

		try 
		{
	    	connection = DB.getConnection();
	    	connection.setAutoCommit(true);
	    	
	    	int affectedRows = 0;

	    	String sql = "DELETE FROM Product WHERE ProductID = ?;";
	    	prepStmt = connection.prepareStatement(sql);

	    	prepStmt.setInt(1, productID);
	    	affectedRows = prepStmt.executeUpdate();

			prepStmt.close();
			DB.closeConnection();
			return affectedRows > 0;
		}
		catch(Exception e)
		{
			throw e;
		}
	}
}