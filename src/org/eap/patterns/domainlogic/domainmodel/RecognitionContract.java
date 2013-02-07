package org.eap.patterns.domainlogic.domainmodel;

import java.util.ArrayList;
import java.util.Date;


public class RecognitionContract implements Contract
{
    Product Product = null;
	double Revenue = 0;
    Date WhenSigned = null;
	int ContractId;
    ArrayList<RevenueRecognition> RevenueRecognitions;

    public RecognitionContract(Product product, double revenue, Date whenSigned)
    {
        this.Product = product;
        this.Revenue = revenue;
        this.WhenSigned = whenSigned;
        RevenueRecognitions = new ArrayList<RevenueRecognition>();
    }

	@Override
	public void AddRevenueRecognition(RevenueRecognition revenueRecognition) {
		 RevenueRecognitions.add(revenueRecognition);
	}

	@Override
	public Product getProduct() {
		// TODO Auto-generated method stub
		return Product;
	}

	@Override
	public double getRevenue() {
		// TODO Auto-generated method stub
		return Revenue;
	}

	@Override
	public Date getWhenSigned() {
		// TODO Auto-generated method stub
		return WhenSigned;
	}

	@Override
	public int getContractId() {
		// TODO Auto-generated method stub
		return ContractId;
	}

}
