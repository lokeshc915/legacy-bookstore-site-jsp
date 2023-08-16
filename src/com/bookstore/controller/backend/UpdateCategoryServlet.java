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
import com.bookstore.service.CategoryService;
import com.bookstore.service.CommonUtility;
import com.bookstore.service.UserService;

/**
 * Servlet implementation class UpdateCategoryServlet
 */
@WebServlet("/backend/update_category")
public class UpdateCategoryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateCategoryServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String button = request.getParameter("button");
		int categoryId = Integer.parseInt(request.getParameter("categoryId"));
		if("Update".equals(button)) {
			updateCategory(request, response,categoryId);
		}
	}

	private void updateCategory(HttpServletRequest request, HttpServletResponse response, int categoryId) throws ServletException, IOException {
		
		// get values from forum first_name,last_name,email,password
		String name = request.getParameter("category_name");
		
		// UserService
		CategoryService categoryService = new CategoryService();
		
		
		Category categoryById = categoryService.getCategoryById(categoryId);
		Category categoryByName = categoryService.findByName(name);
		
		
		// Exist other Category
		if(categoryByName != null && categoryById.getId() != categoryByName.getId()) {
			String message = "Category cannot updated successfully.Category with name " + name + " already exists.";
			CommonUtility.errorMessage(message, request);
			
			String page = "category_forum.jsp";	
			RequestDispatcher dispatcher = request.getRequestDispatcher(page);
			dispatcher.forward(request, response);
			
		}else {
			
			categoryById.setName(name);
			
			// UserService
			categoryService.updateCategory(categoryById);
			
			// userlist
			List<Category> categoryList = categoryService.listCategory();
			
			// set attribute for usersList in request
			request.setAttribute("categoryList", categoryList);
			
			// send a message to list_users.jsp
			String message = "Category updated successfully";
			request.setAttribute("message", message);
			
			String page = "list_categories.jsp";
			
			RequestDispatcher dispatcher = request.getRequestDispatcher(page);
			dispatcher.forward(request, response);
			
		}
			
			
	}

}
