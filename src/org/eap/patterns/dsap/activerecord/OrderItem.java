package org.eap.patterns.dsap.activerecord;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.eap.dao.DataTable;
import org.eap.dao.datasource.DB;

/**
 * Order class
 * @author zerobytes
 */
public class OrderItem extends org.eap.dao.domainobject.OrderItem
{
	public Product Product;

	private OrderItem()
	{

	}
	
	public synchronized static int insert(int productID, int quantity, double discount, Date orderItemDate, Date shippingDate, boolean delivered) throws SQLException
	{
		Connection connection = null;
		PreparedStatement  prepStmt = null;

		try 
		{
	    	connection = DB.getConnection();
	    	connection.setAutoCommit(true);

	    	int insertedID = -1;

	    	String sql = "INSERT INTO OrderItem (ProductID, Quantity, Discount, OrderDate, ShippingDate, Delivered) VALUES (?,?,?,?,?,?);";
	    	prepStmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

	    	prepStmt.setInt(1, productID);
	    	prepStmt.setInt(2, quantity);
	    	prepStmt.setDouble(3, discount);
	    	prepStmt.setDate(4, orderItemDate);
	    	prepStmt.setDate(5, shippingDate);
	    	prepStmt.setBoolean(6, delivered);

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

	public synchronized static OrderItem find(Integer orderID) throws SQLException
	{
		OrderItem order = null;
		Connection connection = null;
		PreparedStatement  prepStmt = null;
		try 
		{
			connection = DB.getConnection();
			connection.setAutoCommit(true);

			prepStmt = connection.prepareStatement("SELECT * FROM OrderItem WHERE OrderItemID = ?;");
			prepStmt.setInt(1, orderID);
			prepStmt.setMaxRows(1); 

			ResultSet rs = prepStmt.executeQuery();

			while (rs.next())
			{
				order = new OrderItem();
				order.OrderItemID 	= rs.getInt("OrderItemID");
				order.ProductID 	= rs.getInt("ProductID");
				order.Quantity 		= rs.getInt("Quantity");
				order.Discount 		= rs.getDouble("Discount");
				order.OrderDate 	= rs.getDate("OrderDate");
				order.ShippingDate 	= rs.getDate("ShippingDate");
				order.Delivered 	= rs.getBoolean("Delivered");

				order.loadProduct();
			}
			rs.close();
			prepStmt.close();
			DB.closeConnection();
			return order;
		}
		catch (Exception e) 
		{
	    	throw e;
		}
	}
	
	public synchronized static DataTable<OrderItem> findOrderItemsbyCustomerID(int customerID) throws SQLException
	{
		DataTable<OrderItem> result = new DataTable<OrderItem>();
		Connection connection = null;
		PreparedStatement  prepStmt = null;

		try 
	    {
			connection = DB.getConnection();
			connection.setAutoCommit(true);

			prepStmt = connection.prepareStatement("SELECT OT.* FROM CustomerOrder CO INNER JOIN OrderItem OT ON CO.OrderItemID	=  OT.OrderItemID WHERE CustomerID = ?;");
			prepStmt.setInt(1, customerID);
			prepStmt.setMaxRows(1); 

			ResultSet rs = prepStmt.executeQuery();

			while (rs.next()) 
			{
				OrderItem order = new OrderItem();

				order = new OrderItem();
				order.OrderItemID 	= rs.getInt("OrderItemID");
				order.ProductID 	= rs.getInt("ProductID");
				order.Quantity 		= rs.getInt("Quantity");
				order.Discount 		= rs.getDouble("Discount");
				order.OrderDate 	= rs.getDate("OrderDate");
				order.ShippingDate 	= rs.getDate("ShippingDate");
				order.Delivered 	= rs.getBoolean("Delivered");

				order.loadProduct();
				result.addRow(order);
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

	public Product getProduct()
	{
		return Product;
	}

	public void setProduct(Product product)
	{
		this.Product = product;
	}

	/**
	 * Load ordered Product
	 * @throws SQLException 
	 */
	private void loadProduct() throws SQLException
	{
		this.Product = org.eap.patterns.dsap.activerecord.Product.findProductByOrderItemID(this.OrderItemID);
		this.Product.OrderItem = this;
	}
}