package org.eap.dao;

import java.util.Hashtable;

public class DataSet
{
	private Hashtable<String, Object> tables;

	public DataSet()
	{
		tables = new Hashtable<String, Object>();
	}

	public DataTable<?> getTable(String tableName)
	{
		return (DataTable<?>)tables.get(tableName);
	}

	public void addTable(String tableName, DataTable<?> table)
	{
		tables.put(tableName, table);
	}
}