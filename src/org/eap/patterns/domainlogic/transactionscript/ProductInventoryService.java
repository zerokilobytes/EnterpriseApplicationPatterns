package org.eap.patterns.domainlogic.transactionscript;


import java.util.Iterator;

import org.eap.dao.Result;
import org.eap.dao.businessobject.Product;

public class ProductInventoryService implements ProductService
{
	/**
	 * Get the 
	 * @throws Exception 
	 */
	@Override
	public double CalculateCostOfProductsBySupplier(int supplierID) throws Exception 
	{
		// Revenue total
		double totalCost = 0.00;

		try
        {
    		// Create instance of gate way
            Gateway gateway = new ProductGateway();
            Result<Product> result = null;

            // Find RevenueRecognition based on coontract ID and date
    		result = gateway.getProductsBySupplier(supplierID);

    		// Compute the total amount
    	    Iterator<Product> iter = result.Items.iterator();
    	    while(iter.hasNext())
    	    {
    	    	Product item = iter.next();
    	    	totalCost += item.Price;
    	    }
        }
        catch (Exception e)
        {
            throw new Exception();
        }

	 	return totalCost;
	}
}
