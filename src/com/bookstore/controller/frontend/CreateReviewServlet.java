package com.bookstore.controller.frontend;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bookstore.dao.BookDao;
import com.bookstore.entity.Book;
import com.bookstore.entity.Customer;
import com.bookstore.entity.Review;
import com.bookstore.service.CommonUtility;
import com.bookstore.service.ReviewService;

/**
 * Servlet implementation class CreateReviewServlet
 */
@WebServlet("/write_review")
public class CreateReviewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateReviewServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("CreateReviewServlet");
		
		ReviewService reviewService = new ReviewService();
		BookDao bookDao = new BookDao();
		
		int bookId = Integer.parseInt(request.getParameter("bookId"));
		int rating = Integer.parseInt(request.getParameter("rating"));
		String headline = request.getParameter("comment_headline");
		String comment = request.getParameter("comment_area");
		
		Review newReview = new Review();
		newReview.setHeadline(headline);
		newReview.setRating(rating);
		newReview.setReviewComment(comment);
		
		Book bookReview = new Book();
		bookReview.setId(bookId);
		newReview.setBookReview(bookReview);
		
		Book book = bookDao.get(bookId);
		request.setAttribute("book", book);
		
		Customer customerReview = (Customer) request.getSession().getAttribute("loggedCustomer");
		newReview.setCustomerReview(customerReview);
		try {
			reviewService.createReview(newReview);
			String message = "Yorum Yapýldý...";
			CommonUtility.successMessage(message, request);
		} catch (Exception e) {
			String message = "Yorum Yapýlmadý";
			CommonUtility.errorMessage(message, request);
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

}
