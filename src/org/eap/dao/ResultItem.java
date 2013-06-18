package org.eap.dao;

import java.util.ArrayList;

/**
 * 
 * @author Markel Mairs
 *
 * @param <T>
 */
@SuppressWarnings("serial")
public class ResultItem<T> extends  ArrayList<T>
{
	public T get(int index)
	{
		if(this.isEmpty() || index >= this.size())
		{
			return null;
		}
		else
		{
			return super.get(index);
		}
	}
}