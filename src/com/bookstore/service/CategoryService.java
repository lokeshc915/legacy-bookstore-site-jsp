package com.bookstore.service;

import java.util.List;

import org.hibernate.SessionFactory;

import com.bookstore.dao.CategoryDao;
import com.bookstore.dao.UserDao;
import com.bookstore.entity.Category;
import com.bookstore.entity.Users;

public class CategoryService {

	//private SessionFactory sessionFactory;
	private CategoryDao categoryDao;
	
	public CategoryService() {
		super();
		//this.categoryDao = new CategoryDao(sessionFactory);
		this.categoryDao = new CategoryDao();
	}
	
	public List<Category> listCategory() {
		List<Category> categories = categoryDao.listAll();
		return categories;
	}
	
	public void createCategory(Category category) {
		categoryDao.create(category);
	}
	
	public Category findByName(String name) {
		Category category = categoryDao.findByName(name);
		return category;
	}
	
	public long count() {
		// TODO Auto-generated method stub
		return categoryDao.count();
	}
	
	public Category getCategoryById(int categoryId) {
		return categoryDao.get(categoryId);
	}
	
	public void updateCategory(Category category) {
		categoryDao.update(category);
	}
	
	public void deleteCategory(int categoryId) {
		categoryDao.delete(categoryId);
	}
}
