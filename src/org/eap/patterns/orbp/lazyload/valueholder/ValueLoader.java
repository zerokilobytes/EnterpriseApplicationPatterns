package org.eap.patterns.orbp.lazyload.valueholder;

import java.sql.SQLException;

import org.eap.dao.DataTable;

public interface ValueLoader<T>
{
	DataTable<T> load() throws SQLException;
}