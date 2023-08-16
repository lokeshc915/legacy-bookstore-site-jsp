package com.bookstore.dao;

import java.util.List;

import com.bookstore.entity.City;

public class CityDao extends HibernateDao<City> implements GeneticDao<City>{

	
	public CityDao() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public City get(Object id) {
		// TODO Auto-generated method stub
		return super.get(City.class, id);
	}

	@Override
	public void delete(Object id) {
		// TODO Auto-generated method stub
		super.delete(City.class, id);
	}

	@Override
	public List<City> listAll() {
		// TODO Auto-generated method stub
		return super.getListAll("City.findAll");
	}

	@Override
	public long count() {
		// TODO Auto-generated method stub
		return super.getCountAll("City.countAll");
	}

	
}
