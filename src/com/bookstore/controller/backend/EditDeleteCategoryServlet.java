package com.bookstore.controller.backend;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bookstore.entity.Category;
import com.bookstore.entity.Users;
import com.bookstore.service.BookService;
import com.bookstore.service.CategoryService;
import com.bookstore.service.CommonUtility;
import com.bookstore.service.UserService;

/**
 * Servlet implementation class EditDeleteCategoryServlet
 */
@WebServlet("/backend/edit_delete_category")
public class EditDeleteCategoryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditDeleteCategoryServlet() {
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
		int categoryId = Integer.parseInt(request.getParameter("categoryId"));
		CategoryService categoryService = null;
		
		if ("Edit".equals(button)) {
			
			 categoryService = new CategoryService();
			 Category category = categoryService.getCategoryById(categoryId);
			 
			 /* There's a case in which two or admin/manager users using the website's back-end at the same time. Imagine this scenario:

			- The first manager views the category list.
			
			- The second manager views the category list.
			
			- The first manager deletes the category 'Cartoon' (user ID: 18)
			
			- The second manager tries to edit the category Tom which has been deleted.
			
			In this case, the second manager should see a meaningful error message, saying 'Could not find category with <category_id>'.*/
			 
			 if(category==null) {
				String message = "Category cannot be found with ID " + categoryId;
				CommonUtility.errorMessage(message, request);
			 }else {
				// set attribute for user in request
				 request.setAttribute("category", category);
			 }
			 
			 String page = "category_forum.jsp";
			 RequestDispatcher dispatcher = request.getRequestDispatcher(page);
			 dispatcher.forward(request, response);
			
		}else if("Delete".equals(button)) {
			
			categoryService = new CategoryService();
			Category category = categoryService.getCategoryById(categoryId);
       	 
       	 	if(category == null) {
       		 
       	 		 String message = "Could not find category with ID " + categoryId
    					+ ", or it might have been deleted by another admin";
        		 request.setAttribute("message", message);
        		 
       	 	}else {
       	 		
       	 		 BookService bookService = new BookService();
       	 		 long countBooksByCategory = bookService.countByCategory(categoryId);
       	 		 
       	 		 if(countBooksByCategory >0 ) {
       	 			String message = "Cannot delete the category(ID : %d) because it contains %d books";
       	 			String danger = "danger";
       	 		    message = String.format(message,categoryId,countBooksByCategory);
       	 			request.setAttribute("message", message);
       	 			request.setAttribute("danger", danger);
       	 		 }else {
       	 			categoryService.deleteCategory(categoryId);
       	 			// send a message to list_users.jsp
       	 			String message = "Category with ID "+ categoryId +"deleted successfully";
       	 			request.setAttribute("message", message);
       	 		 }
       	 		   
       	 	}
       	 
	       	List<Category> categoryList = categoryService.listCategory();
	 		
	 		// set attribute for usersList in request
	 		request.setAttribute("categoryList", categoryList);
	 		
	 		String page = "list_categories.jsp";
	 		
	 		RequestDispatcher dispatcher = request.getRequestDispatcher(page);
	 		dispatcher.forward(request, response);
			
		}
	}

}
