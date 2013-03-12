package org.eap.patterns.domainlogic.transactionscript;

/**
 * ProductInventoryService class
 * @author Markel Mairs
 */
public interface ProductService 
{
	double calculateCostOfProductsBySupplier(int supplier) throws Exception;
	public String getProductNameByProductID(int productID) throws Exception;
}