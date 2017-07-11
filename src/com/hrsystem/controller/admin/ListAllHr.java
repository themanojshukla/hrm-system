package com.hrsystem.controller.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ListAllHr
 */
@WebServlet(name = "hrList", urlPatterns = { "/admin/hrList" })
public class ListAllHr extends HttpServlet {

	private static final long serialVersionUID = 3225883686425658366L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	}
}