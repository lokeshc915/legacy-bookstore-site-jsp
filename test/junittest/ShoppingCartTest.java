package junittest;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.Map;

import org.junit.Rule;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.rules.ExpectedException;

import com.bookstore.entity.Book;
import com.bookstore.entity.ShoppingCart;


public class ShoppingCartTest {

	private static ShoppingCart cart;

	@Rule
	public ExpectedException expectedEx = ExpectedException.none();

	// Code executed before first method is executed
	@BeforeAll
	public static void setUpClass() {
		System.out.println("setUpClass");
		cart = new ShoppingCart();
	}

	@Test
	public void testAddItemWithQuantityIntoTheCartOnlyOneItem() {
		System.out.println("testAddItemWithQuantityIntoTheCartOnlyOneItem");
		cart = new ShoppingCart();
		
		Book book = new Book();
		book.setId(24);
		
		cart.addItem(book, 1);
		
		Map<Book, Integer> items = cart.getCart();
		int quantity = items.get(book);
		System.out.println("Quantity of Book : " + quantity);
		
		assertEquals(1, quantity);
		
	}
	
	@Test
	public void testAddItemWithQuantityIntoTheCart() {
		System.out.println("testAddItemWithQuantityIntoTheCart");
		
		Book book = new Book();
		book.setId(24);
		
		cart.addItem(book, 1);
		cart.addItem(book, 1);
		
		Map<Book, Integer> items = cart.getCart();
		int quantity = items.get(book);
		System.out.println("Quantity of Book : " + quantity);
		
		assertEquals(2, quantity);
		
	}
	
	@Test
	public void testRemoveItem() {
		System.out.println("testRemoveItem");
		
		Book book = new Book();
		book.setId(24);
		
		cart.addItem(book, 1);
		
		cart.removeItem(book);
		
		assertTrue(cart.getCart().isEmpty());
		
	}
	
	@Test
	public void testRemoveItemWithSpecificItem() {
		System.out.println("testRemoveItemWithSpecificItem");
		
		Book book1 = new Book();
		book1.setId(24);
		
		cart.addItem(book1, 1);
		cart.addItem(book1, 1);
		
		Book book2 = new Book();
		book2.setId(7);
		cart.addItem(book2, 1);
		
		cart.removeItem(book2);
		
		assertNull(cart.getCart().get(book2));
		
	}
	
	@Test
	public void testTotalQuantity() {
		System.out.println("testTotalQuantity");
		
		Book book1 = new Book();
		book1.setId(24);
		
		cart.addItem(book1, 1);
		cart.addItem(book1, 1);
		
		Book book2 = new Book();
		book2.setId(7);
		cart.addItem(book2, 1);
		
		int totalQuantity = cart.getTotalQuantity();
		System.out.println("Total Quantity : " + totalQuantity);
		assertEquals(3, totalQuantity);
	}
	
	@Test
	public void testTotalAmountEmpty() {
		System.out.println("testTotalAmountEmpty");
		assertEquals(0.0f, cart.getTotalAmount());
	}
	
	@Test
	public void testTotalAmount() {
		System.out.println("testTotalAmount");
		
		Book book1 = new Book();
		book1.setId(24);
		book1.setPrice(20);
		
		cart.addItem(book1, 1);
		cart.addItem(book1, 1);
		
		Book book2 = new Book();
		book2.setId(7);
		book2.setPrice(10);
		cart.addItem(book2, 1);
		
		
		double totalAmount = cart.getTotalAmount();
		System.out.println("Total Amount : " + totalAmount);
		assertEquals(50.0f, totalAmount);
	}
	
	@Test
	public void testUpdateCart() {
		System.out.println("testUpdateCart");
		
		Book book1 = new Book();
		book1.setId(24);
		
		cart.addItem(book1, 1);
		
		Book book2 = new Book();
		book2.setId(7);
		cart.addItem(book2, 1);
		
		int[] bookIds = {24,7};
		int[] quantities = {3,4};
		
		cart.updateCart(bookIds, quantities);
		System.out.println("Total Quality of Cart : " + cart.getTotalQuantity());
		assertEquals(7, cart.getTotalQuantity());
	}
	
	@Test
	public void testClearCart() {
		cart.clearCart();
		
		int totalQuantity = cart.getTotalQuantity();
		System.out.println("Total Quantity : " + totalQuantity);
		assertEquals(0, totalQuantity);
		
	}

	@AfterAll
	public static void tearDownClass() {
		System.out.println("tearDownClass");
		
	}
}
