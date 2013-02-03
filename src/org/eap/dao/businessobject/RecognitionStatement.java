package org.eap.dao.businessobject;

public class RecognitionStatement 
{
	public int Id;
	public String Name;

	public RecognitionStatement(int id, String name)
	{
		this.Id = id;
		this.Name = name;
	}
	public RecognitionStatement()
	{
		this.Id = -1;
	}
}
