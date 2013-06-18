package org.eap.patterns.dsap.rowdatagateway;

import java.sql.SQLException;
import org.eap.dao.businessobject.Product;

public class ProductGateway extends Product
{
	/**
	 * Constructor
	 */
	private ProductGateway()
	{
		
	}
	/**
	 * @return the productID
	 */
	public synchronized int getProductID() {
		return ProductID;
	}
	/**
	 * @param productID the productID to set
	 */
	public synchronized void setProductID(int productID) {
		ProductID = productID;
	}
	/**
	 * @return the supplierID
	 */
	public synchronized int getSupplierID() {
		return SupplierID;
	}
	/**
	 * @param supplierID the supplierID to set
	 */
	public synchronized void setSupplierID(int supplierID) {
		SupplierID = supplierID;
	}
	/**
	 * @return the price
	 */
	public synchronized double getPrice() {
		return Price;
	}
	/**
	 * @param price the price to set
	 */
	public synchronized void setPrice(double price) {
		Price = price;
	}
	/**
	 * @return the productName
	 */
	public synchronized String getProductName() {
		return ProductName;
	}
	/**
	 * @param productName the productName to set
	 */
	public synchronized void setProductName(String productName) {
		ProductName = productName;
	}
	/**
	 * @return the productDescription
	 */
	public synchronized String getProductDescription() {
		return ProductDescription;
	}
	/**
	 * @param productDescription the productDescription to set
	 */
	public synchronized void setProductDescription(String productDescription) {
		ProductDescription = productDescription;
	}
	/**
	 * @return the inStock
	 */
	public synchronized Boolean getInStock() {
		return InStock;
	}
	/**
	 * @param inStock the inStock to set
	 */
	public synchronized void setInStock(Boolean inStock) {
		InStock = inStock;
	}

	/**
	 * 
	 * @return
	 * @throws SQLException
	 */
	public synchronized boolean delete() throws SQLException
	{
		return false;
	}
	
	/**
	 * 
	 * @return
	 * @throws SQLException
	 */
	public synchronized boolean update() throws SQLException
	{
		return false;
	}
	
	/**
	 * 
	 * @param productID
	 * @return
	 * @throws SQLException
	 */
	public synchronized static ProductGateway find(Integer productID) throws SQLException
	{
		return null;
	}

	/**
	 * 
	 * @param supplierID
	 * @param price
	 * @param productName
	 * @param productDescription
	 * @param inStock
	 * @return
	 * @throws SQLException
	 */
	public synchronized static int insert(Integer supplierID, double price, String productName, String productDescription, Boolean inStock) throws SQLException
	{
		return 0;
	}
	
	/**
	 * 
	 * @param product
	 * @return
	 * @throws SQLException
	 */
	public synchronized static int insert(ProductGateway product) throws SQLException
	{
		return 0;
	}

	/**
	 * @return
	 * @throws SQLException
	 */
	public synchronized static ProductGateway[] findAll() throws SQLException
	{
		return null;
	}
}
