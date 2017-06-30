package com.hrsystem.controller.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hrsystem.dao.IHRDAO;
import com.hrsystem.dao.impl.HRDAO;

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
		IHRDAO hrDAO = new HRDAO();
		if (hrDAO.isExistingById(Integer.parseInt(hrId))) {
			hrDAO.removeExistingById(Integer.parseInt(hrId));
			request.setAttribute("deleteMessage", "<span style='color: green'>HR Deleted Successfully.</span>");
			response.sendRedirect(request.getContextPath()+"/admin/hrList");
		} else {
			request.setAttribute("deleteMessage", "<span style='color: red'>Sorry...! This HR Doesn't exists.</span>");
			request.getRequestDispatcher("/admin/hrList").forward(request, response);
		}
	}

}
