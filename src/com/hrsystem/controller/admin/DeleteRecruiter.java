package com.hrsystem.controller.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hrsystem.dao.IRecruiterDAO;
import com.hrsystem.dao.impl.RecruiterDAO;

/**
 * Servlet implementation class DeleteHR
 */
@WebServlet(name = "deleteRec", urlPatterns = {"/deleteRec"})
public class DeleteRecruiter extends HttpServlet {

	private static final long serialVersionUID = -5719510353888797880L;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		response.sendRedirect(request.getContextPath() + "/admin");
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		String recId = request.getParameter("recId");
		IRecruiterDAO recruiterDAO = new RecruiterDAO();
		if (recruiterDAO.isExistingById(Integer.parseInt(recId))) {
			recruiterDAO.removeExistingById(Integer.parseInt(recId));
			response.sendRedirect(request.getContextPath()+"/recList");
		} else {
			response.sendRedirect(request.getContextPath()+"/admin");
		}
	}

}
