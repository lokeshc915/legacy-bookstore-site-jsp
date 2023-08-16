package com.bookstore.controller.backend;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bookstore.entity.BookOrders;
import com.bookstore.service.BookOrdersService;
import com.bookstore.service.CommonUtility;

/**
 * Servlet implementation class EditDeleteDetailBookOrderServlet
 */
@WebServlet("/backend/edit_delete_detail_order")
public class EditDeleteDetailBookOrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditDeleteDetailBookOrderServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("EditDeleteDetailBookOrderServlet");
		String button = request.getParameter("button");
		int orderId = Integer.parseInt(request.getParameter("orderId"));
		
		if("Detail".equals(button)) {
			detailProcess(request,response,orderId);
		}else if("Edit".equals(button)) {
			editProcess(request,response,orderId);
		}else if("Delete".equals(button)) {
			deleteProcess(request,response,orderId);
		}
	}


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	
	private void detailProcess(HttpServletRequest request, HttpServletResponse response, int orderId) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("EditDeleteDetailBookOrderServlet | detailProcess");
		BookOrdersService bookOrdersService = new BookOrdersService();
		BookOrders bookOrder = bookOrdersService.getBookOrdersById(orderId);
		
		
		/*
		 There's a case in which two or admin/manager users managing orders at the same time. Imagine this scenario:

		- The first manager views the order list.

		- The second manager views the order list.

		- The first manager deletes the user ID 26.

		- The second manager tries to view the details of order ID 26 which has already been deleted.

		In this case, the second manager should see a meaningful error message.
		*/
		if(bookOrder!=null) {
			// set attribute for usersList in request
			request.setAttribute("bookOrder", bookOrder);
			String page = "order_detail.jsp";
			RequestDispatcher dispatcher = request.getRequestDispatcher(page);
			dispatcher.forward(request, response);
		}else {
			String message = "Sipariþ Id# " + orderId + "silindiði için bulunamadý.";
			CommonUtility.errorMessage(message, request);
			String page = "list_orders.jsp";
			RequestDispatcher dispatcher = request.getRequestDispatcher(page);
			dispatcher.forward(request, response);
		}
			
	}
	
	
	private void editProcess(HttpServletRequest request, HttpServletResponse response, int orderId) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("EditDeleteDetailBookOrderServlet | editProcess");
		BookOrdersService bookOrdersService = new BookOrdersService();
		
		BookOrders bookOrder = bookOrdersService.getBookOrdersById(orderId);
		
		if (bookOrder == null) {
			String message = "Sipariþ Id# " + orderId + "bulunamadý.";
			CommonUtility.errorMessage(message, request);
			String page = "list_orders.jsp";
			RequestDispatcher dispatcher = request.getRequestDispatcher(page);
			dispatcher.forward(request, response);
			return;
		}
		
		/*HttpSession session = request.getSession();
		Object isPendingBook = session.getAttribute("NewBookPendingToAddToOrder");
		
		if (isPendingBook == null) {			
			session.setAttribute("bookOrder", bookOrder);
		} else {
			session.removeAttribute("NewBookPendingToAddToOrder");
		}*/
		
		request.setAttribute("bookOrder", bookOrder);
		request.setAttribute("orderId", orderId);
		
		String page = "order_form.jsp";
		RequestDispatcher dispatcher = request.getRequestDispatcher(page);
		dispatcher.forward(request, response);
		
		
	}
	
	private void deleteProcess(HttpServletRequest request, HttpServletResponse response, int orderId) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("EditDeleteDetailBookOrderServlet | deleteProcess");
		BookOrdersService bookOrdersService = new BookOrdersService();
		
		BookOrders bookOrder = bookOrdersService.getBookOrdersById(orderId);
		
		if (bookOrder != null) {		
			bookOrdersService.deleteBookOrder(orderId);
		
			String message = "The sipariþ ID " + orderId + " silindi.";
			
			CommonUtility.successMessage(message, request);
			
			List<BookOrders> bookOrdersList = bookOrdersService.listBookOrders();
			// set attribute for usersList in request
			request.setAttribute("bookOrdersList", bookOrdersList);
			
			String page = "list_orders.jsp";
			RequestDispatcher dispatcher = request.getRequestDispatcher(page);
			dispatcher.forward(request, response);
			
			
		} else {
			
			
			/*			 
			  There's a case in which two or admin/manager users managing order information at the same time. Imagine this scenario:

				- The first manager views the order list.
				
				- The second manager views the order list.
				
				- The first manager deletes the order ID 18
				
				- The second manager tries to delete the order ID 18 which has been deleted.
				
				In this case, the second manager should see a meaningful error message.
			  			  
			 */
			
			
			String message = "Could not find order with ID " + orderId +
					", or it might have been deleted by another admin.";
			
			CommonUtility.errorMessage(message, request);
			
			List<BookOrders> bookOrdersList = bookOrdersService.listBookOrders();
			// set attribute for usersList in request
			request.setAttribute("bookOrdersList", bookOrdersList);
			
			String page = "list_orders.jsp";
			RequestDispatcher dispatcher = request.getRequestDispatcher(page);
			dispatcher.forward(request, response);
			
		}
		
		
	}


	
}
