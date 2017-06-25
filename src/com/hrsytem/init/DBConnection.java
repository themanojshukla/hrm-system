package com.hrsytem.init;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import org.apache.log4j.Logger;

public class DBConnection {
	private static Connection con = null;
	private static Properties prop = null;
	final static Logger LOGGER = Logger.getLogger(DBConnection.class);

	private DBConnection() {

	}

	public static Connection getConnection() throws IOException, SQLException,
			ClassNotFoundException {

		if (con == null) {
			LOGGER.info("Creating new Connection..");
			InputStream input = DBConnection.class.getClassLoader()
					.getResourceAsStream("config.properties");
			prop = new Properties();
			prop.load(input);
			String driver = prop.getProperty("driver");
			String url = prop.getProperty("url");
			String database = prop.getProperty("database");
			String user = prop.getProperty("user");
			String passwd = prop.getProperty("passwd");
			input.close();
			Class.forName(driver);
			LOGGER.info("Connection for Url: " + url + " Database : "
					+ database + " User : " + user + " Password : " + passwd);
			con = DriverManager.getConnection(url + database, user, passwd);
			System.out.println("Connection Created Successfully.");
		}
		return con;
	}

}
