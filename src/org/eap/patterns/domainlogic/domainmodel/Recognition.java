package org.eap.patterns.domainlogic.domainmodel;

import java.util.Date;

public interface Recognition 
{
	double Amount = 0;
	Date RecognitionDate = null;
	boolean IsRecognizable(Date asOfDate);
}
