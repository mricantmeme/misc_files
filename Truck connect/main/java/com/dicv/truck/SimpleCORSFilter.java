package com.dicv.truck;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

@Component
public class SimpleCORSFilter implements Filter {

	static final Logger LOGGER = Logger.getLogger(SimpleCORSFilter.class);

	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		HttpServletResponse response = (HttpServletResponse) res;
		HttpServletRequest request = (HttpServletRequest) req;

		String origin = request.getHeader("Origin");
		response.setHeader("Access-Control-Allow-Credentials", "true");
		response.setHeader("Access-Control-Allow-Origin", origin);
		response.setHeader("Access-Control-Allow-Methods", "POST, GET, PUT, OPTIONS, DELETE");
		response.setHeader("Access-Control-Max-Age", "3600");
		response.setHeader("Access-Control-Allow-Headers",
				"Content-Type,X-Requested-With,Auth-Token,Authorization,accept,Origin,Access-Control-Request-Method,Access-Control-Request-Headers,Access-Control-Allow-Credentials");

		if (request.getMethod().equals("OPTIONS")) {
			try {
				// response.getWriter().print("OK");
				response.getWriter().flush();
			} catch (IOException e) {
				LOGGER.log(Level.ERROR, e.getMessage());
			}
		} else {
			chain.doFilter(request, response);
		}
	}

	public void init(FilterConfig filterConfig) {
	}

	public void destroy() {
	}

}
