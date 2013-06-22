package org.eap.patterns.orbp.unitofwork;

import java.util.ArrayList;
import java.util.Iterator;

@SuppressWarnings("rawtypes")
public class UnitOfWorkScope
{
	private ArrayList newObjects = new ArrayList();
	private ArrayList dirtyObjects = new ArrayList();
	private ArrayList removedObjects = new ArrayList();
	
	private static ThreadLocal current = new ThreadLocal();

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
		if(!removedObjects.contains(obj))
		{
			if (!dirtyObjects.contains(obj) && !newObjects.contains(obj))
			{
				dirtyObjects.add(obj);
			}
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
	
	}
	
	public void commit()
	{
		insertNew();
		updateDirty();
		deleteRemoved();
	}
	private void deleteRemoved()
	{
		// TODO Auto-generated method stub
	}

	private void updateDirty()
	{
		// TODO Auto-generated method stub
	}

	private void insertNew()
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
