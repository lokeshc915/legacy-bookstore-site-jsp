package com.bookstore.controller.frontend;

import java.io.IOException;
import java.util.Arrays;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bookstore.entity.Customer;
import com.bookstore.service.CustomerService;

/**
 * Servlet Filter implementation class CustomerLoginFilter
 */
@WebFilter("/*")
public class CustomerLoginFilter implements Filter {
	
	private static final String[] loginRequiredURLs = {
			"/edit_customer_profile", "/update_customer_profile", "/write_review",
			"/place_order", "/view_orders", "/show_order_detail", "/checkout" , "/checkout_process"
	};
	
    /**
     * Default constructor. 
     */
    public CustomerLoginFilter() {
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
		

		System.out.println("CustomerLoginFilter is invoked..");

		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		HttpSession session = httpRequest.getSession(false);
		
		String path = httpRequest.getRequestURI().substring(httpRequest.getContextPath().length());
		System.out.println("CustomerLoginFilter (path) : " + path);
		
		if(path.startsWith("/backend/")) {
			System.out.println("CustomerLoginFilter(backend) : " + path);
			chain.doFilter(request, response);
			return;
		}

		boolean isLoggedIn = (session != null && session.getAttribute("loggedCustomer") != null);
		System.out.println("CustomerLoginFilter (isLoggedIn) : " + isLoggedIn);
		
		String requestURL = httpRequest.getRequestURL().toString();
		
		System.out.println("Path: " + path);
		System.out.println("loggedIn: " + isLoggedIn);

		Cookie[] cookies = httpRequest.getCookies();
		
		if (!isLoggedIn && isLoginRequired(requestURL)) {
			System.out.println("CustomerLoginFilter 1. if block -> working");
			
			String queryString = httpRequest.getQueryString();
			System.out.println("CustomerLoginFilter 1. if block (queryString) : " + queryString);
			
			String redirectURL = requestURL;
			
			if(queryString != null) {
				redirectURL = redirectURL.concat("?").concat(queryString);
				System.out.println("CustomerLoginFilter 1. if block (requestURL != null -> reDirectURL) : " + redirectURL);
			}
			
			// Redirect Customer to relevant page after Login Process (CustomerLoginServlet from 103 to 116) 
			session.setAttribute("reDirectURL", redirectURL);
			
			
			String page ="frontend/login.jsp";
			RequestDispatcher dispatcher = request.getRequestDispatcher(page);
			dispatcher.forward(request, response);
			
		} else if (!isLoggedIn && cookies != null && ( Arrays.asList(cookies).contains("cookemailCustomer") && 
				Arrays.asList(cookies).contains("cookpasswordCustomer") && Arrays.asList(cookies).contains("cookremCustomer") )) {

			System.out.println("CustomerLoginFilter 2. if block -> Reading values from Cookie");
			String email = "", password = "", rememberVal = "";
			if (cookies != null) {
				for (Cookie cookie : cookies) {
					if ("cookemailCustomer".equals(cookie.getName())) {
						email = cookie.getValue();
					}
					if ("cookpasswordCustomer".equals(cookie.getName())) {
						password = cookie.getValue();
					}
					if ("cookremCustomer".equals(cookie.getName())) {
						rememberVal = cookie.getValue();
					}
				}
			}

			System.out.println("cookemailCustomer :" + email);
			System.out.println("cookpasswordCustomer :" + password);
			System.out.println("cookremCustomer :" + rememberVal);

			if (email != "" && password != "" && rememberVal != "") {

				CustomerService customerService = new CustomerService();
				Customer existCustomer = customerService.checkUserLoginwithEmailandPassword(email, password);
				existCustomer = new Customer(existCustomer.getId(), 
						                     existCustomer.getName(), 
						                     existCustomer.getSurname(), 
						                     existCustomer.getEmail(),
						                     existCustomer.getAddress(),
						                     existCustomer.getPhoneNumber(),
						                     existCustomer.getZipCode(),
						                     existCustomer.getPassword(),
						                     existCustomer.getRegisterDate(),
						                     existCustomer.getReview(),
						                     existCustomer.getOrders(),
						                     existCustomer.getCity(),
						                     existCustomer.getDistrict());
				session = httpRequest.getSession();
				session.setAttribute("loggedCustomer", existCustomer);
				isLoggedIn = true;

				Cookie cEmail = new Cookie("cookemailCustomer", email.trim());
				Cookie cPassword = new Cookie("cookpasswordCustomer", password.trim());
				Cookie cRemember = new Cookie("cookremCustomer", rememberVal.trim());
				cEmail.setMaxAge(60 * 60 * 24 * 15);// 15 days
				cPassword.setMaxAge(60 * 60 * 24 * 15); // 15 days
				cRemember.setMaxAge(60 * 60 * 24 * 15); // 15 days

				
				cRemember.setPath(httpRequest.getContextPath()); // set path
				cEmail.setPath(httpRequest.getContextPath());
				cPassword.setPath(httpRequest.getContextPath());

				httpResponse.addCookie(cEmail);
				httpResponse.addCookie(cPassword);
				httpResponse.addCookie(cRemember);

				RequestDispatcher dispatcher = request.getRequestDispatcher("frontend/index.jsp");
				dispatcher.forward(request, response);

			}

		} else {
			chain.doFilter(request, response);
		}
		
	}
	
	private boolean isLoginRequired(String requestURL) {
		for (String loginRequiredURL : loginRequiredURLs) {
			if (requestURL.contains(loginRequiredURL)) {
				return true;
			}
		}
		
		return false;
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
