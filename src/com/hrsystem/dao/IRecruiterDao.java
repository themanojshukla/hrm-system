package com.hrsystem.dao;

import java.util.List;

import com.hrsystem.model.Recruiter;

public interface IRecruiterDao {
	
	void removeExistingByEmail(String email);
	void removeExistingById(int id);

	boolean isExistingByEmail(String email);
	boolean isExistingById(int Id);
	boolean isOtherExistingByEmail(String email, int id);

	String getNameByEmail(String email);
	String getNameById(int id);
	
	int getIdByEmail(String email);

	List<Recruiter> getRecruiterList();
	List<Recruiter> getRecruiterListBySkills(String skills);
	
	Recruiter getRecruiterByEmail(String email);
	Recruiter getRecruiterById(int id);

	void updateRecruiter(Recruiter recruiter);

	void addNewRecruiter(Recruiter recruiter);
	boolean authenticateRecruiter(String email, String passwd);
	
}
