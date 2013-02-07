package org.eap.patterns.domainlogic.domainmodel;

public class CompleteRecognitionStrategy implements RecognitionStrategy
{

	@Override
	public void CalculateRecognitions(Contract contract) {
		contract.AddRevenueRecognition(new RevenueRecognition(contract.getRevenue(), contract.getWhenSigned()));
	}

}
