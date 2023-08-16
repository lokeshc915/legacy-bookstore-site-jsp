package com.bookstore.controller.backend;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bookstore.entity.City;
import com.bookstore.entity.Customer;
import com.bookstore.entity.District;
import com.bookstore.service.BookOrdersService;
import com.bookstore.service.CityService;
import com.bookstore.service.CommonUtility;
import com.bookstore.service.CustomerService;
import com.bookstore.service.DistrictService;
import com.bookstore.service.ReviewService;

/**
 * Servlet implementation class EditDeleteCustomerServlet
 */
@WebServlet("/backend/edit_delete_customer")
public class EditDeleteCustomerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditDeleteCustomerServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("EditDeleteCustomerServlet");
		String button = request.getParameter("button");
		int customerId = Integer.parseInt(request.getParameter("customerId"));
		CustomerService customerService = null;
		CityService cityService = null;
		DistrictService districtService = null;
		ReviewService reviewService = null;
		
		if ("Edit".equals(button)) {
			
			customerService = new CustomerService();
			Customer editCustomer = customerService.getCustomerById(customerId);
			int editcustomerCityId = editCustomer.getCity().getId();
			System.out.println("EditDeleteCustomerServlet / editcustomerCityId : " + editcustomerCityId);
			
			
			/*There's a case in which two or admin/manager users managing customer information at the same time. Imagine this scenario:

			- The first manager views the customer list.

			- The second manager views the customer list.

			- The first manager deletes the user 'Alice' (ID: 32)

			- The second manager tries to edit the customer Alice which has been deleted.

			In this case, the second manager should see a meaningful error message.
			*/
			 if(editCustomer==null) {
				String message = "Customer cannot be found with ID " + customerId;
				CommonUtility.errorMessage(message, request);
			 }else {
				// set attribute for user in request
				request.setAttribute("customer", editCustomer);
				
				cityService = new CityService();
				districtService = new DistrictService();
				
				// City list for Customer
				List<City> cityList = cityService.listCity();
				System.out.println("EditDeleteCustomerServlet / CityList Size : " + cityList.size());
				request.setAttribute("cityList", cityList);
				
				// District list for Customer
				List<District> districtList = districtService.listDistrict(editcustomerCityId);
				System.out.println("EditDeleteCustomerServlet / DistrictList Size : " + districtList.size());
				request.setAttribute("districtList", districtList);
			 }
			 
			 String page = "customer_forum.jsp";
			 RequestDispatcher dispatcher = request.getRequestDispatcher(page);
			 dispatcher.forward(request, response);
			
		}else if("Delete".equals(button)) {
			
			customerService = new CustomerService();
			Customer deleteCustomer = customerService.getCustomerById(customerId);
				
			/*Imagine this scenario:

				- The first manager views the customer list.

				- The second manager views the customer list.

				- The first manager deletes the customer 'Alice'.

				- The second manager tries to delete the customer 'Alice' which has already been deleted.

				In this case, the second manager should see a meaningful error message.
				*/
			if(deleteCustomer == null) {
	       		 
     	 		 String message = "Could not find customer with ID " + customerId
  					+ ", or it might have been deleted by another admin";
     	 		 CommonUtility.errorMessage(message, request);
      		 
     	 	}else {
     	 		
     	 		/*
     	 		  The admin cannot delete a customer if there are still reviews or orders associated with her/him on the website. 
     	 		  In this assignment, you are required to check only the reviews part.
				  So the delete customer feature should perform this check before deleting the customer. 
				  If there are reviews posted by the customer, display an error message. 
				  Otherwise the customer can be deleted.    	 		       	 
     	 		 */
     	 		
     	 		
     	 		reviewService = new ReviewService();
     	 		long reviewCount = reviewService.countByCustomer(customerId);
     	 		
     	 		if (reviewCount > 0) {
     	 			String message = "Could not delete customer with ID " + customerId
    						+ " because he/she posted reviews for books.";
     	 			CommonUtility.errorMessage(message, request);
     	 			
     	 		}else {
     	 			
     	 			/* The admin cannot delete a customer if there are orders associated with him or her.
     	 			   A customer can be removed from the system if and only if he/she doesn't have any reviews and orders. 
     	 			   In this assignment, you are required to implement the checking orders part.
     	 			 */

     	 			BookOrdersService bookOrdersService = new BookOrdersService();
     	 			long orderCount = bookOrdersService.countByCustomer(customerId);
     	 			
     	 			if (orderCount > 0) {
    					String message = "Could not delete customer with ID " + customerId 
    							+ " because he/she placed orders.";
    					CommonUtility.errorMessage(message, request);
    				} else {
    					customerService.deleteBook(customerId);
        	       		// send a message to list_users.jsp
        	       		String message = "Customer with ID "+ customerId +" deleted successfully";
        	       		CommonUtility.errorMessage(message, request);
    				}
     	 			
     	 			
     	 		}
     	 		   	 		    	 		
     	 	}
			
			// customerlist
			List<Customer> customerList = customerService.listCustomer();
			
			// set attribute for usersList in request
			request.setAttribute("customerList", customerList);
			
		
			String page = "list_customers.jsp";
			
			RequestDispatcher dispatcher = request.getRequestDispatcher(page);
			dispatcher.forward(request, response);
			
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
