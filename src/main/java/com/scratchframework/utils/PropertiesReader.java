package com.scratchframework.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesReader {

	private static Properties prop;

	public static void initializeEnvironmentProperties() {
		try {

			InputStream inputStream = new FileInputStream("config.properties");

			prop = new Properties();

			prop.load(inputStream);

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

	}

	public static String getProperty(String propertyName) {
		
		return prop.getProperty(propertyName);
		
	}

}
