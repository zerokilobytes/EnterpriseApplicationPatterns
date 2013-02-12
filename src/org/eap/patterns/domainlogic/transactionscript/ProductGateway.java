package org.eap.patterns.domainlogic.transactionscript;


import org.eap.dao.Result;
import org.eap.dao.businessobject.Product;
import org.eap.dao.datasource.mock.ProductRepository;


public class ProductGateway implements Gateway
{

	@Override
	public Result<Product> getProductsBySupplier(int supplierID)
	{
		ProductRepository product = new ProductRepository();
		return product.getProductsBySupplier(supplierID);
	}
}
