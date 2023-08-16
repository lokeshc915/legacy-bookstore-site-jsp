package com.bookstore.controller.backend;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bookstore.entity.Article;
import com.bookstore.service.ArticleService;
import com.bookstore.service.CommonUtility;
import com.bookstore.service.DetailArticleService;

/**
 * Servlet implementation class EditDeleteCategoryServlet
 */
@WebServlet("/backend/edit_delete_article")
public class EditDeleteArticleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditDeleteArticleServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("EditDeleteArticleServlet");
		String button = request.getParameter("button");
		int articleId = Integer.parseInt(request.getParameter("articleId"));
		ArticleService articleService = null;
		
		if ("Edit".equals(button)) {
			
			 articleService = new ArticleService();
			 Article article = articleService.getArticleById(articleId);
			 
			 /* There's a case in which two or admin/manager users using the website's back-end at the same time. Imagine this scenario:

			- The first manager views the article list.
			
			- The second manager views the article list.
			
			- The first manager deletes the article 'About Us' (article ID: 3)
			
			- The second manager tries to edit the article Tom which has been deleted.
			
			In this case, the second manager should see a meaningful error message, saying 'Could not find article with <article_id>'.*/
			 
			 if(article==null) {
				String message = "Article cannot be found with ID " + articleId;
				CommonUtility.errorMessage(message, request);
			 }else {
				// set attribute for user in request
				 request.setAttribute("article", article);
			 }
			 
			 String page = "article_forum.jsp";
			 RequestDispatcher dispatcher = request.getRequestDispatcher(page);
			 dispatcher.forward(request, response);
			
		}else if("Delete".equals(button)) {
			
			articleService = new ArticleService();
			Article article = articleService.getArticleById(articleId);
       	 
       	 	if(article == null) {
       		 
       	 		 String message = "Could not find article with ID " + articleId
    					+ ", or it might have been deleted by another admin";
        		 request.setAttribute("message", message);
        		 
       	 	}else {
       	 		
       	 		 DetailArticleService detailArticleService = new DetailArticleService();
       	 		 long countDetailArticlesByArticle = detailArticleService.countByArticle(articleId);
       	 		 
       	 		if(countDetailArticlesByArticle >0 ) {
       	 			String message = "Cannot delete the category(ID : %d) because it contains %d books";
       	 			String danger = "danger";
       	 		    message = String.format(message,articleId,countDetailArticlesByArticle);
       	 			request.setAttribute("message", message);
       	 			request.setAttribute("danger", danger);
       	 		 }else {
       	 			articleService.deleteArticle(articleId);
       	 			// send a message to list_users.jsp
       	 			String message = "Article with ID "+ articleId +"deleted successfully";
       	 			request.setAttribute("message", message);
       	 		 }
       	 		
       	 		   
       	 	}
       	 
	       	List<Article> articleList = articleService.listArticle();
	 		
	 		// set attribute for usersList in request
	 		request.setAttribute("articleList", articleList);
	 		
	 		String page = "list_articles.jsp";
	 		
	 		RequestDispatcher dispatcher = request.getRequestDispatcher(page);
	 		dispatcher.forward(request, response);
			
		}
	}

}
