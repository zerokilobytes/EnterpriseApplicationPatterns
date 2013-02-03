package org.eap.dao.datasource;

import org.eap.dao.Result;
import org.eap.dao.businessobject.Contract;
import org.eap.dao.businessobject.RecognitionStatement;



public class Mock
{
	public static Result<RecognitionStatement> loadRecognitionStatement()
	{
		Result<RecognitionStatement> result = new Result<RecognitionStatement>();
		
		result.add(new RecognitionStatement(1, "Statement 1"));
		result.add(new RecognitionStatement(2, "Statement 2"));
		result.add(new RecognitionStatement(3, "Statement 3"));
		result.add(new RecognitionStatement(4, "Statement 4"));
		result.add(new RecognitionStatement(5, "Statement 5"));
		result.add(new RecognitionStatement(6, "Statement 6"));
		result.add(new RecognitionStatement(7, "Statement 7"));
		result.add(new RecognitionStatement(8, "Statement 8"));
		result.add(new RecognitionStatement(9, "Statement 9"));
		
		return result;
	}
	public static Result<Contract> loadContract()
	{
		return null;
	}
		
}