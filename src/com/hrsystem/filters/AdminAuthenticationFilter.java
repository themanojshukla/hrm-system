package com.hrsystem.filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

/**
 * Servlet Filter implementation class AdminAuthenticationFilter
 */
@WebFilter(servletNames = {"admin"}, urlPatterns = {"/admin","/addHr", "/hrList", "/editHr"})
public class AdminAuthenticationFilter implements Filter {

	final static Logger LOGGER = Logger
			.getLogger(AdminAuthenticationFilter.class);

	public void init(FilterConfig fConfig) throws ServletException {
		System.out.println("Filter started");
		LOGGER.info("Filter for Admin Authentication Initialized.");
	}

	public void destroy() {
		LOGGER.info("Filter for Admin Authentication Destroyed.");
	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		System.out.println("doFilter called");
		HttpServletRequest req = (HttpServletRequest) request;
		HttpSession session = req.getSession(false);
		HttpServletResponse res = (HttpServletResponse) response;
		if (session != null) {
			if (session.getAttribute("adminName") == null) {
				System.out.println("Session Not null but adminName not set");
				session.invalidate();
				request.setAttribute("errorMessage", "Please Login First.");
				req.getRequestDispatcher("admin/index.jsp").forward(request,
						response);
			} else {
				System.out.println("Session not null, fwd to admin Name : "
						+ session.getAttribute("adminName"));
				res.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
		        res.setHeader("Pragma", "no-cache"); // HTTP 1.0.
		        res.setDateHeader("Expires", 0);
				chain.doFilter(request, response);
			}
		} else if (session == null) {
			System.out.println("Session null");
			request.setAttribute("errorMessage", "Please Login First.");
			req.getRequestDispatcher("admin/index.jsp").forward(request,
					response);
		}
	}
}