package org.eap.dao.datasource;

import java.sql.Connection;

interface DataSource 
{
	public Connection getConnection();
	public boolean closeConnection();
	boolean executeQuery(String statement);
}
