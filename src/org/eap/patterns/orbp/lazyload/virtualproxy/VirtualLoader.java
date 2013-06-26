package org.eap.patterns.orbp.lazyload.virtualproxy;

import java.sql.SQLException;
import org.eap.dao.DataTable;

public interface VirtualLoader<T>
{
	DataTable<T> load() throws SQLException;
	boolean isLoaded();
}