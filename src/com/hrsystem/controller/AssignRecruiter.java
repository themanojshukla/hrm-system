package com.hrsystem.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.hrsystem.dao.IInterviewDao;
import com.hrsystem.init.DaoFactory;

/**
 * Servlet implementation class AssignRecruiter
 * 
 * Recruiters are assigned to every candidates only for 3 purposes.
 * 
 * 1. To do screening of candidates. 
 * 2. To Interview the candidates (Technical).
 * 3. To Interview the candidates (HR).
 */
@WebServlet(name = "assignRecruiter", urlPatterns = { "/hr/assignRecruiter" })
public class AssignRecruiter extends HttpServlet {

    private static final long serialVersionUID = -8248382994291556676L;

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException {
	response.sendRedirect(request.getContextPath() + "/candidates");
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException {
	String status = request.getParameter("status");
	HttpSession session = request.getSession(false);

	// status 'NEW' means : assign one recruiter for SCREENING.

	if (status != null && "NEW".equals(status)) {
	    String candidateId = request.getParameter("candidateId");
	    String recruiterId = request.getParameter("recruiterId");
	    String hrId = "" + session.getAttribute("hrID");

	    IInterviewDao interviewDao = DaoFactory.getInterviewDao();
	    interviewDao.assignScreeningRecruiter(status, candidateId, recruiterId, hrId);

	    response.sendRedirect(request.getContextPath() + "/hr/candidates/screenings");
	}

	// status 'INTERVIEW' means : assign one recruiter for INTERVIEW (technical).

	else if (status != null && "INTERVIEW".equals(status)) {
	    
	    String candidateId = request.getParameter("candidateId");
	    String recruiterId = request.getParameter("recruiterId");
	    String hrId = "" + session.getAttribute("hrID");
	    String place = request.getParameter("place");
	    String date = request.getParameter("date");
	    String time = request.getParameter("time");

	    IInterviewDao interviewDao = DaoFactory.getInterviewDao();
	    interviewDao.assignInterviewRecruiter(time, date, place, status, candidateId, recruiterId, hrId);

	    response.sendRedirect(request.getContextPath() + "/hr/candidates/interviews");
	}

	// status 'FINAL' means : assign recruiter for INTERVIEW (HR-Round).

	else if (status != null && "FINAL".equals(status)) {
	    String candidateId = request.getParameter("candidateId");
	    String hrId = "" + session.getAttribute("hrID");

	    IInterviewDao interviewDao = DaoFactory.getInterviewDao();
	    interviewDao.assignHR(status, candidateId, hrId);

	    response.sendRedirect(request.getContextPath() + "/hr/candidates/finalists");
	}
    }
}