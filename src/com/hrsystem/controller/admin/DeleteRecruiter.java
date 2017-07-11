package com.hrsystem.controller.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hrsystem.dao.IRecruiterDao;
import com.hrsystem.dao.factory.DaoFactory;

/**
 * Servlet implementation class DeleteHR
 */
@WebServlet(name = "deleteRec", urlPatterns = {"/admin/deleteRec"})
public class DeleteRecruiter extends HttpServlet {

	private static final long serialVersionUID = -5719510353888797880L;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		response.sendRedirect(request.getContextPath() + "/admin/");
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		String recId = request.getParameter("recId");
		IRecruiterDao recruiterDao = DaoFactory.getRecruiterDao();
		if (recruiterDao.isExistingById(Integer.parseInt(recId))) {
			recruiterDao.removeExistingById(Integer.parseInt(recId));
			response.sendRedirect(request.getContextPath()+"/admin/recList");
		} else {
			response.sendRedirect(request.getContextPath()+"/admin/");
		}
	}

}
