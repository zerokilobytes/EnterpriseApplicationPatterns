package org.eap.patterns.domainlogic.transactionscript;

/**
 * ProductInventoryService class
 * @author Markel Mairs
 */
public interface ProductService 
{
	double CalculateCostOfProductsBySupplier(int supplier) throws Exception;
}