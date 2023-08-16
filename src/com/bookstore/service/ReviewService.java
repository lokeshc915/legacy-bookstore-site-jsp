package com.bookstore.service;

import java.util.List;

import com.bookstore.dao.ReviewDao;
import com.bookstore.entity.Review;

public class ReviewService {

	private ReviewDao reviewDao;
	
	public ReviewService() {
		super();
		//this.userDao = new UserDao(sessionFactory);
		this.reviewDao = new ReviewDao();
	}
	
	public List<Review> listReview() {
		List<Review> reviews = reviewDao.listAll();
		return reviews;
	}
	
	public void createReview(Review review) {
		reviewDao.create(review);
	}
	
	public long count() {
		// TODO Auto-generated method stub
		return reviewDao.count();
	}
	
	public Review getReviewById(int reviewId) {
		return reviewDao.get(reviewId);
	}
	
	public void updateReview(Review review) {
		reviewDao.update(review);
	}
	
	public void deleteReview(int reviewId) {
		reviewDao.delete(reviewId);
	}
	
	public long countByCustomer(int customerId) {
		// TODO Auto-generated method stub
		return reviewDao.countByCustomer(customerId);
	}
	
	public Review findByReviewIdAndBookId(int customerId,int bookId) {
		return reviewDao.findByReviewIdAndBookId(customerId, bookId);
	}
	
	public List<Review> listMostRecent() {
		return reviewDao.listMostRecent();
	}
}
