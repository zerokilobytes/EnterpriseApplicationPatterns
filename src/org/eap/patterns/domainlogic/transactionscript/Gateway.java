package org.eap.patterns.domainlogic.transactionscript;

import org.eap.dao.Result;
import org.eap.dao.businessobject.*;

/**
 * Gateway class
 * @author zerobytes
 */
public interface Gateway 
{
	Result<Product> getProductsBySupplier(int supplierID);
	Result<Product> getProductByID(int productID);
}