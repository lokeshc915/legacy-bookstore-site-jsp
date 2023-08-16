package com.bookstore.controller.frontend;

import java.io.IOException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bookstore.dao.OrdersDAO;
import com.bookstore.entity.Book;
import com.bookstore.entity.BookOrders;
import com.bookstore.entity.Customer;
import com.bookstore.entity.DetailOrder;
import com.bookstore.entity.ShoppingCart;
import com.bookstore.service.CommonUtility;

/**
 * Servlet implementation class PlaceOrderServlet
 */
@WebServlet("/checkout_process")
public class PlaceOrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PlaceOrderServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("PlaceOrderServlet");
		String buttonCheckout = request.getParameter("Checkout");
		if ("Checkout".equals(buttonCheckout)) {
			placeOrder(request,response);
		}
	}


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	private void placeOrder(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("placeOrder is working");
		String recipientName = request.getParameter("recipientName");
		String recipientSurname = request.getParameter("recipientSurname");
		String recipientPhone = request.getParameter("recipientPhone");
		String address = request.getParameter("recipientAddress");
		String city = request.getParameter("recipientCityName");
		String district = request.getParameter("recipientDistrictName");
		String zipcode = request.getParameter("recipientZipCode");
		String paymentMethod = request.getParameter("paymentMethod");
		String shippingAddress = address + ", " + city + ", " + district + ", " + zipcode;
		
		BookOrders order = new BookOrders();
		order.setRecipentName(recipientName + " " + recipientSurname);
		order.setRecipentPhone(recipientPhone);
		order.setShippingAddress(shippingAddress);
		order.setPaymentMethod(paymentMethod);
		
		HttpSession session = request.getSession();
		Customer customer = (Customer) session.getAttribute("loggedCustomer");
		order.setCustomer(customer);
		
		ShoppingCart shoppingCart = (ShoppingCart) session.getAttribute("cart");
		Map<Book, Integer> items = shoppingCart.getCart();
		
		Iterator<Book> iterator = items.keySet().iterator();
		
		Set<DetailOrder> orderDetails = new HashSet<>();
		
		while (iterator.hasNext()) {
			Book book = iterator.next();
			Integer quantity = items.get(book);
			float subtotal = quantity * book.getPrice();
			
			DetailOrder orderDetail = new DetailOrder();
			orderDetail.setBook(book);
			orderDetail.setOrders(order);
			orderDetail.setQuantity(quantity);
			orderDetail.setSubTotal(subtotal);
			
			orderDetails.add(orderDetail);
		}
		
		order.setOrderDetails(orderDetails);
		order.setOrderTotal((float)shoppingCart.getTotalAmount());
		
		OrdersDAO ordersDAO = new OrdersDAO();
		ordersDAO.create(order);
		
		shoppingCart.clearCart();
		
		String message = "Teþekkürler. Sipariþiniz alýnmýþtýr"
				+ "Birkaç gün içinde teslim edilecektir.";
		showMessageFrontend(message, request, response);
	}

	private void showMessageFrontend(String message, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		CommonUtility.successMessage(message, request);
		
		String page = "frontend/message.jsp";
		RequestDispatcher dispatcher = request.getRequestDispatcher(page);
		dispatcher.forward(request, response);
	}

}
