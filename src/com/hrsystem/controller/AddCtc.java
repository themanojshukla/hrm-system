package com.hrsystem.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hrsystem.dao.ICtcDao;
import com.hrsystem.dao.factory.DaoFactory;

/**
 * Servlet implementation class AddCtc
 */
@WebServlet(name = "addCtc", urlPatterns = { "/hr/addCtc" })
public class AddCtc extends HttpServlet {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8873163696918281460L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendRedirect(request.getContextPath()+"/hr/candidates/ctcs");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String basic = request.getParameter("basic");
		String hra = request.getParameter("hra");
		String da = request.getParameter("da");
		String id = request.getParameter("candidateId");
		ICtcDao iCtcDao = DaoFactory.getCtcDao();
		iCtcDao.addCtc(id,basic,hra,da);
		response.sendRedirect(request.getContextPath()+"/hr/candidates/ctcs");
	}

}
