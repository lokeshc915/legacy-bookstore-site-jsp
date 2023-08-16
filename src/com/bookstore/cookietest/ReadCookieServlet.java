package com.bookstore.cookietest;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/read_cookie")
public class ReadCookieServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ReadCookieServlet() {
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		Cookie[] cookies = request.getCookies();
		String email = null;
		String password = null;
		String rememberVal = null;
		
		PrintWriter writer = response.getWriter();
		
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
			
			writer.println("cookemail :" + email + "; ");
			writer.println("cookpassword :" + password + "; ");
			writer.println("cookrem :" + rememberVal + "; ");			
		} else {
			writer.println("No cookies found");
		}

	}

}
