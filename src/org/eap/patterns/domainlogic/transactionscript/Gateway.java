package org.eap.patterns.domainlogic.transactionscript;

import java.util.Date;

import org.eap.dao.Result;
import org.eap.dao.businessobject.*;

public interface Gateway 
{
	Result<RevenueRecognition> FindRecognitionsFor(int contractId, Date asOfDate);
	   
	Result<Contract> FindContract(int contractId);
	   
	void InsertRecognition(int contractId, double amount, Date asOfDate);
}
