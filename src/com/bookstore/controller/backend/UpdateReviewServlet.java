package com.bookstore.controller.backend;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bookstore.entity.Review;
import com.bookstore.service.CommonUtility;
import com.bookstore.service.ReviewService;

/**
 * Servlet implementation class UpdateReviewServlet
 */
@WebServlet("/backend/update_review")
public class UpdateReviewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateReviewServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String button = request.getParameter("button");
		int reviewId = Integer.parseInt(request.getParameter("reviewId"));
		if("Update".equals(button)) {
			updateReview(request, response,reviewId);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	private void updateReview(HttpServletRequest request, HttpServletResponse response, int reviewId) throws ServletException, IOException {
		
		// get values from forum first_name,last_name,email,password
		String reviewHeadline = request.getParameter("review_headline");
		String reviewComment = request.getParameter("review_comment");
		
		// ReviewService
		ReviewService reviewService = new ReviewService();
		
		
		Review reviewById = reviewService.getReviewById(reviewId);
				
		// Exist other Category
		if(reviewById == null) {
			String message = "Review cannot updated successfully.Review cannot already exist.";
			CommonUtility.errorMessage(message, request);
			
			String page = "review_forum.jsp";	
			RequestDispatcher dispatcher = request.getRequestDispatcher(page);
			dispatcher.forward(request, response);
			
		}else {
			
			reviewById.setHeadline(reviewHeadline);
			reviewById.setReviewComment(reviewComment);
			
			
			reviewService.updateReview(reviewById);
			
			List<Review> reviewList = reviewService.listReview();
			
			// set attribute for usersList in request
			request.setAttribute("reviewList", reviewList);
			
			// send a message to list_users.jsp
			String message = "Review updated successfully";
			CommonUtility.errorMessage(message, request);
			
			String page = "list_reviews.jsp";
			
			RequestDispatcher dispatcher = request.getRequestDispatcher(page);
			dispatcher.forward(request, response);
			
		}
		
	}
}
