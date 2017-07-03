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
 * Servlet implementation class UpdateHR
 */
@WebServlet(name = "updateHr", urlPatterns = {"/admin/updateHr"})
public class UpdateHR extends HttpServlet {

	private static final long serialVersionUID = 8443032717178129087L;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		if (session.getAttribute("hr") == null) {
			response.sendRedirect(request.getContextPath() + "/admin/");
		} else {
			request.getRequestDispatcher("../admin/editHr.jsp").forward(request,
					response);
		}

	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		String hrId = request.getParameter("hrId");
		int id = Integer.parseInt(hrId);
		String hrEmail = request.getParameter("hremail");
		String hrName = request.getParameter("hrname");

		HR hr = new HR();
		hr.setId(id);
		hr.setEmail(hrEmail);
		hr.setName(hrName);
		session.removeAttribute("editHrMessage");
		IHRDAO hrDAO = DAOFactory.getDAO(hr);
		if (hrDAO.isExistingById(hr.getId())) {
			if(hrDAO.isOtherExistingByEmail(hrEmail,id)){
				session.setAttribute("color", "red");
				session.setAttribute("hrAddMessage",
						"Sorry, This email id already exists for some other HR. !");
			response.sendRedirect(request.getContextPath() + "/admin/");	
			}
			else{
			hrDAO.updateHr();
			session.setAttribute("color", "green");
			session.setAttribute("hrAddMessage", "HR Updated Successfully !");
			response.sendRedirect(request.getContextPath() + "/admin/");
			}
			
		} else {
			session.setAttribute("color", "red");
			session.setAttribute("addHrMessage",
					"Sorry, This HR doesn't exists. !");
			response.sendRedirect(request.getContextPath() + "/admin/");
		}

	}
}
