package org.HotelRecommand.config;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class PathHelper {
	// it contain static method
	static Properties p = new Properties();
	
	static {
		File f = new File("."); // file loading
		String filePath = f.getAbsolutePath(); // testing
		// remove .from output path
		String mainPath = filePath.substring(0, filePath.length() - 1)+"src\\org\\HotelRecommand\\config\\db.properties";
		// read the properties
		FileInputStream fin;
		try {
			fin = new FileInputStream(mainPath);
			p.load(fin);
		} 
		catch (IOException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {

	}

}
