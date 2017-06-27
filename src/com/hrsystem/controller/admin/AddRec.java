package com.hrsystem.controller.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.hrsystem.dao.IHRDAO;
import com.hrsystem.dao.impl.HRDAO;
import com.hrsystem.dao.impl.RecruiterDAO;
import com.hrsystem.model.HR;
import com.hrsystem.model.Recruiter;

/**
 * Servlet implementation class AddRec
 */
@WebServlet(name = "addRec", urlPatterns = {"/addRec"})
public class AddRec extends HttpServlet {

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		response.sendRedirect(request.getContextPath() + "/admin");
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		String name = request.getParameter("recname");
		String email = request.getParameter("recemail");
		String passwd = request.getParameter("recpasswd");
		String skills = request.getParameter("recskills");
		if (name == null || email == null || passwd == null || skills == null
				|| name.isEmpty() || email.isEmpty() || passwd.isEmpty()
				|| skills.isEmpty()) {
			session.setAttribute("color", "red");
			session.setAttribute("hrAddMessage", "Please fill the form.");
			response.sendRedirect(request.getContextPath() + "/admin");
		} else {
			Recruiter recruiter = new Recruiter();
			recruiter.setEmail(email);
			recruiter.setName(name);
			recruiter.setPasswd(passwd);
			recruiter.setSkills(skills);
			RecruiterDAO recruiterDAO = new RecruiterDAO(recruiter);
			if (recruiterDAO.isExisting()) {
				session.setAttribute("color", "red");
				session.setAttribute("recAddMessage",
						"Oops..!! This is an existing Recruiter.");
				response.sendRedirect(request.getContextPath() + "/admin");
			} else {
				recruiterDAO.addNewRecruiter();
				session.setAttribute("color", "green");
				session.setAttribute("recAddMessage",
						"New Recruiter added Successfully.");
				response.sendRedirect(request.getContextPath() + "/admin");
			}
		}
	}

}
