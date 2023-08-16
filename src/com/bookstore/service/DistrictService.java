package com.bookstore.service;

import java.util.List;

import org.hibernate.SessionFactory;

import com.bookstore.dao.DistrictDao;
import com.bookstore.entity.City;
import com.bookstore.entity.Customer;
import com.bookstore.entity.District;

public class DistrictService {

	private DistrictDao districtDao;
	
	public DistrictService() {
		super();
		this.districtDao = new DistrictDao();
	}
	
	public District getDistrictByCity(int districtId){
		District districtByCity = districtDao.getDistrictByCity(districtId);
		return districtByCity;
	}
	
	
	public List<District> listDistrict(int districtCityId) {
		List<District> districtList = districtDao.listAllByCity(districtCityId);
		return districtList;
	}
}
