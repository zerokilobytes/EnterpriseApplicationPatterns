package org.eap.dao.businessobject;

import java.util.Date;

public class OrderItem
{
	public int OrderItemID;
	public int ProductID;
	public int Quanty;
	public double Discount;
	public Date OrderDate;
	public Date ShippingDate;
	public boolean Delivered;
}
