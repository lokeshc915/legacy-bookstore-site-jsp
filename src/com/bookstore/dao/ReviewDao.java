package com.bookstore.dao;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.bookstore.entity.Customer;
import com.bookstore.entity.Review;
import com.bookstore.util.EncryptUtil;

public class ReviewDao extends HibernateDao<Review> implements GeneticDao<Review>{

	public ReviewDao() {
		// TODO Auto-generated constructor stub
		super();
	}
	
	@Override
	public Review create(Review r) {
		// TODO Auto-generated method stub
		r.setReviewDate(new Date());
		return super.create(r);
	}
	
	@Override
	public Review get(Object id) {
		// TODO Auto-generated method stub
		return super.get(Review.class, id);
	}

	@Override
	public void delete(Object id) {
		// TODO Auto-generated method stub
		super.delete(Review.class, id);
	}

	@Override
	public List<Review> listAll() {
		// TODO Auto-generated method stub
		return super.getListAll("Review.findAll");
	}

	@Override
	public long count() {
		// TODO Auto-generated method stub
		return super.getCountAll("Review.countAll");
	}
	
	public long countByCustomer(int customerId) {
		return super.getCountByNameQuery("Review.countByCustomer", "customerId", customerId);
	}
	
	public Review findByReviewIdAndBookId(int customerId,int bookId) {
		
		Map<String,Object> parameters = new HashMap<>();
		parameters.put("customerId", customerId);
		parameters.put("bookId", bookId);
		
		List<Review> listReview = super.findByNameQuery("Review.findByCustomerIdAndBookId", parameters);
		if(listReview.size()==1) {
			return listReview.get(0);
		}
		
		return null;
	}
	
	public List<Review> listMostRecent() {
		return super.findByNameQuery("Review.findAll", 0, 3);
	}

}
