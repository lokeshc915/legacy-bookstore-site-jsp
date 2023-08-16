package com.bookstore.dao;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.bookstore.entity.BookOrders;

public class OrdersDAO extends HibernateDao<BookOrders> implements GeneticDao<BookOrders> {

	
	public OrdersDAO() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public BookOrders create(BookOrders orders) {
		// TODO Auto-generated method stub
		orders.setOrderDate(new Date());
		orders.setPaymentMethod("Kapýda Ödeme");
		orders.setOrderStatus("Ýþleniyor");
		
		return super.create(orders);
	}

	@Override
	public BookOrders update(BookOrders order) {
		Session session = sessionFactory.openSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			
			session.update(order);
			session.flush();
			session.getTransaction().commit();
			session.close();
			
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			transaction.rollback();
		}
		 
		return order; 
	}
	

	@Override
	public BookOrders get(Object orderId) {
		// TODO Auto-generated method stub
		return super.get(BookOrders.class, orderId);
	}

	@Override
	public void delete(Object orderId) {
		// TODO Auto-generated method stub
		super.delete(BookOrders.class, orderId);
		
	}

	@Override
	public List<BookOrders> listAll() {
		// TODO Auto-generated method stub
		return super.getListAll("BookOrder.findAll");
	}

	@Override
	public long count() {
		// TODO Auto-generated method stub
		return super.getCountAll("BookOrder.countAll");
	}

	public BookOrders get(Integer orderId, Integer customerId) {
		Map<String, Object> parameters = new HashMap<>();
		parameters.put("orderId", orderId);
		parameters.put("customerId", customerId);
		
		List<BookOrders> result = super.findByNameQuery("BookOrder.findByIdAndCustomer", parameters );
		
		if (!result.isEmpty()) {
			return result.get(0);
		}
		return null;
	}
	
	public List<BookOrders> listByCustomer(Integer customerId) {
		return super.findByNameQuery("BookOrder.findByCustomer", 
				"customerId", customerId);
	}
	
	public List<BookOrders> listMostRecentSales() {
		return super.findByNameQuery("BookOrder.findAll", 0, 3);
	}

	public long countOrderDetailByBook(int bookId) {
		return super.getCountByNameQuery("DetailOrder.countByBook", "bookId", bookId);
	}
	
	public long countByCustomer(int customerId) {
		return super.getCountByNameQuery("BookOrder.countByCustomer", "customerId", customerId);
	}
	
	public void deleteBookFromOrderList(int bookId,int orderId) {
		// TODO Auto-generated method stub
		Map<String, Object> parameters = new HashMap<>();
		parameters.put("orderId", orderId);
		parameters.put("bookId", bookId);
		
		super.deleteBookFromOrderList("DetailOrder.deleteBookFromOrderList",parameters);
	}
}
