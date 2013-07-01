package org.eap.patterns.orbp.lazyload.ghost;

public abstract class DomainObject
{
	protected Integer Key;
	LoadStatus Status;

	public DomainObject (Integer key)
	{
	   this.Key = key;
	}

	public Boolean isGhost()
	{
		return Status == LoadStatus.GHOST;
	}

	public Boolean isLoaded()
	{
	   return Status == LoadStatus.LOADED;
	}

	public void markLoading()
	{
		Status = LoadStatus.LOADING;
	}

	public void markLoaded()
	{
	   Status = LoadStatus.LOADED;
	}

	protected abstract void load() throws Exception;
}