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
 * Servlet implementation class EditHR
 */
@WebServlet(name = "editHr", urlPatterns = {"/editHr"})
public class EditHR extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		response.sendRedirect(request.getContextPath() + "/admin");
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String hrId = request.getParameter("hrId");

		IHRDAO hrDAO = new HRDAO();
		if (hrDAO.isExistingById(Integer.parseInt(hrId))) {
			HR hr = new HR();
			hr = hrDAO.getHRById(Integer.parseInt(hrId));
			request.setAttribute("hr",hr);
			request.getRequestDispatcher("admin/editHr.jsp").forward(request, response);
			//response.sendRedirect(request.getContextPath() + "/hrList");
		} else {
			request.setAttribute("message",
					"<span style='color: red'>Sorry...! This HR Doesn't exists.</span>");
			request.getRequestDispatcher("/hrList").forward(request, response);
		}
	}
}