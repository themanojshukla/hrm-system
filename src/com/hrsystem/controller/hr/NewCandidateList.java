package com.hrsystem.controller.hr;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.HttpConstraint;
import javax.servlet.annotation.HttpMethodConstraint;
import javax.servlet.annotation.ServletSecurity;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class NewCandidateList
 */
@WebServlet(name = "hr/newCandidateList", urlPatterns = { "/hr/newCandidateList" })
@ServletSecurity(value=@HttpConstraint(),

httpMethodConstraints={ @HttpMethodConstraint("POST"),

@HttpMethodConstraint("TRACE") })
public class NewCandidateList extends HttpServlet {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1305859410154455304L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.print("get Called");
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.print("Post Called");
	}

}