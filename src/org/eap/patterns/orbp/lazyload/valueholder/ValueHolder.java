package org.eap.patterns.orbp.lazyload.valueholder;

import java.sql.SQLException;

import org.eap.dao.DataTable;

public class ValueHolder<T>
{
	private DataTable<T> value;
	private ValueLoader<T> loader;

	public ValueHolder(ValueLoader<T> loader)
	{
		this.loader = loader;
	}

	public DataTable<T> getValue() throws SQLException
	{
		if (value == null) 
			value = loader.load();

		return value;
	}
}