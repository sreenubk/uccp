
package com.lotus.uccp.util;

import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {

	private static final SessionFactory sessionFactory = buildSessionFactory();

	private static SessionFactory buildSessionFactory() {
		try {
			// For XML mapping
			// return new Configuration().configure().buildSessionFactory();

			// For Annotation
			return new Configuration().configure().buildSessionFactory();

		} catch (Throwable ex) {

			throw new ExceptionInInitializerError(ex);
		}
	}

	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public static void shutdown() {
		// Close caches and connection pools
		getSessionFactory().close();
	}

	public static Connection getConnection() {

		Connection con = null;

		try {
			// org.slf4j.LoggerFactory
			Properties prop = loadPropertiesFile();
			// Properties prop =loadPropertiesFile();

			/*
			 * JDBC.driver=oracle.jdbc.driver.OracleDriver
			 * JDBC.url=jdbc:oracle:thin:@9.42.158.21:1521/consmgmt
			 * JDBC.username=consmgmt_UI JDBC.password=cu8a$Mdev
			 */

			String driver = prop.getProperty("driverName");
			String url = prop.getProperty("spring.datasource.url");
			String username = prop.getProperty("spring.datasource.username");
			String password = prop.getProperty("spring.datasource.password");

			Class.forName(driver);
			con = DriverManager.getConnection(url, username, password);

			System.out.println("::: Connection Formed");

		} catch (Exception ex) {
			System.out.println("::: Exception");
		}

		return con;
	}

	public static Properties loadPropertiesFile() throws Exception {

		Properties prop = new Properties();
		ClassLoader classLoader = new HibernateUtil().getClass().getClassLoader();
		InputStream in = classLoader.getResourceAsStream("application.properties");
		// InputStream in = new FileInputStream("resources\\application.properties");
		prop.load(in);
		in.close();
		return prop;
	}

	public static String getUserName(HttpServletRequest request) {
		HttpSession session = request.getSession(false);

		if (session == null) {
			System.out.println("Session is null");
		} else {
			System.out.println("Session is Found ::-Username" + session.getAttribute("Username"));
			return (String) session.getAttribute("Username");
		}
		return null;
	}

	public static void main(String[] args) {

		System.out.println("UCCP ....");
	}

}