package org.eap.patterns.domainlogic.domainmodel;

import java.util.Date;

public interface Contract 
{
	Product Product = null;
	double Revenue = 0;
	Date WhenSigned = null;
	int ContractId = 0;
	public void AddRevenueRecognition(RevenueRecognition revenueRecognition);
	
	public Product getProduct();
	public double getRevenue();
	public Date getWhenSigned();
	public int getContractId();
}
