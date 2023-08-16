package com.bookstore.entity;

import java.io.Serializable;

import javax.persistence.AssociationOverride;
import javax.persistence.AssociationOverrides;
import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
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
@Table(name = "DETAILORDER", catalog = "JSPPROJECTDATABASE")
@NamedQueries({
	@NamedQuery(name = "DetailOrder.bestSelling", 
			query = "SELECT do.book.id,SUM(do.quantity) FROM DetailOrder do LEFT JOIN Book b ON do.book.id = b.id GROUP BY do.book.id "
					+ "ORDER BY SUM(do.quantity) DESC"),
	@NamedQuery(name = "DetailOrder.countByBook",
				query = "SELECT COUNT(*) FROM DetailOrder do WHERE do.book.id =:bookId"),
	@NamedQuery(name = "DetailOrder.deleteBookFromOrderList",
				query = "DELETE FROM DetailOrder do WHERE do.book.id =:bookId AND do.bookorders.id =:orderId")
	
})
public class DetailOrder implements Serializable{

	@EmbeddedId
	@AttributeOverrides({ @AttributeOverride(name = "ORDER_ID", column = @Column(name = "ORDER_ID", nullable = false)),
		@AttributeOverride(name = "BOOK_ID", column = @Column(name = "BOOK_ID", nullable = false))})
	private DetailOrderId pk = new DetailOrderId();
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "BOOK_ID", insertable = false, updatable = false, nullable = false)
	private Book book;
	
	@ManyToOne(fetch = FetchType.EAGER,cascade=CascadeType.ALL)
	@JoinColumn(name = "ORDER_ID", insertable = false, updatable = false, nullable = false)
	private BookOrders bookorders;
	
	@Column(name="QUANTITY")
	private int quantity;
	
	@Column(name="SUBTOTAL")
	private float subTotal;
	
	public DetailOrder() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public DetailOrderId getPk() {
		return pk;
	}

	public void setPk(DetailOrderId pk) {
		this.pk = pk;
	}
	
	@Transient
	public Book getBook() {
		return getPk().getBook();
	}

	public void setBook(Book book) {
		getPk().setBook(book);
	}

	@Transient
	public BookOrders getOrders() {
		return getPk().getBookorders();
	}

	public void setOrders(BookOrders orders) {
		getPk().setBookorders(orders);
	}
	

	/*public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	public BookOrders getOrders() {
		return orders;
	}

	public void setOrders(BookOrders orders) {
		this.orders = orders;
	}*/
	
	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public float getSubTotal() {
		return subTotal;
	}

	public void setSubTotal(float subTotal) {
		this.subTotal = subTotal;
	}

}
