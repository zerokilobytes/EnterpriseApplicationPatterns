package org.eap.patterns.orbp.lazyload.ghost;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Hashtable;
import java.util.Map;

import org.eap.dao.datasource.DB;

public abstract class Mapper<T>
{
	Map<Integer, T> loadedMap = new Hashtable<Integer, T>();

	public T abstractFind (Integer key)
	{
		T result;
		result = loadedMap.get(key);
		if (result == null)
		{
			result = CreateGhost(key);
			loadedMap.put(key, result);
		}
		return result;
	}

	public void load (DomainObject obj) throws Exception
	{
        if (! obj.isGhost())
        	return;

		PreparedStatement  prepStmt = findStatement();
		ResultSet rs = prepStmt.executeQuery();
		loadLine(rs, obj);
		prepStmt.close();
		DB.closeConnection();
     }

	public void loadLine (ResultSet reader, DomainObject obj) throws Exception
	{
		if (obj.isGhost())
		{
			obj.markLoading();
			doLoadLine (reader, obj);
			obj.markLoaded();
		}
	}

	protected abstract void doLoadLine (ResultSet reader, DomainObject obj) throws Exception;
	protected abstract PreparedStatement findStatement();
	public abstract T CreateGhost(Integer key);
}