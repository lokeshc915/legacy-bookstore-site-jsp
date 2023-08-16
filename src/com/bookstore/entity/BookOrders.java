package com.bookstore.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

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
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="BOOKORDERS",catalog = "JSPPROJECTDATABASE")
@NamedQueries({
	@NamedQuery(name = "BookOrder.findAll", query = "SELECT bo FROM BookOrders bo ORDER BY bo.orderDate DESC"),
	@NamedQuery(name = "BookOrder.countAll", query = "SELECT COUNT(*) FROM BookOrders"),
	@NamedQuery(name = "BookOrder.findByCustomer", 
		query = "SELECT bo FROM BookOrders bo WHERE bo.customerOrders.id =:customerId ORDER BY bo.orderDate DESC"),
	@NamedQuery(name = "BookOrder.findByIdAndCustomer",
		query = "SELECT bo FROM BookOrders bo WHERE bo.id =:orderId AND bo.customerOrders.id =:customerId"),
	@NamedQuery(name = "BookOrder.countByCustomer",
		query = "SELECT COUNT(bo.id) FROM BookOrders bo WHERE bo.customerOrders.id =:customerId")
})
public class BookOrders implements Serializable{

	@Id
	@SequenceGenerator(name="ORDERS_SEQ", sequenceName="ORDERS_SEQ", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="ORDERS_SEQ")
	@Column(name="ORDER_ID", nullable = false)
	private int id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CUSTOMER_ID", nullable = false)
	private Customer customerOrders;
	
	@Column(name="ORDER_DATE")
	private Date orderDate;
	
	@Column(name="SHIPPING_ADDRESS")
	private String shippingAddress;
	
	@Column(name="RECIPIENT_NAME")
	private String recipentName;
	
	@Column(name="RECIPIENT_PHONE")
	private String recipentPhone;
	
	@Column(name="PAYMENT_METHOD")
	private String paymentMethod;
	
	@Column(name="ORDER_TOTAL")
	private float orderTotal;
	
	@Column(name="ORDER_STATUS")
	private String orderStatus;
	
	@OneToMany(fetch = FetchType.EAGER,mappedBy = "pk.bookorders", cascade=CascadeType.ALL)
	private Set<DetailOrder> orderDetails = new HashSet<DetailOrder>();
	
	@Transient
	private int allBookCopies;

	public BookOrders() {
		super();
		// TODO Auto-generated constructor stub
	}

	public BookOrders(Customer customer, Date orderDate, String shippingAddress, String recipentName, String recipentPhone,
			String paymentMethod, float orderTotal, String orderStatus) {
		super();
		this.customerOrders = customer;
		this.orderDate = orderDate;
		this.shippingAddress = shippingAddress;
		this.recipentName = recipentName;
		this.recipentPhone = recipentPhone;
		this.paymentMethod = paymentMethod;
		this.orderTotal = orderTotal;
		this.orderStatus = orderStatus;
	}
	
	
	public BookOrders(Customer customerOrders, Date orderDate, String shippingAddress, String recipentName,
			String recipentPhone, String paymentMethod, float orderTotal, String orderStatus,
			Set<DetailOrder> orderDetails) {
		super();
		this.customerOrders = customerOrders;
		this.orderDate = orderDate;
		this.shippingAddress = shippingAddress;
		this.recipentName = recipentName;
		this.recipentPhone = recipentPhone;
		this.paymentMethod = paymentMethod;
		this.orderTotal = orderTotal;
		this.orderStatus = orderStatus;
		this.orderDetails = orderDetails;
	}

	public Customer getCustomerOrders() {
		return customerOrders;
	}

	public void setCustomerOrders(Customer customerOrders) {
		this.customerOrders = customerOrders;
	}

	public Set<DetailOrder> getOrderDetails() {
		return orderDetails;
	}

	public void setOrderDetails(Set<DetailOrder> orderDetails) {
		this.orderDetails = orderDetails;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Customer getCustomer() {
		return customerOrders;
	}

	public void setCustomer(Customer customer) {
		this.customerOrders = customer;
	}

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public String getShippingAddress() {
		return shippingAddress;
	}

	public void setShippingAddress(String shippingAddress) {
		this.shippingAddress = shippingAddress;
	}

	public String getRecipentName() {
		return recipentName;
	}

	public void setRecipentName(String recipentName) {
		this.recipentName = recipentName;
	}

	public String getRecipentPhone() {
		return recipentPhone;
	}

	public void setRecipentPhone(String recipentPhone) {
		this.recipentPhone = recipentPhone;
	}

	public String getPaymentMethod() {
		return paymentMethod;
	}

	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

	public float getOrderTotal() {
		return orderTotal;
	}

	public void setOrderTotal(float orderTotal) {
		this.orderTotal = orderTotal;
	}

	public String getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}
	
	
	public int getAllBookCopies() {
		allBookCopies = 0;
		
		for (DetailOrder detailOrder : orderDetails) {
			allBookCopies += detailOrder.getQuantity();
		}
		
		return allBookCopies;

	}


	@Override
	public String toString() {
		return "Orders [id=" + id + ", customerOrders=" + customerOrders + ", orderDate=" + orderDate
				+ ", shippingAddress=" + shippingAddress + ", recipentName=" + recipentName + ", recipentPhone="
				+ recipentPhone + ", paymentMethod=" + paymentMethod + ", orderTotal=" + orderTotal + ", orderStatus="
				+ orderStatus + ", orderDetails=" + orderDetails + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
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
		BookOrders other = (BookOrders) obj;
		if (id != other.id)
			return false;
		return true;
	}
	
	
	
	
}
