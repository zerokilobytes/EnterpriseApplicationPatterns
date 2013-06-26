package org.eap.dao.datasource;
import java.sql.*;

public class DB 
{
	private static DataSource dataSource = null;
	
	public static void setDataSource(DataSource ds)
	{
		dataSource = ds;
	}
	public static Connection getConnection()
	{
		return dataSource.getConnection();
	}
	
	public static boolean closeConnection()
	{
		return dataSource.closeConnection();
	}
	
	public static boolean executeQuery(String statement)
	{
		return dataSource.executeQuery(statement);
	}
}