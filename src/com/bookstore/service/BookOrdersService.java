package com.bookstore.service;

import java.util.List;

import com.bookstore.dao.OrdersDAO;
import com.bookstore.entity.BookOrders;

public class BookOrdersService {

	private OrdersDAO ordersDao;

	public BookOrdersService() {
		super();
		ordersDao = new OrdersDAO();
	}
	
	public List<BookOrders> listBookOrders() {
		List<BookOrders> bookOrders = ordersDao.listAll();
		return bookOrders;
	}
	
	public void createBookOrder(BookOrders bookOrder) {
		ordersDao.create(bookOrder);
	}
	
	public long count() {
		// TODO Auto-generated method stub
		return ordersDao.count();
	}
	
	public BookOrders getBookOrdersById(int orderId) {
		return ordersDao.get(orderId);
	}
	
	public void updateBookOrder(BookOrders bookOrder) {
		ordersDao.update(bookOrder);
	}
	
	
	public void deleteBookOrder(int orderId) {
		ordersDao.delete(orderId);
	}
	
	public BookOrders get(Integer orderId, Integer customerId) {
		BookOrders result =  ordersDao.get(orderId, customerId);
		
		if (result !=null) {
			System.out.println("BookOrders get |result not null");
			return result;
		}
		return null;
	}
	
	public List<BookOrders> listByCustomer(Integer customerId) {
		return ordersDao.listByCustomer(customerId);
	}
	
	public List<BookOrders> listMostRecentSales() {
		return ordersDao.listMostRecentSales();
	}
		
	public long countOrderDetailByBook(int bookId) {
		return ordersDao.countOrderDetailByBook(bookId);
	}
	
	public long countByCustomer(int customerId) {
		return ordersDao.countByCustomer(customerId);
	}
	
	public void deleteBookFromOrderList(int bookId,int orderId) {
		ordersDao.deleteBookFromOrderList(bookId, orderId);
	}
}
