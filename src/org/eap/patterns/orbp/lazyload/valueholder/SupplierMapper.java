package org.eap.patterns.orbp.lazyload.valueholder;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.eap.dao.DataTable;
import org.eap.dao.datasource.DB;
import org.eap.patterns.orbp.identitymap.IdentityMap.Supplier;

public class SupplierMapper
{
	public synchronized DataTable<org.eap.dao.domainobject.Supplier> find(Integer supplierID) throws SQLException
	{
		DataTable<org.eap.dao.domainobject.Supplier> suppliers = new DataTable<org.eap.dao.domainobject.Supplier>();
		Connection connection = null;
		PreparedStatement  prepStmt = null;
		try 
		{
			connection = DB.getConnection();
			connection.setAutoCommit(true);

			prepStmt = connection.prepareStatement("SELECT * FROM Supplier WHERE SupplierID = ?;");
			prepStmt.setInt(1, supplierID);
			prepStmt.setMaxRows(1); 

			ResultSet rs = prepStmt.executeQuery();

			while (rs.next())
			{
				Supplier supplier = new Supplier();
				supplier = new Supplier();
				supplier.SupplierID 		= rs.getInt("SupplierID");
				supplier.SupplierName 		= rs.getString("SupplierName");

				suppliers.addRow(supplier);
			}

			rs.close();
			prepStmt.close();
			DB.closeConnection();
			return suppliers;
		}
		catch (SQLException e) 
		{
	    	throw e;
		}
	}

	public synchronized int insert(String supplierName) throws SQLException
	{
		Connection connection = null;
		PreparedStatement  prepStmt = null;

		try 
		{
	    	connection = DB.getConnection();
	    	connection.setAutoCommit(true);

	    	int insertedID = -1;

	    	String sql = "INSERT INTO Supplier (SupplierName) VALUES (?);";
	    	prepStmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

	    	prepStmt.setString(1, supplierName);
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