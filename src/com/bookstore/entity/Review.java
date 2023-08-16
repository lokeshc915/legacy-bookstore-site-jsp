package com.bookstore.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="REVIEW",catalog = "JSPPROJECTDATABASE")
@NamedQueries({
	@NamedQuery(name = "Review.findAll",query = "SELECT r FROM Review r ORDER BY r.id ASC"),
	@NamedQuery(name = "Review.countAll",query = "SELECT COUNT(*) FROM Review r"),
	@NamedQuery(name = "Review.countByCustomer",
	            query = "SELECT COUNT(r.id) FROM Review r WHERE r.customerReview.id =:customerId"),
	@NamedQuery(name = "Review.findByCustomerIdAndBookId",
    			query = "SELECT r FROM Review r WHERE r.customerReview.id =:customerId AND r.bookReview.id =:bookId"),
	@NamedQuery(name = "Review.mostFavoredBooks",
				query = "SELECT r.bookReview.id, COUNT(r.bookReview.id) AS ReviewCount, AVG(r.rating) as AvgRating FROM Review r "
				        + "LEFT JOIN Book b ON r.bookReview.id = b.id " 
						+ "GROUP BY r.bookReview.id HAVING AVG(r.rating) >= 2.0 "
						+ "ORDER BY ReviewCount DESC, AvgRating DESC")
})
public class Review implements Serializable{

	@Id
	@SequenceGenerator(name="REVIEW_SEQ", sequenceName="REVIEW_SEQ", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="REVIEW_SEQ")
	@Column(name="REVIEW_ID", nullable = false)
	private int id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "BOOK_ID", nullable = false)
	private Book bookReview;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CUSTOMER_ID", nullable = false)
	private Customer customerReview;
	
	@Column(name="RATING")
	private int rating;
	
	@Column(name="HEADLINE")
	private String headline;
	
	@Column(name="REVIEW_COMMENT")
	private String reviewComment;
	
	@Column(name="REVIEW_DATE")
	private Date reviewDate;
	
	@Transient
	private String ratingStar;

	public Review() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Review(Book bookReview, Customer customerReview, int rating, String headline, String reviewComment,
			Date reviewDate) {
		super();
		this.bookReview = bookReview;
		this.customerReview = customerReview;
		this.rating = rating;
		this.headline = headline;
		this.reviewComment = reviewComment;
		this.reviewDate = reviewDate;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Book getBookReview() {
		return bookReview;
	}

	public void setBookReview(Book bookReview) {
		this.bookReview = bookReview;
	}

	public Customer getCustomerReview() {
		return customerReview;
	}

	public void setCustomerReview(Customer customerReview) {
		this.customerReview = customerReview;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	public String getHeadline() {
		return headline;
	}

	public void setHeadline(String headline) {
		this.headline = headline;
	}

	public String getReviewComment() {
		return reviewComment;
	}

	public void setReviewComment(String reviewComment) {
		this.reviewComment = reviewComment;
	}

	public Date getReviewDate() {
		return reviewDate;
	}

	public void setReviewDate(Date reviewDate) {
		this.reviewDate = reviewDate;
	}
	
	public String getRatingStar() {
		ratingStar = "";
		
		int numberOfStarsOn = (int) rating;  // 2,5 -> 2
		
		for(int i=0;i<numberOfStarsOn;i++) {
			ratingStar += "on,";  // 2 times on
		}
		

		int next = numberOfStarsOn + 1;  // 2 + 1 = 3
		
		/*if(averageRating > numberOfStarsOn) {  // 2,5 > 2
			averageRatingString += "half,";  // one times half
			next++;  // 3 + 1 = 4
		}*/
		
		for(int j=next;j<=5;j++) {  // 4, 5 
			ratingStar += "off,";  //  putting off into the 4 th and 5th star element
		}
		
		ratingStar = ratingStar.substring(0, ratingStar.length()-1);
		
		return ratingStar;
	}
	
}
