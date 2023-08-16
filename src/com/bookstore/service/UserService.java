package com.bookstore.service;

import java.util.List;

import org.hibernate.SessionFactory;

import com.bookstore.dao.UserDao;
import com.bookstore.entity.Users;
import com.bookstore.util.HibernateUtil;

public class UserService {

	//private SessionFactory sessionFactory;
	private UserDao userDao;
	
	public UserService() {
		super();
		//this.userDao = new UserDao(sessionFactory);
		this.userDao = new UserDao();
	}
	
	public List<Users> listUser() {
		List<Users> users = userDao.listAll();
		return users;
	}
	
	public void createUser(Users user) {
		userDao.create(user);
	}
	
	public Users findByEmail(String email) {
		Users userByEmail = userDao.findByEmail(email);
		return userByEmail;
	}
	
	public long count() {
		// TODO Auto-generated method stub
		return userDao.count();
	}
	
	public Users getUsersById(int userId) {
		return userDao.get(userId);
	}
	
	public void updateUser(Users user) {
		userDao.update(user);
	}
	
	public void deleteUser(int userId) {
		userDao.delete(userId);
	}
	
	public boolean checkUserLogin(String email,String password) {
		return userDao.checkUser(email, password);
	}
	
	public Users checkUserLoginwithEmailandPassword(String email,String password) {
		return userDao.checkUserwithEmailandPassword(email, password);
	}
}
