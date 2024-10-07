package com.bookstore.books.services.implementation;

import java.util.List;

import com.bookstore.books.dao.ReviewDAO;
import com.bookstore.books.dao.implementation.ReviewDAOImplementation;
import com.bookstore.books.entities.Review;
import com.bookstore.books.entities.User;
import com.bookstore.books.services.ReviewService;

public class ReviewServiceImplementation implements ReviewService {

	private ReviewDAO reviewDAO;
	
	public ReviewServiceImplementation() {
		super();
		this.reviewDAO = new ReviewDAOImplementation();
		// TODO Auto-generated constructor stub
	}

	@Override
	public Review createReview(User user, String bookId, String reviewText, int rating) {
		// TODO Auto-generated method stub
		return reviewDAO.createReview(user, bookId, reviewText, rating);
	}

	@Override
	public Review getReviewById(int id) {
		// TODO Auto-generated method stub
		return reviewDAO.getReviewById(id);
	}

	@Override
	public List<Review> getAllReviews() {
		// TODO Auto-generated method stub
		return reviewDAO.getAllReviews();
	}

	@Override
	public List<Review> getReviewsForBook(String bookId) {
		// TODO Auto-generated method stub
		return reviewDAO.getReviewsForBook(bookId);
	}

	@Override
	public List<Review> getReviewsByUser(int userId) {
		// TODO Auto-generated method stub
		return reviewDAO.getReviewsByUser(userId);
	}

	@Override
	public Review updateReview(int id, String updatedReviewText, int updatedRating) {
		// TODO Auto-generated method stub
		return reviewDAO.updateReview(id, updatedReviewText, updatedRating);
	}

	@Override
	public boolean deleteReview(int id) {
		// TODO Auto-generated method stub
		return reviewDAO.deleteReview(id);
	}

}
