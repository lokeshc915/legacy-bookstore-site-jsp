package com.bookstore.dao;

import java.util.List;

import org.hibernate.SessionFactory;

import com.bookstore.entity.Category;

public class CategoryDao extends HibernateDao<Category> implements GeneticDao<Category>{

	public CategoryDao() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	/*public CategoryDao(SessionFactory sessionFactory) {
		super(sessionFactory);
		// TODO Auto-generated constructor stub
	}*/
	
	@Override
	public Category create(Category t) {
		// TODO Auto-generated method stub
		return super.create(t);
	}


	@Override
	public Category update(Category t) {
		// TODO Auto-generated method stub
		return super.update(t);
	}
	

	@Override
	public Category get(Object id) {
		// TODO Auto-generated method stub
		return super.get(Category.class, id);
	}

	@Override
	public void delete(Object id) {
		// TODO Auto-generated method stub
		super.delete(Category.class, id);
	}

	@Override
	public List<Category> listAll() {
		// TODO Auto-generated method stub
		return super.getListAll("Category.findAll");
	}

	@Override
	public long count() {
		// TODO Auto-generated method stub
		return super.getCountAll("Category.countAll");
	}

	public Category findByName(String categoryName) {
		List<Category> categoryByName = super.findByNameQuery("Category.findByName","name",categoryName);
		
		if(categoryByName != null && categoryByName.size() == 1) {
			return categoryByName.get(0);
		}
		
		return null;
	}
}
