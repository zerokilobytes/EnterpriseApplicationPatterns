package org.eap.patterns.domainlogic.tablemodule;

import org.eap.dao.DataSet;

public class Product extends TableModule
{
	public Product(DataSet ds) {
		super(ds);
		// TODO Auto-generated constructor stub
	}

	public double getCostOfProduct(int productID)
	{
		return 0.0;
	}
}
