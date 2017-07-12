package com.hrsystem.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.hrsystem.dao.IAdminDao;
import com.hrsystem.init.DaoFactory;

/**
 * Servlet implementation class AdminLogin
 */
@WebServlet(name = "AdminLogin", urlPatterns = { "/adminLogin" })
public class AdminLogin extends HttpServlet {

	private static final long serialVersionUID = 5044252826829317401L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.sendRedirect(request.getContextPath() + "/admin");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession(false);
		if (session != null && session.getAttribute("adminName") == null) {

			String email = request.getParameter("email");
			String passwd = request.getParameter("passwd");

			IAdminDao adminDAO = DaoFactory.getAdminDao();

			if (adminDAO.authenticateAdmin(email, passwd)) {
				session = request.getSession(true);
				session.setAttribute("adminName", adminDAO.getAdminName(email));
				response.sendRedirect(request.getContextPath() + "/admin");
			}
			else {
				request.setAttribute("errorMessage", "Plz enter valid credentials !!");
				request.getRequestDispatcher("/admin/index.jsp").forward(request, response);
			}
		}
		else {
			response.sendRedirect(request.getContextPath() + "/admin");
		}
	}
}