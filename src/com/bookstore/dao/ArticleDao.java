package com.bookstore.dao;

import java.util.List;

import com.bookstore.entity.Article;

public class ArticleDao extends HibernateDao<Article> implements GeneticDao<Article>{

	
	public ArticleDao() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public Article create(Article t) {
		// TODO Auto-generated method stub
		return super.create(t);
	}

	
	@Override
	public Article update(Article t) {
		// TODO Auto-generated method stub
		return super.update(t);
	}

	@Override
	public Article get(Object id) {
		// TODO Auto-generated method stub
		return super.get(Article.class, id);
	}

	@Override
	public void delete(Object id) {
		// TODO Auto-generated method stub
		super.delete(Article.class, id);
	}

	@Override
	public List<Article> listAll() {
		// TODO Auto-generated method stub
		return super.getListAll("Article.findAll");
	}

	@Override
	public long count() {
		// TODO Auto-generated method stub
		return super.getCountAll("Article.countAll");
	}
	
	public Article findByName(String categoryName) {
		List<Article> articleByName = super.findByNameQuery("Article.findByName","name",categoryName);
		
		if(articleByName != null && articleByName.size() == 1) {
			return articleByName.get(0);
		}
		
		return null;
	}

}
