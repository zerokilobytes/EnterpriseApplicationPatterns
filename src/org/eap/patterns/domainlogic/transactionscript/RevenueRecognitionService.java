package org.eap.patterns.domainlogic.transactionscript;

import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;

import org.eap.dao.Result;
import org.eap.dao.businessobject.ContractStatement;
import org.eap.dao.businessobject.RevenueRecognition;

public class RevenueRecognitionService implements RecognitionService
{
	/**
	 * Get the 
	 * @throws Exception 
	 */
	@Override
	public double GetRecognizedRevenue(int contractId, Date asOfDate) throws Exception 
	{
		// Revenue total
		double revenue = 0;

		try
        {
    		// Create instance of gate way
            Gateway gateway = new RecognitionGateway();
            Result<RevenueRecognition> result = null;

            // Find RevenueRecognition based on coontract ID and date
    		result = gateway.FindRecognitionsFor(contractId, asOfDate);

    		// Compute the total of revenue amount
    	    Iterator<RevenueRecognition> iter = result.Items.iterator();
    	    while(iter.hasNext())
    	    {
    	    	RevenueRecognition item = iter.next();
    	    	revenue += item.Amount;
    	    }
        }
        catch (Exception e)
        {
            throw new Exception();
        }

	 	return revenue;
	}

	@Override
	public void CalculateRevenueRecognitions(int contractId) throws Exception
	{
		 Gateway gateway = new RecognitionGateway();
		 Result<ContractStatement> result = null;

		 try
         {
			 result = gateway.FindContract(contractId);
	         double totalRevenue = result.Items.get(0).Contract.Revenue;
	         Date recognitionDate =  result.Items.get(0).Contract.DateSigned;
	         String contractType =  result.Items.get(0).Product.ProductType;
	         double revenuePiece = 0;
	         
	         Calendar cal = Calendar.getInstance();

	         switch (contractType)
	         {
	             case "S":
	                 revenuePiece = totalRevenue / 3;
	                 
	                 gateway.InsertRecognition(contractId, revenuePiece, recognitionDate);
	                 
	                 cal.setTime(recognitionDate);
	                 cal.add(Calendar.DATE, 60);
	                 gateway.InsertRecognition(contractId, revenuePiece, cal.getTime());
	                 
	                 cal.setTime(recognitionDate);
	                 cal.add(Calendar.DATE, 90);
	                 gateway.InsertRecognition(contractId, revenuePiece, cal.getTime());
	                 break;
	             case "W":
	                 gateway.InsertRecognition(contractId, totalRevenue, recognitionDate);
	                 break;
	             case "D":
	                 revenuePiece = totalRevenue / 3;
	                 
	                 gateway.InsertRecognition(contractId, revenuePiece, recognitionDate);
	                 
	                 cal.setTime(recognitionDate);
	                 cal.add(Calendar.DATE, 30);
	                 gateway.InsertRecognition(contractId, revenuePiece, cal.getTime());
	                 
	                 cal.setTime(recognitionDate);
	                 cal.add(Calendar.DATE, 60);
	                 gateway.InsertRecognition(contractId, revenuePiece, cal.getTime());
	                 break;
	             default:
	                 break;
	         }
		}
	    catch (Exception e)
	    {
	        throw new Exception();
	    }
	}
}
