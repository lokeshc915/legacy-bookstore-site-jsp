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
import com.bookstore.entity.Category;
import com.bookstore.service.BookOrdersService;
import com.bookstore.service.BookService;
import com.bookstore.service.CategoryService;
import com.bookstore.service.CommonUtility;

/**
 * Servlet implementation class EditDeleteBookServlet
 */
@WebServlet("/backend/edit_delete_book")
public class EditDeleteBookServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditDeleteBookServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("EditDeleteBookServlet");
		String button = request.getParameter("button");
		int bookId = Integer.parseInt(request.getParameter("bookId"));
		BookService bookService = null;
		
		if ("Edit".equals(button)) {
			
			bookService = new BookService();
			Book editBook = bookService.getBookById(bookId);
			
			/* There's a case in which two or admin/manager users managing books at the same time. Imagine this scenario:

			- The first manager views the book list.
			
			- The second manager views the book list.
			
			- The first manager deletes the book 'Java 8 in Action' (ID: 76)
			
			- The second manager tries to edit the book 'Java 8 in Action' which has been deleted.
			
			In this case, the second manager should see a meaningful error message.*/
			
			 if(editBook==null) {
				String message = "Book cannot be found with ID " + bookId;
				CommonUtility.errorMessage(message, request);
			 }else {
				// set attribute for user in request
				request.setAttribute("book", editBook);
				
				// Category list for Book
				List<Category> categoryList = bookService.listCategory();
				request.setAttribute("categoryList", categoryList);
			 }
			 
			 String page = "book_forum.jsp";
			 RequestDispatcher dispatcher = request.getRequestDispatcher(page);
			 dispatcher.forward(request, response);
			
		}else if("Delete".equals(button)) {
			
			bookService = new BookService();
			Book deleteBook = bookService.getBookById(bookId);
			
			/*
			  Imagine this scenario:

				- The first manager views the book list.
				
				- The second manager views the book list.
				
				- The first manager deletes the book 'Java 8 in Action' (ID: 76)
				
				- The second manager tries to delete the book 'Java 8 in Action' which has been deleted.
				
				In this case, the second manager should see a meaningful error message.
			  
			*/
			
			if(deleteBook == null) {
	       		 
      	 		 String message = "Could not find book with ID " + bookId
   					+ ", or it might have been deleted by another admin";
      	 		 CommonUtility.errorMessage(message, request);
       		 
      	 	}else {
      	 		
      	 		/*The admin can't delete a book which has reviews. 
      	 		  A book can be removed only if there's no reviews and orders associate with it. 
      	 		  In this assignment, you write code to check the reviews before deleting a book.
      	 		 */
      	    	 		
      	 		if (!deleteBook.getReviews().isEmpty()) {
    				String message = "Could not delete the book with ID " + bookId
    						+ " because it has reviews";
    				CommonUtility.errorMessage(message, request);
    			} else {
    				
    				/* The admin cannot delete a book which is contained in any orders placed by the customers. 
    				 * A book can be removed if and only if no orders and no reviews associated with it. 
    				 * In this assignment, you are required to implement the part of checking orders.
    				 */
    				
    				BookOrdersService bookOrdersService = new BookOrdersService();
    				long countByOrder = bookOrdersService.countOrderDetailByBook(bookId);
    				
    				if (countByOrder > 0) {
    					String message = "Could not delete book with ID " + bookId
    							+ " because there are orders associated with it.";
    					CommonUtility.errorMessage(message, request);
    				}else {
    					bookService.deleteBook(bookId);
        	       		// send a message to list_users.jsp
        	       		String message = "Book with ID "+ bookId +"deleted successfully";
        	       		CommonUtility.successMessage(message, request);
    				}
    				         	 		
    			}
      	 		
      	 	}
			
			// booklist
			List<Book> bookList = bookService.listBook();
			
			// set attribute for usersList in request
			request.setAttribute("bookList", bookList);
			
			
			String page = "list_books.jsp";
			
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
