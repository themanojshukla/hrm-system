package com.hrsystem.controller.admin;

import java.io.IOException;

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
 * Servlet implementation class EditHR
 */
@WebServlet(name = "editRec", urlPatterns = {"/admin/editRec"})
public class EditRecruiter extends HttpServlet {
	
	private static final long serialVersionUID = 9159821276713748113L;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		response.sendRedirect(request.getContextPath() + "/admin/");
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String recId = request.getParameter("recId");
		int id = Integer.parseInt(recId);
		IRecruiterDao recuiterDao = DaoFactory.getRecruiterDao();
		if (recuiterDao.isExistingById(id)) {
			Recruiter recruiter = recuiterDao.getRecruiterById(id);
			HttpSession session = request.getSession(false);
			session.setAttribute("recruiter",recruiter);
			session.setAttribute("color", "blue");
			session.setAttribute("editRecMessage", "Current Name : "+recruiter.getName()
					+"<br> Current Email : "+recruiter.getEmail()+
					"<br> Current Skills : "+recruiter.getSkills()+
					"<br><br>");
			response.sendRedirect(request.getContextPath() + "/admin/updateRec");
		} else {
			request.setAttribute("message",
					"<span style='color: red'>Sorry...! This Recruiter Doesn't exists.</span>");
			request.getRequestDispatcher("/admin/hrRec").forward(request, response);
		}
	}
}