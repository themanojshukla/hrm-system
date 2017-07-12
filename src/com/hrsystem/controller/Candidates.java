package com.hrsystem.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import com.hrsystem.dao.ICandidateDao;
import com.hrsystem.dao.IRecruiterDao;
import com.hrsystem.init.DaoFactory;
import com.hrsystem.init.MyUtils;
import com.hrsystem.model.Candidate;
import com.hrsystem.model.Recruiter;

/**
 * Servlet implementation class Candidates
 * 
 * This servlet handles only requests made by HR-type user.
 * 
 */

@WebServlet(urlPatterns = { "/hr/candidates", "/hr/candidates/*" })
@MultipartConfig

public class Candidates extends HttpServlet {

    private static final long serialVersionUID = 306339039043620631L;

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     *      response)
     * 
     *      'GET' method is used here to send back various types of resources
     *      that are related to candidate.
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException {

	String path = request.getRequestURI();
	String[] pathParts = path.split("/");
	System.out.println(pathParts[0]);
	//
	//
	//
	
	if (pathParts.length > 4 && pathParts[4] != null) {

	    if (pathParts.length > 4 && pathParts[4].matches("[0-9]+")) {

		ICandidateDao candidateDao = DaoFactory.getCandidateDao();
		Candidate candidate = candidateDao.getCandidate(Integer.parseInt(pathParts[4]));

		if (candidate == null) {
		    response.sendRedirect(request.getContextPath() + "/hr/candidates");
		} else {
		    request.setAttribute("candidateInfo", candidate);
		    request.getRequestDispatcher("/hr/candidateInfo.jsp").forward(request, response);
		}

	    } else if (pathParts[4].equals("new")) {

		ICandidateDao candidateDao = DaoFactory.getCandidateDao();
		List<Candidate> newCandidateList = candidateDao.getNewCandidateList();

		IRecruiterDao recruiterDao = DaoFactory.getRecruiterDao();
		List<Recruiter> recruiterList = recruiterDao.getRecruiterList();

		request.setAttribute("recruiterList", recruiterList);
		request.setAttribute("newCandidateList", newCandidateList);

		request.getRequestDispatcher("/hr/newCandidateList.jsp").forward(request, response);

	    } else if (pathParts[4].equals("screenings")) {

		HttpSession session = request.getSession(false);
		int hrId = (int) session.getAttribute("hrID");

		ICandidateDao candidateDao = DaoFactory.getCandidateDao();
		List<Candidate> screeningList = candidateDao.getScreeningCandidateList(hrId);

		IRecruiterDao recruiterDao = DaoFactory.getRecruiterDao();
		List<Recruiter> recruiterList = recruiterDao.getRecruiterList();

		request.setAttribute("recruiterList", recruiterList);
		request.setAttribute("screeningList", screeningList);

		request.getRequestDispatcher("/hr/screeningList.jsp").forward(request, response);
	    }

	    else if (pathParts[4].equals("interviews")) {

		HttpSession session = request.getSession(false);
		int hrId = (int) session.getAttribute("hrID");

		ICandidateDao candidateDao = DaoFactory.getCandidateDao();
		List<Candidate> interviewList = candidateDao.getInterviewCandidateList(hrId);

		IRecruiterDao recruiterDao = DaoFactory.getRecruiterDao();
		List<Recruiter> recruiterList = recruiterDao.getRecruiterList();

		request.setAttribute("recruiterList", recruiterList);
		request.setAttribute("interviewList", interviewList);

		request.getRequestDispatcher("/hr/interviewList.jsp").forward(request, response);

	    } else if (pathParts[4].equals("finalists")) {

		HttpSession session = request.getSession(false);
		int hrId = (int) session.getAttribute("hrID");

		ICandidateDao candidateDao = DaoFactory.getCandidateDao();
		List<Candidate> finalList = candidateDao.getFinalCandidateList(hrId);

		request.setAttribute("finalList", finalList);

		request.getRequestDispatcher("/hr/finalList.jsp").forward(request, response);

	    } else if (pathParts[4].equals("ctcs")) {

		if (pathParts.length > 5 && pathParts[5].matches("[0-9]+")) {

		    ICandidateDao candidateDao = DaoFactory.getCandidateDao();
		    int candidateId = Integer.parseInt(pathParts[5]);

		    Candidate candidate = candidateDao.getCandidateCtc(candidateId);

		    request.setAttribute("candidateInfo", candidate);
		    request.getRequestDispatcher("/hr/candidateInfoCtc.jsp").forward(request, response);

		} else {

		    ICandidateDao candidateDao = DaoFactory.getCandidateDao();
		    List<Candidate> ctcList = candidateDao.getCtcCandidateList();

		    request.setAttribute("ctcList", ctcList);
		    request.getRequestDispatcher("/hr/ctcList.jsp").forward(request, response);

		}
	    } else {

		ICandidateDao candidateDao = DaoFactory.getCandidateDao();
		List<Candidate> candidateList = candidateDao.getCandidateList();

		request.setAttribute("candidateList", candidateList);
		request.getRequestDispatcher("/hr/candidateList.jsp").forward(request, response);

	    }
	} else {

	    ICandidateDao candidateDao = DaoFactory.getCandidateDao();
	    List<Candidate> candidateList = candidateDao.getCandidateList();

	    request.setAttribute("candidateList", candidateList);
	    request.getRequestDispatcher("/hr/candidateList.jsp").forward(request, response);

	}
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     *      response)
     * 
     *      'POST' is to add new candidates.
     * 
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException {

	HttpSession session = request.getSession(false);

	String name = request.getParameter("canname");
	String email = request.getParameter("canemail");
	String dateOfBirth = request.getParameter("candateofbirth");
	String ageString = request.getParameter("canage");
	String address = request.getParameter("canaddress");
	String mobileString = request.getParameter("canmobile");

	Part file = request.getPart("canresume");

	if (name == null || email == null || dateOfBirth == null || ageString == null || address == null
		|| mobileString == null || file == null) {
	    request.getRequestDispatcher("/hr/candidateList.jsp").forward(request, response);
	} else {

	    long mobile = Long.parseLong(mobileString);
	    int age = Integer.parseInt(ageString);
	    byte[] resume = MyUtils.getByteArray(file); // convert file to
							// byte[] array

	    Candidate candidate = new Candidate();
	    candidate.setEmail(email);
	    candidate.setName(name);
	    candidate.setAge(age);
	    candidate.setAddress(address);
	    candidate.setDateOfBirth(dateOfBirth);
	    candidate.setMobile(mobile);
	    candidate.setResume(resume);

	    ICandidateDao candidateDAO = DaoFactory.getCandidateDao();

	    if (candidateDAO.isExistingByEmail(email)) {

		session.setAttribute("color", "red");
		session.setAttribute("candidateAddMessage", "Oops..!! This is an existing Candidate.");
		response.sendRedirect(request.getContextPath() + "/hr/");

	    } else {

		candidateDAO.addNewCandidate(candidate);

		session.setAttribute("color", "green");
		session.setAttribute("candidateAddMessage", "New Candidate added Successfully.");
		response.sendRedirect(request.getContextPath() + "/hr/");
	    }
	}
    }
}