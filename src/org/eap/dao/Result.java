package org.eap.dao;


public class Result<T>
{
	public ResultItem<T> Items;

	public Result()
	{
		Items = new  ResultItem<T>();
	}
}
