package com.hrsystem.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.hrsystem.dao.IRecruiterDao;
import com.hrsystem.dao.factory.DaoFactory;
import com.hrsystem.model.Recruiter;

/**
 * Servlet implementation class Recruiters
 */
@WebServlet(urlPatterns={"/recruiters", "/recruiters/*"})
public class Recruiters extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		IRecruiterDao recDao = DaoFactory.getRecruiterDao();
		List<Recruiter> recList = recDao.getRecruiterList();
		request.setAttribute("recList", recList);
		request.getRequestDispatcher("/admin/recList.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		String name = request.getParameter("recname");
		String email = request.getParameter("recemail");
		String passwd = request.getParameter("recpasswd");
		String skills = request.getParameter("recskills");
		if (name == null || email == null || passwd == null || skills == null
				|| name.isEmpty() || email.isEmpty() || passwd.isEmpty()
				|| skills.isEmpty()) {
			session.setAttribute("color", "red");
			session.setAttribute("recAddMessage", "Please fill the form.");
			response.sendRedirect(request.getContextPath() + "/admin/");
		} else {
			Recruiter recruiter = new Recruiter();
			recruiter.setEmail(email);
			recruiter.setName(name);
			recruiter.setPasswd(passwd);
			recruiter.setSkills(skills);
			IRecruiterDao recruiterDao = DaoFactory.getRecruiterDao();
			if (recruiterDao.isExistingByEmail(email)) {
				session.setAttribute("color", "red");
				session.setAttribute("recAddMessage",
						"Oops..!! This is an existing Recruiter.");
				response.sendRedirect(request.getContextPath() + "/admin/");
			} else {
				recruiterDao.addNewRecruiter(recruiter);
				session.setAttribute("color", "green");
				session.setAttribute("recAddMessage",
						"New Recruiter added Successfully.");
				response.sendRedirect(request.getContextPath() + "/admin/");
			}
		}
	}

}
