package com.hrsystem.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.hrsystem.dao.ICandidateDao;
import com.hrsystem.dao.IInterviewDao;
import com.hrsystem.dao.factory.DaoFactory;
import com.hrsystem.model.Candidate;

/**
 * Servlet implementation class RecCandidates
 */
@WebServlet(urlPatterns = { "/rec/candidates", "/rec/candidates/*" })


public class RecCandidates extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8432429279654690050L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String path = request.getRequestURI();
		System.out.println(path);
		String[] pathParts = path.split("/");
		System.out.println(pathParts.length);

		System.out.println("4th: " + pathParts[3] + "5th: " + pathParts[2]);
		if (pathParts.length > 4 && pathParts[4] != null) {

			/*
			 * if( ){
			 * 
			 * }
			 */
			/*
			 * else if(pathParts[4].equals("new")){ HttpSession session =
			 * request.getSession(false); int hrId = (int)
			 * session.getAttribute("hrID"); ICandidateDao candidateDao =
			 * DaoFactory.getCandidateDao(); List<Candidate> newCandidateList =
			 * candidateDao.getNewCandidateList();
			 * 
			 * IRecruiterDao recruiterDao = DaoFactory.getRecruiterDao();
			 * List<Recruiter> recruiterList = recruiterDao.getRecruiterList();
			 * request.setAttribute("recruiterList", recruiterList);
			 * request.setAttribute("newCandidateList", newCandidateList);
			 * request.getRequestDispatcher("/rec/newCandidateList.jsp").forward
			 * (request, response); }
			 */
			/* else */
			if (pathParts[4].equals("screening")) {

				if (pathParts.length > 5 && pathParts[5].matches("[0-9]+")) {
					HttpSession session = request.getSession(false);
					int recId = (int) session.getAttribute("recID");

					ICandidateDao candidateDao = DaoFactory.getCandidateDao();
					int candidateId = Integer.parseInt(pathParts[5]);
					Candidate candidate = candidateDao.getCandidate(candidateId, "NEW");
					IInterviewDao iInterviewDao = DaoFactory.getInterviewDao();
					if (iInterviewDao.isAllowed(recId, candidateId)) {
						request.setAttribute("allowed", "allowed");
					} else {
						request.setAttribute("allowed", "NotAllowed");
					}
					
					if(candidate ==null){
						response.sendRedirect(request.getContextPath()+"/rec/candidates/screening");	
					}
					else{
					request.setAttribute("for", "SCREENING");
					request.setAttribute("candidateInfo", candidate);
					request.getRequestDispatcher("/rec/candidateInfo.jsp").forward(request, response);
					}
				}

				else {

					HttpSession session = request.getSession(false);
					int recId = (int) session.getAttribute("recID");
					ICandidateDao candidateDao = DaoFactory.getCandidateDao();
					List<Candidate> screeningList = candidateDao.getScreeningCandidateListByRecruiter(recId);
					
					request.setAttribute("screeningList", screeningList);
					request.getRequestDispatcher("/rec/screeningList.jsp").forward(request, response);

				}
			} else if (pathParts[4].equals("interview")) {

				if (pathParts.length > 5 && pathParts[5].matches("[0-9]+")) {
					HttpSession session = request.getSession(false);
					int recId = (int) session.getAttribute("recID");

					ICandidateDao candidateDao = DaoFactory.getCandidateDao();
					int candidateId = Integer.parseInt(pathParts[5]);
					
					Candidate candidate = candidateDao.getCandidate(candidateId,"INTERVIEW");

					IInterviewDao iInterviewDao = DaoFactory.getInterviewDao();
					if (iInterviewDao.isAllowed(recId, candidateId)) {
						request.setAttribute("allowed", "allowed");
					} else {
						request.setAttribute("allowed", "NotAllowed");
					}
					 request.setAttribute("for", "INTERVIEW");
					request.setAttribute("candidateInfo", candidate);
					request.getRequestDispatcher("/rec/candidateInfo.jsp").forward(request, response);

				} else {
					HttpSession session = request.getSession(false);
					int recId = (int) session.getAttribute("recID");
					ICandidateDao candidateDao = DaoFactory.getCandidateDao();
					List<Candidate> interviewList = candidateDao.getInterviewCandidateListByRecruiter(recId);
					request.setAttribute("interviewList", interviewList);
					request.getRequestDispatcher("/rec/interviewList.jsp").forward(request, response);
				}

			} else if (pathParts[4].equals("finalists")) {
				
				HttpSession session = request.getSession(false);
				int hrId = (int) session.getAttribute("hrID");
				ICandidateDao candidateDao = DaoFactory.getCandidateDao();
				List<Candidate> finalList = candidateDao.getFinalCandidateList(hrId);
				request.setAttribute("finalList", finalList);

				request.getRequestDispatcher("/rec/finalList.jsp").forward(request, response);
			}

			else {
				ICandidateDao candidateDao = DaoFactory.getCandidateDao();
				List<Candidate> candidateList = candidateDao.getCandidateList();
				request.setAttribute("candidateList", candidateList);
				request.getRequestDispatcher("/rec/candidateList.jsp").forward(request, response);
			}
		} else {

			ICandidateDao candidateDao = DaoFactory.getCandidateDao();
			List<Candidate> candidateList = candidateDao.getCandidateList();
			request.setAttribute("candidateList", candidateList);
			request.getRequestDispatcher("/rec/candidateList.jsp").forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession(false);

		String id = request.getParameter("candidateId");
		int candidateId = Integer.parseInt(id);
		int recId = (int)session.getAttribute("recID");
		String decisionFor = request.getParameter("for");
		String accept = request.getParameter("accept");
		
		if("SCREENING".equals(decisionFor)) {
			if( "YES".equals(accept) ){
				IInterviewDao iInterviewDao = DaoFactory.getInterviewDao();
				iInterviewDao.updateStatusByRecruiter("scrPassed",recId,candidateId);
			}
			else{
				IInterviewDao iInterviewDao = DaoFactory.getInterviewDao();
				iInterviewDao.updateStatusByRecruiter("scrFailed",recId,candidateId);
			}
			response.sendRedirect(request.getContextPath()+"/rec/candidates/screening");
		}
		else if("INTERVIEW".equals(decisionFor)) {
			if( "YES".equals(accept) ){
				IInterviewDao iInterviewDao = DaoFactory.getInterviewDao();
				iInterviewDao.updateStatusByRecruiter("intPassed",recId,candidateId);
			}
			else{
				IInterviewDao iInterviewDao = DaoFactory.getInterviewDao();
				iInterviewDao.updateStatusByRecruiter("intFailed",recId,candidateId);
			}
			response.sendRedirect(request.getContextPath()+"/rec/candidates/interview");
		}
	}
}