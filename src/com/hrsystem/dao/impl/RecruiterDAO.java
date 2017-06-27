package com.hrsystem.dao.impl;

import java.util.List;

import com.hrsystem.dao.IRecruiterDAO;
import com.hrsystem.model.Recruiter;

public class RecruiterDAO implements IRecruiterDAO{

	private Recruiter recruiter;
	public RecruiterDAO() {
		
	}
	
	public RecruiterDAO(Recruiter recruiter) {
		this.recruiter = recruiter;
	}
	
	@Override
	public void addNewRecruiter() {
		
		
	}
	@Override
	public void removeExistingByEmail(String email) {
		// TODO Auto-generated method stub
		
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
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public List<Recruiter> getRecruiterListBySkills(String skills) {
		// TODO Auto-generated method stub
		return null;
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
