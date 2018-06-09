package com.automation.resources;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;

public class PropertyReader {

	private static final String ENV_PATH = System.getProperty("user.dir")+"\\env.properties";
	private static final String REQUESTBODY_PATH = System.getProperty("user.dir")+"\\RequestBody\\";
	
	public static String getProperty(String property) throws IOException{
		Properties prop =new Properties();
		FileInputStream fis = new FileInputStream(ENV_PATH);
		prop.load(fis);
		return prop.getProperty(property);
	}
	
	public static String generateStringFromResource(String fileName) throws IOException{
		return new String(Files.readAllBytes(Paths.get(REQUESTBODY_PATH+fileName)));
	}
	
	
}
