package org.eap.patterns.dsap.activerecord;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import org.eap.dao.datasource.DB;


public class Customer extends org.eap.dao.businessobject.Customer
{
	public List<OrderItem> OrderItems;

	private Customer()
	{
	}

	/**
	 * Get the total cost of customer orders
	 * @return
	 */
	public double getTotalCostOfOrders()
	{
		double total = 0.00;

		for(OrderItem order : OrderItems)
		{
			total += (order.Product.Price * order.Quanty) * (1.00 - order.Discount);
		}
		return total;
	}
	
	public synchronized static Customer find(Integer customerID) throws SQLException
	{
		Customer customer = null;
		Connection connection = null;
		PreparedStatement  prepStmt = null;

		try 
		{
			connection = DB.getConnection();
			connection.setAutoCommit(true);

			prepStmt = connection.prepareStatement("SELECT * FROM Customer WHERE CustomerID = ?;");
			prepStmt.setInt(1, customerID);
			prepStmt.setMaxRows(1); 

			ResultSet rs = prepStmt.executeQuery();

			while (rs.next())
			{
				customer = new Customer();

				customer.CustomerID = rs.getInt("CustomerID");
				customer.FirstName = rs.getString("FirstName");
				customer.LastName = rs.getString("LastName");
				customer.AddressLine1 = rs.getString("AddressLine1");
				customer.AddressLine2 = rs.getString("AddressLine2");
				customer.City = rs.getString("City");
				customer.State = rs.getString("State");
				customer.Country = rs.getString("Country");
				customer.PhoneNumber = rs.getString("PhoneNumber");
				customer.Email = rs.getString("Email");
				customer.loadOrders();
			}
			rs.close();
			prepStmt.close();
			DB.closeConnection();
			return customer;
		}
		catch (Exception e) 
		{
	    	throw e;
		}
	}
	
	public synchronized static int insert(String firstName, String lastName, String addressLine1, String addressLine2, String city, String state, String country, String phoneNumber, String email) throws SQLException
	{
		Connection connection = null;
		PreparedStatement  prepStmt = null;

		try 
		{
	    	connection = DB.getConnection();
	    	connection.setAutoCommit(true);

	    	int insertedID = -1;

	    	String sql = "INSERT INTO Customer (FirstName, LastName, AddressLine1, AddressLine2,City, State, Country, PhoneNumber, Email) VALUES (?,?,?,?,?,?,?,?,?);";
	    	prepStmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

			prepStmt.setString(1, firstName);
			prepStmt.setString(2, lastName);
			prepStmt.setString(3, addressLine1);
			prepStmt.setString(4, addressLine2);
			prepStmt.setString(5, city);
			prepStmt.setString(6, state);
			prepStmt.setString(7, country);
			prepStmt.setString(8, phoneNumber);
			prepStmt.setString(9, email);

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
	
	public int insertCustomerOrder(OrderItem orderItem) throws Exception
	{
		Connection connection = null;
		PreparedStatement  prepStmt = null;

		try 
		{
	    	connection = DB.getConnection();
	    	connection.setAutoCommit(true);

	    	int insertedID = -1;

	    	String sql = "INSERT INTO OrderItem (OrderItemID) VALUES (?);";
	    	prepStmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

			prepStmt.setInt(1, orderItem.OrderItemID);

	    	prepStmt.executeUpdate();

	    	ResultSet rs = prepStmt.getGeneratedKeys();
	    	if (rs.next()){
	    		insertedID = rs.getInt(1);
	    		this.OrderItems.add(orderItem);
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
	 * Load orders for customers
	 * @throws SQLException 
	 */
	private void loadOrders() throws SQLException
	{
		OrderItems = OrderItem.findOrderItemsbyCustomerID(this.CustomerID);
	}
}
