package org.eap.dao;

public class DataTable<T>
{
	public String Name;
	private DataRowCollection<T> Rows;
	
	public DataTable()
	{
		Rows = new DataRowCollection<T>();
	}

	public DataRowCollection<T> getRows()
	{
		return Rows;
	}
	
	public void addRow(T row)
	{
		Rows.add(row);
	}
}
