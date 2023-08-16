package com.bookstore.controller.frontend;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class CustomerLogoutServlet
 */
@WebServlet("/logout")
public class CustomerLogoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CustomerLogoutServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("CustomerLogoutServlet : doGet");
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("CustomerLogoutServlet : doPost");
		
		HttpSession httpSession = request.getSession();
	    httpSession.removeAttribute("loggedCustomer");
		
		Cookie cEmail = new Cookie("cookemail", null);
	    Cookie cPassword = new Cookie("cookpassword", null);
	    Cookie cRemember = new Cookie("cookrem", null);
	    cEmail.setMaxAge(0);
	    cPassword.setMaxAge(0);
	    cRemember.setMaxAge(0);
	    
	    cRemember.setPath(request.getContextPath()); 
        cEmail.setPath(request.getContextPath());
        cPassword.setPath(request.getContextPath());
	    
	    response.addCookie(cEmail);
	    response.addCookie(cPassword);
	    response.addCookie(cRemember);
	    
	   
	    
	    String page = request.getContextPath();
	    response.sendRedirect(page);
	}

}
