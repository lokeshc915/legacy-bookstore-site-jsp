package com.bookstore.controller.backend;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bookstore.entity.Users;
import com.bookstore.service.CommonUtility;
import com.bookstore.service.UserService;
import com.bookstore.util.EncryptUtil;

/**
 * Servlet implementation class UpdateUsersServlet
 */
@WebServlet("/backend/update_user")
public class UpdateUsersServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateUsersServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String button = request.getParameter("button");
		int userId = Integer.parseInt(request.getParameter("userId"));
		if("Update".equals(button)) {
			updateUser(request, response,userId);
		}
	}

	
	private void updateUser(HttpServletRequest request, HttpServletResponse response, int userId) throws ServletException, IOException {
		
		// get values from forum first_name,last_name,email,password
		String name = request.getParameter("first_name");
		String surname = request.getParameter("last_name");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		
		// UserService
		UserService userService = new UserService();
		
		
		Users userById = userService.getUsersById(userId);
		Users userByEmail = userService.findByEmail(email);
		
		
		// Exist other Email Address
		if(userByEmail != null && userById.getId() != userByEmail.getId()) {
			String message = "User cannot updated successfully.User with email " + email + "already exists.";
			CommonUtility.errorMessage(message, request);
			
			String page = "user_forum.jsp";	
			RequestDispatcher dispatcher = request.getRequestDispatcher(page);
			dispatcher.forward(request, response);
			
		}else {
			String passwordForMd5 = EncryptUtil.md5(password);
			Users user = new Users(userId,name, surname, email, passwordForMd5);
			
			// UserService
			userService.updateUser(user);
			
			// userlist
			List<Users> usersList = userService.listUser();
			
			// set attribute for usersList in request
			request.setAttribute("usersList", usersList);
			
			// send a message to list_users.jsp
			String message = "User updated successfully";
			request.setAttribute("message", message);
			
			String page = "list_users.jsp";
			
			RequestDispatcher dispatcher = request.getRequestDispatcher(page);
			dispatcher.forward(request, response);
			
		}
			
			
	}
}
