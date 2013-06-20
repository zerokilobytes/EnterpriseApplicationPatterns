package org.eap.patterns.domainlogic.transactionscript;


import java.util.Iterator;
import org.eap.dao.Result;
import org.eap.patterns.dsap.rowdatagateway.ProductGateway;

/**
 * ProductInventoryService class
 * @author zerobytes
 */
public class ProductInventoryService implements ProductService
{
	/**
	 * Get the 
	 * @throws Exception 
	 */
	@Override
	public double calculateCostOfProductsBySupplier(int supplierID) throws Exception 
	{
		// Revenue total
		double totalCost = 0.00;

		try
        {
            Result<ProductGateway> result = null;

            // Find get products by supplier
    		result = ProductGateway.getProductsBySupplier(supplierID);

    		// Compute the total amount
    	    Iterator<ProductGateway> iter = result.Items.iterator();
    	    while(iter.hasNext())
    	    {
    	    	ProductGateway item = iter.next();
    	    	totalCost += item.Price;
    	    }
        }
        catch (Exception e)
        {
            throw e;
        }

	 	return totalCost;
	}
}
