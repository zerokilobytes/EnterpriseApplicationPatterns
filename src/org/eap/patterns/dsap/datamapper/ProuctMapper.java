package org.eap.patterns.dsap.datamapper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.eap.dao.datasource.DB;
import org.eap.dao.domainobject.Product;

public class ProuctMapper extends AbstractMapper
{
	protected synchronized Product find(int productID) throws SQLException 
	{
		Product product = null;
		Connection connection = null;
		PreparedStatement  prepStmt = null;

		try 
		{
			connection = DB.getConnection();
			connection.setAutoCommit(true);

			prepStmt = connection.prepareStatement("SELECT * FROM Product WHERE ProductID = ?;");
			prepStmt.setInt(1, productID);
			prepStmt.setMaxRows(1); 

			ResultSet rs = prepStmt.executeQuery();

			while (rs.next())
			{
				product = new Product();
				product.ProductID 			= rs.getInt("ProductID");
				product.SupplierID 			= rs.getInt("SupplierID");
				product.Price 				= rs.getDouble("Price");
				product.ProductName 		= rs.getString("ProductName");
				product.ProductDescription 	= rs.getString("ProductDescription");
				product.InStock 			= rs.getBoolean("InStock"); 
			}
			rs.close();
			prepStmt.close();
			DB.closeConnection();
			return product;
		}
		catch (Exception e) 
		{
	    	throw e;
		}
	}

	@SuppressWarnings("unchecked")
	public synchronized int insert(Product product) throws SQLException
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

	    	prepStmt.setInt(1, product.SupplierID);
	    	prepStmt.setDouble(2, product.Price);
	    	prepStmt.setString(3, product.ProductName);
	    	prepStmt.setString(4, product.ProductDescription);
	    	prepStmt.setBoolean(5, product.InStock);

	    	prepStmt.executeUpdate();

	    	ResultSet rs = prepStmt.getGeneratedKeys();
	    	if (rs.next()){
	    		insertedID = rs.getInt(1);
	    		product.ProductID = insertedID;
	    		loadedMap.put(product.ProductID, product);
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

	public Product findProuct(int id) throws Exception
	{
		return (Product)get(id);
	}
}