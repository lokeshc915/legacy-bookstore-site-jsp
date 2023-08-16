package com.bookstore.controller.backend;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bookstore.entity.City;
import com.bookstore.service.CityService;


/**
 * Servlet implementation class CustomerForumServlet
 */
@WebServlet("/backend/customer_forum")
public class CustomerForumServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CustomerForumServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("CustomerForumServlet");
		CityService cityService = new CityService();
		
		
		List<City> cityList = cityService.listCity();
		
		System.out.println("CityList size : " + cityList.size());
		
		/*String page = "book_forum.jsp";
		RequestDispatcher dispatcher = request.getRequestDispatcher(page);
		dispatcher.forward(request, response);*/
		
		//if request is not from HttpServletRequest, you should do a typecast before
		HttpSession session = request.getSession();
		session.setAttribute("cityList", cityList);
		String page = request.getContextPath() + "/backend/customer_forum.jsp";
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
