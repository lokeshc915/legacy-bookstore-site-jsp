package com.bookstore.controller.frontend;

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
import com.bookstore.service.CityService;
import com.bookstore.service.CustomerService;
import com.bookstore.service.DistrictService;

/**
 * Servlet implementation class EditCustomerProfileServlet
 */
@WebServlet("/edit_customer_profile")
public class EditCustomerProfileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditCustomerProfileServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("EditCustomerProfileServlet");
		String button = request.getParameter("button");
		int customerId = Integer.parseInt(request.getParameter("customerId"));
		CustomerService customerService = null;
		CityService cityService = null;
		DistrictService districtService = null;
		
		if ("Edit".equals(button)) {
			customerService = new CustomerService();
			Customer editCustomer = customerService.getCustomerById(customerId);
			int editcustomerCityId = editCustomer.getCity().getId();
			System.out.println("EditCustomerProfileServlet / editcustomerCityId : " + editcustomerCityId);
			
			// set attribute for user in request
			request.setAttribute("customer", editCustomer);
			
			cityService = new CityService();
			districtService = new DistrictService();
			
			// City list for Customer
			List<City> cityList = cityService.listCity();
			System.out.println("EditCustomerProfileServlet / CityList Size : " + cityList.size());
			request.setAttribute("cityList", cityList);
			
			// District list for Customer
			List<District> districtList = districtService.listDistrict(editcustomerCityId);
			System.out.println("EditCustomerProfileServlet / DistrictList Size : " + districtList.size());
			request.setAttribute("districtList", districtList);
			
			String page = "frontend/edit_customer_profile.jsp";
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
