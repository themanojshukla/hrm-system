package com.hrsystem.dao.impl;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hrsystem.dao.IHrDao;
import com.hrsystem.init.DBConnection;
import com.hrsystem.model.HR;

public class HrDao implements IHrDao {
	final static Logger LOGGER = LoggerFactory.getLogger(HrDao.class);

	@Override
	public void addNewHR(HR hr) {
		String query = "INSERT INTO hr(email,name,password) VALUES(?,?,?)";
		Connection connection = null;
		PreparedStatement stmt = null;
		try {
			connection = DBConnection.getConnection();
			stmt = connection.prepareStatement(query);
			stmt.setString(1, hr.getEmail());
			stmt.setString(2, hr.getName());
			stmt.setString(3, hr.getPasswd());
			stmt.executeUpdate();
			stmt.close();

		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error("Unable to ADD HR : " + hr.getEmail());
			LOGGER.error("Exception for " + e.getMessage());
		}
	}

	@Override
	public void removeExistingByEmail(String email) {
		String query = "DELETE FROM hr where email = ?";
		Connection connection = null;
		PreparedStatement stmt = null;
		try {
			connection = DBConnection.getConnection();
			stmt = connection.prepareStatement(query);
			stmt.setString(1, email);
			stmt.executeUpdate();
			stmt.close();

		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error("Unable to Delete HR : " + email);
			LOGGER.error("Exception for " + e.getMessage());
		}

	}

	@Override
	public void removeExistingById(int id) {
		String query = "DELETE FROM hr where id = ?";
		Connection connection = null;
		PreparedStatement stmt = null;
		try {
			connection = DBConnection.getConnection();
			stmt = connection.prepareStatement(query);
			stmt.setInt(1, id);
			stmt.executeUpdate();
			stmt.close();

		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error("Unable to Delete HR : " + id);
			LOGGER.error("Exception for " + e.getMessage());
		}

	}

	@Override
	public boolean isExistingByEmail(String email) {
		String query = "SELECT count(*) as count FROM hr where email = ?";
		boolean returnValue = false;
		Connection connection = null;
		PreparedStatement stmt = null;
		ResultSet result = null;
		try {
			connection = DBConnection.getConnection();
			stmt = connection.prepareStatement(query);
			stmt.setString(1, email);
			result = stmt.executeQuery();

			if (result.next()) {
				if (result.getInt("count") > 0)
					returnValue = true;
			}
			stmt.close();
			result.close();

		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error("Unable to Check HR : " + email);
			LOGGER.error("Exception for " + e.getMessage());
		}
		return returnValue;

	}

	@Override
	public boolean isExistingById(int id) {
		String query = "SELECT count(*) as count FROM hr where id = ?";
		boolean returnValue = false;
		Connection connection = null;
		PreparedStatement stmt = null;
		ResultSet result = null;
		try {
			connection = DBConnection.getConnection();
			stmt = connection.prepareStatement(query);
			stmt.setInt(1, id);
			result = stmt.executeQuery();

			if (result.next()) {
				if (result.getInt("count") > 0)
					returnValue = true;
			}
			stmt.close();
			result.close();

		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error("Unable to Check HR : " + id);
			LOGGER.error("Exception for " + e.getMessage());
		}
		return returnValue;

	}

	@Override
	public String getNameByEmail(String email) {
		String query = "SELECT name FROM hr where email = ?";
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
			LOGGER.error("Unable to Return HR Name for email : " + email);
			LOGGER.error("Exception for " + e.getMessage());
		}
		return returnValue;
	}

	@Override
	public String getNameById(int id) {
		String query = "SELECT name FROM hr where id = ?";
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
			LOGGER.error("Unable to Return HR Name for email : " + id);
			LOGGER.error("Exception for " + e.getMessage());
		}
		return returnValue;
	}

	@Override
	public List<HR> getHRList() {
		String query = "SELECT * FROM hr";
		List<HR> returnValue = new ArrayList<HR>();
		Connection connection = null;
		PreparedStatement stmt = null;
		ResultSet result = null;
		try {
			connection = DBConnection.getConnection();
			stmt = connection.prepareStatement(query);
			result = stmt.executeQuery();
			while (result.next()) {
				HR hr = new HR();
				hr.setId(result.getInt("id"));
				hr.setName(result.getString("name"));
				hr.setEmail(result.getString("email"));
				returnValue.add(hr);
			}
			stmt.close();
			result.close();

		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error("Unable to get the List of HRs ");
			LOGGER.error("Exception for " + e.getMessage());
		}
		return returnValue;

	}

	@Override
	public HR getHRByEmail(String email) {
		String query = "SELECT * FROM hr where email = ?";
		HR returnValue = null;
		Connection connection = null;
		PreparedStatement stmt = null;
		ResultSet result = null;
		try {
			connection = DBConnection.getConnection();
			stmt = connection.prepareStatement(query);
			stmt.setString(1, email);
			result = stmt.executeQuery();
			if (result.next()) {
				returnValue = new HR();
				returnValue.setId(result.getInt("id"));
				returnValue.setEmail(result.getString("email"));
				returnValue.setName(result.getString("name"));
			}
			stmt.close();
			result.close();

		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error("Unable to get HR : " + email);
			LOGGER.error("Exception for " + e.getMessage());
		}
		return returnValue;
	}

	@Override
	public HR getHRById(int id) {
		String query = "SELECT * FROM hr where id = ?";
		HR returnValue = null;
		Connection connection = null;
		PreparedStatement stmt = null;
		ResultSet result = null;
		try {
			connection = DBConnection.getConnection();
			stmt = connection.prepareStatement(query);
			stmt.setInt(1, id);
			result = stmt.executeQuery();
			if (result.next()) {
				returnValue = new HR();
				returnValue.setId(result.getInt("id"));
				returnValue.setEmail(result.getString("email"));
				returnValue.setName(result.getString("name"));
			}
			stmt.close();
			result.close();

		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error("Unable to get HR for ID : " + id);
			LOGGER.error("Exception for " + e.getMessage());
		}
		return returnValue;
	}

	@Override
	public int getIdByEmail(String email) {
		String query = "SELECT id FROM hr where email = ?";
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
			LOGGER.error("Unable to get ID for HR : " + email);
			LOGGER.error("Exception for " + e.getMessage());
		}
		return returnValue;
	}

	@Override
	public void updateHr(HR hr) {
		String query = "UPDATE hr SET name = ? , email = ? WHERE id = ?";
		Connection connection = null;
		PreparedStatement stmt = null;
		try {
			connection = DBConnection.getConnection();
			stmt = connection.prepareStatement(query);
			stmt.setString(1, hr.getName());
			stmt.setString(2, hr.getEmail());
			stmt.setInt(3, hr.getId());
			stmt.executeUpdate();
			stmt.close();

		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error("Unable to Update HR : " + hr.getEmail());
			LOGGER.error("Exception for " + e.getMessage());
		}
	}

	@Override
	public boolean isOtherExistingByEmail(String hrEmail, int id) {
		String query = "SELECT count(*) as count FROM hr where email = ? AND id != ?";
		boolean returnValue = false;
		Connection connection = null;
		PreparedStatement stmt = null;
		ResultSet result = null;
		try {
			connection = DBConnection.getConnection();
			stmt = connection.prepareStatement(query);
			stmt.setString(1, hrEmail);
			stmt.setInt(2, id);
			result = stmt.executeQuery();

			if (result.next()) {
				if (result.getInt("count") > 0)
					returnValue = true;
			}
			stmt.close();
			result.close();

		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error("Unable to check another hr : " + hrEmail);
			LOGGER.error("Exception for " + e.getMessage());
		}
		return returnValue;
	}

	@Override
	public boolean authenticateHR(String email, String password) {
		String query = "SELECT count(*) as count FROM hr where email = ? and password = ?";
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
			LOGGER.error("Unable to authenticate HR : " + email);
			LOGGER.error("Exception for " + e.getMessage());
		}
		return returnValue;

	}

	

}