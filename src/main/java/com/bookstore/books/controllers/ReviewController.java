package com.bookstore.books.controllers;

import java.util.List;

import com.bookstore.books.entities.Review;
import com.bookstore.books.entities.User;
import com.bookstore.books.services.ReviewService;

public class ReviewController {
    private ReviewService reviewService;

    public ReviewController() {
        this.reviewService = new ReviewService(); // Assuming you have a ReviewService to handle the logic
    }

    // Creates a review for a given book and returns the created Review object
    public Review createReview(User user, int bookId, String reviewText, int rating) {
        // TODO: Implement review creation logic
        return reviewService.addReview(user, bookId, reviewText, rating); // Assuming addReview() returns the created Review
    }

    // Returns a Review object by its ID
    public Review getReviewById(int id) {
        // TODO: Implement logic to fetch a review by its ID
        return reviewService.getReviewById(id); // Assuming getReviewById() returns a Review
    }

    // Returns a list of all reviews
    public List<Review> getAllReviews() {
        // TODO: Implement logic to fetch all reviews
        return reviewService.getAllReviews(); // Assuming getAllReviews() returns a List of Review
    }

    // Returns a list of reviews for a specific book
    public List<Review> getReviewsForBook(int bookId) {
        // TODO: Implement logic to fetch reviews for a specific book by its ID
        return reviewService.getReviewsForBook(bookId); // Assuming getReviewsForBook() returns a List of Review
    }

    // Returns a list of reviews written by a specific user
    public List<Review> getReviewsByUser(int userId) {
        // TODO: Implement logic to fetch reviews by a specific user ID
        return reviewService.getReviewsByUser(userId); // Assuming getReviewsByUser() returns a List of Review
    }

    // Updates a review and returns the updated Review object
    public Review updateReview(int id, String updatedReviewText, int updatedRating) {
        // TODO: Implement logic to update a review by its ID
        return reviewService.updateReview(id, updatedReviewText, updatedRating); // Assuming updateReview() returns the updated Review
    }

    // Deletes a review by its ID and returns a boolean indicating success or failure
    public boolean deleteReview(int id) {
        // TODO: Implement logic to delete a review by its ID
        return reviewService.deleteReview(id); // Assuming deleteReview() returns a boolean
    }
}
