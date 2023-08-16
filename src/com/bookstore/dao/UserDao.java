package com.bookstore.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.SessionFactory;

import com.bookstore.entity.Users;
import com.bookstore.util.EncryptUtil;

public class UserDao extends HibernateDao<Users> implements GeneticDao<Users>{

	public UserDao() {
		// TODO Auto-generated constructor stub
		super();
	}
	
	/*public UserDao(SessionFactory sessionFactory) {
		// TODO Auto-generated constructor stub
		super(sessionFactory);
	}*/
	
	@Override
	public Users create(Users t) {
		// TODO Auto-generated method stub
		return super.create(t);
	}
	
	@Override
	public Users update(Users t) {
		// TODO Auto-generated method stub
		return super.update(t);
		
	}
	@Override
	public Users get(Object id) {
		// TODO Auto-generated method stub
		return super.get(Users.class, id);
	}

	@Override
	public void delete(Object id) {
		// TODO Auto-generated method stub
		super.delete(Users.class, id);
	}

	@Override
	public List<Users> listAll() {
		// TODO Auto-generated method stub
		return super.getListAll("Users.findAll");
	}

	@Override
	public long count() {
		// TODO Auto-generated method stub
		return super.getCountAll("Users.countAll");
	}
	
	public Users findByEmail(String email) {
		List<Users> userListByEmail = super.findByNameQuery("Users.findByEmail","email",email);
		
		if(userListByEmail != null && userListByEmail.size() == 1) {
			return userListByEmail.get(0);
		}
		
		return null;
	}
	
	public boolean checkUser(String email,String password) {
		
		Map<String,Object> parameters = new HashMap<>();
		parameters.put("email", email);
		String passwordForMd5 = EncryptUtil.md5(password);
		parameters.put("password", passwordForMd5);
		
		List<Users> listUsers = super.findByNameQuery("Users.checkLogin", parameters);
		if(listUsers.size()==1) {
			return true;
		}
		return false;
		
	}
	
	public Users checkUserwithEmailandPassword(String email,String password) {
		
		Map<String,Object> parameters = new HashMap<>();
		parameters.put("email", email);
		String passwordForMd5 = EncryptUtil.md5(password);
		parameters.put("password", passwordForMd5);
		
		List<Users> listUsers = super.findByNameQuery("Users.checkLogin", parameters);
		if(listUsers.size()==1) {
			return listUsers.get(0);
		}
		return null;
		
	}
}
