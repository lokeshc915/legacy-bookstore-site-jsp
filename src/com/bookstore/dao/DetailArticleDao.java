package com.bookstore.dao;

import java.util.List;

import com.bookstore.entity.Book;
import com.bookstore.entity.DetailArticle;


public class DetailArticleDao extends HibernateDao<DetailArticle> implements GeneticDao<DetailArticle> {

	public DetailArticleDao() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public DetailArticle create(DetailArticle t) {
		// TODO Auto-generated method stub
		return super.create(t);
	}


	@Override
	public DetailArticle update(DetailArticle t) {
		// TODO Auto-generated method stub
		return super.update(t);
	}


	@Override
	public DetailArticle get(Object id) {
		// TODO Auto-generated method stub
		return super.get(DetailArticle.class, id);
	}

	@Override
	public void delete(Object id) {
		// TODO Auto-generated method stub
		super.delete(DetailArticle.class, id);
	}

	@Override
	public List<DetailArticle> listAll() {
		// TODO Auto-generated method stub
		return super.getListAll("DetailArticle.findAll");
	}

	@Override
	public long count() {
		// TODO Auto-generated method stub
		return super.getCountAll("DetailArticle.countAll");
	}

	public List<DetailArticle> getDetailArticleById(int articleId) {
		// TODO Auto-generated method stub
		List<DetailArticle> detailArticlesByArticle = super.findByNameQuery("DetailArticle.listByArticle","articleId",articleId);
		return detailArticlesByArticle;
	}
	
	public long countByArticle(int articleId) {
		return super.getCountByNameQuery("DetailArticle.countByArticle", "articleId",articleId);
	}
	
	
	

}
