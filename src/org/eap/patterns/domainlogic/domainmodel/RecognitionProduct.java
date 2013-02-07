package org.eap.patterns.domainlogic.domainmodel;

public class RecognitionProduct implements Product
{
	 public String ProductName;
     public RecognitionStrategy RecognitionStrategy;
     public RecognitionProduct(String productName, RecognitionStrategy recognitionStrategy)
     {
         this.ProductName = productName;
         this.RecognitionStrategy = recognitionStrategy;
     }
     public void CalculateRecognitions(Contract contract)
     {
         this.RecognitionStrategy.CalculateRecognitions(contract);
     }
     
     public static Product NewWordProcessor(String productName)
     {
         return new RecognitionProduct(productName, new CompleteRecognitionStrategy());
     }
     public static Product NewSpreadsheet(String productName)
     {
         return new RecognitionProduct(productName, new ThreeWayRecognitionStrategy(60, 90));
     }
     public static Product NewDatabase(String productName)
     {
         return new RecognitionProduct(productName, new ThreeWayRecognitionStrategy(30,60));
     }

}
