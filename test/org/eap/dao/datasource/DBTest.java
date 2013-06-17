package org.eap.dao.datasource;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.sql.Connection;

import org.junit.Test;

import sun.security.util.Resources;

public class DBTest {

	@Test
	public void test() throws IOException {
		
		DB.setDataSource(new SQLite());
		Connection c = DB.getConnection();
	}

}
