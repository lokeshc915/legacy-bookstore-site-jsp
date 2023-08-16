package com.bookstore.dao;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.bookstore.entity.Book;
import com.bookstore.entity.Customer;
import com.bookstore.entity.Users;
import com.bookstore.util.EncryptUtil;

public class CustomerDao extends HibernateDao<Customer> implements GeneticDao<Customer>{

	public CustomerDao() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public Customer create(Customer customer) {
		// TODO Auto-generated method stub
		customer.setRegisterDate(new Date());
		return super.create(customer);
	}
	
	
	@Override
	public Customer update(Customer t) {
		// TODO Auto-generated method stub
		return super.update(t);
	}

	@Override
	public Customer get(Object id) {
		// TODO Auto-generated method stub
		return super.get(Customer.class, id);
	}

	@Override
	public void delete(Object id) {
		// TODO Auto-generated method stub
		super.delete(Customer.class, id);
	}

	@Override
	public List<Customer> listAll() {
		// TODO Auto-generated method stub
		return super.getListAll("Customer.findAll");
	}

	@Override
	public long count() {
		// TODO Auto-generated method stub
		return super.getCountAll("Customer.countAll");
	}

	public Customer findByEmail(String email) {
		List<Customer> customerByEmail = super.findByNameQuery("Customer.findByEmail","email",email);
		
		if(customerByEmail != null && customerByEmail.size() == 1) {
			return customerByEmail.get(0);
		}
		
		return null;
	}
	
	public boolean checkUser(String email,String password) {
		
		Map<String,Object> parameters = new HashMap<>();
		parameters.put("email", email);
		String passwordForMd5 = EncryptUtil.md5(password);
		parameters.put("password", passwordForMd5);
		//parameters.put("password", password);
		
		List<Customer> listCustomers = super.findByNameQuery("Customer.checkLogin", parameters);
		if(listCustomers.size()==1) {
			return true;
		}
		return false;
		
	}
	
	public Customer checkUserwithEmailandPassword(String email,String password) {
		
		Map<String,Object> parameters = new HashMap<>();
		parameters.put("email", email);
		String passwordForMd5 = EncryptUtil.md5(password);
		parameters.put("password", passwordForMd5);
		//parameters.put("password", password);
		
		List<Customer> listCustomers = super.findByNameQuery("Customer.checkLogin", parameters);
		if(listCustomers.size()==1) {
			return listCustomers.get(0);
		}
		return null;
		
	}
}
