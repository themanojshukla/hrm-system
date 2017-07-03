package com.hrsystem.controller.admin;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hrsystem.dao.IHRDAO;
import com.hrsystem.dao.factory.DAOFactory;
import com.hrsystem.model.HR;

/**
 * Servlet implementation class ListAllHr
 */
@WebServlet(name = "hrList", urlPatterns = { "/admin/hrList" })
public class ListAllHr extends HttpServlet {

	private static final long serialVersionUID = 3225883686425658366L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		IHRDAO hrDAO = DAOFactory.getHRDAO();
		List<HR> hrList = hrDAO.getHRList();
		request.setAttribute("hrList", hrList);
		request.getRequestDispatcher("../admin/hrList.jsp").forward(request, response);
	}
}