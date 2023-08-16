package com.bookstore.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.bookstore.entity.Book;
import com.bookstore.entity.Category;

public class BookDao extends HibernateDao<Book> implements GeneticDao<Book>{

	public BookDao() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	/*public BookDao(SessionFactory sessionFactory) {
		super(sessionFactory);
		// TODO Auto-generated constructor stub
	}*/
	
	@Override
	public Book create(Book book) {
		// TODO Auto-generated method stub
		book.setLastUpdatedDate(new Date());
		return super.create(book);
	}

	

	@Override
	public Book update(Book book) {
		// TODO Auto-generated method stub
		book.setLastUpdatedDate(new Date());
		return super.update(book);
	}

	@Override
	public Book get(Object id) {
		// TODO Auto-generated method stub
		return super.get(Book.class, id);
	}


	@Override
	public List<Book> listAll() {
		// TODO Auto-generated method stub
		return super.getListAll("Book.findAll");
	}

	@Override
	public long count() {
		// TODO Auto-generated method stub
		return super.getCountAll("Book.countAll");
	}

	@Override
	public void delete(Object id) {
		// TODO Auto-generated method stub
		super.delete(Book.class, id);
	}
	
	public Book findByTitle(String bookTitle) {
		List<Book> bookByTitle = super.findByNameQuery("Book.findByTitle","title",bookTitle);
		
		if(bookByTitle != null && bookByTitle.size() == 1) {
			return bookByTitle.get(0);
		}
		
		return null;
	}
	
	public List<Book> getBooksByCategory(int categoryId){
		List<Book> booksByCategory = super.findByNameQuery("Book.listByCategory","categoryId",categoryId);
		return booksByCategory;
	}
	
	public List<Book> listNewBooks(){
		List<Book> newBooks=super.findByNameQuery("Book.listNewBook",0,5);
		return newBooks;
	}

	
	public List<Book> searchBook(String keyword){
		List<Book> searchedBooks = super.findByNameQuery("Book.searchBook","keyword",keyword);
		return searchedBooks;
	}
	
	public long countByCategory(int categoryId) {
		return super.getCountByNameQuery("Book.countByCategory", "categoryId",categoryId);
	}
	
	public List<Book> listBestSellingBooks() {
		//return super.findByNameQuery("DetailOrder.bestSelling", 0, 4);
		
		List<Book> listBestSellingBooks = new ArrayList<>();
		
		List<Object[]> result = super.findByNameQueryObjects("DetailOrder.bestSelling", 0, 4);
		
		if (!result.isEmpty()) {
			for (Object[] elements : result) {
				int bookId = (int) elements[0];
				Book book = get(bookId);
				listBestSellingBooks.add(book);
			}
		} 
		
		return listBestSellingBooks;
	}	
	
	public List<Book> listMostFavoredBooks() {
		List<Book> mostFavoredBooks = new ArrayList<>();
		
		List<Object[]> result = super.findByNameQueryObjects("Review.mostFavoredBooks", 0, 4);
		
		System.out.println("listMostFavoredBooks | result size :" + result.size());
		
		if (!result.isEmpty()) {
			for (Object[] elements : result) {
				int bookId = (int) elements[0];
				Book book = get(bookId);
				mostFavoredBooks.add(book);
			}
		} 
		
		return mostFavoredBooks;
	}
	
}
