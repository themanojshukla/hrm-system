package com.hrsystem.controller.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.hrsystem.dao.IHRDAO;
import com.hrsystem.dao.factory.DAOFactory;
import com.hrsystem.model.HR;

/**
 * Servlet implementation class EditHR
 */
@WebServlet(name = "editHr", urlPatterns = {"/admin/editHr"})
public class EditHR extends HttpServlet {
	
	private static final long serialVersionUID = -3696567214067992719L;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		response.sendRedirect(request.getContextPath() + "/admin/");
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String hrId = request.getParameter("hrId");
		int id = Integer.parseInt(hrId);
		IHRDAO hrDAO = DAOFactory.getHRDAO();
		if (hrDAO.isExistingById(id)) {
			HR hr = hrDAO.getHRById(Integer.parseInt(hrId));
			HttpSession session = request.getSession(false);
			session.setAttribute("hr",hr);
			session.setAttribute("color", "blue");
			session.setAttribute("editHrMessage", "Current Name : "+hr.getName()
					+"<br> Current Email : "+hr.getEmail()+"<br><br>");
			response.sendRedirect(request.getContextPath() + "/admin/updateHr");
		} else {
			request.setAttribute("message",
					"<span style='color: red'>Sorry...! This HR Doesn't exists.</span>");
			request.getRequestDispatcher("/admin/hrList").forward(request, response);
		}
	}
}