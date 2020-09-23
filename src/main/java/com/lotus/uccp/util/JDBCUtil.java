package com.lotus.uccp.util;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class JDBCUtil {
	
public static Connection getConnection_ConsentMgmt() {
		
		Connection con = null ;
		
		try
		{
			//org.slf4j.LoggerFactory
			Properties prop = loadPropertiesFile();
		//Properties prop =loadPropertiesFile();
			
			/*JDBC.driver=oracle.jdbc.driver.OracleDriver
					JDBC.url=jdbc:oracle:thin:@9.42.158.21:1521/consmgmt
					JDBC.username=consmgmt_UI
					JDBC.password=cu8a$Mdev*/
		  
		  String driver = prop.getProperty("driverName");
		  String url =  prop.getProperty("spring.datasource.url");
		  String username = prop.getProperty("spring.datasource.username");
		  String password = prop.getProperty("spring.datasource.password");
	      
		  		  
		  Class.forName(driver);
		  con = DriverManager.getConnection(url,username, password);
		  con.setSchema(Constant.SCHEMA);
		  System.out.println("::: Connection Formed");
		  
		}
		catch(Exception ex)
		{
			System.out.println("::: Exception");
		}

		return con ;
	}

public static Connection getConnection() {
	
	Connection con = null ;
	
	try
	{
		//org.slf4j.LoggerFactory
		Properties prop = loadPropertiesFile();
	//Properties prop =loadPropertiesFile();
		
		/*JDBC.driver=oracle.jdbc.driver.OracleDriver
				JDBC.url=jdbc:oracle:thin:@9.42.158.21:1521/consmgmt
				JDBC.username=consmgmt_UI
				JDBC.password=cu8a$Mdev*/
	  
	  String driver = prop.getProperty("driverName");
	  String url =  prop.getProperty("spring.datasource.url");
	  String username = prop.getProperty("spring.datasource.username");
	  String password = prop.getProperty("spring.datasource.password");
      
	  		  
	  Class.forName(driver);
	  con = DriverManager.getConnection(url,username, password);
	  con.setSchema(Constant.SCHEMA);
	  System.out.println("::: Connection Formed");
	  
	}
	catch(Exception ex)
	{
		ex.printStackTrace();
		System.out.println("::: Exception");
	}

	return con ;
}

	
	public static Properties loadPropertiesFile() throws Exception
	{
		
		Properties prop = new Properties();
		ClassLoader classLoader = new JDBCUtil().getClass().getClassLoader();
		InputStream in =  classLoader.getResourceAsStream("application.properties");
        //InputStream in = new FileInputStream("resources\\application.properties");
        prop.load(in);
        in.close();
		return prop;
	}
	
	public static Properties loadPropertiesFile(String fileName) throws Exception {

		Properties prop = new Properties();
		ClassLoader classLoader = new JDBCUtil().getClass().getClassLoader();
		InputStream in = classLoader.getResourceAsStream(fileName);
		prop.load(in);
		in.close();
		return prop;
	}

}
