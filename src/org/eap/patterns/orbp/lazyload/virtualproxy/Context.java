package org.eap.patterns.orbp.lazyload.virtualproxy;

import java.sql.SQLException;

public class Context
{
	public static class Product implements ProxyObject<Product>
	{
		public VirtualList<org.eap.dao.domainobject.Product> getProductsByID(Integer productID) throws SQLException
		{
			ProductLoader loader = new ProductLoader(productID);
			VirtualList<org.eap.dao.domainobject.Product> vList = new VirtualList<org.eap.dao.domainobject.Product>(loader);
			return vList;
		}
	}

	public static class Supplier implements ProxyObject<Supplier>
	{
	}
}