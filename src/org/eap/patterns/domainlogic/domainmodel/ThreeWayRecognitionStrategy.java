package org.eap.patterns.domainlogic.domainmodel;

import java.util.Calendar;

public class ThreeWayRecognitionStrategy implements RecognitionStrategy
{
	int firstRecognitionOffset = 0;
	int secondRecognitionOffset = 0;
	
	public ThreeWayRecognitionStrategy(int firstRecognitionOffset,
	    int secondRecognitionOffset)
	{
	    this.firstRecognitionOffset = firstRecognitionOffset;
	    this.secondRecognitionOffset = secondRecognitionOffset;
	}

	@Override
	public void CalculateRecognitions(Contract contract) {
		double allocation = (contract.getRevenue()) / 3;

		Calendar cal = Calendar.getInstance();
		
        cal.setTime(contract.getWhenSigned());
	    contract.AddRevenueRecognition(new RevenueRecognition(allocation, cal.getTime()));
	    
	    cal.setTime(contract.getWhenSigned());
	    cal.add(Calendar.DATE, firstRecognitionOffset);
	    contract.AddRevenueRecognition(new RevenueRecognition(allocation, cal.getTime()));
	    
	    cal.setTime(contract.getWhenSigned());
	    cal.add(Calendar.DATE, secondRecognitionOffset);
	    contract.AddRevenueRecognition(new RevenueRecognition(allocation, cal.getTime()));
	}

}
