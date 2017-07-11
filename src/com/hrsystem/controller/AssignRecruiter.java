package com.hrsystem.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.hrsystem.dao.IInterviewDao;
import com.hrsystem.dao.factory.DaoFactory;

/**
 * Servlet implementation class AssignRecruiter
 */
@WebServlet(name = "assignRecruiter", urlPatterns = { "/hr/assignRecruiter" })
public class AssignRecruiter extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8248382994291556676L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendRedirect(request.getContextPath()+"/candidates/new");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String status = request.getParameter("status");
		HttpSession session = request.getSession(false);
		if(status !=null && "NEW".equals(status)){
			String candidateId = request.getParameter("candidateId");
			String recruiterId = request.getParameter("recruiterId");
			String hrId = ""+session.getAttribute("hrID");
			IInterviewDao interviewDao = DaoFactory.getInterviewDao();
			interviewDao.assignScreeningRecruiter(status,candidateId,recruiterId,hrId);
			
		}
		else if(status !=null && "INTERVIEW".equals(status)){
			String candidateId = request.getParameter("candidateId");
			String recruiterId = request.getParameter("recruiterId");
			String hrId = ""+session.getAttribute("hrID");
			String place = request.getParameter("place");
			String date = request.getParameter("date");
			String time = request.getParameter("time");
			IInterviewDao interviewDao = DaoFactory.getInterviewDao();
			interviewDao.assignInterviewRecruiter(time,date,place,status,candidateId,recruiterId,hrId);
		}
		else if(status !=null && "FINAL".equals(status)){
			String candidateId = request.getParameter("candidateId");
			String hrId = ""+session.getAttribute("hrID");
			IInterviewDao interviewDao = DaoFactory.getInterviewDao();
			interviewDao.assignHR(status,candidateId,hrId);
		}
		response.sendRedirect(request.getContextPath()+"/candidates/finalists");
		
	}

}
