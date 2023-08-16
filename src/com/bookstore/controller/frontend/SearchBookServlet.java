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

/**
 * Servlet implementation class SearchBookServlet
 */
@WebServlet("/search")
public class SearchBookServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchBookServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String keyword = request.getParameter("keyword");
		keyword = keyword.toLowerCase();
		
		BookService bookService = new BookService();
		//CategoryService categoryService = new CategoryService();
		
		//List<Category> categoryList = categoryService.listCategory();
		
		List<Book> books = null;
		
		if(keyword.equals("")) {
			books = bookService.listBook();
		}else {
			books = bookService.searchBook(keyword);
		}
		
		
		// set attribute for usersList in request
		request.setAttribute("searchBookList", books);
		request.setAttribute("keyword", keyword);
		//request.setAttribute("categoryList", categoryList);
		
		String page = "frontend/search_book.jsp";
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
