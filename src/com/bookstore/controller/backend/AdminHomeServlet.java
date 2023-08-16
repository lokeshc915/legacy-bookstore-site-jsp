package com.bookstore.controller.backend;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bookstore.entity.BookOrders;
import com.bookstore.entity.Review;
import com.bookstore.service.BookOrdersService;
import com.bookstore.service.ReviewService;

/**
 * Servlet implementation class AdminHomeServlet
 */
@WebServlet("/backend/")
public class AdminHomeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminHomeServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(req, resp);
	}



	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("AdminHomeServlet");
		/*String page = "index.jsp";
		RequestDispatcher dispatcher = request.getRequestDispatcher(page);
		dispatcher.forward(request, response);*/
		
		BookOrdersService bookOrdersService = new BookOrdersService();
		ReviewService reviewService = new ReviewService();
		
		List<BookOrders> listMostRecentSales = bookOrdersService.listMostRecentSales();
		List<Review> listMostRecent = reviewService.listMostRecent();
		
		System.out.println("AdminHomeServlet| listMostRecentSales size : " + listMostRecentSales.size());
		System.out.println("AdminHomeServlet| listMostRecent size : " + listMostRecent.size());
		
		request.setAttribute("listMostRecentSales", listMostRecentSales);
		request.setAttribute("listMostRecent", listMostRecent);
			
		/*String page = request.getContextPath() + "/backend/index.jsp";
		response.sendRedirect(page);*/
		
		String page = "index.jsp";
		RequestDispatcher dispatcher = request.getRequestDispatcher(page);
		dispatcher.forward(request, response);
	}

	
}
