package org.eap.dao;

import java.util.Hashtable;

public class DataSet 
{
	private Hashtable<String, Object> tables;
	
	public DataTable getTable(String tableName)
	{
		return (DataTable)tables.get(tableName);
	}
}
