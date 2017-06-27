package com.hrsystem.controller.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class UpdateHR
 */
@WebServlet(name = "updateHr", urlPatterns = { "/updateHr" })
public class UpdateHR extends HttpServlet {

	private static final long serialVersionUID = 8443032717178129087L;
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		response.sendRedirect(request.getContextPath()+"/admin");
	}
	
	protected void dopost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String hrId = request.getParameter("hrId");
		String hrname = request.getParameter("hrname");
		String hremail = request.getParameter("hremail");
	}
/*
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("hrname");
		String email = request.getParameter("hremail");
		String hrId = request.getParameter("hrId");
		if (name == null || email == null || hrId == null) {
			response.sendRedirect(request.getContextPath()+"/admin");
		} else {
			/PrintWriter out = response.getWriter();
			out.write("<span style='color: red;'>Please Enter </span>"
					+ "<div id='addHr' ><% HR hr= (HR)request.getAttribute(\"hr\");%>"
					+"<input type='hidden' id='hrurl'  name='hrurl' value='<%=request.getContextPath() %>/updateHr' />"
					+"Name : <input type='text' id='hrname' name='hrname' value='<%=hr.getName() %>' /><br>"
					+ "Email : <input type='email' id='hremail' name='hremail' value='<%=hr.getEmail() %>' /><br>"
					+"<input type='hidden' id='hrId' name='hrId'  value='<%=hr.getId() %>' /><br><br>"
					+"<button onclick='addHr()' >Update HR </button></div>");
			HR hr = new HR();
			hr.setEmail(email);
			hr.setName(name);
			hr.setPasswd(hrId);
			HRDAO hrDAO = new HRDAO(hr);
			if(hrDAO.isExistingById()){
				response.sendRedirect(request.getContextPath()+"/admin");
				//response.getWriter().write("<span style='color: red;'>Oops..!! This is an existing HR.</span>");
			}
			else {
				hrDAO.updateHR();
				response.getWriter().write("<span style='color: Green;'>New HR added Successfully.</span>");
			}
		}
	}
	*/
}
