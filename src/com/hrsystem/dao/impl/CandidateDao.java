package com.hrsystem.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hrsystem.dao.ICandidateDao;
import com.hrsystem.init.DBConnection;
import com.hrsystem.model.Candidate;
import com.hrsystem.model.Ctc;
import com.hrsystem.model.Interview;

public class CandidateDao implements ICandidateDao {

	static final Logger LOGGER = LoggerFactory.getLogger(CandidateDao.class);

	@Override
	public boolean isExistingByEmail(String email) {
		String query = "SELECT count(*) as count FROM candidate where email = ?";
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
			LOGGER.error("Unable to Check Candidate : " + email);
			LOGGER.error("Exception for " + e.getMessage());
		}
		return returnValue;

	}

	@Override
	public void addNewCandidate(Candidate candidate) {
		String query = "INSERT INTO candidate(name,email,dateofbirth,age,address,mobile,resume) VALUES(?,?,?,?,?,?,?)";
		Connection connection = null;
		PreparedStatement stmt = null;
		try {
			connection = DBConnection.getConnection();
			stmt = connection.prepareStatement(query);
			stmt.setString(1, candidate.getName());
			stmt.setString(2, candidate.getEmail());
			stmt.setString(3, candidate.getDateOfBirth());
			stmt.setInt(4, candidate.getAge());
			stmt.setString(5, candidate.getAddress());
			stmt.setLong(6, candidate.getMobile());
			stmt.setBytes(7, candidate.getResume());

			stmt.executeUpdate();
			stmt.close();

		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error("Unable to ADD Candidate : " + candidate.getEmail());
			LOGGER.error("Exception for " + e.getMessage());
		}
	}

	@Override
	public int getIdByEmail(String email) {
		String query = "SELECT id FROM candidate WHERE email = ?";
		Connection connection = null;
		PreparedStatement stmt = null;
		ResultSet result = null;
		int returnValue = -1;
		try {
			connection = DBConnection.getConnection();
			stmt = connection.prepareStatement(query);
			stmt.setString(1, email);
			result = stmt.executeQuery();
			stmt.close();

			if (result.next()) {
				returnValue = result.getInt("id");
			}
			result.close();

		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error("Unable to get ID of Candidate : " + email);
			LOGGER.error("Exception for " + e.getMessage());
		}
		return returnValue;
	}

	@Override
	public List<Candidate> getCandidateList() {
		String query = "SELECT * FROM candidate";
		List<Candidate> returnValue = null;
		Connection connection = null;
		PreparedStatement stmt = null;
		ResultSet result = null;
		try {
			connection = DBConnection.getConnection();
			stmt = connection.prepareStatement(query);
			result = stmt.executeQuery();
			if (result.next()) {
				returnValue = new ArrayList<Candidate>();
				result.beforeFirst();
			}
			while (result.next()) {
				Candidate candidate = new Candidate();
				candidate.setId(result.getInt("id"));
				candidate.setName(result.getString("name"));
				candidate.setEmail(result.getString("email"));
				candidate.setAge(result.getInt("age"));
				candidate.setMobile(result.getLong("mobile"));
				returnValue.add(candidate);
			}
			stmt.close();
			result.close();

		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error("Unable to get the List of Candidates ");
			LOGGER.error("Exception for " + e.getMessage());
		}
		return returnValue;
	}

	@Override
	public List<Candidate> getNewCandidateList() {
		String query = "SELECT C.* FROM candidate C WHERE C.id NOT IN (SELECT candidate_id FROM interview )";
		List<Candidate> returnValue = null;
		Connection connection = null;
		PreparedStatement stmt = null;
		ResultSet result = null;
		try {
			connection = DBConnection.getConnection();
			stmt = connection.prepareStatement(query);
			result = stmt.executeQuery();
			if (result.next()) {
				returnValue = new ArrayList<Candidate>();
				result.beforeFirst();
			}
			while (result.next()) {
				Candidate candidate = new Candidate();
				candidate.setId(result.getInt("id"));
				candidate.setName(result.getString("name"));
				candidate.setEmail(result.getString("email"));
				candidate.setResume(result.getBytes("resume"));
				returnValue.add(candidate);
			}
			stmt.close();
			result.close();

		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error("Unable to get the List of New Candidates ");
			LOGGER.error("Exception for " + e.getMessage());
		}
		return returnValue;
	}

	@Override
	public List<Candidate> getScreeningCandidateList(int hr_id) {
		String query = "SELECT C.*,I.status FROM candidate C, interview I "
				+ "WHERE I.candidate_id = C.id AND (I.status = 'NEW' OR I.status = 'scrPassed'"
				+ " OR I.status = 'scrFailed') AND I.hr_id = ?";
		List<Candidate> returnValue = null;
		Connection connection = null;
		PreparedStatement stmt = null;
		ResultSet result = null;
		try {
			connection = DBConnection.getConnection();
			stmt = connection.prepareStatement(query);
			stmt.setInt(1, hr_id);
			result = stmt.executeQuery();
			if (result.next()) {
				returnValue = new ArrayList<Candidate>();
				result.beforeFirst();
			}
			while (result.next()) {
				Candidate candidate = new Candidate();
				candidate.setId(result.getInt("id"));
				candidate.setName(result.getString("name"));
				candidate.setEmail(result.getString("email"));
				candidate.setStatus(result.getString("status"));
				returnValue.add(candidate);
			}
			stmt.close();
			result.close();

		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error("Unable to get the List of Screening Candidates ");
			LOGGER.error("Exception for " + e.getMessage());
		}
		return returnValue;
	}

	@Override
	public List<Candidate> getScreeningCandidateListByRecruiter(int hr_id) {
		String query = "SELECT C.*,I.status FROM candidate C, interview I "
				+ "WHERE I.candidate_id = C.id AND I.status = 'NEW' AND I.recruiter_id = ?";
		List<Candidate> returnValue = null;
		Connection connection = null;
		PreparedStatement stmt = null;
		ResultSet result = null;
		try {
			connection = DBConnection.getConnection();
			stmt = connection.prepareStatement(query);
			stmt.setInt(1, hr_id);
			result = stmt.executeQuery();
			if(result.next()){
				returnValue = new ArrayList<Candidate>();
				result.beforeFirst();
			}
			while (result.next()) {
				Candidate candidate = new Candidate();
				candidate.setId(result.getInt("id"));
				candidate.setName(result.getString("name"));
				candidate.setEmail(result.getString("email"));
				candidate.setStatus(result.getString("status"));
				candidate.setAge(result.getInt("age"));
				candidate.setMobile(result.getLong("mobile"));
				returnValue.add(candidate);
			}
			stmt.close();
			result.close();

		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error("Unable to get the List of Screening Candidates ");
			LOGGER.error("Exception for " + e.getMessage());
		}
		return returnValue;
	}

	@Override
	public List<Candidate> getInterviewCandidateList(int hr_id) {
		String query = "SELECT C.*,I.status,I.date,I.place,I.time FROM candidate C, interview I "
				+ "WHERE I.candidate_id = C.id AND (I.status = 'INTERVIEW' OR "
				+ "I.status = 'intFailed' OR I.status = 'intPassed') AND I.hr_id = ?";
		List<Candidate> returnValue = null;
		Connection connection = null;
		PreparedStatement stmt = null;
		ResultSet result = null;
		try {
			connection = DBConnection.getConnection();
			stmt = connection.prepareStatement(query);
			stmt.setInt(1, hr_id);
			result = stmt.executeQuery();
			if(result.next()){
				returnValue = new ArrayList<Candidate>();
				result.beforeFirst();
			}
			while (result.next()) {
				Candidate candidate = new Candidate();
				Interview interview = new Interview();
				candidate.setId(result.getInt("id"));
				candidate.setName(result.getString("name"));
				candidate.setEmail(result.getString("email"));
				candidate.setStatus(result.getString("status"));
				candidate.setAge(result.getInt("age"));
				candidate.setMobile(result.getLong("mobile"));
				interview.setTime(result.getString("time"));
				interview.setDate(result.getString("date"));
				interview.setPlace(result.getString("place"));
				candidate.setInterview(interview);
				returnValue.add(candidate);
			}
			stmt.close();
			result.close();

		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error("Unable to get the List of Interview Candidates ");
			LOGGER.error("Exception for " + e.getMessage());
		}
		return returnValue;
	}

	@Override
	public Candidate getCandidate(int id) {
		String query = "SELECT * FROM candidate WHERE id = ?";
		Candidate returnValue = null;
		Connection connection = null;
		PreparedStatement stmt = null;
		ResultSet result = null;
		try {
			connection = DBConnection.getConnection();
			stmt = connection.prepareStatement(query);
			stmt.setInt(1, id);
			result = stmt.executeQuery();
			if (result.next()) {
				returnValue = new Candidate();
				returnValue.setId(result.getInt("id"));
				returnValue.setName(result.getString("name"));
				returnValue.setEmail(result.getString("email"));
				returnValue.setDateOfBirth(result.getString("dateofbirth"));
				returnValue.setAge(result.getInt("age"));
				returnValue.setAddress(result.getString("address"));
				returnValue.setMobile(result.getLong("mobile"));
				returnValue.setResume(result.getBytes("resume"));
			}
			stmt.close();
			result.close();

		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error("Unable to get Candidate " + id);
			LOGGER.error("Exception for " + e.getMessage());
		}
		return returnValue;
	}

	@Override
	public Candidate getCandidateCtc(int id) {
		String query = "SELECT C.*, CT.*  FROM candidate C, ctc CT " + "WHERE CT.candidate_id = C.id AND C.id = ?";
		Candidate returnValue = null;
		Connection connection = null;
		PreparedStatement stmt = null;
		ResultSet result = null;
		try {
			connection = DBConnection.getConnection();
			stmt = connection.prepareStatement(query);
			stmt.setInt(1, id);
			result = stmt.executeQuery();
			if (result.next()) {
				Ctc ctc = new Ctc();
				returnValue = new Candidate();
				returnValue.setId(result.getInt("id"));
				returnValue.setName(result.getString("name"));
				returnValue.setEmail(result.getString("email"));
				returnValue.setDateOfBirth(result.getString("dateofbirth"));
				returnValue.setAge(result.getInt("age"));
				returnValue.setAddress(result.getString("address"));
				returnValue.setMobile(result.getLong("mobile"));
				returnValue.setResume(result.getBytes("resume"));
				ctc.setBasic(result.getDouble("basic"));
				ctc.setHra(result.getDouble("hra"));
				ctc.setDa(result.getDouble("da"));
				returnValue.setCtc(ctc);
			}
			stmt.close();
			result.close();

		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error("Unable to get Candidate " + id);
			LOGGER.error("Exception for " + e.getMessage());
		}
		return returnValue;
	}

	@Override
	public Candidate getCandidate(int id, String status) {
		String query = "SELECT C.* FROM candidate C, interview I WHERE  C.id = ? "
				+ "AND C.id = I.candidate_id AND I.status = ?";
		Candidate returnValue = null;
		Connection connection = null;
		PreparedStatement stmt = null;
		ResultSet result = null;
		try {
			connection = DBConnection.getConnection();
			stmt = connection.prepareStatement(query);
			stmt.setInt(1, id);
			stmt.setString(2, status);
			result = stmt.executeQuery();
			if (result.next()) {
				returnValue = new Candidate();
				returnValue.setId(result.getInt("id"));
				returnValue.setName(result.getString("name"));
				returnValue.setEmail(result.getString("email"));
				returnValue.setDateOfBirth(result.getString("dateofbirth"));
				returnValue.setAge(result.getInt("age"));
				returnValue.setAddress(result.getString("address"));
				returnValue.setMobile(result.getLong("mobile"));
				returnValue.setResume(result.getBytes("resume"));
			}
			stmt.close();
			result.close();

		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error("Unable to get Candidate " + id);
			LOGGER.error("Exception for " + e.getMessage());
		}
		return returnValue;
	}

	@Override
	public List<Candidate> getFinalCandidateList(int hrId) {
		String query = "SELECT C.* FROM candidate C, interview I "
				+ "WHERE I.candidate_id = C.id AND I.status = 'FINAL' AND I.hr_id = ?";
		List<Candidate> returnValue = null;
		Connection connection = null;
		PreparedStatement stmt = null;
		ResultSet result = null;
		try {
			connection = DBConnection.getConnection();
			stmt = connection.prepareStatement(query);
			stmt.setInt(1, hrId);
			result = stmt.executeQuery();
			if (result.next()) {
				returnValue = new ArrayList<Candidate>();
				result.beforeFirst();
			}
			while (result.next()) {
				Candidate candidate = new Candidate();
				candidate.setId(result.getInt("id"));
				candidate.setName(result.getString("name"));
				candidate.setEmail(result.getString("email"));
				candidate.setDateOfBirth(result.getString("dateofbirth"));
				candidate.setAge(result.getInt("age"));
				candidate.setAddress(result.getString("address"));
				candidate.setMobile(result.getLong("mobile"));
				candidate.setResume(result.getBytes("resume"));
				returnValue.add(candidate);
			}
			stmt.close();
			result.close();

		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error("Unable to get the List of Final Candidates ");
			LOGGER.error("Exception for " + e.getMessage());
		}
		return returnValue;
	}

	@Override
	public List<Candidate> getInterviewCandidateListByRecruiter(int recId) {
		String query = "SELECT C.* FROM candidate C, interview I "
				+ "WHERE I.candidate_id = C.id AND I.status = 'INTERVIEW' AND I.recruiter_id = ?";
		List<Candidate> returnValue = null;
		Connection connection = null;
		PreparedStatement stmt = null;
		ResultSet result = null;
		try {
			connection = DBConnection.getConnection();
			stmt = connection.prepareStatement(query);
			stmt.setInt(1, recId);
			result = stmt.executeQuery();
			if(result.next()){
				returnValue = new ArrayList<Candidate>();
				result.beforeFirst();
			}
			while (result.next()) {
				Candidate candidate = new Candidate();
				candidate.setId(result.getInt("id"));
				candidate.setName(result.getString("name"));
				candidate.setEmail(result.getString("email"));
				candidate.setDateOfBirth(result.getString("dateofbirth"));
				candidate.setAge(result.getInt("age"));
				candidate.setAddress(result.getString("address"));
				candidate.setMobile(result.getLong("mobile"));
				candidate.setResume(result.getBytes("resume"));
				returnValue.add(candidate);
			}
			stmt.close();
			result.close();

		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error("Unable to get the List of Interview Candidates ");
			LOGGER.error("Exception for " + e.getMessage());
		}
		return returnValue;
	}

	@Override
	public List<Candidate> getCtcCandidateList() {
		String query = "SELECT C.*, CT.*  FROM candidate C, ctc CT " + "WHERE CT.candidate_id = C.id ";
		List<Candidate> returnValue = null;
		Connection connection = null;
		PreparedStatement stmt = null;
		ResultSet result = null;
		try {
			connection = DBConnection.getConnection();
			stmt = connection.prepareStatement(query);
			result = stmt.executeQuery();
			if(result.next()){
				returnValue = new ArrayList<Candidate>();
				result.beforeFirst();
			}
			while (result.next()) {
				Ctc ctc = new Ctc();
				Candidate candidate = new Candidate();
				candidate.setId(result.getInt("id"));
				candidate.setName(result.getString("name"));
				candidate.setEmail(result.getString("email"));
				candidate.setDateOfBirth(result.getString("dateofbirth"));
				candidate.setAge(result.getInt("age"));
				candidate.setAddress(result.getString("address"));
				candidate.setMobile(result.getLong("mobile"));
				candidate.setResume(result.getBytes("resume"));
				ctc.setBasic(result.getDouble("basic"));
				ctc.setHra(result.getDouble("hra"));
				ctc.setDa(result.getDouble("da"));
				candidate.setCtc(ctc);
				returnValue.add(candidate);
			}
			stmt.close();
			result.close();

		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error("Unable to get the List of Selected Candidates ");
			LOGGER.error("Exception for " + e.getMessage());
		}
		return returnValue;
	}

}