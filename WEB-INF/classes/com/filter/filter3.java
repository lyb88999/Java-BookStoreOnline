package com.filter;

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

/**
 * Servlet Filter implementation class filter3
 */
@WebFilter("/")
public class filter3 implements Filter {
	private String str="loginservlet";
    /**
     * Default constructor. 
     */
    public filter3() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		// place your code here

		// pass the request along the filter chain
		HttpServletRequest r1=(HttpServletRequest)request;
		HttpServletResponse r2=(HttpServletResponse )response;
		if(r1.getSession().getAttribute("user")!=null) {
			//if(r1.getSession().getAttribute("role").equals(1)||r1.getSession().getAttribute("role").equals(2)) 
				chain.doFilter(request, response);
		}
		else if(r1.getRequestURI().contains(str)||r1.getRequestURI().contains("register.jsp")||r1.getRequestURI().contains("registerservlet")
				||r1.getRequestURI().endsWith("css")||r1.getRequestURI().endsWith("png")||r1.getRequestURI().endsWith("js")||r1.getRequestURI().endsWith("jpg")){
			chain.doFilter(request, response);
		}
		else {
			r2.sendRedirect("/webwork/loginservlet");			
		}
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
