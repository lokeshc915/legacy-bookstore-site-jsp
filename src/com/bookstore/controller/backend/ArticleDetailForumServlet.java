package com.bookstore.controller.backend;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bookstore.entity.Article;
import com.bookstore.service.DetailArticleService;

/**
 * Servlet implementation class ArticleDetailForumServlet
 */
@WebServlet("/backend/article_detail_forum")
public class ArticleDetailForumServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ArticleDetailForumServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("ArticleDetailForumServlet");
		
		DetailArticleService detailArticleService = new DetailArticleService();
		
		List<Article> articleList = detailArticleService.listArticle();
		
		// set attribute for usersList in request for RequestDispatcher
		//request.setAttribute("categoryList", categoryList);
		
		System.out.println("ArticleSize size : " + articleList.size());
		
		/*String page = "book_forum.jsp";
		RequestDispatcher dispatcher = request.getRequestDispatcher(page);
		dispatcher.forward(request, response);*/
		
		//if request is not from HttpServletRequest, you should do a typecast before
		HttpSession session = request.getSession();
		session.setAttribute("articleList", articleList);
		String page = request.getContextPath() + "/backend/article_detail_forum.jsp";
	    response.sendRedirect(page);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
