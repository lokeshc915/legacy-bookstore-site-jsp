package com.bookstore.controller.frontend;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bookstore.entity.Book;
import com.bookstore.entity.ShoppingCart;
import com.bookstore.service.BookService;

/**
 * Servlet implementation class AddBookToCartServlet
 */
@WebServlet("/add_to_cart")
public class AddBookToCartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddBookToCartServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("AddBookToCartServlet");
		int bookId = Integer.parseInt(request.getParameter("bookId")); // add_to_cart?id=${newBook.id} veya view_book?id=${book.id}
		String button = request.getParameter("button");
		System.out.println("book_detail (button) : " + button);
		
		int bookItemFromDetail = 0;
		
		if ("SepeteEkle".equals(button)) {
			System.out.println("Sepete Ekle is working");
			
			bookItemFromDetail = Integer.parseInt(request.getParameter("item"));
			System.out.println("AddBookToCartServlet 'Sepete Ekle' (bookItemFromDetail) : " + bookItemFromDetail);
			addToItemToTheCart(request, response, 0, bookItemFromDetail, bookId);
			
		}else {
			int bookQuantity = Integer.parseInt(request.getParameter("bookQuantity"));
			System.out.println("AddBookToCartServlet (bookQuantity) : " + bookQuantity);
			addToItemToTheCart(request, response, bookQuantity, 0, bookId);
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	private void addToItemToTheCart(HttpServletRequest request, HttpServletResponse response
			,int bookQuantity,int bookItemFromDetail, int bookId) throws ServletException,IOException {
		
		Object cartObject = request.getSession().getAttribute("cart");
		ShoppingCart shoppingCart = null;
		
		if(cartObject != null && cartObject instanceof ShoppingCart) {
			shoppingCart = (ShoppingCart) cartObject;
		}else {
			shoppingCart = new ShoppingCart();
			request.getSession().setAttribute("cart", shoppingCart);
		}
		
		BookService bookService = new BookService();
		Book book = bookService.getBookById(bookId);
		
		if(bookItemFromDetail == 0) {
			book.setQuantity(book.getQuantity() - bookQuantity);
			//bookService.updateBook(book);
			shoppingCart.addItem(book, bookQuantity);
		}else {
			book.setQuantity(book.getQuantity() - bookItemFromDetail);
			//bookService.updateBook(book);
			shoppingCart.addItem(book, bookItemFromDetail);
		}
		
		String redirectURL = request.getContextPath().concat("/view_cart");
		System.out.println("AddBookToCartServlet (redirectURL) :" + redirectURL);
		response.sendRedirect(redirectURL);
	}

}
