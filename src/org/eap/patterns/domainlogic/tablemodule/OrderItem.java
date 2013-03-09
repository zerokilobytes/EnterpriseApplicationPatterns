package org.eap.patterns.domainlogic.tablemodule;

import org.eap.dao.DataSet;

public class OrderItem extends TableModule
{
	public OrderItem(DataSet ds) {
		super(ds);
		// TODO Auto-generated constructor stub
	}

	public OrderItem findOrder(int OrderID)
	{
		//OrderItem item = super.GetRow(OrderID);
		return null;
	}
	
	public int insertOrder(int productID)
	{
		return 0;
	}

	public int deleteOrder(int productID)
	{
		return 0;
	}
}
