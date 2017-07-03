package com.hrsystem.controller.hr;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class HRServlet
 */
@WebServlet(name = "hr", urlPatterns = {"/hr", "/hr/"})
public class HRServlet extends HttpServlet {

	private static final long serialVersionUID = 7270895322380401179L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		if (session != null) {
			request.getRequestDispatcher("/hr/mainpage.jsp").forward(request,
					response);
		}
	}
}