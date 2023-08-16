package com.bookstore.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="CITY",catalog = "JSPPROJECTDATABASE")
@NamedQueries({
	@NamedQuery(name = "City.findAll",query = "SELECT c FROM City c ORDER BY c.id DESC"),
	@NamedQuery(name = "City.countAll",query = "SELECT COUNT(*) FROM City c")
})
public class City implements Serializable{

	@Id
	@SequenceGenerator(name="CITY_SEQ", sequenceName="CITY_SEQ", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="CITY_SEQ")
	@Column(name="ID", nullable = false)
	private int id;
	
	@Column(name="CITY_NAME")
	private String cityName;
	
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "districtCity") // java.lang.IllegalStateException: Multiple representations of the same entity -> cascade = CascadeType SÝL
	private Set<District> districts = new HashSet<District>();

	@OneToOne(fetch = FetchType.EAGER, mappedBy = "city",cascade = CascadeType.MERGE)
	private Customer customer;
	
	public City() {
		super();
		// TODO Auto-generated constructor stub
	}

	public City(int id, String cityName) {
		super();
		this.id = id;
		this.cityName = cityName;
	}

	public City(String cityName) {
		super();
		this.cityName = cityName;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public Set<District> getDistricts() {
		return districts;
	}

	public void setDistricts(Set<District> districts) {
		this.districts = districts;
	}
	
	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	@Override
	public String toString() {
		return "City [id=" + id + ", cityName=" + cityName + "]";
	}
	
	
}
