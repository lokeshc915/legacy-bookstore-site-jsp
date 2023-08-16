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

import com.bookstore.dao.BookDao;
import com.bookstore.entity.Book;
import com.bookstore.entity.Category;
import com.bookstore.entity.Review;
import com.bookstore.service.BookService;

public class BookDaoTest /*extends BaseDaoTest*/ {

	static BookDao bookDao = null;

	@Rule
	public ExpectedException expectedEx = ExpectedException.none();

	// Code executed before first method is executed
	@BeforeAll
	public static void setUpClass() {
		System.out.println("setUpClass");

		//BaseDaoTest.setUpClass();
		//bookDao = new BookDao(sessionFactory);
		bookDao = new BookDao();
	}

	@Test
	public void testCreateBook() throws ParseException, IOException {
		
		Book newBook = new Book();
		
		// Category
		Category category = new Category("Bilim Kurgu");
		category.setId(24);
		newBook.setCategory(category);
		
		// Book Information
		newBook.setTitle("Macera Boyutu Bilim Kurgu");
		newBook.setAuthor("Ahmet Tan");
		newBook.setDescription("Macera Boyutu Bilim Macerasý");
		newBook.setPrice(35.75f);
		newBook.setIsbn("03214565");
		
		// Date
		SimpleDateFormat dateformat = new SimpleDateFormat("dd/mm/yyyy");
		Date publishDate = dateformat.parse("25/06/2010");
		newBook.setPublishDate(publishDate); 
		
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
        newBook.setImage(imageBytes);
        
		
		Book createdBook = bookDao.create(newBook);
		assertTrue(createdBook.getId()>0);
	}
	
	@Test
	public void testUpdateBook() throws ParseException {
		
		Book existBook = new Book();
		existBook.setId(4);
		// Category
		Category category = new Category("Bilim Kurgu");
		category.setId(24);
		existBook.setCategory(category);
		
		// Book Information
		existBook.setTitle("Bilime Giriþ");
		existBook.setAuthor("Ahmet Iþýk");
		existBook.setDescription("Bilimin Önemi ve Nerelerde Kullanýldýðý");
		existBook.setPrice(40f);
		existBook.setIsbn("03214567");
		
		// Date
		SimpleDateFormat dateformat = new SimpleDateFormat("dd/mm/yyyy");
		Date publishDate = dateformat.parse("25/06/2009");
		existBook.setPublishDate(publishDate); 
		
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
        existBook.setImage(imageBytes);
        
		
		Book updatedBook = bookDao.update(existBook);
		assertEquals(existBook.getTitle(),updatedBook.getTitle());
	}

	@Test
	public void testDeleteBook() {
		System.out.println("testDeleteBook");
		Integer id = 5;
		bookDao.delete(id);
		
		Book book = bookDao.get(id);
		if(book==null) {
			System.out.println("Kitap Silindiði için Bulunmadý..");
		}
		assertNull(book);
	}
	
	@Test
	public void testDeleteNotExistsBook() {
		System.out.println("testDeleteNotExistsBook");
		Integer id = 222222222;
		bookDao.delete(id);
		
		Book book = bookDao.get(id);
		
		expectedEx.expect(EntityNotFoundException.class);
		expectedEx.expectMessage("Bu tür bir kullanýcý hiç yok. O yüzden silinmez");
		assertNull(book);
	}

	@Test
	public void testGetBook() {
		System.out.println("testGetBook");
		Integer id = 4;
		Book book = bookDao.get(id);
		if(book!=null) {
			System.out.println(book.getTitle());
		}

		assertNotNull(book);
	}
	
	@Test
	public void testGetBookNotFound() {
		System.out.println("testGetBookNotFound");
		Integer id = 222222222;
		Book book = bookDao.get(id);
		if(book!=null) {
			System.out.println(book.getTitle());
		}

		assertNull(book);
	}

	@Test
	public void testListAllBooks() {
		System.out.println("testListAllBooks");
		List<Book> books = bookDao.listAll();
		for(Book book : books) {
			System.out.println("ID : " + book.getId() + ", Title : " + book.getTitle());
		}
		assertTrue(books.size()>0);
	}

	@Test
	public void testBookCount() {
		System.out.println("testBookCount");
		long totalBooks = bookDao.count();
		System.out.println("Toplam Kitap Sayýsý " + totalBooks);
		assertEquals(2, totalBooks);
	}
	
	@Test
	public void testBookCountByCategory() {
		System.out.println("testBookCountByCategory");
		int categoryId = 24;
		long totalBooks = bookDao.countByCategory(categoryId);
		System.out.println("Toplam Kitap Sayýsý " + totalBooks);
		assertTrue(totalBooks>0);
	}
	
	@Test
	public void testBookFindByTitle() {
		System.out.println("testBookFindByTitle");
		String bookTitle = "Bilime Giriþ";
		Book book = bookDao.findByTitle(bookTitle);
		assertNotNull(book);
	}
	
	@Test
	public void testBookFindByTitleNotFound() {
		System.out.println("testBookFindByTitleNotFound");
		String bookTitle = "AAAAAAAAAA";
		Book book = bookDao.findByTitle(bookTitle);
		assertNull(book);
	}

	@Test
	public void testBookFindByCategoryFound() {
		System.out.println("testBookFindByCategoryFound");
		int categoryId = 21;
		List<Book> books = bookDao.getBooksByCategory(categoryId);
		for(Book book : books) {
			System.out.println("ID : " + book.getId() + ", Title : " + book.getTitle());
		}
		assertTrue(books.size()>0);
	}
	
	@Test
	public void testListNewBooks() {
		System.out.println("testListNewBooks");
		List<Book> books = bookDao.listNewBooks();
		
		for(Book book : books) {
			SimpleDateFormat dt = new SimpleDateFormat("dd/mm/yyyy"); 
			String publishDate = dt.format(book.getPublishDate());
			System.out.println("ID : " + book.getId() + ", Title : " + book.getTitle()+ ", Published Date : " + publishDate);
		}
		assertTrue(books.size()>0);
	}
	
	@Test
	public void testBookBySearchWithTitleKeyword() {
		System.out.println("testBookBySearchWithKeyword");
		String keyword = "Java";
		List<Book> books = bookDao.searchBook(keyword);
		
		for(Book book : books) {
			SimpleDateFormat dt = new SimpleDateFormat("dd/mm/yyyy"); 
			String publishDate = dt.format(book.getPublishDate());
			System.out.println("ID : " + book.getId() + ", Title : " + book.getTitle()+ ", Published Date : " + publishDate);
		}
		
		assertTrue(books.size()>0);
		
	}
	
	@Test
	public void testBookBySearchWithTitleKeywordNotFound() {
		System.out.println("testBookBySearchWithKeywordNotFound");
		String keyword = "Java1";
		List<Book> books = bookDao.searchBook(keyword);
		
		for(Book book : books) {
			SimpleDateFormat dt = new SimpleDateFormat("dd/mm/yyyy"); 
			String publishDate = dt.format(book.getPublishDate());
			System.out.println("ID : " + book.getId() + ", Title : " + book.getTitle()+ ", Published Date : " + publishDate);
		}
		
		assertTrue(books.size()>0);
		
	}
	
	@Test
	public void testBookBySearchWithAuthorKeyword() {
		System.out.println("testBookBySearchWithAuthorKeyword");
		String keyword = "Ahmet";
		List<Book> books = bookDao.searchBook(keyword);
		
		for(Book book : books) {
			SimpleDateFormat dt = new SimpleDateFormat("dd/mm/yyyy"); 
			String publishDate = dt.format(book.getPublishDate());
			System.out.println("ID : " + book.getId() + 
					           ", Title : " + book.getTitle()+ 
					           ", Published Date : " + publishDate +
					           ", Author : " + book.getAuthor());
		}
		
		assertTrue(books.size()>0);
		
	}
	
	@Test
	public void testBookBySearchWithAuthorKeywordNotFound() {
		System.out.println("testBookBySearchWithAuthorKeywordNotFound");
		String keyword = "NNNN";
		List<Book> books = bookDao.searchBook(keyword);
		
		for(Book book : books) {
			SimpleDateFormat dt = new SimpleDateFormat("dd/mm/yyyy"); 
			String publishDate = dt.format(book.getPublishDate());
			System.out.println("ID : " + book.getId() + 
					           ", Title : " + book.getTitle()+ 
					           ", Published Date : " + publishDate +
					           ", Author : " + book.getAuthor());
		}
		
		assertTrue(books.size()>0);
		
	}
	
	@Test
	public void testBookBySearchWithDescriptionKeyword() {
		System.out.println("testBookBySearchWithDescriptionKeyword");
		String keyword = "Bilim";
		List<Book> books = bookDao.searchBook(keyword);
		
		for(Book book : books) {
			SimpleDateFormat dt = new SimpleDateFormat("dd/mm/yyyy"); 
			String publishDate = dt.format(book.getPublishDate());
			System.out.println("ID : " + book.getId() + 
					           ", Title : " + book.getTitle()+ 
					           ", Published Date : " + publishDate +
					           ", Author : " + book.getAuthor() +
					           ", Description : " + book.getDescription());
		}
		
		assertTrue(books.size()>0);
		
	}
	
	@Test
	public void testBookBySearchWithDescriptionKeywordNotFound() {
		System.out.println("testBookBySearchWithDescriptionKeywordNotFound");
		String keyword = "YYYYY";
		List<Book> books = bookDao.searchBook(keyword);
		
		for(Book book : books) {
			SimpleDateFormat dt = new SimpleDateFormat("dd/mm/yyyy"); 
			String publishDate = dt.format(book.getPublishDate());
			System.out.println("ID : " + book.getId() + 
					           ", Title : " + book.getTitle()+ 
					           ", Published Date : " + publishDate +
					           ", Author : " + book.getAuthor() +
					           ", Description : " + book.getDescription());
		}
		
		assertTrue(books.size()>0);
		
	}
	
	@Test
	public void testBookAverageRatingOnlyOne() {
		System.out.println("testBookAverageRatingOnlyOne");
		Book testBook = new Book();
		
		Set<Review> reviews = new HashSet<>();
		Review review1 = new Review();
		review1.setRating(5);
		reviews.add(review1);
		
		testBook.setReviews(reviews);
		
		float averageRating = testBook.getAverageRating();
		System.out.println("Average Rating : " + averageRating);
		
		assertEquals(5.0, averageRating);
	}
	
	@Test
	public void testBookAverageRatingEmpty() {
		System.out.println("testBookAverageRatingEmpty");
		Book testBook = new Book();
		
		float averageRating = testBook.getAverageRating();
		System.out.println("Average Rating : " + averageRating);
		
		assertEquals(0.0, averageRating);
	}
	
	@Test
	public void testBookAverageRating() {
		System.out.println("testBookAverageRating");
		Book testBook = new Book();
		
		Set<Review> reviews = new HashSet<>();
		
		Review review1 = new Review();
		review1.setRating(5);
		reviews.add(review1);
		
		Review review2 = new Review();
		review2.setRating(4);
		reviews.add(review2);
		
		Review review3 = new Review();
		review3.setRating(3);
		reviews.add(review3);
		
		testBook.setReviews(reviews);
		
		float averageRating = testBook.getAverageRating();
		System.out.println("Average Rating : " + averageRating);
		
		assertEquals(4.0, averageRating);
	}
	
	@Test
	public void testBookAverageRatingEmptyStar() {
		System.out.println("testBookAverageRatingEmptyStar");
		
		float averageRating = 0.0f;
		Book book = new Book();
		String actual = book.getAverageRatingString(averageRating);
		
		String expect = "off,off,off,off,off";
		
		assertEquals(expect, actual);
	}
	
	@Test
	public void testBookAverageRatingFullStar() {
		System.out.println("testBookAverageRatingFullStar");
		
		float averageRating = 5.0f;
		Book book = new Book();
		String actual = book.getAverageRatingString(averageRating);
		
		String expect = "on,on,on,on,on";
		
		assertEquals(expect, actual);
	}
	
	@Test
	public void testBookAverageRatingThreeStar() {
		System.out.println("testBookAverageRatingThreeStar");
		
		float averageRating = 3.0f;
		Book book = new Book();
		String actual = book.getAverageRatingString(averageRating);
		
		String expect = "on,on,on,off,off";
		
		assertEquals(expect, actual);
	}
	
	@Test
	public void testBookAverageRatingTwoAndHalfStar() {
		System.out.println("testBookAverageRatingTwoAndHalfStar");
		
		float averageRating = 2.5f;
		Book book = new Book();
		String actual = book.getAverageRatingString(averageRating);
		
		String expect = "on,on,half,off,off";
		
		assertEquals(expect, actual);
	}
	
	@Test
	public void testBookAverageRatingTwoAndHalfOtherStar() {
		System.out.println("testBookAverageRatingTwoAndHalfStar");
		
		float averageRating = 2.7f;
		Book book = new Book();
		String actual = book.getAverageRatingString(averageRating);
		
		String expect = "on,on,half,off,off";
		
		assertEquals(expect, actual);
	}
	
	@Test
	public void testListBestSellingBooks() {
		List<Book> topBestSellingBooks = bookDao.listBestSellingBooks();
		
		for (Book book : topBestSellingBooks) {
			System.out.println(book.getTitle());
		}
		
		//assertEquals(4, topBestSellingBooks.size());
	}
	
	@Test
	public void testListMostFavoredBooks() {
		List<Book> topFavoredBooks = bookDao.listMostFavoredBooks();

		for (Book book : topFavoredBooks) {
			System.out.println(book.getTitle());
		}
		
		//assertEquals(4, topFavoredBooks.size());
	}
	
	
	@AfterAll
	public static void tearDownClass() {
		System.out.println("tearDownClass");
		bookDao.HibernateSessionFactoryClose();
	}

}
