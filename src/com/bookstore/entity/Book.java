package com.bookstore.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;
import java.util.Arrays;
import java.util.Base64;
import java.util.Comparator;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

@Entity
@Table(name="BOOK",catalog = "JSPPROJECTDATABASE")
@NamedQueries({
	@NamedQuery(name = "Book.findAll",query = "SELECT b FROM Book b ORDER BY b.id ASC"),
	@NamedQuery(name = "Book.countAll",query = "SELECT COUNT(*) FROM Book b"),
	@NamedQuery(name = "Book.countByCategory",
	            query = "SELECT COUNT(b) FROM Book b LEFT JOIN Category c ON b.category.id =c.id WHERE c.id=:categoryId"),
	@NamedQuery(name = "Book.findByTitle",query = "SELECT b FROM Book b WHERE b.title =:title"),
	@NamedQuery(name = "Book.listByCategory",
				query = "SELECT b FROM Book b LEFT JOIN Category c ON b.category.id =c.id WHERE c.id=:categoryId"),
	@NamedQuery(name = "Book.listNewBook",query = "SELECT b FROM Book b ORDER BY b.publishDate DESC"),
	@NamedQuery(name = "Book.searchBook",
	            query = "SELECT b FROM Book b "
	            		+ "WHERE lower(b.title) LIKE '%' || :keyword || '%' "
	            		+ "OR lower(b.author) LIKE '%' || :keyword || '%' "
	            		+ "OR lower(b.description) LIKE '%' || :keyword || '%'")
})
public class Book implements Serializable{

	@Id
	@SequenceGenerator(name="BOOK_SEQ", sequenceName="BOOK_SEQ", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="BOOK_SEQ")
	@Column(name="BOOK_ID", nullable = false)
	private int id;
	
	@Column(name="TITLE")
	private String title;
	
	@Column(name="AUTHOR")
	private String author;
	
	@Column(name="DESCRIPTION")
	private String description;
	
	@Column(name="ISBN")
	private String isbn;
	
	@Column(name="IMAGE")
	private byte[] image;
	
	@Transient
	private String base64Image;
	
	@Transient
	private float averageRating;
	
	@Transient
	private String averageRatingString;
	
	@Transient
	private String averageRatingStar;
	
	@Column(name="PRICE")
	private float price;
	
	@Temporal(TemporalType.DATE)
	@Column(name="PUBLISH_DATE")
	private Date publishDate;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="LAST_UPDATED_DATE")
	private Date lastUpdatedDate;
	
	@Column(name="QUANTITY")
	private int quantity;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CATEGORY_ID", nullable = false)
	private Category category;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "bookReview")
	private Set<Review> reviews = new HashSet<Review>();
	
	@OneToMany(fetch = FetchType.EAGER,mappedBy = "pk.book")
	private Set<DetailOrder> detailOrders = new HashSet<DetailOrder>();

	public Book() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	public Book(int id) {
		super();
		this.id = id;
	}

	public Book(String title, String author, String description, String isbn, byte[] image, float price,
			Date publishDate, Date lastUpdatedDate, int quantity, Category category) {
		super();
		this.title = title;
		this.author = author;
		this.description = description;
		this.isbn = isbn;
		this.image = image;
		this.price = price;
		this.publishDate = publishDate;
		this.lastUpdatedDate = lastUpdatedDate;
		this.quantity = quantity;
		this.category = category;
	}
	
	public Book(String title, String author, String description, String isbn, byte[] image, String base64Image,
			float price, Date publishDate, Date lastUpdatedDate, int quantity, Category category, Set<Review> reviews,
			Set<DetailOrder> detailOrders) {
		super();
		this.title = title;
		this.author = author;
		this.description = description;
		this.isbn = isbn;
		this.image = image;
		this.base64Image = base64Image;
		this.price = price;
		this.publishDate = publishDate;
		this.lastUpdatedDate = lastUpdatedDate;
		this.quantity = quantity;
		this.category = category;
		this.reviews = reviews;
		this.detailOrders = detailOrders;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public byte[] getImage() {
		return image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}

	public String getBase64Image() {
		this.base64Image = Base64.getEncoder().encodeToString(this.image);
	    return base64Image;
	}

	public void setBase64Image(String base64Image) {
		this.base64Image = base64Image;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public Date getPublishDate() {
		return publishDate;
	}

	public void setPublishDate(Date publishDate) {
		this.publishDate = publishDate;
	}

	public Date getLastUpdatedDate() {
		return lastUpdatedDate;
	}

	public void setLastUpdatedDate(Date lastUpdatedDate) {
		this.lastUpdatedDate = lastUpdatedDate;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}
	

	public Set<Review> getReviews() {
		
		TreeSet<Review> sortedReview = new TreeSet<>(new Comparator<Review>() {

			@Override
			public int compare(Review review1, Review review2) {
				// TODO Auto-generated method stub
				return review2.getReviewDate().compareTo(review1.getReviewDate());
			}
		});
		sortedReview.addAll(reviews);
		return sortedReview;
	}

	public void setReviews(Set<Review> reviews) {
		this.reviews = reviews;
	}

	public Set<DetailOrder> getOrderDetails() {
		return detailOrders;
	}

	public void setOrderDetails(Set<DetailOrder> detailOrders) {
		this.detailOrders = detailOrders;
	}

	@Override
	public String toString() {
		return "Book [id=" + id + ", title=" + title + ", author=" + author + ", description=" + description + ", isbn="
				+ isbn + ", image=" + Arrays.toString(image) + ", base64Image=" + base64Image + ", price=" + price
				+ ", publishDate=" + publishDate + ", lastUpdatedDate=" + lastUpdatedDate + ", quantity=" + quantity
				+ ", category=" + category + ", reviews=" + reviews + ", orderDetails=" + detailOrders + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Book other = (Book) obj;
		if (id != other.id)
			return false;
		return true;
	}

	public float getAverageRating() {
		
		averageRating = 0.0f;
		float sum = 0.0f;
		
		if(reviews.isEmpty()) {
			return 0.0f;
		}
		
		for(Review review : reviews) {
			sum += review.getRating();
		}
		
		averageRating = sum / reviews.size();
		
		return averageRating;
	}
	
	public String getAverageRatingString(float averageRating) {
		
		String averageRatingString = "";
		
		int numberOfStarsOn = (int) averageRating;  // 2,5 -> 2
		
		for(int i=0;i<numberOfStarsOn;i++) {
			averageRatingString += "on,";  // 2 times on
		}
		

		int next = numberOfStarsOn + 1;  // 2 + 1 = 3
		
		if(averageRating > numberOfStarsOn) {  // 2,5 > 2
			averageRatingString += "half,";  // one times half
			next++;  // 3 + 1 = 4
		}
		
		for(int j=next;j<=5;j++) {  // 4, 5 
			averageRatingString += "off,";  //  putting off into the 4 th and 5th star element
		}
		
		averageRatingString = averageRatingString.substring(0, averageRatingString.length()-1);
		
		return averageRatingString;
	}
	
	public String getAverageRatingStar() {
		float averageRating = getAverageRating();
		
		return getAverageRatingString(averageRating);
	} 
	
}
