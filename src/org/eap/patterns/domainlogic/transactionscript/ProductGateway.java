package org.eap.patterns.domainlogic.transactionscript;

import org.eap.dao.Result;
import org.eap.dao.businessobject.Product;
import org.eap.dao.datasource.Mock;

/**
 * ProductGateway class
 * 
 * @author Markel Mairs
 */
public class ProductGateway implements Gateway {
	/**
	 * Get Products by suppliers
	 */
	public Result<Product> getProductsBySupplier(int supplierID) {
		Result<Product> mock = Mock.getProducts();
		Result<Product> result = new Result<Product>();
		
		for(Product product : mock.Items)
		{
			if(product.SupplierID == supplierID)
				result.Items.add(product);
		}
		return result;
	}

	/**
	 * Get Products By ID
	 * 
	 * @param productID
	 * @return
	 */
	public Result<Product> getProductByID(int productID) {
		Result<Product> result = Mock.getProductByID(productID);
		return result;
	}
}