package com.hrsystem.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.hrsystem.dao.IHrDao;
import com.hrsystem.dao.factory.DaoFactory;
import com.hrsystem.model.HR;

/**
 * Servlet implementation class HRLogin
 */
@WebServlet(urlPatterns = {"/hrLogin"})
public class HRLogin extends HttpServlet {

	private static final long serialVersionUID = 5044252826829317401L;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		response.sendRedirect(request.getContextPath() + "/hr");
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		if (session != null && session.getAttribute("hrName") == null) {

			String email = request.getParameter("email");
			String passwd = request.getParameter("passwd");
			IHrDao hrDAO = DaoFactory.getHrDao();
			if (hrDAO.authenticateHR(email, passwd)) {
				session = request.getSession(true);
				session.setAttribute("hrName", hrDAO.getNameByEmail(email));
				session.setAttribute("hrID", hrDAO.getIdByEmail(email));
				response.sendRedirect(request.getContextPath() + "/hr");
			} else {
				request.setAttribute("errorMessage",
						"Plz enter valid credentials !!");
				request.getRequestDispatcher("/hr/index.jsp").forward(request,
						response);
			}
		} else {
			response.sendRedirect(request.getContextPath() + "/hr");
		}
	}
}