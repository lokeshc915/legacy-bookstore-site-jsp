package com.bookstore.service;

import java.util.List;

import org.hibernate.SessionFactory;

import com.bookstore.dao.BookDao;
import com.bookstore.dao.CategoryDao;
import com.bookstore.entity.Book;
import com.bookstore.entity.Category;

public class BookService {

	//private SessionFactory sessionFactory;
	private BookDao bookDao;
	private CategoryDao categoryDao;
	
	public BookService() {
		super();
		//this.bookDao = new BookDao(sessionFactory);
		//this.categoryDao = new CategoryDao(sessionFactory);
		this.bookDao = new BookDao();
		this.categoryDao = new CategoryDao();
	}
	
	public List<Book> listBook() {
		List<Book> books = bookDao.listAll();
		return books;
	}
	
	public void createBook(Book book) {
		bookDao.create(book);
	}
	
	public Book findByTitle(String title) {
		Book book = bookDao.findByTitle(title);
		return book;
	}
	
	public long count() {
		// TODO Auto-generated method stub
		return bookDao.count();
	}
	
	public Book getBookById(int bookId) {
		return bookDao.get(bookId);
	}
	
	public void updateBook(Book book) {
		bookDao.update(book);
	}
	
	public void deleteBook(int bookId) {
		bookDao.delete(bookId);
	}
	
	public List<Category> listCategory(){
		List<Category> categories = categoryDao.listAll();
		return categories;
	}
	
	public List<Book> getBooksByCategory(int categoryId){
		List<Book> books = bookDao.getBooksByCategory(categoryId);
		return books;
	}
	
	public List<Book> listNewBooks(){
		List<Book> newBooks = bookDao.listNewBooks();
		return newBooks;
	}
	
	public List<Book> searchBook(String keyword){
		List<Book> searchedBooks = bookDao.searchBook(keyword);
		return searchedBooks;
	}
	
	public List<Book> listBestSellingBooks(){
		List<Book> listBestSellingBooks = bookDao.listBestSellingBooks();
		return listBestSellingBooks;
	}
	
	public List<Book> listMostFavoredBooks(){
		List<Book> listMostFavoredBooks = bookDao.listMostFavoredBooks();
		return listMostFavoredBooks;
	}
	
	public long countByCategory(int categoryId) {
		// TODO Auto-generated method stub
		return bookDao.countByCategory(categoryId);
	}
}
