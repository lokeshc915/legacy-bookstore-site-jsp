package com.bookstore.controller.backend;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

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
 * Servlet implementation class AddSelectedBookToOrderServlet
 */
@WebServlet("/backend/add_selected_book_order")
public class AddSelectedBookToOrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddSelectedBookToOrderServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("AddSelectedBookToOrderServlet");
		String button = request.getParameter("button");
		
		if("Add".equals(button)) {
			addBookToOrder(request, response);
		}
		else if("Cancel".equals(button)) {
			String page = "order_form.jsp";	
			response.sendRedirect(page);
		}
	}

	private void addBookToOrder(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("AddSelectedBookToOrderServlet | addBookToOrder");
		int bookId = Integer.parseInt(request.getParameter("bookId"));
		BookService bookService = new BookService();
		int quantity = 0;
		
		String[] arrayOfBookIds = request.getParameterValues("bookId");
		String[] arrayOfQuantities = new String[arrayOfBookIds.length];
		
		for(int i=1;i<=arrayOfQuantities.length;i++) {  // i=1
			String quantityString = request.getParameter("quantity"+i); // quantity1 , quantity2 ...
			System.out.println("addBookToOrder quantityString : " + quantityString);
			arrayOfQuantities[i-1] = quantityString;  // i-1
			System.out.println("addBookToOrder arrayOfQuantities[i-1] :" + arrayOfQuantities[i-1] );
		}
		
		int[] bookIds = Arrays.stream(arrayOfBookIds).mapToInt(Integer::parseInt).toArray();
		int[] quantities = Arrays.stream(arrayOfQuantities).mapToInt(Integer::parseInt).toArray();
				
		Book book = null;
		
		// bookIds ile quantities birbirine map ettik
		Map<Integer, Integer> bookwithQuantity = IntStream.range(0, bookIds.length).boxed()
			    .collect(Collectors.toMap(i -> bookIds[i], i -> quantities[i]));
		
		for (Map.Entry<Integer, Integer> entry : bookwithQuantity.entrySet()) {
			if(entry.getValue() != 0) {
				quantity = entry.getValue();
				book = bookService.getBookById(entry.getKey());
				System.out.println("Kitap Adý : " + book.getTitle());
			}
		}
		
		/*for(int i=0;i<bookIds.length;i++) {
			for(int j=0;j<quantities.length;j++) {
				if(quantities[j]!= 0) {
					quantity = quantities[j];
					System.out.println("addBookToOrder | quantity :" + quantity);
					if() {
						
					}
				}
			}
		}*/
		
		
		/*if(bookIds[0] == bookId) {
			quantity = quantities[1];
			System.out.println("addBookToOrder | quantity :" + quantity);
			book = bookService.getBookById(bookIds[1]);
			System.out.println("Kitap Adý : " + book.getTitle());
		}*/
		
		int orderId = Integer.parseInt(request.getParameter("orderId"));
		BookOrdersService bookOrdersService = new BookOrdersService();
		BookOrders bookOrder = bookOrdersService.getBookOrdersById(orderId);
		
		if(bookOrder !=null) {
			System.out.println("AddBookToOrderServlet bookOrder is not null");
		}
		
		
		System.out.println("bookOrder.getOrderTotal() : " + bookOrder.getOrderTotal());
		
		System.out.println("quantity : " + quantity);
		
		if(quantity < 0 || quantity == 0) {
			String message = "Miktar " + quantity + " olamaz. Miktarý 0'dan büyük giriniz.";
			CommonUtility.errorMessage(message, request);
			
			List<Book> bookList = bookService.listBook();
			request.setAttribute("bookList", bookList);
			
			String resultPage = "list_order_books.jsp";
			RequestDispatcher dispatcher = request.getRequestDispatcher(resultPage);
			dispatcher.forward(request, response);
			return;
		}
		
		float subTotal = quantity * book.getPrice();
		System.out.println("subTotal : " + subTotal);
		
		
		DetailOrder detailOrder = new DetailOrder();
		detailOrder.setBook(book);
		detailOrder.setQuantity(quantity);
		detailOrder.setSubTotal(subTotal);
		
		float newTotal = bookOrder.getOrderTotal() + subTotal;
		bookOrder.setOrderTotal(newTotal);
		
		
		// update existing order detail that has the same book	
		boolean isBoolAlreadyInOrder = false;
		Set<DetailOrder> orderDetails = bookOrder.getOrderDetails();
		for (DetailOrder od : orderDetails) {
			if (od.getBook().equals(book)) {
				isBoolAlreadyInOrder = true;
				od.setQuantity(od.getQuantity() + quantity);
				od.setSubTotal(od.getSubTotal() + subTotal);
				break;
			}
		}
		
		if (!isBoolAlreadyInOrder) {
			orderDetails.add(detailOrder);
		}
		
		
		request.setAttribute("bookOrder", bookOrder);
		
		String resultPage = "order_form.jsp";
		RequestDispatcher dispatcher = request.getRequestDispatcher(resultPage);
		dispatcher.forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
