package com.hrsystem.init;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DBConnection {
	private static Connection con = null;
	final static Logger LOGGER = LoggerFactory.getLogger(DBConnection.class);

	private DBConnection() {

	}

	public static Connection getConnection() throws IOException, SQLException,
			ClassNotFoundException {
		LOGGER.info("Connection requested.");
		if (con == null) {
			Properties prop = null;
			LOGGER.info("Creating new connection.");
			InputStream input = DBConnection.class.getClassLoader()
					.getResourceAsStream("config.properties");
			prop = new Properties();
			// prop.load(DBConnection.class.getClassLoader().getResourceAsStream("config.properties"));
			prop.load(input);
			String driver = prop.getProperty("driver");
			String url = prop.getProperty("url");
			String database = prop.getProperty("database");
			String user = prop.getProperty("user");
			String passwd = prop.getProperty("passwd");
			input.close();
			Class.forName(driver);
			LOGGER.info("Connection for Url: " + url + " Database : "
					+ database + " User : " + user + " Password : "
					+ (!passwd.isEmpty()));
			con = DriverManager.getConnection(url + database, user, passwd);
		}
		return con;
	}
}