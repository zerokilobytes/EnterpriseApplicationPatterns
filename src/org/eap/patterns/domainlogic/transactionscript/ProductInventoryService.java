package org.eap.patterns.domainlogic.transactionscript;


import java.util.Iterator;

import org.eap.dao.Result;
import org.eap.dao.businessobject.Product;

/**
 * ProductInventoryService class
 * @author Markel Mairs
 */
public class ProductInventoryService implements ProductService
{
	Gateway gateway;
	
	public ProductInventoryService(Gateway gateway)
	{
		this.gateway = gateway;
	}
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
            Result<Product> result = null;

            // Find get products by supplier
    		result = this.gateway.getProductsBySupplier(supplierID);

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
