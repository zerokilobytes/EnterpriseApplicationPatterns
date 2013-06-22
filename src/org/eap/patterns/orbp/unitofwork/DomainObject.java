package org.eap.patterns.orbp.unitofwork;

interface DomainObject
{
	void markNew();
	void markClean();
	void markDirty();
	void markRemoved();

	boolean insert();
	boolean update();
	boolean delete();
}
