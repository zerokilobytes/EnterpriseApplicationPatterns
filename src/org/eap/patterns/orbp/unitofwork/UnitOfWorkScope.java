package org.eap.patterns.orbp.unitofwork;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;

import org.eap.dao.datasource.DB;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

@SuppressWarnings("rawtypes")
public class UnitOfWorkScope
{
	private Connection connection;

	private ArrayList newObjects = new ArrayList();
	private ArrayList dirtyObjects = new ArrayList();
	private ArrayList removedObjects = new ArrayList();

	private static ThreadLocal current = new ThreadLocal();
	
	private UnitOfWorkScope()
	{
		connection = DB.getConnection();

		try {
			connection.setAutoCommit(false);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public Connection getConnection()
	{
		return connection;
	}

	@SuppressWarnings("unchecked")
	public void registerNew(DomainObject obj) 
	{
		 if(!dirtyObjects.contains(obj) &&  !removedObjects.contains(obj) &&  !newObjects.contains(obj))
		 {
			 newObjects.add(obj);
		 }
	}

	@SuppressWarnings("unchecked")
	public void registerDirty(DomainObject obj)
	{
		if (!removedObjects.contains(obj) && !dirtyObjects.contains(obj) && !newObjects.contains(obj))
		{
			dirtyObjects.add(obj);
		}
	}

	@SuppressWarnings("unchecked")
	public void registerRemoved(DomainObject obj)
	{
		if(newObjects.remove(obj)) 
			return;

		dirtyObjects.remove(obj);

		if(!removedObjects.contains(obj)) 
		{
			removedObjects.add(obj);
		}
	}

	public void registerClean(DomainObject obj) 
	{
		throw new NotImplementedException();
	}

	public void commit() throws SQLException
	{
		insertNew();
		updateDirty();
		deleteRemoved();

		connection.commit();
		DB.closeConnection();
	}

	private void deleteRemoved()  throws SQLException
	{
		// TODO Auto-generated method stub
	}

	private void updateDirty()  throws SQLException
	{
		// TODO Auto-generated method stub
	}

	private void insertNew() throws SQLException
	{
		for (Iterator objects = (Iterator) newObjects.iterator(); objects.hasNext();)
		{
			DomainObject obj = (DomainObject) objects.next();
			obj.insert();
		}
	}

	public static void newCurrent()
	{
		setCurrent(new UnitOfWorkScope());
	}

	@SuppressWarnings("unchecked")
	public static void setCurrent(UnitOfWorkScope scope) 
	{
		current.set(scope);
	}

	public static UnitOfWorkScope getCurrent() 
	{
		return (UnitOfWorkScope) current.get();
	}
}
