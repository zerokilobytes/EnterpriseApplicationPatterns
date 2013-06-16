package org.eap.patterns.domainlogic.transactionscript;

/**
 * ProductInventoryService class
 * @author zerobytes
 */
public interface ProductService 
{
	double calculateCostOfProductsBySupplier(int supplier) throws Exception;
	public String getProductNameByProductID(int productID) throws Exception;
}