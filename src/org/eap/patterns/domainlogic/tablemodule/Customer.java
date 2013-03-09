package org.eap.patterns.domainlogic.tablemodule;

import org.eap.dao.DataSet;

public class Customer extends TableModule
{
	public Customer(DataSet ds) {
		super(ds);
		// TODO Auto-generated constructor stub
	}

	public double getCostOfOrders(int customerID)
	{
		return 0.0;
	}
}
