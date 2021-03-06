package org.eap.patterns.dsap.activerecord;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.eap.dao.DataTable;
import org.eap.dao.datasource.DB;

public class Product extends org.eap.dao.domainobject.Product
{
	public OrderItem OrderItem;
	/**
	 * Constructor
	 */
	private Product()
	{
		
	}

	/**
	 * @return the productID
	 */
	public synchronized int getProductID() {
		return ProductID;
	}
	/**
	 * @return the supplierID
	 */
	public synchronized int getSupplierID() {
		return SupplierID;
	}
	/**
	 * @param supplierID the supplierID to set
	 */
	public synchronized void setSupplierID(int supplierID) {
		SupplierID = supplierID;
	}
	/**
	 * @return the price
	 */
	public synchronized double getPrice() {
		return Price;
	}
	/**
	 * @param price the price to set
	 */
	public synchronized void setPrice(double price) {
		Price = price;
	}
	/**
	 * @return the productName
	 */
	public synchronized String getProductName() {
		return ProductName;
	}
	/**
	 * @param productName the productName to set
	 */
	public synchronized void setProductName(String productName) {
		ProductName = productName;
	}
	/**
	 * @return the productDescription
	 */
	public synchronized String getProductDescription() {
		return ProductDescription;
	}
	/**
	 * @param productDescription the productDescription to set
	 */
	public synchronized void setProductDescription(String productDescription) {
		ProductDescription = productDescription;
	}
	/**
	 * @return the inStock
	 */
	public synchronized Boolean getInStock() {
		return InStock;
	}
	/**
	 * @param inStock the inStock to set
	 */
	public synchronized void setInStock(Boolean inStock) {
		InStock = inStock;
	}

	/**
	 * 
	 * @return
	 * @throws SQLException
	 */
	public synchronized boolean delete() throws SQLException
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

	    	prepStmt.setInt(1, this.ProductID);
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

	/**
	 * 
	 * @return
	 * @throws SQLException
	 */
	public synchronized boolean update() throws SQLException
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

		    	prepStmt.setInt(1, this.SupplierID);
		    	prepStmt.setDouble(2, this.Price);
		    	prepStmt.setString(3, this.ProductName);
		    	prepStmt.setString(4, this.ProductDescription);
		    	prepStmt.setBoolean(5, this.InStock);
		    	prepStmt.setInt(6, this.ProductID);

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

	/**
	 * 
	 * @param productID
	 * @return
	 * @throws SQLException
	 */
	public synchronized static Product find(Integer productID) throws SQLException
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
	
	public synchronized static Product findProductByOrderItemID(Integer orderID) throws SQLException
	{
		Product product = null;
		Connection connection = null;
		PreparedStatement  prepStmt = null;
		try 
		{
			connection = DB.getConnection();
			connection.setAutoCommit(true);

			prepStmt = connection.prepareStatement("SELECT P.* FROM OrderItem O INNER JOIN Product P ON O.ProductID = P.ProductID WHERE O.OrderItemID = ?;");
			prepStmt.setInt(1, orderID);
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

	public synchronized static DataTable<Product> getProductsBySupplier(int supplierID) throws SQLException
	{
		DataTable<Product> result = new DataTable<Product>();
		Connection connection = null;
		PreparedStatement  prepStmt = null;
		try 
		{
			connection = DB.getConnection();
			connection.setAutoCommit(true);

			prepStmt = connection.prepareStatement("SELECT * FROM Product WHERE SupplierID = ?;");
			prepStmt.setInt(1, supplierID);
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
	
	public synchronized static Product getProductByID(int productID) throws SQLException
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

	/**
	 * 
	 * @param supplierID
	 * @param price
	 * @param productName
	 * @param productDescription
	 * @param inStock
	 * @return
	 * @throws SQLException
	 */
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
	 * 
	 * @param product
	 * @return
	 * @throws SQLException
	 */
	public synchronized static int insert(Product product) throws SQLException
	{
		return  insert(product.SupplierID, product.Price, product.ProductName, product.ProductDescription, product.InStock);
	}

	/**
	 * @return
	 * @throws SQLException
	 */
	public synchronized static Product[] findAll() throws SQLException
	{
		ArrayList<Product> result = new ArrayList<Product>();
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

				result.add(product);
			}
			rs.close();
			stmt.close();
			DB.closeConnection();

			return result.toArray(new Product[result.size()]);
		}
		catch (Exception e) 
		{
	    	throw e;
		}
	}
}