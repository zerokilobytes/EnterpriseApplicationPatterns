/**
 * 
 */
package org.eap.dao.datasource.mock;

import org.eap.dao.Result;
import org.eap.dao.businessobject.Product;

/**
 * @author SNOW
 *
 */
public class ProductRepository implements org.eap.dao.repository.ProductRepository
{
	public Result<org.eap.dao.businessobject.Product> loadProductsInStock()
	{
		Result<Product> result = new Result<Product>();
		
		/*result.add(new RevenueRecognition(1, "Statement 1"));
		result.add(new RevenueRecognition(2, "Statement 2"));
		result.add(new RevenueRecognition(3, "Statement 3"));
		result.add(new RevenueRecognition(4, "Statement 4"));
		result.add(new RevenueRecognition(5, "Statement 5"));
		result.add(new RevenueRecognition(6, "Statement 6"));
		result.add(new RevenueRecognition(7, "Statement 7"));
		result.add(new RevenueRecognition(8, "Statement 8"));
		result.add(new RevenueRecognition(9, "Statement 9"));*/
		
		return result;
	}
}
