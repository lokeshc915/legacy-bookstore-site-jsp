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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="CUSTOMER",catalog = "JSPPROJECTDATABASE")
@NamedQueries({
	@NamedQuery(name = "Customer.findAll",query = "SELECT c FROM Customer c ORDER BY c.registerDate DESC"),
	@NamedQuery(name = "Customer.countAll",query = "SELECT COUNT(*) FROM Customer c"),
	@NamedQuery(name = "Customer.findByEmail",query = "SELECT c FROM Customer c WHERE c.email =:email"),
	@NamedQuery(name = "Customer.checkLogin",query = "SELECT c FROM Customer c WHERE c.email =:email AND c.password=:password")
})
public class Customer implements Serializable{

	@Id
	@SequenceGenerator(name="CUSTOMER_SEQ", sequenceName="CUSTOMER_SEQ", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="CUSTOMER_SEQ")
	@Column(name="CUSTOMER_ID", nullable = false)
	private int id;
	
	@Column(name="NAME")
	private String name;
	
	@Column(name="SURNAME")
	private String surname;
	
	@Column(name="EMAIL")
	private String email;
	
	@Column(name="ADDRESS")
	private String address;
	
	//@Column(name="CITY")
	//private String city;
	
	//@Column(name="STATE")
	//private String state;
	
	@Column(name="PHONE_NUMBER")
	private String phoneNumber;
	
	@Column(name="ZIP_CODE")
	private String zipCode;
	
	@Column(name="PASSWORD")
	private String password;
	
	@Column(name="REGISTER_DATE")
	private Date registerDate;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "customerReview")
	private Set<Review> review = new HashSet<Review>();
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "customerOrders")
	private Set<BookOrders> orders = new HashSet<BookOrders>();
	
	@OneToOne(fetch = FetchType.EAGER,cascade = CascadeType.MERGE)
	@JoinColumn(name = "CITY")
	private City city;
	
	@OneToOne(fetch = FetchType.EAGER,cascade = CascadeType.MERGE)
	@JoinColumn(name = "DISTRICT")
	private District district;

	public Customer() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Customer(String name, String surname, String email, String address, City city, District district,
			String phoneNumber, String zipCode, String password, Date registerDate, Set<Review> review,
			Set<BookOrders> orders) {
		super();
		this.name = name;
		this.surname = surname;
		this.email = email;
		this.address = address;
		this.city = city;
		this.district = district;
		this.phoneNumber = phoneNumber;
		this.zipCode = zipCode;
		this.password = password;
		this.registerDate = registerDate;
		this.review = review;
		this.orders = orders;
	}
	

	public Customer(int id, String name, String surname, String email, String address, String phoneNumber,
			String zipCode, String password, Date registerDate, Set<Review> review, Set<BookOrders> orders, City city,
			District district) {
		super();
		this.id = id;
		this.name = name;
		this.surname = surname;
		this.email = email;
		this.address = address;
		this.phoneNumber = phoneNumber;
		this.zipCode = zipCode;
		this.password = password;
		this.registerDate = registerDate;
		this.review = review;
		this.orders = orders;
		this.city = city;
		this.district = district;
	}
	
	public Customer(int id, String name, String surname, String email, String address, String phoneNumber,
	String zipCode, String password,City city,
	District district) {
		this.id = id;
		this.name = name;
		this.surname = surname;
		this.email = email;
		this.address = address;
		this.phoneNumber = phoneNumber;
		this.zipCode = zipCode;
		this.password = password;
		this.city = city;
		this.district = district;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public City getCity() {
		return city;
	}

	public void setCity(City city) {
		this.city = city;
	}

	public District getDistrict() {
		return district;
	}

	public void setDistrict(District district) {
		this.district = district;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getRegisterDate() {
		return registerDate;
	}

	public void setRegisterDate(Date registerDate) {
		this.registerDate = registerDate;
	}

	public Set<Review> getReview() {
		return review;
	}

	public void setReview(Set<Review> review) {
		this.review = review;
	}

	public Set<BookOrders> getOrders() {
		return orders;
	}

	public void setOrders(Set<BookOrders> orders) {
		this.orders = orders;
	}
	
	public String getFullName() {
		return this.name + " " + this.surname;
	}
}
