package com.bookstore.service;

import java.util.List;

import com.bookstore.dao.ArticleDao;
import com.bookstore.entity.Article;

public class ArticleService {

	private ArticleDao articleDao;
	
	public ArticleService() {
		super();
		//this.categoryDao = new CategoryDao(sessionFactory);
		this.articleDao = new ArticleDao();
	}
	
	public List<Article> listArticle() {
		List<Article> articles = articleDao.listAll();
		return articles;
	}
	
	public void createArticle(Article article) {
		articleDao.create(article);
	}
	
	public Article findByName(String name) {
		Article article = articleDao.findByName(name);
		return article;
	}
	
	public long count() {
		// TODO Auto-generated method stub
		return articleDao.count();
	}
	
	public Article getArticleById(int articleId) {
		return articleDao.get(articleId);
	}
	
	public void updateArticle(Article article) {
		articleDao.update(article);
	}
	
	public void deleteArticle(int articleId) {
		articleDao.delete(articleId);
	}
}
