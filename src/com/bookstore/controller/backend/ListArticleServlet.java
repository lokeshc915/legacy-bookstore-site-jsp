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
import com.bookstore.entity.Category;
import com.bookstore.entity.Users;
import com.bookstore.service.ArticleService;
import com.bookstore.service.CategoryService;
import com.bookstore.service.UserService;

/**
 * Servlet implementation class ListCategoryServlet
 */
@WebServlet("/backend/list_articles")
public class ListArticleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ListArticleServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("ListArticleServlet");
		// Get user list from service
		
		ArticleService articleService = new ArticleService();
		List<Article> articleList = articleService.listArticle();
				
		// set attribute for usersList in request
		request.setAttribute("articleList", articleList);
		
		String page = "list_articles.jsp";
		
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
