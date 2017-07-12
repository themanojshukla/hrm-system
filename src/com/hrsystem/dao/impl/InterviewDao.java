package com.hrsystem.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hrsystem.dao.IInterviewDao;
import com.hrsystem.init.DBConnection;

public class InterviewDao implements IInterviewDao {
	final static Logger LOGGER = LoggerFactory.getLogger(InterviewDao.class);
	
	@Override
	public void assignScreeningRecruiter(String status, String candidateId, String recruiterId, String hrId) {
		String query = "INSERT INTO interview(status,candidate_id,hr_id,recruiter_id) VALUES(?,?,?,?)";
		Connection connection = null;
		PreparedStatement stmt = null;
		try {
			connection = DBConnection.getConnection();
			stmt = connection.prepareStatement(query);
			stmt.setString(1, status);
			stmt.setString(2, candidateId);
			stmt.setString(3, hrId);
			stmt.setString(4, recruiterId);
			stmt.executeUpdate();
			stmt.close();

		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error("Unable to ADD Candidate for : " + status + " ID : " +candidateId );
			LOGGER.error("Exception for " + e.getMessage());
		}
		
	}
	
	@Override
	public void assignInterviewRecruiter(String time, String date, String place, String status, String candidateId, String recruiterId, String hrId) {
		String query = "UPDATE interview SET status = ?, recruiter_id = ?, time = ?, date = ?, place = ?  where hr_id =? AND candidate_id= ?";
		Connection connection = null;
		PreparedStatement stmt = null;
		try {
			connection = DBConnection.getConnection();
			stmt = connection.prepareStatement(query);
			stmt.setString(1, status);
			stmt.setString(2, recruiterId);
			stmt.setString(3, time);
			stmt.setString(4, date);
			stmt.setString(5, place);
			stmt.setString(6, hrId);
			stmt.setString(7, candidateId);
			stmt.executeUpdate();
			stmt.close();

		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error("Unable to Update Candidate for : " + status + " ID : " +candidateId );
			LOGGER.error("Exception for " + e.getMessage());
		}
		
	}

	@Override
	public void assignHR(String status, String candidateId, String hrId) {
		String query = "UPDATE interview SET status = ? where candidate_id =? AND hr_id = ?";
		Connection connection = null;
		PreparedStatement stmt = null;
		try {
			connection = DBConnection.getConnection();
			stmt = connection.prepareStatement(query);
			stmt.setString(1, status);
			stmt.setString(2, candidateId);
			stmt.setString(3, hrId);
			stmt.executeUpdate();
			stmt.close();

		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error("Unable to Update Candidate " +candidateId  +" for : " + status  );
			LOGGER.error("Exception for " + e.getMessage());
		}
		
	}
	
	@Override
	public boolean isAllowed(int recId, int candidateId) {
		System.out.println(recId+" "+candidateId);
		String query = "SELECT count(*) as count FROM interview WHERE candidate_id = ? AND recruiter_id = ?";
		Connection connection = null;
		PreparedStatement stmt = null;
		ResultSet result = null;
		boolean returnValue = false;
		try {
			connection = DBConnection.getConnection();
			stmt = connection.prepareStatement(query);
			stmt.setInt(1, candidateId);
			stmt.setInt(2, recId);
			result = stmt.executeQuery();
			if(result.next()){
				if(result.getInt("count")>0)
					returnValue = true;
			}
			stmt.close();
			result.close();
			
			
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error("Unable to authenticate Candidate "+ candidateId +" with recruiter : " + recId  );
			LOGGER.error("Exception for " + e.getMessage());
		}
		return returnValue;
		
	}

	@Override
	public void updateStatusByRecruiter(String status,int recruiterId,int  candidateId ) {
		String query = "UPDATE interview SET status = ? WHERE recruiter_id = ? AND candidate_id= ?";
		Connection connection = null;
		PreparedStatement stmt = null;
		try {
			connection = DBConnection.getConnection();
			stmt = connection.prepareStatement(query);
			stmt.setString(1, status);
			stmt.setInt(2, recruiterId);
			stmt.setInt(3, candidateId);
			stmt.executeUpdate();
			stmt.close();

		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error("Unable to Update Candidate for : " + status + " ID : " + candidateId );
			LOGGER.error("Exception for " + e.getMessage());
		}
		
	}

	@Override
	public void selectThisCandidate(String id, int hrId) {
		String query = "UPDATE interview SET status = ? where candidate_id =? AND hr_id = ?";
		Connection connection = null;
		PreparedStatement stmt = null;
		try {
			connection = DBConnection.getConnection();
			stmt = connection.prepareStatement(query);
			stmt.setString(1, "SELECTED");
			stmt.setString(2, id);
			stmt.setInt(3, hrId);
			System.out.println("SELECECT : "+id+" "+hrId);
			stmt.executeUpdate();
			stmt.close();

		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error("Unable to Update as Selected Candidate " +id );
			LOGGER.error("Exception for " + e.getMessage());
		}
		
	}

}
