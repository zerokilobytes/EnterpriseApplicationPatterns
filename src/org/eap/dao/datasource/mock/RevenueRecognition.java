/**
 * 
 */
package org.eap.dao.datasource.mock;

import java.util.Date;

import org.eap.dao.Result;
import org.eap.dao.businessobject.Contract;



/**
 * @author SNOW
 *
 */
public class RevenueRecognition implements org.eap.dao.repository.RevenueRecognition
{
	public static Result<org.eap.dao.businessobject.RevenueRecognition> loadRecognitionStatement(int contractId, Date asOfDate)
	{
		Result<org.eap.dao.businessobject.RevenueRecognition> result = new Result<org.eap.dao.businessobject.RevenueRecognition>();
		
		/*result.add(new RevenueRecognition(1, "Statement 1"));
		result.add(new RevenueRecognition(2, "Statement 2"));
		result.add(new RevenueRecognition(3, "Statement 3"));
		result.add(new RevenueRecognition(4, "Statement 4"));
		result.add(new RevenueRecognition(5, "Statement 5"));
		result.add(new RevenueRecognition(6, "Statement 6"));
		result.add(new RevenueRecognition(7, "Statement 7"));
		result.add(new RevenueRecognition(8, "Statement 8"));
		result.add(new RevenueRecognition(9, "Statement 9"));*/
		
		return result;
	}
	public static Result<Contract> loadContract()
	{
		return null;
	}
}
