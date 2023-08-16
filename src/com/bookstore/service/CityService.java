package com.bookstore.service;

import java.util.List;


import com.bookstore.dao.CityDao;
import com.bookstore.entity.Category;
import com.bookstore.entity.City;

public class CityService {

	private CityDao cityDao;
	
	public CityService() {
		super();
		this.cityDao = new CityDao();
	}
	
	public List<City> listCity() {
		List<City> cities = cityDao.listAll();
		return cities;
	}
	
	public long count() {
		// TODO Auto-generated method stub
		return cityDao.count();
	}
	
	public City getCityById(int cityId) {
		return cityDao.get(cityId);
	}
}
