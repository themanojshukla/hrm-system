package com.hrsystem.dao;

import java.util.List;

import com.hrsystem.model.Candidate;

public interface ICandidateDao {

	boolean isExistingByEmail(String email);

	int getIdByEmail(String email);

	List<Candidate> getCandidateList();

	void addNewCandidate(Candidate candidate);

	List<Candidate> getScreeningCandidateList(int hrId);

	Candidate getCandidate(int id);

	List<Candidate> getInterviewCandidateList(int hrId);

	List<Candidate> getNewCandidateList();

	List<Candidate> getFinalCandidateList(int hrId);

	List<Candidate> getScreeningCandidateListByRecruiter(int recId);

	List<Candidate> getInterviewCandidateListByRecruiter(int recId);

	Candidate getCandidate(int candidateId, String string);

	List<Candidate> getCtcCandidateList();

	Candidate getCandidateCtc(int id);

}
