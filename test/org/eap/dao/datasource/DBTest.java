package org.eap.dao.datasource;

import static org.junit.Assert.*;

import java.io.IOException;
import java.sql.Connection;
import org.junit.Test;

public class DBTest {

	@Test
	public void test() throws IOException {
		
		DB.setDataSource(new SQLite());
		Connection c = DB.getConnection();
		
		assertNotNull("A atabase connection must be establishe", c);
	}

}