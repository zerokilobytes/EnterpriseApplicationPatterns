package org.eap.patterns.domainlogic.domainmodel;

import java.util.Date;

public class RevenueRecognition implements Recognition
{
	 public double Amount = 0;
     public Date RecognitionDate = null;
     public boolean IsRecognizable(Date asOfDate)
     {
         if (asOfDate.after(RecognitionDate))
         {
             return true;
         }
         else
         {
             return false;
         }
     }
     public RevenueRecognition(double amount, Date asOfDate)
     {
         this.Amount = amount;
         this.RecognitionDate = asOfDate;
     }
}
