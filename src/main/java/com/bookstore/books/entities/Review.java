package com.bookstore.books.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "Reviews")
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ReviewID")
    private int reviewID;

    @ManyToOne
    @JoinColumn(name = "ISBN", nullable = false)
    private Book book;

    @ManyToOne
    @JoinColumn(name = "UserID", nullable = false)
    private User user;

    @Column(name = "Rating")
    private int rating;

    @Column(name = "ReviewText")
    private String reviewText;

	public Review(int reviewID, Book book, User user, int rating, String reviewText) {
		super();
		this.reviewID = reviewID;
		this.book = book;
		this.user = user;
		this.rating = rating;
		this.reviewText = reviewText;
	}

	public Review() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getReviewID() {
		return reviewID;
	}

	public void setReviewID(int reviewID) {
		this.reviewID = reviewID;
	}

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	public String getReviewText() {
		return reviewText;
	}

	public void setReviewText(String reviewText) {
		this.reviewText = reviewText;
	}

	@Override
	public String toString() {
		return "Review [reviewID=" + reviewID + ", book=" + book + ", user=" + user + ", rating=" + rating
				+ ", reviewText=" + reviewText + "]";
	}

    
    // Getters and Setters
}
