package com.hrsystem.controller.hr;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.hrsystem.dao.IHRDAO;
import com.hrsystem.dao.impl.HRDAO;
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
		String email = request.getParameter("email");
		String passwd = request.getParameter("passwd");
		HR hr = new HR();
		hr.setEmail(email);
		hr.setPasswd(passwd);
		IHRDAO hrDAO = new HRDAO(hr);
		if (hrDAO.authenticateHR()) {
			HttpSession session = request.getSession(true);
			session.setAttribute("hrName", hrDAO.getNameByEmail(email));
			response.sendRedirect(request.getContextPath() + "/hr");
		} else {
			request.setAttribute("errorMessage",
					"Plz enter valid credentials !!");
			request.getRequestDispatcher("/hr/index.jsp")
					.forward(request, response);
		}
	}
}