package org.eap.dao.datasource;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.*;
import java.util.Scanner;

public class SQLite implements DataSource
{
	protected Connection connection = null;
	
	public SQLite()
	{
	    createTable();
	}
	protected boolean openConnection() 
	{
		try 
		{
			Class.forName("org.sqlite.JDBC");
			connection = DriverManager.getConnection("jdbc:sqlite:test.db");
			return true;
		} 
		catch ( Exception e ) 
		{
			System.err.println( e.getClass().getName() + ": " + e.getMessage() );
		}
		return false;
	}

	public Connection getConnection()
	{
		openConnection();
		return connection;
	}
	
	public boolean closeConnection()
	{
		try {
			if(!connection.isClosed())
			connection.close();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	protected void createTable()
	{
		openConnection();
		
	    Statement stmt = null;
	    try
	    {
	     
	      String[] sql = getSetupScript(); 
	      
	      for(String statement: sql)
	      {
	    	  stmt = connection.createStatement();
	    	  statement = statement.trim();
	    	  stmt.execute(statement);
	    	  stmt.close();
	      }
	      connection.close();
	    } 
	    catch ( Exception e ) 
	    {
	      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	    }
	}

	protected String[] getSetupScript()
	{
		try
		{
			return new Scanner( new File("sql/eap.sql") ).useDelimiter("\\A").next().split("<END-OF-STATEMENT>");
		}
		catch (FileNotFoundException e)
		{
			e.printStackTrace();
		}
		return null;
	}

	public static String convertStreamToString(InputStream is) 
	{
	    @SuppressWarnings("resource")
		java.util.Scanner s = new Scanner(is).useDelimiter("\\A");
	    return s.hasNext() ? s.next() : "";
	}
}