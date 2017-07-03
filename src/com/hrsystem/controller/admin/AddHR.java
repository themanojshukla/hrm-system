package com.hrsystem.controller.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.hrsystem.dao.IHRDAO;
import com.hrsystem.dao.factory.DAOFactory;
import com.hrsystem.model.HR;

/**
 * Servlet implementation class AddHR
 */
@WebServlet(name = "addHr", urlPatterns = {"/admin/addHr"})
public class AddHR extends HttpServlet {

	private static final long serialVersionUID = -2087574126061302133L;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		response.sendRedirect(request.getContextPath() + "/admin/");
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		String name = request.getParameter("hrname");
		String email = request.getParameter("hremail");
		String passwd = request.getParameter("hrpasswd");
		if (name == null || email == null || passwd == null || name.isEmpty()
				|| email.isEmpty() || passwd.isEmpty()) {
			session.setAttribute("color", "red");
			session.setAttribute("hrAddMessage","Please fill the form.");
			response.sendRedirect(request.getContextPath() + "/admin/");
		} else {
			HR hr = new HR();
			hr.setEmail(email);
			hr.setName(name);
			hr.setPasswd(passwd);
			IHRDAO hrDAO = DAOFactory.getDAO(hr);
			if (hrDAO.isExisting()) {
				session.setAttribute("color", "red");
				session.setAttribute("hrAddMessage","Oops..!! This is an existing HR.");
				response.sendRedirect(request.getContextPath() + "/admin/");
			} else {
				hrDAO.addNewHR();
				session.setAttribute("color", "green");
				session.setAttribute("hrAddMessage","New HR added Successfully.");
				response.sendRedirect(request.getContextPath() + "/admin/");
			}
		}
	}
}