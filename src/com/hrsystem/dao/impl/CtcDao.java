package com.hrsystem.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hrsystem.dao.ICtcDao;
import com.hrsystem.init.DBConnection;

public class CtcDao implements ICtcDao {
	final static Logger LOGGER = LoggerFactory.getLogger(CtcDao.class);
	@Override
	public double getTotalSalary(int candidateID) {
		String query = "SELECT basic,hra,da FROM salary where candidate_id = ?";
		double returnValue = 0;
		Connection connection = null;
		PreparedStatement stmt = null;
		ResultSet result = null;
		try {
			connection = DBConnection.getConnection();
			stmt = connection.prepareStatement(query);
			stmt.setInt(1, candidateID);
			result = stmt.executeQuery();
			if (result.next()) {
				returnValue = result.getDouble("basic")
						+ result.getDouble("hra") + result.getDouble("da");
			}
			stmt.close();
			result.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println(returnValue);

		return returnValue;
	}

	@Override
	public void addCtc(String id, String basic, String hra, String da) {
		String query = "INSERT INTO ctc(basic,hra,da,candidate_id) VALUES(?,?,?,?)";
		Connection connection = null;
		PreparedStatement stmt = null;
		try {
			connection = DBConnection.getConnection();
			stmt = connection.prepareStatement(query);
			stmt.setString(1, id);
			stmt.setString(2, hra);
			stmt.setString(3, da);
			stmt.setString(4, id);
			stmt.executeUpdate();
			stmt.close();

		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error("Unable to CTC for : " + id);
			LOGGER.error("Exception for " + e.getMessage());
		}
	}

	

	

}