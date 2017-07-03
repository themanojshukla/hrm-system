package com.hrsystem.dao;

import com.hrsystem.model.Candidate;


public interface ICTCDAO {
	
	double getTotalSalary(Candidate candidate);
	void setBasic(double basic);
	void setHRA(double hra);
	void setDA(double da);
	
	
}
