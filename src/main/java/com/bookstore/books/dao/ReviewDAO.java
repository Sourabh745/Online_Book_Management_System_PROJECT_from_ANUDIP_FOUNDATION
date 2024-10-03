package com.bookstore.books.dao;

import java.util.List;

import com.bookstore.books.entities.Review;
import com.bookstore.books.entities.User;

public interface ReviewDAO {
	Review createReview(User user, int bookId, String reviewText, int rating);

    Review getReviewById(int id);

    List<Review> getAllReviews();

    List<Review> getReviewsForBook(int bookId);

    List<Review> getReviewsByUser(int userId);

    Review updateReview(int id, String updatedReviewText, int updatedRating);

    boolean deleteReview(int id);
}
