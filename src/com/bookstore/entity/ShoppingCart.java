package com.bookstore.entity;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import com.bookstore.service.BookService;

public class ShoppingCart {

	private Map<Book, Integer> cart = new HashMap<>();
	
	// add Item with its quantity into the cart
	public void addItem(Book book,int quantity) {
		if(cart.containsKey(book)) {
			Integer quantityOfCart = cart.get(book) + quantity;
			cart.put(book, quantityOfCart);
		}else {
			cart.put(book, quantity);
		}
	}
	
	// return cart
	public Map<Book, Integer> getCart(){
		return this.cart;
	}
	
	// remove book
	public void removeItem(Book book) {
		cart.remove(book);
	}
	
	
	// total Quantity
	public int getTotalQuantity() {
		int total = 0;
		
		Iterator<Book> iterator = cart.keySet().iterator();
		while(iterator.hasNext()) {
			Book book = iterator.next();
			Integer quantity = cart.get(book);
			total += quantity;		
		}
		
		return total;
	}
	
	// total Amount
	public double getTotalAmount() {
		double total = 0.0f;
		
		Iterator<Book> iterator = cart.keySet().iterator();
		while(iterator.hasNext()) {
			Book book = iterator.next();
			Integer quantity = cart.get(book);
			double subTotal = quantity * book.getPrice();
			total += subTotal;		
		}
		
		return total;
	}
	
	// Total Items
	public int getTotalItems() {
		return cart.size();
	}
	
	
	public void updateCart(int[] bookIds,int[] quantities) {
		for(int i=0;i<bookIds.length;i++) {
			Book key = new Book(bookIds[i]);
			Integer quantity = quantities[i];
			key.setQuantity(key.getQuantity() - quantity);
			cart.put(key, quantity);
		}
	}
	
	public void updateCart(Book key,int quantity) {
			cart.put(key, quantity);
	}
	
	// clearAllItems
	public void clearCart() {
		cart.clear();
	}
}
