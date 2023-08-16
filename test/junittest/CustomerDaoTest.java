package junittest;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.Rule;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.rules.ExpectedException;

import com.bookstore.dao.CustomerDao;
import com.bookstore.entity.City;
import com.bookstore.entity.Customer;
import com.bookstore.entity.District;

public class CustomerDaoTest {

	static CustomerDao customerDao = null;

	@Rule
	public ExpectedException expectedEx = ExpectedException.none();

	// Code executed before first method is executed
	@BeforeAll
	public static void setUpClass() {
		System.out.println("setUpClass");

		customerDao = new CustomerDao();
	}

	@Test
	public void testCreateCustomer() {
		System.out.println("testCreateCustomer");
		Customer customer = new Customer();
		customer.setName("Ersin");
		customer.setSurname("Ulubatþýl");
		customer.setEmail("e@u.com");
		customer.setPassword("secret91");
		customer.setPhoneNumber("0987654321");
		
		City city = new City();
		city.setId(34);
		city.setCityName("Ýstanbul");
		
		District district = new District();
		district.setId(6);
		district.setDistrictName("Karaköy");
		
		customer.setAddress("Çarþý Cad. No:5/4");
		customer.setZipCode("34600");
		
		customer.setCity(city);
		customer.setDistrict(district);
		
		Customer savedCustomer = customerDao.create(customer);
		assertTrue(savedCustomer.getId() >0);
		
	}
	
	@Test
	public void testGetCustomer() {
		System.out.println("testGetCustomer");
		Integer customerId = 43;
		Customer customer = customerDao.get(customerId);
		System.out.println("---------------------------");
		System.out.println("Customer Information");
		System.out.println("Customer Name : " + customer.getName());
		System.out.println("Customer Name City : " + customer.getCity().getCityName());
		System.out.println("Customer Name District : " + customer.getDistrict().getDistrictName());
		
		assertNotNull(customer);
	}
	
	@Test
	public void testCustomerUpdate() {
		System.out.println("testCustomerUpdate");
		Integer customerId = 13;
		Customer customer = customerDao.get(customerId);
		System.out.println("---------------------------");
		City city = new City();
		city.setId(34);
		city.setCityName("Ýstanbul");
		
		District district = new District();
		district.setId(6);
		district.setDistrictName("Karaköy");
		
		customer.setCity(city);
		customer.setDistrict(district);
		
		Customer updatedCustomer = customerDao.update(customer);
		assertTrue(updatedCustomer.getCity().getCityName().equals(city.getCityName()) && 
				updatedCustomer.getDistrict().getDistrictName().equals(district.getDistrictName()));
	}

	@Test
	public void testDeleteCustomer() {
		System.out.println("testDeleteCustomer");
		Integer customerId = 13;
		customerDao.delete(customerId);
		
		Customer customer = customerDao.get(customerId);
		if(customer== null) {
			System.out.println("Obje silinmiþ");
		}
		
		assertNull(customer);
	}

	@Test
	public void testCustomerListAll() {
		System.out.println("testCustomerListAll");
		List<Customer> customerList = customerDao.listAll();
		assertFalse(customerList.isEmpty());
	}

	@Test
	public void testCustomerCount() {
		System.out.println("testCustomerCount");
		long totalCustomers = customerDao.count();
		System.out.println("Toplam Müþteri Sayýsý " + totalCustomers);
		assertEquals(10, totalCustomers);
	}
	
	@Test
	public void testCustomerFindByEmail() {
		System.out.println("testCustomerFindByEmail");
		String email = "o@o.com";
		Customer customer = customerDao.findByEmail(email);
		System.out.println("Aranan Müþteri Email :" +customer.getEmail());
		assertNotNull(customer);
		
	}
	
	@Test
	public void testcheckLoginCustomerSuccess() {
		System.out.println("testcheckLoginCustomerSuccess");
		String email = "e@a.com";
		String password = "secret90";
		
		boolean checkCustomer = customerDao.checkUser(email, password);
		if(checkCustomer) {
			System.out.println("testcheckLoginCustomerSuccess : Giriþ Baþarýlý");
		}else {
			System.out.println("testcheckLoginCustomerSuccess : Giriþ Baþarýsýz");
		}
		assertTrue(checkCustomer);
		
	}
	
	@Test
	public void testcheckLoginCustomerFailed() {
		System.out.println("testcheckLoginCustomerFailed");
		String email = "e@aaaaa.com";
		String password = "secret90";
		
		boolean checkCustomer = customerDao.checkUser(email, password);
		if(checkCustomer) {
			System.out.println("testcheckLoginCustomerSuccess : Giriþ Baþarýlý");
		}else {
			System.out.println("testcheckLoginCustomerSuccess : Giriþ Baþarýsýz");
		}
		assertFalse(checkCustomer);
		
	}

	@AfterAll
	public static void tearDownClass() {
		System.out.println("tearDownClass");
		customerDao.HibernateSessionFactoryClose();
	}
}
