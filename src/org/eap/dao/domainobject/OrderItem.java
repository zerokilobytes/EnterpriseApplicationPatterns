package org.eap.dao.domainobject;

import java.sql.Date;

public class OrderItem
{
	public int OrderItemID;
	public int ProductID;
	public int Quantity;
	public double Discount;
	public Date OrderDate;
	public Date ShippingDate;
	public boolean Delivered;
}
