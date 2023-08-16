package com.bookstore.controller.frontend;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bookstore.entity.Book;
import com.bookstore.entity.Category;
import com.bookstore.service.BookService;
import com.bookstore.service.CategoryService;
import com.bookstore.service.CommonUtility;

/**
 * Servlet implementation class ViewBooksByCategoryServlet
 */
@WebServlet("/view_category")
public class ViewBooksByCategoryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ViewBooksByCategoryServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		int categoryId = Integer.parseInt(request.getParameter("id")); // view_category?id=${category.id}
		BookService bookService = new BookService();
		CategoryService categoryService = new CategoryService();
		
		List<Book> bookListByCategory = bookService.getBooksByCategory(categoryId);
		
		Category category = categoryService.getCategoryById(categoryId);
		
		/* Imagine this scenario:

			- A visitor comes to the website and he sees a category 'Cartoon'.

			- An admin/manager deletes the category 'Cartoon'.

			- The visitor clicks the category Cartoon.

			In this case, the category is no longer available, so the website should display a meaningful error message to the visitor.
			
		*/
		if (category == null) {
			
			String message = "Üzgünüz Kitap bulunamadý...";
			CommonUtility.errorMessage(message, request);
			request.getRequestDispatcher("frontend/books_list_by_category.jsp").forward(request, response);
			return;
		}
		
		//List<Category> categoryList = categoryService.listCategory();
		
		request.setAttribute("bookListByCategory", bookListByCategory);
		request.setAttribute("category",category);
		//request.setAttribute("categoryList", categoryList);
		
		String page = "frontend/books_list_by_category.jsp";
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(page);
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
