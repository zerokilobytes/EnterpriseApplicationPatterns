package org.eap;

import org.eap.dao.ResultItem;
import org.eap.dao.businessobject.Person;

/**
 * @author Zerobytes
 * 
 *
 */
public class TestPatterns
{
	public static void main(String[] args)
	{
		ResultItem<Person> persons = new ResultItem<Person>();
		persons.add(new Person());

		
		System.out.println("Hello!");
		
	}
}