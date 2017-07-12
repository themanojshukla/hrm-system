package com.hrsystem.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class RecruiterServlet
 * 
 * Recruiter's mainpage/homepage.
 */
@WebServlet(name = "rec", urlPatterns = { "/rec", "/rec/" })
public class RecruiterServlet extends HttpServlet {
    
    private static final long serialVersionUID = 8966338235324929789L;

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException {
	    request.getRequestDispatcher("/rec/mainpage.jsp").forward(request, response);
    }
    
}