package junittest;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.hibernate.SessionFactory;
import org.junit.Rule;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.rules.ExpectedException;

import com.bookstore.dao.UserDao;
import com.bookstore.entity.Users;
import com.bookstore.util.HibernateUtil;

public class UserDaoTest /*extends BaseDaoTest*/{
	
	static UserDao userDao = null;
	
	@Rule
	public ExpectedException expectedEx = ExpectedException.none();
	
	// Code executed before first method is executed
	@BeforeAll
	public static void setUpClass() {
		System.out.println("setUpClass");
		
		BaseDaoTest.setUpClass();
		//userDao = new UserDao(sessionFactory);
		userDao = new UserDao();
	}
	

	@Test
	public void testCreateUsers() {
		System.out.println("testCreateUsers");
		
		Users user1 = new Users();
		user1.setName("Ersin");
		user1.setSurname("Kavak");
		user1.setEmail("e@k.com");
		user1.setPassword("Secret_90");
		
		user1 = userDao.create(user1);
		
		assertTrue(user1.getId()>0);
	}
	
	@Test()
	public void testCreateUsersFieldsNotSet() {
		System.out.println("testCreateUsersFieldsNotSet");
		
		Users user = new Users();	
		user = userDao.create(user);
		expectedEx.expect(NullPointerException.class);
		expectedEx.expectMessage("NullPointerException caught");
	}

	@Test
	public void testUpdateUsers() {
		System.out.println("testUpdateUsers");
		
		Users user = new Users();
		user.setId(1);
		user.setName("Aslý");
		user.setSurname("Doðan");
		user.setEmail("a@d.com");
		user.setPassword("0002");
		
		user = userDao.update(user);
		
		// Password Check
		String password = "0001";
		String updateActualPassword = user.getPassword();
		
		assertEquals(password, updateActualPassword);
	}

	@Test
	public void testGetUsersFound() {
		System.out.println("testGetUsersFound");
		Integer id = 1;
		Users user = userDao.get(id);
		if(user!=null) {
			System.out.println(user.getEmail());
		}

		assertNotNull(user);
	}
	
	@Test
	public void testGetUsersNotFound() {
		System.out.println("testGetUsersNotFound()");
		Integer id = 1111111111;
		Users user = userDao.get(id);
		if(user==null) {
			System.out.println("Kullanýcý Bulunmadý..");
		}
		assertNull(user);
	}

	@Test
	public void testDelete() {
		System.out.println("testDelete");
		Integer id = 7;
		userDao.delete(id);
		
		Users user = userDao.get(id);
		if(user==null) {
			System.out.println("Kullanýcý Silindiði için Bulunmadý..");
		}
		assertNull(user);
	}
	
	@Test
	public void testDeleteNotExistsUsers() {
		System.out.println("testDelete");
		Integer id = 88;
		userDao.delete(id);
		expectedEx.expect(EntityNotFoundException.class);
		expectedEx.expectMessage("Bu tür bir kullanýcý hiç yok. O yüzden silinmez");
		
	}

	@Test
	public void testListAll() {
		System.out.println("testListAll");
		List<Users> users = userDao.listAll();
		for(Users user : users) {
			System.out.println("Ad Soyad : " + user.getName() + " " + user.getSurname() + "| Email : " + user.getEmail());
		}
		assertTrue(users.size()>0);
	}

	@Test
	public void testCount() {
		System.out.println("testCount");
		long totalUsers = userDao.count();
		System.out.println("Toplam Kullanýcý Sayýsý " + totalUsers);
		assertEquals(4, totalUsers);
	}
	
	@Test
	public void testFindByEmail() {
		System.out.println("testFindByEmail");
		String email = "d@d.com";
		Users user = userDao.findByEmail(email);
		
		assertNotNull(user);
	}
	
	@Test
	public void testcheckLoginUserSuccess() {
		System.out.println("testcheckUser");
		String email = "d@d.com";
		String password = "321";
		
		boolean checkUser = userDao.checkUser(email, password);
		
		assertTrue(checkUser);
		
	}
	
	@Test
	public void testcheckLoginUserFailed() {
		System.out.println("testcheckUser");
		String email = "q@q.com";
		String password = "qqqqqq";
		
		boolean checkUser = userDao.checkUser(email, password);
		
		assertFalse(checkUser);
		
	}
	
	
	@AfterAll
	public static void tearDownClass() {
		System.out.println("tearDownClass");
		//sessionFactory.close();
		userDao.HibernateSessionFactoryClose();
	}

}
