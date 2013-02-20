package org.eap.patterns.domainlogic.transactionscript;

import org.eap.dao.Result;
import org.eap.dao.businessobject.*;

/**
 * Gateway class
 * @author Markel Mairs
 */
public interface Gateway 
{
	Result<Product> getProductsBySupplier(int supplierID);
}