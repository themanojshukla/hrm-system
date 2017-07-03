package com.hrsystem.controller.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class AdminLogout
 */
@WebServlet(name = "AdminLogout", urlPatterns = { "/adminLogout" })
public class AdminLogout extends HttpServlet {
	
	private static final long serialVersionUID = -4442097404210954543L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		if(session == null){
			response.sendRedirect(request.getContextPath()+"/");
		}
		else{
			session.removeAttribute("adminName");
			session.invalidate();
			request.setAttribute("errorMessage", "Admin Logged Out !!");
			request.getRequestDispatcher("/index.jsp").forward(request, response);
		}
	}
}