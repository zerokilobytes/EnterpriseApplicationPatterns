package org.eap.patterns.domainlogic.transactionscript;

import org.eap.dao.Result;
import org.eap.dao.businessobject.*;

public interface Gateway 
{
	Result<Product> loadProductsInStock();
}
