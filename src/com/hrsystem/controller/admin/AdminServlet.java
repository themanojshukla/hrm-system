package com.hrsystem.controller.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class AdminServlet
 */
@WebServlet(name = "admin", urlPatterns = {"/admin" , "/admin/"})
public class AdminServlet extends HttpServlet {

	private static final long serialVersionUID = -4729710381118326332L;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Admin serv called");
		HttpSession session = request.getSession(false);
		if (session != null) {
			System.out.println("admin main");
			request.getRequestDispatcher("/admin/mainpage.jsp").forward(request,
					response);
		}
	}
}