package org.eap.patterns.orbp.lazyload.lazyinitialization;

import static org.junit.Assert.*;

import org.eap.dao.datasource.DB;
import org.eap.dao.datasource.SQLite;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ProductTest {

	@Before
	public void setUp() 
	{
		DB.setDataSource(new SQLite());
	}

	@After
	public void tearDown() 
	{
		DB.closeConnection();
	}

	@Test
	public void test()
	{
		fail("Not yet implemented");
	}
}