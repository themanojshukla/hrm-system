package com.hrsystem.dao;

public interface IInterviewDao {

	void assignScreeningRecruiter(String status, String candidateId, String recruiterId, String hrId);

	void assignInterviewRecruiter(String time, String date, String place, String status, String candidateId, String recruiterId, String hrId);

	boolean isAllowed(int recId, int candidateId);

	void updateStatusByRecruiter(String status, int recID, int candidateId);

	void assignHR(String status, String candidateId, String hrId);

	void selectThisCandidate(String id, int hrId);

}
