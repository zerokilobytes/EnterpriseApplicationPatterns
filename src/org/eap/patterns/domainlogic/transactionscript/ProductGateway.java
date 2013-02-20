package org.eap.patterns.domainlogic.transactionscript;


import org.eap.dao.Result;
import org.eap.dao.businessobject.Product;
import org.eap.dao.datasource.mock.ProductRepository;

/**
 * ProductGateway class
 * @author Markel Mairs
 */
public class ProductGateway implements Gateway
{
	/**
	 * Get Products by suppliers
	 */
	public Result<Product> getProductsBySupplier(int supplierID)
	{
		ProductRepository product = new ProductRepository();
		return product.getProductsBySupplier(supplierID);
	}
}
