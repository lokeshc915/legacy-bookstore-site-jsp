package com.bookstore.controller.frontend;

import java.io.IOException;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

import com.bookstore.dao.CategoryDao;
import com.bookstore.entity.Article;
import com.bookstore.entity.Book;
import com.bookstore.entity.Category;
import com.bookstore.service.ArticleService;
import com.bookstore.service.BookService;
import com.bookstore.service.CategoryService;

/**
 * Servlet Filter implementation class CommonFilter
 */
@WebFilter("/*")
public class CommonFilter implements Filter {

	private final CategoryService categoryService;
	private final BookService bookService;
	
    /**
     * Default constructor. 
     */
    public CommonFilter() {
        // TODO Auto-generated constructor stub
    	categoryService = new CategoryService();
    	bookService = new BookService();
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		// place your code here
		System.out.println("CommonFilter --> dolFilter");
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		//String path = httpRequest.getRequestURI()
		String path = httpRequest.getRequestURI().substring(httpRequest.getContextPath().length());
		System.out.println("CommonFilter path : " + path);
		
		if(path.startsWith("/backend/")) {
			System.out.println("CommonFilter(backend) : " + path);
			
		}else {
			System.out.println("CommonFilter(frontend) : " + path);
			
			List<Category> categoryList = categoryService.listCategory();
			request.setAttribute("categoryList", categoryList);
			System.out.println("CommonFilter(frontend) categorySize:" + categoryList.size());
			
			List<Book> newBookList = bookService.listNewBooks();
			request.setAttribute("newBookList", newBookList);
			System.out.println("CommonFilter(frontend) newBookList:" + newBookList.size());
			
			ArticleService articleService = new ArticleService();
			List<Article> articleList = articleService.listArticle();
			request.setAttribute("articleList", articleList);
			System.out.println("HomeServlet | articleList:" + articleList.size());
		}
		
		// pass the request along the filter chain
		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
