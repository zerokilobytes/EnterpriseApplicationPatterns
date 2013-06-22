package org.eap.patterns.orbp.unitofwork;

public class Supplier extends org.eap.dao.domainobject.Supplier implements DomainObject
{
	public int getSupplierID() {
		return SupplierID;
	}

	public String getSupplierName() {
		return SupplierName;
	}
	public void setSupplierName(String supplierName) {
		SupplierName = supplierName;
	}

	@Override
	public void markNew() {
		UnitOfWorkScope.getCurrent().registerNew(this);
	}

	@Override
	public void markClean() {
		UnitOfWorkScope.getCurrent().registerClean(this);
	}

	@Override
	public void markDirty() {
		UnitOfWorkScope.getCurrent().registerDirty(this);
	}

	@Override
	public void markRemoved() {
		UnitOfWorkScope.getCurrent().registerRemoved(this);
		
	}

	@Override
	public boolean insert() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete() {
		// TODO Auto-generated method stub
		return false;
	}
}