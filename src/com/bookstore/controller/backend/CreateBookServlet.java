package com.bookstore.controller.backend;

import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.bookstore.entity.Book;
import com.bookstore.entity.Category;
import com.bookstore.service.BookService;
import com.bookstore.service.CategoryService;
import com.bookstore.service.CommonUtility;

/**
 * Servlet implementation class CreateBookServlet
 */
@WebServlet("/backend/create_book")
@MultipartConfig(
		fileSizeThreshold = 1024 * 10, // 10KB
		maxFileSize = 1024 * 300, // 300KB
		maxRequestSize = 1024 * 1024 // 1 MB
		) 
public class CreateBookServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateBookServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String button = request.getParameter("button");
		
		if("Save".equals(button)) {
			System.out.println("CreateBookServlet :Save Button");
			createBook(request, response);
		}
		else if("Cancel".equals(button)) {
			System.out.println("CreateBookServlet :Cancel Button");
			String page = "list_books.jsp";	
			response.sendRedirect(page);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	private void createBook(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String title = request.getParameter("book_title");
		String author = request.getParameter("book_author");
		String isbn = request.getParameter("book_isbn");
		String priceText = request.getParameter("book_price");
		String quantityText = request.getParameter("book_quantity");
		int categoryId = Integer.parseInt(request.getParameter("category"));
		String description = request.getParameter("book_description");
		
		float price = Float.parseFloat(priceText);
		int quantity = Integer.parseInt(quantityText);
		
		// Date
		SimpleDateFormat dt = new SimpleDateFormat("dd/mm/yyyy"); 
		Date publishDate = null;
		try {
			publishDate = dt.parse(request.getParameter("publishDate"));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		// Image
		// obtains the upload file part in this multipart request
        Part filePart = request.getPart("imageFileUpload");
		
		
		// BookService
		BookService bookService = new BookService();
		
		// CategoryService
		CategoryService categoryService = new CategoryService();
		
		Book existBook = bookService.findByTitle(title);
		
		if(existBook!=null) {
			
			String message = "This title name " + title + " already exists. Please write another book title name";
			CommonUtility.errorMessage(message, request);
			
			String page = "book_forum.jsp";
			
			RequestDispatcher dispatcher = request.getRequestDispatcher(page);
			dispatcher.forward(request, response);
			
		}else {
						
			
			// Create Book
			
			Book newBook = new Book();
			newBook.setAuthor(author);
			newBook.setTitle(title);
			newBook.setDescription(description);
			newBook.setIsbn(isbn);
			newBook.setPublishDate(publishDate);
			newBook.setPrice(price);
			newBook.setQuantity(quantity);
			
			// Category related with Book
			Category category = categoryService.getCategoryById(categoryId);
			newBook.setCategory(category);
			
			// Image 
			if(filePart!=null && filePart.getSize()>0) {
	        	long size = filePart.getSize();
	        	byte[] imageBytes = new byte[(int) size];
	        	
	        	InputStream inputStream = filePart.getInputStream();
	        	inputStream.read(imageBytes);
	        	inputStream.close();
	        	
	        	newBook.setImage(imageBytes);
	        }
			
			// Creating Book with Service
			bookService.createBook(newBook);
			
			// userlist
			List<Book> bookList = bookService.listBook();
			
			// set attribute for usersList in request
			request.setAttribute("bookList", bookList);
			
			// send a message to list_users.jsp
			String message = "New book created successfully";
			request.setAttribute("message", message);
			
			String page = "list_books.jsp";
			
			RequestDispatcher dispatcher = request.getRequestDispatcher(page);
			dispatcher.forward(request, response);
			
		}
		
	}

}
