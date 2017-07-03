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
@WebFilter(urlPatterns = {"/hr/*"})
public class HRAuthenticationFilter implements Filter {

	final static Logger LOGGER = Logger.getLogger(HRAuthenticationFilter.class);

	public void init(FilterConfig fConfig) throws ServletException {
		LOGGER.info("Filter for HR Authentication Initialized.");
	}

	public void destroy() {
		LOGGER.info("Filter for HR Authentication Destroyed.");
	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpSession session = req.getSession(false);
		HttpServletResponse res = (HttpServletResponse) response;
		if (session != null) {
			
			if (session.getAttribute("hrName") == null) {
				session.invalidate();
				request.setAttribute("errorMessage", "Please Login First.");
				req.getRequestDispatcher("/hr/index.jsp").forward(req, res);
			} else {
				res.setHeader("Cache-Control",
						"no-cache, no-store, must-revalidate"); // HTTP 1.1.
				res.setHeader("Pragma", "no-cache"); // HTTP 1.0.
				res.setDateHeader("Expires", 0);
				chain.doFilter(request, response);
			}
			
		} else if (session == null) {
			request.setAttribute("errorMessage", "Please Login First.");
			req.getRequestDispatcher("/index.jsp").forward(request, response);
		}
	}
}