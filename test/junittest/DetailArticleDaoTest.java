package junittest;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityNotFoundException;

import org.junit.Rule;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.rules.ExpectedException;

import com.bookstore.dao.DetailArticleDao;
import com.bookstore.entity.Article;
import com.bookstore.entity.DetailArticle;

public class DetailArticleDaoTest /*extends BaseDaoTest*/ {

	static DetailArticleDao detailArticleDao = null;

	@Rule
	public ExpectedException expectedEx = ExpectedException.none();

	// Code executed before first method is executed
	@BeforeAll
	public static void setUpClass() {
		System.out.println("setUpClass");

		//BaseDaoTest.setUpClass();
		//bookDao = new BookDao(sessionFactory);
		detailArticleDao = new DetailArticleDao();
	}

	@Test
	public void testCreateDetailArticle() throws ParseException, IOException {
		
		DetailArticle newDetailArticle = new DetailArticle();
		
		// Article
		Article newArticle = new Article("Hakkýmýzda");
		newArticle.setId(1);
		newDetailArticle.setArticle(newArticle);
		
		// Detail Article Information
		String paragragh1 = "Paragraf 1 - Donec id elit non mi porta gravida at eget metus. Etiam porta sem malesuada magna mollis euismod. Donec sed odio dui.";
		String paragragh2 = "Paragraf 2 - Donec id elit non mi porta gravida at eget metus. Etiam porta sem malesuada magna mollis euismod. Donec sed odio dui.";
		String paragragh3 = "Paragraf 3 - Donec id elit non mi porta gravida at eget metus. Etiam porta sem malesuada magna mollis euismod. Donec sed odio dui.";
		
		newDetailArticle.setParagragh1(paragragh1);
		newDetailArticle.setParagragh2(paragragh2);
		newDetailArticle.setParagragh3(paragragh3);
				
		// Image 
	    File file = new File("C:\\Users\\Noyan\\Desktop\\Adsýz.png");
        byte[] imageBytes = new byte[(int) file.length()];
 
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            fileInputStream.read(imageBytes);
            fileInputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        newDetailArticle.setImage(imageBytes);
        
		
        DetailArticle createdDetailArticle = detailArticleDao.create(newDetailArticle);
		assertTrue(createdDetailArticle.getId()>0);
	}
	
	@Test
	public void testUpdateDetailArticle() throws ParseException {
		
		DetailArticle existDetailArticle = new DetailArticle();
		existDetailArticle.setId(1);

		// Article
		Article newArticle = new Article("Hakkýmýzda");
		newArticle.setId(1);
		existDetailArticle.setArticle(newArticle);
		
		// Detail Article Information
		String paragragh1 = "Paragraf 1 - Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.";
		String paragragh2 = "Paragraf 2 - Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.";
		String paragragh3 = "Paragraf 3 - Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.";
		
		existDetailArticle.setParagragh1(paragragh1);
		existDetailArticle.setParagragh2(paragragh2);
		existDetailArticle.setParagragh3(paragragh3);
		
		// Image 
	    File file = new File("C:\\Users\\Noyan\\Desktop\\Adsýz.png");
        byte[] imageBytes = new byte[(int) file.length()];
 
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            fileInputStream.read(imageBytes);
            fileInputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        existDetailArticle.setImage(imageBytes);
        
		
        DetailArticle updatedDetailArticle = detailArticleDao.update(existDetailArticle);
		assertEquals(existDetailArticle.getParagragh1(),updatedDetailArticle.getParagragh1());
	}

	@Test
	public void testDeleteDetailArticle() {
		System.out.println("testDeleteDetailArticle");
		Integer id = 5;
		detailArticleDao.delete(id);
		
		DetailArticle detailArticle = detailArticleDao.get(id);
		if(detailArticle==null) {
			System.out.println("DetailArticle Silindiði için Bulunmadý..");
		}
		assertNull(detailArticle);
	}
	
	@Test
	public void testDeleteNotExistsDetailArticle() {
		System.out.println("testDeleteNotExistsDetailArticle");
		Integer id = 222222222;
		detailArticleDao.delete(id);
		
		DetailArticle detailArticle = detailArticleDao.get(id);
		
		expectedEx.expect(EntityNotFoundException.class);
		expectedEx.expectMessage("Bu tür bir kullanýcý hiç yok. O yüzden silinmez");
		assertNull(detailArticle);
	}

	@Test
	public void testGetDetailArticle() {
		System.out.println("testGetDetailArticle");
		Integer id = 1;
		DetailArticle detailArticle = detailArticleDao.get(id);
		if(detailArticle!=null) {
			System.out.println(detailArticle.getArticle().getName());
			System.out.println(detailArticle.getParagragh1());
		}

		assertNotNull(detailArticle);
	}
	
	@Test
	public void testGetDetailArticleNotFound() {
		System.out.println("testGetBookNotFound");
		Integer id = 222222222;
		DetailArticle detailArticle = detailArticleDao.get(id);
		if(detailArticle!=null) {
			System.out.println(detailArticle.getArticle().getName());
			System.out.println(detailArticle.getParagragh1());
		}

		assertNull(detailArticle);
	}

	@Test
	public void testListAllDetailArticle() {
		System.out.println("testListAllDetailArticle");
		List<DetailArticle> detailArticles = detailArticleDao.listAll();
		for(DetailArticle detailArticle : detailArticles) {
			System.out.println("ID : " + detailArticle.getId());
		}
		assertTrue(detailArticles.size()>0);
	}

	@Test
	public void testDetailArticleCount() {
		System.out.println("testDetailArticleCount");
		long totalBooks = detailArticleDao.count();
		System.out.println("Toplam Article Detail Sayýsý " + totalBooks);
		assertEquals(1, totalBooks);
	}
	

	@Test
	public void testBookFindByDetailArticleFound() {
		System.out.println("testBookFindByDetailArticleFound");
		int detailArticleId = 1;
		List<DetailArticle> detailArticles = detailArticleDao.getDetailArticleById(detailArticleId);
		for(DetailArticle detailArticle : detailArticles) {
			System.out.println("ID : " + detailArticle.getId());
		}
		assertTrue(detailArticles.size()>0);
	}
	
	@Test
	public void testDetailArticleCountByArticle() {
		System.out.println("testDetailArticleCountByArticle");
		int detailArticleId = 1;
		long totalBooks = detailArticleDao.countByArticle(detailArticleId);
		System.out.println("Toplam Article Detail Sayýsý " + totalBooks);
		assertTrue(totalBooks>0);
	}
	
	
	@AfterAll
	public static void tearDownClass() {
		System.out.println("tearDownClass");
		detailArticleDao.HibernateSessionFactoryClose();
	}

}
