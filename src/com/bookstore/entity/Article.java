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
@Table(name="ARTICLE",catalog = "JSPPROJECTDATABASE")
@NamedQueries({
	@NamedQuery(name = "Article.findAll",query = "SELECT a FROM Article a ORDER BY a.id ASC"),
	@NamedQuery(name = "Article.countAll",query = "SELECT COUNT(*) FROM Article a"),
	@NamedQuery(name = "Article.findByName",query = "SELECT a FROM Article a WHERE a.name =:name")
})
public class Article implements Serializable{

	@Id
	@SequenceGenerator(name="ARTICLE_SEQ", sequenceName="ARTICLE_SEQ", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="ARTICLE_SEQ")
	@Column(name="ARTICLE_ID", nullable = false)
	private int id;
	
	@Column(name="NAME")
	private String name;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "article")
	private Set<DetailArticle> detailArticles = new HashSet<DetailArticle>();
	

	public Article() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	public Article(int id) {
		super();
		this.id = id;
	}


	public Article(String name) {
		super();
		this.name = name;
	}
	
	
	public Article(String name, Set<DetailArticle> detailArticles) {
		super();
		this.name = name;
		this.detailArticles = detailArticles;
	}
	
	

	public Article(int id, String name, Set<DetailArticle> detailArticles) {
		super();
		this.id = id;
		this.name = name;
		this.detailArticles = detailArticles;
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
		return "Article [id=" + id + ", name=" + name + "]";
	}

	public Set<DetailArticle> getDetailArticles() {
		return detailArticles;
	}

	public void setDetailArticles(Set<DetailArticle> detailArticles) {
		this.detailArticles = detailArticles;
	}
	
	

	
}
