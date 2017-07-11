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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Servlet Filter implementation class RecruiterAuthenticationFilter
 */
@WebFilter(urlPatterns = {"/rec/*"})
public class RecruiterAuthenticationFilter implements Filter {

	final static Logger LOGGER = LoggerFactory.getLogger(RecruiterAuthenticationFilter.class);

	public void init(FilterConfig fConfig) throws ServletException {
		LOGGER.info("Filter for Recruiter Authentication Initialized.");
	}

	public void destroy() {
		LOGGER.info("Filter for Recruiter Authentication Destroyed.");
	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		
		LOGGER.info("Recruiter.doFilter() called.");
		
		HttpServletRequest req = (HttpServletRequest) request;
		HttpSession session = req.getSession(false);
		HttpServletResponse res = (HttpServletResponse) response;
		if (session != null) {
			
			if (session.getAttribute("recName") == null) {
				session.invalidate();
				System.out.println("DO FILTER FOR REC05");
				request.setAttribute("errorMessage", "Please Login First.");
				req.getRequestDispatcher("/rec/index.jsp").forward(req, res);
			} else {
				res.setHeader("Cache-Control",
						"no-cache, no-store, must-revalidate"); // HTTP 1.1.
				res.setHeader("Pragma", "no-cache"); // HTTP 1.0.
				res.setDateHeader("Expires", 0);
				System.out.println("DO FILTER FOR REC");
				chain.doFilter(request, response);
			}
			
		} else /*if (session == null)*/ {
			System.out.println("DO FILTER FOR REC0");
			request.setAttribute("errorMessage", "Please Login First.");
			req.getRequestDispatcher("/index.jsp").forward(request, response);
		}
		LOGGER.info("Recruiter.doFilter() finished.");
	}
}