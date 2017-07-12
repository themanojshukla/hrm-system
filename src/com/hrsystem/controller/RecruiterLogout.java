package com.hrsystem.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class RecruiterLogout
 */
@WebServlet(name = "RecruiterLogout", urlPatterns = { "/recruiterLogout" })
public class RecruiterLogout extends HttpServlet {

    private static final long serialVersionUID = 5016683726702150426L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException {
	doPost(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException {
	HttpSession session = request.getSession(false);
	if (session == null) {
	    response.sendRedirect(request.getContextPath() + "/");
	} else {
	    session.removeAttribute("recName");
	    session.removeAttribute("recID");
	    session.invalidate();
	    response.sendRedirect(request.getContextPath());
	}
    }
}