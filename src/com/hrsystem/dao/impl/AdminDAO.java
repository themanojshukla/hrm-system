package com.hrsystem.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.apache.log4j.Logger;

import com.hrsystem.dao.IAdminDAO;
import com.hrsystem.model.Admin;
import com.hrsytem.init.DBConnection;

public class AdminDAO implements IAdminDAO {
	final static Logger LOGGER = Logger.getLogger(AdminDAO.class);

	private Admin admin;

	public AdminDAO(Admin admin) {
		this.admin = admin;
	}

	@Override
	public boolean authenticateAdmin() {
		// System.out.println("Param : " + admin.getEmail() + " " +
		// admin.getPasswd());
		String query = "SELECT count(*) as count FROM admin where email = ? and password = ?";
		boolean returnValue = false;
		Connection connection = null;
		PreparedStatement stmt = null;
		ResultSet result = null;
		try {
			connection = DBConnection.getConnection();
			stmt = connection.prepareStatement(query);
			stmt.setString(1, admin.getEmail());
			stmt.setString(2, admin.getPasswd());
			result = stmt.executeQuery();

			if (result.next()) {
				System.out.println(result.getInt("count"));
				if (result.getInt("count") > 0)
					returnValue = true;
			}
			stmt.close();
			result.close();

		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.fatal("Unable to authenticate Admin : " + admin.getEmail());
			LOGGER.error("Exception for " + e.getMessage());
		}

		System.out.println(returnValue);
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
			stmt.setString(1, admin.getEmail());
			result = stmt.executeQuery();
			if (result.next()) {
				returnValue = result.getString("name");
			}
			stmt.close();
			result.close();

		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.fatal("Unable to authenticate Admin : " + admin.getEmail());
			LOGGER.error("Exception for " + e.getMessage());
		}

		System.out.println(returnValue);

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
			stmt.setInt(1, admin.getId());
			result = stmt.executeQuery();
			if (result.next()) {
				returnValue = result.getString("name");
			}
			stmt.close();
			result.close();

		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.fatal("Unable to authenticate Admin : " + admin.getEmail());
			LOGGER.error("Exception for " + e.getMessage());
		}

		System.out.println(returnValue);

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
			stmt.setString(1, admin.getEmail());
			result = stmt.executeQuery();
			if (result.next()) {
				returnValue = result.getInt("id");
			}
			stmt.close();
			result.close();

		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.fatal("Unable to authenticate Admin : " + admin.getEmail());
			LOGGER.error("Exception for " + e.getMessage());
		}
		return returnValue;
	}
	
}
