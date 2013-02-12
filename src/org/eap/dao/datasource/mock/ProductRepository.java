/**
 * 
 */
package org.eap.dao.datasource.mock;

import org.eap.dao.Result;
import org.eap.dao.businessobject.Product;
import org.eap.dao.datasource.Mock;

/**
 * @author SNOW
 *
 */
public class ProductRepository implements org.eap.dao.repository.ProductRepository
{
	public Result<org.eap.dao.businessobject.Product> getProductsBySupplier(int supplierID)
	{
		Result<Product> result = Mock.getProducts();	
		return result;
	}

	public Result<org.eap.dao.businessobject.Product> GetProductsByID(int productID) {
		Result<Product> result = Mock.getProductBiID(productID);	
		return result;
	}
}
