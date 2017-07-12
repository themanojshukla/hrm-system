package com.hrsystem.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class AdminServlet
 * 
 * Admin homepage contoller
 */
@WebServlet(name = "admin", urlPatterns = { "/admin", "/admin/" })
public class AdminServlet extends HttpServlet {

    private static final long serialVersionUID = -4729710381118326332L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException {
	request.getRequestDispatcher("/admin/mainpage.jsp").forward(request, response);

    }
}