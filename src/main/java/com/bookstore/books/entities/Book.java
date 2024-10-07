package com.bookstore.books.entities;
import jakarta.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "Books")
public class Book {

    @Id
    @Column(name = "BookID", length = 13)
    private String bookId;

    @Column(name = "Title", nullable = false)
    private String title;

    @ManyToOne
    @JoinColumn(name = "AuthorID", nullable = false)
    private Author author;

    @Column(name = "description")
    private String description;

    @Column(name = "PublicationDate")
    private Date publicationDate;

    @Column(name = "Price")
    private double price;

    @Column(name = "Quantity")
    private int quantity;

    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<OrderItems> orderItems;

    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Review> reviews;

    public Book(String bookId, String title, Author author, String description, Date publicationDate, double price,
			int quantity, List<OrderItems> orderItems, List<Review> reviews) {
		super();
		this.bookId = bookId;
		this.title = title;
		this.author = author;
		this.description = description;
		this.publicationDate = publicationDate;
		this.price = price;
		this.quantity = quantity;
		this.orderItems = orderItems;
		this.reviews = reviews;
	}
    public Book(String bookId, String title, Author author, String description,double price, int quantity) {
		super();
		this.bookId = bookId;
		this.title = title;
		this.author = author;
		this.description = description;
		this.price = price;
		this.quantity = quantity;
	}

	public Book(String bookId, String title, Author author, double price) {
		super();
		this.bookId = bookId;
		this.title = title;
		this.author = author;
		this.price = price;
	}
	public Book() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getBookId() {
		return bookId;
	}

	public void setBookId(String bookId) {
		this.bookId = bookId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Author getAuthor() {
		return author;
	}

	public void setAuthor(Author author) {
		this.author = author;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getPublicationDate() {
		return publicationDate;
	}

	public void setPublicationDate(Date publicationDate) {
		this.publicationDate = publicationDate;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public List<OrderItems> getOrderItems() {
		return orderItems;
	}

	public void setOrderItems(List<OrderItems> orderItems) {
		this.orderItems = orderItems;
	}

	public List<Review> getReviews() {
		return reviews;
	}

	public void setReviews(List<Review> reviews) {
		this.reviews = reviews;
	}

	@Override
	public String toString() {
		return "Book [bookId=" + bookId + ", title=" + title + ", author=" + (author != null ? author.getName() : "N/A") + ", description=" + description
				+ ", publicationDate=" + publicationDate + ", price=" + price + ", quantity=" + quantity
				+ "]";
	}

    
    // Getters and Setters
}
