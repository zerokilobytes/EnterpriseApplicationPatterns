package org.eap;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

/**
 * @author zerobytes
 * 
 * 
 */
public class TestPatterns {
	@SuppressWarnings("resource")
	public static void main(String[] args) throws IOException {
		//File file = new File("sql/eap.sql");
		
		 String text = new Scanner( new File("sql/eap.sql") ).useDelimiter("\\A").next();
		 text.toString();
	}
}