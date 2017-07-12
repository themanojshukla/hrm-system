package com.hrsystem.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.hrsystem.dao.IHrDao;
import com.hrsystem.init.DaoFactory;
import com.hrsystem.model.HR;

/**
 * Servlet implementation class HRs
 */
@WebServlet(urlPatterns = { "/hrs", "/hrs/*" })

public class HRs extends HttpServlet {

    private static final long serialVersionUID = 2756777938945605334L;

    /**
     * Get list of existing HRs.
     * 
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException {
	IHrDao hrDAO = DaoFactory.getHrDao();
	List<HR> hrList = hrDAO.getHRList();
	request.setAttribute("hrList", hrList);
	request.getRequestDispatcher("/admin/hrList.jsp").forward(request, response);
    }

    /**
     * To add new HRs
     * 
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException {
	HttpSession session = request.getSession(false);
	String name = request.getParameter("hrname");
	String email = request.getParameter("hremail");
	String passwd = request.getParameter("hrpasswd");
	if (name == null || email == null || passwd == null || name.isEmpty() || email.isEmpty() || passwd.isEmpty()) {
	    session.setAttribute("color", "red");
	    session.setAttribute("hrAddMessage", "Please fill the form.");
	    response.sendRedirect(request.getContextPath() + "/admin/");
	} else {
	    HR hr = new HR();
	    hr.setEmail(email);
	    hr.setName(name);
	    hr.setPasswd(passwd);
	    IHrDao hrDAO = DaoFactory.getHrDao();
	    if (hrDAO.isExistingByEmail(email)) {
		session.setAttribute("color", "red");
		session.setAttribute("hrAddMessage", "Oops..!! This is an existing HR.");
		response.sendRedirect(request.getContextPath() + "/admin/");
	    } else {
		hrDAO.addNewHR(hr);
		session.setAttribute("color", "green");
		session.setAttribute("hrAddMessage", "New HR added Successfully.");
		response.sendRedirect(request.getContextPath() + "/admin/");
	    }
	}
    }
}