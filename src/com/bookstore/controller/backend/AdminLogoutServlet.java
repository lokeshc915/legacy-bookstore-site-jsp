package com.bookstore.controller.backend;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class AdminLogoutServlet
 */
@WebServlet("/backend/logout")
public class AdminLogoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminLogoutServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("AdminLogoutServlet");
		HttpSession httpSession = request.getSession();
	    httpSession.removeAttribute("existUser");
	    httpSession.removeAttribute("loginName");
		
		Cookie cEmail = new Cookie("cookemailCustomer", null);
	    Cookie cPassword = new Cookie("cookpasswordCustomer", null);
	    Cookie cRemember = new Cookie("cookremCustomer", null);
	    cEmail.setMaxAge(0);
	    cPassword.setMaxAge(0);
	    cRemember.setMaxAge(0);
	    
	    cRemember.setPath(request.getContextPath()); 
        cEmail.setPath(request.getContextPath());
        cPassword.setPath(request.getContextPath());
	    
	    response.addCookie(cEmail);
	    response.addCookie(cPassword);
	    response.addCookie(cRemember);
	    
	    
	    /*String page = "index.jsp";
	    RequestDispatcher requestDispatcher = request.getRequestDispatcher(page);
	    requestDispatcher.forward(request, response);*/
	    
	    String page = request.getContextPath() + "/backend/login.jsp";
	    response.sendRedirect(page);
	}

}
