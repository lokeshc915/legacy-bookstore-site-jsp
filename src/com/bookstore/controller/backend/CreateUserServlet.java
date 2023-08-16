package com.bookstore.controller.backend;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.SessionFactory;

import com.bookstore.dao.UserDao;
import com.bookstore.entity.Users;
import com.bookstore.service.CommonUtility;
import com.bookstore.service.UserService;
import com.bookstore.util.EncryptUtil;
import com.bookstore.util.HibernateUtil;

/**
 * Servlet implementation class CreateUserServlet
 */
@WebServlet("/backend/create_user")
public class CreateUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	//static SessionFactory sessionFactory  = null;
	//static UserDao userDao = null;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateUserServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    /*@Override
    public void init() throws ServletException {
    	// TODO Auto-generated method stub
    	sessionFactory  = HibernateUtil.getSessionFactory();
		userDao = new UserDao(sessionFactory);
    }*/

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String button = request.getParameter("button");
		
		if("Save".equals(button)) {
			createUser(request, response);
		}
		else if("Cancel".equals(button)) {
			String page = "list_users.jsp";	
			response.sendRedirect(page);
		}
		
	}

	private void createUser(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// get values from forum first_name,last_name,email,password
		String name = request.getParameter("first_name");
		String surname = request.getParameter("last_name");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		
		// UserService
		UserService userService = new UserService();
		
		Users existUser = userService.findByEmail(email);
		
		if(existUser!=null) {
			
			String message = "This email address " + email + " used by another user. Please write another email address";
			CommonUtility.errorMessage(message, request);
			
			String page = "user_forum.jsp";
			
			RequestDispatcher dispatcher = request.getRequestDispatcher(page);
			dispatcher.forward(request, response);
			
		}else {
			String passwordForMd5 = EncryptUtil.md5(password);
			Users user = new Users(name, surname, email, passwordForMd5);
			
			// UserService
			userService.createUser(user);
			
			// userlist
			List<Users> usersList = userService.listUser();
			
			// set attribute for usersList in request
			request.setAttribute("usersList", usersList);
			
			// send a message to list_users.jsp
			String message = "New user created successfully";
			request.setAttribute("message", message);
			
			String page = "list_users.jsp";
			
			RequestDispatcher dispatcher = request.getRequestDispatcher(page);
			dispatcher.forward(request, response);
			
		}
	}
	
}
