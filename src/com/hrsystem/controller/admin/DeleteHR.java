package com.hrsystem.controller.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hrsystem.dao.IHRDAO;
import com.hrsystem.dao.impl.HRDAO;
import com.hrsystem.model.HR;

/**
 * Servlet implementation class DeleteHR
 */
@WebServlet(name = "deleteHr", urlPatterns = {"/deleteHr"})
public class DeleteHR extends HttpServlet {

	private static final long serialVersionUID = -857830464162055338L;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		response.sendRedirect(request.getContextPath() + "/admin");
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String hrId = request.getParameter("hrId");

		HR hr = new HR();
		hr.setId(Integer.parseInt(hrId));
		IHRDAO hrDAO = new HRDAO(hr);
		if (hrDAO.isExistingById()) {
			hrDAO.removeExisting();
			request.setAttribute("deleteMessage", "<span style='color: green'>HR Deleted Successfully.</span>");
			//request.getRequestDispatcher("/hrList").forward(request, response);
			response.sendRedirect(request.getContextPath()+"/hrList");
		} else {
			request.setAttribute("deleteMessage", "<span style='color: red'>Sorry...! This HR Doesn't exists.</span>");
			request.getRequestDispatcher("/hrList").forward(request, response);
		}
	}

}
