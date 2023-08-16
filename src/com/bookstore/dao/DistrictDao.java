package com.bookstore.dao;

import java.util.List;

import com.bookstore.entity.District;

public class DistrictDao extends HibernateDao<District> implements GeneticDao<District>{

	
	public DistrictDao() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public long count() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public District create(District t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public District update(District t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public District get(Object id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<District> listAll() {
		// TODO Auto-generated method stub
		List<District> districtList = null;
		return districtList;
	}


	@Override
	public void delete(Object id) {
		// TODO Auto-generated method stub
		
	}
	
	public List<District> listAllByCity(int districtCityId) {
		List<District> districtList = super.findByNameQuery("District.findAll","cityId",districtCityId);
		//List<District> districtList = super.getListAll("District.findAll");
		return districtList;
	}
	

	public District getDistrictByCity(int districtId){
		List<District> districtByCity = super.findByNameQuery("District.findAllByCityId","districtId",districtId);
		
		if(districtByCity!=null && districtByCity.size() == 1) {
			return districtByCity.get(0);
		}
		
		return null;
	}
}
