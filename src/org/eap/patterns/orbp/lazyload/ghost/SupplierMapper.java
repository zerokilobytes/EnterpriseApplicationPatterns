package org.eap.patterns.orbp.lazyload.ghost;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.eap.dao.datasource.DB;

public class SupplierMapper extends Mapper<Supplier>
{
	protected synchronized Supplier find(int productID) throws SQLException 
	{
		Supplier supplier = null;
		Connection connection = null;
		PreparedStatement  prepStmt = null;

		try
		{
			connection = DB.getConnection();
			connection.setAutoCommit(true);

			prepStmt = connection.prepareStatement("SELECT * FROM Supplier WHERE SupplierID = ?;");
			prepStmt.setInt(1, productID);
			prepStmt.setMaxRows(1); 

			ResultSet rs = prepStmt.executeQuery();

			while (rs.next())
			{
				supplier = new Supplier(rs.getInt("SupplierID"));
				supplier.SupplierName 		= rs.getString("SupplierName");
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

	public synchronized int insert(Supplier supplier) throws SQLException
	{
		Connection connection = null;
		PreparedStatement  prepStmt = null;

		try 
		{
	    	connection = DB.getConnection();
	    	connection.setAutoCommit(true);

	    	int insertedID = -1;

	    	String sql = "INSERT INTO Supplier () VALUES (?);";
	    	prepStmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

	    	prepStmt.setString(1, supplier.SupplierName);

	    	prepStmt.executeUpdate();

	    	ResultSet rs = prepStmt.getGeneratedKeys();
	    	if (rs.next()){
	    		insertedID = rs.getInt(1);
	    		supplier.Key = insertedID;
	    		loadedMap.put(supplier.Key, supplier);
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

	public Supplier Find (Integer key)
	{
		return (Supplier) abstractFind(key);
	}

	@Override
	public Supplier CreateGhost(Integer key)
	{
		return new Supplier(key);
	}

	@Override
	protected void doLoadLine(ResultSet reader, DomainObject obj) throws Exception
	{
		Supplier supplier = (Supplier) obj;
		supplier.SupplierName = reader.getString("SupplierName");
	}

	@Override
	protected PreparedStatement findStatement() {
		// TODO Auto-generated method stub
		return null;
	}
}