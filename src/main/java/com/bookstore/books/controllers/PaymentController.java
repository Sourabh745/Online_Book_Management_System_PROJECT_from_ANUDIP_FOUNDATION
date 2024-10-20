package com.bookstore.books.controllers;

import java.util.List;
import java.util.Scanner;

import com.bookstore.books.entities.Book;
import com.bookstore.books.entities.Orders;
import com.bookstore.books.entities.Payment;
import com.bookstore.books.entities.User;
import com.bookstore.books.services.OrderService;
import com.bookstore.books.services.PaymentService;
import com.bookstore.books.services.implementation.OrderServiceImplementation;
import com.bookstore.books.services.implementation.PaymentServiceImplementation;

public class PaymentController {
    private PaymentService paymentService;
    private OrderService orderService;

    public PaymentController() {
        // Assuming you have an implementation of PaymentService
        this.paymentService = new PaymentServiceImplementation();
        this.orderService = new OrderServiceImplementation();
    }

    // Creates a payment for a given order ID and returns the created Payment object
    public void createPayment(User loggedInUser) {
        try {
            // Step 1: Fetch orders by user ID
            List<Orders> orderList = orderService.getOrdersByUser(loggedInUser.getUserID());
            
            if (orderList != null && !orderList.isEmpty()) {
                System.out.println("Available Orders:");
                for (Orders order : orderList) {
                    System.out.println("Order ID: " + order.getOrderID() + 
                                       " | Status: " + order.getStatus() + 
                                       " | Total Cost: " + order.getTotalCost());
                }
                
                // Step 2: Choose the order to make payment for
                System.out.println("Enter the Order ID to make payment for:");
                Scanner scanner = new Scanner(System.in);
                int selectedOrderId = scanner.nextInt();
                
                // Step 3: Find the selected order
                Orders selectedOrder = null;
                for (Orders order : orderList) {
                    if (order.getOrderID() == selectedOrderId) {
                        selectedOrder = order;
                        break;
                    }
                }
                
                if (selectedOrder == null) {
                    System.out.println("Invalid Order ID selected.");
                    return;
                }
                
                // Step 4: Verify order status and payment
                if (selectedOrder.getStatus().equalsIgnoreCase("Paid")) {
                    System.out.println("This order has already been paid for.");
                    return;
                }
                
                if (selectedOrder.getStatus().equalsIgnoreCase("COD")) {
                    System.out.println("This order is already marked for Cash on Delivery.");
                    return;
                }
                
                selectedOrder.setStatus("Awaiting Delivery with COD");                
                
                
               Orders newOrder = orderService.updateOrder(selectedOrder);
               if (newOrder != null) {
                   System.out.println("Order " + selectedOrderId + " has been successfully marked for Cash on Delivery.");
               } else {
                   System.out.println("Failed to create order.");
               }
                
            } else {
                System.out.println("No orders available for this user.");
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

//        try {
//            // Validate the input parameters
//        	
//            if (amount <= 0) {
//                System.out.println("Amount must be greater than zero.");
//                return null;
//            }
//
//            // Call PaymentService to create the payment
//            Payment payment = paymentService.createPayment(orderId, paymentMethod, amount);
//            if (payment != null) {
//                System.out.println("Payment created successfully: " + payment);
//            } else {
//                System.out.println("Failed to create payment.");
//            }
//            return payment;
//        } catch (Exception e) {
//            e.printStackTrace();
//            return null;
//        }
 //   }

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
