package org.eap.patterns.orbp.lazyload.lazyinitialization;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.eap.dao.DataTable;
import org.eap.dao.datasource.DB;

public class Product extends org.eap.dao.domainobject.Product
{
	private static final Map<Integer, DataTable<Product>> products = new HashMap<Integer, DataTable<Product>>();

    private Product()
    {
    }

	public DataTable<Product> getProductsBySupplier(Integer supplierID) throws SQLException
	{
		if(!products.containsKey(supplierID))
		{
			products.put(supplierID, getProductsBySupplier(supplierID)); // Lazy initialization
        }

        return products.get(supplierID);
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
}
