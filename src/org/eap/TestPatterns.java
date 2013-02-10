package org.eap;

import org.eap.patterns.domainlogic.transactionscript.ProductInventoryService;


/**
 * @author Zerobytes
 * 
 *
 */
public class TestPatterns
{
	public static void main(String[] args)
	{

		
		double cost = 0;
		ProductInventoryService service = new ProductInventoryService();
		try {
			cost = service.CalculateCostOfProductsBySupplier(1);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("The cost of products by supplier is " + cost);
		
	}
}