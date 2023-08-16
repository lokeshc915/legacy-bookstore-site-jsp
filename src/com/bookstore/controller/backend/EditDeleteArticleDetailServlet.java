package com.bookstore.controller.backend;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bookstore.entity.DetailArticle;
import com.bookstore.entity.Review;
import com.bookstore.service.CommonUtility;
import com.bookstore.service.DetailArticleService;
import com.bookstore.service.ReviewService;

/**
 * Servlet implementation class EditDeleteReviewServlet
 */
@WebServlet("/backend/edit_delete_article_detail")
public class EditDeleteArticleDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditDeleteArticleDetailServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("EditDeleteArticleDetailServlet");
		String button = request.getParameter("button");
		int detailArticleId = Integer.parseInt(request.getParameter("detailArticleId"));
		
		if ("Edit".equals(button)) {
			System.out.println("EditDeleteArticleDetailServlet | Edit button pressed");
			editArticleDetail(request, response,detailArticleId);
		}else if("Delete".equals(button)) {
			System.out.println("EditDeleteArticleDetailServlet | Delete button pressed");
			deleteArticleDetail(request, response,detailArticleId);
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	private void editArticleDetail(HttpServletRequest request, HttpServletResponse response,int detailArticleId) throws ServletException, IOException {
		System.out.println("EditDeleteArticleDetailServlet | editArticleDetail");
		
		DetailArticleService detailArticleService = new DetailArticleService();
		DetailArticle articleDetail = detailArticleService.getDetailArticle(detailArticleId);
		
		 
		 /* There's a case in which two or admin/manager users managing customer detailArticle at the same time. Imagine this scenario:

		- The first manager views the article detail list.
		
		- The second manager views the article detail list.
		
		- The first manager deletes the article detail ID 33.
		
		- The second manager tries to edit the article detail ID 33 which has already been deleted.
		
		In this case, the second manager should see a meaningful error message.*/
		 
		 if(articleDetail==null) {
			String message = "Article Detail cannot be found with ID " + articleDetail;
			CommonUtility.errorMessage(message, request);
						
			
		 }else {
			// set attribute for user in request
			 request.setAttribute("articleDetail", articleDetail);
		 }
		 
		 String page = "article_detail_forum.jsp";
		 RequestDispatcher dispatcher = request.getRequestDispatcher(page);
		 dispatcher.forward(request, response);
	}
	
	private void deleteArticleDetail(HttpServletRequest request, HttpServletResponse response,int detailArticleId) throws ServletException, IOException {
		
		System.out.println("EditDeleteArticleDetailServlet | deleteArticleDetail");
		
		DetailArticleService detailArticleService = new DetailArticleService();
		DetailArticle articleDetail = detailArticleService.getDetailArticle(detailArticleId);
		
		
		
		/*Imagine this scenario:

		- The first manager views the article detail list.
		
		- The second manager views the article detail list.
		
		- The first manager deletes the article detail ID 33.
		
		- The second manager tries to edit the article detail ID 33 which has already been deleted.
		
		In this case, the second manager should see a meaningful error message.
			*/
		if(articleDetail == null) {
       		 
 	 		 String message = "Could not find review with ID " + detailArticleId
					+ ", or it might have been deleted by another admin";
 	 		CommonUtility.errorMessage(message, request);
  		 
 	 	}else {
 	 		
 	 		detailArticleService.deleteDetailArticle(detailArticleId);
       		// send a message to list_users.jsp
       		String message = "Article Detail with ID "+ detailArticleId +" deleted successfully";
       		CommonUtility.successMessage(message, request);
 	 		
 	 	}
		
		
		List<DetailArticle> detailArticleList = detailArticleService.listDetailArticle();
		
		
		// set attribute for usersList in request
		request.setAttribute("detailArticleList", detailArticleList);
		
		String page = "list_article_details.jsp";
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(page);
		dispatcher.forward(request, response);
	}

}
