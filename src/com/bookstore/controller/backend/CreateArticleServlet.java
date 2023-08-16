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
 * Servlet implementation class CreateArticleServlet
 */
@WebServlet("/backend/create_article")
public class CreateArticleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateArticleServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("CreateArticleServlet");
		
		String button = request.getParameter("button");
		
		if("Save".equals(button)) {
			createArticle(request, response);
		}
		else if("Cancel".equals(button)) {
			String page = "list_categories.jsp";	
			response.sendRedirect(page);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	private void createArticle(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("CreateArticleServlet | createArticle is working ");
		
		String name = request.getParameter("article_name");
		
		// UserService
		ArticleService articleService = new ArticleService();
		
		Article existArticle = articleService.findByName(name);
		
		if(existArticle!=null) {
			
			String message = "This article name " + name + " already exists. Please write another article name";
			CommonUtility.errorMessage(message, request);
			
			String page = "article_forum.jsp";
			
			RequestDispatcher dispatcher = request.getRequestDispatcher(page);
			dispatcher.forward(request, response);
			
		}else {
			
			Article article = new Article(name);
			
			// UserService
			articleService.createArticle(article);
			
			// userlist
			List<Article> articleList = articleService.listArticle();
			
			// set attribute for usersList in request
			request.setAttribute("articleList", articleList);
			
			// send a message to list_users.jsp
			String message = "New article created successfully";
			request.setAttribute("message", message);
			
			String page = "list_articles.jsp";
			
			RequestDispatcher dispatcher = request.getRequestDispatcher(page);
			dispatcher.forward(request, response);
			
		}
		
		
	}

}
