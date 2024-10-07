package com.bookstore.books.controllers;

import java.util.List;
import java.util.Scanner;

import com.bookstore.books.entities.Review;
import com.bookstore.books.entities.User;
import com.bookstore.books.services.ReviewService;
import com.bookstore.books.services.UserService;
import com.bookstore.books.services.implementation.ReviewServiceImplementation;
import com.bookstore.books.services.implementation.UserServiceImplementation;

public class ReviewController {
    private ReviewService reviewService;
    private UserService userService;
    private UserController userController;
    private Scanner scanner;
    private User loggedInUser;


    public ReviewController() {
        this.reviewService = new ReviewServiceImplementation();
        this.userService = new UserServiceImplementation();
        this.scanner = new Scanner(System.in);
        this.loggedInUser = null;  // No user logged in initially

    }

    public void showMenu(User loggedInUser) {
    	this.loggedInUser = loggedInUser; // Set logged-in user
    	
        while (true) {
        	if (this.loggedInUser == null) {
                System.out.println("You need to login first.");
                userController.loginUser(); // After login, set the loggedInUser
                this.loggedInUser = userController.loginUser();  // Assuming this method returns the logged-in user
            } 
        	else {
            System.out.println("\nReview Management Menu:");
            System.out.println("1. Add a Review");
            System.out.println("2. Update a Review");
            System.out.println("3. Delete a Review");
            System.out.println("4. View All Reviews");
            System.out.println("5. View Reviews by User");
            //System.out.println("6. View Review by ID");
            System.out.println("6. View Review of Book");
            System.out.println("7. Exit");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    addReview(loggedInUser);
                    break;
                case 2:
                    updateReview();
                    break;
                case 3:
                    deleteReview();
                    break;
                case 4:
                    viewAllReviews();
                    break;
                case 5:
                    viewReviewsByUser();
                    break;
//                case 6:
//                    viewReviewById();
//                    break;
                case 6:
                	viewReviewsByBook();
                	break;
                case 7:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        	}
        }
    }

    // Add Review
    private void addReview(User loggedInUser) {
    	 if (loggedInUser == null) {
    	        System.out.println("You need to log in first.");
    	        return;
    	    }

        User user = userService.getUserDetails(loggedInUser.getUserID());
        if (user == null) {
            System.out.println("User not found.");
            return;
        }

        System.out.print("Enter book ID: ");
        String bookId = scanner.nextLine();

        
        System.out.print("Enter your review text: ");
        String reviewText = scanner.nextLine();

        System.out.print("Enter your rating (1-5): ");
        int rating = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        Review newReview = reviewService.createReview(user, bookId, reviewText, rating);
        if (newReview != null) {
            System.out.println("Review added successfully.");
        } else {
            System.out.println("Failed to add review.");
        }
    }

    // Update Review
 // Update Review
    private void updateReview() {
        System.out.print("Enter review ID to update: ");
        int reviewId = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        Review review = reviewService.getReviewById(reviewId);

        if (review == null) {
            System.out.println("Review not found.");
            return;
        }

        // Check if the logged-in user is the owner of the review
        if (loggedInUser == null || review.getUser().getUserID() != loggedInUser.getUserID()) {
            System.out.println("You can only update your own reviews.");
            return;
        }

        System.out.print("Enter new review text (leave blank to keep current): ");
        String newText = scanner.nextLine();

        System.out.print("Enter new rating (leave blank to keep current): ");
        String newRatingInput = scanner.nextLine();
        int newRating = newRatingInput.isEmpty() ? -1 : Integer.parseInt(newRatingInput);

        Review updatedReview = reviewService.updateReview(reviewId, newText, newRating);
        if (updatedReview != null) {
            System.out.println("Review updated successfully.");
        } else {
            System.out.println("Failed to update review.");
        }
    }
    

    // Delete Review
    private void deleteReview() {
        System.out.print("Enter review ID to delete: ");
        int reviewId = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        Review review = reviewService.getReviewById(reviewId);

        if (review == null) {
            System.out.println("Review not found.");
            return;
        }

        // Check if the logged-in user is the owner of the review
        if (loggedInUser == null || review.getUser().getUserID() != loggedInUser.getUserID()) {
            System.out.println("You can only delete your own reviews.");
            return;
        }

        boolean isDeleted = reviewService.deleteReview(reviewId);
        if (isDeleted) {
            System.out.println("Review deleted successfully.");
        } else {
            System.out.println("Failed to delete review.");
        }
    }



    // View All Reviews
    private void viewAllReviews() {
    	if (loggedInUser.getUsername() != "admin") {
            System.out.println("You must be logged in to view your orders.");
            return;
        }
        List<Review> reviews = reviewService.getAllReviews();
        if (reviews != null && !reviews.isEmpty()) {
            for (Review review : reviews) {
                System.out.println(review);
            }
        } else {
            System.out.println("No reviews found.");
        }
    }

    // View Reviews by User
    private void viewReviewsByUser() {
        // Check if the user is logged in
        if (loggedInUser == null) {
            System.out.println("You must be logged in to view your reviews.");
            return;
        }

        // Fetch reviews for the logged-in user
        List<Review> reviews = reviewService.getReviewsByUser(loggedInUser.getUserID());
        
        if (reviews != null && !reviews.isEmpty()) {
            for (Review review : reviews) {
                System.out.println(review);
            }
        } else {
            System.out.println("No reviews found for you.");
        }
    }

    // View Reviews by Book
    private void viewReviewsByBook() {
        System.out.print("Enter book ID: ");
        String bookId = scanner.nextLine();
        scanner.nextLine(); // Consume newline

        List<Review> reviews = reviewService.getReviewsForBook(bookId);
        if (reviews != null && !reviews.isEmpty()) {
            for (Review review : reviews) {
                System.out.println(review);
            }
        } else {
            System.out.println("No reviews found for this book.");
        }
    }

    // View Review by ID
    private void viewReviewById() {
        System.out.print("Enter review ID: ");
        int reviewId = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        Review review = reviewService.getReviewById(reviewId);
        if (review != null) {
            System.out.println(review);
        } else {
            System.out.println("Review not found.");
        }
    }
}
