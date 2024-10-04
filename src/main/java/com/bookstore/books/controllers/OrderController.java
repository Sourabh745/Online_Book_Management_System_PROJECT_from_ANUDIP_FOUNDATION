package com.bookstore.books.controllers;

import java.util.List;
import java.util.Scanner;

import com.bookstore.books.entities.OrderItems;
import com.bookstore.books.entities.Orders;
import com.bookstore.books.entities.Payment;
import com.bookstore.books.entities.User;
import com.bookstore.books.services.OrderService;
import com.bookstore.books.services.UserService;
import com.bookstore.books.services.implementation.OrderServiceImplementation;
import com.bookstore.books.services.implementation.UserServiceImplementation;

public class OrderController {
    private OrderService orderService;
    private UserService userService;
    private Scanner scanner;
    private User loggedInUser;

    public OrderController() {
        this.orderService = new OrderServiceImplementation();
        this.userService = new UserServiceImplementation();
        this.scanner = new Scanner(System.in);
        this.loggedInUser = null;  // No user logged in initially
    }

    public void showMenu() {
        while (true) {
            if (loggedInUser == null) {
                System.out.println("You need to login first.");
                login();
            } else {
                System.out.println("Order Menu:");
                System.out.println("1. Create Order");
                System.out.println("2. View All Orders");
                System.out.println("3. View Order by ID");
                System.out.println("4. Update Order");
                System.out.println("5. Delete Order");
                System.out.println("6. Logout");
                System.out.println("7. Exit");
                System.out.print("Choose an option: ");

                int choice = scanner.nextInt();
                scanner.nextLine();  // Consume newline

                switch (choice) {
                    case 1:
                        createOrder();
                        break;
                    case 2:
                        viewAllOrders();
                        break;
                    case 3:
                        viewOrderById();
                        break;
                    case 4:
                        updateOrder();
                        break;
                    case 5:
                        deleteOrder();
                        break;
                    case 6:
                        logout();
                        break;
                    case 7:
                        System.out.println("Exiting...");
                        return;
                    default:
                        System.out.println("Invalid choice. Try again.");
                }
            }
        }
    }

    // Login method to allow users to log in
    private void login() {
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        loggedInUser = userService.authenticateUser(username, password);

        if (loggedInUser != null) {
            System.out.println("Login successful! Welcome " + loggedInUser.getUsername());
        } else {
            System.out.println("Invalid credentials. Please try again.");
        }
    }

    // Logout method to log out the user
    private void logout() {
        System.out.println("Logging out...");
        loggedInUser = null;
    }

    // Create Order
    public void createOrder() {
        if (loggedInUser == null) {
            System.out.println("You must be logged in to create an order.");
            return;
        }

        System.out.print("Enter payment method (e.g., Credit Card): ");
        String payMethod = scanner.nextLine();
        Payment payment = new Payment();
        payment.setPaymentMethod(payMethod);

        System.out.println("Enter order items (Enter 0 to stop):");
        List<OrderItems> orderItems = orderService.collectOrderItems();  // Collect order items from the user

        // Calculate total cost
        double totalCost = orderItems.stream().mapToDouble(item -> item.getPrice() * item.getQuantity()).sum();

        // Set order status (initially pending)
        String status = "Pending";

        // Create the order
        Orders newOrder = orderService.createOrder(loggedInUser, orderItems, payment, totalCost, status);

        if (newOrder != null) {
            System.out.println("Order created successfully: " + newOrder);
        } else {
            System.out.println("Failed to create order.");
        }
    }

    // View All Orders (for the logged-in user)
    public void viewAllOrders() {
        if (loggedInUser == null) {
            System.out.println("You must be logged in to view your orders.");
            return;
        }

        List<Orders> orders = orderService.getOrdersByUser(loggedInUser.getUserID());
        if (orders != null && !orders.isEmpty()) {
            System.out.println("Your Orders:");
            for (Orders order : orders) {
                System.out.println(order);
            }
        } else {
            System.out.println("No orders found.");
        }
    }

    // View Order by ID (for the logged-in user)
    public void viewOrderById() {
    	loggedInUser.getUserID();
        if (loggedInUser == null) {
            System.out.println("You must be logged in to view your orders.");
            return;
        }

        System.out.print("Enter order ID: ");
        int orderId = scanner.nextInt();
        scanner.nextLine();  // Consume newline

        Orders order = orderService.getOrderById(orderId);
        if (order != null && order.getUser().getUserID() == loggedInUser.getUserID()) {
            System.out.println("Order details: " + order);
        } else {
            System.out.println("Order not found or you don't have permission to view this order.");
        }
    }

    // Update Order (for the logged-in user)
    public void updateOrder() {
        if (loggedInUser == null) {
            System.out.println("You must be logged in to update your orders.");
            return;
        }

        System.out.print("Enter order ID to update: ");
        int orderId = scanner.nextInt();
        scanner.nextLine();  // Consume newline

        Orders existingOrder = orderService.getOrderById(orderId);
        if (existingOrder == null || existingOrder.getUser().getUserID() != loggedInUser.getUserID()) {
            System.out.println("Order not found or you don't have permission to update this order.");
            return;
        }

        System.out.print("Enter updated payment method (e.g., Credit Card) or leave blank to keep current: ");
        String updatedPaymentMethod = scanner.nextLine();
        List<Payment> payments = existingOrder.getPayments();
        if (!updatedPaymentMethod.isEmpty()) {
            Payment updatedPayment = new Payment();
            updatedPayment.setPaymentMethod(updatedPaymentMethod);
            payments.add(updatedPayment);  // Add the new payment to the list
        }

        System.out.println("Enter updated order items (Enter 0 to stop):");
        List<OrderItems> updatedOrderItems = orderService.collectOrderItems();  // Collect updated order items

        // Calculate updated total cost
        double updatedTotalCost = updatedOrderItems.stream().mapToDouble(item -> item.getPrice() * item.getQuantity()).sum();

        // Set updated status (optional, you can prompt for this)
        String updatedStatus = "Updated";  // Or get this from user input

        Orders updatedOrder = orderService.updateOrder(orderId, updatedOrderItems, payments, updatedTotalCost, updatedStatus);
        if (updatedOrder != null) {
            System.out.println("Order updated successfully: " + updatedOrder);
        } else {
            System.out.println("Failed to update order.");
        }
    }

    // Delete Order (for the logged-in user)
    public void deleteOrder() {
        if (loggedInUser == null) {
            System.out.println("You must be logged in to delete your orders.");
            return;
        }

        System.out.print("Enter order ID to delete: ");
        int orderId = scanner.nextInt();
        scanner.nextLine();  // Consume newline

        Orders orderToDelete = orderService.getOrderById(orderId);
        if (orderToDelete == null || orderToDelete.getUser().getUserID() != loggedInUser.getUserID()) {
            System.out.println("Order not found or you don't have permission to delete this order.");
            return;
        }

        boolean success = orderService.deleteOrder(orderId);
        if (success) {
            System.out.println("Order deleted successfully.");
        } else {
            System.out.println("Failed to delete order.");
        }
    }
}
