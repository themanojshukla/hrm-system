package com.hrsystem.controller.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.hrsystem.dao.IRecruiterDAO;
import com.hrsystem.dao.impl.RecruiterDAO;
import com.hrsystem.model.Recruiter;

/**
 * Servlet implementation class UpdateHR
 */
@WebServlet(name = "updateRec", urlPatterns = {"/admin/updateRec"})
public class UpdateRecruiter extends HttpServlet {

	private static final long serialVersionUID = 8443032717178129087L;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		if (session.getAttribute("recruiter") == null) {
			response.sendRedirect(request.getContextPath() + "/admin/");
		} else {
			request.getRequestDispatcher("../admin/editRec.jsp").forward(request,
					response);
		}

	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession(false);
		String recId = request.getParameter("recId");
		int id = Integer.parseInt(recId);
		String recEmail = request.getParameter("recemail");
		String recName = request.getParameter("recname");
		String recSkills = request.getParameter("recskills");
		Recruiter recruiter = new Recruiter();
		recruiter.setId(id);
		recruiter.setEmail(recEmail);
		recruiter.setName(recName);
		recruiter.setSkills(recSkills);
		session.setAttribute("recruiter", recruiter);
		IRecruiterDAO recruiterDAO = new RecruiterDAO(recruiter);
		if (recruiterDAO.isExistingById(recruiter.getId())) {
			if(recruiterDAO.isOtherExistingByEmail(recEmail,id)){
				session.removeAttribute("recruiter");
				session.removeAttribute("editRecruiterMessage");
				session.setAttribute("color", "red");
				session.setAttribute("recAddMessage",
						"Sorry, This email id already exists for some other Recruiter. !");
			response.sendRedirect(request.getContextPath() + "/admin/");	
			}
			else{
			recruiterDAO.updateRecruiter();
			session.removeAttribute("recruiter");
			session.removeAttribute("editRecMessage");
			session.setAttribute("color", "green");
			session.setAttribute("recAddMessage", "Recruiter Updated Successfully !");
			response.sendRedirect(request.getContextPath() + "/admin/");
			}
			
		} else {
			session.removeAttribute("hr");
			session.removeAttribute("editHrMessage");
			session.setAttribute("color", "red");
			session.setAttribute("addHrMessage",
					"Sorry, This HR doesn't exists. !");
			response.sendRedirect(request.getContextPath() + "/admin/");
		}

	}
}
