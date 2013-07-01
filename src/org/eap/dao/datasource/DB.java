package org.eap.dao.datasource;
import java.sql.*;

public  class DB 
{
	private static DataSource dataSource = null;
	
	public synchronized static void setDataSource(DataSource ds)
	{
		dataSource = ds;
	}
	public synchronized static Connection getConnection()
	{
		return dataSource.getConnection();
	}
	
	public synchronized static boolean closeConnection()
	{
		return dataSource.closeConnection();
	}
	
	public synchronized static boolean executeQuery(String statement)
	{
		return dataSource.executeQuery(statement);
	}
}