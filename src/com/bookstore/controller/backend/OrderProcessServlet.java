package com.bookstore.controller.backend;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bookstore.entity.Book;
import com.bookstore.entity.BookOrders;
import com.bookstore.entity.DetailOrder;
import com.bookstore.service.BookOrdersService;
import com.bookstore.service.BookService;
import com.bookstore.service.CommonUtility;

/**
 * Servlet implementation class OrderProcessServlet
 */
@WebServlet("/backend/order_process")
public class OrderProcessServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public OrderProcessServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("OrderProcessServlet");
		
		String button = request.getParameter("button");
		
		if("Kaydet".equals(button)) {
			System.out.println("OrderProcessServlet | Kaydet button is clicked");
			saveBookToOrder(request, response);
			
		}else if("Cancel".equals(button)) {
			System.out.println("OrderProcessServlet | Cancel button is clicked");
			String page = "list_orders.jsp";	
			response.sendRedirect(page);
			
		}else if("Delete".equals(button)) {
			System.out.println("OrderProcessServlet | Delete is working");
			delete(request, response);
		}else if("Ekle".equals(button)) {
			System.out.println("OrderProcessServlet | Ekle is working");
			addBookToOrderList(request, response);
		}
	}


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	private void saveBookToOrder(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("OrderProcessServlet | saveBookToOrder is working");
		
		int orderId = Integer.parseInt(request.getParameter("orderId"));
		
		
		String recipientName = request.getParameter("recipientName");
		String recipientPhone = request.getParameter("recipientPhone");
		String shippingAddress = request.getParameter("shippingAddress");
		
		System.out.println("SaveBookOrderServlet | recipientName : " + recipientName);
		System.out.println("SaveBookOrderServlet | recipientPhone : " + recipientPhone);
		System.out.println("SaveBookOrderServlet | shippingAddress : " + shippingAddress);
		
		StringBuilder errorMessage = new StringBuilder();
		
		
		String errorMessageText = showErrorMessage(recipientName, recipientPhone, shippingAddress, errorMessage);
		System.out.println("OrderProcessServlet | Before if-else statement | errorMessageText : " + errorMessageText);
		if(!errorMessageText.isEmpty()) {
			System.out.println("OrderProcessServlet | !errorMessageText.isEmpty() | errorMessageText : " + errorMessageText);
			CommonUtility.errorMessage(errorMessageText, request);
			
			BookOrdersService bookOrdersService = new BookOrdersService();
			BookOrders bookOrder = bookOrdersService.getBookOrdersById(orderId);
			
			request.setAttribute("bookOrder", bookOrder);
			
			
			String resultPage = "order_form.jsp";
			RequestDispatcher dispatcher = request.getRequestDispatcher(resultPage);
			dispatcher.forward(request, response);
			
		}else {
			
			System.out.println("OrderProcessServlet | errorMessageText.isEmpty() | errorMessageText : " + errorMessageText);
			
			BookOrdersService bookOrdersService = new BookOrdersService();
			BookOrders bookOrder = bookOrdersService.getBookOrdersById(orderId);
			
			String paymentMethod = request.getParameter("paymentMethod");
			String orderStatus = request.getParameter("orderStatus");
			
			System.out.println("OrderProcessServlet | paymentMethod : " + paymentMethod);
			
			System.out.println("OrderProcessServlet | orderStatus : " + orderStatus);
						
			bookOrder.setRecipentName(recipientName);
			bookOrder.setRecipentPhone(recipientPhone);
			bookOrder.setShippingAddress(shippingAddress);
			bookOrder.setPaymentMethod(paymentMethod);
			bookOrder.setOrderStatus(orderStatus);
			
			String[] arrayBookId = request.getParameterValues("bookId");
			String[] arrayPrice = request.getParameterValues("price");
			String[] arrayQuantity = new String[arrayBookId.length];
			
			
			for (int i = 1; i <= arrayQuantity.length; i++) {
				arrayQuantity[i - 1] = request.getParameter("quantity" + i);
			}
			
			Set<DetailOrder> orderDetails = bookOrder.getOrderDetails();
			orderDetails.clear();
			
			float totalAmount = 0.0f;
			
			for (int i = 0; i < arrayBookId.length; i++) {
				int bookId = Integer.parseInt(arrayBookId[i]);
				int quantity = Integer.parseInt(arrayQuantity[i]);
				float price = Float.parseFloat(arrayPrice[i]);
				
				float subtotal = price * quantity;
				
				DetailOrder orderDetail = new DetailOrder();
				orderDetail.setBook(new Book(bookId));
				orderDetail.setQuantity(quantity);
				orderDetail.setSubTotal(subtotal);
				orderDetail.setOrders(bookOrder);
				
				orderDetails.add(orderDetail);
				
				totalAmount += subtotal;
			}
			
			bookOrder.setOrderTotal(totalAmount);
			
			System.out.println("------------------------------------------------------------");
			System.out.println(bookOrder.getId() + "# RecipentName : " + bookOrder.getRecipentName());
			System.out.println(bookOrder.getId() + "# RecipentPhone : " + bookOrder.getRecipentPhone());
			System.out.println(bookOrder.getId() + "# ShippingAddress : " + bookOrder.getShippingAddress());
			System.out.println(bookOrder.getId() + "# OrderStatus : " + bookOrder.getOrderStatus());
			System.out.println(bookOrder.getId() + "# PaymentMethod : " + bookOrder.getPaymentMethod());
			System.out.println(bookOrder.getId() + "# AllBookCopies : " + bookOrder.getAllBookCopies());			
			for (DetailOrder detail : bookOrder.getOrderDetails()) {
				Book book = detail.getBook();
				int quantity = detail.getQuantity();
				float subtotal = detail.getSubTotal();
				System.out.println("\tBook Name : " + book.getTitle() 
				                   + "| Book Quantity : " + quantity + " | Sub Total : " + subtotal);
				System.out.println("----------------------------------------------------------------------------");
			}			
			System.out.println("----------------------------------------------------------------");
			
			bookOrdersService.updateBookOrder(bookOrder);
			
			String message = "Order #  " + bookOrder.getId() + " Updated.";
			
			CommonUtility.successMessage(message, request);
			
			List<BookOrders> bookOrdersList = bookOrdersService.listBookOrders();
			
			
			// set attribute for usersList in request
			request.setAttribute("bookOrdersList", bookOrdersList);
			
			String page = "list_orders.jsp";
			
			RequestDispatcher dispatcher = request.getRequestDispatcher(page);
			dispatcher.forward(request, response);
						
		}
		
		
		
	}
	
	private String showErrorMessage(String recipientName, String recipientPhone, String shippingAddress,
			StringBuilder errorMessage) {
		System.out.println("OrderProcessServlet | showErrorMessage function is working");
		
		if(recipientName.equals("")) {
			System.out.println("OrderProcessServlet | recipientName is empty");
			String message = "Alýcý Alaný Boþ Býrakýlmýþ. ";
			errorMessage.append(message);
		}
		
		if(recipientPhone.equals("")) {
			System.out.println("OrderProcessServlet | recipientName is empty");
			String message = "Telefon Alaný Boþ Býrakýlmýþ. ";
			errorMessage.append(message);
		}
		
		if(shippingAddress.equals("")) {
			System.out.println("OrderProcessServlet | recipientName is empty");
			String message = "Teslimat Adresi Alaný Boþ Býrakýlmýþ. ";
			errorMessage.append(message);
		}
		
		String errorMessageText = errorMessage.toString();
		
		System.out.println("OrderProcessServlet | showErrorMessage | errorMessageText : " + errorMessageText);
		
		return errorMessageText;
	}
	
	private void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("OrderProcessServlet | delete is working");
		
		int bookId = Integer.parseInt(request.getParameter("bookId"));
		int orderId = Integer.parseInt(request.getParameter("orderId"));
		
		System.out.println("OrderProcessServlet | bookId : " + bookId + " | orderId : " + orderId);
		
		BookOrdersService bookOrdersService = new BookOrdersService();
		BookOrders bookOrder = bookOrdersService.getBookOrdersById(orderId);
		
		if(bookOrder == null) {
			System.out.println("OrderProcessServlet | delete |  bookOrder is null");
		}
		
		Set<DetailOrder> orderDetails = bookOrder.getOrderDetails();
		Iterator<DetailOrder> iterator = orderDetails.iterator();
		
		
		
		while (iterator.hasNext()) {
			DetailOrder orderDetail = iterator.next();
			
			if (orderDetail.getBook().getId() == bookId) {
				System.out.println("OrderProcessServlet | delete | Book Title : " + orderDetail.getBook().getTitle() + " silindi.");
				float newTotal = bookOrder.getOrderTotal() - orderDetail.getSubTotal();
				bookOrder.setOrderTotal(newTotal);
				iterator.remove();
				
				bookOrdersService.deleteBookFromOrderList(bookId, orderId);
				
			}
		}
		
		
		orderDetails = bookOrder.getOrderDetails();

		request.setAttribute("bookOrder", bookOrder);
		
		String editOrderFormPage = "order_form.jsp";
		RequestDispatcher dispatcher = request.getRequestDispatcher(editOrderFormPage);
		dispatcher.forward(request, response);
	}
	
	private void addBookToOrderList(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		BookService bookService = new BookService();
		BookOrdersService bookOrdersService = new BookOrdersService();
		int orderId = Integer.parseInt(request.getParameter("orderId"));
		
		BookOrders bookOrder = bookOrdersService.getBookOrdersById(orderId);
		if(bookOrder !=null) {
			System.out.println("OrderProcessServlet | addBookToOrderList | bookOrder is not null");
		}
		
		List<Book> bookList = bookService.listBook();
		request.setAttribute("bookList", bookList);
		request.setAttribute("bookOrder",bookOrder);
		request.setAttribute("orderId",orderId);
		
		String page = "list_order_books.jsp";
		RequestDispatcher dispatcher = request.getRequestDispatcher(page);
		dispatcher.forward(request, response);
	}

}
