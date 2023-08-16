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

import com.bookstore.dao.CategoryDao;
import com.bookstore.entity.Category;
import com.bookstore.entity.Users;
import com.bookstore.util.HibernateUtil;

public class CategoryDaoTest /*extends BaseDaoTest*/{
	
	static CategoryDao categoryDao = null;
	
	@Rule
	public ExpectedException expectedEx = ExpectedException.none();


	// Code executed before first method is executed
	@BeforeAll
	public static void setUpClass(){
		System.out.println("setUpClass");
		
		//BaseDaoTest.setUpClass();
		//categoryDao = new CategoryDao(sessionFactory);
		categoryDao = new CategoryDao();
	}

	@Test
	public void testCreateCategory() {
		System.out.println("testCreateCategory");
		Category newCategory = new Category("Moda");
		Category category = categoryDao.create(newCategory);
		
		assertTrue(category!=null && category.getId()>0);
	}
	
	@Test()
	public void testCreateCategoryFieldsNotSet() {
		System.out.println("testCreateCategoryFieldsNotSet");
		
		Category category = new Category();	
		category = categoryDao.create(category);
		expectedEx.expect(NullPointerException.class);
		expectedEx.expectMessage("NullPointerException caught");
	}

	@Test
	public void testUpdateCategory() {
		System.out.println("testUpdateCategory");
		Category updateCategory = new Category("Bilim");
		updateCategory.setId(21);
		Category category = categoryDao.update(updateCategory);
		
		assertEquals(updateCategory.getName(), category.getName());
	}

	@Test
	public void testGetCategory() {
		System.out.println("testGetCategory");
		Integer id = 21;
		Category category = categoryDao.get(id);
		if(category!=null) {
			System.out.println(category.getName());
		}

		assertNotNull(category);
	}
	
	@Test
	public void testGetCategoryNotFound() {
		System.out.println("testGetUsersNotFound()");
		Integer id = 222222222;
		Category category = categoryDao.get(id);
		if(category!=null) {
			System.out.println(category.getName());
		}

		assertNull(category);
	}

	@Test
	public void testDeleteCategory() {
		System.out.println("testDeleteCategory");
		Integer id = 23;
		categoryDao.delete(id);
		
		Category category = categoryDao.get(id);
		if(category==null) {
			System.out.println("Kategori Silindiði için Bulunmadý..");
		}
		assertNull(category);
	}
	
	@Test
	public void testDeleteNotExistsCategory() {
		System.out.println("testDeleteNotExistsCategory");
		Integer id = 222222222;
		categoryDao.delete(id);
		
		Category category = categoryDao.get(id);
		
		expectedEx.expect(EntityNotFoundException.class);
		expectedEx.expectMessage("Bu tür bir kullanýcý hiç yok. O yüzden silinmez");
		assertNull(category);
	}

	@Test
	public void testListAllCategories() {
		System.out.println("testListAllCategories");
		List<Category> categories = categoryDao.listAll();
		for(Category category : categories) {
			System.out.println("ID : " + category.getId() + ", Ad : " + category.getName());
		}
		assertTrue(categories.size()>0);
	}

	@Test
	public void testCategoryCount() {
		System.out.println("testCount");
		long totalCategories = categoryDao.count();
		System.out.println("Toplam Kategori Sayýsý " + totalCategories);
		assertEquals(3, totalCategories);
	}
	
	@Test
	public void testCategoryFindByName() {
		System.out.println("testCategoryFindByName");
		String categoryName = "Bilim";
		Category category = categoryDao.findByName(categoryName);
		assertNotNull(category);
	}
	
	@Test
	public void testCategoryFindByNameNotFound() {
		System.out.println("testCategoryFindByName");
		String categoryName = "AAAAA";
		Category category = categoryDao.findByName(categoryName);
		assertNull(category);
	}
	
	@AfterAll
	public static void tearDownClass() {
		System.out.println("tearDownClass");
		//BaseDaoTest.tearDownClass();
		categoryDao.HibernateSessionFactoryClose();
	}

}
