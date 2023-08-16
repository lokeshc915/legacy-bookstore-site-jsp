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

import com.bookstore.entity.Users;
import com.bookstore.service.UserService;

/**
 * Servlet implementation class AdminLoginServlet
 */
@WebServlet("/backend/login")
public class AdminLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminLoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String email = request.getParameter("email");
	    String password = request.getParameter("password");
		
	    UserService userService = new UserService();
	    
	    boolean success = userService.checkUserLogin(email, password);
	    if(success) {
	    	 if (request.getParameter("remember") != null) {
		          String remember = request.getParameter("remember");
		          System.out.println("remember : " + remember);
		          Cookie cEmail = new Cookie("cookemail", email.trim());
		          Cookie cPassword = new Cookie("cookpassword", password.trim());
		          Cookie cRemember = new Cookie("cookrem", remember.trim());
		          cEmail.setMaxAge(60 * 60 * 24 * 15);//15 days
		          cPassword.setMaxAge(60 * 60 * 24 * 15); //15 days
		          cRemember.setMaxAge(60 * 60 * 24 * 15); //15 days
		          
		          
		          cRemember.setPath(request.getContextPath()); //set path
                  cEmail.setPath(request.getContextPath());
                  cPassword.setPath(request.getContextPath());
		          
		          response.addCookie(cEmail);
		          response.addCookie(cPassword);
		          response.addCookie(cRemember);
		           
		     }
	    	 
	    	 Users existUser = userService.checkUserLoginwithEmailandPassword(email, password);
	    	 existUser = new Users(existUser.getId(),existUser.getName(), existUser.getSurname(), existUser.getEmail(), existUser.getPassword());
	    	 HttpSession httpSession = request.getSession();
	         httpSession.setAttribute("loginName", existUser.getFullName());
	         httpSession.setAttribute("existUser",existUser);
	         
	         
	    	 
	         String page = "/backend/";
	         RequestDispatcher requestDispatcher = request.getRequestDispatcher(page);
	         requestDispatcher.forward(request, response);
	    	 
	   }else {
		   String message = "Kullanýcý Adý veya Parola Hatalý";
		   request.setAttribute("message", message);
		   
		   String page = "login.jsp";
		   RequestDispatcher requestDispatcher = request.getRequestDispatcher(page);
		   requestDispatcher.forward(request, response);
		   
		   
		   /*String page = request.getContextPath() + "/backend/login.jsp";
		   response.sendRedirect(page);*/
	   }
	
	}

}
