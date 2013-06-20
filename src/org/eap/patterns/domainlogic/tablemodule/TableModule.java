package org.eap.patterns.domainlogic.tablemodule;

import org.eap.dao.DataRowCollection;
import org.eap.dao.DataSet;
import org.eap.dao.DataTable;

public abstract class TableModule 
{
	protected String tableName;
	DataSet dataset;
	public TableModule(DataSet ds)
	{
		this.dataset = ds;
	}
	
	protected DataTable<?> getTable()
	{
		return dataset.getTable(tableName);
	}
	public DataRowCollection<?> GetRows()
    {
		DataTable<?> table = getTable();
		return table.getRows();
    }

    public abstract Object GetRowByID(int ID);

}
