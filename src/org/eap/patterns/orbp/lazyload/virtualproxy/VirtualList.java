package org.eap.patterns.orbp.lazyload.virtualproxy;

import java.sql.SQLException;
import java.util.ArrayList;

import org.eap.dao.DataTable;

public class VirtualList<T>
{
	private DataTable<T> source;
	private VirtualLoader<T> loader;

	public VirtualList(VirtualLoader<T> loader)
	{
		this.loader = loader;
	}

	private DataTable<T> getSource() throws SQLException
	{
		if (source == null) source = loader.load();
			return source;
	}

	public int size() throws SQLException
	{
		return getSource().size();
	}

	public boolean isEmpty() throws SQLException
	{
		return getSource().isEmpty();
	}

	public boolean isLoaded()
	{
		return loader.isLoaded();
	}

	public VirtualLoader<T> load()
	{
		try {
			loader.load();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return loader;
	}

	public ArrayList<T> toList() throws Exception
	{
		return (ArrayList<T>) getSource().getRows();
	}

	public T getFirst() throws Exception
	{
		return getSource().getRows().get(0);
	}
}