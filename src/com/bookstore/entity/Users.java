package com.bookstore.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


@Entity
@Table(name="USERS",catalog = "JSPPROJECTDATABASE")
@NamedQueries({
	@NamedQuery(name = "Users.findAll",query = "SELECT u FROM Users u ORDER BY u.id ASC"),
	@NamedQuery(name = "Users.countAll",query = "SELECT COUNT(*) FROM Users u"),
	@NamedQuery(name = "Users.findByEmail",query = "SELECT u FROM Users u WHERE u.email =:email"),
	@NamedQuery(name = "Users.checkLogin",query = "SELECT u FROM Users u WHERE u.email =:email AND u.password=:password")
})
public class Users implements Serializable{
	
	@Id
	@SequenceGenerator(name="USER_SEQ", sequenceName="USER_SEQ", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="USER_SEQ")
	@Column(name="USER_ID", nullable = false)
	private int id;
	
	@Column(name="NAME")
	private String name;
	
	@Column(name="SURNAME")
	private String surname;
	
	@Column(name="EMAIL")
	private String email;
	
	@Column(name="PASSWORD")
	private String password;
	
	public Users() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Users(int id, String name, String surname, String email, String password) {
		super();
		this.id = id;
		this.name = name;
		this.surname = surname;
		this.email = email;
		this.password = password;
	}

	public Users(String name, String surname, String email, String password) {
		super();
		this.name = name;
		this.surname = surname;
		this.email = email;
		this.password = password;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFullName() {
		return this.name + " " + this.surname;
	}
	
	@Override
	public String toString() {
		return "Users [id=" + id + ", name=" + name + ", surname=" + surname + ", email=" + email + ", password="
				+ password + "]";
	}
	
	
}
