package org.eap.patterns.domainlogic.transactionscript;

import java.util.Date;


import org.eap.dao.Result;
import org.eap.dao.businessobject.ContractStatement;
import org.eap.dao.businessobject.RevenueRecognition;

public class RecognitionGateway implements Gateway
{
	@Override
	public Result<RevenueRecognition> FindRecognitionsFor(int contractId, Date asOfDate) {
		return org.eap.dao.datasource.mock.RevenueRecognition.loadRecognitionStatement(contractId, asOfDate);
	}

	@Override
	public Result<ContractStatement> FindContract(int contractId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void InsertRecognition(int contractId, double amount, Date asOfDate) {
		// TODO Auto-generated method stub
	}
}
