package com.bookstore.books.services;

import java.util.List;

import com.bookstore.books.entities.Review;
import com.bookstore.books.entities.User;

public interface ReviewService {
    
    Review createReview(User user, String bookId, String reviewText, int rating);

    Review getReviewById(int id);

    List<Review> getAllReviews();

    List<Review> getReviewsForBook(String bookId);

    List<Review> getReviewsByUser(int userId);

    Review updateReview(int id, String updatedReviewText, int updatedRating);

    boolean deleteReview(int id);
}
