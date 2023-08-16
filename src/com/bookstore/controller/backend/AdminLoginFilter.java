package com.bookstore.controller.backend;

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

import com.bookstore.entity.Users;
import com.bookstore.service.UserService;

/**
 * Servlet Filter implementation class AdminLoginFilter
 */
@WebFilter("/backend/*")
public class AdminLoginFilter implements Filter {

	/**
	 * Default constructor.
	 */
	public AdminLoginFilter() {
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
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		// place your code here
		System.out.println("AdminLoginFilter is invoked..");

		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		HttpSession session = httpRequest.getSession(false);

		boolean isLoggedIn = (session != null && session.getAttribute("loginName") != null);

		Cookie[] cookies = httpRequest.getCookies();

		String loginURI = httpRequest.getContextPath() + "/backend/login";

		boolean isLoginRequest = httpRequest.getRequestURI().equals(loginURI);

		boolean isLoginPage = httpRequest.getRequestURI().endsWith("login.jsp");

		if (!isLoggedIn && cookies != null && ( Arrays.asList(cookies).contains("cookemail") && 
				Arrays.asList(cookies).contains("cookpassword") && Arrays.asList(cookies).contains("cookrem") )) {

			System.out.println("AdminLoginFilter 1. if block -> Reading values from Cookie");
			String email = "", password = "", rememberVal = "";
			if (cookies != null) {
				for (Cookie cookie : cookies) {
					if ("cookemail".equals(cookie.getName())) {
						email = cookie.getValue();
					}
					if ("cookpassword".equals(cookie.getName())) {
						password = cookie.getValue();
					}
					if ("cookrem".equals(cookie.getName())) {
						rememberVal = cookie.getValue();
					}
				}
			}

			System.out.println("cookemail :" + email);
			System.out.println("cookpassword :" + password);
			System.out.println("cookrem :" + rememberVal);

			if (email != "" && password != "" && rememberVal != "") {

				UserService userService = new UserService();
				Users existUser = userService.checkUserLoginwithEmailandPassword(email, password);
				existUser = new Users(existUser.getName(), existUser.getSurname(), existUser.getEmail(),
						existUser.getPassword());
				session = httpRequest.getSession();
				session.setAttribute("loginName", existUser.getFullName());
				isLoggedIn = true;

				Cookie cEmail = new Cookie("cookemail", email.trim());
				Cookie cPassword = new Cookie("cookpassword", password.trim());
				Cookie cRemember = new Cookie("cookrem", rememberVal.trim());
				cEmail.setMaxAge(60 * 60 * 24 * 15);// 15 days
				cPassword.setMaxAge(60 * 60 * 24 * 15); // 15 days
				cRemember.setMaxAge(60 * 60 * 24 * 15); // 15 days

				
				cRemember.setPath(httpRequest.getContextPath()); // set path
				cEmail.setPath(httpRequest.getContextPath());
				cPassword.setPath(httpRequest.getContextPath());

				httpResponse.addCookie(cEmail);
				httpResponse.addCookie(cPassword);
				httpResponse.addCookie(cRemember);

				RequestDispatcher dispatcher = request.getRequestDispatcher("/backend/");
				dispatcher.forward(request, response);

			}

		} else if (isLoggedIn && (isLoginRequest || isLoginPage)) {
			// the admin is already logged in and he's trying to login again
			// then forwards to the admin's homepage
			System.out.println("AdminLoginFilter 2. if block -> the admin is already logged in and he's trying to login again then forwards to the admin's homepage");
			RequestDispatcher dispatcher = request.getRequestDispatcher("/backend/");
			dispatcher.forward(request, response);

		} else if (isLoggedIn || isLoginRequest) {
			// continues the filter chain
			// allows the request to reach the destination
			System.out.println("AdminLoginFilter 3. if block -> continues the filter chain , allows the request to reach the destination");
			chain.doFilter(request, response);

		} else {
			// the admin is not logged in, so authentication is required
			// forwards to the Login page
			System.out.println("AdminLoginFilter 4. if block -> the admin is not logged in, so authentication is required forwards to the Login page");
			RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
			dispatcher.forward(request, response);

		}

	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
