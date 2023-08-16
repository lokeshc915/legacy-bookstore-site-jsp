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
import com.bookstore.service.BookService;
import com.bookstore.service.CategoryService;
import com.bookstore.service.CityService;
import com.bookstore.service.CommonUtility;
import com.bookstore.service.CustomerService;
import com.bookstore.service.DistrictService;
import com.bookstore.util.EncryptUtil;

/**
 * Servlet implementation class UpdateCustomerServlet
 */
@WebServlet("/backend/update_customer")
public class UpdateCustomerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateCustomerServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String button = request.getParameter("button");
		if("Update".equals(button)) {
			updateCustomer(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	private void updateCustomer(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int customerId = Integer.parseInt(request.getParameter("customerId"));
		
		CustomerService customerService = new CustomerService();
		CityService cityService = new CityService();
		DistrictService districtService = new DistrictService();
		
		Customer existCustomer = customerService.getCustomerById(customerId);
		
		String email = request.getParameter("customer_email");
		Customer customerByEmail = customerService.findByEmail(email);
		
		if(customerByEmail != null && existCustomer.getId() != customerByEmail.getId()) {
			
			String message = "Customer cannot updated successfully.Customer with email " + email + " already exists.";
			CommonUtility.errorMessage(message, request);
			
			String page = "customer_forum.jsp";	
			RequestDispatcher dispatcher = request.getRequestDispatcher(page);
			dispatcher.forward(request, response);
			
		}else {
			
			readFromFieldsByUpdateCustomer(existCustomer, request, response, customerService,email,cityService,districtService);
			
			// userlist
			List<Customer> customerList = customerService.listCustomer();
			
			// set attribute for usersList in request
			request.setAttribute("customerList", customerList);
			
			// send a message to list_users.jsp
			String message = "Customer updated successfully";
			request.setAttribute("message", message);
			
			String page = "list_customers.jsp";
			
			RequestDispatcher dispatcher = request.getRequestDispatcher(page);
			dispatcher.forward(request, response);
			
		}
		
		
	}
	
	private void readFromFieldsByUpdateCustomer(Customer updateCustomer,HttpServletRequest request,
			HttpServletResponse response,CustomerService customerService,String customerEmail
			,CityService cityService,DistrictService districtService) throws IOException, ServletException {
		
		String customerName = request.getParameter("customer_name");
		String customerSurname = request.getParameter("customer_surname");
		String customerPhoneNumber = request.getParameter("customer_phoneNumber");
		int cityId = Integer.parseInt(request.getParameter("city"));
		int districtId = Integer.parseInt(request.getParameter("district"));
		String customerAddress = request.getParameter("customer_address");
		String customerZipcode = request.getParameter("customer_zipCode");
		String customerPassword = request.getParameter("customer_password");
		String passwordForMd5 = EncryptUtil.md5(customerPassword);
		
		updateCustomer.setName(customerName);
		updateCustomer.setSurname(customerSurname);
		updateCustomer.setPhoneNumber(customerPhoneNumber);
		updateCustomer.setAddress(customerAddress);
		updateCustomer.setZipCode(customerZipcode);
		updateCustomer.setEmail(customerEmail);
		updateCustomer.setPassword(passwordForMd5);
		
		City city = cityService.getCityById(cityId);
		District district = districtService.getDistrictByCity(districtId);
		
		updateCustomer.setCity(city);
		updateCustomer.setDistrict(district);
		
		customerService.updateBook(updateCustomer);
	}
	
}
