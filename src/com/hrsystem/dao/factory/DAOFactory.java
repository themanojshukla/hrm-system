package com.hrsystem.dao.factory;

import com.hrsystem.dao.IAdminDAO;
import com.hrsystem.dao.IHRDAO;
import com.hrsystem.dao.IRecruiterDAO;
import com.hrsystem.dao.impl.AdminDAO;
import com.hrsystem.dao.impl.HRDAO;
import com.hrsystem.dao.impl.RecruiterDAO;
import com.hrsystem.model.Admin;
import com.hrsystem.model.HR;
import com.hrsystem.model.Recruiter;

public class DAOFactory {

	public static IAdminDAO getDAO(Admin admin) {
		return new AdminDAO(admin);
	}
	
	public static IAdminDAO getAdminDAO() {
		return new AdminDAO();
	}
	
	public static IHRDAO getDAO(HR hr) {
		return new HRDAO(hr);
	}
	
	public static IHRDAO getHRDAO() {
		return new HRDAO();
	}
	
	public static IRecruiterDAO getDAO(Recruiter recruiter) {
		return new RecruiterDAO(recruiter);
	}
	
	public static IRecruiterDAO getRecruiterDAO() {
		return new RecruiterDAO();
	}
	
}