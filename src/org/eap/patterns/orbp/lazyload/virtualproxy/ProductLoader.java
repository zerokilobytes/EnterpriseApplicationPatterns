package org.eap.patterns.orbp.lazyload.virtualproxy;

import java.sql.SQLException;
import org.eap.dao.DataTable;
import org.eap.dao.domainobject.Product;

public class ProductLoader implements VirtualLoader<Product>
{
	private int productID;
	private boolean isLoaded = false;

	public ProductLoader(Integer productID) 
	{
		this.productID = productID;
	}

	@Override
	public DataTable<Product> load() throws SQLException
	{
		DataTable<Product> result =  ProductMapper.create().getProductByID(productID);
		isLoaded = true;
		return result;
	}

	@Override
	public boolean isLoaded()
	{
		return isLoaded;
	}
}