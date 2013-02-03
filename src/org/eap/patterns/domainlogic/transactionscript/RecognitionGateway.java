package org.eap.patterns.domainlogic.transactionscript;

import java.util.Date;


import org.eap.dao.Result;
import org.eap.dao.businessobject.Contract;
import org.eap.dao.businessobject.RecognitionStatement;
import org.eap.dao.datasource.Mock;

public class RecognitionGateway implements Gateway
{
	@Override
	public Result<RecognitionStatement> FindRecognitionsFor(int contractId, Date asOfDate) {
		return Mock.loadRecognitionStatement();
	}

	@Override
	public Result<Contract> FindContract(int contractId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void InsertRecognition(int contractId, double amount, Date asOfDate) {
		// TODO Auto-generated method stub
	}
}
