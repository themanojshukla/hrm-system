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
import com.hrsystem.dao.IInterviewDao;
import com.hrsystem.dao.IRecruiterDao;
import com.hrsystem.dao.factory.DaoFactory;
import com.hrsystem.model.Candidate;
import com.hrsystem.model.Recruiter;
import com.hrsystem.utils.MyUtils;

/**
 * Servlet implementation class Candidates
 */
@WebServlet(urlPatterns = { "/hr/candidates", "/hr/candidates/*" })
@MultipartConfig

public class Candidates extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 306339039043620631L;

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
		/*
		 * String path0 = pathParts[0]; // " " String path1 = pathParts[1]; //
		 * HRM-System String path2 = pathParts[2]; // candidates String path3 =
		 * path String path4 = pathParts[3]; // {candidate_id}
		 */
		System.out.println("4th: "+pathParts[3] + "5th: "+pathParts[2]);
		if(pathParts.length > 4 && pathParts[4] != null){
			
			if( pathParts.length > 4 && pathParts[4].matches("[0-9]+")){
				System.out.println("pat3 called");
				ICandidateDao candidateDao = DaoFactory.getCandidateDao();
				Candidate candidate = candidateDao.getCandidate(Integer.parseInt(pathParts[4]));
				if(candidate ==null){
					response.sendRedirect(request.getContextPath()+"/hr/candidates");	
				}
				else{
				request.setAttribute("candidateInfo", candidate);
				request.getRequestDispatcher("/hr/candidateInfo.jsp").forward(request, response);
				}
			}
			else if(pathParts[4].equals("new")){
				//HttpSession session = request.getSession(false);
				//int hrId = (int) session.getAttribute("hrID");
				ICandidateDao candidateDao = DaoFactory.getCandidateDao();
				List<Candidate> newCandidateList = candidateDao.getNewCandidateList();
				
				IRecruiterDao recruiterDao = DaoFactory.getRecruiterDao();
				List<Recruiter> recruiterList = recruiterDao.getRecruiterList();
				request.setAttribute("recruiterList", recruiterList);
				request.setAttribute("newCandidateList", newCandidateList);
				request.getRequestDispatcher("/hr/newCandidateList.jsp").forward(request, response);
			}
			else if (pathParts[4].equals("screenings")) {
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
			
			else if(pathParts[4].equals("interviews")){
				HttpSession session = request.getSession(false);
				int hrId = (int) session.getAttribute("hrID");
				ICandidateDao candidateDao = DaoFactory.getCandidateDao();
				List<Candidate> interviewList = candidateDao.getInterviewCandidateList(hrId);
				IRecruiterDao recruiterDao = DaoFactory.getRecruiterDao();
				List<Recruiter> recruiterList = recruiterDao.getRecruiterList();
				request.setAttribute("recruiterList", recruiterList);
				request.setAttribute("interviewList", interviewList);	
				request.getRequestDispatcher("/hr/interviewList.jsp").forward(request, response);
			} else if(pathParts[4].equals("finalists")){
				HttpSession session = request.getSession(false);
				int hrId = (int) session.getAttribute("hrID");
				ICandidateDao candidateDao = DaoFactory.getCandidateDao();
				List<Candidate> finalList = candidateDao.getFinalCandidateList(hrId);
				request.setAttribute("finalList", finalList);
				
				request.getRequestDispatcher("/hr/finalList.jsp").forward(request, response);
			}
			else if(pathParts[4].equals("ctcs")){
				if (pathParts.length > 5 && pathParts[5].matches("[0-9]+")) {
										ICandidateDao candidateDao = DaoFactory.getCandidateDao();
					int candidateId = Integer.parseInt(pathParts[5]);
					Candidate candidate = candidateDao.getCandidateCtc(candidateId);
					// request.setAttribute("for", "INTERVIEW");
					request.setAttribute("candidateInfo", candidate);
					request.getRequestDispatcher("/hr/candidateInfoCtc.jsp").forward(request, response);

				}
				else{
//				HttpSession session = request.getSession(false);
//				int hrId = (int) session.getAttribute("hrID");
				ICandidateDao candidateDao = DaoFactory.getCandidateDao();
				List<Candidate> ctcList = candidateDao.getCtcCandidateList();
				request.setAttribute("ctcList", ctcList);
				request.getRequestDispatcher("/hr/ctcList.jsp").forward(request, response);
				}
			}
			
			else {
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
			byte[] resume = MyUtils.getByteArray(file);
			int age = Integer.parseInt(ageString);
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