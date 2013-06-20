package org.eap.patterns.dsap.rowdatagateway;

import java.sql.SQLException;

/**
 * Gateway class
 * @author zerobytes
 */
public interface Gateway 
{
	boolean delete() throws SQLException;
	boolean update() throws SQLException;
}