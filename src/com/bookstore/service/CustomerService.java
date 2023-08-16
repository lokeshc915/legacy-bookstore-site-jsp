package com.bookstore.service;

import java.util.List;

import org.apache.commons.lang3.RandomStringUtils;

import com.bookstore.dao.CustomerDao;
import com.bookstore.entity.Customer;
import com.bookstore.entity.Users;

public class CustomerService {

	private CustomerDao customerDao;
	
	public CustomerService() {
		super();
		//this.bookDao = new BookDao(sessionFactory);
		//this.categoryDao = new CategoryDao(sessionFactory);
		this.customerDao = new CustomerDao();
	}
	
	public List<Customer> listCustomer() {
		List<Customer> customers = customerDao.listAll();
		return customers;
	}
	
	public void createBook(Customer customer) {
		customerDao.create(customer);
	}
	
	public long count() {
		// TODO Auto-generated method stub
		return customerDao.count();
	}
	
	public Customer getCustomerById(int customerId) {
		return customerDao.get(customerId);
	}
	
	public void updateBook(Customer customer) {
		customerDao.update(customer);
	}
	
	public void deleteBook(int customerId) {
		customerDao.delete(customerId);
	}
	
	public Customer findByEmail(String email) {
		Customer customerByEmail = customerDao.findByEmail(email);
		return customerByEmail;
	}
	
	public boolean checkUserLogin(String email,String password) {
		return customerDao.checkUser(email, password);
	}
	
	public Customer checkUserLoginwithEmailandPassword(String email,String password) {
		return customerDao.checkUserwithEmailandPassword(email, password);
	}
	
	public String resetCustomerPassword(String email) {
	    Customer customer = customerDao.findByEmail(email);	     
	    String randomPassword = RandomStringUtils.randomAlphanumeric(10);	     
	    customer.setPassword(randomPassword);
	    customerDao.update(customer);	     
	    return randomPassword;
	}
}
