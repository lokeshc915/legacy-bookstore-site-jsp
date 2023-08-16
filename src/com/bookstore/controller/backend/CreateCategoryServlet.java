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
import com.bookstore.service.CategoryService;
import com.bookstore.service.CommonUtility;
import com.bookstore.service.UserService;

/**
 * Servlet implementation class CreateCategoryServlet
 */
@WebServlet("/backend/create_category")
public class CreateCategoryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateCategoryServlet() {
        super();
        // TODO Auto-generated constructor stub
    }


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String button = request.getParameter("button");
		
		if("Save".equals(button)) {
			createCategory(request, response);
		}
		else if("Cancel".equals(button)) {
			String page = "list_categories.jsp";	
			response.sendRedirect(page);
		}
	}

	
	private void createCategory(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String name = request.getParameter("category_name");
		
		// UserService
		CategoryService categoryService = new CategoryService();
		
		Category existCategory = categoryService.findByName(name);
		
		if(existCategory!=null) {
			
			String message = "This category name " + name + " already exists. Please write another category name";
			CommonUtility.errorMessage(message, request);
			
			String page = "category_forum.jsp";
			
			RequestDispatcher dispatcher = request.getRequestDispatcher(page);
			dispatcher.forward(request, response);
			
		}else {
			
			Category category = new Category(name);
			
			// UserService
			categoryService.createCategory(category);
			
			// userlist
			List<Category> categoryList = categoryService.listCategory();
			
			// set attribute for usersList in request
			request.setAttribute("categoryList", categoryList);
			
			// send a message to list_users.jsp
			String message = "New category created successfully";
			request.setAttribute("message", message);
			
			String page = "list_categories.jsp";
			
			RequestDispatcher dispatcher = request.getRequestDispatcher(page);
			dispatcher.forward(request, response);
			
		}
	}
	
}
