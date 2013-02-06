package org.eap.patterns.domainlogic.transactionscript;

import java.sql.Date;

import org.eap.dao.Result;
import org.eap.dao.ResultItem;
import org.eap.dao.businessobject.RevenueRecognition;

public class RevenueRecognitionService implements RecognitionService
{
	@Override
	public double GetRecognizedRevenue(int contractId, Date asOfDate) {
		double result = 0;
        Gateway gateway = new RecognitionGateway();
        Result<RevenueRecognition> datatable = null;

        try
        {
            datatable = gateway.FindRecognitionsFor(contractId, asOfDate);
            for(RevenueRecognition dataRow : datatable.Items)
            {
                result += dataRow.Amount;
            }
        }
        catch (Exception sqlException)
        {
            
        }

        return result;
	}

	@Override
	public void CalculateRevenueRecognitions(int contractId) {
		// TODO Auto-generated method stub
		
	}
}
