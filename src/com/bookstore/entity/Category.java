package com.bookstore.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="CATEGORY",catalog = "JSPPROJECTDATABASE")
@NamedQueries({
	@NamedQuery(name = "Category.findAll",query = "SELECT c FROM Category c ORDER BY c.id ASC"),
	@NamedQuery(name = "Category.countAll",query = "SELECT COUNT(*) FROM Category c"),
	@NamedQuery(name = "Category.findByName",query = "SELECT c FROM Category c WHERE c.name =:name")
})
public class Category implements Serializable{

	@Id
	@SequenceGenerator(name="CATEGORY_SEQ", sequenceName="CATEGORY_SEQ", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="CATEGORY_SEQ")
	@Column(name="CATEGORY_ID", nullable = false)
	private int id;
	
	@Column(name="NAME")
	private String name;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "category")
	private Set<Book> books = new HashSet<Book>();

	public Category() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Category(String name) {
		super();
		this.name = name;
	}
	
	
	public Category(String name, Set<Book> books) {
		super();
		this.name = name;
		this.books = books;
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

	@Override
	public String toString() {
		return "Category [id=" + id + ", name=" + name + "]";
	}

	public Set<Book> getBooks() {
		return books;
	}

	public void setBooks(Set<Book> books) {
		this.books = books;
	}
	
}
