package org.eap.patterns.domainlogic.transactionscript;

public interface BillingService 
{
	long CalculateOrderCost(int orderID) throws Exception;
}
