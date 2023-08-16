package junittest;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.hibernate.SessionFactory;
import org.junit.Rule;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.rules.ExpectedException;

import com.bookstore.dao.ArticleDao;
import com.bookstore.dao.CategoryDao;
import com.bookstore.entity.Article;
import com.bookstore.entity.Category;
import com.bookstore.entity.Users;
import com.bookstore.util.HibernateUtil;

public class ArticleDaoTest /*extends BaseDaoTest*/{
	
	static ArticleDao articleDao = null;
	
	@Rule
	public ExpectedException expectedEx = ExpectedException.none();


	// Code executed before first method is executed
	@BeforeAll
	public static void setUpClass(){
		System.out.println("setUpClass");
		
		//BaseDaoTest.setUpClass();
		//categoryDao = new CategoryDao(sessionFactory);
		articleDao = new ArticleDao();
	}

	@Test
	public void testCreateArticle() {
		System.out.println("testCreateArticle");
		Article newArticle = new Article("Hakkýmýzda");
		Article article = articleDao.create(newArticle);
		
		assertTrue(article!=null && article.getId()>0);
	}
	
	@Test()
	public void testCreateArticleFieldsNotSet() {
		System.out.println("testCreateArticleFieldsNotSet");
		
		Article article = new Article();
		article = articleDao.create(article);
		expectedEx.expect(NullPointerException.class);
		expectedEx.expectMessage("NullPointerException caught");
	}

	@Test
	public void testUpdateArticle() {
		System.out.println("testUpdateArticle");
		Article updateArticle = new Article("Biz Kimiz");
		updateArticle.setId(1);
		Article article = articleDao.update(updateArticle);
		
		assertEquals(updateArticle.getName(), article.getName());
	}

	@Test
	public void testGetArticle() {
		System.out.println("testGetArticle");
		Integer id = 1;
		Article article = articleDao.get(id);
		if(article!=null) {
			System.out.println(article.getName());
		}

		assertNotNull(article);
	}
	
	@Test
	public void testGetArticleNotFound() {
		System.out.println("testGetArticleNotFound()");
		Integer id = 222222222;
		Article article = articleDao.get(id);
		if(article!=null) {
			System.out.println(article.getName());
		}

		assertNotNull(article);
	}

	@Test
	public void testDeleteArticle() {
		System.out.println("testDeleteArticle");
		Integer id = 23;
		articleDao.delete(id);
		
		Article article = articleDao.get(id);
		if(article==null) {
			System.out.println("Article Silindiði için Bulunmadý..");
		}
		assertNull(article);
	}
	
	@Test
	public void testDeleteNotExistsArticle() {
		System.out.println("testDeleteNotExistsArticle");
		Integer id = 222222222;
		articleDao.delete(id);
		
		Article article = articleDao.get(id);
		
		expectedEx.expect(EntityNotFoundException.class);
		expectedEx.expectMessage("Bu tür bir article hiç yok. O yüzden silinmez");
		assertNull(article);
	}

	@Test
	public void testListAllArticles() {
		System.out.println("testListAllArticles");
		List<Article> articles = articleDao.listAll();
		for(Article article : articles) {
			System.out.println("ID : " + article.getId() + ", Ad : " + article.getName());
		}
		assertTrue(articles.size()>0);
	}

	@Test
	public void testArticleCount() {
		System.out.println("testArticleCount");
		long totalArticles = articleDao.count();
		System.out.println("Toplam Article Sayýsý :" + totalArticles);
		assertEquals(1, totalArticles);
	}
	
	@Test
	public void testArticleFindByName() {
		System.out.println("testArticleFindByName");
		String articleName = "Hakkýmýzda";
		Article article = articleDao.findByName(articleName);
		assertNotNull(article);
	}
	
	@Test
	public void testArticleFindByNameNotFound() {
		System.out.println("testArticleFindByNameNotFound");
		String articleName = "AAAAA";
		Article article = articleDao.findByName(articleName);
		assertNull(article);
	}
	
	@AfterAll
	public static void tearDownClass() {
		System.out.println("tearDownClass");
		//BaseDaoTest.tearDownClass();
		articleDao.HibernateSessionFactoryClose();
	}

}
