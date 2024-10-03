package com.bookstore.books.controllers;

import java.util.List;

import com.bookstore.books.entities.Payment;
import com.bookstore.books.services.PaymentService;
import com.bookstore.books.services.implementation.PaymentServiceImplementation;

public class PaymentController {
    private PaymentService paymentService;

    public PaymentController() {
        // Assuming you have an implementation of PaymentService
        this.paymentService = new PaymentServiceImplementation();
    }

    // Creates a payment for a given order ID and returns the created Payment object
    public Payment createPayment(int orderId, String paymentMethod, double amount) {
        try {
            // Validate the input parameters
            if (amount <= 0) {
                System.out.println("Amount must be greater than zero.");
                return null;
            }

            // Call PaymentService to create the payment
            Payment payment = paymentService.createPayment(orderId, paymentMethod, amount);
            if (payment != null) {
                System.out.println("Payment created successfully: " + payment);
            } else {
                System.out.println("Failed to create payment.");
            }
            return payment;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // Returns a Payment object by its ID
    public Payment getPaymentById(int id) {
        try {
            // Fetch payment by ID using the service
            Payment payment = paymentService.getPaymentById(id);
            if (payment != null) {
                System.out.println("Payment found: " + payment);
            } else {
                System.out.println("Payment not found.");
            }
            return payment;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // Returns a list of all payments
    public List<Payment> getAllPayments() {
        try {
            // Fetch all payments using the service
            List<Payment> payments = paymentService.getAllPayments();
            if (payments != null && !payments.isEmpty()) {
                System.out.println("All Payments:");
                for (Payment payment : payments) {
                    System.out.println(payment);
                }
            } else {
                System.out.println("No payments found.");
            }
            return payments;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // Returns a list of payments made by a specific user
    public List<Payment> getPaymentsByUser(int userId) {
        try {
            // Fetch payments by user ID using the service
            List<Payment> payments = paymentService.getPaymentsByUser(userId);
            if (payments != null && !payments.isEmpty()) {
                System.out.println("Payments by User ID " + userId + ":");
                for (Payment payment : payments) {
                    System.out.println(payment);
                }
            } else {
                System.out.println("No payments found for the user.");
            }
            return payments;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // Updates a payment and returns the updated Payment object
    public Payment updatePayment(int id, String newPaymentMethod, double newAmount) {
        try {
            if (newAmount <= 0) {
                System.out.println("Amount must be greater than zero.");
                return null;
            }

            // Fetch existing payment and update the details
            Payment updatedPayment = paymentService.updatePayment(id, newPaymentMethod, newAmount);
            if (updatedPayment != null) {
                System.out.println("Payment updated successfully: " + updatedPayment);
            } else {
                System.out.println("Failed to update payment.");
            }

            return updatedPayment;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // Deletes a payment by its ID and returns a boolean indicating success or failure
    public boolean deletePayment(int id) {
        try {
            // Call PaymentService to delete the payment
            boolean isDeleted = paymentService.deletePayment(id);
            if (isDeleted) {
                System.out.println("Payment deleted successfully.");
            } else {
                System.out.println("Failed to delete payment.");
            }

            return isDeleted;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
