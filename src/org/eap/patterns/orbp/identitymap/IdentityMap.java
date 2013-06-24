package org.eap.patterns.orbp.identitymap;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

import org.eap.dao.datasource.DB;

public class IdentityMap 
{
	static IdentityMap soleInstance;

	private Map<Integer, Supplier> Supplier = new HashMap<Integer, Supplier>();
	private Map<Integer, Product> Product = new HashMap<Integer, Product>();

	protected IdentityMap()
	{
		
	}

	public static IdentityMap getInstance()
	{
		if(soleInstance == null)
		{
			soleInstance = new IdentityMap();
		}
		return soleInstance;
	}
	
	private static Connection getConnection()
	{
		return DB.getConnection();
	}

	public static class Supplier extends org.eap.dao.domainobject.Supplier
	{
		public static void addSupplier(Supplier supplier) throws SQLException
		{
			if(!IdentityMap.getInstance().Supplier.containsKey(supplier.SupplierID))
				insert(supplier);
		}

		public static Supplier getSupplier(Integer id) throws SQLException
		{
			Supplier supplier = (Supplier) IdentityMap.getInstance().Supplier.get(id);
			return (supplier == null) ? find(id) : supplier;
		}

		private synchronized static Supplier find(Integer supplierID) throws SQLException
		{
			Supplier supplier = null;
			Connection connection = null;
			PreparedStatement  prepStmt = null;
			try 
			{
				connection = getConnection();

				prepStmt = connection.prepareStatement("SELECT * FROM Supplier WHERE SupplierID = ?;");
				prepStmt.setInt(1, supplierID);
				prepStmt.setMaxRows(1); 

				ResultSet rs = prepStmt.executeQuery();

				while (rs.next())
				{
					supplier = new Supplier();
					supplier.SupplierID 		= rs.getInt("SupplierID");
					supplier.SupplierName 		= rs.getString("SupplierName");

					IdentityMap.getInstance().Supplier.put(supplier.SupplierID, supplier);
				}

				rs.close();
				prepStmt.close();
				DB.closeConnection();
				return supplier;
			}
			catch (Exception e) 
			{
		    	throw e;
			}
		}

		private synchronized static boolean insert(Supplier supplier) throws SQLException
		{
			Connection connection = null;
			PreparedStatement  prepStmt = null;

			try 
			{
				connection = getConnection();
				connection.setAutoCommit(true);

		    	int insertedID = -1;

		    	String sql = "INSERT INTO Supplier (SupplierName) VALUES (?);";
		    	prepStmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

		    	prepStmt.setString(1, supplier.SupplierName);

		    	prepStmt.executeUpdate();

		    	ResultSet rs = prepStmt.getGeneratedKeys();

		    	if (rs.next())
		    	{
		    		insertedID = rs.getInt(1);
		    		supplier.SupplierID = insertedID;
		    		IdentityMap.getInstance().Supplier.put(supplier.SupplierID, supplier);
		    	}

				prepStmt.close();
				return insertedID > -1;
			}
			catch(Exception e)
			{
				throw e;
			}
		}
	}

	public static class Product extends org.eap.dao.domainobject.Product
	{
		public static void addProduct(Product product) throws Exception
		{
			if(!IdentityMap.getInstance().Supplier.containsKey(product.ProductID))
				insert(product);
		}

		public static Product getProduct(Integer id) throws SQLException
		{
			Product product = (Product) IdentityMap.getInstance().Product.get(id);
			return (product == null) ? find(id) : product;
		}

		private synchronized static Product find(Integer productID) throws SQLException
		{
			Product product = null;
			Connection connection = null;
			PreparedStatement  prepStmt = null;
			try 
			{
				connection = getConnection();
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
					
					IdentityMap.getInstance().Product.put(product.ProductID, product);
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

		private static boolean insert(Product product) throws Exception
		{
			Connection connection = null;
			PreparedStatement  prepStmt = null;

			try
			{
		    	connection = getConnection();
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
		    	if (rs.next())
		    	{
		    		insertedID = rs.getInt(1);
		    		product.ProductID = insertedID;
		    		IdentityMap.getInstance().Product.put(product.ProductID, product);
		    	}

				prepStmt.close();
				DB.closeConnection();
				return insertedID != -1;
			}
			catch(Exception e)
			{
				throw e;
			}
		}
	}
}