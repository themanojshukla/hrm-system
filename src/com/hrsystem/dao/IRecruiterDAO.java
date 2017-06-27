package com.hrsystem.dao;

import java.util.List;

import com.hrsystem.model.Recruiter;

public interface IRecruiterDAO {
	
	void addNewRecruiter();

	void removeExistingByEmail(String email);
	void removeExistingById(int id);

	boolean isExisting();
	boolean isExistingByEmail(String email);
	boolean isExistingById(int Id);

	String getNameByEmail(String email);
	String getNameById(int id);
	String getNameById();

	int getIdByEmail(String email);

	List<Recruiter> getRecruiterList();
	List<Recruiter> getRecruiterListBySkills(String skills);
	
	Recruiter getRecruiterByEmail(String email);
	Recruiter getRecruiterById(int id);
	
}
