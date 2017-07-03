package com.hrsystem.controller.admin;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hrsystem.dao.IRecruiterDAO;
import com.hrsystem.dao.factory.DAOFactory;
import com.hrsystem.model.Recruiter;

/**
 * Servlet implementation class ListAllRec
 */
@WebServlet(name = "recList", urlPatterns = { "/admin/recList" })
public class ListAllRecruiter extends HttpServlet {

	private static final long serialVersionUID = 3225883686425658366L;

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		IRecruiterDAO recDAO = DAOFactory.getRecruiterDAO();
		List<Recruiter> recList = recDAO.getRecruiterList();
		request.setAttribute("recList", recList);
		request.getRequestDispatcher("../admin/recList.jsp").forward(request, response);
	}
}