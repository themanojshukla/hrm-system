package com.hrsystem.controller.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hrsystem.dao.IHrDao;
import com.hrsystem.init.DaoFactory;

/**
 * Servlet implementation class DeleteHR
 */
@WebServlet(name = "deleteHr", urlPatterns = {"/admin/deleteHr"})
public class DeleteHR extends HttpServlet {

	private static final long serialVersionUID = -857830464162055338L;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		response.sendRedirect(request.getContextPath() + "/admin/");
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String hrId = request.getParameter("hrId");
		IHrDao hrDao = DaoFactory.getHrDao();
		if (hrDao.isExistingById(Integer.parseInt(hrId))) {
			hrDao.removeExistingById(Integer.parseInt(hrId));
			request.setAttribute("deleteMessage", "<span style='color: green'>HR Deleted Successfully.</span>");
			response.sendRedirect(request.getContextPath()+"/hrs");
		} else {
			request.setAttribute("deleteMessage", "<span style='color: red'>Sorry...! This HR Doesn't exists.</span>");
			request.getRequestDispatcher("/hrs").forward(request, response);
		}
	}

}
