package org.eap.dao;

/**
 * 
 * @author zerobytes
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