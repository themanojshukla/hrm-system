package com.hrsystem.init;

import com.hrsystem.dao.IAdminDao;
import com.hrsystem.dao.ICandidateDao;
import com.hrsystem.dao.ICtcDao;
import com.hrsystem.dao.IHrDao;
import com.hrsystem.dao.IInterviewDao;
import com.hrsystem.dao.IRecruiterDao;
import com.hrsystem.dao.impl.AdminDao;
import com.hrsystem.dao.impl.CandidateDao;
import com.hrsystem.dao.impl.CtcDao;
import com.hrsystem.dao.impl.HrDao;
import com.hrsystem.dao.impl.InterviewDao;
import com.hrsystem.dao.impl.RecruiterDao;

public class DaoFactory {
	// Singleton Object Creation

	private static IAdminDao adminDao;
	private static IHrDao hrDao;
	private static IRecruiterDao recruiterDao;
	private static ICtcDao ctcDao;
	private static ICandidateDao candidateDao;
	private static IInterviewDao interviewDao;

	public static IAdminDao getAdminDao() {
		if (adminDao == null) {
			adminDao = new AdminDao();
		}
		return adminDao;
	}

	public static IHrDao getHrDao() {
		if (hrDao == null) {
			hrDao = new HrDao();
		}
		return hrDao;
	}

	public static IRecruiterDao getRecruiterDao() {
		if(recruiterDao == null){
			recruiterDao = new RecruiterDao();
		}
		return recruiterDao;
	}

	public static ICandidateDao getCandidateDao() {
		if(candidateDao == null){
			candidateDao = new CandidateDao();
		}
		return candidateDao;
	}

	public static IInterviewDao getInterviewDao() {
		if(interviewDao == null){
			interviewDao = new InterviewDao();
		}
		return interviewDao;
	}

	public static ICtcDao getCtcDao() {
		if(ctcDao == null){
			ctcDao = new CtcDao();
		}
		return ctcDao;
	}

}