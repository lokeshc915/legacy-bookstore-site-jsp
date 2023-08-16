package junittest;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.junit.Rule;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.rules.ExpectedException;

import com.bookstore.dao.OrdersDAO;
import com.bookstore.entity.Book;
import com.bookstore.entity.Customer;
import com.bookstore.entity.DetailOrder;
import com.bookstore.entity.BookOrders;

public class OrdersDaoTest {
	
	static OrdersDAO ordersDAO   = null;
	
	@Rule
	public ExpectedException expectedEx = ExpectedException.none();


	// Code executed before first method is executed
	@BeforeAll
	public static void setUpClass(){
		System.out.println("setUpClass");
		
		ordersDAO = new OrdersDAO();
	}

	@Test
	public void testCreateOrder() {
		System.out.println("testCreateOrder");
		BookOrders bookorders = new BookOrders();
		
		Customer customer = new Customer();
		customer.setId(42);
		
		bookorders.setCustomer(customer);
		bookorders.setRecipentName("Recipent Name");
		bookorders.setRecipentPhone("123456789");
		bookorders.setShippingAddress("Address");
		
		Set<DetailOrder> orderDetails = new HashSet<DetailOrder>();
		DetailOrder detailOrder = new DetailOrder();
		
		Book book = new Book(43);
		detailOrder.setBook(book);
		detailOrder.setQuantity(1);
		detailOrder.setSubTotal(35.75f);
		detailOrder.setOrders(bookorders);
		
		orderDetails.add(detailOrder);
		
		bookorders.setOrderDetails(orderDetails);
		
		BookOrders savedOrders = ordersDAO.create(bookorders);
		
		assertNotNull(savedOrders);
		//assertTrue(savedOrders.getId() > 0);
		
		
	}
	
	@Test
	public void testCreateBookOrderTwoBook() {
		BookOrders bookorders = new BookOrders();
		
		Customer customer = new Customer();
		customer.setId(46);
		
		bookorders.setCustomer(customer);
		bookorders.setRecipentName("Recipent Name 1");
		bookorders.setRecipentPhone("0123456789");
		bookorders.setShippingAddress("Home Address");
		
		Set<DetailOrder> orderDetails = new HashSet<DetailOrder>();
		
		Book book1 = new Book(41);
		DetailOrder detailOrder1 = new DetailOrder();
		detailOrder1.setBook(book1);
		detailOrder1.setQuantity(1);
		detailOrder1.setSubTotal(35.75f);
		detailOrder1.setOrders(bookorders);
		
		orderDetails.add(detailOrder1);

		Book book2 = new Book(4);
		DetailOrder detailOrder2 = new DetailOrder();
		detailOrder2.setBook(book2);
		detailOrder2.setQuantity(1);
		detailOrder2.setSubTotal(32f);
		detailOrder2.setOrders(bookorders);
		
		orderDetails.add(detailOrder2);
		
		bookorders.setOrderDetails(orderDetails);
		
		ordersDAO.create(bookorders);
		
		assertTrue(bookorders.getId() > 0 && bookorders.getOrderDetails().size() == 2);
		
	}	
	
	@Test
	public void testGetOrder() {
		Integer orderId = 60;
		BookOrders bookorders = ordersDAO.get(orderId);
		
		System.out.println("BOOK ORDER #" + orderId);
		System.out.println("Book Recipent Name : " + bookorders.getRecipentName());
		System.out.println("Book Recipent Phone : " + bookorders.getRecipentPhone());
		System.out.println("Book Shipping Address : " + bookorders.getShippingAddress());
		System.out.println("Book Order Status : " + bookorders.getOrderStatus());
		System.out.println("Book Order Total : " + bookorders.getOrderTotal());
		System.out.println("Book Payment Method : " + bookorders.getPaymentMethod());
		
		
		assertEquals(1, bookorders.getOrderDetails().size());
	}
	
	@Test
	public void testListAll() {
		List<BookOrders> listOrders = ordersDAO.listAll();
		
		for (BookOrders order : listOrders) {
			System.out.println("Order Id : " + order.getId());
			System.out.println("Order Customer Full Name : " + order.getCustomer().getFullName());
			System.out.println("Order Total : " + order.getOrderTotal());
			System.out.println("Order Status : " + order.getOrderStatus());

			for (DetailOrder detail : order.getOrderDetails()) {
				Book book = detail.getBook();
				int quantity = detail.getQuantity();
				float subtotal = detail.getSubTotal();
				System.out.println("\tBook Title : " + book.getTitle() 
				                   + "| Book Quantity : " + quantity + " | Subtotal : " + subtotal);
			}
			System.out.println("----------------------------------------------------------------------------");
		}
		
		assertTrue(listOrders.size() > 0);
	}
	
	@Test
	public void testUpdateBookOrdersShippingAddress() {
		Integer orderId = 59;
		BookOrders bookorders = ordersDAO.get(orderId);
		
		System.out.println("Update Before (ShippingAddress) : " + bookorders.getShippingAddress());
		
		bookorders.setShippingAddress("New Home Shipping Address");
		
		try {
			ordersDAO.update(bookorders);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		BookOrders updatedOrder = ordersDAO.get(orderId);
		
		System.out.println("Update After (ShippingAddress) : " + updatedOrder.getShippingAddress() );
		
		assertEquals(bookorders.getShippingAddress(), updatedOrder.getShippingAddress());
		
	}
	
	@Test
	public void testUpdateBookOrderDetail() {
		Integer orderId = 48;
		BookOrders order = ordersDAO.get(orderId);
		
		Iterator<DetailOrder> iterator = order.getOrderDetails().iterator();
		
		while (iterator.hasNext()) {
			DetailOrder orderDetail = iterator.next();
			if (orderDetail.getBook().getId() == 24) {
				orderDetail.setQuantity(2);
				orderDetail.setSubTotal(67.98f);
			}
		}
			
		
		ordersDAO.update(order);
		
		BookOrders updatedOrder = ordersDAO.get(orderId);
		
		iterator = updatedOrder.getOrderDetails().iterator();
		
		int expectedQuantity = 2;
		float expectedSubtotal = 67.98f;
		int actualQuantity = 0;
		float actualSubtotal = 0;
		
		while (iterator.hasNext()) {
			DetailOrder orderDetail = iterator.next();
			if (orderDetail.getBook().getId() == 24) {
				actualQuantity = orderDetail.getQuantity();
				System.out.println("actualQuantity : " + actualQuantity);
				actualSubtotal = orderDetail.getSubTotal();
				System.out.println("actualSubtotal : " + actualSubtotal);
			}
		}		
		
		assertEquals(expectedQuantity, actualQuantity);
		//assertEquals(expectedSubtotal, actualSubtotal, 0.0f);
		
	}
	
	@Test
	public void testGetByIdAndCustomerNull() {
		Integer orderId = 10;
		Integer customerId = 99;
	
		BookOrders bookorders = ordersDAO.get(orderId, customerId);
		
		assertNull(bookorders);
	}

	@Test
	public void testGetByIdAndCustomerNotNull() {
		Integer orderId = 59;
		Integer customerId = 46;
	
		BookOrders bookorders = ordersDAO.get(orderId, customerId);
		
		assertNotNull(bookorders);
	}	
	
	@Test
	public void testDeleteOrder() {
		int orderId = 59;
		
		try {
			ordersDAO.delete(orderId);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		
		BookOrders bookorders = ordersDAO.get(orderId);
		
		if(bookorders!=null) {
			System.out.println("Order # " + orderId + " silmedi.");
		}else {
			System.out.println("Order # " + orderId + " silindi.");
		}
		
		assertNull(bookorders);
	}
	
	@Test
	public void testCount() {
		long totalOrders = ordersDAO.count();
		assertEquals(2, totalOrders);
	}
	
	@Test
	public void testListByCustomerNoOrders() {
		Integer customerId = 99;
		List<BookOrders> listOrders = ordersDAO.listByCustomer(customerId);
		
		assertTrue(listOrders.isEmpty());
	}
	
	@Test
	public void testListByCustomerHaveOrders() {
		Integer customerId = 46;
		List<BookOrders> listOrders = ordersDAO.listByCustomer(customerId);
		System.out.println("#" + customerId + " Orders Size : " + listOrders.size());
		assertTrue(listOrders.size() > 0);
	}
	
	@Test
	public void testListMostRecentSales() {
		List<BookOrders> listOrders = ordersDAO.listMostRecentSales();
		assertEquals(3, listOrders.size());
	}
	
	
	@AfterAll
	public static void tearDownClass() {
		System.out.println("tearDownClass");
		
		ordersDAO.HibernateSessionFactoryClose();
	}

}
