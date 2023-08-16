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

/**
 * Servlet implementation class UpdateCategoryServlet
 */
@WebServlet("/backend/update_article")
public class UpdateArticleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateArticleServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("UpdateArticleServlet");
		String button = request.getParameter("button");
		int articleId = Integer.parseInt(request.getParameter("articleId"));
		if("Update".equals(button)) {
			updateArticle(request, response,articleId);
		}
	}

	private void updateArticle(HttpServletRequest request, HttpServletResponse response, int articleId) throws ServletException, IOException {
		
		System.out.println("UpdateArticleServlet | updateArticle");
		
		// get values from article_name in form
		String name = request.getParameter("article_name");
		
		// ArticleService
		ArticleService articleService = new ArticleService();
		
		Article articleById = articleService.getArticleById(articleId);
		Article articleByName = articleService.findByName(name);
				
		
		// Exist other Article
		if(articleByName != null && articleById.getId() != articleByName.getId()) {
			String message = "Article cannot updated successfully.Article with name " + name + " already exists.";
			CommonUtility.errorMessage(message, request);
			
			String page = "article_forum.jsp";	
			RequestDispatcher dispatcher = request.getRequestDispatcher(page);
			dispatcher.forward(request, response);
			
		}else {
			
			articleById.setName(name);
			
			// ArticleService
			articleService.updateArticle(articleById);
			
			// articlelist
			List<Article> articleList = articleService.listArticle();
			
			// set attribute for usersList in request
			request.setAttribute("articleList", articleList);
			
			// send a message to list_users.jsp
			String message = "Article updated successfully";
			request.setAttribute("message", message);
			
			String page = "list_articles.jsp";
			
			RequestDispatcher dispatcher = request.getRequestDispatcher(page);
			dispatcher.forward(request, response);
			
		}
			
			
	}

}
