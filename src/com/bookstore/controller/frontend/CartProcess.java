package com.bookstore.controller.frontend;

import java.io.IOException;
import java.util.Arrays;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bookstore.entity.Book;
import com.bookstore.entity.ShoppingCart;
import com.bookstore.service.BookService;
import com.bookstore.service.CommonUtility;

/**
 * Servlet implementation class RemoveItemFromCart
 */
@WebServlet("/cart_process")
public class CartProcess extends HttpServlet {
	private static final long serialVersionUID = 1L;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CartProcess() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("CartProcess");
		int bookId = Integer.parseInt(request.getParameter("bookId"));
		String button = request.getParameter("button");
		
		// Remove Selected Item From Cart
		if("Remove".equals(button)) {
			System.out.println("Remove is working");
			removeItemFromTheCart(request, response, bookId);
		}else if("Update".equals(button)) {
			System.out.println("Update is working");
			updateItemsInTheCart(request, response);
		}else if("EmptyAllCart".equals(button)) {
			clearAllItemsIntheCart(request, response);
		}else if("Checkout".equals(button)) {
			checkoutForm(request, response);
		}else if("Continue Shopping".equals(button)) {
			continueShopping(request, response);
		}
		
	}



	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	private void removeItemFromTheCart(HttpServletRequest request, HttpServletResponse response
        , int bookId) throws ServletException,IOException {
		System.out.println("removeItemFromTheCart is working");
		
		Object cartObject = request.getSession().getAttribute("cart");
		ShoppingCart shoppingCart = (ShoppingCart) cartObject;
		
		BookService bookService = new BookService();
		Book book = bookService.getBookById(bookId);
		
		Integer quantity = null;
		
		String[] arrayOfBookIds = request.getParameterValues("bookId");
		String[] arrayOfQuantities = new String[arrayOfBookIds.length];
		
		for(int i=1;i<=arrayOfQuantities.length;i++) {  // i=1
			String quantityString = request.getParameter("quantity"+i); // quantity1 , quantity2 ...
			arrayOfQuantities[i-1] = quantityString;  // i-1
			System.out.println("removeItemFromTheCart arrayOfQuantities[i-1] :" + arrayOfQuantities[i-1] );
		}
		
		int[] bookIds = Arrays.stream(arrayOfBookIds).mapToInt(Integer::parseInt).toArray();
		int[] quantities = Arrays.stream(arrayOfQuantities).mapToInt(Integer::parseInt).toArray();
		
		if(bookIds[0] == bookId) {
			quantity = quantities[0];
			System.out.println("quantity :" + quantity);
			book.setQuantity(book.getQuantity() + quantity);
			bookService.updateBook(book);
			shoppingCart.removeItem(book);
		}
		
		
		//shoppingCart.removeItem(book);
		
		String redirectURL = request.getContextPath().concat("/view_cart");
		System.out.println("removeItemFromTheCart (redirectURL) :" + redirectURL);
		response.sendRedirect(redirectURL);
	}
	
	private void updateItemsInTheCart(HttpServletRequest request, HttpServletResponse response
	        ) throws ServletException,IOException {
		System.out.println("updateItemsInTheCart is working");
		
		BookService bookService = new BookService();
		
		String[] arrayOfBookIds = request.getParameterValues("bookId");
		String[] arrayOfQuantities = new String[arrayOfBookIds.length];
		
		for(int i=1;i<=arrayOfQuantities.length;i++) {  // i=1
			String quantity = request.getParameter("quantity"+i); // quantity1 , quantity2 ...
			arrayOfQuantities[i-1] = quantity;  // i-1
			System.out.println("updateItemsInTheCart arrayOfQuantities[i-1] :" + arrayOfQuantities[i-1] );
		}
		
		int[] bookIds = Arrays.stream(arrayOfBookIds).mapToInt(Integer::parseInt).toArray();
		int[] quantities = Arrays.stream(arrayOfQuantities).mapToInt(Integer::parseInt).toArray();
		
		Object cartObject = request.getSession().getAttribute("cart");
		ShoppingCart shoppingCart = (ShoppingCart) cartObject;
		String message = "";
		
		for(int i=0;i<bookIds.length;i++) {
			Book key = bookService.getBookById(bookIds[i]);
			Integer quantity = quantities[i];
			int initialQuantityOfItem = key.getQuantity();
			
			
			if(quantity == 0) {
				message = "Kitap Adý " + key.getTitle() + " Miktarý 0 girilmez.";
				CommonUtility.errorMessage(message, request);
			}else if(quantity<1 || quantity>3) {
				message = "Kitap Adý " + key.getTitle() + " Miktarý 1 ile 3 arasýnda girilmelidir.";
				if(quantity > key.getQuantity() ) {
					message = "Kitap Adý " + key.getTitle() + " Stokta bulunmamaktadýr.";
				}
				
				CommonUtility.errorMessage(message, request);
				
			}else {
				if(key.getQuantity() >=1) {
					if(quantity == 1) {
						System.out.println("quantity == 1");
						System.out.println("quantity == 1 key.getQuantity() :" + key.getQuantity());
					}else if(quantity == 2) {
						System.out.println("quantity == 2");
						System.out.println("quantity == 2 key.getQuantity() :" + key.getQuantity());
					}else {
						System.out.println("quantity == 3");
						System.out.println("quantity == 3 key.getQuantity() :" + key.getQuantity());
					}
					
					System.out.println("Kitap Adý : " + key.getTitle() + " son miktar : " + initialQuantityOfItem);
					
					//key.setQuantity(initialQuantityOfItem - quantity);
					//bookService.updateBook(key);
					shoppingCart.updateCart(key, quantity);
				}
			}
		}
		
		
		
		
		//shoppingCart.updateCart(bookIds, quantities);
		
		System.out.println("updateItemsInTheCart message : " + message);
		
		String page = "frontend/shopping_cart.jsp";
		RequestDispatcher requestDispatcher = request.getRequestDispatcher(page);
		requestDispatcher.forward(request, response);
		
	}
	
	private void clearAllItemsIntheCart(HttpServletRequest request, HttpServletResponse response
	        ) throws ServletException,IOException {
		
		Object cartObject = request.getSession().getAttribute("cart");
		ShoppingCart shoppingCart = (ShoppingCart) cartObject;
		
		Book key = null;
		Integer quantity = null;
		BookService bookService = new BookService();
		String message = "";
		
		String[] arrayOfBookIds = request.getParameterValues("bookId");
		String[] arrayOfQuantities = new String[arrayOfBookIds.length];
		
		for(int i=1;i<=arrayOfQuantities.length;i++) {  // i=1
			String quantityString = request.getParameter("quantity"+i); // quantity1 , quantity2 ...
			arrayOfQuantities[i-1] = quantityString;  // i-1
			System.out.println("clearAllItemsIntheCart arrayOfQuantities[i-1] :" + arrayOfQuantities[i-1] );
		}
		
		int[] bookIds = Arrays.stream(arrayOfBookIds).mapToInt(Integer::parseInt).toArray();
		int[] quantities = Arrays.stream(arrayOfQuantities).mapToInt(Integer::parseInt).toArray();
		
		for(int i=0;i<bookIds.length;i++) {
			key = bookService.getBookById(bookIds[i]);
			quantity = quantities[i];
			
			if(quantity == 1) {
				System.out.println("quantity == 1");
				System.out.println("quantity == 1 key.getQuantity() :" + key.getQuantity());
				key.setQuantity(key.getQuantity() + quantity);
			}else if(quantity == 2) {
				System.out.println("quantity == 2");
				System.out.println("quantity == 2 key.getQuantity() :" + key.getQuantity());
				key.setQuantity(key.getQuantity() + quantity);
			}else {
				System.out.println("quantity == 3");
				System.out.println("quantity == 3 key.getQuantity() :" + key.getQuantity());
				key.setQuantity(key.getQuantity() + quantity);
			}
			bookService.updateBook(key);
			shoppingCart.updateCart(key, quantity);
		}
		
		shoppingCart.clearCart();
		
		String redirectURL = request.getContextPath().concat("/view_cart");
		System.out.println("updateItemsInTheCart (redirectURL) :" + redirectURL);
		response.sendRedirect(redirectURL);
		
	}
	
	private void checkoutForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("checkoutForm is working");
						
		String redirectURL = request.getContextPath().concat("/checkout");
		System.out.println("checkoutForm (redirectURL) :" + redirectURL);
		response.sendRedirect(redirectURL);
		
	}

	private void continueShopping(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String page = "/";
		RequestDispatcher requestDispatcher = request.getRequestDispatcher(page);
		requestDispatcher.forward(request, response);
	}
	
	
}
