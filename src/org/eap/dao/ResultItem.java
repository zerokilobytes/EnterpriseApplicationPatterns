package org.eap.dao;

import java.util.ArrayList;

/**
 * 
 * @author zerobytes
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