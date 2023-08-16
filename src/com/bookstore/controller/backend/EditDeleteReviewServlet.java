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
 * Servlet implementation class EditDeleteReviewServlet
 */
@WebServlet("/backend/edit_delete_review")
public class EditDeleteReviewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditDeleteReviewServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("EditDeleteReviewServlet");
		String button = request.getParameter("button");
		int reviewId = Integer.parseInt(request.getParameter("reviewId"));
		ReviewService reviewService = null;
		
		if ("Edit".equals(button)) {
			
			reviewService = new ReviewService();
			Review editReview = reviewService.getReviewById(reviewId);
			 
			 /* There's a case in which two or admin/manager users managing customer reviews at the same time. Imagine this scenario:

			- The first manager views the review list.
			
			- The second manager views the review list.
			
			- The first manager deletes the review ID 33.
			
			- The second manager tries to edit the review ID 33 which has already been deleted.
			
			In this case, the second manager should see a meaningful error message.*/
			 
			 if(editReview==null) {
				String message = "Review cannot be found with ID " + reviewId;
				CommonUtility.errorMessage(message, request);
				
				
				/*
				List<Review> reviewList = reviewService.listReview();
		 		
		 		// set attribute for usersList in request
		 		request.setAttribute("reviewList", reviewList);
		 		
		 		String page = "list_reviews.jsp";
		 		
		 		RequestDispatcher dispatcher = request.getRequestDispatcher(page);
		 		dispatcher.forward(request, response);
				*/
				
			 }else {
				// set attribute for user in request
				 request.setAttribute("review", editReview);
			 }
			 
			 String page = "review_forum.jsp";
			 RequestDispatcher dispatcher = request.getRequestDispatcher(page);
			 dispatcher.forward(request, response);
			
			 
		}else if("Delete".equals(button)) {
			
			reviewService = new ReviewService();
			Review deleteReview = reviewService.getReviewById(reviewId);
			
			
			
			/*Imagine this scenario:

			- The first manager views the review list.
			
			- The second manager views the review list.
			
			- The first manager deletes the review ID 33.
			
			- The second manager tries to edit the review ID 33 which has already been deleted.
			
			In this case, the second manager should see a meaningful error message.
				*/
			if(deleteReview == null) {
	       		 
     	 		 String message = "Could not find review with ID " + reviewId
  					+ ", or it might have been deleted by another admin";
     	 		CommonUtility.errorMessage(message, request);
      		 
     	 	}else {
     	 		
     	 		reviewService.deleteReview(reviewId);
	       		// send a message to list_users.jsp
	       		String message = "Customer with ID "+ reviewId +" deleted successfully";
	       		CommonUtility.successMessage(message, request);
     	 		
     	 	}
			
			
			List<Review> reviewList = reviewService.listReview();
	 		
	 		// set attribute for usersList in request
	 		request.setAttribute("reviewList", reviewList);
	 		
	 		String page = "list_reviews.jsp";
	 		
	 		RequestDispatcher dispatcher = request.getRequestDispatcher(page);
	 		dispatcher.forward(request, response);
		}
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
