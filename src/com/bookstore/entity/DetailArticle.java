package com.bookstore.entity;

import java.io.Serializable;
import java.util.Base64;

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
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="DETAILARTICLE",catalog = "JSPPROJECTDATABASE")
@NamedQueries({
	@NamedQuery(name = "DetailArticle.findAll",query = "SELECT da FROM DetailArticle da ORDER BY da.id ASC"),
	@NamedQuery(name = "DetailArticle.countAll",query = "SELECT COUNT(*) FROM DetailArticle da"),
	@NamedQuery(name = "DetailArticle.listByArticle",
		query = "SELECT da FROM DetailArticle da LEFT JOIN Article a ON da.article.id =a.id WHERE a.id=:articleId"),
	@NamedQuery(name = "DetailArticle.countByArticle",
    	query = "SELECT COUNT(da) FROM DetailArticle da LEFT JOIN Article a ON da.article.id =a.id WHERE a.id=:articleId")
})
public class DetailArticle implements Serializable{

	@Id
	@SequenceGenerator(name="DETAILARTICLE_SEQ", sequenceName="DETAILARTICLE_SEQ", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="DETAILARTICLE_SEQ")
	@Column(name="ARTICLE_DETAIL_ID", nullable = false)
	private int id;
	
	@Column(name="PARAGRAGH_1")
	private String paragragh1;
	
	@Column(name="PARAGRAGH_2")
	private String paragragh2;
	
	@Column(name="PARAGRAGH_3")
	private String paragragh3;
	
	@Column(name="ARTICLE_IMAGE")
	private byte[] image;
	
	@Transient
	private String base64Image;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ARTICLE_ID", nullable = false)
	private Article article;
	
	
	
	public DetailArticle() {
		super();
		// TODO Auto-generated constructor stub
	}

	public DetailArticle(int id) {
		super();
		this.id = id;
	}

	public DetailArticle(int id, String paragragh1, String paragragh2, String paragragh3, byte[] image,
			Article article) {
		super();
		this.id = id;
		this.paragragh1 = paragragh1;
		this.paragragh2 = paragragh2;
		this.paragragh3 = paragragh3;
		this.image = image;
		this.article = article;
	}

	
	public DetailArticle(int id, String paragragh1, String paragragh2, String paragragh3, byte[] image,
			String base64Image, Article article) {
		super();
		this.id = id;
		this.paragragh1 = paragragh1;
		this.paragragh2 = paragragh2;
		this.paragragh3 = paragragh3;
		this.image = image;
		this.base64Image = base64Image;
		this.article = article;
	}


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getParagragh1() {
		return paragragh1;
	}

	public void setParagragh1(String paragragh1) {
		this.paragragh1 = paragragh1;
	}

	public String getParagragh2() {
		return paragragh2;
	}

	public void setParagragh2(String paragragh2) {
		this.paragragh2 = paragragh2;
	}

	public String getParagragh3() {
		return paragragh3;
	}

	public void setParagragh3(String paragragh3) {
		this.paragragh3 = paragragh3;
	}

	public byte[] getImage() {
		return image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}

	public Article getArticle() {
		return article;
	}

	public void setArticle(Article article) {
		this.article = article;
	}

	public void setBase64Image(String base64Image) {
		this.base64Image = base64Image;
	}

	public String getBase64Image() {
		this.base64Image = Base64.getEncoder().encodeToString(this.image);
	    return base64Image;
	}
}
