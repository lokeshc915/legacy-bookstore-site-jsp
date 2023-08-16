package com.bookstore.entity;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Embeddable
public class DetailOrderId implements Serializable{

	@ManyToOne(fetch = FetchType.EAGER,cascade=CascadeType.ALL)
	@JoinColumn(name = "BOOK_ID", insertable = false, updatable = false, nullable = false)
	private Book book;
	
	@ManyToOne(fetch = FetchType.EAGER,cascade=CascadeType.ALL)
	@JoinColumn(name = "ORDER_ID", insertable = false, updatable = false, nullable = false)
	private BookOrders bookorders;
	
	public Book getBook() {
		return book;
	}
	public void setBook(Book book) {
		this.book = book;
	}
	
	public BookOrders getBookorders() {
		return bookorders;
	}
	public void setBookorders(BookOrders bookorders) {
		this.bookorders = bookorders;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((book == null) ? 0 : book.hashCode());
		result = prime * result + ((bookorders == null) ? 0 : bookorders.hashCode());
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DetailOrderId other = (DetailOrderId) obj;
		if (book == null) {
			if (other.book != null)
				return false;
		} else if (!book.equals(other.book))
			return false;
		if (bookorders == null) {
			if (other.bookorders != null)
				return false;
		} else if (!bookorders.equals(other.bookorders))
			return false;
		return true;
	}
	

}
