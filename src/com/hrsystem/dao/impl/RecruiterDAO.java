package com.hrsystem.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.hrsystem.dao.IRecruiterDAO;
import com.hrsystem.model.Recruiter;
import com.hrsytem.init.DBConnection;

public class RecruiterDAO implements IRecruiterDAO{
	final static Logger LOGGER = Logger.getLogger(HRDAO.class);

	private Recruiter recruiter;
	public RecruiterDAO() {
		
	}
	
	public RecruiterDAO(Recruiter recruiter) {
		this.recruiter = recruiter;
	}
	
	@Override
	public void addNewRecruiter() {
		String query = "INSERT INTO recruiter(email,name,password,skills) VALUES(?,?,?,?)";
		Connection connection = null;
		PreparedStatement stmt = null;
		try {
			connection = DBConnection.getConnection();
			stmt = connection.prepareStatement(query);
			stmt.setString(1, recruiter.getEmail());
			stmt.setString(2, recruiter.getName());
			stmt.setString(3, recruiter.getPasswd());
			stmt.setString(4, recruiter.getSkills());
			stmt.executeUpdate();
			stmt.close();
			
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.fatal("Unable to Add Recruiter : " + recruiter.getEmail());
			LOGGER.error("Exception for " + e.getMessage());
		}
	}
	@Override
	public void removeExistingByEmail(String email) {
		
	}
	@Override
	public void removeExistingById(int id) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public boolean isExisting() {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean isExistingByEmail(String email) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean isExistingById(int Id) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public String getNameByEmail(String email) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public String getNameById(int id) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public String getNameById() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public int getIdByEmail(String email) {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public List<Recruiter> getRecruiterList() {
		String query = "SELECT * FROM recruiter";
		List<Recruiter> returnValue = new ArrayList<Recruiter>();
		Connection connection = null;
		PreparedStatement stmt = null;
		ResultSet result = null;
		try {
			connection = DBConnection.getConnection();
			stmt = connection.prepareStatement(query);
			result = stmt.executeQuery();
			while (result.next()) {
				Recruiter recruiter = new Recruiter();
				recruiter.setId(result.getInt("id"));
				recruiter.setName(result.getString("name"));
				recruiter.setEmail(result.getString("email"));
				recruiter.setSkills(result.getString("skills"));
				returnValue.add(recruiter);
			}
			stmt.close();
			result.close();

		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.fatal("Unable to get the List of Recruiters ");
			LOGGER.error("Exception for " + e.getMessage());
		}
		return returnValue;
	}
	
	@Override
	public List<Recruiter> getRecruiterListBySkills(String skills) {
		String query = "SELECT * FROM recruiter";
		List<Recruiter> returnValue = new ArrayList<Recruiter>();
		Connection connection = null;
		PreparedStatement stmt = null;
		ResultSet result = null;
		try {
			connection = DBConnection.getConnection();
			stmt = connection.prepareStatement(query);
			result = stmt.executeQuery();
			while (result.next()) {
				Recruiter recruiter = new Recruiter();
				recruiter.setId(result.getInt("id"));
				recruiter.setName(result.getString("name"));
				recruiter.setEmail(result.getString("email"));
				recruiter.setSkills(result.getString("skills"));
				returnValue.add(recruiter);
			}
			stmt.close();
			result.close();

		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.fatal("Unable to get the List of HRs ");
			LOGGER.error("Exception for " + e.getMessage());
		}
		return returnValue;
	}
	@Override
	public Recruiter getRecruiterByEmail(String email) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Recruiter getRecruiterById(int id) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	
}
