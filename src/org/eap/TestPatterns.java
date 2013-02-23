package org.eap;

import org.eap.patterns.domainlogic.domainmodel.DiscountBillingStrategy;
import org.eap.patterns.domainlogic.transactionscript.Gateway;
import org.eap.patterns.domainlogic.transactionscript.ProductGateway;
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
		tracsactionScript();
		domainModel();
	}

	private static void domainModel() {
		double cost = 0;
		DiscountBillingStrategy strategy = new DiscountBillingStrategy();
		cost = strategy.getCustomerOrdersCost(1);
		System.out.println("The cost of orders is " + cost);
	}

	public static void tracsactionScript() {
		double cost = 0;
		
		// Create instance of gate way
        Gateway gateway = new ProductGateway();
        
		ProductInventoryService service = new ProductInventoryService(gateway);
		try {
			cost = service.CalculateCostOfProductsBySupplier(1);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println("The cost of products by supplier is " + cost);
	}
}