package com.bookstore.controller.backend;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bookstore.entity.Book;
import com.bookstore.entity.City;
import com.bookstore.entity.Customer;
import com.bookstore.entity.District;
import com.bookstore.service.CityService;
import com.bookstore.service.CommonUtility;
import com.bookstore.service.CustomerService;
import com.bookstore.service.DistrictService;
import com.bookstore.util.EncryptUtil;

/**
 * Servlet implementation class CreateCustomerServlet
 */
@WebServlet("/backend/create_customer")
public class CreateCustomerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateCustomerServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String button = request.getParameter("button");
		
		if("Save".equals(button)) {
			System.out.println("CreateCustomerServlet :Save Button");
			createCustomer(request, response);
		}
		else if("Cancel".equals(button)) {
			System.out.println("CreateCustomerServlet :Cancel Button");
			String page = "list_customers.jsp";	
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

	private void createCustomer(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		CustomerService customerService = new CustomerService();
		CityService cityService = new CityService();
		DistrictService districtService = new DistrictService();
		
		String customerEmail = request.getParameter("customer_email");
		Customer existCustomer = customerService.findByEmail(customerEmail);
		
		if(existCustomer!=null) {
			String message = "This customer email address " + customerEmail + " used by another user. Please write another email address";
			CommonUtility.errorMessage(message, request);
			
			String page = "customer_forum.jsp";
			
			RequestDispatcher dispatcher = request.getRequestDispatcher(page);
			dispatcher.forward(request, response);
		}else {
			
			String customerName = request.getParameter("customer_name");
			String customerSurname = request.getParameter("customer_surname");
			String customerPhoneNumber = request.getParameter("customer_phoneNumber");
			int cityId = Integer.parseInt(request.getParameter("city"));
			int districtId = Integer.parseInt(request.getParameter("district"));
			String customerAddress = request.getParameter("customer_address");
			String customerZipcode = request.getParameter("customer_zipCode");
			String customerPassword = request.getParameter("customer_password");
			String passwordForMd5 = EncryptUtil.md5(customerPassword);
			
			Customer newCustomer = new Customer();
			newCustomer.setName(customerName);
			newCustomer.setSurname(customerSurname);
			newCustomer.setPhoneNumber(customerPhoneNumber);
			newCustomer.setAddress(customerAddress);
			newCustomer.setZipCode(customerZipcode);
			newCustomer.setEmail(customerEmail);
			newCustomer.setPassword(passwordForMd5);
			
			City city = cityService.getCityById(cityId);
			District district = districtService.getDistrictByCity(districtId);
			
			newCustomer.setCity(city);
			newCustomer.setDistrict(district);
			
			customerService.createBook(newCustomer);
			
			// customerlist
			List<Customer> customerList = customerService.listCustomer();
			
			// set attribute for usersList in request
			request.setAttribute("customerList", customerList);
			
			
			// send a message to list_users.jsp
			String message = "New customer created successfully";
			request.setAttribute("message", message);
			
			String page = "list_customers.jsp";
			
			RequestDispatcher dispatcher = request.getRequestDispatcher(page);
			dispatcher.forward(request, response);
			
		}
		
	}
}
