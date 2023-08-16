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

/**
 * Servlet implementation class EditDeleteUsersServlet
 */
@WebServlet("/backend/edit_delete_user")
public class EditDeleteUsersServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditDeleteUsersServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("EditDeleteUsersServlet");
		String button = request.getParameter("button");
		int userId = Integer.parseInt(request.getParameter("userId"));
		UserService userService = null;
		
		if ("Edit".equals(button)) {
			 userService = new UserService();
			 Users user = userService.getUsersById(userId);
			 
			 /* There's a case in which two or admin/manager users using the website's back-end at the same time. Imagine this scenario:

			- The first manager views the user list.
			
			- The second manager views the user list.
			
			- The first manager deletes the user 'Tom' (user ID: 18)
			
			- The second manager tries to edit the user Tom which has been deleted.
			
			In this case, the second manager should see a meaningful error message, saying 'Could not find user with <user_id>'.*/
			 
			 if(user==null) {
				String message = "User cannot be found with ID " + userId;
				CommonUtility.errorMessage(message, request);
			 }else {
				 
				// set password as null to make the password is left blank by default
				// if left blank, the user's password won't be updated
				// this is to work with the encrypted password feature
				user.setPassword(null);
				 
				// set attribute for user in request
				request.setAttribute("user", user);
			 }
			 
			 String page = "user_forum.jsp";
			 RequestDispatcher dispatcher = request.getRequestDispatcher(page);
			 dispatcher.forward(request, response);
			 
         }else if("Delete".equals(button)) {
        	 
        	 userService = new UserService();
        	 Users user = userService.getUsersById(userId);
        	 
        	 if(user == null) {
        		 
        		 String message = "Could not find user with ID " + userId
     					+ ", or it might have been deleted by another admin";
         		 request.setAttribute("message", message);
         		 
        	 }else if(userId == 1){
        		 
        		 String message = "The default admin user account cannot be deleted.";
          		 request.setAttribute("message", message);
          		 
        	 }else {
        		 userService.deleteUser(userId);
         		 // send a message to list_users.jsp
         		 String message = "User with ID "+ userId +"deleted successfully";
         		 request.setAttribute("message", message);
        	 }
        	 
     		 // userlist
 			 List<Users> usersList = userService.listUser();
 			
 			 // set attribute for usersList in request
 			 request.setAttribute("usersList", usersList);
     		
     		 String page = "list_users.jsp";
     		
     		 RequestDispatcher dispatcher = request.getRequestDispatcher(page);
     		 dispatcher.forward(request, response);
         }
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
	}

}
