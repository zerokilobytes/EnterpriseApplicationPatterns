package org.eap.patterns.orbp.unitofwork;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.eap.dao.datasource.DB;

public class Supplier extends org.eap.dao.domainobject.Supplier implements DomainObject
{
	private int SupplierID;

	public int getSupplierID() {
		return SupplierID;
	}

	private Supplier()
	{
		
	}

	public Supplier(String supplierName)
	{
		this.SupplierName = supplierName;
		this.markNew();
	}

	public String getSupplierName() {
		return SupplierName;
	}
	public void setSupplierName(String supplierName) {
		SupplierName = supplierName;
		markDirty();
	}

	private void setSupplierID(int insertedID)
	{
		this.SupplierID = insertedID;
	}

	public Connection getConnection()
	{
		return UnitOfWorkScope.getCurrent().getConnection();
	}

	@Override
	public void markNew() {
		UnitOfWorkScope.getCurrent().registerNew(this);
	}

	@Override
	public void markClean() {
		UnitOfWorkScope.getCurrent().registerClean(this);
	}

	@Override
	public void markDirty() {
		UnitOfWorkScope.getCurrent().registerDirty(this);
	}

	@Override
	public void markRemoved() {
		UnitOfWorkScope.getCurrent().registerRemoved(this);
		
	}

	@Override
	public synchronized boolean insert() throws SQLException
	{
		Connection connection = null;
		PreparedStatement  prepStmt = null;

		try 
		{
	    	connection = getConnection();

	    	int insertedID = -1;

	    	String sql = "INSERT INTO Supplier (SupplierName) VALUES (?);";
	    	prepStmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

	    	prepStmt.setString(1, this.SupplierName);

	    	prepStmt.executeUpdate();

	    	ResultSet rs = prepStmt.getGeneratedKeys();
	    	if (rs.next()){
	    		insertedID = rs.getInt(1);
	    		this.setSupplierID(insertedID);
	    	}

			prepStmt.close();
			return insertedID > -1;
		}
		catch(Exception e)
		{
			throw e;
		}
	}

	public synchronized static Supplier find(Integer supplierID) throws SQLException
	{
		Supplier supplier = null;
		Connection connection = null;
		PreparedStatement  prepStmt = null;
		try 
		{
			connection = DB.getConnection();

			prepStmt = connection.prepareStatement("SELECT * FROM Supplier WHERE SupplierID = ?;");
			prepStmt.setInt(1, supplierID);
			prepStmt.setMaxRows(1); 

			ResultSet rs = prepStmt.executeQuery();

			while (rs.next())
			{
				supplier = new Supplier();
				supplier.SupplierID 		= rs.getInt("SupplierID");
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

	public static Supplier create(String supplierName)
	{
		Supplier supplier = new Supplier();
		supplier.SupplierName = supplierName;

		supplier.markNew();
		return supplier;
	}

	@Override
	public synchronized boolean update() throws SQLException
	{
		Connection connection = null;
		PreparedStatement  prepStmt = null;
		int affectedRows = 0;

		try 
		{
			connection = getConnection();

	    	String sql = "UPDATE Supplier SET SupplierName = ? WHERE SupplierID = ?";
	    	prepStmt = connection.prepareStatement(sql);

	    	prepStmt.setString(1, this.SupplierName);
	    	prepStmt.setInt(2, this.SupplierID);

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

	@Override
	public synchronized boolean delete() throws SQLException
	{
		Connection connection = null;
		PreparedStatement  prepStmt = null;

		try 
		{
			connection = getConnection();

	    	int affectedRows = 0;

	    	String sql = "DELETE FROM Supplier WHERE SupplierID = ?;";
	    	prepStmt = connection.prepareStatement(sql);

	    	prepStmt.setInt(1, this.SupplierID);
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