package junittest;

import static org.junit.jupiter.api.Assertions.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.junit.Rule;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.rules.ExpectedException;

import com.bookstore.dao.ReviewDao;
import com.bookstore.entity.Book;
import com.bookstore.entity.Customer;
import com.bookstore.entity.Review;

public class ReviewDaoTest {

	static ReviewDao reviewDao = null;
	
	@Rule
	public ExpectedException expectedEx = ExpectedException.none();
	
	// Code executed before first method is executed
	@BeforeAll
	public static void setUpClass() {
		System.out.println("setUpClass");
		BaseDaoTest.setUpClass();
		reviewDao = new ReviewDao();
	}

	@Test
	public void testCreateReview() {
		System.out.println("testCreateReview");
		Review review = new Review();
		Book book = new Book();
		book.setId(43);
		
		Customer customer = new Customer();
		customer.setId(22);
		
		review.setBookReview(book);
		review.setCustomerReview(customer);
		
		review.setHeadline("Kitab� be�enmedim");
		review.setRating(2);
		review.setReviewComment("Kitab� okumama ra�men pek ilgimi �ekmedi.");
		
		Review savedReview = reviewDao.create(review);
		
		if(savedReview !=null) {
			System.out.println("Kitap Ba�ar�l� bir �ekilde kaydedildi.");
		}else {
			System.out.println("Kitap Ba�ar�l� bir �ekilde kaydedilmedi.");
		}
		
		assertTrue(savedReview.getId()>0);
		
	}

	@Test
	public void testGetReview() {
		System.out.println("testGetReview");
		int reviewId = 2;
		Review review = reviewDao.get(reviewId);
		
		if(review!=null) {
			System.out.println("Yorum Bilgisi");
			System.out.println("Kitap Id :" + review.getBookReview().getId());
			System.out.println("M��teri Id :" + review.getCustomerReview().getId());
			System.out.println("Puan(Rating) :"+ review.getRating());
			System.out.println("Ba�l�k : "+ review.getHeadline());
			System.out.println("Yorum : "+ review.getReviewComment());
			
			SimpleDateFormat dateformat = new SimpleDateFormat("dd/MM/yyyy");
			String publishDate = dateformat.format(review.getReviewDate());
			System.out.println("Yorum Olu�turma Tarihi : " + publishDate);
		}
		
		assertNotNull(review);
		
	}
	
	@Test
	public void testUpdateReview() {
		System.out.println("testUpdateReview");
		int reviewId = 2;
		Review review = reviewDao.get(reviewId);
		
		if(review!=null) {
			
			review.setHeadline("Kitap fena de�il");
			review.setRating(3);
			review.setReviewComment("Kitap �ok s�r�kleyici de�il fakat okumaya de�er");
		}
		
		Review updatedReview = reviewDao.update(review);
		
		System.out.println("Yorum Bilgisi");
		System.out.println("Kitap Id :" + updatedReview.getBookReview().getId());
		System.out.println("M��teri Id :" + updatedReview.getCustomerReview().getId());
		System.out.println("Puan(Rating) :"+ updatedReview.getRating());
		System.out.println("Ba�l�k : "+ updatedReview.getHeadline());
		System.out.println("Yorum : "+ updatedReview.getReviewComment());
		
		SimpleDateFormat dateformat = new SimpleDateFormat("dd/MM/yyyy");
		String publishDate = dateformat.format(updatedReview.getReviewDate());
		System.out.println("Yorum Olu�turma Tarihi : " + publishDate);
		
		
		assertEquals(review.getHeadline(), updatedReview.getHeadline());
	}

	@Test
	public void testDeleteReview() {
		System.out.println("testDeleteReview");
		Integer reviewId = 3;
		reviewDao.delete(reviewId);
		
		Review review = reviewDao.get(reviewId);
		if(review==null) {
			System.out.println("Yorum Silindi�i i�in Bulunmad�..");
		}
		assertNull(review);
	}

	@Test
	public void testListAllReviews() {
		System.out.println("testListAllReviews");
		List<Review> reviews = reviewDao.listAll();
		System.out.println("--------------Yorum Bilgisi Listesi ---------------");
		for(Review review : reviews) {
			System.out.println("Kitap Id :" + review.getBookReview().getId());
			System.out.println("M��teri Id :" + review.getCustomerReview().getId());
			System.out.println("Puan(Rating) :"+ review.getRating());
			System.out.println("Ba�l�k : "+ review.getHeadline());
			System.out.println("Yorum : "+ review.getReviewComment());
			
			SimpleDateFormat dateformat = new SimpleDateFormat("dd/MM/yyyy");
			String publishDate = dateformat.format(review.getReviewDate());
			System.out.println("Yorum Olu�turma Tarihi : " + publishDate);
			System.out.println("-----------------------------------------------");
		}
		assertTrue(reviews.size()>0);
	}

	@Test
	public void testCountReview() {
		System.out.println("testCountReview");
		long totalReviews = reviewDao.count();
		System.out.println("Toplam Yorum Say�s� : " + totalReviews);
		assertEquals(1, totalReviews);
	}
	
	@Test
	public void testFindByReviewIdAndBookId() {
		System.out.println("testFindByReviewIdAndBookId");

		int customerId = 14;
		int bookId = 4;
		
		Review review = reviewDao.findByReviewIdAndBookId(customerId, bookId);
		
		if(review != null) {
			System.out.println("---------Yorum Bilgisi----------");
			System.out.println("Yorum Id :" + review.getId());
			System.out.println("Yorum Ba�l�k :" + review.getHeadline());
			System.out.println("Yorum :" + review.getReviewComment());
		}
		
		assertNotNull(review);
	}
	
	@Test
	public void testNotFindByReviewIdAndBookId() {
		System.out.println("testNotFindByReviewIdAndBookId");

		int customerId = 1456;
		int bookId = 4500;
		
		Review review = reviewDao.findByReviewIdAndBookId(customerId, bookId);
		
		if(review == null) {
			System.out.println("Yorum Bulunamad�..");
		}
		
		assertNull(review);
	}
	
	
	@AfterAll
	public static void tearDownClass() {
		System.out.println("tearDownClass");
		reviewDao.HibernateSessionFactoryClose();
	}

}
