package org.eap.patterns.orbp.unitofwork;

import java.sql.SQLException;

interface DomainObject
{
	void markNew();
	void markClean();
	void markDirty();
	void markRemoved();

	boolean insert() throws SQLException;
	boolean update() throws SQLException;
	boolean delete() throws SQLException;
}
