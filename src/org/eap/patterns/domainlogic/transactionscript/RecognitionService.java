package org.eap.patterns.domainlogic.transactionscript;

import java.util.Date;

public interface RecognitionService 
{
	double GetRecognizedRevenue(int contractId, Date asOfDate) throws Exception;
	void CalculateRevenueRecognitions(int contractId) throws Exception;
}
