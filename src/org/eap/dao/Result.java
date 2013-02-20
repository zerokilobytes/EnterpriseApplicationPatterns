package org.eap.dao;

/**
 * 
 * @author Markel Mairs
 *
 * @param <T> Result Type
 */
public class Result<T>
{
	public ResultItem<T> Items;

	public Result()
	{
		Items = new  ResultItem<T>();
	}
}