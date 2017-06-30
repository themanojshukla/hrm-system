package com.hrsystem.controller.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.hrsystem.dao.IAdminDAO;
import com.hrsystem.dao.impl.AdminDAO;
import com.hrsystem.model.Admin;

/**
 * Servlet implementation class AdminLogin
 */
@WebServlet(name = "AdminLogin", urlPatterns = {"/adminLogin"})
public class AdminLogin extends HttpServlet {

	private static final long serialVersionUID = 5044252826829317401L;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		response.sendRedirect(request.getContextPath() + "/admin");
	}
	
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String email = request.getParameter("email");
		String passwd = request.getParameter("passwd");
		Admin admin = new Admin();
		admin.setEmail(email);
		admin.setPasswd(passwd);
		IAdminDAO adminDAO = new AdminDAO(admin);
		if (adminDAO.authenticateAdmin()) {
			HttpSession session = request.getSession(true);
			session.setAttribute("adminName", adminDAO.getAdminName(email));
			System.out.println("session set");
			response.sendRedirect(request.getContextPath() + "/admin");
		} else {
			request.setAttribute("errorMessage",
					"Plz enter valid credentials !!");
			request.getRequestDispatcher("../admin/index.jsp")
					.forward(request, response);
		}
	}
}