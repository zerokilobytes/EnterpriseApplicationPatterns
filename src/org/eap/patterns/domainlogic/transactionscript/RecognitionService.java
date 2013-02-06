package org.eap.patterns.domainlogic.transactionscript;

import java.sql.Date;

public interface RecognitionService 
{
	double GetRecognizedRevenue(int contractId, Date asOfDate);
	void CalculateRevenueRecognitions(int contractId);
}
