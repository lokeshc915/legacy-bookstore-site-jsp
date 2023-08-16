package com.bookstore.controller.frontend;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bookstore.entity.Category;
import com.bookstore.entity.Customer;
import com.bookstore.service.CategoryService;
import com.bookstore.service.CustomerService;

/**
 * Servlet implementation class CustomerLoginServlet
 */
@WebServlet("/login")
public class CustomerLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CustomerLoginServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("CustomerLoginServlet : doGet");
		String page = "frontend/login.jsp";
		RequestDispatcher requestDispatcher = request.getRequestDispatcher(page);
		requestDispatcher.forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("CustomerLoginServlet : doPost");
		
		CustomerService customerService = new CustomerService();

		String email = request.getParameter("email");
		String password = request.getParameter("password");

		boolean success = customerService.checkUserLogin(email, password);
		if (success) {
			if (request.getParameter("remember") != null) {
				String remember = request.getParameter("remember");
				System.out.println("remember : " + remember);
				Cookie cEmail = new Cookie("cookemailCustomer", email.trim());
				Cookie cPassword = new Cookie("cookpasswordCustomer", password.trim());
				Cookie cRemember = new Cookie("cookremCustomer", remember.trim());
				cEmail.setMaxAge(60 * 60 * 24 * 15);// 15 days
				cPassword.setMaxAge(60 * 60 * 24 * 15); // 15 days
				cRemember.setMaxAge(60 * 60 * 24 * 15); // 15 days

				cRemember.setPath(request.getContextPath()); // set path
				cEmail.setPath(request.getContextPath());
				cPassword.setPath(request.getContextPath());

				response.addCookie(cEmail);
				response.addCookie(cPassword);
				response.addCookie(cRemember);

			}

			Customer existCustomer = customerService.checkUserLoginwithEmailandPassword(email, password);
			existCustomer = new Customer(existCustomer.getId(), 
					                     existCustomer.getName(), 
					                     existCustomer.getSurname(), 
					                     existCustomer.getEmail(),
					                     existCustomer.getAddress(),
					                     existCustomer.getPhoneNumber(),
					                     existCustomer.getZipCode(),
					                     existCustomer.getPassword(),
					                     existCustomer.getRegisterDate(),
					                     existCustomer.getReview(),
					                     existCustomer.getOrders(),
					                     existCustomer.getCity(),
					                     existCustomer.getDistrict());
			
			HttpSession httpSession = request.getSession();
			httpSession.setAttribute("loggedCustomer", existCustomer);
			
			Object objRedirectURL = httpSession.getAttribute("reDirectURL");
			System.out.println("CustomerLoginServlet (objRedirectURL) : " + objRedirectURL);
			
			if(objRedirectURL != null) {
				String redirectURL = (String) objRedirectURL;
				System.out.println("CustomerLoginServlet (redirectURL) : " + redirectURL);
				httpSession.removeAttribute("reDirectURL");
				if(redirectURL.contains("/write_review")) {
					System.out.println("CustomerLoginServlet (/write_review) : " + redirectURL);
					ViewBookServlet.getViewBookById(request, response);
				}else {
					response.sendRedirect(redirectURL);
				}
				
			}else {
				System.out.println("CustomerLoginServlet (Open Index Page)");
				String page = "frontend/index.jsp";
				RequestDispatcher requestDispatcher = request.getRequestDispatcher(page);
				requestDispatcher.forward(request, response);
			}

		} else {
			String message = "Kullanýcý Adý veya Parola Hatalý";
			request.setAttribute("message", message);

			String page = "frontend/login.jsp";
			RequestDispatcher requestDispatcher = request.getRequestDispatcher(page);
			requestDispatcher.forward(request, response);

		}
		
	}

}
