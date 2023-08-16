package com.bookstore.controller.frontend;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bookstore.entity.Article;
import com.bookstore.entity.Book;
import com.bookstore.entity.DetailArticle;
import com.bookstore.service.ArticleService;
import com.bookstore.service.BookService;
import com.bookstore.service.DetailArticleService;

/**
 * Servlet implementation class HomeServlet
 */
@WebServlet("")
public class HomeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HomeServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("HomeServlet");
		
		//CategoryService categoryService = new CategoryService();
		//BookService bookService = new BookService();
		
		//List<Category> categoryList = categoryService.listCategory();
		//List<Book> newBookList = bookService.listNewBooks();
		
		
		// set attribute for usersList in request
		//request.setAttribute("categoryList", categoryList);
		//request.setAttribute("newBookList", newBookList);
		
		BookService bookService = new BookService();
		List<Book> listBestSellingBooks = bookService.listBestSellingBooks();
		List<Book> listMostFavoredBooks = bookService.listMostFavoredBooks();
		
		request.setAttribute("listBestSellingBooks", listBestSellingBooks);
		request.setAttribute("listMostFavoredBooks", listMostFavoredBooks);
		
		String page = "frontend/index.jsp";
		RequestDispatcher dispatcher = request.getRequestDispatcher(page);
		dispatcher.forward(request, response);
	}

}
