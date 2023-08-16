package com.bookstore.cookietest;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/create_cookie")
public class CreateCookieServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public CreateCookieServlet() {
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
        Cookie cEmail = new Cookie("cookemail", "namhm@codejava.net");
        Cookie cPassword = new Cookie("cookpassword", "danangcity");
        Cookie cRemember = new Cookie("cookrem", "true");
        cEmail.setMaxAge(60 * 60 * 24 * 15);//15 days
        cPassword.setMaxAge(60 * 60 * 24 * 15); //15 days
        
        response.addCookie(cEmail);
        response.addCookie(cPassword);
        response.addCookie(cRemember);
        
        response.getWriter().println("Cookies have ben set");

	}

}
