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
import com.bookstore.entity.Customer;
import com.bookstore.entity.Review;
import com.bookstore.service.BookService;
import com.bookstore.service.CategoryService;
import com.bookstore.service.CommonUtility;
import com.bookstore.service.ReviewService;

/**
 * Servlet implementation class ViewBookServlet
 */
@WebServlet("/view_book")
public class ViewBookServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private static int bookId;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ViewBookServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("ViewBookServlet");
		bookId = Integer.parseInt(request.getParameter("id")); // view_view?id=${newBook.id} veya view_book?id=${bookByCategory.id}
		BookService bookService = new BookService();
		
		Book book = bookService.getBookById(bookId);
		
		/*Consider this scenario:

			- A visitor comes to the website, and he is viewing books in the category 'Lifestyle'.

			- The visitor sees a book with title '4 hour work week'.

			- An manager deletes that book in the back-end.

			- The visitor clicks on the book '4 hour work week' which has been deleted.

			In this case, the website should display a friendly error message to the visitor.
			
		*/
		if(book!=null) {
			// set attribute for usersList in request
			request.setAttribute("book", book);
		}else {
			String message = "Üzgünüz bu kitap bulunamadý...";
			CommonUtility.errorMessage(message, request);
		}
		
		
		/*
			Check whether loggedCustomer has already defined a review or not 
		*/
		ReviewService reviewService = new ReviewService();
		Customer customerReview = (Customer) request.getSession().getAttribute("loggedCustomer");
		if(customerReview != null) {
			Review existReview = reviewService.findByReviewIdAndBookId(customerReview.getId(), bookId);
			if(existReview!=null) {
				request.setAttribute("existReview", existReview);
			}else {
				request.setAttribute("existReview", null);
			}
		}
		
		String page = "frontend/book_detail.jsp";
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
	
	public static void getViewBookById(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		BookService bookService = new BookService();
		Book book = bookService.getBookById(bookId);
		
		request.setAttribute("book", book);
		
		String page = "frontend/book_detail.jsp";
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(page);
		dispatcher.forward(request, response);
	}

}
