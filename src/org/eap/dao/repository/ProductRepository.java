package org.eap.dao.repository;


import org.eap.dao.Result;
import org.eap.dao.businessobject.Product;

public interface ProductRepository
{
	public Result<Product> getProductsBySupplier(int supplierID);
}