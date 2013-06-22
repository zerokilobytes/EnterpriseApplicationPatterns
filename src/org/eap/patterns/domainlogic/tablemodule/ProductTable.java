package org.eap.patterns.domainlogic.tablemodule;

import org.eap.dao.DataRowCollection;
import org.eap.dao.DataSet;
import org.eap.dao.domainobject.Product;


public class ProductTable extends TableModule
{
	public ProductTable(DataSet ds) {
		super(ds);
		this.tableName = "Product";
	}
	
	public Product GetRowByID(int Id)
	{
		DataRowCollection<?> rows =  this.GetRows();
		Product product = null;

		for(Object obj : rows.toArray())
		{
			product = (Product)obj;
			if(product.ProductID == Id)
				return product;
		}
		return null;
	}

	public double getCostOfProduct(int productID)
	{
		Product product = GetRowByID(productID);
		return product.Price;
	}
}