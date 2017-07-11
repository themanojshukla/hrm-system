package com.hrsystem.dao;

public interface ICtcDao {
		
	void addCtc(String id, String basic, String hra, String da);

	double getTotalSalary(int candidateID);
	
}
