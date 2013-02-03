package org.eap;

import org.eap.dao.Result;
import org.eap.dao.businessobject.RecognitionStatement;
import org.eap.patterns.domainlogic.transactionscript.RecognitionGateway;

/**
 * @author Zerobytes
 * 
 *
 */
public class TestPatterns
{
	public static void main(String[] args)
	{
		RecognitionGateway gw = new RecognitionGateway();
		Result<RecognitionStatement> r = gw.FindRecognitionsFor(0, null);
		
		System.out.println("Hello!"); 
	}
}