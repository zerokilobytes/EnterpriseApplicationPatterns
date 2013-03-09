package org.eap.patterns.domainlogic.tablemodule;

import org.eap.dao.DataRow;
import org.eap.dao.DataSet;

public class TableModule 
{
	DataSet dataset;
	public TableModule(DataSet ds)
	{
		this.dataset = ds;
	}
	
	
	public DataRow GetRow(int index)
    {
        //return _ds.Tables[0].Rows[index];
		return null;
    }

    public Object GetRowByID(int ID)
    {
        //return _ds.Tables[0].Select(...);
    	return null;
    }

}
