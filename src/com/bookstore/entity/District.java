package com.bookstore.entity;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "DISTRICT", catalog = "JSPPROJECTDATABASE")
@NamedQueries({
		@NamedQuery(name = "District.findAllByCityId", 
				    query = "SELECT d FROM District d WHERE d.id=:districtId"),
		@NamedQuery(name = "District.findAll", 
					query = "SELECT d FROM District d WHERE d.districtCity.id =: cityId")
})
public class District implements Serializable {

	@Id
	@SequenceGenerator(name = "STATE_SEQ", sequenceName = "STATE_SEQ", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "STATE_SEQ")
	@Column(name = "ID", nullable = false)
	private int id;

	@ManyToOne(fetch = FetchType.EAGER) // java.lang.IllegalStateException: Multiple representations of the same entity -> cascade = CascadeType SÝL
	@JoinColumn(name = "CITY_ID")
	private City districtCity;

	@Column(name = "DISTRICT_NAME")
	private String districtName;

	@OneToOne(fetch = FetchType.EAGER, mappedBy = "district",cascade = CascadeType.MERGE)
	private Customer customer;

	public District() {
		super();
		// TODO Auto-generated constructor stub
	}

	public District(int id, City districtCity, String districtName) {
		super();
		this.id = id;
		this.districtCity = districtCity;
		this.districtName = districtName;
	}

	public District(City districtCity, String districtName) {
		super();
		this.districtCity = districtCity;
		this.districtName = districtName;
	}

	public District(String districtName) {
		super();
		this.districtName = districtName;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public City getCity() {
		return districtCity;
	}

	public void setCity(City districtCity) {
		this.districtCity = districtCity;
	}

	public String getDistrictName() {
		return districtName;
	}

	public void setDistrictName(String districtName) {
		this.districtName = districtName;
	}

	public City getDistrictCity() {
		return districtCity;
	}

	public void setDistrictCity(City districtCity) {
		this.districtCity = districtCity;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	@Override
	public String toString() {
		return "State [id=" + id + ", city=" + districtCity + ", districtName=" + districtName + "]";
	}

}
