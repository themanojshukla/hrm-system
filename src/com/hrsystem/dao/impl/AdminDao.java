package com.hrsystem.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hrsystem.dao.IAdminDao;
import com.hrsystem.init.DBConnection;

public class AdminDao implements IAdminDao {
	final static Logger LOGGER = LoggerFactory.getLogger(AdminDao.class);

	@Override
	public boolean authenticateAdmin(String email, String password) {
		String query = "SELECT count(*) as count FROM admin where email = ? and password = ?";
		boolean returnValue = false;
		Connection connection = null;
		PreparedStatement stmt = null;
		ResultSet result = null;
		try {
			connection = DBConnection.getConnection();
			stmt = connection.prepareStatement(query);
			stmt.setString(1, email);
			stmt.setString(2, password);
			result = stmt.executeQuery();

			if (result.next()) {
				if (result.getInt("count") > 0)
					returnValue = true;
			}
			stmt.close();
			result.close();

		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error("Unable to authenticate Admin : " + email);
			LOGGER.error("Exception for " + e.getMessage());
		}

		return returnValue;
	}

	@Override
	public String getAdminName(String email) {
		String query = "SELECT name FROM admin where email = ?";
		String returnValue = null;
		Connection connection = null;
		PreparedStatement stmt = null;
		ResultSet result = null;
		try {
			connection = DBConnection.getConnection();
			stmt = connection.prepareStatement(query);
			stmt.setString(1, email);
			result = stmt.executeQuery();
			if (result.next()) {
				returnValue = result.getString("name");
			}
			stmt.close();
			result.close();

		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error("Unable to get Admin name : " + email);
			LOGGER.error("Exception for " + e.getMessage());
		}
		return returnValue;
	}

	@Override
	public String getAdminName(int id) {
		String query = "SELECT name FROM admin where id = ?";
		String returnValue = null;
		Connection connection = null;
		PreparedStatement stmt = null;
		ResultSet result = null;
		try {
			connection = DBConnection.getConnection();
			stmt = connection.prepareStatement(query);
			stmt.setInt(1, id);
			result = stmt.executeQuery();
			if (result.next()) {
				returnValue = result.getString("name");
			}
			stmt.close();
			result.close();

		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error("Unable to get Admin name : " + id);
			LOGGER.error("Exception for " + e.getMessage());
		}

		return returnValue;
	}

	@Override
	public int getAdminID(String email) {
		String query = "SELECT id FROM admin where email = ?";
		int returnValue = 0;
		Connection connection = null;
		PreparedStatement stmt = null;
		ResultSet result = null;
		try {
			connection = DBConnection.getConnection();
			stmt = connection.prepareStatement(query);
			stmt.setString(1, email);
			result = stmt.executeQuery();
			if (result.next()) {
				returnValue = result.getInt("id");
			}
			stmt.close();
			result.close();

		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error("Unable to get Admin ID : " + email);
			LOGGER.error("Exception for " + e.getMessage());
		}
		return returnValue;
	}

}