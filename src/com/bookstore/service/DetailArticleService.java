package com.bookstore.service;

import java.util.List;

import com.bookstore.dao.ArticleDao;
import com.bookstore.dao.DetailArticleDao;
import com.bookstore.entity.Article;
import com.bookstore.entity.DetailArticle;

public class DetailArticleService {

	private DetailArticleDao detailArticleDao;
	private ArticleDao articleDao;
	
	public DetailArticleService() {
		super();
		this.articleDao = new ArticleDao();
		this.detailArticleDao = new DetailArticleDao();
	}
	
	public List<DetailArticle> listDetailArticle() {
		List<DetailArticle> detailArticles = detailArticleDao.listAll();
		return detailArticles;
	}
	
	public void createBook(DetailArticle detailArticle) {
		detailArticleDao.create(detailArticle);
	}
	
	public long count() {
		// TODO Auto-generated method stub
		return detailArticleDao.count();
	}
	
	public List<DetailArticle> getDetailArticleById(int articleId){
		List<DetailArticle> detailArticles = detailArticleDao.getDetailArticleById(articleId);
		return detailArticles;
	}
	
	public DetailArticle getDetailArticle(int detailArticleId) {
		return detailArticleDao.get(detailArticleId);
	}
	
	
	public void updateDetailArticle(DetailArticle detailArticle) {
		detailArticleDao.update(detailArticle);
	}
	
	public void deleteDetailArticle(int detailArticleId) {
		detailArticleDao.delete(detailArticleId);
	}
	
	public List<Article> listArticle(){
		List<Article> articles = articleDao.listAll();
		return articles;
	}
	
	public long countByArticle(int articleId) {
		// TODO Auto-generated method stub
		return detailArticleDao.countByArticle(articleId);
	}
	
}
